package com.myetc_ui.adapter;

import java.util.ArrayList;
import java.util.List;

import com.myetc_ui.R;
import com.myetc_ui.adapter.NewSpinnerAdapter.NewEntity;
import com.myetc_ui.bean.NewSpinner;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


public class UserAdapter extends BaseAdapter implements Filterable {

	private MyFilter myFilter;
	private List<NewSpinner> NewEntitys;
	private Context context;

	private ArrayList<NewSpinner> mOriginalValues;

	private final Object mLock = new Object();
	private NewEntity newEntity;

	public UserAdapter(Context context, List<NewSpinner> NewEntitys) {
		this.context = context;
		this.NewEntitys = NewEntitys;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return NewEntitys.size();
	}

	@Override
	public Object getItem(int arg0) {

		return NewEntitys.get(arg0).getTitle();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.tool_adapter_spinner_entyty_new, null);
			newEntity = new NewEntity();
			newEntity.title = (TextView) convertView.findViewById(R.id.newspinner_title);
			newEntity.state = (TextView) convertView.findViewById(R.id.newspinner_state);
			newEntity.time = (TextView) convertView.findViewById(R.id.newspinner_time);
			newEntity.source = (TextView) convertView.findViewById(R.id.newspinner_source);
			newEntity.heat = (TextView) convertView.findViewById(R.id.newspinner_heat);
			convertView.setTag(newEntity);
		}else{
			newEntity = (NewEntity) convertView.getTag();
		}
		NewSpinner newSpinner = NewEntitys.get(position);
		newEntity.title.setText(newSpinner.getTitle());
		newEntity.state.setText(newSpinner.getState());
		newEntity.time.setText(newSpinner.getTime());
		newEntity.source.setText(newSpinner.getSource());
		newEntity.heat.setText("热度("+newSpinner.getHeat()+")");
		
		
		return convertView;
	}

	class NewEntity{
		TextView title;
		TextView state;
		TextView time;
		TextView source;
		TextView heat;
		
	}

	@Override
	public Filter getFilter() {
		if (myFilter == null) {
			myFilter = new MyFilter();
		}
		return myFilter;
	}

	class MyFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence prefix) { 
            // 持有过滤操作完成之后的数据。该数据包括过滤操作之后的数据的值以及数量。 count:数量 values包含过滤操作之后的数据的值  
            FilterResults results = new FilterResults();  
  
            if (mOriginalValues == null) {  
                synchronized (mLock) {  
                    // 将list的用户 集合转换给这个原始数据的ArrayList  
                    mOriginalValues = new ArrayList<NewSpinner>(NewEntitys);  
                }  
            }  
            if (prefix == null || prefix.length() == 0) {  
                synchronized (mLock) {  
                    ArrayList<NewSpinner> list = new ArrayList<NewSpinner>(  
                            mOriginalValues);  
                    results.values = list;  
                    results.count = list.size();  
                }  
            } else {  
                // 做正式的筛选  
                String prefixString = prefix.toString().toLowerCase();  
  
                // 声明一个临时的集合对象 将原始数据赋给这个临时变量  
                final ArrayList<NewSpinner> values = mOriginalValues;  
  
                final int count = values.size();  
  
                // 新的集合对象  
                final ArrayList<NewSpinner> newValues = new ArrayList<NewSpinner>(  
                        count);  
  
                for (int i = 0; i < count; i++) {  
                    // 如果姓名的前缀相符或者电话相符就添加到新的集合  
                    final NewSpinner value = (NewSpinner) values.get(i);  
                    	//这里验证符合你的输入条件。
                  //现在，无验证，返回所有
//                    
//                    if(条件符合){
//                    	newValues.add(value);  
//                    }
                        newValues.add(value);  
                }  
                // 然后将这个新的集合数据赋给FilterResults对象  
                results.values = newValues;  
                results.count = newValues.size();  
            }  
  
            return results;  }

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			// 重新将与适配器相关联的List重赋值一下
			NewEntitys = (List<NewSpinner>) results.values;

			if (results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}

	}
}