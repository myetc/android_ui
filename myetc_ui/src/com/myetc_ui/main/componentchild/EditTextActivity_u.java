package com.myetc_ui.main.componentchild;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
public class EditTextActivity_u {
	private static final int name_id = R.string.component_edittext;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class EditTextActivity extends BaseActivity {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_edittext);
			this.changeBar_Title(name_id);

			EditText editText = (EditText) findViewById(R.id.monitor_edit_text0);
			final TextView textView0 = (TextView) findViewById(R.id.monitor_text0);
			final TextView textView1 = (TextView) findViewById(R.id.monitor_text1);
			final TextView textView2 = (TextView) findViewById(R.id.monitor_text2);

			editText.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence text, int start,
						int before, int count) {
					// text 输入框中改变后的字符串信息
					// start 输入框中改变后的字符串的起始位置
					// before 输入框中改变前的字符串的位置 默认为0
					// count 输入框中改变后的一共输入字符串的数量
					textView1.setText("输入后字符串 [ " + text.toString()
							+ " ] 起始光标 [ " + start + " ] 输入数量 [ " + count
							+ " ]");

				}

				@Override
				public void beforeTextChanged(CharSequence text, int start,
						int count, int after) {
					// text 输入框中改变前的字符串信息
					// start 输入框中改变前的字符串的起始位置
					// count 输入框中改变前后的字符串改变数量一般为0
					// after 输入框中改变后的字符串与起始位置的偏移量
					System.out.println(text.toString());
					textView0.setText("输入前字符串 [ " + text.toString()
							+ " ]起始光标 [ " + start + " ]结束偏移量  [" + after + " ]");
				}

				@Override
				public void afterTextChanged(Editable edit) {
					// edit 输入结束呈现在输入框中的信息
					textView2.setText("输入结束后的内容为 [" + edit.toString()
							+ " ] 即将显示在屏幕上");
				}
			});

		}

		public void show0(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text0)))
							.getText(), Toast.LENGTH_SHORT).show();
		}

		public void show1(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text1)))
							.getText(), Toast.LENGTH_SHORT).show();
		}

		public void show2(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text2)))
							.getText(), Toast.LENGTH_SHORT).show();
		}

		public void show3(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text3)))
							.getText(), Toast.LENGTH_SHORT).show();
		}

		public void show4(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text4)))
							.getText(), Toast.LENGTH_SHORT).show();
		}

		public void show5(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text5)))
							.getText(), Toast.LENGTH_SHORT).show();
		}

		public void show6(View v) {
			Toast.makeText(
					this,
					((EditText) (findViewById(R.id.sample_edit_text6)))
							.getText(), Toast.LENGTH_SHORT).show();
		}
	}
}