package com.example.utils;

import java.io.File;

import android.content.Context;
import android.content.SharedPreferences;

public class SPutils {
	public static boolean isLogin(Context context) {
		File file = new File("/data/data/" + context.getPackageName()
				+ "/shared_prefs", "UserInfo.xml");
		if(file.exists()){
			return true;
		}else {
			return false;
		}
	}
	
	public static String getUserName(Context context){
		SharedPreferences sp=context.getSharedPreferences("UserInfo",Context.MODE_PRIVATE);
		String userName="";
		if(sp!=null){
			userName=sp.getString("username", "");
			return userName;
		}
		return userName;
	}
}
