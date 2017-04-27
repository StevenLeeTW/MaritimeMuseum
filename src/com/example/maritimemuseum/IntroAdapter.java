package com.example.maritimemuseum;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class IntroAdapter extends BaseAdapter{

	public static class ViewHolder {
		TextView text_intro;	
	}
	
	public static class DataClass {
		String str_intro;
		
		public DataClass(String introduction) {
			str_intro = introduction;		
		}
	}
	public List<DataClass> listData = new ArrayList<DataClass>();

	private LayoutInflater mInflater;

	public IntroAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = this.mInflater.inflate(R.layout.museum_intro_text, null);
			holder = new ViewHolder();
			
			holder.text_intro= (TextView) convertView.findViewById(R.id.textView10);		
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}
		DataClass data = listData.get(position);
		holder.text_intro.setText(data.str_intro);
		return convertView;
	}
	

}
