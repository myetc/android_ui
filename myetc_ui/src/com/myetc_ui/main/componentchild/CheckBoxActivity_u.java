package com.myetc_ui.main.componentchild;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
public class CheckBoxActivity_u {
	private static final int name_id = R.string.component_checkbox;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class CheckBoxActivity extends BaseActivity {
		boolean state = false;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_checkbox);
			this.changeBar_Title(name_id);
		}
		public void select_or_clean(View v) {
		CheckBox cb1=	(CheckBox) findViewById(R.id.cb1);
		CheckBox cb2=	(CheckBox) findViewById(R.id.cb2);
		CheckBox cb3=	(CheckBox) findViewById(R.id.cb3);
		CheckBox cb4=	(CheckBox) findViewById(R.id.cb4);
		CheckBox[] cb_arr = {cb1,cb2,cb3,cb4};
		CheckBox cb_all = (CheckBox) v;
			
			for (int i = 0; i < cb_arr.length; i++) {
				cb_arr[i].setChecked(cb_all.isChecked());
			}
			
			

		}
		public void inverse(View v) {
		CheckBox cb1=	(CheckBox) findViewById(R.id.cb1);
		CheckBox cb2=	(CheckBox) findViewById(R.id.cb2);
		CheckBox cb3=	(CheckBox) findViewById(R.id.cb3);
		CheckBox cb4=	(CheckBox) findViewById(R.id.cb4);
		CheckBox[] cb_arr = {cb1,cb2,cb3,cb4};
			
			for (int i = 0; i < cb_arr.length; i++) {
				cb_arr[i].setChecked(!cb_arr[i].isChecked());
			}
			
			

		}
		
	}
}