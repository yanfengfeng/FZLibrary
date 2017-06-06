package fengzi.library.nfc_or_bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.io.IOException;
import java.security.PublicKey;

import fengzi.library.tool.LogUtil;

/**
 * 作者: {yff} time: 2017/4/5  17:01
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{蓝牙连接}
 */

public class BluetoothService {

    String TAG = this.getClass().getSimpleName();

    BluetoothAdapter bleAdapter;

    public BluetoothService(BluetoothAdapter bleAdapter) {
        this.bleAdapter = bleAdapter;
    }

    public void connect(){

    }

    final  class Mythead extends  Thread{

        String mac;

        public Mythead(String mac) {
            this.mac = mac;
        }

        @Override
        public void run() {
            super.run();
            if(bleAdapter == null){
                bleAdapter = BluetoothAdapter.getDefaultAdapter();
            }
//            BluetoothDevice mBluetoothDevice = bleAdapter.getRemoteDevice(mac);
//            bleAdapter.cancelDiscovery();
////            try {
//                socket = mBluetoothDevice.createRfcommSocketToServiceRecord(uuid);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                //e.printStackTrace();
//                LogUtil.e(TAG, "==Mythead 异常=====" +  e.getMessage());
//            }
        }
    }

}
