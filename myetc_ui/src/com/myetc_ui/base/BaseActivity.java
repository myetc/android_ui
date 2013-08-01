package com.myetc_ui.base;

import com.myetc_ui.about.ShareBean;
import com.myetc_ui.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public abstract class BaseActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	};

	public void back(View view) {
		this.finish();
	}
	
	public void changeBar_Title(int id){
		TextView bar_title = (TextView)findViewById(R.id.bar_title);
		bar_title.setText(id);
	}
}
