package com.myetc_ui.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.DigitalClock;

public class MyDigitalClock extends DigitalClock {
	Calendar mCalendar;
	private final static String mFormat12 = "EEEE,MMMM-dd-yyyy hh:mm:ss aa";// h:mm:ss
																			// aa
	private final static String mFormat24 = "EEEE,MMMM-dd-yyyy kk:mm:ss";// h:mm:ss
																			// aa
	private FormatChangeObserver mFormatChangeObserver;

	private Runnable mTicker;
	private Handler mHandler;

	private boolean mTickerStopped = false;
	String mFormat = "k:mm:ss";// h:mm:ss aa
	private static String[] weekdays = { "星期日", "星期一", "星期二", "星期三", "星期四",
			"星期五", "星期六" };

	public MyDigitalClock(Context context) {
		super(context);
		initClock(context);
	}

	public MyDigitalClock(Context context, AttributeSet attrs) {
		super(context, attrs);
		initClock(context);
	}

	private void initClock(Context context) {
		Resources r = context.getResources();
		if (mCalendar == null) {
			mCalendar = Calendar.getInstance();
		}
		mFormatChangeObserver = new FormatChangeObserver();
		getContext().getContentResolver().registerContentObserver(
				Settings.System.CONTENT_URI, true, mFormatChangeObserver);

	}

	@Override
	protected void onAttachedToWindow() {
		mTickerStopped = false;
		super.onAttachedToWindow();

		mHandler = new Handler();

		mTicker = new Runnable() {
			@Override
			public void run() {
				if (mTickerStopped) {
					return;
				}
				mCalendar.setTimeInMillis(System.currentTimeMillis());
				setText(DateFormat.format(mFormat, mCalendar));
/**
 * 拼结才是王道 ！
 */
				
				int myear = (mCalendar.get(Calendar.YEAR));

				int mmonth = (mCalendar.get(Calendar.MONTH) + 1);// 月份+1是一年中的第几个月

				int mmonthday = (mCalendar.get(Calendar.DAY_OF_MONTH));// 一月中的日期

				final int mweekday = (mCalendar.get(Calendar.DAY_OF_WEEK)) - 1;

				final String mDate = format(myear) + "-" + format(mmonth) + "-"
						+ format(mmonthday);

				setText(DateFormat.format(mFormat, mCalendar) + "  " + mDate
						+ " " + weekdays[mweekday]);

				invalidate();
				long now = SystemClock.uptimeMillis();
				long next = now + (1000 - now % 1000);
				mHandler.postAtTime(mTicker, next);
			}
		};
		mTicker.run();
	}

	/**
	 * Pulls 12/24 mode from system settings
	 */
	private boolean get24HourMode() {
		return android.text.format.DateFormat.is24HourFormat(getContext());
	}

	private void setFormat() {
		if (get24HourMode()) {
			mFormat = mFormat24;
		} else {
			mFormat = mFormat12;
		}
	}

	private static String format(int t) {

		String s = "" + t;

		if (s.length() == 1) {

			s = "0" + s;

		}

		return s;

	}

	private class FormatChangeObserver extends ContentObserver {

		public FormatChangeObserver() {
			super(new Handler());
		}

		@Override
		public void onChange(boolean selfChange) {
			setFormat();
		}
	}
}