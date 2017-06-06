package fengzi.library.permissions;


import android.Manifest;

import java.util.HashMap;

/**
 * 作者: {yff} time: 2017/4/18  18:47
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{权限的常量
 * 同一组的任何一个权限被授权了，其他权限也自动被授权
 * }
 */

 class PermissionsContanst {

    /**
     * 存储(android.permission-group.STORAGE)
     */
    public static final  int WRITE_EXTERNAL_STORAGE = 0x01;
    public static final  int READ_EXTERNAL_STORAGE = 0x02;
    /**
     * 拍照权限(android.permission-group.CAMERA)
     */
    public static final  int CAMERA = 0x10;
    /**
     * 联系人(android.permission-group.CONTACTS)
     */
    public static final  int READ_CONTACTS = 0x20;
    public static final  int WRITE_CONTACTS = 0x21;
    public static final  int GET_ACCOUNTS = 0x22;
    /**
     * 手机(android.permission-group.PHONE)
     */
    public static final  int READ_CALL_LOG = 0x30;
    public static final  int READ_PHONE_STATE = 0x31;
    public static final  int CALL_PHONE = 0x32;
    public static final  int WRITE_CALL_LOG = 0x33;
    public static final  int USE_SIP = 0x34;
    public static final  int PROCESS_OUTGOING_CALLS = 0x35;
    public static final  int ADD_VOICEMAIL = 0x36;
    /**
     * 日历(android.permission-group.CALENDAR)
     */
    public static final  int READ_CALENDAR = 0x40;
    public static final  int WRITE_CALENDAR = 0x41;
    /**
     * 传感器(android.permission-group.SENSORS)
     */
    public static final  int BODY_SENSORS = 0x50;
    /**
     * 位置(android.permission-group.LOCATION)
     */
    public static final  int ACCESS_FINE_LOCATION = 0x60;
    public static final  int ACCESS_COARSE_LOCATION = 0x61;
    /**
     * 麦克风(android.permission-group.MICROPHONE)
     */
    public static final  int MICROPHONE = 0x70;
    /**
     * 短信(android.permission-group.SMS)
     */
    public static final  int READ_SMS = 0x80;
    public static final  int RECEIVE_WAP_PUSH = 0x81;
    public static final  int RECEIVE_MMS = 0x82;
    public static final  int RECEIVE_SMS = 0x83;
    public static final  int SEND_SMS = 0x84;
//    public static final  int READ_CELL_BROADCASTS = 0x85;


     final static HashMap<Integer,String> permiss = new HashMap<>();

    public static String getPermiss(int type){

        return  permiss.get(type);
    }

    static {

        permiss.put(WRITE_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permiss.put(READ_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE);

        permiss.put(CAMERA,Manifest.permission.CAMERA);

        permiss.put(READ_CONTACTS,Manifest.permission.READ_CONTACTS);
        permiss.put(WRITE_CONTACTS,Manifest.permission.WRITE_CONTACTS);
        permiss.put(GET_ACCOUNTS,Manifest.permission.GET_ACCOUNTS);

        permiss.put(MICROPHONE,Manifest.permission.RECORD_AUDIO);

        permiss.put(READ_SMS,Manifest.permission.READ_SMS);
        permiss.put(RECEIVE_WAP_PUSH,Manifest.permission.RECEIVE_WAP_PUSH);
        permiss.put(RECEIVE_MMS,Manifest.permission.RECEIVE_MMS);
        permiss.put(RECEIVE_SMS,Manifest.permission.RECEIVE_SMS);
        permiss.put(SEND_SMS,Manifest.permission.SEND_SMS);
//        permiss.put(READ_CELL_BROADCASTS,Manifest.permission.READ_CELL_BROADCASTS);

        permiss.put(ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION);
        permiss.put(ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION);

        permiss.put(BODY_SENSORS,Manifest.permission.BODY_SENSORS);

        permiss.put(READ_CALENDAR,Manifest.permission.READ_CALENDAR);
        permiss.put(WRITE_CALENDAR,Manifest.permission.WRITE_CALENDAR);

        permiss.put(READ_CALL_LOG,Manifest.permission.READ_CALL_LOG);
        permiss.put(READ_PHONE_STATE,Manifest.permission.READ_PHONE_STATE);
        permiss.put(CALL_PHONE,Manifest.permission.CALL_PHONE);
        permiss.put(WRITE_CALL_LOG,Manifest.permission.WRITE_CALL_LOG);
        permiss.put(USE_SIP,Manifest.permission.USE_SIP);
        permiss.put(PROCESS_OUTGOING_CALLS,Manifest.permission.PROCESS_OUTGOING_CALLS);
        permiss.put(ADD_VOICEMAIL,Manifest.permission.ADD_VOICEMAIL);

    }


}
