package com.example.newapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;
import android.os.Parcelable;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.AddAddressAdapter;
import com.example.adapter.AddressAdapter;
import com.example.api.API;
import com.example.base.BaseActivity;
import com.example.bean.Address;
import com.example.utils.InfoUtils;

/**
 * @作者 陈籽屹
 * @时间 2016年6月3日 新增地址
 */
public class AddAddressActivity extends BaseActivity implements OnClickListener {
	private ListView listview;
	private List<Address> list;
	private AddAddressAdapter adapter;
	private AlertDialog dialog;

	private RelativeLayout rl_city_chooes;
	private RelativeLayout rl_quyu_chooes;
	private RelativeLayout rl_xiaoqu_chooes;
	private RelativeLayout rl_lianxiren_chooes;
	private RelativeLayout rl_dianhua_chooes;
	private RelativeLayout rl_xiangxidizhi_chooes;
	private TextView tv_city_chooes;
	private TextView tv_quyu_chooes;
	private TextView tv_xiaoqu_chooes;
	private TextView tv_lianxiren_chooes;
	private TextView tv_dianhua_chooes;
	private TextView tv_xiangxidizhi_chooes;

	private TextView tv_city;
	private TextView tv_quyu;
	private TextView tv_xiaoqu;
	private TextView tv_lianxiren;
	private TextView tv_dianhua;
	private TextView tv_xiangxidizhi;

	private TextView tv_save;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case InfoUtils.INFO_SUCCESS:
				Toast.makeText(AddAddressActivity.this,
						msg.obj.toString().trim(), Toast.LENGTH_SHORT).show();
				break;
			case InfoUtils.INFO_ERROR:

