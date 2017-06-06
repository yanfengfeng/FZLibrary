package fengzi.library.interfaces;

import android.app.Activity;

/**
 * 作者: {yff} time: 2017/3/22  18:16
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{}
 */

public interface BaseUtilInterface {

    /**
     * 日志打印
     * @param msg
     */
      void log(String msg);

    /**
     * 弹出框提示
     * @param msg
     */
      void toast(String msg);


    /**
     * 获取当前手机的宽和高
     */
    int getScreemHeight(boolean isHeight);

    /**
     * 判断对象是否为空
     */
    boolean isEmpty(Object o);


}
