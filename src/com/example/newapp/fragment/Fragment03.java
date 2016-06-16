package com.example.newapp.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.example.adapter.MineAdapter;
import com.example.base.BaseFragment;
import com.example.newapp.LoginActivity;
import com.example.newapp.ModifyPasswordActivity;
import com.example.newapp.MyOrderActivity;
import com.example.newapp.NewAddressActivity;
import com.example.newapp.R;
import com.example.newapp.RegisterActivity;
import com.example.utils.SPutils;

/**
 * @作者 陈籽屹
 * @时间 2016年5月30日 我的
 */
public class Fragment03 extends BaseFragment implements OnClickListener {
	private View view;
	/**
	 * 用户名
	 */
	private TextView tv_username;
	/**
	 * 登录/退出登录
	 */
	private TextView tv_exit;
	private GridView gv_mine;
	private List<Map<String, Object>> listdata;
	private MineAdapter mineAdapter;
	SharedPreferences userSp;
	/**
	 * 注册
	 */
	private TextView tv_register;
	private boolean isLogin = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		view = inflater.inflate(R.layout.mine, null);
		initView();
		return view;
	}

	@Override
	public void onResume() {
		// TODO 自动生成的方法存根
		super.onResume();

		if (SPutils.isLogin(getActivity())) {
			tv_username.setText(userSp.getString("username", "") + "  ,欢迎光临");
			isLogin = true;
			tv_exit.setText("退出登录");
			tv_register.setVisibility(View.GONE);
		} else {
			tv_username.setText("...");
			isLogin = false;
			tv_exit.setText("登录");
			tv_register.setVisibility(View.VISIBLE);
		}

	}

	private void initView() {
		// TODO 自动生成的方法存根
		userSp = getActivity().getSharedPreferences("UserInfo",
				Context.MODE_PRIVATE);
		listdata = new ArrayList<>();
		initGridviewData();
		tv_username = (TextView) view.findViewById(R.id.tv_username);
		tv_exit = (TextView) view.findViewById(R.id.tv_exit);
		gv_mine = (GridView) view.findViewById(R.id.gv_mine);
		tv_register = (TextView) view.findViewById(R.id.tv_register);

		tv_exit.setOnClickListener(this);
		tv_register.setOnClickListener(this);
		mineAdapter = new MineAdapter(getActivity(), listdata);

		gv_mine.setAdapter(mineAdapter);
		gv_mine.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				chooes(position);
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.tv_exit:
			if (isLogin) {
				exitLogin();
				tv_username.setText("...");
				isLogin = false;
				tv_exit.setText("登录");
				tv_register.setVisibility(View.VISIBLE);
			} else {
				intent.setClass(getActivity(), LoginActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.tv_register:
			intent.setClass(getActivity(), RegisterActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initGridviewData() {
		Map map = new HashMap<>();
		map.put("headicon", getResources().getDrawable(R.drawable.ic_launcher));
		map.put("name", "我的订单");
		listdata.add(map);
		map = new HashMap<>();
		map.put("headicon", getResources().getDrawable(R.drawable.ic_launcher));
		map.put("name", "收货地址");
		listdata.add(map);
		map = new HashMap<>();
		map.put("headicon", getResources().getDrawable(R.drawable.ic_launcher));
		map.put("name", "修改密码");
		listdata.add(map);
	}

	public void chooes(int position) {
		String strName = (String) listdata.get(position).get("name");
		Intent intent = new Intent();
		if (strName.equals("我的订单")) {
			if (SPutils.isLogin(getActivity())) {
				intent.setClass(getActivity(), MyOrderActivity.class);
				startActivity(intent);
			} else {
				intent.setClass(getActivity(), LoginActivity.class);
				startActivity(intent);
			}
		} else if (strName.equals("修改密码")) {
			if (SPutils.isLogin(getActivity())) {
				intent.setClass(getActivity(), ModifyPasswordActivity.class);
				startActivity(intent);
			} else {
				intent.setClass(getActivity(), LoginActivity.class);
				startActivity(intent);
			}
		} else {
			if (SPutils.isLogin(getActivity())) {
				intent.setClass(getActivity(), NewAddressActivity.class);
				startActivity(intent);
			} else {
				intent.setClass(getActivity(), LoginActivity.class);
				startActivity(intent);
			}
		}
	}

	private void exitLogin() {
		File file = new File("/data/data/" + getActivity().getPackageName()
				+ "/shared_prefs", "UserInfo.xml");

		if (file.exists()) {
			file.delete();
		}
	}
}
