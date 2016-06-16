package com.example.newapp;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.widget.ListView;

import com.example.adapter.MyOrderAdapter;
import com.example.api.API;
import com.example.base.BaseActivity;
import com.example.bean.Order;
import com.example.utils.InfoUtils;

/**
 * 我的订单
 * @作者  陈籽屹
 * @时间 2016年6月13日
 */
public class MyOrderActivity extends BaseActivity {
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case InfoUtils.INFO_SUCCESS:

				break;

			case InfoUtils.INFO_ERROR:

				break;
			}
		};
	};
private ListView lv_myorder;
private MyOrderAdapter adapter;
private List<Order> list;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO 自动生成的方法存根
		super.onCreate(arg0);
		setContentView(R.layout.myorder_activity);
		lv_myorder=(ListView) findViewById(R.id.lv_myorder);
//		adapter=new MyOrderAdapter(list, MyOrderActivity.this);
//		lv_myorder.setAdapter(adapter);
		getData();
	}

	private void getData() {
		// TODO 自动生成的方法存根
		String url = API.FinishOrderOrder_URL;
		get_myorder(MyOrderActivity.this, url, "153641583973", new Messenger(
				handler));
	}

}
