package com.example.base;

import com.example.api.API;
import com.example.service.MyService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Messenger;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class BaseFragment extends Fragment{

@Override
public void onCreate(Bundle savedInstanceState) {
	// TODO 自动生成的方法存根
	super.onCreate(savedInstanceState);
}
	
	/**
	 * 热销商品
	 * @param paramContext
	 * @param url
	 * @param number
	 * @param paramMessenger
	 */
	public void get_hotsale_info(Context paramContext,String url,	String number,Messenger paramMessenger){
		Intent intent=new Intent(paramContext,MyService.class);
		intent.putExtra("url", url);
		intent.putExtra("number", number);
		intent.putExtra("api", API.HOTSALE_API);
		intent.putExtra(API.HOTSALE_MESSAGE, paramMessenger);
		paramContext.startService(intent);
		
	}
	
	/**
	 * 获取我的地址
	 * @param paramContext
	 * @param url
	 * @param name
	 * @param paramMessenger
	 */
	public void get_address(Context paramContext,String url,	String name ,Messenger paramMessenger){
		Intent intent=new Intent(paramContext,MyService.class);
		intent.putExtra("url", url);
		intent.putExtra("name", name);
		intent.putExtra("api", API.ADDRESS_API);
		intent.putExtra(API.ADDRESS_MESSAGE, paramMessenger);
		paramContext.startService(intent);
		
	}
	/**
	 * 下订单
	 * @param paramContext
	 * @param url
	 * @param userName
	 * @param jsondata
	 * @param paramMessenger
	 */
	public void set_order_info(Context paramContext,String url,	String userName,String address,String jsondata,Messenger paramMessenger){
		Intent intent=new Intent(paramContext,MyService.class);
		intent.putExtra("url", url);
		intent.putExtra("userName", userName);
		intent.putExtra("address", address);
		intent.putExtra("jsondata", jsondata);
		intent.putExtra("api", API.RequestOrder_API);
		intent.putExtra(API.RequestOrder_MESSAGE, paramMessenger);
		paramContext.startService(intent);
		
	}
	
}
