package com.myetc_ui.main.othercomponentchild;

import java.util.Timer;
import java.util.TimerTask;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;

/**
 * 常用按钮，为了简单，简单再简单。代码已经小白的不能再小白了！ 无任何逻辑可言！完成流水代码！仅限此同级目录。
 * 
 * @author Administrator
 * 
 */
public class ChronometerActivity_u {
	private static final int name_id = R.string.othercomponent_chronometer;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.OTHERCOMPONENT)
	public static class ChronometerActivity extends BaseActivity {
		private Button button1;
		private Chronometer chronometer1;
		private Button button2;
		private Button button3;
		private Button button4;
		private TextView textview1;
		protected int flag = 0;
		private Handler handler;
		private Timer timer;
		protected int mlCount;
		private Button button5;
		private Chronometer chronometer2;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_othercomponent_chronometer);
			this.changeBar_Title(name_id);
			test1();
			test2();
			test3();
		}
		
		
		private void test3() {
			final long logtime = SystemClock.elapsedRealtime();
			button5 = (Button) findViewById(R.id.main_othercomponent_chronometer_button5);	
			chronometer2 = (Chronometer) findViewById(R.id.main_othercomponent_chronometer_chronometer2);
			chronometer2.setBase(logtime);
			chronometer2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
				@Override
				public void onChronometerTick(Chronometer ch) {
					long time = SystemClock.elapsedRealtime()-ch.getBase();
					if(time/1000>=10){
						System.out.println("=========时间到:");
						Toast.makeText(ChronometerActivity.this, "时间到", Toast.LENGTH_SHORT).show();
						chronometer2.setBase(SystemClock.elapsedRealtime());
					}
				}
			});
			
			button5.setOnClickListener(new OnClickListener() {
				@Override
                public void onClick(View v) {
                        // TODO Auto-generated method stub
					chronometer2.start();
                }
        });
		}


		private void test2() {
			button2 = (Button) findViewById(R.id.main_othercomponent_chronometer_button2);
			button3 = (Button) findViewById(R.id.main_othercomponent_chronometer_button3);
			button4 = (Button) findViewById(R.id.main_othercomponent_chronometer_button4);
			textview1 = (TextView) findViewById(R.id.main_othercomponent_chronometer_textview1);
			   button2.setEnabled(true);
               button3.setEnabled(false);
               button4.setEnabled(false);
               
			button2.setOnClickListener(new OnClickListener() {
				@Override
                public void onClick(View v) {
                        // TODO Auto-generated method stub
                        flag = 1;
                        textview1.setText("00:00:0");
                        button2.setEnabled(false);
                        button3.setEnabled(true);
                        button4.setEnabled(true);
                }
        });

			button3.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                        // TODO Auto-generated method stub

                        flag = 0;
                        button2.setEnabled(true);
                        button3.setEnabled(false);
                        button4.setEnabled(true);
                }
        });
			button4.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					button2.setEnabled(true);
					button3.setEnabled(false);
					button4.setEnabled(false);
					 flag = 0;
					 mlCount = 0;
				}
			});

        timer = new Timer();
        timer.schedule(new TimerTask() {

                private Message msg;

				@Override
                public void run() {
                        // TODO Auto-generated method stub
                        msg = new Message();
                        msg.what = flag;
                        handler.sendMessage(msg);
                }
        }, 0, 100);

        handler = new Handler() {
				@Override
                public void handleMessage(Message msg) {
                        // TODO Auto-generated method stub
                        super.handleMessage(msg);

                        if (msg.what == 1) {
                                mlCount++;
                                int totalSec = 0;
                                int yushu = 0;

                                totalSec = mlCount / 10;
                                yushu = mlCount % 10;
                                
                                int min = totalSec / 60;
                                int sec = totalSec % 60;
                                
                                textview1.setText(String.format("%1$02d:%2$02d:%3$d", min, sec, yushu));
                        }
                }

        };
			
		}

		private void test1() {

			chronometer1 = (Chronometer) findViewById(R.id.main_othercomponent_chronometer_chronometer1);
			// Sets the format string used for display. The Chronometer will
			// display this string, with the first "%s" replaced by the current
			// timer value in "MM:SS" or "H:MM:SS" form. If the format string is
			// null, or if you never call setFormat(), the Chronometer will
			// simply display the timer value in "MM:SS" or "H:MM:SS" form.
//			chronometer1.setFormat("计时:%s");
			button1 = (Button) findViewById(R.id.main_othercomponent_chronometer_button1);
			button1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if ("开始".equals(button1.getText().toString())) {
						button1.setText("暂停");
						chronometer1.start();
					} else {
						chronometer1.stop();
						button1.setText("开始");
					}

				}
			});
		}

	}
}