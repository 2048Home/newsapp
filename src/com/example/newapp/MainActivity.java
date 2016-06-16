package com.example.newapp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.HotsaleAdapter;
import com.example.adapter.SpinnerAdapter;
import com.example.base.BaseActivity;
import com.example.dao.UserDao;
import com.example.newapp.fragment.Fragment01;
import com.example.newapp.fragment.Fragment01.onCarListener;
import com.example.newapp.fragment.Fragment02;
import com.example.newapp.fragment.Fragment03;
import com.example.utils.InfoUtils;

public class MainActivity extends BaseActivity implements OnClickListener {
	private ViewPager viewPager;
	private TextView btn_01, btn_02, btn_03;
	private List<Fragment> fragmentList;
	private Spinner sp_title;
	private SpinnerAdapter sa;
	private List listdata;
	UserDao user;
	/**
	 * 计数
	 */
	private TextView tv_count;
	/**
	 * 标题
	 */
	private TextView tv_title;
	int count = 0;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case InfoUtils.INFO_SUCCESS:
				break;

			case InfoUtils.INFO_ERROR:
				break;
			}

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		btn_01 = (TextView) findViewById(R.id.btn_01);
		btn_02 = (TextView) findViewById(R.id.btn_02);
		btn_03 = (TextView) findViewById(R.id.btn_03);
		sp_title = (Spinner) findViewById(R.id.sp_title);
		tv_count = (TextView) findViewById(R.id.tv_count);
		tv_title = (TextView) findViewById(R.id.tv_title);

		btn_01.setOnClickListener(this);
		btn_02.setOnClickListener(this);
		btn_03.setOnClickListener(this);

		user = new UserDao(MainActivity.this);
		initSpinner();
		initFragment();
		initViewPager();
	}

	HotsaleAdapter hotsaleAdapter;

	private void initSpinner() {
		// TODO 自动生成的方法存根
		listdata = new ArrayList<>();
		listdata.add("111111");
		listdata.add("222222");
		listdata.add("333333");
		listdata.add("444444");
		sp_title.setVisibility(View.VISIBLE);
		sa = new SpinnerAdapter(MainActivity.this, listdata);

		sp_title.setAdapter(sa);
		sp_title.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO 自动生成的方法存根

			}
		});
	}

	private void initViewPager() {
		// TODO 自动生成的方法存根
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO 自动生成的方法存根
				switch (arg0) {
				case 0:
					tv_title.setText("全部商品");
					sp_title.setVisibility(View.VISIBLE);
					break;
				case 1:
					tv_title.setText("购物车");
					sp_title.setVisibility(View.GONE);
					fragmentList.get(1).onStart();
					break;
				case 2:
					tv_title.setText("我的");
					sp_title.setVisibility(View.GONE);
					break;

				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO 自动生成的方法存根

			}
		});
		viewPager
				.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
		// viewPager.setOffscreenPageLimit(fragmentList.size());
		viewPager.setOffscreenPageLimit(0);
		viewPager.setCurrentItem(0);
	}

	// int index = 0;

	private void initFragment() {
		// TODO 自动生成的方法存根
		fragmentList = new ArrayList<>();
		Fragment01 fragment01 = new Fragment01();
		fragmentList.add(fragment01);
		fragmentList.add(new Fragment02());
		fragmentList.add(new Fragment03());
		fragment01.setonCarListener(new onCarListener() {

			@Override
			public void onAddCar() {
				// TODO 自动生成的方法存根
				tv_count.setVisibility(View.VISIBLE);
				List list = user.queryData();
				// tv_count.setText(++index+"");
				tv_count.setText(list.size() + "");
			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.btn_01:
			viewPager.setCurrentItem(0);
			tv_title.setText("全部商品");
			break;
		case R.id.btn_02:
			viewPager.setCurrentItem(1);
			tv_title.setText("购物车");
			break;
		case R.id.btn_03:
			viewPager.setCurrentItem(2);
			tv_title.setText("我的");
			break;

		default:
			break;
		}
	}

	class MyFragmentAdapter extends FragmentPagerAdapter {

		public MyFragmentAdapter(FragmentManager fm) {
			super(fm);
			// TODO 自动生成的构造函数存根
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO 自动生成的方法存根
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return fragmentList.size();
		}

	}

	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自动生成的方法存根
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				// ExitApplication.getInstance().exit();
				return super.onKeyDown(keyCode, event);
			}
			return true;
		}
		return false;
	}
}
