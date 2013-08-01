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
 * 新闻资讯Adapter类
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class ButtonListAdapter extends BaseAdapter {
	private Context context;// 运行上下文
	private List<ShareBean> listItems;// 数据集合
	private LayoutInflater listContainer;// 视图容器
	private int itemViewResource;// 自定义项视图源

	static class ListItemView { // 自定义控件集合
		public Button button;
	}

	/**
	 * 实例化Adapter
	 * 
	 * @param context
	 * @param data
	 * @param resource
	 */
	public ButtonListAdapter(Context context, List<ShareBean> data, int resource) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
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
	 * ListView Item设置
	 */
	public View getView(final int position, View convertView, ViewGroup parent) {
		// 自定义视图
		ListItemView listItemView = null;
		if (convertView == null) {
			// 获取list_item布局文件的视图
			convertView = listContainer.inflate(this.itemViewResource, null);

			listItemView = new ListItemView();
			// 获取控件对象
			listItemView.button = (Button) convertView
					.findViewById(R.id.list_button);
			// 设置控件集到convertView
			convertView.setTag(listItemView);

		} else {
			listItemView = (ListItemView) convertView.getTag();
		}
		// 设置文字和图片
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