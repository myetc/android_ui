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
 * 常用对话框，为了简单，简单再简单。代码已经小白的不能再小白了！ 无任何逻辑可言！完成流水代码！仅限此同级目录。
 * AlertDialog的构造方法全部是Protected的，所以不能直接通过new一个AlertDialog来创建出一个AlertDialog。
 * 
 * 要创建一个AlertDialog，就要用到AlertDialog.Builder中的create()方法。
 * 
 * 使用AlertDialog.Builder创建对话框需要了解以下几个方法：
 * 
 * setTitle ：为对话框设置标题 setIcon ：为对话框设置图标 setMessage：为对话框设置内容 setView ：
 * 给对话框设置自定义样式 setItems ：设置对话框要显示的一个list，一般用于显示几个命令时 setMultiChoiceItems
 * ：用来设置对话框显示一系列的复选框 setNeutralButton ：普通按钮
 * 
 * setPositiveButton ：给对话框添加"Yes"按钮 setNegativeButton ：对话框添加"No"按钮 create ：
 * 创建对话框 show ：显示对话框
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
			builder.setTitle("你确定要离开吗？");
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// 这里添加点击确定后的逻辑
							Toast.makeText(DialogActivity.this, "你选择了确定",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// 这里添加点击确定后的逻辑
							Toast.makeText(DialogActivity.this, "你选择了取消",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.create().show();

		}

		public void ismessage(View v) {
			new AlertDialog.Builder(this).setMessage("显示信息，不会自动消失").show();
		}

		public void morebutton(View v) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.icon);
			builder.setTitle("投票");
			builder.setMessage("您认为什么样的内容能吸引您？");
			builder.setPositiveButton("有趣味的",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Toast.makeText(DialogActivity.this, "你选择了有趣味的",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setNeutralButton("有思想的",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Toast.makeText(DialogActivity.this, "你选择了有思想的",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setNegativeButton("主题强的",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Toast.makeText(DialogActivity.this, "你选择了主题强的",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.create().show();
		}

		public void list(View v) {
			final String[] mItems = { "tx", "item1", "itme2", "item3", "itme4",
					"item5", "item6" };

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("列表选择框");
			builder.setItems(mItems, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// 点击后弹出窗口选择了第几项
					Toast.makeText(DialogActivity.this,
							"你选择的id为" + which + " , " + mItems[which],
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
			builder.setTitle("单项选择");
			builder.setSingleChoiceItems(mItems, 0,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							mSingleChoiceID = whichButton;
							Toast.makeText(
									DialogActivity.this,
									"你选择的id为" + whichButton + " , "
											+ mItems[whichButton],
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// if (mSingleChoiceID > 0) {
							Toast.makeText(DialogActivity.this,
									"你选择的是" + mSingleChoiceID,
									Toast.LENGTH_SHORT).show();
							// }
						}
					});
			builder.setNegativeButton("取消",
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
			mProgressDialog.setTitle("进度条窗口");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setMax(MAX_PROGRESS);
			mProgressDialog.setButton("确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// 这里添加点击后的逻辑
						}
					});
			mProgressDialog.setButton2("取消",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// 这里添加点击后的逻辑
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
			builder.setTitle("多项选择");
			builder.setMultiChoiceItems(mItems, new boolean[] { false, false,
					false, false, false, false, false },
					new DialogInterface.OnMultiChoiceClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton, boolean isChecked) {
							if (isChecked) {
								MultiChoiceID.add(whichButton);
								Toast.makeText(
										DialogActivity.this,
										"你选择的id为" + whichButton + " , "
												+ mItems[whichButton],
										Toast.LENGTH_SHORT).show();
							} else {
								MultiChoiceID.remove(whichButton);
							}

						}
					});
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String str = "";
							int size = MultiChoiceID.size();
							for (int i = 0; i < size; i++) {
								str += mItems[MultiChoiceID.get(i)] + ", ";
							}
							Toast.makeText(DialogActivity.this, "你选择的是" + str,
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setNegativeButton("取消",
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
			builder.setTitle("自定义输入框");
			builder.setView(textEntryView);
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							EditText userName = (EditText) textEntryView
									.findViewById(R.id.username);
							EditText password = (EditText) textEntryView
									.findViewById(R.id.password);
							Toast.makeText(
									DialogActivity.this,
									"姓名 ：" + userName.getText().toString()
											+ "密码："
											+ password.getText().toString(),
									Toast.LENGTH_SHORT).show();
							;
						}
					});
			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

						}
					});
			builder.create().show();
		}

		public void readpro(View v) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setTitle("读取ing");
			mProgressDialog.setMessage("正在读取中请稍候");
			mProgressDialog.setIndeterminate(true);
			mProgressDialog.setCancelable(true);
			mProgressDialog.show();
		}
	}
}