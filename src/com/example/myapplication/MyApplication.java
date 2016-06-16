package com.example.myapplication;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.db.MyDataBase;

public class MyApplication extends Application {
	public static final String TAG = "MyApplication";
	public static Context context;
	MyDataBase db;
	@Override
	public void onCreate() {
		// TODO 自动生成的方法存根
		try {
			super.onCreate();
			context = getApplicationContext();
			db=new MyDataBase(context);
			db.getWritableDatabase();
			String processName = getCurProcessName(this);
			if (processName != null) {
				boolean defaultProcess = processName
						.equals("com.example.newapp");
				if (defaultProcess) {
					initAppForMainProcess();
				} else {
					return;
				}
			}
		} catch (Exception e) {
			// 防止不可遇见错误
			Log.d(TAG, "Exception=" + e);
		}
	}

	private void initAppForMainProcess() {
		// TODO 自动生成的方法存根
//		DisplayImageOptions options = new DisplayImageOptions.Builder()
//				.cacheInMemory(true).cacheOnDisc(true)
//				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//				.bitmapConfig(Bitmap.Config.RGB_565)// 防止内存溢出的，图片太多就这这个。还有其他设置
//				// 默认Bitmap.Config.ARGB_8888
//				.showImageOnLoading(R.drawable.dt_standard_index_news_bg) // 默认图片
//				.showImageForEmptyUri(R.drawable.dt_standard_index_news_bg) // url爲空會显示该图片，自己放在drawable里面的
//				.showImageOnFail(R.drawable.dt_standard_index_news_bg)// 加载失败显示的图片
//				.displayer(new RoundedBitmapDisplayer(1)) // 圆角，不需要请删除
//				.build();
//Log.d(TAG, "213123123123123");
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//				this)
//				.memoryCacheExtraOptions(480, 800)
//				// 缓存在内存的图片的宽和高度
//				.memoryCache(new WeakMemoryCache())
//				.memoryCacheSize(2 * 1024 * 1024)
//				// 缓存到内存的最大数据
//				.threadPriority(Thread.NORM_PRIORITY - 2)
//				.denyCacheImageMultipleSizesInMemory()
//				.discCacheFileNameGenerator(new Md5FileNameGenerator())
//				.tasksProcessingOrder(QueueProcessingType.LIFO) // 设置图片加载和显示队列处理的类型
//																// 默认为QueueProcessingType.FIFO
//				.defaultDisplayImageOptions(options) // 上面的options对象，一些属性配置
//				.build();
//
//		ImageLoader.getInstance().init(config); // 初始化
	}

	private String getCurProcessName(Context context) {
		int pid = android.os.Process.myPid();
		ActivityManager mActivityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
				.getRunningAppProcesses()) {
			if (appProcess.pid == pid) {
				 Log.d(TAG, appProcess.processName);
				return appProcess.processName;

			}
		}
		return null;
	}
}
