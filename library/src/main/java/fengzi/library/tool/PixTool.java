package fengzi.library.tool;

import android.app.Activity;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * 作者: {yff} time: 2017/4/19  10:11
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{设备像素的转换}
 */

public class PixTool {

//    public static float dp2px(Activity activity,int dp){
//        Resources r = activity.getResources();
//        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
//        return   px;
//    }

    public static int getPhoneW(Activity activity){
        return activity.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getPhoneH(Activity activity){
        return activity.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dp2px(Activity activity,int dp){
        Resources r = activity.getResources();
        return (int) (dp * r.getDisplayMetrics().scaledDensity + 0.5);
    }

    public static int px2dp(Activity activity,int px){
        Resources r = activity.getResources();
        return (int) (px / r.getDisplayMetrics().scaledDensity + 0.5);
    }
}
