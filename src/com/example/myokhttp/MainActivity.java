package com.example.myokhttp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private Button btnGet, btnPost1, btnPostString, btnPostUpload, btnGetDownload, btnPostFrom,btnPostJSON;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnGet = (Button) findViewById(R.id.btn_getAction);
		btnPost1 = (Button) findViewById(R.id.btn_postAction1);
		btnPostString = (Button) findViewById(R.id.btn_postAction2);
		btnPostFrom = (Button) findViewById(R.id.btn_postForm);
		btnPostUpload = (Button) findViewById(R.id.btn_postUpload);
		btnGetDownload = (Button) findViewById(R.id.btn_getDownLoad);
		btnPostJSON=(Button) findViewById(R.id.btn_postJson);		
		
		btnGet.setOnClickListener(this);
		btnPost1.setOnClickListener(this);
		btnPostString.setOnClickListener(this);		
		btnPostUpload.setOnClickListener(this);
		btnGetDownload.setOnClickListener(this);
		btnPostFrom.setOnClickListener(this);
		btnPostJSON.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn_getAction:
            Intent intentGet=new Intent(getApplication(),GetActivity.class);
            startActivity(intentGet);
			break;
		case R.id.btn_getDownLoad:
			Intent intentDownload=new Intent(getApplicationContext(),GetDownloadActivity.class);
			startActivity(intentDownload);
			break;
		case R.id.btn_postAction1:
            //值对
			Intent intentPost=new Intent(getApplicationContext(),PostActivity.class);
			startActivity(intentPost);
			break;
		case R.id.btn_postAction2:
             //字符串
			Intent intentPostString=new Intent(getApplicationContext(),PostStringActivity.class);
			startActivity(intentPostString);	
			break;
		case R.id.btn_postForm:
			  //表单Form数据
			Intent intentPostForm=new Intent(getApplicationContext(),PostFormActivity.class);
			startActivity(intentPostForm);	
			break;
		case R.id.btn_postUpload:
			Intent intentPostFile=new Intent(getApplicationContext(),PostFileActivity.class);
			startActivity(intentPostFile);	
			break;
		case R.id.btn_postJson:
			Intent intentPostJson=new Intent(getApplicationContext(),PostJSONActivity.class);
			startActivity(intentPostJson);	
			break;

		}

	}
}
