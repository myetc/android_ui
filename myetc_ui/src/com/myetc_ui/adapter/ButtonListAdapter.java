package com.myetc_ui.adapter;

import java.util.List;

import com.myetc_ui.about.HELPGo;
import com.myetc_ui.about.ShareBean;
import com.myetc_ui.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ������ѶAdapter��
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class ButtonListAdapter extends BaseAdapter {
	private Context context;// ����������
	private List<ShareBean> listItems;// ���ݼ���
	private LayoutInflater listContainer;// ��ͼ����
	private int itemViewResource;// �Զ�������ͼԴ

	static class ListItemView { // �Զ���ؼ�����
		public Button button;
	}

	/**
	 * ʵ����Adapter
	 * 
	 * @param context
	 * @param data
	 * @param resource
	 */
	public ButtonListAdapter(Context context, List<ShareBean> data, int resource) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context); // ������ͼ����������������
		this.itemViewResource = resource;
		this.listItems = data;
	}

	public int getCount() {
		return listItems.size();
	}

	public Object getItem(int arg0) {
		return null;
	}

	public long getItemId(int arg0) {
		return 0;
	}

	/**
	 * ListView Item����
	 */
	public View getView(final int position, View convertView, ViewGroup parent) {
		// �Զ�����ͼ
		ListItemView listItemView = null;
		if (convertView == null) {
			// ��ȡlist_item�����ļ�����ͼ
			convertView = listContainer.inflate(this.itemViewResource, null);

			listItemView = new ListItemView();
			// ��ȡ�ؼ�����
			listItemView.button = (Button) convertView
					.findViewById(R.id.list_button);
			// ���ÿؼ�����convertView
			convertView.setTag(listItemView);

		} else {
			listItemView = (ListItemView) convertView.getTag();
		}
		// �������ֺ�ͼƬ
		final int demo = listItems.get(position).getT();
		listItemView.button.setText(demo);
		listItemView.button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(listItems.get(position).getC()==null)return;
				
				if(listItems.get(position).getSte()!=null)
				HELPGo.goNextActivity(context, listItems.get(position).getC(),listItems.get(position).getSte(),demo);
				else
				HELPGo.goNextActivity(context, listItems.get(position).getC());
			}
		});
		
		return convertView;
	}

}