package com.fengzi;

import android.os.Build;

/**
 * 作者: {yff} time: 2017/3/29  11:56
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{}
 */

public class Text {
    public static void main(String[] args){

//        int[] fun ={0,1,2,3,4,5,6};
        String fun[] = new String[]{"1","2","3","4","5","6","7"};
        String fun1[] = new String[fun.length - 3];
        System.arraycopy(fun,3,fun1,0,fun.length - 3);
//        for (String ss : fun1)
//       String ss =  String.format("%s (联系方式 %s,系统信息:%s,%s, %d , %d, %s, %s)","12345", Build.MODEL,Build.VERSION.RELEASE,
//                "720","1080",Build.VERSION.SDK_INT + "" ,Build.DISPLAY,Build.VERSION.INCREMENTAL);
       String ss =  String.format("联系方式:%s,反馈内容(:%s,手机型号:%s,手机的宽高:%s * %s,App版本号:%s,手机当前版本%s)",
               "158123456","这个APP很好",Build.MODEL,720,"1080",Build.VERSION.SDK_INT,Build.VERSION.INCREMENTAL);
        System.out.println("===========" + ss);

    }
}
