package com.myetc_ui.about;

import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.main.interfacechild.LoginLayoutActivity_u.LoginLayoutActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class HELPGo {
	public static void goNextActivity(Context context,Class formActivity){
		Intent i = new Intent(context,formActivity);
		context.startActivity(i);;
	}
	public static void goNextActivity(Context context,Class formActivity,String value){
		Intent i = new Intent(context,formActivity);
		i.putExtra("command", value);
		context.startActivity(i);;
	}
	public static void goNextActivity(Context context,Class formActivity,Scanner_Type_Enum ste,int title){
		Intent i = new Intent(context,formActivity);
		i.putExtra("command", ste);
		i.putExtra("title", title);
		context.startActivity(i);;
	}
}
