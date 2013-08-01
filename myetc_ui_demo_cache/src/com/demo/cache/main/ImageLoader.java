package com.demo.cache.main;


import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.demo.cache.main.MyAdapter.ViewHolder;
import com.demo.cache.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;

public class ImageLoader {
	private static final String TAG = "ImageLoader";
	private static final int MAX_CAPACITY = 10;// 一级缓存的最大空间
	private long cacheTime = 60*1000;
	// 0.75是加载因子为经验值，true则表示按照最近访问量的高低排序，false则表示按照插入顺序排序
	private HashMap<String, Bitmap> mFirstLevelCache = new LinkedHashMap<String, Bitmap>(
			MAX_CAPACITY / 2, 0.75f, true) {

	private static final long serialVersionUID = 1L;

		protected boolean removeEldestEntry(Entry<String, Bitmap> eldest) {
			if (size() > MAX_CAPACITY) {// 当超过一级缓存阈值的时候，将老的值从一级缓存搬到二级缓存
				mSecondLevelCache.put(eldest.getKey(),
						new SoftReference<Bitmap>(eldest.getValue()));
				return true;
			}
			return false;
		};
	};
	// 二级缓存，采用的是软应用，只有在内存吃紧的时候软应用才会被回收，有效的避免了oom
	private ConcurrentHashMap<String, SoftReference<Bitmap>> mSecondLevelCache = new ConcurrentHashMap<String, SoftReference<Bitmap>>(
				MAX_CAPACITY / 2);
	// 定时清理缓存
	private Runnable mClearCache = new Runnable() {
		@Override
		public void run() {
			clear();
		}
	};
	private Handler mPurgeHandler = new Handler();


	/**
	 *  重置缓存清理的timer
	 */
	private void resetPurgeTimer() {
		/*
		 * 方法postDelayed的作用是延迟多少毫秒后开始运行，
		 * 而removeCallbacks方法是删除指定的Runnable对象，使线程对象停止运行。
		 */
		mPurgeHandler.removeCallbacks(mClearCache);
		mPurgeHandler.postDelayed(mClearCache, cacheTime);
	}

	/**
	 * 清理缓存
	 */
	private void clear() {
		mFirstLevelCache.clear();
		mSecondLevelCache.clear();
	}

	/**
	 * 返回缓存，如果没有则返回null
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap getBitmapFromCache(String url,ViewHolder holder) {
		Bitmap bitmap = null;
		bitmap = getFromFirstLevelCache(url);// 从一级缓存中拿
		if (bitmap != null) {
			holder.mImageView.setVisibility(View.VISIBLE);
			holder.pb.setVisibility(View.GONE);
			return bitmap;
		}
		bitmap = getFromSecondLevelCache(url);// 从二级缓存中拿
		return bitmap;
	}

	/**
	 * 从二级缓存中拿
	 * 
	 * @param url
	 * @return
	 */
	private Bitmap getFromSecondLevelCache(String url) {
		Bitmap bitmap = null;
		SoftReference<Bitmap> softReference = mSecondLevelCache.get(url);
		if (softReference != null) {
			bitmap = softReference.get();
			if (bitmap == null) {// 由于内存吃紧，软引用已经被gc回收了
				mSecondLevelCache.remove(url);
			}
		}
		return bitmap;
	}

	/**
	 * 从一级缓存中拿
	 * 
	 * @param url
	 * @return
	 */
	private Bitmap getFromFirstLevelCache(String url) {
		Bitmap bitmap = null;
		synchronized (mFirstLevelCache) {
			bitmap = mFirstLevelCache.get(url);
			if (bitmap != null) {// 将最近访问的元素放到链的头部，提高下一次访问该元素的检索速度（LRU算法）
				mFirstLevelCache.remove(url);
				mFirstLevelCache.put(url, bitmap);
			}
		}
		return bitmap;
	}

