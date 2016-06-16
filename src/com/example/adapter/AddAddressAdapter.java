package com.example.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.adapter.AddressAdapter.ViewHolder;
import com.example.newapp.R;

/**
 * 地址显示
 * @作者  陈籽屹
 * @时间 2016年6月7日
 */
public class AddAddressAdapter extends BaseAdapter {
	private List<Map<String, String>> listdata;

	private Context context;
	private LayoutInflater layoutInflater;
	private ViewHolder holder;

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return listdata.size();
	}

	public AddAddressAdapter(List<Map<String, String>> listdata, Context context) {
		super();
		this.listdata = listdata;
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return listdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.add_item, null);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_chooese = (TextView) convertView
					.findViewById(R.id.tv_chooese);
			convertView.setTag(holder);
		} else {
			convertView.getTag();
		}
		holder.tv_name.setText(listdata.get(position).get("name"));
		holder.tv_chooese.setText(listdata.get(position).get("chooes"));
		return convertView;
	}

	public class ViewHolder {
		TextView tv_name;
		TextView tv_chooese;
	}
}
