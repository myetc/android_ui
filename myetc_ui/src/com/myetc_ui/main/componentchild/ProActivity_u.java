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
 * ���ð�ť��Ϊ�˼򵥣����ټ򵥡������Ѿ�С�׵Ĳ�����С���ˣ� ���κ��߼����ԣ������ˮ���룡���޴�ͬ��Ŀ¼��
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
			// ʵ����
			mypDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			// ���ý�������񣬷��Ϊ���Σ��п̶ȵ�
			mypDialog.setTitle("һ��");
			// ����ProgressDialog ����
			mypDialog.setMessage("��Ϣ��Ϣ����");
			// ����ProgressDialog ��ʾ��Ϣ
			mypDialog.setIcon(R.drawable.icon);
			// ����ProgressDialog ����ͼ��
			mypDialog.setProgress(100);
			// ����ProgressDialog ����������
			mypDialog.setButton("�ɻ�ȥ", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			// ����ProgressDialog ��һ��Button
			mypDialog.setIndeterminate(false);
			// ����ProgressDialog �Ľ������Ƿ���ȷ
			mypDialog.setCancelable(true);
			// ����ProgressDialog �Ƿ���԰��˻ذ���ȡ��
			mypDialog.show();
			// ��ProgressDialog��ʾ
			
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
			// ʵ����
			mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			// ���ý�������񣬷��ΪԲ�Σ���ת��
			mypDialog.setTitle("��ʾ��");
			// ����ProgressDialog ����
			mypDialog.setMessage("�����ĵȴ������ݼ����С���");
			// ����ProgressDialog ��ʾ��Ϣ
			mypDialog.setIcon(R.drawable.icon);
			// ����ProgressDialog ����ͼ��
			mypDialog.setButton("������", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			// ����ProgressDialog ��һ��Button
			mypDialog.setIndeterminate(false);
			// ����ProgressDialog �Ľ������Ƿ���ȷ
			mypDialog.setCancelable(true);
			// ����ProgressDialog �Ƿ���԰��˻ذ���ȡ��
			mypDialog.show();
			// ��ProgressDialog��ʾ
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