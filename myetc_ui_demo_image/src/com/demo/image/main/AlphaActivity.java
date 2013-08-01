package com.demo.image.main;

import java.util.Timer;
import java.util.TimerTask;

import com.demo.image.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.SeekBar;

public class AlphaActivity extends Activity {
	private ImageView imageview1;
	private SeekBar sb1;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// 改变图片的透明度
			super.handleMessage(msg);
				imageview1.setAlpha(msg.arg1);
				System.out.println(msg.arg1);
//				imageview1.setAlpha(255);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_image_alpha);

		imageview1 = (ImageView) findViewById(R.id.demo_image_alpha_image1);

		sb1 = (SeekBar) findViewById(R.id.demo_image_alpha_seekbar1);
		final Timer timer = new Timer();
		/*
		 * 拖动进度条来改变透明度，这只是一种办法。 实际情况中，用到效果会很多。 比如，在一定时间内把图片自动处理成什么什么
		 */
		sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				/*
				 * 下面这断代码，就是一个简单的例子，当你拖动完成后，程序将在3S之后，把图片还原成正常
				 * 
				 */
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						int num = AlphaActivity.this.sb1.getProgress() + 1;
						if (num >= AlphaActivity.this.sb1.getMax()) {
							this.cancel();
							return;
						}
						AlphaActivity.this.sb1.setProgress(num);
					}
				}, 1000, 10);

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				Message msg = new Message();
				msg.arg1 = progress;
				handler.sendMessage(msg);

			}
		});

	}

}
