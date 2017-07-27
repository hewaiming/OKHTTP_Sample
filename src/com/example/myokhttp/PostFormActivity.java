package com.example.myokhttp;

import java.io.IOException;

import javax.security.auth.callback.Callback;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostFormActivity extends Activity {
	private TextView tvInfo;
	private String URL = "http://125.64.59.11:8000/scgy/aostar/login/login.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_form);
		tvInfo = (TextView) findViewById(R.id.tv_show);
		MyOkHttpTest();

	}

	private void MyOkHttpTest() {
		OkHttpClient client = new OkHttpClient();
		RequestBody formBody = new MultipartBody.Builder()
				// 一定要设置这句
				.setType(MultipartBody.FORM).addFormDataPart("username", "hewaiming")//
				.addFormDataPart("password", "aostar")//
				// .addFormDataPart("myfile", "1.png",
				// RequestBody.create(MediaType.parse("application/octet-stream"),
				// file))
				.build();

		final Request request = new Request.Builder().url(URL).post(formBody).build();
		Call call = client.newCall(request);
		call.enqueue(new okhttp3.Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				final String res = response.body().string();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						tvInfo.setText(res);
						Toast.makeText(PostFormActivity.this, "Post Form数据 success!", Toast.LENGTH_SHORT).show();
					}
				});
			}

			@Override
			public void onFailure(Call call, IOException e) {
				Toast.makeText(PostFormActivity.this, "Post  Form数据 Failed", Toast.LENGTH_SHORT).show();
			}
		});

	}

}
