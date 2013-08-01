package com.myetc_ui.main.componentchild;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;

/**
 * ���öԻ���Ϊ�˼򵥣����ټ򵥡������Ѿ�С�׵Ĳ�����С���ˣ� ���κ��߼����ԣ������ˮ���룡���޴�ͬ��Ŀ¼��
 * AlertDialog�Ĺ��췽��ȫ����Protected�ģ����Բ���ֱ��ͨ��newһ��AlertDialog��������һ��AlertDialog��
 * 
 * Ҫ����һ��AlertDialog����Ҫ�õ�AlertDialog.Builder�е�create()������
 * 
 * ʹ��AlertDialog.Builder�����Ի�����Ҫ�˽����¼���������
 * 
 * setTitle ��Ϊ�Ի������ñ��� setIcon ��Ϊ�Ի�������ͼ�� setMessage��Ϊ�Ի����������� setView ��
 * ���Ի��������Զ�����ʽ setItems �����öԻ���Ҫ��ʾ��һ��list��һ��������ʾ��������ʱ setMultiChoiceItems
 * ���������öԻ�����ʾһϵ�еĸ�ѡ�� setNeutralButton ����ͨ��ť
 * 
 * setPositiveButton �����Ի������"Yes"��ť setNegativeButton ���Ի������"No"��ť create ��
 * �����Ի��� show ����ʾ�Ի���
 * 
 * @author myetc
 * 
 */
public class DialogActivity_u {
	private static final int name_id = R.string.component_dialog;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class DialogActivity extends BaseActivity {

		protected static final int MAX_PROGRESS = 100;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_dialog);
			this.changeBar_Title(name_id);

		}

		public void issure(View v) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.icon);
			builder.setTitle("��ȷ��Ҫ�뿪��");
			builder.setPositiveButton("ȷ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// ������ӵ��ȷ������߼�
							Toast.makeText(DialogActivity.this, "��ѡ����ȷ��",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setNegativeButton("ȡ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// ������ӵ��ȷ������߼�
							Toast.makeText(DialogActivity.this, "��ѡ����ȡ��",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.create().show();

		}

		public void ismessage(View v) {
			new AlertDialog.Builder(this).setMessage("��ʾ��Ϣ�������Զ���ʧ").show();
		}

		public void morebutton(View v) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.icon);
			builder.setTitle("ͶƱ");
			builder.setMessage("����Ϊʲô������������������");
			builder.setPositiveButton("��Ȥζ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Toast.makeText(DialogActivity.this, "��ѡ������Ȥζ��",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setNeutralButton("��˼���",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Toast.makeText(DialogActivity.this, "��ѡ������˼���",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setNegativeButton("����ǿ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Toast.makeText(DialogActivity.this, "��ѡ��������ǿ��",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.create().show();
		}

		public void list(View v) {
			final String[] mItems = { "tx", "item1", "itme2", "item3", "itme4",
					"item5", "item6" };

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("�б�ѡ���");
			builder.setItems(mItems, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// ����󵯳�����ѡ���˵ڼ���
					Toast.makeText(DialogActivity.this,
							"��ѡ���idΪ" + which + " , " + mItems[which],
							Toast.LENGTH_SHORT).show();
				}
			});
			builder.create().show();
		}

		int mSingleChoiceID = 0;
		private ProgressDialog mProgressDialog;
		private ArrayList<Integer> MultiChoiceID;

		public void radiobuttonlist(View v) {
			final String[] mItems = { "tx", "item1", "itme2", "item3", "itme4",
					"item5", "item6" };
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			mSingleChoiceID = 0;
			builder.setIcon(R.drawable.icon);
			builder.setTitle("����ѡ��");
			builder.setSingleChoiceItems(mItems, 0,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							mSingleChoiceID = whichButton;
							Toast.makeText(
									DialogActivity.this,
									"��ѡ���idΪ" + whichButton + " , "
											+ mItems[whichButton],
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setPositiveButton("ȷ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// if (mSingleChoiceID > 0) {
							Toast.makeText(DialogActivity.this,
									"��ѡ�����" + mSingleChoiceID,
									Toast.LENGTH_SHORT).show();
							// }
						}
					});
			builder.setNegativeButton("ȡ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
						}
					});
			builder.create().show();
		}

		public void pro(View v) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setIcon(R.drawable.icon);
			mProgressDialog.setTitle("����������");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setMax(MAX_PROGRESS);
			mProgressDialog.setButton("ȷ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// ������ӵ������߼�
						}
					});
			mProgressDialog.setButton2("ȡ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// ������ӵ������߼�
						}
					});
			mProgressDialog.show();
			new Thread() {
				public void run() {
					int Progress = 0;
					while (Progress < MAX_PROGRESS) {
						try {
							Progress++;
							Thread.sleep(10);
							mProgressDialog.incrementProgressBy(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
			}.start();
		}

		public void checkbox(View v) {
			final String[] mItems = { "tx", "item1", "itme2", "item3", "itme4",
					"item5", "item6" };
			MultiChoiceID = new ArrayList<Integer>();

			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			MultiChoiceID.clear();

			builder.setIcon(R.drawable.icon);
			builder.setTitle("����ѡ��");
			builder.setMultiChoiceItems(mItems, new boolean[] { false, false,
					false, false, false, false, false },
					new DialogInterface.OnMultiChoiceClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton, boolean isChecked) {
							if (isChecked) {
								MultiChoiceID.add(whichButton);
								Toast.makeText(
										DialogActivity.this,
										"��ѡ���idΪ" + whichButton + " , "
												+ mItems[whichButton],
										Toast.LENGTH_SHORT).show();
							} else {
								MultiChoiceID.remove(whichButton);
							}

						}
					});
			builder.setPositiveButton("ȷ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String str = "";
							int size = MultiChoiceID.size();
							for (int i = 0; i < size; i++) {
								str += mItems[MultiChoiceID.get(i)] + ", ";
							}
							Toast.makeText(DialogActivity.this, "��ѡ�����" + str,
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setNegativeButton("ȡ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

						}
					});
			builder.create().show();
		}

		public void diydialog(View v) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			LayoutInflater factory = LayoutInflater.from(this);
			final View textEntryView = factory.inflate(
					R.layout.tool_layout_dialog_login, null);
			builder.setIcon(R.drawable.icon);
			builder.setTitle("�Զ��������");
			builder.setView(textEntryView);
			builder.setPositiveButton("ȷ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							EditText userName = (EditText) textEntryView
									.findViewById(R.id.username);
							EditText password = (EditText) textEntryView
									.findViewById(R.id.password);
							Toast.makeText(
									DialogActivity.this,
									"���� ��" + userName.getText().toString()
											+ "���룺"
											+ password.getText().toString(),
									Toast.LENGTH_SHORT).show();
							;
						}
					});
			builder.setNegativeButton("ȡ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

						}
					});
			builder.create().show();
		}

		public void readpro(View v) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setTitle("��ȡing");
			mProgressDialog.setMessage("���ڶ�ȡ�����Ժ�");
			mProgressDialog.setIndeterminate(true);
			mProgressDialog.setCancelable(true);
			mProgressDialog.show();
		}
	}
}