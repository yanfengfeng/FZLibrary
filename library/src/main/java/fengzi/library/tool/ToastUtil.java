package fengzi.library.tool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast
 */
public class ToastUtil {
	
	private static Activity context;
	
	static Toast toast;

	public static void show(Activity contexts, String message) {
		 context = contexts;
		toastShow( message);
	}

	public static void show(String message) {
		if(context == null){
			context = ActUtil.getInstance().getTopActivity();
		}
		toastShow(message);
	}

	@SuppressLint("ShowToast")
	private static void toastShow(String toastString) {
		if(context==null){
			return;
		}
		if(TextUtils.isEmpty(toastString)){
			toastString = "";
		}
		if(toast == null){
			toast = Toast.makeText(context, toastString, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER,0 ,0);
		}else{
			toast.setText(toastString);
		}
		toast.show();

	}

}
