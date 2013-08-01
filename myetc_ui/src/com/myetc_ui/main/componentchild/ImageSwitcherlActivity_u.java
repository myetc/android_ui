package com.myetc_ui.main.componentchild;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.format.Time;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;

/**
 * 常用标签，为了简单，简单再简单。代码已经小白的不能再小白了！ 无任何逻辑可言！完成流水代码！仅限此同级目录。
 * 
 * @author Administrator
 * 
 */
public class ImageSwitcherlActivity_u {
	private static final int name_id = R.string.component_imageswitcher;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class ImageSwitcherlActivity extends BaseActivity {
		private ImageSwitcher is2;
		private Gallery gallery;
		private Integer[] mThumbIds = { R.drawable.girl_1, R.drawable.girl_2,
				R.drawable.girl_3, R.drawable.girl_4, R.drawable.girl_5,
				R.drawable.girl_6, R.drawable.girl_7 };
		private Integer[] mImageIds = { R.drawable.girl_1, R.drawable.girl_2,
				R.drawable.girl_3, R.drawable.girl_4, R.drawable.girl_5,
				R.drawable.girl_6, R.drawable.girl_7 };
		private ImageSwitcher is1;
		private Button button1;
		private ImageSwitcher is3;
		private int nowValue= 0;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_imageswitcher);
			this.changeBar_Title(name_id);

			test1();
			test2();
			test3();
		}

		private void test3() {
			is3 = (ImageSwitcher) findViewById(R.id.main_component_imageswitcher_switcher3);
			is3.setInAnimation(AnimationUtils.loadAnimation(this,
					android.R.anim.fade_in));
			is3.setOutAnimation(AnimationUtils.loadAnimation(this,
					android.R.anim.fade_out));
			
			is3.setFactory(new ViewFactory() {  
		            public View makeView() {  
		              return new ImageView(ImageSwitcherlActivity.this);  
		            }  
		        });  
			

			final Handler handler = new Handler(){

				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					
					switch (msg.what) {
					case 0:
						 is3.setImageResource(mImageIds[nowValue]);
						 nowValue++;
						break;
					default:
						break;
					}
					
				}
				
			};
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					if(nowValue>=mImageIds.length)
						nowValue = 0;
					//在图片源中，一个个的拿，拿完了，重头开始再拿，拿到你电池没电
					//安卓UI是单线程的，换安卓规定的去改变UI
					Message msg = new Message();
					msg.what = 0;
					handler.sendMessage(msg);
					
				}
			}, 100, 1000);//100ms后，第隔1000ms去调用下run
		}

		private void test1() {
			button1 = (Button) findViewById(R.id.main_component_imageswitcher_button1);
			is1 = (ImageSwitcher) findViewById(R.id.main_component_imageswitcher_switcher1);
			is1.setInAnimation(AnimationUtils.loadAnimation(this,
					android.R.anim.slide_in_left));
			is1.setOutAnimation(AnimationUtils.loadAnimation(this,
					android.R.anim.slide_out_right));
			
			  is1.setFactory(new ViewFactory() {  
				  
		            public View makeView() {  
		              return new ImageView(ImageSwitcherlActivity.this);  
		            }  
		        });  
			  //设置个默认地
			  is1.setImageResource(R.drawable.girl_1);
			  is1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					//随机在图片源中抽一一
					int num = new Random().nextInt(mImageIds.length-1);
					 is1.setImageResource(mImageIds[num]);
					 Toast.makeText(ImageSwitcherlActivity.this, "你以为点我，我不会变啊!【"+num+"】", Toast.LENGTH_SHORT).show();;
				}
			} );
			  button1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					//随机在图片源中抽一一
					 is1.setImageResource(mImageIds[new Random().nextInt(mImageIds.length-1)]);
				}
			});
		}

		private void test2() {
			SelectedListener selectedListener = new SelectedListener();
			is2 = (ImageSwitcher) findViewById(R.id.main_component_imageswitcher_switcher2);
			is2.setFactory(selectedListener);

			is2.setInAnimation(AnimationUtils.loadAnimation(this,
					android.R.anim.fade_in));
			is2.setOutAnimation(AnimationUtils.loadAnimation(this,
					android.R.anim.fade_out));

			gallery = (Gallery) findViewById(R.id.main_component_imageswitcher_gallery);

			gallery.setAdapter(new ImageAdapter(this));
			gallery.setOnItemSelectedListener(selectedListener);
		}

		public class SelectedListener implements OnItemSelectedListener,
				ViewFactory {

			@Override
			public View makeView() {
				ImageView i = new ImageView(ImageSwitcherlActivity.this);
				i.setBackgroundColor(0xFF000000);
				i.setScaleType(ImageView.ScaleType.FIT_CENTER);
				i.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				return i;
			}

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				is2.setImageResource(mImageIds[position]);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		}

		public class ImageAdapter extends BaseAdapter {
			public ImageAdapter(Context c) {
				mContext = c;
			}

			public int getCount() {
				return mThumbIds.length;
			}

			public Object getItem(int position) {
				return position;
			}

			public long getItemId(int position) {
				return position;
			}

			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView i = new ImageView(mContext);

				i.setImageResource(mThumbIds[position]);
				i.setAdjustViewBounds(true);
				i.setLayoutParams(new Gallery.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				i.setBackgroundResource(R.drawable.button);
				return i;
			}

			private Context mContext;

		}

	}

}
