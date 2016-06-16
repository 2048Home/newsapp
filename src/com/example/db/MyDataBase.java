package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper{
   private static final int VERSION=1;
   private static final String DBNAME="newapp.db";
   
   private static final String CREATE_USER_TABLE="create table  if not exists User( _id integer primary key autoincrement, " +   
           "name text,password text)";//用户表
   private static final String CREATE_GOOD_TABLE="create table  if not exists Good( _id integer primary key autoincrement, " +   
			"goodName text, goodPrice text,goodWeight text,goodImgPath text,oneBoxWeight text)";
   
	public MyDataBase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DBNAME, factory, VERSION);
		// TODO 自动生成的构造函数存根
	}
	public MyDataBase(Context context){
		super(context,DBNAME,null,VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自动生成的方法存根
		db.execSQL(CREATE_USER_TABLE);//创建用户表
		db.execSQL(CREATE_GOOD_TABLE);//创建商品表
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
		
	}

}
