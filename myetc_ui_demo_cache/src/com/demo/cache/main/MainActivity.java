package com.demo.cache.main;


import java.util.ArrayList;
import java.util.List;

import com.demo.cache.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
/**
 * 
 * 这个主入口，不仅为本项目提供，同时也为myetc_ui提供，做一个库的存在
 * 注：所有的xml文件，变量，source文件，均以demo_开头。避免与myetc_ui中的xml重复
 * @author myetc
 * @param <T>
 *
 */
public class MainActivity<T> extends Activity {

	private ListView demo_mListview;//将要显示的列表
	private MyAdapter demo_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_cache_main);
		superview();
	}

	private void superview() {
		
		demo_mListview = (ListView) findViewById(R.id.main_lv_list);
		List<String> list = new ArrayList<String>();
		for (int i = 10; i < 100; i++) {
			list.add("http://cdn-img.easyicon.net/png/5155/5155"+i+".gif");
		}
		
		demo_adapter = new MyAdapter(this, list);
		demo_mListview.setAdapter(demo_adapter);
		demo_mListview.setOnScrollListener(mScrollListener);
	}

	OnScrollListener mScrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			switch (scrollState) {
			case OnScrollListener.SCROLL_STATE_FLING:
				demo_adapter.setFlagBusy(true);
				break;
			case OnScrollListener.SCROLL_STATE_IDLE:
				demo_adapter.setFlagBusy(false);
				break;
			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				demo_adapter.setFlagBusy(false);
				break;
			default:
				break;
			}
			demo_adapter.notifyDataSetChanged();
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

		}
	};
}
