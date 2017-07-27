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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostJSONActivity extends Activity {
	private TextView tvInfo;
	private String URL = "http://125.64.59.11:8000/scgy/aostar/login/login.php";
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_json);
		tvInfo = (TextView) findViewById(R.id.tv_show);
		MyOkHttpTest();

	}

	public String bolwingJson(String username, String password) {
		return "{'username':" + username + "," + "'password':" + password + "}";
	}

	private void MyOkHttpTest() {
		OkHttpClient client = new OkHttpClient();
		// json为String类型的json数据
		// 转换为JSON
		String json = bolwingJson("hewaiming", "aostar");
		RequestBody requestBody = RequestBody.create(JSON, json);
		// 创建一个请求对象
		Request request = new Request.Builder().url(URL).post(requestBody).build();
		Call call = client.newCall(request);
		call.enqueue(new okhttp3.Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				final String res = response.body().string();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						tvInfo.setText(res);
						Toast.makeText(PostJSONActivity.this, "Post JSON success!", Toast.LENGTH_SHORT).show();
					}
				});
			}

			@Override
			public void onFailure(Call call, IOException e) {
				Toast.makeText(PostJSONActivity.this, "Post JSON Failed", Toast.LENGTH_SHORT).show();
			}
		});

	}

}
