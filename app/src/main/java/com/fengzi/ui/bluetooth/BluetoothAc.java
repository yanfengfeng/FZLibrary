package com.fengzi.ui.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.fengzi.adapter.MyBluetoothAdapter;
import com.fengzi.bean.BluetoothBean;
import com.fengzi.test.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fengzi.library.base.activity.FBaseActivity;
import fengzi.library.intent.IntentUtil;
import fengzi.library.interfaces.BluetoothInterface;
import fengzi.library.nfc_or_bluetooth.BluetoothTool;

/**
 * 蓝牙
 */
public class BluetoothAc extends FBaseActivity implements MyBluetoothAdapter.OnItemCickLisetern, BluetoothInterface {

    TextView textView;

    BluetoothTool bluetAdapter;

    SwipeRefreshLayout swpl;

    RecyclerView rcv;

    MyBluetoothAdapter adapter;

    List<BluetoothBean> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_bluetooth);
        initView();
        initBluet();
        getNearBluet();
    }
    void initView(){
        textView = (TextView) findViewById(R.id.tv_content);
        swpl = (SwipeRefreshLayout) findViewById(R.id.wrlt);
        rcv = (RecyclerView) findViewById(R.id.rcv);
        adapter = new MyBluetoothAdapter(this,listData);
        rcv.setAdapter(adapter);
        rcv.setLayoutManager(new LinearLayoutManager(this));

//        swpl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh(int index) {
//                listData.clear();
//                bluetAdapter.startScanning();
//            }
//
//            @Override
//            public void onLoad(int index) {
//
//            }
//        });
        swpl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listData.clear();
                bluetAdapter.startScanning();
            }
        });
    }

    void initBluet(){
         bluetAdapter = BluetoothTool.getIntance(this);
        if(!bluetAdapter.isDeviceSupport()){
            toast("该设备不支持蓝牙功能");
            finish();
            return;
        }
        bluetAdapter.setListerion(this);
        if(!bluetAdapter.isBluetoothOpen()){
            toast("请打开蓝牙功能才可以使用");
            IntentUtil.openBluetoothPermission(this,11);
        }

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
             log("=======设备部支持低功耗蓝牙==========");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 11);
            }
        }


    }

    String blueInfo;
    void getNearBluet(){
        blueInfo = bluetAdapter.getBlueInfo();

        textView.setText(blueInfo);
        Set<BluetoothDevice> bondedDevices = bluetAdapter.getBluetooth().getBondedDevices();
        if(bondedDevices != null){
            for (BluetoothDevice device : bondedDevices) {
                textView.append("\n" + device.getName() + "\t\t\t" + device.getAddress());
            }
        }

//        IntentFilter filter = new IntentFilter();
//        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//        filter.addAction(BluetoothDevice.ACTION_FOUND);
//        registerReceiver(mReceiver, filter);


//        Intent discoverableIntent = new
//                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 60);
//        startActivity(discoverableIntent);

        bluetAdapter.startScanning();
    }

    BluetoothAdapter.LeScanCallback callback = new BluetoothAdapter.LeScanCallback(){

        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

            log("====LeScanCallback======" + device.getName() + "====" + device.getAddress());
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 11:
                log("=======onRequestPermissionsResult=======" + grantResults[0]);
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
                break;
        }
    }


    public void onClickListern(int postion, BluetoothBean bean){
        toast("click:" + bean.name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bluetAdapter != null) {
            bluetAdapter.stopScanning();
        }
    }

    @Override
    public void isOpen(boolean isOpen) {
        bluetAdapter.startScanning();
    }

    @Override
    public void fundOver() {
        swpl.setRefreshing(false);
    }

    @Override
    public void fundBluetooth(BluetoothDevice device) {
        listData.add(new BluetoothBean(device.getName(),device.getAddress()));
        adapter.notifyDataSetChanged();
    }
}
