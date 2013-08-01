package com.myetc_ui.main.componentchild;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.myetc_ui.about.HELPGo;
import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.view.MyGallery3DGallery;
import com.myetc_ui.view.MyGalleryAdapter;
import com.myetc_ui.view.MyGalleryDefault;
import com.myetc_ui.view.MyGalleryLoop;
import com.myetc_ui.R;

/**
 * 常用标签，为了简单，简单再简单。代码已经小白的不能再小白了！ 无任何逻辑可言！完成流水代码！仅限此同级目录。
 * 
 * @author Administrator
 * 
 */
public class GalleryActivity_u {
	private static final int name_id = R.string.component_gallery;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class GalleryActivity extends BaseActivity {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_gallery);
			this.changeBar_Title(name_id);

			test1();
			test2();
			test3();
		}

		private void test3() {

			Button button = (Button) findViewById(R.id.main_component_gallery_button1);
			button.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					HELPGo.goNextActivity(GalleryActivity.this, Gallery3D.class);

				}
			});

		}

		private void test2() {
			Gallery mGallery = (Gallery) findViewById(R.id.main_component_gallery_gallery2);
			ArrayList<Integer> imgList = new ArrayList<Integer>();
			imgList.add(R.drawable.image_array1);
			imgList.add(R.drawable.image_array2);
			imgList.add(R.drawable.image_array3);
			imgList.add(R.drawable.image_array4);
			imgList.add(R.drawable.image_array5);
			mGallery.setAdapter(new MyGalleryLoop(this, imgList));
		}

		private void test1() {
			Gallery mGallery = (Gallery) findViewById(R.id.main_component_gallery_gallery1);
			ArrayList<Integer> imgList = new ArrayList<Integer>();
			imgList.add(R.drawable.image_array1);
			imgList.add(R.drawable.image_array2);
			imgList.add(R.drawable.image_array3);
			imgList.add(R.drawable.image_array4);
			imgList.add(R.drawable.image_array5);
			mGallery.setAdapter(new MyGalleryDefault(this, imgList));
		}

	}

	public static class Gallery3D extends Activity {

		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_gallery_3d);
			// 不能太少！！
			Integer[] images = { R.drawable.girl_1, R.drawable.girl_2,
					R.drawable.girl_3, R.drawable.girl_4, R.drawable.girl_5,
					R.drawable.girl_6, R.drawable.girl_7 };

			MyGalleryAdapter adapter = new MyGalleryAdapter(this, images);
			adapter.createReflectedImages();// 创建倒影效果
			MyGallery3DGallery galleryFlow = (MyGallery3DGallery) this
					.findViewById(R.id.main_component_gallery_gallery_3d_gallery1);
			galleryFlow.setFadingEdgeLength(0);
			galleryFlow.setSpacing(-100); // 图片之间的间距
			galleryFlow.setAdapter(adapter);

			galleryFlow.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Toast.makeText(getApplicationContext(),
							String.valueOf(position), Toast.LENGTH_SHORT)
							.show();
				}

			});
			galleryFlow.setSelection(4);
		}
	}
}