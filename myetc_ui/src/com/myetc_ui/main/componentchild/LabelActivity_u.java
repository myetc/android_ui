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
public class LabelActivity_u {
	private static final int name_id = R.string.component_label;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class LabelActivity extends BaseActivity {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_label);
			this.changeBar_Title(name_id);
			style1();
//			style2();
			
		}

		private void style2() {
			TextView tv = (TextView) findViewById(R.id.text2);
			AssetManager mgr = getAssets();// 得到AssetManager
			Typeface tf = Typeface.createFromAsset(mgr, "fonts/hksn.ttf");// 根据路径得到Typeface
			tv.setTypeface(tf);
		}

		private void style1() {
			TextView tv = (TextView) findViewById(R.id.text1);
			String textStr1 = "<font color=\"#ffff00\">如果有一天，</font><br>";
			String textStr2 = "<font color=\"#00ff00\">我厌倦了这里，</font><br>";
			String textStr3 = "<font color=\"#ff00ff\">我会乘着梦，</font><br>";
			String textStr4 = "<font color=\"#00ffff\">飞向那个属于自己的<br>世界……</font><br>";
			tv.setText(Html.fromHtml(textStr1 + textStr2 + textStr3 + textStr4));
		}

	}
}