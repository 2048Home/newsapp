package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bean.Order;
import com.example.newapp.R;

public class MyOrderAdapter extends BaseAdapter{
private List<Order> list;
private LayoutInflater inflater;
private Context context;
ViewHolder holder;

	public MyOrderAdapter(List<Order> list, Context context) {
	super();
	this.list = list;
	this.context=context;
	this.inflater = LayoutInflater.from(context);
}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return list!=null?list.size():0;
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		if(convertView==null){
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.myorder_item, null);
			holder.tv_weight=(TextView) convertView.findViewById(R.id.tv_weight);
			holder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_singleprice=(TextView) convertView.findViewById(R.id.tv_singleprice);
			holder.tv_sumprice=(TextView) convertView.findViewById(R.id.tv_sumprice);
			holder.tv_priceKind=(TextView) convertView.findViewById(R.id.tv_priceKind);
			convertView.setTag(holder);
		}else {
			convertView.getTag();
		}
		holder.tv_name.setText(list.get(position).getGoodName());
		holder.tv_singleprice.setText(list.get(position).getGoodPrice());
		holder.tv_sumprice.setText(list.get(position).getGoodSum());
		holder.tv_weight.setText(list.get(position).getGoodWeight());
		holder.tv_priceKind.setText(list.get(position).getPriceKind());
		
		return convertView;
	}
  
	
	public class ViewHolder {
		TextView	tv_weight;//重量
		TextView  tv_name;//名称
		TextView  tv_singleprice;//单价
		TextView  tv_sumprice;//总价
		TextView  tv_priceKind;//类
	}
}
