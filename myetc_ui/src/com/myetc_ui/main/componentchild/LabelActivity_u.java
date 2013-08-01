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
public class LabelActivity_u {
	private static final int name_id = R.string.component_label;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class LabelActivity extends BaseActivity {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_label);
			this.changeBar_Title(name_id);
			style1();
//			style2();
			
		}

		private void style2() {
			TextView tv = (TextView) findViewById(R.id.text2);
			AssetManager mgr = getAssets();// �õ�AssetManager
			Typeface tf = Typeface.createFromAsset(mgr, "fonts/hksn.ttf");// ����·���õ�Typeface
			tv.setTypeface(tf);
		}

		private void style1() {
			TextView tv = (TextView) findViewById(R.id.text1);
			String textStr1 = "<font color=\"#ffff00\">�����һ�죬</font><br>";
			String textStr2 = "<font color=\"#00ff00\">����������</font><br>";
			String textStr3 = "<font color=\"#ff00ff\">�һ�����Σ�</font><br>";
			String textStr4 = "<font color=\"#00ffff\">�����Ǹ������Լ���<br>���硭��</font><br>";
			tv.setText(Html.fromHtml(textStr1 + textStr2 + textStr3 + textStr4));
		}

	}
}