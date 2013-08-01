package com.demo.image.main;

import java.util.Random;

import com.demo.image.R;
import com.myetc_tool.ImageHelp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	int[] image_arr = { R.drawable.image_array1, R.drawable.image_array2,
			R.drawable.image_array3, R.drawable.image_array4,
			R.drawable.image_array5, R.drawable.image_array6,

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("==============进来了====");
		setContentView(R.layout.demo_image_main);
	}

	public void changeimg(View v) {
		int index = new Random().nextInt(image_arr.length);
		Toast.makeText(this, "更换图片"+index , Toast.LENGTH_SHORT).show();
		ImageView imageView = (ImageView) findViewById(R.id.demo_image_main_image1);;
		imageView.setImageResource(image_arr[index]);
//		imageView.setBackgroundResource(R.drawable.ic_launcher);
	}
	public void getimg(View v) {
		int key = new Integer(v.getTag().toString());
		ImageView imageView1 = (ImageView) findViewById(R.id.demo_image_main_image1);
		ImageView imageView2 = (ImageView) findViewById(R.id.demo_image_main_image2);
		switch (key) {
		case 1:
			//最简单的办法，得到Drawable，给另一个组件
			Drawable d = imageView1.getDrawable();
			imageView2.setImageDrawable(d);
			imageView1.getDrawable();
			break;
		case 2:
			//转了一圈，主要目的不是为了赋予图片显示，而是用另一种方法去实现。Drawable——转换——Bitmap。很多时候需要用到
			Drawable d2 = imageView1.getDrawable();
			BitmapDrawable bd = (BitmapDrawable) d2;
			Bitmap b= bd.getBitmap();
			imageView2.setImageBitmap(b);
			
			break;	
		case 3:
			
					
				break;
		default:
			break;
		}
		
		
	}
	public void getotherimg(View v) {
		int key = new Integer(v.getTag().toString());
		ImageView imageView3 = (ImageView) findViewById(R.id.demo_image_main_image3);
		switch (key) {
		case 1:
		 TextView tv1 = (TextView) findViewById(R.id.demo_image_main_text1);
			new  AsyncTask<Object, Void, Bitmap>(){
				private ImageView iv ;
				private String url;
				@Override
				protected Bitmap doInBackground(Object... params) {
					iv = (ImageView) params[0];
					url = (String) params[1];
					return ImageHelp.loadImageFromInternet(url);
				}
				@Override
				protected void onPostExecute(Bitmap result) {
					System.out.println("baidu:"+result);
					if(result!=null)
					iv.setImageBitmap(result);
					else
					iv.setImageResource(R.drawable.noimg);
					super.onPostExecute(result);
				}
				
			}.execute(imageView3,tv1.getText().toString());
			break;
			case 2:
				TextView tv2 = (TextView) findViewById(R.id.demo_image_main_text2);
				Bitmap b = BitmapFactory.decodeFile(tv2.getText().toString());
				if(b!=null) 
				imageView3.setImageBitmap(new BitmapDrawable(b).getBitmap());
				else
				imageView3.setImageResource(R.drawable.noimg);
			break;
		default:
			break;
		}
	}
}
