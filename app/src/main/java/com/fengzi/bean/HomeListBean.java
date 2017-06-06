package com.fengzi.bean;

import android.support.v7.widget.RecyclerView;

import com.fengzi.Text;
import com.fengzi.ui.bluetooth.BluetoothAc;
import com.fengzi.ui.designMode.DesignModeAc;
import com.fengzi.ui.http.HttpAc;
import com.fengzi.ui.kotlin.KotlinAc;
import com.fengzi.ui.nfc.NfcAc;
import com.fengzi.ui.note.NoteAc;
import com.fengzi.ui.permissions.PermissionsAc;
import com.fengzi.ui.recyclerView.RecyclerViewAc;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: {yff} time: 2017/3/30  10:46
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{}
 */

public class HomeListBean  {

    public  String key;
    public Class<?> content;

    public static  List<HomeListBean> ITEMS = new ArrayList<HomeListBean>();

    static{
        ITEMS.add(new HomeListBean("nfc", NfcAc.class));
        ITEMS.add(new HomeListBean("蓝牙",BluetoothAc.class));
        ITEMS.add(new HomeListBean("权限",PermissionsAc.class));
        ITEMS.add(new HomeListBean("注解",NoteAc.class));
        ITEMS.add(new HomeListBean("RecyclerView的使用",RecyclerViewAc.class));
        ITEMS.add(new HomeListBean("网络请求",HttpAc.class));
        ITEMS.add(new HomeListBean("设计模式",DesignModeAc.class));
        ITEMS.add(new HomeListBean("Kotlin",KotlinAc.class));
//        ITEMS.add(new HomeListBean("注解", Text.class));
    }

    public HomeListBean(String key, Class<?> content) {
        this.key = key;
        this.content = content;
    }

}
