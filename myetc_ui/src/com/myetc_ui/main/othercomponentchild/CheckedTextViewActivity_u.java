package com.myetc_ui.main.othercomponentchild;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;

/**
 * 常用按钮，为了简单，简单再简单。代码已经小白的不能再小白了！ 无任何逻辑可言！完成流水代码！仅限此同级目录。
 * 
 * @author Administrator
 * 
 */
public class CheckedTextViewActivity_u {
	private static final int name_id = R.string.othercomponent_checkedtextview;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.OTHERCOMPONENT)
	public static class CheckedTextViewActivity extends BaseActivity {
		private ListView listView;
		private CheckedTextView checkedTextView1;
		private CheckedTextView checkedTextView2;
		private CheckedTextView checkedTextView3;
		private CheckedTextView checkedTextView4;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_othercomponent_checkedtextview);
			this.changeBar_Title(name_id);
			test();	
		}

		private void test() {
			listView=(ListView)findViewById(R.id.listView);
	        checkedTextView1=(CheckedTextView)findViewById(R.id.checkedTextView1);
	        checkedTextView2=(CheckedTextView)findViewById(R.id.checkedTextView2);
	        checkedTextView3=(CheckedTextView)findViewById(R.id.checkedTextView3);
	        checkedTextView4=(CheckedTextView)findViewById(R.id.checkedTextView4);
	        //设置checkedTextView1为选中状态
	        checkedTextView1.setChecked(true);       
	        //设置checkedTextView2的页边距，即距上/下/左/右各20像素，默认为未选中状态
	        checkedTextView2.setPadding(20, 20, 20, 20); 
	        //设置checkedTextView3为选中状态，并更改其显示图标，使用android系统资源arrow_down_float
	        checkedTextView3.setChecked(true);
	        checkedTextView3.setCheckMarkDrawable(android.R.drawable.arrow_down_float);
	        //设置checkedTextView4反转状态，由默认的未选中反转为选中状态
	        checkedTextView4.toggle();
	//点击状态后变更相反，如选中变为未选中，未选中的变为选中
	        checkedTextView1.setOnClickListener(new View.OnClickListener() {                           
	                            @Override
	                            public void onClick(View v) {
	                                     // TODO Auto-generated method stub
	                                      checkedTextView1.toggle();
	                            }
	                   });       
	        //点击状态后变更相反，如选中变为未选中，未选中的变为选中
	  checkedTextView2.setOnClickListener(new View.OnClickListener() {
	                           
	                           @Override
	                            public void onClick(View v) {
	                                     // TODO Auto-generated method stub
	                                      checkedTextView2.toggle();
	                            }
	                   });
	 
	 //点击状态后变更相反，即下三角转化为上三角符号
	  checkedTextView3.setOnClickListener(new View.OnClickListener() {
	                   @Override
	                   public void onClick(View v) {
	                            // TODO Auto-generated method stub  
	                            checkedTextView3.setCheckMarkDrawable(android.R.drawable.arrow_up_float);
	                   }
	});
	 //点击状态后变更相反，如选中变为未选中，未选中的变为选中
	 checkedTextView4.setOnClickListener(new View.OnClickListener() {                  
	                   @Override
	                   public void onClick(View v) {
	                            // TODO Auto-generated method stub
	                             checkedTextView4.toggle();
	                   }
	         });       
	        //设置listView的模式为CHOICE_MODE_SINGLE
	        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
		}

	}
}