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
	private static final int MAX_CAPACITY = 10;// һ����������ռ�
	private long cacheTime = 60*1000;
	// 0.75�Ǽ�������Ϊ����ֵ��true���ʾ��������������ĸߵ�����false���ʾ���ղ���˳������
	private HashMap<String, Bitmap> mFirstLevelCache = new LinkedHashMap<String, Bitmap>(
			MAX_CAPACITY / 2, 0.75f, true) {

	private static final long serialVersionUID = 1L;

		protected boolean removeEldestEntry(Entry<String, Bitmap> eldest) {
			if (size() > MAX_CAPACITY) {// ������һ��������ֵ��ʱ�򣬽��ϵ�ֵ��һ������ᵽ��������
				mSecondLevelCache.put(eldest.getKey(),
						new SoftReference<Bitmap>(eldest.getValue()));
				return true;
			}
			return false;
		};
	};
	// �������棬���õ�����Ӧ�ã�ֻ�����ڴ�Խ���ʱ����Ӧ�òŻᱻ���գ���Ч�ı�����oom
	private ConcurrentHashMap<String, SoftReference<Bitmap>> mSecondLevelCache = new ConcurrentHashMap<String, SoftReference<Bitmap>>(
				MAX_CAPACITY / 2);
	// ��ʱ������
	private Runnable mClearCache = new Runnable() {
		@Override
		public void run() {
			clear();
		}
	};
	private Handler mPurgeHandler = new Handler();


	/**
	 *  ���û��������timer
	 */
	private void resetPurgeTimer() {
		/*
		 * ����postDelayed���������ӳٶ��ٺ����ʼ���У�
		 * ��removeCallbacks������ɾ��ָ����Runnable����ʹ�̶߳���ֹͣ���С�
		 */
		mPurgeHandler.removeCallbacks(mClearCache);
		mPurgeHandler.postDelayed(mClearCache, cacheTime);
	}

	/**
	 * ������
	 */
	private void clear() {
		mFirstLevelCache.clear();
		mSecondLevelCache.clear();
	}

	/**
	 * ���ػ��棬���û���򷵻�null
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap getBitmapFromCache(String url,ViewHolder holder) {
		Bitmap bitmap = null;
		bitmap = getFromFirstLevelCache(url);// ��һ����������
		if (bitmap != null) {
			holder.mImageView.setVisibility(View.VISIBLE);
			holder.pb.setVisibility(View.GONE);
			return bitmap;
		}
		bitmap = getFromSecondLevelCache(url);// �Ӷ�����������
		return bitmap;
	}

	/**
	 * �Ӷ�����������
	 * 
	 * @param url
	 * @return
	 */
	private Bitmap getFromSecondLevelCache(String url) {
		Bitmap bitmap = null;
		SoftReference<Bitmap> softReference = mSecondLevelCache.get(url);
		if (softReference != null) {
			bitmap = softReference.get();
			if (bitmap == null) {// �����ڴ�Խ����������Ѿ���gc������
				mSecondLevelCache.remove(url);
			}
		}
		return bitmap;
	}

	/**
	 * ��һ����������
	 * 
	 * @param url
	 * @return
	 */
	private Bitmap getFromFirstLevelCache(String url) {
		Bitmap bitmap = null;
		synchronized (mFirstLevelCache) {
			bitmap = mFirstLevelCache.get(url);
			if (bitmap != null) {// ��������ʵ�Ԫ�طŵ�����ͷ���������һ�η��ʸ�Ԫ�صļ����ٶȣ�LRU�㷨��
				mFirstLevelCache.remove(url);
				mFirstLevelCache.put(url, bitmap);
			}
		}
		return bitmap;
	}

	/**
	 * ����ͼƬ������������о�ֱ�Ӵӻ������ã�������û�о�����
	 * @param url
	 * @param adapter
	 * @param holder
	 */
	public void loadImage(String url, BaseAdapter adapter, ViewHolder holder,Handler handler) {
		resetPurgeTimer();
		holder.mImageView.setVisibility(View.GONE);
		holder.pb.setVisibility(View.VISIBLE);
		Bitmap bitmap = getBitmapFromCache(url,holder);// �ӻ����ж�ȡ
		if (bitmap == null) {
			holder.mImageView.setImageResource(R.drawable.noimg);//����û����ΪĬ��ͼƬ
//			ImageLoadTask imageLoadTask = new ImageLoadTask();
//			imageLoadTask.execute(url, adapter, holder);
			
			new MyThread(url, adapter, holder, handler).start();
		} else {
			holder.mImageView.setImageBitmap(bitmap);//��Ϊ����ͼƬ
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
			Bitmap drawable = loadImageFromInternet(url);// ��ȡ����ͼƬ
			
			Message msg = new Message();
			
			if (drawable == null) {
//				holder.mImageView.setVisibility(View.VISIBLE);
//				holder.pb.setVisibility(View.GONE);
				msg.what = 0;
				handler.sendMessage(msg);
				return;
			}
			addImage2Cache(url, drawable);// ���뻺��
//			adapter.notifyDataSetChanged();// ����getView����ִ�У����ʱ��getViewʵ���ϻ��õ��ոջ���õ�ͼƬ
		
			msg.what = 1;
			handler.sendMessage(msg);
		}
	}
	/**
	 * ���뻺��
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
			Bitmap drawable = loadImageFromInternet(url);// ��ȡ����ͼƬ
			return drawable;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (result == null) {
				holder.mImageView.setVisibility(View.VISIBLE);
				holder.pb.setVisibility(View.GONE);
				return;
			}
			
			addImage2Cache(url, result);// ���뻺��
			adapter.notifyDataSetChanged();// ����getView����ִ�У����ʱ��getViewʵ���ϻ��õ��ոջ���õ�ͼƬ
		}
	}
/**
 * ��������ͼƬ
 * @param url  ָ��ͼƬ��ַ
 * @return Bitmap ����ͼƬ�Ķ���
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
				Log.d(TAG, "û�ҵ�����ͼƬ --����ֵ stateCode��" + stateCode);
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
