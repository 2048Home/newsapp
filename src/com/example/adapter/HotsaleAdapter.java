package com.example.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.Good;
import com.example.bean.User;
import com.example.dao.UserDao;
import com.example.newapp.R;

/**
 * @作者 陈籽屹
 * @时间 2016年5月29日 商品适配器
 */
public class HotsaleAdapter extends BaseAdapter {

	private List<Good> listdata;
	private Context context;
	private LayoutInflater layoutInflater;
	private ViewHolder holder;
	private String url = "http://192.168.1.102:8080/Vegetable/upload/";
    UserDao user;
	HashMap<Integer, View> map = new HashMap<>();

	public HotsaleAdapter(List<Good> listdata, Context context) {
		super();
		this.listdata = listdata;
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
		this.user=new UserDao(context);
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

		holder.iv_hotsale_car.setOnClickListener(new myOnClick(position));
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

		public myOnClick(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			Good good=new Good();
			good.setGoodName(listdata.get(position).getGoodName());
			good.setGoodPrice(listdata.get(position).getGoodPrice());
			good.setGoodImgPath(url+listdata.get(position).getGoodImgPath());
			good.setGoodWeight(listdata.get(position).getGoodWeight());
			good.setOneBoxWeight(listdata.get(position).getOneBoxWeight());
			user.add(good);
			if (onbuyListener != null) {
				onbuyListener.onBuy();
			}
		}

	}
}
