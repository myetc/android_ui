package listview.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import listview.adapter.XListViewAdapter;
import listview.adapter.XListViewBean;
import listview.main.view.XListView;
import listview.main.view.XListView.IXListViewListener;
import me.maxwin.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class XListViewActivity extends Activity {
	private XListView mListView;
	private XListViewAdapter xlva;
	private ArrayList<XListViewBean> parentList = new ArrayList<XListViewBean>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		initData(true);// �õ�ģ������
		xlva = new XListViewAdapter(parentList, this);

		mListView.setAdapter(xlva);// ��ӵ�listview��ȥ

		// ���ˢ�£��ͼ��ظ����¼�
		mListView.setXListViewListener(new XListView.IXListViewListener() {
			@Override
			public void onRefresh() {
				Toast.makeText(XListViewActivity.this, "Ҫˢ�£��Ǹ������¼���һ��",
						Toast.LENGTH_SHORT).show();
				parentList.clear();
				initData(true);// �õ�ģ������
				xlva.notifyDataSetChanged();
				mListView.stopRefresh();
				mListView.stopLoadMore();
				mListView.setRefreshTime("�ո� ");
			}

			@Override
			public void onLoadMore() {
				Toast.makeText(XListViewActivity.this, "Ҫ���ࣿ�Ǹ����ں����һ��",
						Toast.LENGTH_SHORT).show();
				initData(false);
				xlva.notifyDataSetChanged();
				mListView.stopLoadMore();
			}
		});
	}

	private void initData(boolean isFirst) {

		mListView = (XListView) findViewById(R.id.xListView);
		mListView.setPullLoadEnable(true);

		ArrayList<Integer> chlidList0 = new ArrayList<Integer>();
		chlidList0.add(R.drawable.bbpp1__background_09_480_425);
		XListViewBean xlvb0 = new XListViewBean(null, chlidList0);

		ArrayList<Integer> chlidList1 = new ArrayList<Integer>();
		chlidList1.add(R.drawable.noimg);
		chlidList1.add(R.drawable.noimg);
		XListViewBean xlvb1 = new XListViewBean("����", chlidList1);

		ArrayList<Integer> chlidList2 = new ArrayList<Integer>();
		chlidList2.add(R.drawable.noimg);
		chlidList2.add(R.drawable.noimg);
		chlidList2.add(R.drawable.noimg);
		XListViewBean xlvb2 = new XListViewBean("����", chlidList2);
		if (isFirst)
			parentList.add(xlvb0);
		parentList.add(xlvb1);
		parentList.add(xlvb2);
	}

}