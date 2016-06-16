package com.example.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.Good;
import com.example.dao.UserDao;
import com.example.myapplication.MyApplication;
import com.example.newapp.R;

/**
 * @作者 陈籽屹
 * @时间 2016年5月29日 购物车
 */
public class ShoppingCarAdapter extends BaseAdapter {
	private List<Good> listdata;
	private Context context;
	private LayoutInflater layoutInflater;
	private ViewHolder holder;
	final int ADD = 1;
	final int REMOVE = 2;
	final int NONE = 0;
	HashMap<Integer, View> map = new HashMap<>();
	String url = "http://192.168.1.102:8080/Vegetable/upload/";

	public ShoppingCarAdapter(List<Good> listdata, Context context) {
		super();
		this.listdata = listdata;
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		final String price = listdata.get(position).getGoodPrice();
		if (map.get(position) == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.shopping_car_item,
					null);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_single_price = (TextView) convertView
					.findViewById(R.id.tv_single_price);
			holder.tv_jian = (TextView) convertView.findViewById(R.id.tv_jian);
			holder.tv_show = (TextView) convertView.findViewById(R.id.tv_show);
			holder.tv_jia = (TextView) convertView.findViewById(R.id.tv_jia);
			holder.iv_picture_name = (ImageView) convertView
					.findViewById(R.id.iv_picture_name);
			holder.iv_remove = (ImageView) convertView
					.findViewById(R.id.iv_remove);
			map.put(position, convertView);
			convertView.setTag(holder);
		} else {
			//把convertView存起来，防止ITEM在异步加载时候错乱
			convertView = map.get(position);
			convertView.getTag();
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.iv_remove.setOnClickListener(new MyOnclick(position, holder,
				NONE, onJianListener, ""));
		holder.tv_jia.setOnClickListener(new MyOnclick(position, holder, ADD,
				onJianListener, price));
		holder.tv_jian.setOnClickListener(new MyOnclick(position, holder,
				REMOVE, onJianListener, price));
        //填充数据
		Glide.with(context).load(listdata.get(position).getGoodImgPath())
				.centerCrop().error(R.drawable.dt_standard_index_news_bg)
				.placeholder(R.drawable.dt_standard_index_news_bg)
				.into(holder.iv_picture_name);
		holder.tv_name.setText(listdata.get(position).getGoodName());
		holder.tv_single_price.setText(price);

		return convertView;
	}

	class ViewHolder {
		private TextView tv_name;
		private TextView tv_single_price;
		private TextView tv_jian;
		private TextView tv_show;
		private TextView tv_jia;
		private ImageView iv_picture_name;
		private ImageView iv_remove;
	}

	public class MyOnclick implements OnClickListener {
		UserDao user;
		int position;
		ViewHolder holder;
		int state;
		onJianListener onJianListener;
		String price;
		final int ADD = 1;
		final int REMOVE = 2;
		final int NONE = 0;
		boolean reMarkPosition = true;
		int count = 0;
		HashMap<String, Integer> map;
		

		public MyOnclick(int position, ViewHolder holder, int state,
				onJianListener onJianListener, String price) {
			this.position = position;
			this.holder = holder;
			this.state = state;
			this.onJianListener = onJianListener;
			this.price = price;
			user=new UserDao(context);
		};

		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			int number = Integer.valueOf(holder.tv_show.getText().toString().trim());
			if (state == NONE) {
				listdata.remove(position);
				user.deleteById(position+1);
				notifyDataSetChanged();
			} else if (state == REMOVE) {
				if (onJianListener != null) {
					onJianListener.onJian(Integer.parseInt(price), "jian", 0);
					if (!holder.tv_show.getText().equals("0")) {
						// Log.d(MyApplication.TAG,
						// holder.tv_show.getText().toString().indexOf(0)+"");
				

						holder.tv_show.setText(--number + "");
					}
				}
			} else if (state == ADD) {
				if (onJianListener != null) {
					onJianListener.onJian(Integer.parseInt(price), "add", 0);

					holder.tv_show.setText(++number + "");
				}
			}
		}
	}

	onJianListener onJianListener;

	public interface onJianListener {
		void onJian(int price, String state, int position);
	}

	public void setonJianListener(onJianListener onJianListener) {
		this.onJianListener = onJianListener;
	}

}
