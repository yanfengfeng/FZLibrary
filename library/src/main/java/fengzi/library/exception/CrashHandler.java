///*
// * File name: CrashHandler.java
// * Copyright: Copyright (c) 2006-2013 hoperun Inc, All rights reserved
// * Description: <描述>
// * Author: shen_feng
// * Last modified date: 2013-9-13
// * Version: <版本编号>
// * Edit history: <修改内容><修改人>
// */
//package fengzi.library.exception;
//
//import android.content.Context;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.pm.PackageManager.NameNotFoundException;
//import android.os.Build;
//import android.os.Process;
//
//import java.lang.Thread.UncaughtExceptionHandler;
//import java.lang.reflect.Field;
//
//import fengzi.library.tool.LogUtil;
//
///**
// *
// * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
// *
// * @author chuyanqiang
// * @Version [版本号, 2015-9-13]
// */
//public class CrashHandler implements UncaughtExceptionHandler {
//
//	public static final String TAG = "CrashHandler";
//
//	// 系统默认的UncaughtException处理类
//	private UncaughtExceptionHandler mDefaultHandler;
//
//	// CrashHandler实例
//	private static CrashHandler INSTANCE = new CrashHandler();
//
//	// 程序的Context对象
//	private Context mContext;
//
//	// 用来存储设备信息和异常信息
//	private StringBuilder sb = new StringBuilder();
//
//	/** 保证只有一个CrashHandler实例 */
//	private CrashHandler() {
//	}
//
//	/** 获取CrashHandler实例 ,单例模式 */
//	public static CrashHandler getInstance() {
//		return INSTANCE;
//	}
//
//	/**
//	 * 初始化
//	 *
//	 * @param context
//	 */
//	public void init(Context context, PostExceptionInter postExceptionInter) {
//		mContext = context;
//		this.postExceptionInter = postExceptionInter;
//		// 获取系统默认的UncaughtException处理器
//		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
//		// 设置该CrashHandler为程序的默认处理器
//		Thread.setDefaultUncaughtExceptionHandler(this);
//	}
//
//	/**
//	 * 当UncaughtException发生时会转入该函数来处理
//	 */
//	@Override
//	public void uncaughtException(Thread thread, final Throwable ex) {
//
//
//		ex.printStackTrace();
//		// 将问题给子类，子类实现
//		postExceptionInter.postException(ex, collectDeviceInfo(mContext));
//
//		//如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
//        if (mDefaultHandler != null) {
//        	mDefaultHandler.uncaughtException(thread, ex);
//        } else {
//            Process.killProcess(Process.myPid());
//        }
//	}
//
//	public interface PostExceptionInter {
//		void postException(Throwable ex, String deviceInfo);
//	}
//
//	private PostExceptionInter postExceptionInter;
//
//	/**
//	 * 收集设备参数信息
//	 *
//	 * @param ctx
//	 */
//	public String collectDeviceInfo(Context ctx) {
//		try {
//			PackageManager pm = ctx.getPackageManager();
//			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
//					PackageManager.GET_ACTIVITIES);
//			if (pi != null) {
//				String versionName = pi.versionName == null ? "null"
//						: pi.versionName;
//				String versionCode = pi.versionCode + "";
//				sb.append("设备信息:\n");
//				sb.append("App Version: " + versionName + "_" + versionCode
//						+ "\n");
//				// android版本号
//				sb.append("OS Version: " + Build.VERSION.RELEASE + "_"
//						+ Build.VERSION.SDK_INT + "\n");
//				// 手机制造商
//				sb.append("Vendor: " + Build.MANUFACTURER + "\n");
//				// 手机型号
//				sb.append("Model: " + Build.MODEL + "\n");
//				sb.append("=============================================\n");
//			}
//
//		} catch (NameNotFoundException e) {
//			LogUtil.e(
//					TAG,
//					"an error occured when collect package info"
//							+ e.getMessage());
//		}
//		Field[] fields = Build.class.getDeclaredFields();
//		for (Field field : fields) {
//			try {
//				field.setAccessible(true);
//				sb.append(field.getName() + ": " + field.get(null).toString()
//						+ "\n");
//				LogUtil.d(TAG, field.getName() + " : " + field.get(null));
//			} catch (Exception e) {
//				LogUtil.e(
//						TAG,
//						"an error occured when collect crash info"
//								+ e.getMessage());
//			}
//
//		}
//		sb.append("=============================================\n");
//		return sb.toString();
//	}
//
//}
