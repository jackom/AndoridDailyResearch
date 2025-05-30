package cn.sirius.adsdk.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 简单的图片加载工具，仅作为范例
 */
public abstract class LoadImageUtil extends AsyncTask<Void, Void, Drawable> {
	
	private String url;
	

	public LoadImageUtil(String url) {
		super();
		this.url = url;
	}

	@Override
	protected Drawable doInBackground(Void... params) {
		Drawable drawable = null;
		try {
			InputStream in = (InputStream) new URL(url).openConnection().getContent();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[4 * 1024]; // 4K buffer
			int bytesRead;
			while ((bytesRead = in.read(buf)) != -1) {
				bos.write(buf, 0, bytesRead);
			}
			byte[] byteArray = bos.toByteArray();
			Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
			drawable = new BitmapDrawable(decodeByteArray);
			in.close();
			bos.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return drawable;
	}

	@Override
	protected void onPostExecute(Drawable result) {
		super.onPostExecute(result);
		onReceived(result);
	}
	
	public abstract void onReceived(Drawable result);
	

}
