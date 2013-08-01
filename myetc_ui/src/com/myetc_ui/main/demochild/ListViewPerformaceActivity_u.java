package com.myetc_ui.main.demochild;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.demo.cache.main.MainActivity;
import com.myetc_ui.about.HELPGo;
import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;

public class ListViewPerformaceActivity_u {
	private static final int name_id = R.string.demo_listviewperformace;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.DEMO)
	public static class ListViewPerformaceActivity extends BaseActivity {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_demo_listviewperformace);
			changeBar_Title(name_id);
		}
		/**
		 * �����ⲿ��Ŀ�⣬Ϊ��ʹ��Ŀ�߼�������
		 * �������demo�����ڱ���Ŀ�У�С�׵Ĳ�ι�ϵ˲��ͱ���
		 * ���ͣ��ⲿ��Ŀ�����һ��������������Ŀ�����������С�
		 * �ⲿ��Ŀ�⣬��eclipse�б��Ϊ��һ��library
		 * ������һ������adnroid library projects connot be lauched
		 * ˵�����׿��Ŀ�⣬���ܹ����䣬���������Ŀ���Ҽ��ҵ�propertiesѡ����ҵ�androidѡ��
		 * ��is library�Ĺ�ȥ����OK��
		 */
		public void demo1_cache(View v) {
			 HELPGo.goNextActivity(this,MainActivity.class);
		}
	}
}
