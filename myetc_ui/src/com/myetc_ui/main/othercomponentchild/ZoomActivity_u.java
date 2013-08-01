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
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;

/**
 * 常用按钮，为了简单，简单再简单。代码已经小白的不能再小白了！ 无任何逻辑可言！完成流水代码！仅限此同级目录。
 * 
 * @author Administrator
 * 
 */
public class ZoomActivity_u {
	private static final int name_id = R.string.othercomponent_zoom;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.OTHERCOMPONENT)
	public static class ZoomActivity extends BaseActivity {

		private ZoomButton btn_minus;
		private ZoomButton btn_plus;
		private ZoomControls zoomControls;
		private ZoomButtonsController zbc;
		private ImageView imageView;
		private ZoomButtonsController mZoomButtonsController;
		private TextView textview;
		private Bitmap bmp;
		private int id;
		private RelativeLayout layout1;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_othercomponent_zoom);
			this.changeBar_Title(name_id);

			test1();
			test2();
		}

		private void test1() {

			btn_minus = (ZoomButton) findViewById(R.id.main_othercomponent_zoom_btn_minus);
			btn_plus = (ZoomButton) findViewById(R.id.main_othercomponent_zoom_btn_plus);
			zoomControls = (ZoomControls) findViewById(R.id.main_othercomponent_zoom_zoomControls);

			zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Toast.makeText(ZoomActivity.this, "点击了zoomControls中的大",
							Toast.LENGTH_SHORT).show();
				}
			});
			zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Toast.makeText(ZoomActivity.this, "点击了zoomControls中的小",
							Toast.LENGTH_SHORT).show();
				}
			});

		}

		private void test2() {
			textview = (TextView) findViewById(R.id.main_othercomponent_zoom_textview1);
			layout1 = (RelativeLayout) findViewById(R.id.main_othercomponent_zoom_layout1);

			bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.girl_1);
			imageView = new ImageView(ZoomActivity.this);
			imageView.setId(id);
			imageView.setImageBitmap(bmp);
			imageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					mZoomButtonsController.setVisible(true);
				}
			});
			mZoomButtonsController = new ZoomButtonsController(imageView);
			mZoomButtonsController.setFocusable(true);
			mZoomButtonsController.setZoomInEnabled(true);
			mZoomButtonsController.setZoomOutEnabled(true);
			mZoomButtonsController.setZoomSpeed(1000);
			mZoomButtonsController.setAutoDismissed(true);
			mZoomButtonsController
					.setOnZoomListener(new ZoomButtonsController.OnZoomListener() {
						@Override
						public void onZoom(boolean arg0) {
							textview.setText("点击了：" + (arg0 ? "放大" : "缩小"));
						}

						@Override
						public void onVisibilityChanged(boolean arg0) {
							textview.setText("缩放按钮" + (arg0 ? "显示" : "隐藏")
									+ "了");
						}
					});
			layout1.addView(imageView);
		}
	}
}