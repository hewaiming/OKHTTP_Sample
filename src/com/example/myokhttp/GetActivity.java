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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetActivity extends Activity {
	private TextView tvInfo;
    private String URL="http://125.64.59.11:8000/scgy/android/odbcPHP/getPotNo1.php";
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get);
		tvInfo = (TextView) findViewById(R.id.tv_show);
		MyOkHttpTest();

	}

	private void MyOkHttpTest() {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().get().url(URL).build();
		Call call = client.newCall(request);
		// 异步调用,并设置回调函数
		call.enqueue(new okhttp3.Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				final String res = response.body().string();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						tvInfo.setText(res);
					}
				});

			}

			@Override
			public void onFailure(Call call, IOException e) {
				Toast.makeText(GetActivity.this, "get failed", Toast.LENGTH_SHORT).show();

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
