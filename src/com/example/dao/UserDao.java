package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.example.bean.Good;
import com.example.db.MyDataBase;
import com.example.myapplication.MyApplication;

public class UserDao {
	private MyDataBase helper;
	private SQLiteDatabase db;

	public UserDao(Context context) {
		helper = new MyDataBase(context);
	}

	/**
	 * 添加数据
	 * 
	 * @param good
	 */
	public void add(Good good) {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		// 执行添加信息操作
		ContentValues values = new ContentValues();
		values.put("goodName", good.getGoodName());
		values.put("goodPrice", good.getGoodPrice());
		values.put("goodWeight", good.getGoodWeight());
		values.put("goodImgPath", good.getGoodImgPath());
		values.put("oneBoxWeight", good.getOneBoxWeight());
		db.insert("Good", null, values);// 返回一个rowId
		db.close();
		// db.execSQL("insert into Good (goodName,goodPrice,goodWeight,goodImgPath,oneBoxWeight) values ('"+good.getGoodName()+"','"+good.getGoodPrice()+"','"+good.getGoodWeight()+"','"+good.getGoodImgPath()+"','"+good.getOnBoxWeight()+"')");
	}

	/**
	 * 查询
	 */
	public List<Good> queryData() {
		int count = 0;
		List<Good> list = new ArrayList<>();
		db = helper.getWritableDatabase();
		String[] columns = { "goodName", "goodPrice", "goodImgPath",
				"goodWeight" };
		Cursor c = db.query("Good", null, null, null, null, null, null);
		if (c != null) {
			// if (c.moveToFirst()) {
			while (c.moveToNext()) {
				Good good = new Good();
				String goodName = c.getString(c.getColumnIndex("goodName"));
				String goodPrice = c.getString(c.getColumnIndex("goodPrice"));
				String goodImgPath = c.getString(c
						.getColumnIndex("goodImgPath"));
				String goodWeight = c.getString(c.getColumnIndex("goodWeight"));
				String oneBoxWeight = c.getString(c
						.getColumnIndex("oneBoxWeight"));
				good.setGoodName(goodName);
				good.setGoodPrice(goodPrice);
				good.setGoodImgPath(goodImgPath);
				good.setGoodWeight(goodWeight);
				good.setOneBoxWeight(oneBoxWeight);
				list.add(good);
//				if(!goodWeight.equals("0")){
//					list.add(good);
//				}
				// }
			}
		}
		c.close();
		db.close();
		return list;
	}

	/**
	 * 删除所有
	 * 
	 * @param list
	 */
	public void deleteAll() {
		db = helper.getWritableDatabase();
		db.delete("Good", null, null);
		db.close();
	}

	/**
	 * 根据ID删数据
	 * 
	 * @param id
	 */
	public void deleteById(int id) {
		db = helper.getWritableDatabase();
		db.delete("Good", "_id=?", new String[] { String.valueOf(id) });
		db.close();
	}

	/**
	 * 根据名字删数据
	 * 
	 * @param id
	 */
	public void deleteByName(String goodName) {
		db = helper.getWritableDatabase();
		db.delete("Good", "goodName=?", new String[] { goodName });
		db.close();
	}

	/**
	 * 通过名字判断商品是否存在
	 * 
	 * @param dataKey
	 *            需要查询的值对应的键名
	 * @param data
	 *            需要查询的值
	 * @return
	 */
	public <T> boolean query(T dataKey, T data) {
		db = helper.getWritableDatabase();
		Cursor c = db.query("Good", new String[] { "goodName" },
				dataKey + "=?", new String[] { (String) data }, null, null,
				null);
		if (c != null /* || c.moveToFirst() */) {
			if (c.moveToNext()) {
				db.close();
				return true;
			}
		}
		db.close();
		return false;
	}

	public String queryGoodWeightByName(String[] name) {
		db = helper.getWritableDatabase();
		String[] columns = { "goodWeight" };
		String selection = "goodName=?";
		Cursor c = db.query("Good", columns, selection, name, null, null, null);
		while (c.moveToNext()) {
			return c.getString(c.getColumnIndex("goodWeight"));
		}
		return "";
	}

	/**
	 * @param tableName
	 * @param dataKey
	 *            改变数据的键
	 * @param data
	 *            改变数据的值
	 * @param values
	 *            约束条件的值
	 */
	public <T> void Updata(String tableName, T dataKey, T data, String[] values) {
		db = helper.getWritableDatabase();
		ContentValues Cvalues = new ContentValues();
		Cvalues.put(dataKey.toString(), data.toString());
		db.update(tableName, Cvalues, "goodName=?", values);
		Log.d(MyApplication.TAG, 	db.update(tableName, Cvalues, "goodName=?", values)+"");
		db.close();
	}

}
