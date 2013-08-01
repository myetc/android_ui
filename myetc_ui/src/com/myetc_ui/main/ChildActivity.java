package com.myetc_ui.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

import com.myetc_ui.R;
import com.myetc_ui.about.Config;
import com.myetc_ui.about.ShareBean;
import com.myetc_ui.about.scanner.Scanner;
import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.adapter.ButtonListAdapter;
import com.myetc_ui.base.BaseActivity;

import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

public class ChildActivity extends BaseActivity {
	private ListView interface_listview;
	private Scanner_Type_Enum ste;
	private int title;
	private List<ShareBean> list = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_child);
		title =   (Integer) getIntent().getExtras().get("title");
		this.changeBar_Title(title);
		ste = (Scanner_Type_Enum) getIntent().getExtras().get("command");
		
		
		initView();
	}

	private void initView() {
		interface_listview = (ListView) findViewById(R.id.interface_listview);
		if (ste != null) {
			list =new Scanner(this, Config.PACKAGE_START).scan(ste);
			if(list==null)return;
		}
		ButtonListAdapter buttonListAdapter = new ButtonListAdapter(this, list,
				R.layout.tool_adapter_list_button);
		interface_listview.setAdapter(buttonListAdapter);
	}

}