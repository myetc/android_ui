package com.myetc_ui.main.demochild;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.demo.image.main.AlphaActivity;
import com.demo.image.main.ChangeActivity;
import com.demo.image.main.MainActivity;
import com.myetc_ui.about.HELPGo;
import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;

public class ImageActivity_u {
	private static final int name_id = R.string.demo_image;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.DEMO)
	public static class ImageActivity extends BaseActivity {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_demo_image);
			changeBar_Title(name_id);
		}
		/**
		 * 引用外部项目库，为了使项目逻辑理清晰
		 * 如果，把demo加载在本项目中，小白的层次关系瞬间就崩了
		 * 解释：外部项目完成是一个独立的完整项目，能正常运行。
		 * 外部项目库，在eclipse中标记为了一个library
		 * 如果你第一次运行adnroid library projects connot be lauched
		 * 说这个安卓项目库，不能够发射，你可以在项目上右键找到properties选项，再找到android选项
		 * 把is library的勾去掉就OK了
		 */
		public void demo1_image(View v) {
			 HELPGo.goNextActivity(this,MainActivity.class);
		}
		public void demo2_image(View v) {
			 HELPGo.goNextActivity(this,ChangeActivity.class);
		}
		public void demo3_image(View v) {
			 HELPGo.goNextActivity(this,AlphaActivity.class);
		}
	}
}
