package com.example.utils;

import java.io.File;

import android.content.Context;

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
}
