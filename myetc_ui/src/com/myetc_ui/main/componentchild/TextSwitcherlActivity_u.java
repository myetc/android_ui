package com.myetc_ui.main.componentchild;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
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
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;

/**
 * ���ñ�ǩ��Ϊ�˼򵥣����ټ򵥡������Ѿ�С�׵Ĳ�����С���ˣ� ���κ��߼����ԣ������ˮ���룡���޴�ͬ��Ŀ¼��
 * 
 * @author Administrator
 * 
 */
public class TextSwitcherlActivity_u {
	private static final int name_id = R.string.component_textswitcher;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class TextSwitcherlActivity extends BaseActivity {
		private TextSwitcher ts1;
		private Gallery gallery;
		private String[] mImageIds = { "��","��","��","��","��","��"};
		private int nowValue= 0;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_textswitcher);
			this.changeBar_Title(name_id);

			test3();
		}

		private void test3() {
			ts1 = (TextSwitcher) findViewById(R.id.main_component_textswitcher_switcher1);
			ts1.setInAnimation(AnimationUtils.loadAnimation(this,
					android.R.anim.fade_in));
			ts1.setOutAnimation(AnimationUtils.loadAnimation(this,
					android.R.anim.fade_out));
			
			ts1.setFactory(new ViewFactory() {  
					public View makeView() {  
		            	TextView tv = new TextView(TextSwitcherlActivity.this);
		            	tv.setTextSize(240);
		            	tv.setTextColor(new Color().BLUE);
		              return tv;  
		            }  
		        });  
			

			final Handler handler = new Handler(){

				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					
					switch (msg.what) {
					case 0:
						 ts1.setText(mImageIds[nowValue]);
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
					//��ͼƬԴ�У�һ�������ã������ˣ���ͷ��ʼ���ã��õ�����û��
					//��׿UI�ǵ��̵߳ģ�����׿�涨��ȥ�ı�UI
					Message msg = new Message();
					msg.what = 0;
					handler.sendMessage(msg);
					
				}
			}, 100, 1000);//100ms�󣬵ڸ�1000msȥ������run
		}





	}

}
