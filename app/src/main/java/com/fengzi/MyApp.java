package com.fengzi;

import android.app.Application;

import com.fengzi.exception.CrashHandler;
import com.fengzi.exception.PostExceptionToEmailImpl;
import com.pgyersdk.crash.PgyCrashManager;


/**
 * 作者: {yff} time: 2017/5/21  10:29
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{}
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(getApplicationContext(),new PostExceptionToEmailImpl());
        PgyCrashManager.register(getApplicationContext());
    }
}
