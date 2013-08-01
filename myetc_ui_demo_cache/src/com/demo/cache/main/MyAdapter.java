package com.demo.cache.main;

import java.util.List;

import com.demo.cache.R;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private static final String TAG = "demo_adapter";
	private boolean mBusy = false;
	ViewHolder viewHolder = null;
	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}

	private ImageLoader mImageLoader;
	private int mCount;
	private Context mContext;
	private List<String> list;
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			switch (msg.what) {
			case 0:
				viewHolder.mImageView.setVisibility(View.VISIBLE);
				viewHolder.pb.setVisibility(View.GONE);
//				System.out.println("============msg:重============");
				break;
			case 1:
				notifyDataSetChanged();// 触发getView方法执行，这个时候getView实际上会拿到刚刚缓存好的图片
//				System.out.println("============msg:1============");
				break;
			default:
				break;
			}
		}
		
	};
	public MyAdapter(Context context ,List<String> list) {
		this.mContext = context;
		this.list = list;
		mImageLoader = new ImageLoader();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.demo_cache_list_item, null);// 这个过程相当耗时间
			viewHolder = new ViewHolder();
			viewHolder.mTextView = (TextView) convertView
					.findViewById(R.id.tv_tips);
			viewHolder.mImageView = (ImageView) convertView
					.findViewById(R.id.iv_image);
			viewHolder.pb = (ProgressBar) convertView
					.findViewById(R.id.pb);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String url = "";
		url = (String) list.get(position);
		if (!mBusy) {
//			Log.d(TAG, "=========mBusy:false==============");
			mImageLoader.loadImage(url, this, viewHolder,handler);
		} else {
//			Log.d(TAG, "=========mBusy:true==============");
			Bitmap bitmap = mImageLoader.getBitmapFromCache(url,viewHolder);
			if (bitmap != null) {
				viewHolder.mImageView.setImageBitmap(bitmap);
			} else {
				viewHolder.mImageView.setImageResource(R.drawable.noimg);
			}
		}
		viewHolder.mTextView.setText("第"+position+"条");
		return convertView;
	}

	static class ViewHolder {
		TextView mTextView;
		ImageView mImageView;
		ProgressBar pb;
	}

}
