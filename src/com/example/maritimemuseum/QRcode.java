package com.example.maritimemuseum;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.zxing.activity.CaptureActivity;
import com.zxing.encoding.EncodingHandler;
import com.example.maritimemuseum.R;


public class QRcode extends Activity {
	private EditText strEditText;
	private ImageView qrImgImageView;
	private Bundle bundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	       
        strEditText = (EditText) this.findViewById(R.id.et_qr_string);
        qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
        bundle = new Bundle();
        Button scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
        scanBarCodeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent openCameraIntent = new Intent(QRcode.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});
        
        Button searchButton = (Button) this.findViewById(R.id.search);
        searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				String contentString = strEditText.getText().toString();
				if (checkNum(contentString))//判斷數字 
				{
					Intent i = new Intent();
					i.setClass(QRcode.this, MainIntro.class);		
					bundle.putString("index", contentString);	
					bundle.putInt("num", 3);
					i.putExtras(bundle);	
					startActivity(i);	
					
				}else {
						
					Toast.makeText(QRcode.this, "請輸入編號1~85", Toast.LENGTH_SHORT).show();
				}	
			}
        });
	}
		

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundleData = data.getExtras();
			String scanResult = bundleData.getString("result");
			Intent i = new Intent();
			i.setClass(QRcode.this, MainIntro.class);		
			bundleData.putString("index", scanResult);	
			bundleData.putInt("num", 3);
			i.putExtras(bundleData);	
			startActivity(i);	
		//	resultTextView.setText(scanResult);
		}
	}
	

	public boolean checkNum(String itemNum)
	{
		for(int i = 0; i < itemNum.length(); i++)
		{
			if(itemNum.length() > 2)
				return false;
			else if(!Character.isDigit(itemNum.charAt(i)))
				return false;
			else if(itemNum.length() == 1)
				return true;
			else
			{
				int n = Integer.parseInt(itemNum);
				if(n > 0 && n < 86)
					return true;
				else
					return false;
			}
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_qrcode, menu);
		return true;
	}

}	
