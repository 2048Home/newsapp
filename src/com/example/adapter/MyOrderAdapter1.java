package com.example.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Messenger;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.api.API;
import com.example.bean.Goods;
import com.example.bean.Order;
import com.example.newapp.R;
import com.example.service.MyService;

public class MyOrderAdapter1 extends BaseAdapter {
	private List<Order> list;
	private LayoutInflater inflater;
	private Context context;
	private Handler handler;

	// ViewHolder holder;

	public MyOrderAdapter1(List<Order> list, Handler handler, Context context) {
		super();
		this.list = list;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.handler = handler;
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return list != null ? list.size() : 0;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		HashMap<Integer, View> map = new HashMap<>();
		ViewHolder holder = null;
		if (map.get(position) == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.myorder_item, null);
			holder.tv_weight = (TextView) convertView
					.findViewById(R.id.tv_weight);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_singleprice = (TextView) convertView
					.findViewById(R.id.tv_singleprice);
			holder.tv_sumprice = (TextView) convertView
					.findViewById(R.id.tv_sumprice);
			holder.tv_priceKind = (TextView) convertView
					.findViewById(R.id.tv_priceKind);
//			holder.btn_confirm = (Button) convertView
//					.findViewById(R.id.btn_confirm);
			convertView.setTag(holder);

		} else {
			convertView = map.get(position);
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_name.setText(list.get(position).getGoods().get(position).getGoodName());
//		holder.tv_singleprice
//				.setText("单价：" + list.get(position).getGoodPrice());
//		holder.tv_weight.setText("重量：" + list.get(position).getGoodWeight());
//		holder.tv_sumprice.setText("合计：" + list.get(position).getGoodSum());
		// holder.tv_priceKind.setText(list.get(position).getPriceKind());
//		holder.btn_confirm.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO 自动生成的方法存根
//				confirmOrder(position);
//			}
//		});

		return convertView;
	}

	public class ViewHolder {
		TextView tv_weight;// 重量
		TextView tv_name;// 名称
		TextView tv_singleprice;// 单价
		TextView tv_sumprice;// 总价
		TextView tv_priceKind;// 类
//		Button btn_confirm;
	}

	// http:// 120.25.166.191/Vegetable/servlet/ ConfirmGood?orderId=xx &
	// goodState=xx

	public void confirmOrder(int position) {
		String url = API.CONFIRM_ORDER_URL;
		Intent intent = new Intent(context, MyService.class);
		intent.putExtra("url", url);
		intent.putExtra("orderId", list.get(position).getOrderId());
		intent.putExtra("goodState", "1");
		intent.putExtra("api", API.CONFIRM_ORDER);
		intent.putExtra(API.CONFIRM_ORDER_MESSAGE, new Messenger(handler));
		context.startService(intent);
	}
}
