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
	/* 相关变量声明 */
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
		/* 加载main.xml Layout */
		setContentView(R.layout.demo_image_change);

		/* 取得屏幕分辨率大小 */
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		displayWidth = dm.widthPixels;
		/* 屏幕高度须扣除下方Button高度 */
		displayHeight = dm.heightPixels - 80;
		/* 初始化相关变量 */
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
			/* 如果是第一次按，就删除原来默认的ImageView */
			layout1.removeView(mImageView);
		} else {
			/* 如果不是第一次按，就删除上次放大缩小所产生的ImageView */
			layout1.removeView((ImageView) findViewById(id));
		}
		/* 产生新的ImageView，放入reSize的Bitmap对象，再放入Layout中 */
		id++;
		ImageView imageView = new ImageView(this);
		imageView.setId(id);
		imageView.setImageBitmap(resizeBmp);
		layout1.addView(imageView);

	}



}
