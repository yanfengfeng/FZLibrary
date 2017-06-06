package fengzi.library.tool;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @类功能说明:(这里用一句话描述这个类的作用)
 * @author yff
 * @date 2016年10月17日 下午1:54:24
 * @version V2.0
 */
public class AppUtil {

	/**
	 * 描述：打开并安装文件.
	 * 
	 * @param context
	 *            the context
	 * @param file
	 *            apk文件路径
	 */
	public static void installApk(Context context, File file) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * 描述：卸载程序.
	 * 
	 * @param context
	 *            the context
	 * @param packageName
	 *            包名
	 */
	public static void uninstallApk(Context context, String packageName) {
		Intent intent = new Intent(Intent.ACTION_DELETE);
		Uri packageURI = Uri.parse("package:" + packageName);
		intent.setData(packageURI);
		context.startActivity(intent);
	}

	/**
	 * 用来判断服务是否运行.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param className
	 *            判断的服务名字 "com.xxx.xx..XXXService"
	 * @return true 在运行 false 不在运行
	 */
	public static boolean isServiceRunning(Context ctx, String className) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) ctx
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> servicesList = activityManager
				.getRunningServices(Integer.MAX_VALUE);
		Iterator<RunningServiceInfo> l = servicesList.iterator();
		while (l.hasNext()) {
			RunningServiceInfo si = l.next();
			if (className.equals(si.service.getClassName())) {
				isRunning = true;
			}
		}
		return isRunning;
	}

	/**
	 * 判断当前设备是手机还是平板
	 * @param context
	 * @return 平板返回 True，手机返回 False
	 */
	public static boolean isPad(Context context) {
		return (context.getResources().getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK)
				>= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	/**
	 * 停止服务.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param className
	 *            the class name
	 * @return true, if successful
	 */
	public static boolean stopRunningService(Context ctx, String className) {
		Intent intent_service = null;
		boolean ret = false;
		try {
			intent_service = new Intent(ctx, Class.forName(className));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (intent_service != null) {
			ret = ctx.stopService(intent_service);
		}
		return ret;
	}

	/**
	 * Gps是否打开 需要
	 *
	 * @param context
	 *            the context
	 * @return true, if is gps enabled
	 */
	public static boolean isGpsEnabled(Context context) {
		LocationManager lm = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}


	/**
	 * 是否为调试模式.
	 */
	@SuppressLint("NewApi")
	public static boolean isApkDebugable(Context context) {
		try {
			ApplicationInfo info = context.getApplicationInfo();
			return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
		} catch (Exception e) {

		}
		return false;
	}


	/**
	 * 是否在前台运行
	 * @param context
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	public static boolean isRunningForeground(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		String currentPackageName = cn.getPackageName();
		if (!TextUtils.isEmpty(currentPackageName)
				&& currentPackageName.equals(context.getPackageName())) {
			return true;
		}

		return false;
	}

	/**
	 * 获取手机内部剩余存储空间
	 * @return
	 */
	public static long getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}

	/**
	 * 获取手机内部总的存储空间
	 * @return
	 */
	public static long getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return totalBlocks * blockSize;
	}

	/**
	 * 获取SDCARD剩余存储空间
	 * @return
	 */
	public static long getAvailableExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		} else {
			return -1;
		}
	}

	/**
	 * 获取SDCARD总的存储空间
	 * 
	 * @return
	 */
	public static long getTotalExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return totalBlocks * blockSize;
		} else {
			return -1;
		}
	}

	/**
	 * SDCARD是否存
	 */
	public static boolean externalMemoryAvailable() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}
	
	/**
	 * 获取签名
	 * @param context  
	 * @return
	 */
	public static String getSign(Context context) {
		PackageManager pm = context.getPackageManager();
		List<PackageInfo> apps = pm
				.getInstalledPackages(PackageManager.GET_SIGNATURES);
		Iterator<PackageInfo> iter = apps.iterator();
		while (iter.hasNext()) {
			PackageInfo packageinfo = iter.next();
			String packageName = packageinfo.packageName;
			if (packageName.equals(context.getPackageName())) {
				return packageinfo.signatures[0].toCharsString();
			}
		}
		return "";
	}

	/**
	 * 根据文件名获取资源id
	 *
	 * @param variableName
	 * @param context
	 * @param c
	 * @return
	 */
	public static int getResIdByFieldName(String variableName, Context context,
										  Class<?> c) {
		try {
			Field idField = c.getDeclaredField(variableName);
			return idField.getInt(idField);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static int getLayoutId(Context paramContext, String paramString) {

		return getResId(paramContext, paramString, "layout");
	}

	public static int getStringId(Context paramContext, String paramString) {

		return getResId(paramContext, paramString, "string");
	}

	public static int getDrawableId(Context paramContext, String paramString) {

		return getResId(paramContext, paramString, "drawable");
	}

	public static int getStyleId(Context paramContext, String paramString) {

		return getResId(paramContext, paramString, "style");
	}

	public static int getId(Context paramContext, String paramString) {

		return getResId(paramContext, paramString, "id");
	}

	public static int getColorId(Context paramContext, String paramString) {

		return getResId(paramContext, paramString, "color");
	}

	public static int getArrayId(Context paramContext, String paramString) {

		return getResId(paramContext, paramString, "array");
	}

	public static int getMipmapId(Context paramContext, String paramString) {

		return getResId(paramContext, paramString, "mipmap");
	}

	private static int getResId(Context paramContext, String paramString,
								String type) {

		return paramContext.getResources().getIdentifier(paramString, type,
				paramContext.getPackageName());
	}

	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}
}
