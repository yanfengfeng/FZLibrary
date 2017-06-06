package fengzi.library.nfc_or_bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.ParcelUuid;
import android.support.annotation.RequiresApi;

import fengzi.library.intent.IntentUtil;
import fengzi.library.interfaces.BluetoothInterface;
import fengzi.library.tool.LogUtil;

/**
 * 作者: {yff} time: 2017/3/31  09:34
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{蓝牙使用工具}
 */

public class BluetoothTool {

    public static final int openBluetoothCode = 0x19;

    static BluetoothTool tool;

    BluetoothAdapter bluetAdapter;

    Activity activity;

    BluetoothInterface bluetoothInterface;

    BluetoothService service;

    public static BluetoothTool getIntance(Activity activity) {
        if (tool == null) {
            tool = new BluetoothTool(activity);
        }
        return tool;
    }


    BluetoothTool(Activity activity) {
        initBluetooth(activity);
    }

    void initBluetooth(Activity activity) {
        this.activity = activity;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            BluetoothManager bluetoothManager =
                    (BluetoothManager) activity.getSystemService(Context.BLUETOOTH_SERVICE);
            bluetAdapter = bluetoothManager.getAdapter();
        } else {
            bluetAdapter = BluetoothAdapter.getDefaultAdapter();
        }
    }

    boolean isNoNull(){

        return bluetAdapter != null;
    }

    boolean activityNoNull(){

        return activity != null;
    }

    /**
     * @return true 设备支持蓝牙功能  否则不支持
     */
    public boolean isDeviceSupport() {

        return isNoNull();
    }

    /**
     * @return true 蓝牙已经打开, 否则么有打开,么有打开调用 applyOpenBluetooth申请打开
     */
    public boolean isBluetoothOpen() {
        if (isNoNull()) {
//            return  bluetAdapter.getState() == BluetoothAdapter.STATE_ON;
            return bluetAdapter.isEnabled();
        }
        return false;
    }

    /**
     * 获取本机蓝牙信息
     */
    public BluetoothAdapter getBluetooth() {
        return bluetAdapter;
    }

    /**
     * 申请打开蓝牙
     */
    public void applyOpenBluetooth() {
        IntentUtil.openBluetoothPermission(activity, openBluetoothCode);
    }

    public  int getStatue(){
        return  bluetAdapter == null ? -1 : bluetAdapter.getState();
    }

    /**
     * 开始扫描蓝牙
     */
    public  void startScanning(){
        if(isNoNull()){
            registerBroadcast();
            bluetAdapter.startDiscovery();
//            bluetAdapter.startLeScan(null);
        }
    }
    public  void stopScanning(){
        if(isNoNull()){
            bluetAdapter.cancelDiscovery();
//            bluetAdapter.startLeScan(null);
        }
    }

    void registerBroadcast(){
        if(activityNoNull()){
            IntentFilter filter = new IntentFilter();
            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            filter.addAction(BluetoothDevice.ACTION_FOUND);
            activity.registerReceiver(mReceiver, filter);
        }
    }

    public void setListerion(BluetoothInterface bluetoothInterface){
        this.bluetoothInterface = bluetoothInterface;
    }


    //resultCode 1 打开   0 未打开
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.d("======onActivityResult=====" + requestCode + "=====" + resultCode);
        if (requestCode == openBluetoothCode) {
            if (bluetoothInterface != null) {
                bluetoothInterface.isOpen(resultCode == 1);
            }
        }
    }



     final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.d("===mReceiver=======" + action);
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                ParcelUuid[] uuids = device.getUuids();
                if(uuids != null && uuids.length > 0){
                    for (ParcelUuid s : uuids){
                        LogUtil.d("===getUuid=======" + s.getUuid());
                        LogUtil.d("====describeContents======" + s.describeContents());
                        LogUtil.d("====toString======" + s.toString());
                    }
                }
                if(bluetoothInterface != null && device != null){
                    bluetoothInterface.fundBluetooth(device);
                }
//                textView.append("\n搜索到的设备:" + device.getName() + "==" + device.getAddress());
                LogUtil.d("====mReceiver======" + device.getName() + "====" + device.getAddress());
            }else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                    if(bluetoothInterface != null){
                        bluetoothInterface.fundOver();
                    }
            }
        }
    };

    /**
     * 蓝牙连接
     */
    public  void connect(){
        if(service == null){
            service = new BluetoothService(bluetAdapter);
        }
        service.connect();

    }

    public String getBlueInfo(){

        return  bluetAdapter == null ? "" : "本机蓝牙信息\n名称:" + bluetAdapter.getName() +
                "\n地址" + bluetAdapter.getAddress()  +
                "\nMode:" + bluetAdapter.getScanMode() +
                "\nstate:" + bluetAdapter.getState();
    }

    //销毁蓝牙相关功能
    public void onDestroy() {
        if (isNoNull()) {
            if (bluetAdapter.isDiscovering()) {
                bluetAdapter.cancelDiscovery();
                //            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//                bluetAdapter.stopLeScan(null);
//            }
            }
            try {
                
                if(activityNoNull()){
                    activity.unregisterReceiver(mReceiver);
                }
            } catch (Exception e) {
            }
        }
    }
}
