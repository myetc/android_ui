package com.myetc_ui.main.componentchild;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
public class EditTextActivity_u {
	private static final int name_id = R.string.component_edittext;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class EditTextActivity extends BaseActivity {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_edittext);
			this.changeBar_Title(name_id);

			EditText editText = (EditText) findViewById(R.id.monitor_edit_text0);
			final TextView textView0 = (TextView) findViewById(R.id.monitor_text0);
			final TextView textView1 = (TextView) findViewById(R.id.monitor_text1);
			final TextView textView2 = (TextView) findViewById(R.id.monitor_text2);

			editText.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence text, int start,
						int before, int count) {
					// text ������иı����ַ�����Ϣ
					// start ������иı����ַ�������ʼλ��
					// before ������иı�ǰ���ַ�����λ�� Ĭ��Ϊ0
					// count ������иı���һ�������ַ���������
					textView1.setText("������ַ��� [ " + text.toString()
							+ " ] ��ʼ��� [ " + start + " ] �������� [ " + count
							+ " ]");

				}

				@Override
				public void beforeTextChanged(CharSequence text, int start,
						int count, int after) {
					// text ������иı�ǰ���ַ�����Ϣ
					// start ������иı�ǰ���ַ�������ʼλ��
					// count ������иı�ǰ����ַ����ı�����һ��Ϊ0
					// after ������иı����ַ�������ʼλ�õ�ƫ����
					System.out.println(text.toString());
					textView0.setText("����ǰ�ַ��� [ " + text.toString()
							+ " ]��ʼ��� [ " + start + " ]����ƫ����  [" + after + " ]");
				}

				@Override
				public void afterTextChanged(Editable edit) {
					// edit �������������������е���Ϣ
					textView2.setText("��������������Ϊ [" + edit.toString()
							+ " ] ������ʾ����Ļ��");
				}
			});

		}

		public void show0(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text0)))
							.getText(), Toast.LENGTH_SHORT).show();
		}

		public void show1(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text1)))
							.getText(), Toast.LENGTH_SHORT).show();
		}

		public void show2(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text2)))
							.getText(), Toast.LENGTH_SHORT).show();
		}

		public void show3(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text3)))
							.getText(), Toast.LENGTH_SHORT).show();
		}

		public void show4(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text4)))
							.getText(), Toast.LENGTH_SHORT).show();
		}

		public void show5(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text5)))
							.getText(), Toast.LENGTH_SHORT).show();
		}

		public void show6(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text6)))
							.getText(), Toast.LENGTH_SHORT).show();
		}
	}
}