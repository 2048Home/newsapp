package com.example.newapp.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
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

/**
 * @作者 陈籽屹
 * @时间 2016年5月30日 商品展示
 */
public class Fragment01 extends BaseFragment {
	private View view;
	private GridView gv_hot_sale;
	private List<Good> listdata;
	private HotsaleAdapter hotsaleAdapter;
	private List<Good> tempList;
	private int index = 0;
	private Handler mhandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case InfoUtils.INFO_SUCCESS:
				Log.d(MyApplication.TAG, "********************5555");
				tempList.clear();
				tempList = (List<Good>) msg.getData()
						.getParcelableArrayList("list").get(0);
				Log.d(MyApplication.TAG, "********************6666");
				listdata.addAll(tempList);
				hotsaleAdapter.notifyDataSetChanged();
				break;

			case InfoUtils.INFO_ERROR:
				index-=10;
				getData();
				Toast.makeText(getActivity(), msg.obj.toString(),
						Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		view = inflater.inflate(R.layout.hot_sale, null);
		initView();
		return view;
	}

	private void initView() {
		// TODO 自动生成的方法存根
		getData();
		gv_hot_sale = (GridView) view.findViewById(R.id.gv_hot_sale);
		TextView tv_phone=(TextView) view.findViewById(R.id.tv_phone);
		// Good good = new Good();
		// good.setGoodImgPath("");
		// good.setGoodName("asdhakj");
		// good.setGoodPrice("200");
		// good.setGoodWeight("20");
		// listdata = new ArrayList<>();
		// listdata.add(good);
		tempList = new ArrayList<>();
		listdata = new ArrayList<>();
		hotsaleAdapter = new HotsaleAdapter(listdata, getActivity());
		gv_hot_sale.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO 自动生成的方法存根
				switch (scrollState) {
				case SCROLL_STATE_IDLE:// 不滚动
					// 当在底部
					if (gv_hot_sale.getLastVisiblePosition() == gv_hot_sale
							.getCount() - 1) {
						getData();
					}
					break;

				default:
					break;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO 自动生成的方法存根

			}
		});
		hotsaleAdapter.setOnBuyListener(new onBuyListener() {

			@Override
			public void onBuy() {
				// TODO 自动生成的方法存根
				if (oncListener != null) {
					oncListener.onAddCar();
				}
			}
		});
		gv_hot_sale.setAdapter(hotsaleAdapter);
	}

	public void getData() {
		String url = API.HOTSALE_URL;
//		int number = index+9;
		get_hotsale_info(getActivity(), url, index + "", new Messenger(
				mhandler));
		index+=10;
	}

	public interface onCarListener {
		void onAddCar();
	}

	onCarListener oncListener;

	public void setonCarListener(onCarListener oncListener) {
		this.oncListener = oncListener;
	}



	@Override
	public void onDestroy() {
		// TODO 自动生成的方法存根
		super.onDestroy();
		index = 0;
	}
}
