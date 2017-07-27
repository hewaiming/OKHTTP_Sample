package com.example.myokhttp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.security.auth.callback.Callback;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetDownloadActivity extends Activity {
	private TextView tvInfo;
	private ImageView ivInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_download);
		tvInfo = (TextView) findViewById(R.id.tv_show);
		ivInfo = (ImageView) findViewById(R.id.iv_download);
		downloadImg();

	}

	public void downloadImg() {
		OkHttpClient client = new OkHttpClient();
		final Request request = new Request.Builder().get().url("https://www.baidu.com/img/bd_logo1.png").build();
		Call call = client.newCall(request);
		call.enqueue(new okhttp3.Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				Log.e("moer", "onFailure: ");
				Toast.makeText(GetDownloadActivity.this, "下载图片失败！", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				// 第一种方式，保存图片
				InputStream is = response.body().byteStream();
				int len = 0;
				File file = new File(Environment.getExternalStorageDirectory(), "n.png");
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buf = new byte[128];

				while ((len = is.read(buf)) != -1) {
					fos.write(buf, 0, len);
				}
				fos.flush(); // 关闭流 fos.close(); is.close();
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						tvInfo.setText("图片下载成功！");
					}
				});

				// 第二种方式 显示图片
				/*
				 * InputStream is = response.body().byteStream(); final Bitmap
				 * bitmap = BitmapFactory.decodeStream(is); runOnUiThread(new
				 * Runnable() {
				 * 
				 * @Override public void run() { ivInfo.setImageBitmap(bitmap);
				 * tvInfo.setText("图片下载成功！"); } });
				 * 
				 * is.close();
				 */

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
