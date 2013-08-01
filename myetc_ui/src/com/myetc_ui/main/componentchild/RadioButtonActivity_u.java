package com.myetc_ui.main.componentchild;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;

/**
 * 常用标签，为了简单，简单再简单。代码已经小白的不能再小白了！ 无任何逻辑可言！完成流水代码！仅限此同级目录。
 * 
 * @author Administrator
 * 
 */
public class RadioButtonActivity_u {
	private static final int name_id = R.string.component_radiobutton;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class RadioButtonActivity extends BaseActivity {
		boolean state = false;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_radionbutton);
			this.changeBar_Title(name_id);

			RadioButton rb = (RadioButton) findViewById(R.id.radiobutton_change);

			rb.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					RadioButton rb = (RadioButton) findViewById(R.id.radiobutton_change);
					rb.clearAnimation();
					System.err.println("=====state:" + state + "===");
					rb.setChecked(state);
					state = !state;

				}
			});
		}

		public void clearstate(View v) {
			RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton1);
			RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
			RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3);
			RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton4);
			RadioButton rb5 = (RadioButton) findViewById(R.id.radioButton5);
			rb1.setChecked(false);
			rb2.setChecked(false);
			rb3.setChecked(false);
			rb4.setChecked(false);
			rb5.setChecked(false);

		}

	}
}