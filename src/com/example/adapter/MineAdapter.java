package com.example.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newapp.R;

/**
 * @作者  陈籽屹
 * @时间 2016年5月29日
 * 我的
 */
public class MineAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String,Object>> listdata;
	private LayoutInflater layoutInflater;
	private ViewHolder holder;

	public MineAdapter(Context context, List<Map<String,Object>> listdata) {
		super();
		this.context = context;
		this.listdata = listdata;
		this.layoutInflater = LayoutInflater.from(context);
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
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.mine_item, null);
			holder.tv_mine = (TextView) convertView.findViewById(R.id.tv_mine);
			holder.iv_mine = (ImageView) convertView.findViewById(R.id.iv_mine);
			convertView.setTag(holder);
		} else {
			convertView.getTag();
		}
		holder.iv_mine.setImageDrawable( (Drawable) listdata.get(position).get("headicon"));
		holder.tv_mine.setText((CharSequence) listdata.get(position).get("name"));
		
		return convertView;
	}

	class ViewHolder {
		private TextView tv_mine;
		private ImageView iv_mine;

	}
}
