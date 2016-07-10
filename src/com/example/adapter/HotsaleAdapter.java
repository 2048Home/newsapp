package com.example.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.api.API;
import com.example.bean.Good;
import com.example.bean.User;
import com.example.dao.UserDao;
import com.example.newapp.LoginActivity;
import com.example.newapp.MyOrderActivity;
import com.example.newapp.R;
import com.example.utils.SPutils;

/**
 * @作者 陈籽屹
 * @时间 2016年5月29日 商品适配器
 */
public class HotsaleAdapter extends BaseAdapter {

	private List<Good> listdata;
	private Context context;
	private LayoutInflater layoutInflater;
	private ViewHolder holder;
	private String url = API.COMMON_URL + "/Vegetable/upload/";
	UserDao user;
	HashMap<Integer, View> map = new HashMap<>();

	public HotsaleAdapter(List<Good> listdata, Context context) {
		super();
		this.listdata = listdata;
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
		this.user = new UserDao(context);
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return listdata.size() != 0 ? listdata.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return listdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO 自动生成的方法存根
		return false;
	}

	int count = 0;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		if (map.get(position) == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.hotsale_item, null);
			holder.iv_hotsale_item = (ImageView) convertView
					.findViewById(R.id.iv_hotsale_item);
			holder.iv_hotsale_car = (ImageView) convertView
					.findViewById(R.id.iv_hotsale_car);
			holder.tv_hotsale_name = (TextView) convertView
					.findViewById(R.id.tv_hotsale_name);
			holder.tv_hotsale_price = (TextView) convertView
					.findViewById(R.id.tv_hotsale_price);
			map.put(position, convertView);
			convertView.setTag(holder);
		} else {
			convertView = map.get(position);
			convertView.getTag();
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.iv_hotsale_car
				.setOnClickListener(new myOnClick(position, holder));
		if (user.query("goodName", listdata.get(position)
				.getGoodName())){

			holder.iv_hotsale_car.setBackgroundResource(R.drawable.shoppingcar);
		}else {
			holder.iv_hotsale_car.setBackgroundResource(R.drawable.shoppingcar_nomal);
		}
		holder.tv_hotsale_name.setText(listdata.get(position).getGoodName());
		holder.tv_hotsale_price.setText("￥"
				+ listdata.get(position).getGoodPrice());
		final String Url = url + listdata.get(position).getGoodImgPath();
		Glide.with(context).load(Url).centerCrop().crossFade(500)
				.error(R.drawable.dt_standard_index_news_bg)
				.placeholder(R.drawable.dt_standard_index_news_bg).crossFade()
				.into(holder.iv_hotsale_item);

		return convertView;
	}

	class ViewHolder {
		private ImageView iv_hotsale_item;
		private ImageView iv_hotsale_car;
		private TextView tv_hotsale_price;
		private TextView tv_hotsale_name;
	}

	public interface onBuyListener {
		void onBuy();
	}

	onBuyListener onbuyListener;

	public void setOnBuyListener(onBuyListener onbuylistener) {
		this.onbuyListener = onbuylistener;
	}

	class myOnClick implements OnClickListener {
		int position;
		ViewHolder holder;

		public myOnClick(int position, ViewHolder holder) {
			this.position = position;
			this.holder = holder;
		}

		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			Intent intent = new Intent();
			if (SPutils.isLogin(context)) {
				if (!user.query("goodName", listdata.get(position)
						.getGoodName())) {
					Good good = new Good();
					good.setGoodName(listdata.get(position).getGoodName());
					good.setGoodPrice(listdata.get(position).getGoodPrice());
					good.setGoodImgPath(url
							+ listdata.get(position).getGoodImgPath());
					good.setGoodWeight(listdata.get(position).getGoodWeight());
					good.setOneBoxWeight(listdata.get(position)
							.getOneBoxWeight());
					user.add(good);
					 holder.iv_hotsale_car.setBackgroundResource(R.drawable.shoppingcar);
					if (onbuyListener != null) {
						onbuyListener.onBuy();
					}
				} else {
					Toast.makeText(context, "请勿重复添加", Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				intent.setClass(context, LoginActivity.class);
				context.startActivity(intent);
			}
		}

	}
}
