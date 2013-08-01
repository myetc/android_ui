package com.myetc_tool;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.http.AndroidHttpClient;
import android.util.Log;
import android.widget.Toast;

/**
 * Image帮助类
 * 
 * @author MyEtc
 * 
 */
public class ImageHelp {
	/**
	 * 加载网络图片
	 * 
	 * @param url
	 *            指定图片地址
	 * @return Bitmap 返回图片的对象
	 */
	public static Bitmap loadImageFromInternet(String url) {
		Bitmap bitmap = null;
		HttpClient client = AndroidHttpClient.newInstance("Android");
		HttpParams params = client.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 3000);
		HttpConnectionParams.setSocketBufferSize(params, 3000);
		HttpResponse response = null;
		InputStream inputStream = null;
		HttpGet httpGet = null;
		try {
			httpGet = new HttpGet(url);
			response = client.execute(httpGet);
			int stateCode = response.getStatusLine().getStatusCode();
			if (stateCode != HttpStatus.SC_OK) {
				Log.d("【ImageHelp.loadImageFromInternet】",
						"没找到网络图片 --返回值 stateCode：" + stateCode);
				return bitmap;
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try {
					inputStream = entity.getContent();
					return bitmap = BitmapFactory.decodeStream(inputStream);
				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
					entity.consumeContent();
				}
			}
		} catch (ClientProtocolException e) {
			httpGet.abort();
			e.printStackTrace();
		} catch (IOException e) {
			httpGet.abort();
			e.printStackTrace();
		} finally {
			((AndroidHttpClient) client).close();
		}
		return bitmap;
	}

	private static float scaleWidth = 1;//
	private static float scaleHeight = 1;
	private static float curDegrees = 0;

	/**
	 * 
	 * 图片缩放旋转的method
	 * 
	 * @param bmp
	 *            要处理的图片
	 * @param scale
	 *            缩放比例
	 * @param rotate
	 *            旋转方式角度（正负之分）
	 * @param minX
	 *            限制缩放图片最小的宽
	 * @param minY
	 *            限制缩放图片最小的高
	 * @param maxX
	 *            限制缩放图片最大的宽
	 * @param maxY
	 *            限制缩放图片最大的高
	 * @return
	 */
	public static Bitmap createImageRotationAndScaling(Bitmap bmp,
			double scale, float rotate, float minX, float minY, float maxX,
			float maxY) {
		// 计算出默认图片的大小
		int bmpW = bmp.getWidth();
		int bmpH = bmp.getHeight();
		// 一个处理图片的——坐标变换矩阵
		// 处理颜色时用ColorMatrix
		// 注意这个Matirx是android.graphics底下的那个
		Matrix mt = new Matrix();
		// 设置图片放大比例
		// 计算出这次要放大的比例
		float sw = scaleWidth;
		float sh = scaleHeight;
		scaleWidth = (float) (scaleWidth * scale);
		scaleHeight = (float) (scaleHeight * scale);
		// /////////////--------旋转一定要放在缩放之后，不然缩放无效果--------------//////////////////////
		// 设置旋转角度
		// 如果是设置为0则表示不旋转
		// 设置的数是负数则向左转
		// 设置的数是正数则向右转

		mt.setRotate(curDegrees = curDegrees + rotate);

		/*
		 * 限制缩放最大与最小
		 */
		if (scaleWidth * scale * bmpW < minX
				|| scaleHeight * scale * bmpH < minY
				|| scaleWidth * scale * bmpW > maxX
				|| scaleHeight * scale * bmpH > maxY) {
			scaleWidth = (float) (sw * 1.0);
			scaleHeight = (float) (sh * 1.0);
			mt.postScale(scaleWidth, scaleHeight);
		} else {
			mt.postScale(scaleWidth, scaleHeight);
		}

		// 产生reSize后的Bitmap对象
		Bitmap resizeBmp = Bitmap.createBitmap(bmp, 0, 0, bmpW, bmpH, mt, true);
		return resizeBmp;
	}

}
