package listview.adapter;

import java.util.ArrayList;








import me.maxwin.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class XListViewAdapter extends BaseAdapter {

//private ArrayList<Integer> listImageSource;
ArrayList<XListViewBean> parentList = new ArrayList<XListViewBean>();
private Context context;
private ImageViewGroup ivg;

	public XListViewAdapter(ArrayList<XListViewBean> parentList, Context context) {
	this.parentList = parentList;
	this.context = context;
}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return parentList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return parentList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_adapter, null);// 这个过程相当耗时间
			ivg = new ImageViewGroup();
			ivg.ll = (LinearLayout) convertView
					.findViewById(R.id.ll);
			
			ivg.tv = (TextView) convertView
					.findViewById(R.id.tv);
			
			convertView.setTag(ivg);
		} else {
			ivg = (ImageViewGroup) convertView.getTag();
		}
		XListViewBean xlvb = parentList.get(position);
		ArrayList<Integer> chlidList =xlvb.al;
		String time = xlvb.time;
		System.out.println("time:"+time);
		if(time!=null){
			ivg.tv.setText(time);
			ivg.tv.setVisibility(View.VISIBLE);
		}else{
			ivg.tv.setText("");
			ivg.tv.setVisibility(View.GONE);
		}
		
		int num = chlidList.size();
		ivg.ll.removeAllViews();
		for (int i = 0; i < num; i++) {
			ImageView iv = new ImageView(context);
			iv.setImageResource(chlidList.get(i));
			ivg.ll.addView(iv);
		}
		
		return convertView;
	
		
	}
	
class ImageViewGroup{
	TextView tv;
	LinearLayout ll;
}
}
