package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bean.Good;
import com.example.db.MyDataBase;

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
		List<Good> list = new ArrayList<>();
		db = helper.getWritableDatabase();
		String[] columns = { "goodName", "goodPrice", "goodImgPath",
				"goodWeight" };
		Cursor c = db.query("Good", null, null, null, null, null, null);
		if (c != null) {
			if (c.moveToFirst()) {
				while (c.moveToNext()) {
					Good good = new Good();
					String goodName = c.getString(c.getColumnIndex("goodName"));
					String goodPrice = c.getString(c
							.getColumnIndex("goodPrice"));
					String goodImgPath = c.getString(c
							.getColumnIndex("goodImgPath"));
					String goodWeight = c.getString(c
							.getColumnIndex("goodWeight"));
					String oneBoxWeight = c.getString(c
							.getColumnIndex("oneBoxWeight"));
					good.setGoodName(goodName);
					good.setGoodPrice(goodPrice);
					good.setGoodImgPath(goodImgPath);
					good.setGoodWeight(goodWeight);
					good.setOneBoxWeight(oneBoxWeight);
					list.add(good);
				}
			}
		}
		c.close();
		db.close();
		return list;
	}
	
	/**
	 * 删除所有
	 * @param list
	 */
	public void deleteAll(){
		db=helper.getWritableDatabase();
		db.delete("Good", null, null);
		db.close();
	}
	public void deleteById(int id){
		db=helper.getWritableDatabase();
		db.delete("Good", "_id=?", new String[]{String.valueOf(id)});
		db.close();
	}
}
