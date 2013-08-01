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
 * 此类结构规范，但在性能上未处理
 * 我还是写一个注释吧，因为这个东西用的太多了。为什么安卓没有自带一个呢？ 好像带了一个，但是我看不懂
 * 用于显示界面
 * 最常见的选项卡布局，不是用tab实现，而是完全自定义，灵活性更高！
 * @author MyEtc4
 * 
 */
public class MainActivity extends Activity {

	private ScrollLayout scrollLayout;//自己定义的一个布局，和LinesLayout同级
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
	 * 验证布局合法性
	 * @return
	 */
	private boolean checkout() {
	 rg1_count = rg1.getChildCount();
	 view_count = scrollLayout.getChildCount();
		if(rg1_count!=view_count){
			throw new IllegalStateException(
					"你的按钮数量和你的布局页面不一致.");
		}
		return true;
	}
	
	/**
	 * 初始化事件
	 */
	private void initAction() {
		//准备一个缓存的按钮数组
		rb1_array = new RadioButton[rg1_count];
		//把页面上的按钮，添加过来。并以按钮的位置设置标识
		for (int i = 0; i < rg1_count; i++) {
			rb1_array[i] = (RadioButton) rg1.getChildAt(i);
			rb1_array[i].setTag(i);
			rb1_array[i].setOnClickListener(new RGListen());
		}
		
		//监听滚动视图的左右拖动改变视图时的事件
		scrollLayout
				.SetOnViewChangeListener(new ScrollLayout.OnViewChangeListener() {
					@Override
					public void OnViewChange(int postion) {
						//设置和当前视力对应的按钮为选中状态（因为是在RadioGroup中的原因，所以不用设置其它为未选中状态）
						rb1_array[postion].setChecked(true);
					}
				});
	
		
	}
	/**
	 * 初始化所有组件
	 */
	private void initView() {
		rg1 = (RadioGroup) findViewById(R.id.demo_scrolllayout_main_rg1);//页面中的按钮组
		scrollLayout = (ScrollLayout) findViewById(R.id.demo_scrolllayout_main_sl1);
	}

	/**
	 * 选项按钮的监听类实现
	 * @author MyEtc
	 *
	 */
	class RGListen implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			//转换按钮，和你的布局时写的组件一样
			RadioButton rb = (RadioButton) v;
			//设置屏幕视图到和当前按钮对应的视图
			scrollLayout.scrollToScreen(new Integer(rb.getTag().toString()));
		}

	}
	
	/**
	 * 页面监听方法
	 * @param v 页面事件元素
	 */
	public void changeScroll(View v){
		boolean state = !scrollLayout.getIsScroll();
		scrollLayout.setIsScroll(state);
		Button button = (Button) v;
		button.setText((state?"关闭":"开启")+"手势左右滑动");
	}

}
