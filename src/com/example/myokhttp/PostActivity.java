package com.example.myokhttp;

import java.io.IOException;
import java.security.KeyStore.PrivateKeyEntry;

import javax.security.auth.callback.Callback;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PostActivity extends Activity {
	private TextView tvInfo;
	private WebView webView; 
	private String URL="http://125.64.59.11:8000/scgy/aostar/login/login.php";
	private String LoginURL="http://125.64.59.11:8000/scgy/aostar/login/login.html";
	private String OKURL="http://125.64.59.11:8000/scgy/aostar/gsinfoMORE.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		tvInfo = (TextView) findViewById(R.id.tv_show);
		webView=(WebView) findViewById(R.id.web);
		MyOkHttpTest();

	}

	private void MyOkHttpTest() {
		OkHttpClient client = new OkHttpClient();
		FormBody formBody = new FormBody.Builder().add("username", "hewaiming").add("password", "aostar").build();
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
						webView.loadUrl(OKURL);
						Toast.makeText(PostActivity.this, "Post success!", Toast.LENGTH_SHORT).show();
					}
				});
			}

			@Override
			public void onFailure(Call call, IOException e) {
				Toast.makeText(PostActivity.this, "Post Failed", Toast.LENGTH_SHORT).show();
				webView.loadUrl(LoginURL);
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
