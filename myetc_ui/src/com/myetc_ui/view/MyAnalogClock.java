package com.myetc_ui.view;

/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.TimeZone;

import com.myetc_ui.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews.RemoteView;


/**
 * This widget display an analogic clock with two hands for hours and minutes.
 */
@RemoteView
public class MyAnalogClock extends View {
	private Time mCalendar;
	private Drawable mHourHand;
	private Drawable mMinuteHand;
	private Drawable mDial;

	private int mDialWidth;
	private int mDialHeight;

	private boolean mAttached;

	private float mMinutes;
	private float mHour;

	private boolean mChanged;

	// /������������ʾ���õ������
	private static String debug = "MyAnalogClock";

	private static int SECONDS_FLAG = 0;
	private Message secondsMsg;
	private float mSeconds;
	// /////end

	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				onTimeChanged();// ���»�ȡ��ϵͳ�ĵ�ǰʱ�䣬�õ�ʱ���֣���
				invalidate();// ǿ�ƻ��ƣ����������onDraw();
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	public MyAnalogClock(Context context) {
		this(context, null);
	}

	public MyAnalogClock(Context context, AttributeSet attrs) {
		this(context, attrs, 0);

	}

	public MyAnalogClock(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		Resources r = context.getResources();

		if (mDial == null) {
			mDial = r.getDrawable(R.drawable.clock_dial);
		}

		if (mHourHand == null) {
			mHourHand = r.getDrawable(R.drawable.clock_hand_hour);
		}

		if (mMinuteHand == null) {
			mMinuteHand = r.getDrawable(R.drawable.clock_hand_minute);
		}

		mCalendar = new Time();

		mDialWidth = mDial.getIntrinsicWidth();
		mDialHeight = mDial.getIntrinsicHeight();

	}

	@Override
	/*
	 * * ������������
	 */
	protected void onAttachedToWindow() {
//		//Log.e(debug, "onAttachedToWindow");
		super.onAttachedToWindow();
		if (!mAttached) {
			mAttached = true;
			// /////////�޸ĵ�������ʾ�󣬲���Ҫ�㲥������
			// IntentFilter filter = new IntentFilter();
			//
			// filter.addAction(Intent.ACTION_TIME_TICK);// ÿ��һ���ӻᷢ��������һ��action
			// filter.addAction(Intent.ACTION_TIME_CHANGED);//
			// �ⲿ�޸�ϵͳʱ�䣬����������action
			// filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);//
			// �ⲿ�޸�ϵͳ��ʱ��������action

			// getContext().registerReceiver(mIntentReceiver, filter);
			// null,handler ������������ʱ��Ч����ȥ����
			// ///////end
		}

		// NOTE: It's safe to do these after registering the receiver since the
		// receiver always runs
		// in the main thread, therefore the receiver can't run before this
		// method returns.

		// The time zone may have changed while the receiver wasn't registered,
		// so update the Time
		mCalendar = new Time();

		// Make sure we update to the current time
		onTimeChanged();

		initSecondsThread();
	}

