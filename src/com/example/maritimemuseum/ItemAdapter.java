package com.example.maritimemuseum;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter{
	
		public static class ViewHolder {
			TextView text_name;	
			TextView text_des;
			}
		
		public static class DataClass {
			String str_name;
			String str_des;
		
			public DataClass(String name, String des) {	
				str_name = name;
				str_des = des;
			}
		}
	public List<DataClass> listData = new ArrayList<DataClass>();

	private LayoutInflater mInflater;

	public ItemAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
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
			convertView = this.mInflater.inflate(R.layout.item_text, null);
			holder = new ViewHolder();
			
			holder.text_name= (TextView) convertView.findViewById(R.id.itemName);		
			holder.text_des= (TextView) convertView.findViewById(R.id.itemDes);		
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}
		DataClass data = listData.get(position);
		holder.text_name.setText(data.str_name);
		holder.text_des.setText(data.str_des);		
		return convertView;
	}


}
