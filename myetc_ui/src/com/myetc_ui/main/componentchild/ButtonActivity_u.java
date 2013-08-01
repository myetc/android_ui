package com.myetc_ui.main.componentchild;

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
public class ButtonActivity_u {
	private static final int name_id = R.string.component_button;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class ButtonActivity extends BaseActivity implements
			OnClickListener {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_button);
			this.changeBar_Title(name_id);
			onclick2();
			onclick3();
			onclick4();
		}

		private void onclick4() {
			Button button4 = (Button) findViewById(R.id.button4);
			button4.setOnClickListener(this);

		}

		private void onclick3() {
			class mybutton implements OnClickListener {

				@Override
				public void onClick(View v) {
					Toast.makeText(ButtonActivity.this,
							"class mybutton implements OnClickListener �ⲿ��ʵ��",
							Toast.LENGTH_SHORT).show();

				}
			}
			Button button3 = (Button) findViewById(R.id.button3);
			button3.setOnClickListener(new mybutton());
			// TODO Auto-generated method stub

		}

		private void onclick2() {
			Button button2 = (Button) findViewById(R.id.button2);
			button2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(
							ButtonActivity.this,
							"setOnClickListener(new View.OnClickListener() {});���ݲ�ʵ��",
							Toast.LENGTH_SHORT).show();

				}
			});
		}

		public void onclick1(View v) {
			Toast.makeText(this, "android:onClick='onclick' ��xml����",
					Toast.LENGTH_SHORT).show();
		}

		/**
		 * ������ť ���ý���״̬ ת�� ������ť��û��Ҫдɨ�裬�Զ���ֱ������򵥵Ĵ���
		 */
		public void onaction(View v) {
			Button button5 = (Button) findViewById(R.id.button5);
			Button button6 = (Button) findViewById(R.id.button6);
			button5.setEnabled(true);
			button6.setEnabled(true);
			button5.setText("����");
			button6.setText("����");
			Button button = (Button) v;
			button.setEnabled(false);
			button.setText("����");
		}
		/**
		 * ������ť ���ý���״̬ ת�� ������ť��û��Ҫдɨ�裬�Զ���ֱ������򵥵Ĵ���
		 */
		public void onaction2(View v) {
			Button button7 = (Button) findViewById(R.id.button7);
			Button button8 = (Button) findViewById(R.id.button8);
			button7.setEnabled(true);
			button8.setEnabled(true);
			button7.setText("����");
			button8.setText("����");
			Button button = (Button) v;
			button.setEnabled(false);
			button.setText("����");
		}

		@Override
		public void onClick(View v) {
			Toast.makeText(
					ButtonActivity.this,
					"ButtonActivity extends BaseActivity implements OnClickListener"
							+ "����ʵ��OnClickListener�ӿ�", Toast.LENGTH_SHORT)
					.show();

		}
	}
}