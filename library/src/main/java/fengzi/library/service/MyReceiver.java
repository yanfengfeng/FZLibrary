package fengzi.library.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import fengzi.library.tool.LogUtil;

/**
 * 监听网络连接状态
 * Created by Administrator on 2016/12/5.
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() == Intent.ACTION_BOOT_COMPLETED){//开机启动

        }else  if(intent.getAction().equals(WifiManager.RSSI_CHANGED_ACTION)){

        } else if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){//wifi连接上与否

        }
    }

    /**
     * 网络状态判断
     */
    void networkStatues(Context context,Intent intent){
        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if(info.getState().equals(NetworkInfo.State.DISCONNECTED)){
           LogUtil.e("===网络连接断开");
        } else if(intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION) ||
                intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION) ){//wifi打开与否
            int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_DISABLED);
            if(wifistate == WifiManager.WIFI_STATE_DISABLED){
                LogUtil.e("系统关闭wifi");
            } else if(wifistate == WifiManager.WIFI_STATE_ENABLED){
                LogUtil.e("系统开启wifi");
                if(info.getState().equals(NetworkInfo.State.CONNECTED)){
                    WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                    if(TextUtils.isEmpty(wifiInfo.getSSID())){
                     LogUtil.e("网络连接断开 ");
                    }else{
                     LogUtil.e("连接到网络 " + wifiInfo.getSSID());
                    }
                }
            }
        }else if(info.getState().equals(NetworkInfo.State.CONNECTING)){
                 LogUtil.e("正在连接网络... ");
        }
    }

    //网络监听
    void  netWork(Intent intent,Context context){

        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo  mobNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo  wifiNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mobNetInfo.getState() == NetworkInfo.State.CONNECTED && wifiNetInfo.getState() != NetworkInfo.State.CONNECTED) {//手机网络连接成功

        }else if(wifiNetInfo.getState() == NetworkInfo.State.CONNECTED){//wifi

        }else{
        }
    }

}