	private void initSecondsThread() {
		secondsMsg = mHandler.obtainMessage(SECONDS_FLAG);
		Thread newThread = new Thread() {
			@Override
			public void run() {
				while (mAttached) {
					// ��������Ϣ�����»�ȡ�Ļ���
					// ����һ��this message is already in use ���쳣
					secondsMsg = mHandler.obtainMessage(SECONDS_FLAG);
					// /end
					mHandler.sendMessage(secondsMsg);
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		};
		newThread.start();

	}

	@Override
	/*
	 * * ���봰�� �ڰ�home����������������¼����������Ӧ�õļ������ǳ��������š�
	 * ����ⲿ�޸�ϵͳ�¼���action=Intent.ACTION_TIME_CHANGED ��back�����������¼����´δ�onCreate��������
	 */
	protected void onDetachedFromWindow() {
//		Log.e(debug, "onDetachedFromWindow");
		super.onDetachedFromWindow();
		if (mAttached) {
			// getContext().unregisterReceiver(mIntentReceiver); �������룬����Ҫ�������ˡ�
			mAttached = false;
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		Log.e(debug, "onMeasure");
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		float hScale = 1.0f;
		float vScale = 1.0f;

		if (widthMode != MeasureSpec.UNSPECIFIED && widthSize < mDialWidth) {
			hScale = (float) widthSize / (float) mDialWidth;
		}

		if (heightMode != MeasureSpec.UNSPECIFIED && heightSize < mDialHeight) {
			vScale = (float) heightSize / (float) mDialHeight;
		}

		float scale = Math.min(hScale, vScale);

		setMeasuredDimension(resolveSize((int) (mDialWidth * scale),
				widthMeasureSpec), resolveSize((int) (mDialHeight * scale),
				heightMeasureSpec));
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//		Log.e(debug, "onSizeChanged");
		super.onSizeChanged(w, h, oldw, oldh);
		mChanged = true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//Log.e(debug, "canvas");
		boolean changed = mChanged;
		if (changed) {
			mChanged = false;
		}

		int availableWidth = getWidth();
		int availableHeight = getHeight();

		int x = availableWidth / 2;
		int y = availableHeight / 2;

		final Drawable dial = mDial;
		int w = dial.getIntrinsicWidth();
		int h = dial.getIntrinsicHeight();

		boolean scaled = false;

		if (availableWidth < w || availableHeight < h) {
			scaled = true;
			float scale = Math.min((float) availableWidth / (float) w,
					(float) availableHeight / (float) h);
			canvas.save();
			canvas.scale(scale, scale, x, y);
		}

		if (changed) {
			dial.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y + (h / 2));
		}
		dial.draw(canvas);
		// //////�����ǻ���12��Сʱ����ͼ

		canvas.save();
		canvas.rotate(mHour / 12.0f * 360.0f, x, y);

		final Drawable hourHand = mHourHand;
		if (changed) {
			w = hourHand.getIntrinsicWidth();
			h = hourHand.getIntrinsicHeight();
			hourHand.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y
					+ (h / 2));
		}
		hourHand.draw(canvas);
		canvas.restore();
		// //////�����ǻ���ʱ��

		canvas.save();
		canvas.rotate(mMinutes / 60.0f * 360.0f, x, y);

		final Drawable minuteHand = mMinuteHand;
		if (changed) {
			w = minuteHand.getIntrinsicWidth();
			h = minuteHand.getIntrinsicHeight();
			minuteHand.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y
					+ (h / 2));
		}
		minuteHand.draw(canvas);
		canvas.restore();
		// //////�����ǻ��Ʒ���

		// /��������Ļ���
		canvas.save();
		canvas.rotate(mSeconds / 60.0f * 360.0f, x, y);

		final Drawable secondHand = mMinuteHand;// //��ʱ������������
		if (changed) {
			w = secondHand.getIntrinsicWidth();
			h = secondHand.getIntrinsicHeight();
			secondHand.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y
					+ (h / 2));
		}
		secondHand.draw(canvas);
		canvas.restore();
		// /////end

		if (scaled) {
			canvas.restore();
		}
	}

	/**
	 * �ı�ʱ��
	 */
	private void onTimeChanged() {
		//Log.e(debug, "onTimeChanged");
		mCalendar.setToNow();// ///��ȡ�ֻ�����ĵ�ǰʱ�䣬����ʵ���еı�׼�ı���ʱ��

		int hour = mCalendar.hour;// Сʱ
		int minute = mCalendar.minute;// ����
		int second = mCalendar.second;// ��

		mSeconds = second;
		mMinutes = minute + second / 60.0f;
		mHour = hour + mMinutes / 60.0f;

		mChanged = true;
	}

	/**
	 * �����������ֻ��������action�� 1.Intent.ACTION_TIME_TICK��ÿ���ӷ���һ��
	 * 2.Intent.ACTION_TIME_CHANGE�� �ⲿ�޸�ϵͳʱ�� 3.Intent.ACTION_TIMEZONE_CHANGED
	 * �ⲿϵͳ��ʱ�� ��home�����ܼ������� ��back����������
	 */
	private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			//Log.e(debug, "onReceive");
			//Log.e(debug, "intent action=" + intent.getAction());
			if (intent.getAction().equals(Intent.ACTION_TIMEZONE_CHANGED)) {
				String tz = intent.getStringExtra("time-zone");
				mCalendar = new Time(TimeZone.getTimeZone(tz).getID());
			}
			onTimeChanged();
			invalidate();
		}
	};
}
