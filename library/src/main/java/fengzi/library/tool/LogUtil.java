package fengzi.library.tool;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.kysoft.library.BuildConfig;

/**
 * 日志答应信息
 * 2015-6-7
 * @author yff
 *
 */
public class LogUtil {

	private static boolean is_log = BuildConfig.DEBUG;

	final  static String TAG = LogUtil.class.getSimpleName();
	
	private static Context context;

	/**
	 * log.i();正常颜色
	 */
	public static void i(String msg) {
		i(TAG, msg);
	}

	/**
	 * log.i();正常颜色
	 */
	public static void i(String tag, String msg) {
		show(LogType.i,tag, msg);
	}

	/**
	 * log.e();红色
	 */
	public static void e(String msg) {
		e(TAG, msg);
	}

	/**
	 * log.e();红色
	 */
	public static void e(String tag, String msg) {
		show(LogType.e,tag, msg);
	}

	/**
	 * log.v();黑色
	 */
	public static void v(String msg) {
		v(TAG, msg);
	}

	/**
	 * log.v();黑色
	 */
	public static void v(String tag, String msg) {
		show(LogType.v,tag, msg);
	}

	/**
	 * log.d();蓝色
	 */
	public static void d(String tag, String msg) {
		show(LogType.d,TAG, msg);
	}

	/**
	 * log.d();蓝色
	 */
	public static void d(String msg) {
		v(TAG, msg);
	}

	/**
	 * log.w();橙色
	 */
	public static void w(String tag, String msg) {
		show(LogType.w,tag, msg);
	}

	/**
	 * log.w();橙色
	 */
	public static void w(String msg) {
		w(TAG, msg);
	}

	private static void show(LogType type,String tag, String msg) {
		if (is_log){
			if(type == LogType.e){
				Log.e(tagIsNull(tag), printInfo(msg));
			}else if(type == LogType.i){
				Log.i(tagIsNull(tag), printInfo(msg));
			}else if(type == LogType.w){
				Log.w(tagIsNull(tag), printInfo(msg));
			}else if(type == LogType.v){
				Log.v(tagIsNull(tag), printInfo(msg));
			}else if(type == LogType.wtf) {
				Log.wtf(tagIsNull(tag), printInfo(msg));
			}else if(type == LogType.d) {
				Log.d(tagIsNull(tag), printInfo(msg));
			}else{
				System.out.println(printInfo(msg));
			}
		}else{
			Log.e("","================" + is_log);
		}
	}

	private static String tagIsNull(String tag){

		return TextUtils.isEmpty(tag) ? TAG : tag;
	}
	
	private static String printInfo(String msg){
		if(TextUtils.isEmpty(msg)){
			msg = "打印么有任何信息=======content is null=========";
		}
		return msg;
	}

	enum LogType{
		d,e,i,w,v,wtf;
	}
}
