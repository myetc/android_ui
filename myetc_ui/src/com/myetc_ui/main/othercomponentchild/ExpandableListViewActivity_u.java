package com.myetc_ui.main.othercomponentchild;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomButton;
import android.widget.ZoomButtonsController;
import android.widget.ZoomControls;

import com.myetc_tool.ImageHelp;
import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.adapter.ExpandableAdapter;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;

/**
 * 常用按钮，为了简单，简单再简单。代码已经小白的不能再小白了！ 无任何逻辑可言！完成流水代码！仅限此同级目录。
 * 
 * @author Administrator
 * 
 */
public class ExpandableListViewActivity_u {
	private static final int name_id = R.string.othercomponent_expandablelistview;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.OTHERCOMPONENT)
	public static class ExpandableListViewActivity extends BaseActivity {

		private ExpandableListView elv;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_othercomponent_expandablelistview);
			this.changeBar_Title(name_id);
			
			test1();
		}

		private void test1() {
			elv = (ExpandableListView) findViewById(R.id.main_othercomponent_expandablelistview_elv1);
			
			elv.setAdapter(new  ExpandableAdapter(this));  
			
		}


	}
}