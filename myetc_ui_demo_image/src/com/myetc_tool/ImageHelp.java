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
 * Image������
 * 
 * @author MyEtc
 * 
 */
public class ImageHelp {
	/**
	 * ��������ͼƬ
	 * 
	 * @param url
	 *            ָ��ͼƬ��ַ
	 * @return Bitmap ����ͼƬ�Ķ���
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
				Log.d("��ImageHelp.loadImageFromInternet��",
						"û�ҵ�����ͼƬ --����ֵ stateCode��" + stateCode);
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
	 * ͼƬ������ת��method
	 * 
	 * @param bmp
	 *            Ҫ�����ͼƬ
	 * @param scale
	 *            ���ű���
	 * @param rotate
	 *            ��ת��ʽ�Ƕȣ�����֮�֣�
	 * @param minX
	 *            ��������ͼƬ��С�Ŀ�
	 * @param minY
	 *            ��������ͼƬ��С�ĸ�
	 * @param maxX
	 *            ��������ͼƬ���Ŀ�
	 * @param maxY
	 *            ��������ͼƬ���ĸ�
	 * @return
	 */
	public static Bitmap createImageRotationAndScaling(Bitmap bmp,
			double scale, float rotate, float minX, float minY, float maxX,
			float maxY) {
		// �����Ĭ��ͼƬ�Ĵ�С
		int bmpW = bmp.getWidth();
		int bmpH = bmp.getHeight();
		// һ������ͼƬ�ġ�������任����
		// ������ɫʱ��ColorMatrix
		// ע�����Matirx��android.graphics���µ��Ǹ�
		Matrix mt = new Matrix();
		// ����ͼƬ�Ŵ����
		// ��������Ҫ�Ŵ�ı���
		float sw = scaleWidth;
		float sh = scaleHeight;
		scaleWidth = (float) (scaleWidth * scale);
		scaleHeight = (float) (scaleHeight * scale);
		// /////////////--------��תһ��Ҫ��������֮�󣬲�Ȼ������Ч��--------------//////////////////////
		// ������ת�Ƕ�
		// ���������Ϊ0���ʾ����ת
		// ���õ����Ǹ���������ת
		// ���õ���������������ת

		mt.setRotate(curDegrees = curDegrees + rotate);

		/*
		 * ���������������С
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

		// ����reSize���Bitmap����
		Bitmap resizeBmp = Bitmap.createBitmap(bmp, 0, 0, bmpW, bmpH, mt, true);
		return resizeBmp;
	}

}
