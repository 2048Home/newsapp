package com.example.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.json.JSONException;

import android.util.Log;

import com.example.bean.Good;
import com.example.myapplication.MyApplication;

public class HttpConnectUtils {

	/**
	 * 通过get方式访问
	 * @param path
	 * @return
	 * @throws HttpHostConnectException
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @throws ClientProtocolException
	 * @throws JSONException
	 * @throws IOException
	 * @throws Exception
	 */
	public static String httpConncet(String path)
			throws HttpHostConnectException, ConnectTimeoutException,
			SocketTimeoutException, ClientProtocolException, JSONException,
			IOException, Exception {
		String Str_JSONArray = null;
		InputStream is = null;
		URL url = new URL(path);
		// ----------new start-----------
		HttpURLConnection httpURLConnection = (HttpURLConnection) url
				.openConnection();
		httpURLConnection.setConnectTimeout(3000);
		httpURLConnection.setReadTimeout(3000);
		httpURLConnection.connect();
		if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			InputStream stream = httpURLConnection.getInputStream();
			byte[] bff = readInputStream(stream);
			Str_JSONArray = new String(bff);
			if (stream != null) {
				stream.close();
			}
		}
		httpURLConnection.disconnect();
		return Str_JSONArray;
	}

	/**
	 * 从输入流读取数据
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		if (outSteam != null) {
			outSteam.close();

		}
		if (outSteam != null) {
			inStream.close();

		}
		inStream.close();
		return outSteam.toByteArray();
	}

	/**
	 * 通过post访问
	 * @param Path
	 * @return
	 * @throws Exception
	 */
	public static String httpConnectByPost(String Path) throws Exception {
		String Str_JSONArray = null;
		URL url = new URL(Path);
		HttpURLConnection httpUrlConnection = (HttpURLConnection) url
				.openConnection();// 创建一个HTTP连接
		httpUrlConnection.setRequestMethod("POST");// 指定使用POST请求方式
		httpUrlConnection.setDoInput(true);// 向连接中写入数据
		httpUrlConnection.setDoInput(true);// 向连接中读取数据
		httpUrlConnection.setUseCaches(false);// 禁止使用缓存
		httpUrlConnection.setInstanceFollowRedirects(true);// 自动执行HTTP重定向
		DataOutputStream out = new DataOutputStream(
				httpUrlConnection.getOutputStream());// 获取输出流
		List<Good> list = new ArrayList<Good>();
		Good good = new Good();
		good.setGoodName("huanggua");
		good.setGoodPrice("21");
		good.setGoodWeight("52");
		list.add(good);
		try {
			String data = "userName="+"153641583973"+"&json=" + JsonUtils.setJsonData(list);
			out.writeBytes(data);
			if (out != null) {
				out.flush();
				out.close();
			}
			if (httpUrlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream is = httpUrlConnection.getInputStream();
				byte[] bff = readInputStream(is);
				Str_JSONArray = new String(bff);
				if (is != null) {
					is.close();
				}
				httpUrlConnection.disconnect();
				return Str_JSONArray;
			}
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			Log.e(MyApplication.TAG, "JSONException=" + e);
			e.printStackTrace();
		}

		return Str_JSONArray;

	}

}
