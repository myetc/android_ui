package com.myetc_ui.main.interfacechild;

import android.os.Bundle;
import android.util.Log;

import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;
public class TabLayoutActivity_u{
private static final int name_id = R.string.interface_tablayout;
@Scanner_Type(name = name_id, type = Scanner_Type_Enum.INTERFACE)
public static class TabLayoutActivity extends BaseActivity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_interface_tablayout);
	}
}
}