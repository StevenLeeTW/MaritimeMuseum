package com.example.maritimemuseum;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
        mainMenu();
	}
	Bundle bundleData =new Bundle();		
	
	//首頁  包含簡介 參觀資訊  展覽品 等按鈕轉換
	public void mainMenu()
	{
		setContentView(R.layout.activity_main_menu);
		
		Button intro = (Button)findViewById(R.id.intro);
		Button visit = (Button)findViewById(R.id.visitInfo);
		Button item = (Button)findViewById(R.id.itemInfo);
		
		 intro.setOnClickListener(new OnClickListener()
		 {
			 public void onClick(View v)
			 {
				 introduction();
			 }
		 });
		 
		 visit.setOnClickListener(new OnClickListener()
		 {
			 public void onClick(View v)
			 {
				 visitInfo();
			 }
		 });
		 
		 item.setOnClickListener(new OnClickListener()
		 {
			 public void onClick(View v)
			 {
				 itemInfo();
			 }
		 });
	}
	
	//簡介頁面  含博物館簡介  創辦人的話  開發團隊 等按鈕
	public void introduction()
	{
		setContentView(R.layout.intro);
		Button mIntro = (Button)findViewById(R.id.mintrod);
		Button teamIntro = (Button)findViewById(R.id.teamb);
		Button words = (Button)findViewById(R.id.words);
		Button backToMenu = (Button)findViewById(R.id.bti);
		
		mIntro.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				museumIntro();
			}
		});
		
		words.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				words();
			}
		})	;
		
		teamIntro.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			 {
				 teamIntroduction();
			 }
		});
		
		backToMenu.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			 {
				 mainMenu();
			 }
		});
		
	}
	
	//博物館介紹
	public void museumIntro()
	{
		Intent i = new Intent();
		i.setClass(MainMenu.this, MainIntro.class);		
		bundleData.putString("s", "history");		
		bundleData.putInt("num", 1);
		i.putExtras(bundleData);	
		startActivity(i);	
	}
	
	//創辦人的話
	public void words()
	{
		Intent i = new Intent();
		i.setClass(MainMenu.this, MainIntro.class);		
		bundleData.putString("s", "words");	
		bundleData.putInt("num", 1);
		i.putExtras(bundleData);
		startActivity(i);	
	}
	
	//開發團隊頁面
	public void teamIntroduction()
	{
		 setContentView(R.layout.team);			
		 Button backToIntro = (Button)findViewById(R.id.button5);
		 backToIntro.setOnClickListener(new OnClickListener()
			{
				public void onClick(View v)
				{
					introduction();
				}
			});
	}
	
	//參觀資訊
	public void visitInfo()
	{
		Intent i = new Intent();
		i.setClass(MainMenu.this, MainIntro.class);		
		bundleData.putInt("num", 2);
		i.putExtras(bundleData);	
		startActivity(i);	
	}
	
	public void  itemInfo()
	{
		Intent i = new Intent();
		i.setClass(MainMenu.this, QRcode.class);		
		startActivity(i);	
	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
