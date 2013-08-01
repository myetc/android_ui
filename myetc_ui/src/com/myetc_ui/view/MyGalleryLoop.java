package com.myetc_ui.view;

import android.content.Context;
import android.widget.Gallery;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.myetc_ui.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class MyGalleryLoop extends BaseAdapter {
	private Context mContext;
	private ArrayList<Integer> imgList;

	public MyGalleryLoop(Context c ,ArrayList<Integer> imgList){
		mContext = c;
		this.imgList =imgList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub

		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		
		ImageView i = new ImageView(mContext);
		// 从imgList取得图片ID
		i.setImageResource(imgList.get(position%(imgList.size())));
//		i.setScaleType(ImageView.ScaleType.FIT_XY);
		//如果你要统一图片的大小
//		i.setLayoutParams(new Gallery.LayoutParams(100,100));
		return i;
	}

}
