package com.demo.image.main;

import java.util.Timer;
import java.util.TimerTask;

import com.demo.image.R;
import com.myetc_tool.ImageHelp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ChangeActivity extends Activity {
	/* ��ر������� */
	private ImageView mImageView;
	private Button mButton_big;
	private Button mButton_small;
	private Button mButton_left;
	private Button mButton_right;
	private RelativeLayout layout1;
	private Bitmap bmp;
	private int id = 0;
	private int displayWidth;
	private int displayHeight;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����main.xml Layout */
		setContentView(R.layout.demo_image_change);

		/* ȡ����Ļ�ֱ��ʴ�С */
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		displayWidth = dm.widthPixels;
		/* ��Ļ�߶���۳��·�Button�߶� */
		displayHeight = dm.heightPixels - 80;
		/* ��ʼ����ر��� */
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.noimg);
		layout1 = (RelativeLayout) findViewById(R.id.demo_image_change_relative);

		mImageView = (ImageView) findViewById(R.id.demo_image_change_image1);

	}

	public void changeImage(View v) {
		int key = new Integer(v.getTag().toString());
		Bitmap resizeBmp = null;
		switch (key) {
		case 1:
			resizeBmp = ImageHelp.createImageRotationAndScaling(bmp, 1.25, 0,10,10,displayWidth,displayHeight);
			break;
		case 2:
			resizeBmp =  ImageHelp.createImageRotationAndScaling(bmp, 0.8, 0,10,10,displayWidth,displayHeight);
			break;
		case 3:
			resizeBmp =  ImageHelp.createImageRotationAndScaling(bmp, 1, -5,10,10,displayWidth,displayHeight);
			break;
		case 4:
			resizeBmp =  ImageHelp.createImageRotationAndScaling(bmp, 1, 5,10,10,displayWidth,displayHeight);
			break;
		default:
			break;
		}

		if (id == 0) {
			/* ����ǵ�һ�ΰ�����ɾ��ԭ��Ĭ�ϵ�ImageView */
			layout1.removeView(mImageView);
		} else {
			/* ������ǵ�һ�ΰ�����ɾ���ϴηŴ���С��������ImageView */
			layout1.removeView((ImageView) findViewById(id));
		}
		/* �����µ�ImageView������reSize��Bitmap�����ٷ���Layout�� */
		id++;
		ImageView imageView = new ImageView(this);
		imageView.setId(id);
		imageView.setImageBitmap(resizeBmp);
		layout1.addView(imageView);

	}



}
