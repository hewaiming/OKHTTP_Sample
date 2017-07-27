package com.example.myokhttp;

import java.io.File;
import java.io.IOException;

import javax.security.auth.callback.Callback;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostFileActivity extends Activity {
	private TextView tvInfo;
	private String URL = "http://125.64.59.11:8000/scgy/file_upload/upload_file.php";
	private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_file);
		tvInfo = (TextView) findViewById(R.id.tv_show);
		MyOkHttpTest();

	}

	private void PostFileTest() throws IOException {

		OkHttpClient client1 = new OkHttpClient();
		File file = new File(Environment.getExternalStorageDirectory(), "README.md");
		if (!file.exists()) {
			Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
		} else {

			Request request = new Request.Builder().url(URL).post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
					.build();
			Response response = client1.newCall(request).execute();
			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);
			System.out.println(response.body().string());
		}

	}

	private void MyOkHttpTest() {

		File file = new File(Environment.getExternalStorageDirectory(), "n.png");
		if (!file.exists()) {
			Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
		} else {
			OkHttpClient client = new OkHttpClient();
			RequestBody muiltipartBody = new MultipartBody.Builder()
					// 一定要设置这句
					.setType(MultipartBody.FORM)
					// .addFormDataPart("username", "admin")//
					// .addFormDataPart("password", "admin")//
					.addFormDataPart("file", "n.png",
							RequestBody.create(MediaType.parse("application/octet-stream"), file))
					.build();

			Request request = new Request.Builder().url(URL).post(muiltipartBody).build();

			Call call = client.newCall(request);
			call.enqueue(new okhttp3.Callback() {

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					final String res = response.body().string();
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							tvInfo.setText(res);
							Toast.makeText(PostFileActivity.this, "Post文件success!", Toast.LENGTH_SHORT).show();
						}
					});
				}

				@Override
				public void onFailure(Call call, IOException e) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(PostFileActivity.this, "Post 文件Failed", Toast.LENGTH_SHORT).show();
						}
					});

				}
			});
		}
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
