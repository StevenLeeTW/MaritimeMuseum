package com.example.maritimemuseum;

import java.util.ArrayList;
import java.util.List;

import com.example.maritimemuseum.IntroAdapter.DataClass;
import com.example.maritimemuseum.IntroAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VisitAdapter extends BaseAdapter{
	public static class ViewHolder {
		TextView text_open;	
		TextView text_close;
		TextView text_remark;
		TextView text_address;
		TextView text_phone;
		TextView text_traffic;		
		ImageView iMap;
	}
	
	public static class DataClass {
		String str_open;
		String str_close;
		String str_remark;
		String str_address;
		String str_phone;
		String str_traffic;
		
		public DataClass(String open, String close, String remark, String addr,
				String phone, String traffic) {	
			str_open = open;
			str_close = close;
			str_remark = remark;
			str_address = addr;
			str_phone = phone;
			str_traffic = traffic;
		}
	}
	public List<DataClass> listData = new ArrayList<DataClass>();

	private LayoutInflater mInflater;

	public VisitAdapter(Context context) {
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
			convertView = this.mInflater.inflate(R.layout.visit_info_text, null);
			holder = new ViewHolder();
			
			holder.text_open= (TextView) convertView.findViewById(R.id.open);		
			holder.text_close= (TextView) convertView.findViewById(R.id.close);		
			holder.text_remark= (TextView) convertView.findViewById(R.id.remark);		
			holder.text_address= (TextView) convertView.findViewById(R.id.addr);		
			holder.text_phone= (TextView) convertView.findViewById(R.id.phone);		
			holder.text_traffic= (TextView) convertView.findViewById(R.id.traffic);		
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}
		DataClass data = listData.get(position);
		holder.text_open.setText(data.str_open);
		holder.text_close.setText(data.str_close);
		holder.text_remark.setText(data.str_remark);
		holder.text_address.setText(data.str_address);
		holder.text_phone.setText(data.str_phone);
		holder.text_traffic.setText(data.str_traffic);
		return convertView;
	}

}
