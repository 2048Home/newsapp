package com.example.adapter;

import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.adapter.HotsaleAdapter.ViewHolder;
import com.example.bean.Address;
import com.example.newapp.AddAddressActivity;
import com.example.newapp.R;

/**
 * 收货地址
 * 
 * @作者 陈籽屹
 * @时间 2016年6月7日
 */
public class AddressAdapter extends BaseAdapter {
	private List<Address> listdata;

	private Context context;
	private LayoutInflater layoutInflater;
	private ViewHolder holder;

	public AddressAdapter(List<Address> listdata, Context context) {
		super();
		this.listdata = listdata;
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return listdata.size();
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.add_addrees_item,
					null);
			holder.name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.dianhua = (TextView) convertView
					.findViewById(R.id.tv_dianhua);
			holder.dizhi = (TextView) convertView.findViewById(R.id.tv_dizhi);
			holder.btn_delete = (Button) convertView
					.findViewById(R.id.btn_delete);
			holder.btn_edit=(Button) convertView.findViewById(R.id.btn_edit);
			holder.btn_edit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					Intent intent = new Intent();
					intent.setClass(context, AddAddressActivity.class);
					Bundle bun = new Bundle();
					bun.putSerializable("address", listdata.get(position));
					bun.putString("state", "edit");
					bun.putInt("position", position);
					intent.putExtras(bun);
					if (onChooeseListener != null) {
						onChooeseListener.onChooese(position,intent);
					}
				}
			});
			holder.btn_delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					if (onChooeseListener != null) {
						onChooeseListener.onChooese(position,null);
					}
				}
			});
			convertView.setTag(holder);
		} else {
			convertView.getTag();
		}
		holder.name.setText(listdata.get(position).getLianxiren());
		holder.dianhua.setText(listdata.get(position).getDianhua());
		holder.dizhi.setText(listdata.get(position).getXiangxidizhi());
		return convertView;
	}

	class ViewHolder {
		TextView name;
		TextView dianhua;
		TextView dizhi;
		Button btn_delete;
		Button btn_edit;
	}

	public interface OnChooeseListener {
		void onChooese(int position,Intent intent);
	}

	OnChooeseListener onChooeseListener;

	public void setOnChooeseListener(OnChooeseListener onChooeseListener) {
		this.onChooeseListener = onChooeseListener;
	}

}