	/**
	 * 加载图片，如果缓存中有就直接从缓存中拿，缓存中没有就下载
	 * @param url
	 * @param adapter
	 * @param holder
	 */
	public void loadImage(String url, BaseAdapter adapter, ViewHolder holder,Handler handler) {
		resetPurgeTimer();
		holder.mImageView.setVisibility(View.GONE);
		holder.pb.setVisibility(View.VISIBLE);
		Bitmap bitmap = getBitmapFromCache(url,holder);// 从缓存中读取
		if (bitmap == null) {
			holder.mImageView.setImageResource(R.drawable.noimg);//缓存没有设为默认图片
//			ImageLoadTask imageLoadTask = new ImageLoadTask();
//			imageLoadTask.execute(url, adapter, holder);
			
			new MyThread(url, adapter, holder, handler).start();
		} else {
			holder.mImageView.setImageBitmap(bitmap);//设为缓存图片
		}
//		holder.pb.setVisibility(View.GONE);
	}

	class MyThread extends Thread{
		String url;
		BaseAdapter adapter;
		ViewHolder holder ;
		Handler handler;
		public MyThread(String url, BaseAdapter adapter, ViewHolder holder,Handler handler) {
			this.url = url;
			this.adapter = adapter;
			this.holder = holder;
			this.handler= handler;
		}

		@Override
		public void run() {
			Bitmap drawable = loadImageFromInternet(url);// 获取网络图片
			
			Message msg = new Message();
			
			if (drawable == null) {
//				holder.mImageView.setVisibility(View.VISIBLE);
//				holder.pb.setVisibility(View.GONE);
				msg.what = 0;
				handler.sendMessage(msg);
				return;
			}
			addImage2Cache(url, drawable);// 放入缓存
//			adapter.notifyDataSetChanged();// 触发getView方法执行，这个时候getView实际上会拿到刚刚缓存好的图片
		
			msg.what = 1;
			handler.sendMessage(msg);
		}
	}
	/**
	 * 放入缓存
	 * 
	 * @param url
	 * @param value
	 */
	public void addImage2Cache(String url, Bitmap value) {
		if (value == null || url == null) {
			return;
		}
		synchronized (mFirstLevelCache) {
			mFirstLevelCache.put(url, value);
		}
	}

	class ImageLoadTask extends AsyncTask<Object, Void, Bitmap> {
		String url;
		BaseAdapter adapter;
		ViewHolder holder ;

		@Override
		protected Bitmap doInBackground(Object... params) {
			url = (String) params[0];
			adapter = (BaseAdapter) params[1];
			holder = (ViewHolder) params[2];
			Bitmap drawable = loadImageFromInternet(url);// 获取网络图片
			return drawable;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (result == null) {
				holder.mImageView.setVisibility(View.VISIBLE);
				holder.pb.setVisibility(View.GONE);
				return;
			}
			
			addImage2Cache(url, result);// 放入缓存
			adapter.notifyDataSetChanged();// 触发getView方法执行，这个时候getView实际上会拿到刚刚缓存好的图片
		}
	}
/**
 * 加载网络图片
 * @param url  指定图片地址
 * @return Bitmap 返回图片的对象
 */
	public Bitmap loadImageFromInternet(String url) {
		Bitmap bitmap = null;
		HttpClient client = AndroidHttpClient.newInstance("Android");
		HttpParams params = client.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 3000);
		HttpConnectionParams.setSocketBufferSize(params, 3000);
		HttpResponse response = null;
		InputStream inputStream = null;
		HttpGet httpGet = null;
		try {
			httpGet = new HttpGet(url);
			response = client.execute(httpGet);
			int stateCode = response.getStatusLine().getStatusCode();
			if (stateCode != HttpStatus.SC_OK) {
				Log.d(TAG, "没找到网络图片 --返回值 stateCode：" + stateCode);
				return bitmap;
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try {
					inputStream = entity.getContent();
					return bitmap = BitmapFactory.decodeStream(inputStream);
				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
					entity.consumeContent();
				}
			}
		} catch (ClientProtocolException e) {
			httpGet.abort();
			e.printStackTrace();
		} catch (IOException e) {
			httpGet.abort();
			e.printStackTrace();
		} finally {
			((AndroidHttpClient) client).close();
		}
		return bitmap;
	}

}
