package com.myetc_ui.main.othercomponentchild;

import java.util.Calendar;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ZoomButton;
import android.widget.ZoomButtonsController;
import android.widget.ZoomControls;

import com.myetc_tool.ImageHelp;
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
public class DataPickerActivity_u {
	private static final int name_id = R.string.othercomponent_datepicker;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.OTHERCOMPONENT)
	public static class DataPickerActivity extends BaseActivity {
		private DatePicker dp1;
		private TimePicker tp1;
		private TextView tv1;
		private TextView tv2;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_othercomponent_datepicker);
			this.changeBar_Title(name_id);
			
			dp1 = (DatePicker) findViewById(R.id.main_othercomponent_datepicker_dp1);
			tp1 = (TimePicker) findViewById(R.id.main_othercomponent_datepicker_tp1);
			tv1 = (TextView) findViewById(R.id.main_othercomponent_datepicker_tv1);
			tv2 = (TextView) findViewById(R.id.main_othercomponent_datepicker_tv2);
			
			Calendar c = Calendar.getInstance();
			final int year = c.get(Calendar.YEAR);
			final int month = c.get(Calendar.MONTH);
			final int day = c.get(Calendar.DAY_OF_MONTH);
			
			dp1.init(year, month, day, new DatePicker.OnDateChangedListener() {
				@Override
				public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
					tv1.setText(year+":"+month+":"+day);
				}
			});
		}


	}
}