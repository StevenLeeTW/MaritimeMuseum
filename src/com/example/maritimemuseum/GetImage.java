package com.example.maritimemuseum;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class GetImage extends AsyncTask<String, Integer, Bitmap>{
	ImageView image;
	public GetImage(ImageView im)
	{
		image = im;
	}
	@Override
	protected Bitmap doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		Bitmap bitmap = null;

		publishProgress(0); 
        HttpClient hc = new DefaultHttpClient();  
        publishProgress(30);  
        HttpGet hg = new HttpGet(arg0[0]);
		try
		{
			HttpResponse hr = hc.execute(hg);
			bitmap = BitmapFactory.decodeStream(hr.getEntity().getContent());
		}
		catch(IOException e)
		{
			return null;
		}
		 publishProgress(100);  
		return bitmap;
	}	 

	@Override  
    protected void onPostExecute(Bitmap Result){  
    
        image.setImageBitmap(Result);         
    }  
}
