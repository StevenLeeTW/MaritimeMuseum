package com.example.maritimemuseum;
//使用函式化方式，設定字串參數，根據按鈕按下的選項來呼叫特定的說明

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.view.Menu;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainIntro extends Activity {
	
		ImageView image;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		 //requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_intro);		
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		ListView List = (ListView) this.findViewById(R.id.mainIntroListView);
		image =(ImageView)findViewById(R.id.img);
		ListView List2 = (ListView) this.findViewById(R.id.list2);

		IntroAdapter iAdapter = new IntroAdapter(this);
		VisitAdapter vAdapter = new VisitAdapter(this);		
		ItemAdapter itAdapter = new ItemAdapter(this);

		Bundle bundleData = getIntent().getExtras();
		String s = bundleData.getString("s");
		String index = bundleData.getString("index");
		int num = bundleData.getInt("num");
		
		try{			
			Resources resources  = this.getResources();
			InputStream inStream = resources.openRawResource(R.raw.tkuinstructor);
			BufferedReader is = new BufferedReader(new InputStreamReader(inStream));
			String xmlData="";
			String thisLine=null;
			
			while((thisLine = is.readLine())!= null)
			{
				xmlData += thisLine;
			}
			
			xmlData = xmlData.replace("\t", "");
			Document doc = Jsoup.parse(xmlData);
			
			if(num == 1)
			{	
				image.setVisibility(8);
				List.setVisibility(8);
				List2.setVisibility(0);
				List2.setAdapter(iAdapter);		
				showContent1(doc, iAdapter, s);
			}
			else if(num == 2)
			{	
				image.setVisibility(0);
				List.setVisibility(0);
				List2.setVisibility(8);
				List.setAdapter(vAdapter);
				showContent2(doc, vAdapter);
			}
			//依照QR Code所傳的值(item編號)來解析
			else if(num == 3)
			{
				image.setVisibility(0);
				List.setVisibility(0);
				List2.setVisibility(8);
				List.setAdapter(itAdapter);				
				showContent3(doc, itAdapter, index);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void showContent1(Document doc, IntroAdapter adapter, String s)
	{		
			Elements abtms = doc.select("about_museum");
			
			for(int i = 0; i < abtms.size(); i++)
			{
				Element abtm=abtms.get(i);
				
				Elements hists = abtm.select(s);
				
				for (int j = 0; j < hists.size(); j++) {
					Element hist = abtm.getElementsByTag(s).get(j);					
					IntroAdapter.DataClass data = new IntroAdapter.DataClass("");

					for(int k = 0; k <hist.childNodes().size(); k++)
					{						
						org.jsoup.nodes.Node node = hist.childNode(k);
						if (node.getClass() != Element.class)
						{
							continue;
						}						
						Element el = (Element) node;
						if (el.tagName().compareTo("description") == 0)
						{	
								data.str_intro = el.text();								
						}								
					}					
					adapter.listData.add(data);		
				}	
			}	
	}
	
	public void showContent2(Document doc, VisitAdapter adapter)
	{				
		
		Elements ivs = doc.select("information_visit");
		for(int i = 0; i < ivs.size(); i++)
		{
			Element iv=ivs.get(i);
			
			Elements infos = iv.select("info");
			
			for (int j = 0; j < infos.size(); j++) 
			{
				VisitAdapter.DataClass data = new VisitAdapter.DataClass("", "", "", "", "", "");

				//Element hist = abtm.getElementsByTag("key").get(j);					
				Element info = infos.get(j);
				for(int k = 0; k <info.childNodes().size(); k++)
				{						
					org.jsoup.nodes.Node node = info.childNode(k);
					if (node.getClass() != Element.class)
					{
						continue;
					}						
					
					Element el = (Element) node;
					if (el.tagName().compareTo("open") == 0)
					{	
							data.str_open = el.text();								
					}								
					else if(el.tagName().compareTo("close") == 0)
					{
						data.str_close= el.text();		
					}
					else if(el.tagName().compareTo("remark") == 0)
					{
						data.str_remark = el.text();		
					}
					else if(el.tagName().compareTo("address") == 0)
					{
						data.str_address = el.text();		
					}
					else if(el.tagName().compareTo("phone") == 0)
					{
						data.str_phone = el.text();		
					}
					else if(el.tagName().compareTo("traffic") == 0)
					{
						data.str_traffic = el.text();		
					}
					else if(el.tagName().compareTo("url") == 0)
					{	
						String	url = el.text();
						GetImage gi = new GetImage(image);
						gi.execute(url);
							
					}
				}					
				adapter.listData.add(data);		
			}	
		}
	}
	
	public void showContent3(Document doc, ItemAdapter adapter, String itemIndex)
	{
		Elements intros = doc.select("introduction");
		int index = Integer.parseInt(itemIndex);
		ItemAdapter.DataClass data = new ItemAdapter.DataClass("", "");
		for(int i =0 ; i < intros.size(); i++)
		{
			Element intro=intros.get(i);			
			Elements items = intro.select("item");			
				
				//get()引數從QR傳值過來 將字串轉成數字並-1
			Element item = items.get(index-1);
				
			for(int k = 0; k < item.childNodes().size(); k++)
			{
				org.jsoup.nodes.Node node = item.childNode(k);
				if (node.getClass() != Element.class)
				{
					continue;
				}							
				Element el = (Element) node;
				if(el.tagName().compareTo("name")==0 )
				{
					data.str_name = el.text();				
				}
				else if(el.tagName().compareTo("url")==0)
				{						
					if(URLUtil.isNetworkUrl(el.text()))
					{	
						String  url = el.text();
						GetImage gi = new GetImage(image);
						gi.execute(url);
					}
				}
				else if(el.tagName().compareTo("description")==0)
				{
					data.str_des = el.text();
				}
			}		
			adapter.listData.add(data);			
		}		
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_intro, menu);
		return true;
	}

}
