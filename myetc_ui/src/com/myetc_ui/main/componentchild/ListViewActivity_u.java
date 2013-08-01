package com.myetc_ui.main.componentchild;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.myetc_ui.about.HELPGo;
import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;

/**
 * ListView是Android软件开发中非常重要组件之一，基本上是个软件基本都会使用ListView
 * ，今天我通过一个demo来教大家怎么样使用ListView组件
 * 绘制出漂亮的列表，说道ListView就不得不说Adapter适配器，因为只有通过Adapter才
 * 可以把列表中的数据映射到ListView中。
 * 在android的开发中最Adapter 一共可以分为 ArrayAdapter<T>, BaseAdapter, CursorAdapter,
 * HeaderViewListAdapter, ResourceCursorAdapter, SimpleAdapter,
 * SimpleCursorAdapter, WrapperListAdapter 软件开发中最常用的有ArrayAdapter<T>,
 * BaseAdapter, SimpleAdapter，
 * 
 * @author myetc
 * 
 */
public class ListViewActivity_u {
	private static final int name_id = R.string.component_listview;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class ListViewActivity extends BaseActivity {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_listview);
			this.changeBar_Title(name_id);

		}

		public void goSimpleList(View v){
			
			HELPGo.goNextActivity(this, SimpleList.class);
		}
		public void goTitleList(View v){
			
			HELPGo.goNextActivity(this, TitleList.class);
		}
		public void goIconList(View v){
			
			HELPGo.goNextActivity(this, IconList.class);
		}
		public void goColorList(View v){
			
			HELPGo.goNextActivity(this, ColorList.class);
		}
		public void goProgressBarActivity(View v){
			
			HELPGo.goNextActivity(this, ProgressBarActivity.class);
		}
	}
	public static class SimpleList extends ListActivity {
	    private String[] mListStr = {"姓名：李喜锋","性别：男","年龄：23","邮箱：owqdmjep@163.com","QQ:740930061","电话：15818594215","婚姻：已婚","爱好：游戏","其它：想不起来了！"};
	    ListView mListView = null;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
		mListView = getListView();
		setListAdapter(new ArrayAdapter<String>(this,
	                android.R.layout.simple_list_item_1, mListStr));
		mListView.setOnItemClickListener(new OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> adapterView, View view, int position,
			    long id) {
			Toast.makeText(SimpleList.this,"您选择了" + mListStr[position], Toast.LENGTH_LONG).show();
		    }
		});

		super.onCreate(savedInstanceState);
	    }
	}
	public static class TitleList extends ListActivity {
	    private String[] mListTitle = { "姓名", "性别", "年龄", "居住地","邮箱","QQ","电话","婚姻","爱好","其它"};
	    private String[] mListStr = { "李喜锋", "男", "23", "深圳",
	    		"owqdmjep@163.com","740930061","15818594215","已婚","游戏","想不起来了！"};
	    ListView mListView = null;
	    ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
		mListView = getListView();

		int lengh = mListTitle.length;
		for(int i =0; i < lengh; i++) {
		    Map<String,Object> item = new HashMap<String,Object>();
		    item.put("title", mListTitle[i]);
		    item.put("text", mListStr[i]);
		    mData.add(item);
		}
		SimpleAdapter adapter = new SimpleAdapter(this,mData,android.R.layout.simple_list_item_2,
			new String[]{"title","text"},new int[]{android.R.id.text1,android.R.id.text2});
	        setListAdapter(adapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> adapterView, View view, int position,
			    long id) {
			Toast.makeText(TitleList.this,"您选择了标题：" + mListTitle[position] + "内容："+mListStr[position], Toast.LENGTH_LONG).show();
		    }
		});
		super.onCreate(savedInstanceState);
	    }
	}
	
	public static class IconList extends ListActivity {
		   private String[] mListTitle = { "姓名", "性别", "年龄", "居住地","邮箱","QQ","电话","婚姻","爱好","其它"};
		    private String[] mListStr = { "李喜锋", "男", "23", "深圳",
		    		"owqdmjep@163.com","740930061","15818594215","已婚","游戏","想不起来了！"};
	    ListView mListView = null;
	    ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
		mListView = getListView();

		int lengh = mListTitle.length;
		for(int i =0; i < lengh; i++) {
		    Map<String,Object> item = new HashMap<String,Object>();
		    item.put("image", R.drawable.icon);
		    item.put("title", mListTitle[i]);
		    item.put("text", mListStr[i]);
		    mData.add(item);
		}
		SimpleAdapter adapter = new SimpleAdapter(this,mData,R.layout.tool_adapter_list_image,
			new String[]{"image","title","text"},new int[]{R.id.image,R.id.title,R.id.text});
	        setListAdapter(adapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> adapterView, View view, int position,
			    long id) {
			Toast.makeText(IconList.this,"您选择了标题：" + mListTitle[position] + "内容："+mListStr[position], Toast.LENGTH_LONG).show();
		    }
		});
		super.onCreate(savedInstanceState);
	    }
	}
	
	
	public static class ColorList extends ListActivity {
		   private String[] mListTitle = { "姓名", "性别", "年龄", "居住地","邮箱","QQ","电话","婚姻","爱好","其它"};
		    private String[] mListStr = { "李喜锋", "男", "23", "深圳",
		    		"owqdmjep@163.com","740930061","15818594215","已婚","游戏","想不起来了！"};
	    ListView mListView = null;
	    MyListAdapter myAdapter = null;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
		mListView = getListView();
		myAdapter = new MyListAdapter(this);
		setListAdapter(myAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> adapterView, View view, int position,
			    long id) {
			View v=adapterView.getChildAt(position);
			v.setBackgroundColor(Color.RED);
			Toast.makeText(ColorList.this,"您选择了" + mListStr[position], Toast.LENGTH_LONG).show();
		    }
		});

		super.onCreate(savedInstanceState);
	    }

	    class MyListAdapter extends BaseAdapter {
	    	
		private int[] colors = new int[] { 0xff626569, 0xff4f5257 };
		public MyListAdapter(Context context) {
		    mContext = context;
		}
		class ListItemView { // 自定义控件集合
			   ImageView iamge = null;
			    TextView title = null;
			    TextView text = null;
		}
		public int getCount() {
		    return mListStr.length;
		}

		@Override
		public boolean areAllItemsEnabled() {
		    return false;
		}

		public Object getItem(int position) {
		    return position;
		}

		public long getItemId(int position) {
		    return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
		    ListItemView liv = null;
		    if (convertView == null) {
		    	convertView = LayoutInflater.from(mContext).inflate(R.layout.tool_adapter_list_image2, null);
		    	liv = new ListItemView();
		    	liv.iamge = (ImageView) convertView.findViewById(R.id.color_image);
		    	liv.title =(TextView) convertView.findViewById(R.id.color_title);
		    	liv.text= (TextView) convertView.findViewById(R.id.color_text);
		    	convertView.setTag(liv);
		    }else{
		    	liv = (ListItemView) convertView.getTag();
		    }
		
		    int colorPos = position % colors.length;
		    convertView.setBackgroundColor(colors[colorPos]);
		    liv.title.setText(mListTitle[position]);
		    liv.text.setText(mListStr[position]);
		    liv.iamge.setImageResource(R.drawable.icon);
		    return convertView;
		}

		private Context mContext;
	    }
	}
	
	
	
	
	
	
	
	
	
	


	/**
	 * @author myetc
	 * ListView列表项中添加ProgressBar
	 */
	
	
	
	
	
	
	


	/**
	 * @author myetc
	 * ListView列表项中添加ProgressBar
	 */
	public static class ProgressBarActivity extends Activity {
		private ListView mListView = null ;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main_component_listview_pro);
	        init();
	    }

	    private void init(){
	    	  List<String> list = new  ArrayList<String>();
		        for (int i = 0; i < 15; i++) {
		        	list.add("http://cdn-img.easyicon.net/png/10904/109042"+i+".png");
				}
	    	mListView =  (ListView)findViewById(R.id.main_component_listview_pro_listview);
	    	mListView.setAdapter(new MyListViewAdapter(this,list));
	    }

	    private class MyListViewAdapter extends BaseAdapter{
	    	private LayoutInflater inflater = null; 
	    	List<String> list ;
	        class ViewHolder{
		    	ImageView iv = null ;
		    	TextView tv = null ;
		    	ProgressBar pb = null;
		    }
	    	public MyListViewAdapter(Context con,List<String> list) {
	    		inflater = (LayoutInflater)con.getSystemService(LAYOUT_INFLATER_SERVICE);
	    		this.list =list;
	    	}
			@Override
			public int getCount() {
				// 在这里返回的是列表的条数！
				return list.size();
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return arg0;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				ViewHolder mHolder = new ViewHolder() ;
				if(convertView == null){
					convertView = inflater.inflate(R.layout.tool_adapter_list_pro, null);
					mHolder.iv = (ImageView)(convertView.findViewById(R.id.iv));
					mHolder.tv = (TextView)(convertView.findViewById(R.id.textView1));
					mHolder.pb = (ProgressBar)(convertView.findViewById(R.id.pb));
					
					convertView.setTag(mHolder);
				}else{
					mHolder = (ViewHolder)convertView.getTag();
				}
				mHolder.tv.setText(list.get(position));
//				if(mHolder.iv.isEnabled())
				mHolder.iv.setVisibility(View.GONE);
				mHolder.pb.setVisibility(View.VISIBLE);
				mHolder.iv.setTag(list.get(position));
				new AsyncTask<ViewHolder, Void, Bitmap>(){	
					private ViewHolder v=null; ;
					@Override
					protected Bitmap doInBackground(ViewHolder... s) {
					v = s[0];
			            InputStream in = getStreamFromURL(v.iv.getTag().toString());
			            return  BitmapFactory.decodeStream(in);
				}
				@Override
				protected void onPostExecute(Bitmap result) {
					super.onPostExecute(result);
						v.iv.setVisibility(View.VISIBLE);
						 v.iv.setImageBitmap(result);
						 v.pb.setVisibility(View.GONE);
						 
						 
				}}.execute(mHolder);
				return convertView;
			}

	    
	    }

	


	}
	
	
	
	
	
	
	
		public static InputStream getStreamFromURL(String imageURL) {
			InputStream in=null;
			try {
				URL url=new URL(imageURL);
				HttpURLConnection connection=(HttpURLConnection) url.openConnection();
				connection.setRequestProperty("Connection", "Keep-Alive");//维持长连接
				connection.setConnectTimeout(6* 1000);//设置连接超时
				if (connection.getResponseCode() != 200)throw new RuntimeException("请求失败");
				in=connection.getInputStream();

			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				System.out.println("*******请求图片********");
				return in;
			}

		}
	
		
	
}