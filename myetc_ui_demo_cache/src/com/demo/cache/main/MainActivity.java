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
 * �������ڣ�����Ϊ����Ŀ�ṩ��ͬʱҲΪmyetc_ui�ṩ����һ����Ĵ���
 * ע�����е�xml�ļ���������source�ļ�������demo_��ͷ��������myetc_ui�е�xml�ظ�
 * @author myetc
 * @param <T>
 *
 */
public class MainActivity<T> extends Activity {

	private ListView demo_mListview;//��Ҫ��ʾ���б�
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
