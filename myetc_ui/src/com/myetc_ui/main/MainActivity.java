package com.myetc_ui.main;

import java.util.ArrayList;
import java.util.List;

import com.myetc_ui.R;
import com.myetc_ui.about.HELPGo;
import com.myetc_ui.about.ShareBean;
import com.myetc_ui.about.scanner.Scanner;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.adapter.ButtonListAdapter;
import com.myetc_ui.base.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

	private ListView menu_listview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		initView();
	}

	private void initView() {
		menu_listview = (ListView) findViewById(R.id.menu_listview);
		
		
		/**
		 * ���������ע�����˵�����Ŀ��
		 * ע�᷽��,list.add(new ShareBean(�˵����ƣ�������string�����ù�����,
				ChildActivity.class, �Դ����ƣ���ӷ���Ϊɨ��));
		 */
		List<ShareBean> list = new ArrayList<ShareBean>();
		list.add(new ShareBean(R.string.menu_interface,
				ChildActivity.class, Scanner_Type_Enum.INTERFACE));
		list.add(new ShareBean(R.string.menu_component,
				ChildActivity.class, Scanner_Type_Enum.COMPONENT));
		list.add(new ShareBean(R.string.menu_othercomponent,
				ChildActivity.class, Scanner_Type_Enum.OTHERCOMPONENT));
		
		list.add(new ShareBean(R.string.menu_authority,
				ChildActivity.class, Scanner_Type_Enum.AUTHORITY));
		list.add(new ShareBean(R.string.menu_sdk,
				ChildActivity.class, Scanner_Type_Enum.SDK));
		list.add(new ShareBean(R.string.menu_demo,
				ChildActivity.class, Scanner_Type_Enum.DEMO));
		list.add(new ShareBean(R.string.menu_data,
				ChildActivity.class, Scanner_Type_Enum.DATA));
		
		String[] menu_main = getResources().getStringArray(R.array.menu_main);
		ButtonListAdapter buttonListAdapter = new ButtonListAdapter(this, list,
				R.layout.tool_adapter_list_button_main);
		menu_listview.setAdapter(buttonListAdapter);
	}

}
