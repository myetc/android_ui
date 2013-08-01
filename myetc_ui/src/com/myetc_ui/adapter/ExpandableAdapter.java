package com.myetc_ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableAdapter extends BaseExpandableListAdapter {
	Activity a;
	private  List<String> groupArray;  
	private  List<List<String>> childArray;
	public ExpandableAdapter(Activity a) {
		groupArray = new  ArrayList<String>();  
		childArray = new  ArrayList<List<String>>();  
		  
		groupArray.add("第一行" );  
		groupArray.add("第二行" );  
		groupArray.add("第三行" );  
		groupArray.add("第四行" );  
		groupArray.add("第五行" );  
		groupArray.add("第六行" );  
		  
		List<String> tempArray = new  ArrayList<String>();  
		tempArray.add("第一条" );  
		tempArray.add("第二条" );  
		tempArray.add("第三条" );  
		tempArray.add("第四条" );  
		tempArray.add("第五条" );  
		tempArray.add("第六条" );  
		  
		for (int  index = 0 ; index <groupArray.size(); ++index)  
		{  
		    childArray.add(tempArray);  
		} 
		this.a = a;
	}

	@Override
	public Object getChild(int  groupPosition, int  childPosition) {
		  return  childArray.get(groupPosition).get(childPosition);  
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		  return  childPosition;  
	}

	@Override
	public View getChildView(int  groupPosition, int  childPosition,  
            boolean  isLastChild, View convertView, ViewGroup parent) {
		String string = childArray.get(groupPosition).get(childPosition);  
        return  getGenericView(string);  
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		 return  childArray.get(groupPosition).size();  
	}

	@Override
	public Object getGroup(int groupPosition) {
	    return  groupArray.get(groupPosition);  
	}

	@Override
	public int getGroupCount() {
        return  groupArray.size();  
	}

	@Override
	public long getGroupId(int groupPosition) {
		 return  groupPosition;  
	}
	@Override
	public View getGroupView(int  groupPosition, boolean  isExpanded,  
            View convertView, ViewGroup parent) {
		String string = groupArray.get(groupPosition);  
        return  getGenericView(string);  
	}
    // View stub to create Group/Children 's View   
    public  TextView getGenericView(String string)  
    {  
        // Layout parameters for the ExpandableListView   
        AbsListView.LayoutParams layoutParams = new  AbsListView.LayoutParams(  
                ViewGroup.LayoutParams.FILL_PARENT, 64 );  
        TextView text = new  TextView(a);  
        text.setLayoutParams(layoutParams);  
        // Center the text vertically   
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL); 
        // Set the text starting position   
        text.setPadding(36 , 0 , 0 , 0 );  
        text.setText(string);  
        return  text;  
    } 
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int  groupPosition, int  childPosition) {
		// TODO Auto-generated method stub
		return true ;
	}

}
