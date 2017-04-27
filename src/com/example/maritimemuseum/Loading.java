package com.example.maritimemuseum;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Loading extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.loading);
       handler.sendMessageDelayed(new Message(), 3000);
	}
	private Handler handler = new Handler() {
		  @Override
	        public void handleMessage(Message msg) {
	            super.handleMessage(msg);
	            Intent intent = new Intent();
	            intent.setClass(Loading.this, MainMenu.class);
	            Loading.this.startActivity(intent);
	            Loading.this.finish();
	        }		
	};
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_menu, menu);
		return true;
	}

}
