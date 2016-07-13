package com.example.newapp.fragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.example.adapter.ShoppingCarAdapter;
import com.example.adapter.ShoppingCarAdapter.onJianListener;
import com.example.api.API;
import com.example.base.BaseFragment;
import com.example.bean.Good;
import com.example.dao.UserDao;
import com.example.newapp.LoginActivity;
import com.example.newapp.R;
import com.example.utils.InfoUtils;
import com.example.utils.JsonUtils;
import com.example.utils.SPutils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @作者 陈籽屹
 * @时间 2016年5月30日 购物车
 */
public class Fragment02 extends BaseFragment implements OnClickListener {
	protected static final String TAG = "Fragment02";
	private View view;
	private ListView shopping_car;
	private List<Good> listdata;
	private ShoppingCarAdapter shoppingCarAdapter;
	TextView tv_totalprice;
	SharedPreferences userSp;
	/**
	 * 清空
	 */
	Button btn_clear;
	UserDao user;

	/**
	 * 下单
	 */
	private Button btn_buy;
	private String Total = "合计：";
	private String address;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case InfoUtils.INFO_SUCCESS:
				address = msg.obj.toString();
				Log.d(TAG, "传入购物车地址：" + msg.obj.toString());
				break;

			case InfoUtils.INFO_ERROR:

				break;
			case InfoUtils.SET_ORDER_SUCCESS:
				Toast.makeText(getActivity(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
				break;
			case InfoUtils.SET_ORDER_ERROR:

				break;
			}

		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		view = inflater.inflate(R.layout.shopping_car, null);
		intiView();
		return view;
	}

	double count = 0;

	private void intiView() {
		user = new UserDao(getActivity());
		// getAddress();
		// TODO 自动生成的方法存根
		userSp = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		shopping_car = (ListView) view.findViewById(R.id.shopping_car);

	
		tv_totalprice = (TextView) view.findViewById(R.id.tv_totalprice);
		btn_clear = (Button) view.findViewById(R.id.btn_clear);
		btn_clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				if (SPutils.isLogin(getActivity())) {
					user.deleteAll();
					listdata.clear();
					// count = 0;
					// List<Good> list = user.queryData();
					tv_totalprice.setText("合计：" + 0);
					shoppingCarAdapter.notifyDataSetChanged();
				} else {
					Intent intent = new Intent();
					intent.setClass(getActivity(), LoginActivity.class);
					startActivity(intent);
				}

			}
		});

		btn_buy = (Button) view.findViewById(R.id.btn_buy);
		btn_buy.setOnClickListener(this);

	}

	@Override
	public void onStart() {
		// TODO 自动生成的方法存根
		super.onStart();
		getAddress();
		count = 0;
		tv_totalprice.setText(Total + String.valueOf(count));
		listdata = user.queryData();
		shoppingCarAdapter = new ShoppingCarAdapter(listdata, getActivity());
		// 设置数量的加减
		shoppingCarAdapter.setonJianListener(new onJianListener() {

			@Override
			public void onJian(double price, String state, int position, int number) {
				// TODO 自动生成的方法存根
				if (state.equals("add")) {
					count += price*5;
					// count=price;
					// Log.d(MyApplication.TAG, count+"");
					user.Updata("Good", "goodWeight", number, new String[] { listdata.get(position).getGoodName() });
					// Log.d(MyApplication.TAG,
					// listdata.get(position).getGoodName());
					tv_totalprice.setText(Total + String.valueOf(count));
				} else if (state.equals("jian")) {
					if (count > 0) {
						count -= price*5;
						// count=price;
						// Log.d(MyApplication.TAG, count+"");
						user.Updata("Good", "goodWeight", number,
								new String[] { listdata.get(position).getGoodName() });

						tv_totalprice.setText(Total + String.valueOf(count));
					}
				} else {
					// listdata.remove(position);
					// user.deleteById(position + 1);
					user.deleteByName(listdata.get(position).getGoodName());
					listdata.remove(position);
				}
				shoppingCarAdapter.notifyDataSetChanged();
			}
		});
		shopping_car.setAdapter(shoppingCarAdapter);
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.btn_buy:

			if (SPutils.isLogin(getActivity())) {
			
				setOrder(address);
			} else {
				Intent intent = new Intent();
				intent.setClass(getActivity(), LoginActivity.class);
				startActivity(intent);
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 获取地址
	 */
	public void getAddress() {
		String url = API.ADDRESS_URL;
		String userName = userSp != null ? userSp.getString("username", "") : "";
		get_address(getActivity(), url, userName, new Messenger(handler));
	}

	/**
	 * 下单之前提示选择地址
	 */
	public void setOrder(String address) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		View view = getActivity().getLayoutInflater().inflate(R.layout.request_order, null);
		final EditText et_order_address = (EditText) view.findViewById(R.id.et_order_address);
		Log.d(TAG, "下订单：" + address);
		et_order_address.setText(address == null ? "" : address);
		final EditText et_order_remark = (EditText) view.findViewById(R.id.et_order_remark);
		builder.setMessage("请核实地址及填写备注").setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO 自动生成的方法存根
				dialog.dismiss();
			};
		}).setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO 自动生成的方法存根
				List<Good> list = new ArrayList<>();
				String url = API.RequestOrder_URL;
				list = user.queryData();
				String userAddress = et_order_address.getText().toString().trim();
				try {
					String json = JsonUtils.setJsonData(list);
					if (list.size() != 0 && !userAddress.equals("")) {
						try {
							System.out.println("userAddress" + userAddress);
							set_order_info(getActivity(), url, SPutils.getUserName(getActivity()),
									URLEncoder.encode(userAddress, "UTF-8"),
									URLEncoder.encode(et_order_remark.getText().toString().trim(), "UTF-8"), json,
									new Messenger(handler));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						user.deleteAll();
						listdata.clear();
						tv_totalprice.setText("合计：" + 0);
						shoppingCarAdapter.notifyDataSetChanged();
					} else if (userAddress.equals("")) {
						Toast.makeText(getActivity(), "亲，请填写送货地址哦", Toast.LENGTH_LONG).show();
					} else if (list.size() == 0) {
						Toast.makeText(getActivity(), "亲，选择商品哦", Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}

		}).setCancelable(false).create().show();
		;
	}
}
