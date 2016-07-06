package com.example.newapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.HotsaleAdapter;
import com.example.adapter.HotsaleAdapter.onBuyListener;
import com.example.api.API;
import com.example.base.BaseFragment;
import com.example.bean.Good;
import com.example.myapplication.MyApplication;
import com.example.newapp.R;
import com.example.utils.InfoUtils;

public class MyView extends TextView{

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO 自动生成的构造函数存根
	}

	public MyView(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
	}
	@Override
	public boolean isFocused() {
		// TODO 自动生成的方法存根
		return true;
	}
	
}
