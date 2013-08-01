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
 * 常用标签，为了简单，简单再简单。代码已经小白的不能再小白了！ 无任何逻辑可言！完成流水代码！仅限此同级目录。
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

	// 1.AndroidManifest.xml中必须使用许可"android.permission.INTERNET",否则会出Web page
	// not available错误。
	// 2.如果访问的页面中有Javascript，则webview必须设置支持Javascript。
	// webview.getSettings().setJavaScriptEnabled(true);
	// 3.如果页面中链接，如果希望点击链接继续在当前browser中响应，而不是新开Android的系统browser中响应该链接，必须覆盖
	// webview的WebViewClient对象。
	//
	public static class WebViewActivity1 extends Activity {
		private WebView webview = null;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_component_webview_1);
			webview = (WebView) findViewById(R.id.main_component_webview_1_webview);
			webview.loadUrl("http://www.baidu.com");
			// 还在本页面在打开
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
					Toast.makeText(WebViewActivity2.this, "点击了！",
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
			// 还在本页面在打开
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