				break;

			}
		};
	};

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO 自动生成的方法存根
		super.onCreate(arg0);
		setContentView(R.layout.add_address);
		initTitle();
		setTitle("新增地址");

		initview();
		// initView();
		Intent intent = getIntent();
		if (intent != null && intent.getStringExtra("flag") == null) {
			Bundle bun = intent.getExtras();
			String state = bun.getString("state");
			Address address = (Address) bun.get("address");
			if (state.equals("edit")) {
				setAddInvisiable();
				setTitle("编辑地址");
//				position = bun.getInt("position");
//				tv_city_chooes.setText(address.getCity());
//				tv_quyu_chooes.setText(address.getCquyu());
//				tv_dianhua_chooes.setText(address.getDianhua());
//				tv_lianxiren_chooes.setText(address.getLianxiren());
//				tv_xiaoqu_chooes.setText(address.getXiaoqu());
//				tv_xiangxidizhi_chooes.setText(address.getXiangxidizhi());
			}
		} else {
			setImageVisiable();
		}
	}

	private void initview() {
		// TODO 自动生成的方法存根
		rl_city_chooes = (RelativeLayout) findViewById(R.id.rl_city_chooes);
		rl_quyu_chooes = (RelativeLayout) findViewById(R.id.rl_quyu_chooes);
		rl_xiaoqu_chooes = (RelativeLayout) findViewById(R.id.rl_xiaoqu_chooes);
		rl_lianxiren_chooes = (RelativeLayout) findViewById(R.id.rl_lianxiren_chooes);
		rl_dianhua_chooes = (RelativeLayout) findViewById(R.id.rl_dianhua_chooes);
		rl_xiangxidizhi_chooes = (RelativeLayout) findViewById(R.id.rl_xiangxidizhi_chooes);
		rl_city_chooes.setOnClickListener(this);
		rl_quyu_chooes.setOnClickListener(this);
		rl_xiaoqu_chooes.setOnClickListener(this);
		rl_lianxiren_chooes.setOnClickListener(this);
		rl_dianhua_chooes.setOnClickListener(this);
		rl_xiangxidizhi_chooes.setOnClickListener(this);

		tv_save = (TextView) findViewById(R.id.tv_save);
		tv_save.setOnClickListener(this);

		tv_city = (TextView) findViewById(R.id.tv_city);
		tv_quyu = (TextView) findViewById(R.id.tv_quyu);
		tv_xiaoqu = (TextView) findViewById(R.id.tv_xiaoqu);
		tv_lianxiren = (TextView) findViewById(R.id.tv_lianxiren);
		tv_dianhua = (TextView) findViewById(R.id.tv_dianhua);
		tv_xiangxidizhi = (TextView) findViewById(R.id.tv_xiangxidizhi);

		tv_city_chooes = (TextView) findViewById(R.id.tv_city_chooes);
		tv_quyu_chooes = (TextView) findViewById(R.id.tv_quyu_chooes);
		tv_xiaoqu_chooes = (TextView) findViewById(R.id.tv_xiaoqu_chooes);
		tv_lianxiren_chooes = (TextView) findViewById(R.id.tv_lianxiren_chooes);
		tv_dianhua_chooes = (TextView) findViewById(R.id.tv_dianhua_chooes);
		tv_xiangxidizhi_chooes = (TextView) findViewById(R.id.tv_xiangxidizhi_chooes);

	}

	Address address;

	/**
	 * 设置对话框
	 * 
	 * @param name
	 */
	public void setDialog(String name) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				AddAddressActivity.this);
		if (name.equals("城市")) {
			final String[] items = new String[] { "长沙市", "株洲市" };
			builder.setTitle("请选择城市")
					.setSingleChoiceItems(items, 0,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO 自动生成的方法存根
									tv_city_chooes.setText(items[which]);
									dialog.dismiss();
								}
							}).setCancelable(false).create().show();
		} else if (name.equals("区域")) {
			final String[] items = new String[] { "天心区", "雨花区", "岳麓区", "芙蓉区",
					"开福区" };
			builder.setTitle("请选择区域")
					.setSingleChoiceItems(items, 0,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO 自动生成的方法存根
									tv_quyu_chooes.setText(items[which]);
									dialog.dismiss();
								}
							}).setCancelable(false).create().show();
		} else if (name.equals("小区")) {
			final String[] items = new String[] { "天心区", "雨花区", "岳麓区", "芙蓉区",
					"开福区" };
			builder.setTitle("请选择小区")
					.setSingleChoiceItems(items, 0,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO 自动生成的方法存根
									tv_xiaoqu_chooes.setText(items[which]);
									dialog.dismiss();
								}
							}).setCancelable(false).create().show();
		} else if (name.equals("联系人")) {
			final EditText edit = new EditText(AddAddressActivity.this);
			edit.setBackground(null);
			builder.setTitle("请输入联系人名称")
					.setView(edit)
					.setNeutralButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO 自动生成的方法存根
									tv_lianxiren_chooes.setText(edit.getText());
									dialog.dismiss();
								}

							}).setCancelable(false).create().show();

		} else if (name.equals("电话")) {
			final EditText edit = new EditText(AddAddressActivity.this);
			edit.setBackground(null);
			edit.setInputType(InputType.TYPE_CLASS_PHONE);
			builder.setTitle("请输入电话号码")
					.setView(edit)
					.setNeutralButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO 自动生成的方法存根
									tv_dianhua_chooes.setText(edit.getText());
									dialog.dismiss();
								}

							}).setCancelable(false).create().show();
		} else if (name.equals("详细地址")) {
			final EditText edit = new EditText(AddAddressActivity.this);
			edit.setBackground(null);
			builder.setTitle("请输入详细地址")
					.setView(edit)
					.setNeutralButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO 自动生成的方法存根
									tv_xiangxidizhi_chooes.setText(edit
											.getText());
									dialog.dismiss();
								}

							}).setCancelable(false).create().show();
		}

	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		String name = "";
		super.onClick(v);
		switch (v.getId()) {
		case R.id.rl_city_chooes:
			name = (String) tv_city.getText();
			setDialog(name);
			break;
		case R.id.rl_quyu_chooes:
			name = (String) tv_quyu.getText();
			setDialog(name);
			break;
		case R.id.rl_xiaoqu_chooes:
			name = (String) tv_xiaoqu.getText();
			setDialog(name);
			break;
		case R.id.rl_lianxiren_chooes:
			name = (String) tv_lianxiren.getText();
			setDialog(name);
			break;
		case R.id.rl_dianhua_chooes:
			name = (String) tv_dianhua.getText();
			setDialog(name);
			break;
		case R.id.rl_xiangxidizhi_chooes:
			name = (String) tv_xiangxidizhi.getText();
			setDialog(name);
			break;
		case R.id.tv_save:
			// Intent intent = new Intent();
			// address = new Address();
			// Bundle bun = new Bundle();
			// address.setCity(tv_city_chooes.getText().toString());
			// address.setCquyu(tv_quyu_chooes.getText().toString());
			// address.setDianhua(tv_dianhua_chooes.getText().toString());
			// address.setLianxiren(tv_lianxiren_chooes.getText().toString());
			// address.setXiaoqu(tv_xiaoqu_chooes.getText().toString());
			// address.setXiangxidizhi(tv_xiangxidizhi_chooes.getText().toString());
			// bun.putSerializable("address", address);
			// bun.putInt("position", position);
			// intent.putExtras(bun);
			// if (position == 0) {
			// setResult(20, intent);
			//
			// } else {
			// setResult(30, intent);
			// }
			String url = API.CHANGE_ADDRESS_URL;
			String userName = "yzk";
			String address = "湖南";
			get_change_address(AddAddressActivity.this, url, userName, address,
					new Messenger(handler));
//			this.finish();
			break;
		default:
			break;
		}
	}

}
