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
 * ���ð�ť��Ϊ�˼򵥣����ټ򵥡������Ѿ�С�׵Ĳ�����С���ˣ� ���κ��߼����ԣ������ˮ���룡���޴�ͬ��Ŀ¼��
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
					Toast.makeText(ZoomActivity.this, "�����zoomControls�еĴ�",
							Toast.LENGTH_SHORT).show();
				}
			});
			zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Toast.makeText(ZoomActivity.this, "�����zoomControls�е�С",
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
							textview.setText("����ˣ�" + (arg0 ? "�Ŵ�" : "��С"));
						}

						@Override
						public void onVisibilityChanged(boolean arg0) {
							textview.setText("���Ű�ť" + (arg0 ? "��ʾ" : "����")
									+ "��");
						}
					});
			layout1.addView(imageView);
		}
	}
}