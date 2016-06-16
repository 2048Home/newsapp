package com.example.utils;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;

/**
 * @author LPM
 * @version 1.0
 * @created 25-九月-2014 15:21:06
 */
public class JosnOper {

	public JosnOper(){

	}

	public void finalize() throws Throwable {

	}

//	/**
//	 * 将Json替换成正确的格式(暂不考虑)
//	 * 
//	 * @param jsonStr
//	 */
//	public static String HandlerJsonToRightForm(String jsonStr){
//		return "";
//	}

	/**
	 * 将json转换为List集合
	 * 
	 * @param json 
	 * @param t
	 * @deprecated ConvertJsonToArray(json, new TypeToken<List<Person>>(){}.getType())
	 */

	
	public <T> List<T> ConvertJsonToArray(String json , Type t){
		Gson gson = new Gson();
		List<T> list = gson.fromJson(json, t);
		return list;
	}

	/**
	 * 把json转换成指定的Model对象
	 * @param <T>
	 * 
	 * @param json
	 * @param classOfT
	 */
	public static <T> Object ConvertJsonToModel(String json, Class<T> classOfT){

			return new Gson().fromJson(json, classOfT);

	}

}