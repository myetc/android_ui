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
 * ���ð�ť��Ϊ�˼򵥣����ټ򵥡������Ѿ�С�׵Ĳ�����С���ˣ� ���κ��߼����ԣ������ˮ���룡���޴�ͬ��Ŀ¼��
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
	        //����checkedTextView1Ϊѡ��״̬
	        checkedTextView1.setChecked(true);       
	        //����checkedTextView2��ҳ�߾࣬������/��/��/�Ҹ�20���أ�Ĭ��Ϊδѡ��״̬
	        checkedTextView2.setPadding(20, 20, 20, 20); 
	        //����checkedTextView3Ϊѡ��״̬������������ʾͼ�꣬ʹ��androidϵͳ��Դarrow_down_float
	        checkedTextView3.setChecked(true);
	        checkedTextView3.setCheckMarkDrawable(android.R.drawable.arrow_down_float);
	        //����checkedTextView4��ת״̬����Ĭ�ϵ�δѡ�з�תΪѡ��״̬
	        checkedTextView4.toggle();
	//���״̬�����෴����ѡ�б�Ϊδѡ�У�δѡ�еı�Ϊѡ��
	        checkedTextView1.setOnClickListener(new View.OnClickListener() {                           
	                            @Override
	                            public void onClick(View v) {
	                                     // TODO Auto-generated method stub
	                                      checkedTextView1.toggle();
	                            }
	                   });       
	        //���״̬�����෴����ѡ�б�Ϊδѡ�У�δѡ�еı�Ϊѡ��
	  checkedTextView2.setOnClickListener(new View.OnClickListener() {
	                           
	                           @Override
	                            public void onClick(View v) {
	                                     // TODO Auto-generated method stub
	                                      checkedTextView2.toggle();
	                            }
	                   });
	 
	 //���״̬�����෴����������ת��Ϊ�����Ƿ���
	  checkedTextView3.setOnClickListener(new View.OnClickListener() {
	                   @Override
	                   public void onClick(View v) {
	                            // TODO Auto-generated method stub  
	                            checkedTextView3.setCheckMarkDrawable(android.R.drawable.arrow_up_float);
	                   }
	});
	 //���״̬�����෴����ѡ�б�Ϊδѡ�У�δѡ�еı�Ϊѡ��
	 checkedTextView4.setOnClickListener(new View.OnClickListener() {                  
	                   @Override
	                   public void onClick(View v) {
	                            // TODO Auto-generated method stub
	                             checkedTextView4.toggle();
	                   }
	         });       
	        //����listView��ģʽΪCHOICE_MODE_SINGLE
	        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
		}

	}
}