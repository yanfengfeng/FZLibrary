package fengzi.library.tool;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;

import java.util.Stack;

/**
 * 
 * @类功能说明:(全局的acitivty的管理) 
 * @author yff
 * @date 2016年10月17日 下午2:31:35 
 * @version V2.0
 */
public class ActUtil {
	
	private static Stack<FragmentActivity> mActivityStack;
	
	private static ActUtil mActUtil;

	/**
	 * 获取acitivy的集合
	 * @return
	 */
	public static int getAcList() {
 
		return mActivityStack == null ? 0 : mActivityStack.size();
	}

	/**
	 * 单一实例
	 */
	public static ActUtil getInstance() {
		if (mActUtil == null) {
			mActUtil = new ActUtil();
		}
		return mActUtil;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(FragmentActivity activity) {
		if (mActivityStack == null) {
			mActivityStack = new Stack<FragmentActivity>();
		}
		mActivityStack.add(activity);
	}

	/**
	 * 获取栈顶Activity（堆栈中最后一个压入的）
	 */
	public FragmentActivity getTopActivity() {
		FragmentActivity activity = mActivityStack.lastElement();
		return activity;
	}

	/**
	 * 结束栈顶Activity（堆栈中最后一个压入的）
	 */
	public void killTopActivity() {
		FragmentActivity activity = mActivityStack.lastElement();
		killActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void killActivity(FragmentActivity activity) {
		if (activity != null) {
			mActivityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void killActivity(Class<?> cls) {
		for (FragmentActivity activity : mActivityStack) {
			if (activity.getClass().equals(cls)) {
				killActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void killAllActivity() {
		for (int i = 0, size = mActivityStack.size(); i < size; i++) {
			if (null != mActivityStack.get(i)) {
				mActivityStack.get(i).finish();
			}
		}
		mActivityStack.clear();
	}
	
	/**
	 * 退出应用
	 * @param context
	 */
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public void AppExit(Context context) {
		try {
			killAllActivity();
			ActivityManager activityMgr = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.killBackgroundProcesses(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
		}
	}

 

	public static DisplayMetrics getDisplayMetrics(Context context) {
		Resources mResources;
		if (context == null) {
			mResources = Resources.getSystem();
		} else {
			mResources = context.getResources();
		}
		// DisplayMetrics{density=1.5, width=480, height=854, scaledDensity=1.5,
		// xdpi=160.421, ydpi=159.497}
		// DisplayMetrics{density=2.0, width=720, height=1280,
		// scaledDensity=2.0, xdpi=160.42105, ydpi=160.15764}
		DisplayMetrics mDisplayMetrics = mResources.getDisplayMetrics();
		
		return mDisplayMetrics;
	}

}
