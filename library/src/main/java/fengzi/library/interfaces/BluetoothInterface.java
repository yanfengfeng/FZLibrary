package fengzi.library.interfaces;

import android.bluetooth.BluetoothDevice;

/**
 * 作者: {yff} time: 2017/3/31  11:02
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{蓝牙的相关回调}
 */

public interface BluetoothInterface {

    /**
     * @param isOpen true 打开  否则么有打开
     */
    public void isOpen(boolean isOpen);

    /**
     * 蓝牙扫描完成
     */
    public void fundOver();

    /**
     * @param device 蓝牙发现的设备
     */
    public void fundBluetooth(BluetoothDevice device);

}
