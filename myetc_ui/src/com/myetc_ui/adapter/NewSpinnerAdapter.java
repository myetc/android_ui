package com.myetc_ui.adapter;

import java.util.List;

import com.myetc_ui.bean.NewSpinner;
import com.myetc_ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewSpinnerAdapter extends BaseAdapter {
private List<NewSpinner> list;
private Context context;


public NewSpinnerAdapter(List<NewSpinner> list, Context context) {
	super();
	this.list = list;
	this.context = context;
}

private NewEntity newEntity;
	class NewEntity{
		TextView title;
		TextView state;
		TextView time;
		TextView source;
		TextView heat;
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
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
		NewSpinner newSpinner = list.get(position);
		newEntity.title.setText(newSpinner.getTitle());
		newEntity.state.setText(newSpinner.getState());
		newEntity.time.setText(newSpinner.getTime());
		newEntity.source.setText(newSpinner.getSource());
		newEntity.heat.setText("»»∂»("+newSpinner.getHeat()+")");
		
		
		return convertView;
	}

}
