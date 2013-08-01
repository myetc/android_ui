package com.myetc_ui.main.componentchild;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.format.Time;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.myetc_ui.about.HELPGo;
import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.R;

/**
 * ���ñ�ǩ��Ϊ�˼򵥣����ټ򵥡������Ѿ�С�׵Ĳ�����С���ˣ� ���κ��߼����ԣ������ˮ���룡���޴�ͬ��Ŀ¼��
 * 
 * @author Administrator
 * 
 */
public class WebViewActivity_u {
	private static final int name_id = R.string.component_webview;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.COMPONENT)
	public static class WebViewActivity extends BaseActivity {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_webview);
			this.changeBar_Title(name_id);

		}

		public void webview1(View v) {
			HELPGo.goNextActivity(this, WebViewActivity1.class);
		}
		public void webview2(View v) {
			HELPGo.goNextActivity(this, WebViewActivity2.class);
		}
	}

	// 1.AndroidManifest.xml�б���ʹ�����"android.permission.INTERNET",������Web page
	// not available����
	// 2.������ʵ�ҳ������Javascript����webview��������֧��Javascript��
	// webview.getSettings().setJavaScriptEnabled(true);
	// 3.���ҳ�������ӣ����ϣ��������Ӽ����ڵ�ǰbrowser����Ӧ���������¿�Android��ϵͳbrowser����Ӧ�����ӣ����븲��
	// webview��WebViewClient����
	//
	public static class WebViewActivity1 extends Activity {
		private WebView webview = null;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_webview_1);
			webview = (WebView) findViewById(R.id.main_component_webview_1_webview);
			webview.loadUrl("http://www.baidu.com");
			// ���ڱ�ҳ���ڴ�
			webview.setWebViewClient(new WebViewClient() {
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					view.loadUrl(url);
					return true;
				}
			});

			WebSettings webSettings = webview.getSettings();
			webSettings.setJavaScriptEnabled(true);

		}

		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
				webview.goBack();
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}
	}

	public static class WebViewActivity2 extends Activity {
		private WebView webview = null;
		private Handler mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				switch (msg.what) {
				case 0:
					Toast.makeText(WebViewActivity2.this, "����ˣ�",
							Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
			}

		};

		@SuppressLint("JavascriptInterface")
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_webview_1);
			webview = (WebView) findViewById(R.id.main_component_webview_1_webview);
			// ���ڱ�ҳ���ڴ�
			webview.setWebViewClient(new WebViewClient() {
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					view.loadUrl(url);
					return true;
				}
			});

			webview.loadUrl("file:///android_asset/html/demo.html");
			WebSettings webSettings = webview.getSettings();
			webSettings.setJavaScriptEnabled(true);
			webview.addJavascriptInterface(new Object() {
				public void clickOnAndroid() {
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							System.out.println("======clickOnAndroid");
							Message msg = new Message();
							msg.what = 0;
							mHandler.sendMessage(msg);
							webview.loadUrl("javascript:wave()");       
						}
					});

				}
			}, "demo");

		}


		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
				webview.goBack();
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}
	}
}
