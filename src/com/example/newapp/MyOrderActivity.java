package com.example.newapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.MyOrderAdapter;
import com.example.api.API;
import com.example.base.BaseActivity;
import com.example.bean.Order;
import com.example.utils.InfoUtils;

/**
 * 我的订单
 * 
 * @作者 陈籽屹
 * @时间 2016年6月13日
 */
public class MyOrderActivity extends BaseActivity {
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case InfoUtils.INFO_SUCCESS:
				templist.clear();
				list.clear();
				templist = (List<Order>) msg.getData()
						.getParcelableArrayList("list").get(0);
				list.addAll(templist);
				adapter.notifyDataSetChanged();
				break;

			case InfoUtils.INFO_ERROR:
				Toast.makeText(MyOrderActivity.this, msg.obj.toString(),
						Toast.LENGTH_SHORT).show();
				break;
			case InfoUtils.GET_SUCCESS:
				Toast.makeText(MyOrderActivity.this,
						msg.obj.toString() == "1" ? "成功收货" : "收货异常",
						Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};
	private List<Order> templist;
	private ListView lv_myorder;
	private MyOrderAdapter adapter;
	private List<Order> list;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO 自动生成的方法存根
		super.onCreate(arg0);
		setContentView(R.layout.myorder_activity);
		lv_myorder = (ListView) findViewById(R.id.lv_myorder);
		templist = new ArrayList<>();
		list = new ArrayList<>();
		adapter = new MyOrderAdapter(list, handler, MyOrderActivity.this);
		// adapter = new OrderAdaoter();
		lv_myorder.setAdapter(adapter);
		getData();
	}

	private void getData() {
		// TODO 自动生成的方法存根
		SharedPreferences userSP = getSharedPreferences("UserInfo",
				MODE_PRIVATE);
		if (userSP != null) {
			String url = API.FinishOrderOrder_URL;
			get_myorder(MyOrderActivity.this, url,
					userSP.getString("username", ""), new Messenger(handler));
		}
	}

	// public class OrderAdaoter extends BaseAdapter {
	// HashMap<Integer, View> map = new HashMap<>();
	//
	// @Override
	// public int getCount() {
	// // TODO 自动生成的方法存根
	// return list.size();
	// }
	//
	// @Override
	// public Object getItem(int position) {
	// // TODO 自动生成的方法存根
	// return list.get(position);
	// }
	//
	// @Override
	// public long getItemId(int position) {
	// // TODO 自动生成的方法存根
	// return position;
	// }
	//
	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	// // TODO 自动生成的方法存根
	// ViewHolder holder = null;
	// if (map.get(position) == null) {
	// convertView = getLayoutInflater().inflate(
	// R.layout.order_adapter, null);
	// holder = new ViewHolder();
	// holder.lv_order = (ListView) convertView
	// .findViewById(R.id.lv_orderadapter);
	// holder.btn_confirm = (Button) convertView
	// .findViewById(R.id.btn_confirm);
	// convertView.setTag(holder);
	// } else {
	// convertView = map.get(position);
	// holder = (ViewHolder) convertView.getTag();
	// }
	// holder.lv_order.setAdapter(new MyOrderAdapter(list,
	// MyOrderActivity.this));
	// holder.btn_confirm.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO 自动生成的方法存根
	//
	// }
	// });
	// return convertView;
	// }
	//
	// class ViewHolder {
	// ListView lv_order;
	// Button btn_confirm;
	// }
	//
	// }
}
