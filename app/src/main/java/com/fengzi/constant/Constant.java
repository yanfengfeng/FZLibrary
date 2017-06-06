package com.fengzi.constant;

import java.util.HashMap;

/**
 * 作者: {yff} time: 2017/3/27  09:55
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{}
 */

public class Constant {


    public static HashMap<String,String> hashmap = new HashMap<>();

    static {
        addHasMap("nfc", "NfcAc");
        addHasMap("蓝牙","bluetooth");
    }



    static void addHasMap(String key,String value){
        hashmap.put(key,value);
    }



}
