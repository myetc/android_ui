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
			// �ı�ͼƬ��͸����
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
		 * �϶����������ı�͸���ȣ���ֻ��һ�ְ취�� ʵ������У��õ�Ч����ܶࡣ ���磬��һ��ʱ���ڰ�ͼƬ�Զ������ʲôʲô
		 */
		sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				/*
				 * ������ϴ��룬����һ���򵥵����ӣ������϶���ɺ󣬳�����3S֮�󣬰�ͼƬ��ԭ������
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
