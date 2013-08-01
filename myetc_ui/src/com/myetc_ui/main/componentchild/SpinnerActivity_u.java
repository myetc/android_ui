package com.myetc_ui.main.componentchild;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.adapter.NewSpinnerAdapter;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.bean.NewSpinner;
import com.myetc_ui.R;

/**
 * ���ñ�ǩ��Ϊ�˼򵥣����ټ򵥡������Ѿ�С�׵Ĳ�����С���ˣ� ���κ��߼����ԣ������ˮ���룡���޴�ͬ��Ŀ¼��
 * 
 * @author Administrator
 * 
 */
public class SpinnerActivity_u {
	private static final int name_id = R.string.component_spinner;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class SpinnerActivity extends BaseActivity {
		boolean state = false;
		private Spinner spinner;
		private ArrayAdapter<String> adapter;
		private TextView view;
		private final String[] m = { "A��", "B��", "O��", "AB��", "����" };
		private Spinner spinner2;
		private TextView view2;
		private ArrayAdapter adapter2;
		private Spinner spinner3;
		private TextView view3;
		private ArrayAdapter<CharSequence> adapter3;
		private Spinner spinner4;
		private TextView view4;
		private Button button51;
		private TextView view51;
		
		String[] citys = { "������", "�Ϻ���", "�����", "������", "������", "������", "������",
		"������" };

		List<NewSpinner> list = new ArrayList<NewSpinner>();
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_spinner);
			this.changeBar_Title(name_id);

			for (int i = 0; i < 20; i++) {
				list.add(new NewSpinner("����ϴ�����"+i,
						"��С����Ϣ͸¶�����������Ҫ�����ˡ�ϲ���ǲ�ϲ�����˾�֪����", "����", "С����",
						9999));
			}
			test1();
			test2();
			test3();
			test4();
			test5();
		}

		private void test1() {
			spinner = (Spinner) findViewById(R.id.spinner_spinner1);
			view = (TextView) findViewById(R.id.spinner_text1);
			// ����ѡ������ArrayAdapter��������
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, m);

			// ���������б�ķ��
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			// ��adapter ��ӵ�spinner��
			spinner.setAdapter(adapter);

			// ����¼�Spinner�¼�����
			spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

			// ����Ĭ��ֵ
			spinner.setVisibility(View.VISIBLE);
		}

		private void test2() {

			spinner2 = (Spinner) findViewById(R.id.spinner_spinner2);
			view2 = (TextView) findViewById(R.id.spinner_text2);
			// ����ѡ������ArrayAdapter��������
			adapter2 = ArrayAdapter.createFromResource(this,
					R.array.spinner_source1,
					android.R.layout.simple_spinner_item);
			// ���������б�ķ��
			adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// ��adapter ��ӵ�spinner��
			spinner2.setAdapter(adapter2);
			// ����¼�Spinner�¼�����
			spinner2.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
			// ����Ĭ��ֵ
			spinner2.setVisibility(View.VISIBLE);

		}

		private void test3() {

			spinner3 = (Spinner) findViewById(R.id.spinner_spinner3);
			view3 = (TextView) findViewById(R.id.spinner_text3);
			// ����ѡ������ArrayAdapter��������
			adapter3 = ArrayAdapter.createFromResource(this,
					R.array.spinner_source1,
					android.R.layout.simple_spinner_item);
			// ���������б�ķ��
			adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
			// ��adapter ��ӵ�spinner��
			spinner3.setAdapter(adapter3);
			// ����¼�Spinner�¼�����
			spinner3.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
			// ����Ĭ��ֵ
			spinner3.setVisibility(View.VISIBLE);

		}

		private void test4() {
			spinner4 = (Spinner) findViewById(R.id.spinner_spinner4);
			view4 = (TextView) findViewById(R.id.spinner_text4);
			// ����ѡ������Adapter��������
			NewSpinnerAdapter nsa = new NewSpinnerAdapter(list, this);
			spinner4.setAdapter(nsa);
			// ����¼�Spinner�¼�����

			// ����Ĭ��ֵ
			spinner4.setVisibility(View.VISIBLE);
		}


	
		int a = 0;
		private Button button52;
		private Button button53;
		private TextView view53;
		private TextView view52;

		private void test5() {
			
			button51 = (Button) findViewById(R.id.spinner_button51);
			button52 = (Button) findViewById(R.id.spinner_button52);
			button53 = (Button) findViewById(R.id.spinner_button53);
			view51 = (TextView) findViewById(R.id.spinner_text51);
			view52 = (TextView) findViewById(R.id.spinner_text52);
			view53 = (TextView) findViewById(R.id.spinner_text53);
			button51.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					new AlertDialog.Builder(SpinnerActivity.this)
							.setTitle("ѡ��")
							.setSingleChoiceItems(citys, a,
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											button51.setText(citys[which]);
											view51.setText(citys[which]);
											a = which;
											dialog.dismiss();
										}
									}).create().show();

				}
			});
			button52.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					new AlertDialog.Builder(SpinnerActivity.this)
							.setTitle("ѡ��")
							.setIcon(R.drawable.icon)
							.setSingleChoiceItems(citys, a,
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											button52.setText(citys[which]);
											view52.setText(citys[which]);
											a = which;
											dialog.dismiss();
										}
									}).create().show();

				}
			});
		
			button53.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

				
					NewSpinnerAdapter nsa = new NewSpinnerAdapter(list,
							SpinnerActivity.this);
					new AlertDialog.Builder(SpinnerActivity.this)
							.setTitle("������ѡѡѡ�����񡭡���������")
							.setAdapter(nsa,
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											list.get(which);
											NewSpinner ns = list.get(which);
											button53.setText(ns.getTitle());
											view53.setText(ns.getTitle());
											a = which;
											dialog.dismiss();
										}
									}).create().show();
					;
				}
			});
		}

		// ʹ��������ʽ����
		class SpinnerSelectedListener implements OnItemSelectedListener {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				view.setText("���ѡ���ǣ�" + m[arg2]);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		}

		// ʹ��XML��ʽ����
		class SpinnerXMLSelectedListener implements OnItemSelectedListener {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				view2.setText("���ѡ���ǣ�" + adapter2.getItem(arg2));
			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}

		}

		// ʹ��XML��ʽ����
		class SpinnerXMLSelectedListener3 implements OnItemSelectedListener {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				view2.setText("���ѡ���ǣ�" + adapter2.getItem(arg2));
			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}

		}
	}
}