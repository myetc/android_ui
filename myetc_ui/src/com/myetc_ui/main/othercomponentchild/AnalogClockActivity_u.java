package com.myetc_ui.main.othercomponentchild;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;

/**
 * ���ð�ť��Ϊ�˼򵥣����ټ򵥡������Ѿ�С�׵Ĳ�����С���ˣ� ���κ��߼����ԣ������ˮ���룡���޴�ͬ��Ŀ¼��
 * 
 * @author Administrator
 * 
 */
public class AnalogClockActivity_u {
	private static final int name_id = R.string.othercomponent_analogclock;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.OTHERCOMPONENT)
	public static class AnalogClockActivity extends BaseActivity {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_othercomponent_analogclock);
			this.changeBar_Title(name_id);
			
		}

	}
}