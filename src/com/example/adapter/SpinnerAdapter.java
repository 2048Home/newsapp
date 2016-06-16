package com.example.adapter;

import java.util.List;

import com.example.newapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SpinnerAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater layoutInflater;
	private List listdata;
	private ViewHoder holder;

	public SpinnerAdapter(Context context, List listdata) {
		super();
		this.context = context;
		this.listdata = listdata;
		this.layoutInflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return listdata.size() != 0 ? listdata.size() : 0;
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
			holder = new ViewHoder();
			convertView = layoutInflater.inflate(R.layout.spinner_item, null);
			holder.tv_spinner_item = (TextView) convertView
					.findViewById(R.id.tv_spinner_item);
		}
		holder.tv_spinner_item.setText((CharSequence) listdata.get(position));
		return convertView;
	}

	class ViewHoder {
		TextView tv_spinner_item;
	}
}
