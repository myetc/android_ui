package com.myetc_ui.main.componentchild;

import java.util.Timer;
import java.util.TimerTask;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

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
public class ProActivity_u {
	private static final int name_id = R.string.component_pro;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class ProActivity extends BaseActivity {

		private ProgressBar pb5;
		private ProgressDialog mypDialog;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_pro);
			this.changeBar_Title(name_id);

			test1();
		}

		public void showprogressDialog2(View v) {
			mypDialog = new ProgressDialog(this);
			// 实例化
			mypDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			// 设置进度条风格，风格为长形，有刻度的
			mypDialog.setTitle("一休");
			// 设置ProgressDialog 标题
			mypDialog.setMessage("休息休息……");
			// 设置ProgressDialog 提示信息
			mypDialog.setIcon(R.drawable.icon);
			// 设置ProgressDialog 标题图标
			mypDialog.setProgress(100);
			// 设置ProgressDialog 进度条进度
			mypDialog.setButton("干活去", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			// 设置ProgressDialog 的一个Button
			mypDialog.setIndeterminate(false);
			// 设置ProgressDialog 的进度条是否不明确
			mypDialog.setCancelable(true);
			// 设置ProgressDialog 是否可以按退回按键取消
			mypDialog.show();
			// 让ProgressDialog显示
			
			new Timer().schedule(new TimerTask() {
				
				@Override
				public void run() {
					if(mypDialog.getProgress()>=100){
						cancel();
					}
					mypDialog.incrementProgressBy(1);
					
				}
			}, 100,100);
		}

		public void showprogressDialog(View v) {
			ProgressDialog mypDialog = new ProgressDialog(this);
			// 实例化
			mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			// 设置进度条风格，风格为圆形，旋转的
			mypDialog.setTitle("提示：");
			// 设置ProgressDialog 标题
			mypDialog.setMessage("请耐心等待，数据加载中……");
			// 设置ProgressDialog 提示信息
			mypDialog.setIcon(R.drawable.icon);
			// 设置ProgressDialog 标题图标
			mypDialog.setButton("不等了", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			// 设置ProgressDialog 的一个Button
			mypDialog.setIndeterminate(false);
			// 设置ProgressDialog 的进度条是否不明确
			mypDialog.setCancelable(true);
			// 设置ProgressDialog 是否可以按退回按键取消
			mypDialog.show();
			// 让ProgressDialog显示
		}

		class Mytask extends TimerTask {

			int value = 0, value2 = 0;

			@Override
			public void run() {
				value = pb5.getProgress();
				value2 = pb5.getSecondaryProgress();
				if (value >= 100 && value2 >= 100) {
					this.cancel();
				}
				if (value >= value2) {
					pb5.incrementSecondaryProgressBy(5);
				}
				pb5.incrementProgressBy(1);
			}
		}

		private void test1() {
			pb5 = (ProgressBar) findViewById(R.id.progressBar5);
			Timer timer = new Timer();
			Mytask m = new Mytask();

			timer.schedule(m, 1000, 500);
		}

	}
}