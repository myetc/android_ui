package com.demo.scrolllayut.main;

import com.example.myetc_ui_layout_scrolllayut.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * ����ṹ�淶������������δ����
 * �һ���дһ��ע�Ͱɣ���Ϊ��������õ�̫���ˡ�Ϊʲô��׿û���Դ�һ���أ� �������һ���������ҿ�����
 * ������ʾ����
 * �����ѡ����֣�������tabʵ�֣�������ȫ�Զ��壬����Ը��ߣ�
 * @author MyEtc4
 * 
 */
public class MainActivity extends Activity {

	private ScrollLayout scrollLayout;//�Լ������һ�����֣���LinesLayoutͬ��
	private int view_count;
	private RadioGroup rg1;
	private RadioButton[] rb1_array;
	private int rg1_count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_scrolllayout_main);

		initView();
		if(!checkout())return;
		initAction();
		
	

	}
	
	/**
	 * ��֤���ֺϷ���
	 * @return
	 */
	private boolean checkout() {
	 rg1_count = rg1.getChildCount();
	 view_count = scrollLayout.getChildCount();
		if(rg1_count!=view_count){
			throw new IllegalStateException(
					"��İ�ť��������Ĳ���ҳ�治һ��.");
		}
		return true;
	}
	
	/**
	 * ��ʼ���¼�
	 */
	private void initAction() {
		//׼��һ������İ�ť����
		rb1_array = new RadioButton[rg1_count];
		//��ҳ���ϵİ�ť����ӹ��������԰�ť��λ�����ñ�ʶ
		for (int i = 0; i < rg1_count; i++) {
			rb1_array[i] = (RadioButton) rg1.getChildAt(i);
			rb1_array[i].setTag(i);
			rb1_array[i].setOnClickListener(new RGListen());
		}
		
		//����������ͼ�������϶��ı���ͼʱ���¼�
		scrollLayout
				.SetOnViewChangeListener(new ScrollLayout.OnViewChangeListener() {
					@Override
					public void OnViewChange(int postion) {
						//���ú͵�ǰ������Ӧ�İ�ťΪѡ��״̬����Ϊ����RadioGroup�е�ԭ�����Բ�����������Ϊδѡ��״̬��
						rb1_array[postion].setChecked(true);
					}
				});
	
		
	}
	/**
	 * ��ʼ���������
	 */
	private void initView() {
		rg1 = (RadioGroup) findViewById(R.id.demo_scrolllayout_main_rg1);//ҳ���еİ�ť��
		scrollLayout = (ScrollLayout) findViewById(R.id.demo_scrolllayout_main_sl1);
	}

	/**
	 * ѡ�ť�ļ�����ʵ��
	 * @author MyEtc
	 *
	 */
	class RGListen implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			//ת����ť������Ĳ���ʱд�����һ��
			RadioButton rb = (RadioButton) v;
			//������Ļ��ͼ���͵�ǰ��ť��Ӧ����ͼ
			scrollLayout.scrollToScreen(new Integer(rb.getTag().toString()));
		}

	}
	
	/**
	 * ҳ���������
	 * @param v ҳ���¼�Ԫ��
	 */
	public void changeScroll(View v){
		boolean state = !scrollLayout.getIsScroll();
		scrollLayout.setIsScroll(state);
		Button button = (Button) v;
		button.setText((state?"�ر�":"����")+"�������һ���");
	}

}
