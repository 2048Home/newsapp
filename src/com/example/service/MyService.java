package com.example.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.util.Log;

import com.example.api.API;
import com.example.bean.Good;
import com.example.bean.Order;
import com.example.myapplication.MyApplication;
import com.example.utils.HttpConnectUtils;
import com.example.utils.InfoUtils;
import com.example.utils.JosnOper;
import com.example.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class MyService extends IntentService {

	public MyService(String name) {
		super(name);
		// TODO 自动生成的构造函数存根
	}

	@SuppressLint({ "SimpleDateFormat" })
	public MyService() {
		super("com.example.service.MyService");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO 自动生成的方法存根
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onCreate() {
		// TODO 自动生成的方法存根
		super.onCreate();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO 自动生成的方法存根
		int i = intent.getIntExtra("api", 0);
		if (i == 0) {
			return;
		}
		switch (i) {
		case API.HOTSALE_API:
			get_hotsale_info(intent);
			break;
		case API.LOGIN_API:
			get_login_info(intent);
			break;
		case API.REGISTER_API:
			get_register_info(intent);
			break;
		case API.RequestOrder_API:
			get_requestOrde_info(intent);
			break;
		case API.FinishOrderOrder_API:
			get_finishOrder_info(intent);
			break;
		case API.ChangPassword_API:
			get_changepassword_info(intent);
			break;
		case API.CHANGE_ADDRESS_API:
			get_changeaddress(intent);
			break;
		case API.ADDRESS_API:
			get_address(intent);
			break;

		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private void sendMsgToAct(Intent paramIntent, int paramInt,
			String paramString, List<?> paramList) {
		int i = paramIntent.getIntExtra("api", 0);
		Bundle localBundle1 = paramIntent.getExtras();
		Messenger localMessenger = null;
		if (localBundle1 != null) {
			localMessenger = null;
			switch (i) {
			case API.HOTSALE_API:
				localMessenger = (Messenger) localBundle1
						.get(API.HOTSALE_MESSAGE);
				break;
			case API.LOGIN_API:
				localMessenger = (Messenger) localBundle1
						.get(API.LOGIN_MESSAGE);
				break;
			case API.REGISTER_API:
				localMessenger = (Messenger) localBundle1
						.get(API.REGISTER_MESSAGE);
				break;
			case API.RequestOrder_API:
				localMessenger = (Messenger) localBundle1
						.get(API.RequestOrder_MESSAGE);
				break;
			case API.FinishOrderOrder_API:
				localMessenger = (Messenger) localBundle1
						.get(API.FinishOrderOrder_MESSAGE);
				break;
			case API.ChangPassword_API:
				localMessenger = (Messenger) localBundle1
						.get(API.ChangPassword_MESSAGE);
				break;
			case API.CHANGE_ADDRESS_API:
				localMessenger = (Messenger) localBundle1
						.get(API.CHANGE_ADDRESS_MESSAGE);
				break;
			case API.ADDRESS_API:
				localMessenger = (Messenger) localBundle1
						.get(API.ADDRESS_MESSAGE);
				break;
			default:
				break;
			}
		}
		Message localMessage = Message.obtain();
		localMessage.what = paramInt;
		localMessage.obj = paramString;

		if (paramList != null) {
			Bundle localBundle2 = new Bundle();
			ArrayList<List<?>> localArrayList = new ArrayList<List<?>>();
			localArrayList.add(paramList);
			localBundle2.putParcelableArrayList("list",
					(ArrayList<? extends Parcelable>) localArrayList);
			localMessage.setData(localBundle2);
		}

		try {
			localMessenger.send(localMessage);
			return;
		} catch (Exception localException) {
			Log.w(super.getClass().getName(), "Exception Message: "
					+ localException.toString());
		}

	}

	/**
	 * 获取地址
	 * 
	 * @param intent
	 */
	private void get_address(Intent intent) {
		// TODO 自动生成的方法存根
		String url = intent.getStringExtra("url");
		String userName = "userName=" + intent.getStringExtra("name");
		String[] str = { userName };
		String URL = url + setUrl(str);

		String json;
		try {
			json = HttpConnectUtils.httpConncet(URL);
			sendMsgToAct(intent, InfoUtils.INFO_SUCCESS, json, null);
		} catch (HttpHostConnectException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	/**
	 * 修改地址
	 * 
	 * @param intent
	 */
	private void get_changeaddress(Intent intent) {
		// TODO 自动生成的方法存根
		String url = intent.getStringExtra("url");
		String userName = "userName=" + intent.getStringExtra("name");
		String address = "address=" + intent.getStringExtra("address");
		String[] str = { userName, address };
		String URL = url + setUrl(str);
		try {
			String json = HttpConnectUtils.httpConncet(URL);
			sendMsgToAct(intent, InfoUtils.INFO_SUCCESS, json, null);
		} catch (HttpHostConnectException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param intent
	 */
	private void get_changepassword_info(Intent intent) {
		// TODO 自动生成的方法存根
		String url = intent.getStringExtra("url");
		String name = "name=" + intent.getStringExtra("name");
		String password1 = "password1=" + intent.getStringExtra("password");
		String password2 = "password2="
				+ intent.getStringExtra("againpassword");
		String[] str = new String[] { name, password1, password2 };
		String URL = url + setUrl(str);
		try {
			String json = HttpConnectUtils.httpConncet(URL);
			sendMsgToAct(intent, InfoUtils.INFO_SUCCESS, json, null);
		} catch (HttpHostConnectException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 完成订单
	 * 
	 * @param intent
	 */
	private void get_finishOrder_info(Intent intent) {
		// TODO 自动生成的方法存根
		List<Order> list=new ArrayList<>();;
		String url = intent.getStringExtra("url");
		String userid = "userId=" + intent.getStringExtra("userId");
		String[] str = new String[] { userid };
		String URL = url + setUrl(str);
		try {
			String json = HttpConnectUtils.httpConncet(URL);
			
			List<Order> templist=new ArrayList<>();
            JSONArray jaab=new JSONArray(json);
            for(int x=0;x<jaab.length();x++){
			JSONArray ja=(JSONArray) jaab.get(x);
			String jsonstr=ja.toString();
			JosnOper jp=new JosnOper();
			list=jp.ConvertJsonToArray(jsonstr, new TypeToken<List<Order>>(){}.getType());
			for(int i=0;i<list.size();i++){
				Order order=new Order();
				order.setGoodName(list.get(i).getGoodName());
				order.setGoodPrice(list.get(i).getGoodPrice());
				order.setGoodSum(list.get(i).getGoodSum());
				order.setGoodWeight(list.get(i).getGoodWeight());
				order.setOrderId(list.get(i).getOrderId());
				order.setPriceKind(list.get(i).getPriceKind());
				templist.add(order);
			}
            }
			sendMsgToAct(intent, InfoUtils.INFO_SUCCESS, "", templist);
		} catch (HttpHostConnectException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 下订单
	 * 
	 * @param intent
	 */
	private void get_requestOrde_info(Intent intent) {
		// TODO 自动生成的方法存根
		String url = intent.getStringExtra("url");
		String name = "userName=" + intent.getStringExtra("userName");
//		String address = "address=" + intent.getStringExtra("address");
		String address = "address=" + "address";
		String jsondata = "json=" + intent.getStringExtra("jsondata");
		String[] str = new String[] { name, address, jsondata };
		String URL = url + setUrl(str);
		Log.d(MyApplication.TAG,URL);
		// String URL = url ;
		try {
			// String json = HttpConnectUtils.httpConnectByPost(URL);
			String json = HttpConnectUtils.httpConncet(URL);
			sendMsgToAct(intent, InfoUtils.SET_ORDER_SUCCESS, json, null);
		} catch (HttpHostConnectException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 注册
	 * 
	 * @param intent
	 */
	private void get_register_info(Intent intent) {
		// TODO 自动生成的方法存根
		String url = intent.getStringExtra("url");
		String name = "userName=" + intent.getStringExtra("name");
		String password = "password=" + intent.getStringExtra("password");
		String address = "Address=" + intent.getStringExtra("address");
		String forgetAnswer = "forgetAnswer="
				+ intent.getStringExtra("forgetAnswer");
		String[] str = new String[] { name, password, address, forgetAnswer };
		String URL = url + setUrl(str);
		try {
			String json = HttpConnectUtils.httpConncet(URL);
			sendMsgToAct(intent, InfoUtils.INFO_SUCCESS, json, null);
		} catch (HttpHostConnectException e) {
			// TODO 自动生成的 catch 块
			sendMsgToAct(intent, InfoUtils.INFO_ERROR, "网络连接异常", null);
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			sendMsgToAct(intent, InfoUtils.INFO_ERROR, "网络连接超时", null);
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			sendMsgToAct(intent, InfoUtils.INFO_ERROR, "网络状态不稳定，请检查网络", null);
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			// Log.e(MyApplication.TAG,"IOException="+e);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 把参数转换为url
	 * 
	 * @param str
	 * @return
	 */
	public String setUrl(String[] str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			if (i < str.length - 1) {
				sb.append(str[i] + "&");
			} else {
				sb.append(str[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * 登录
	 * 
	 * @param intent
	 */
	private void get_login_info(Intent intent) {
		// TODO 自动生成的方法存根

		String url = intent.getStringExtra("url");
		String name = "name=" + intent.getStringExtra("name");
		String password = "password=" + intent.getStringExtra("password");
		String[] str = new String[] { name, password };
		String URL = url + setUrl(str);
		try {
			String jsonstr = HttpConnectUtils.httpConncet(URL);
			sendMsgToAct(intent, InfoUtils.INFO_SUCCESS, jsonstr, null);
		} catch (HttpHostConnectException e) {
			// TODO 自动生成的 catch 块
			sendMsgToAct(intent, InfoUtils.INFO_ERROR, "网络连接异常", null);
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			// TODO 自动生成的 catch 块
			sendMsgToAct(intent, InfoUtils.INFO_ERROR, "网络连接超时", null);
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			// TODO 自动生成的 catch 块
			sendMsgToAct(intent, InfoUtils.INFO_ERROR, "网络状态不稳定，请检查网络", null);
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			sendMsgToAct(intent, InfoUtils.INFO_ERROR, "数据异常", null);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	/**
	 * 获取热销商品
	 * 
	 * @param intent
	 */
	private void get_hotsale_info(Intent intent) {
		// TODO 自动生成的方法存根
		String url = intent.getStringExtra("url");
		String number = intent.getStringExtra("number");
		String URL = url + "number=" + number;
		try {
			String jsonstr = HttpConnectUtils.httpConncet(URL);
			List<Good> list = JsonUtils.jsonParse(jsonstr);
			sendMsgToAct(intent, InfoUtils.INFO_SUCCESS, "", list);
			Log.d(MyApplication.TAG, "********************7777");
		} catch (HttpHostConnectException e) {
			// TODO 自动生成的 catch 块
			sendMsgToAct(intent, InfoUtils.INFO_ERROR, "网络连接异常", null);
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			sendMsgToAct(intent, InfoUtils.INFO_ERROR, "网络连接超时", null);
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			// TODO 自动生成的 catch 块
			sendMsgToAct(intent, InfoUtils.INFO_ERROR, "网络状态不稳定，请检查网络", null);
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			sendMsgToAct(intent, InfoUtils.INFO_ERROR, "数据异常", null);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
