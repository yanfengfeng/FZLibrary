package com.fengzi.ui.permissions;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fengzi.test.R;

import fengzi.library.base.activity.FBaseActivity;
import fengzi.library.permissions.PermissionsTool;

/**
 * 权限
 */
public class PermissionsAc extends FBaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_permissions);
        setTitle("权限");
        initView();
    }

    void initView(){
        findViewById(R.id.btn_andPermission).setOnClickListener(this);
        findViewById(R.id.btn_permissionsDispatcher).setOnClickListener(this);
        findViewById(R.id.btn_myslef).setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_andPermission:
               gotoActivity(AndPermissionAc.class);
                break;
            case R.id.btn_permissionsDispatcher:
                gotoActivity(PermissionsDispatcherAc.class);
                break;
            case R.id.btn_myslef:
                gotoActivity(PermissionsMyselftAc.class);
                break;

        }
    }
}
