package com.example.newapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.API;
import com.example.base.BaseActivity;
import com.example.bean.Goods;
import com.example.bean.Order;
import com.example.service.MyService;
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
				for (int i = 0; i < list.size(); i++) {
					lv_myorder.expandGroup(i);
				}
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
	private ExpandableListView lv_myorder;
	private OrderAadpter adapter;
	private List<Order> list;
	private List<Goods> cGoods;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO 自动生成的方法存根
		super.onCreate(arg0);
		setContentView(R.layout.myorder_activity);
		lv_myorder = (ExpandableListView) findViewById(R.id.lv_myorder);
		templist = new ArrayList<>();
		list = new ArrayList<>();
		cGoods = new ArrayList<>();
		// adapter = new MyOrderAdapter(list, handler, MyOrderActivity.this);
		adapter = new OrderAadpter();
		lv_myorder.setAdapter(adapter);
		lv_myorder.setGroupIndicator(null);

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

	public void confirmOrder(int position) {
		String url = API.CONFIRM_ORDER_URL;
		Intent intent = new Intent(MyOrderActivity.this, MyService.class);
		intent.putExtra("url", url);
		intent.putExtra("orderId", list.get(position).getOrderId());
		intent.putExtra("goodState", "1");
		intent.putExtra("api", API.CONFIRM_ORDER);
		intent.putExtra(API.CONFIRM_ORDER_MESSAGE, new Messenger(handler));
		startService(intent);
	}

	public class OrderAadpter extends BaseExpandableListAdapter {

		@Override
		public int getGroupCount() {
			// TODO 自动生成的方法存根
			return list.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO 自动生成的方法存根
			return list.get(groupPosition).getGoods().size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			// TODO 自动生成的方法存根
			return list.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			// TODO 自动生成的方法存根
			return list.get(groupPosition).getGoods().get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			// TODO 自动生成的方法存根
			return 0;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO 自动生成的方法存根
			return 0;
		}

		@Override
		public boolean hasStableIds() {
			// TODO 自动生成的方法存根
			return false;
		}

		@Override
		public View getGroupView(final int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			// TODO 自动生成的方法存根
			HashMap<Integer, View> map = new HashMap<>();
			cGoods = (List<Goods>) list.get(groupPosition).getGoods();
			ViewHolderp holder = null;
			if (map.get(groupPosition)==null) {
				convertView = getLayoutInflater().inflate(
						R.layout.order_adapter, null);
				holder = new ViewHolderp();
				holder.lv_order = (ListView) convertView
						.findViewById(R.id.lv_orderadapter);
				holder.btn_confirm = (Button) convertView
						.findViewById(R.id.btn_confirm);
				convertView.setTag(holder);
			} else {
				convertView=map.get(groupPosition);
				holder = (ViewHolderp) convertView.getTag();
			}
			// holder.lv_order.setAdapter(new
			// MyOrderAdapter(list.get(position).getGoods()));
			// holder.lv_order.setAdapter(new MyOrderAdapter(list,
			// MyOrderActivity.this));
			holder.btn_confirm.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					confirmOrder(groupPosition);
					for(int i=0;i<cGoods.size();i++){
						cGoods.remove(i);
					}
					list.remove(groupPosition);
					notifyDataSetChanged();
				}
			});
			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			HashMap<Integer, View> map = new HashMap<>();
			ViewHolderc holder = null;
			if (map.get(childPosition)==null) {
				holder = new ViewHolderc();
				convertView = getLayoutInflater().inflate(
						R.layout.myorder_item, null);
				holder.tv_weight = (TextView) convertView
						.findViewById(R.id.tv_weight);
				holder.tv_name = (TextView) convertView
						.findViewById(R.id.tv_name);
				holder.tv_singleprice = (TextView) convertView
						.findViewById(R.id.tv_singleprice);
				holder.tv_sumprice = (TextView) convertView
						.findViewById(R.id.tv_sumprice);
				holder.tv_priceKind = (TextView) convertView
						.findViewById(R.id.tv_priceKind);
				// holder.btn_confirm = (Button) convertView
				// .findViewById(R.id.btn_confirm);
				convertView.setTag(holder);

			} else {
				convertView=map.get(childPosition);
				holder = (ViewHolderc) convertView.getTag();
			}
			holder.tv_name.setText(cGoods.get(childPosition).getGoodName());
			holder.tv_singleprice.setText("单价："
					+ cGoods.get(childPosition).getGoodPrice());
			holder.tv_weight.setText("重量："
					+ cGoods.get(childPosition).getGoodWeight());
			holder.tv_sumprice.setText("合计："
					+ cGoods.get(childPosition).getGoodSum());
			//
			// holder.tv_priceKind.setText(list.get(position).getPriceKind());
			// holder.btn_confirm.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// // TODO 自动生成的方法存根
			// confirmOrder(position);
			// }
			// });
			return convertView;
			// TODO 自动生成的方法存根
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO 自动生成的方法存根
			return false;
		}

	}

	class ViewHolderc {
		TextView tv_weight;// 重量
		TextView tv_name;// 名称
		TextView tv_singleprice;// 单价
		TextView tv_sumprice;// 总价
		TextView tv_priceKind;// 类

		// Button btn_confirm;
	}

	class ViewHolderp {
		ListView lv_order;
		Button btn_confirm;
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
		// public View getView(int position, View convertView, ViewGroup parent)
		// {
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
		// holder.lv_order.setAdapter(new
		// MyOrderAdapter(list.get(position).getGoods()));
		// // holder.lv_order.setAdapter(new MyOrderAdapter(list,
		// // MyOrderActivity.this));
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
		//
		// public class MyOrderAdapter extends BaseAdapter {
		// private List<Goods> list;
		// private LayoutInflater inflater;
		// private Context context;
		// private Handler handler;
		//
		// // ViewHolder holder;
		// public MyOrderAdapter(List<Goods> list) {
		// this.list = list;
		// }
		//
		// // public MyOrderAdapter(List<Goods> list, Handler handler, Context
		// // context) {
		// // super();
		// // this.list = list;
		// // this.context = context;
		// // this.inflater = LayoutInflater.from(context);
		// // this.handler = handler;
		// // }
		//
		// @Override
		// public int getCount() {
		// // TODO 自动生成的方法存根
		// return list != null ? list.size() : 0;
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
		// public View getView(final int position, View convertView,
		// ViewGroup parent) {
		// // TODO 自动生成的方法存根
		// HashMap<Integer, View> map = new HashMap<>();
		// ViewHolder holder = null;
		// if (map.get(position) == null) {
		// holder = new ViewHolder();
		// convertView = getLayoutInflater().inflate(R.layout.myorder_item,
		// null);
		// holder.tv_weight = (TextView) convertView
		// .findViewById(R.id.tv_weight);
		// holder.tv_name = (TextView) convertView
		// .findViewById(R.id.tv_name);
		// holder.tv_singleprice = (TextView) convertView
		// .findViewById(R.id.tv_singleprice);
		// holder.tv_sumprice = (TextView) convertView
		// .findViewById(R.id.tv_sumprice);
		// holder.tv_priceKind = (TextView) convertView
		// .findViewById(R.id.tv_priceKind);
		// // holder.btn_confirm = (Button) convertView
		// // .findViewById(R.id.btn_confirm);
		// convertView.setTag(holder);
		//
		// } else {
		// convertView = map.get(position);
		// holder = (ViewHolder) convertView.getTag();
		// }
		// holder.tv_name.setText(list.get(position).getGoodName());
		// // holder.tv_singleprice.setText("单价："
		// // + list.get(position).getGoodPrice());
		// // holder.tv_weight
		// // .setText("重量：" + list.get(position).getGoodWeight());
		// // holder.tv_sumprice.setText("合计：" +
		// // list.get(position).getGoodSum());
		// // //
		// // holder.tv_priceKind.setText(list.get(position).getPriceKind());
		// // holder.btn_confirm.setOnClickListener(new OnClickListener() {
		// //
		// // @Override
		// // public void onClick(View v) {
		// // // TODO 自动生成的方法存根
		// // confirmOrder(position);
		// // }
		// // });
		//
		// return convertView;
		// }
		//
		// public class ViewHolder {
		// TextView tv_weight;// 重量
		// TextView tv_name;// 名称
		// TextView tv_singleprice;// 单价
		// TextView tv_sumprice;// 总价
		// TextView tv_priceKind;// 类
		// // Button btn_confirm;
		// }
		//
		// // http:// 120.25.166.191/Vegetable/servlet/ ConfirmGood?orderId=xx &
		// // goodState=xx
		//

		// }
	}
}
