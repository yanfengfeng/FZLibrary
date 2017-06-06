package com.fengzi.ui.permissions;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fengzi.test.R;

import fengzi.library.base.activity.PermissionsActivity;
import fengzi.library.permissions.PermissionsTool;


public class PermissionsMyselftAc extends PermissionsActivity implements View.OnClickListener {

    TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions_myselft);
        setTitle("自己写的权限");
        initView();
    }

    void initView() {
        findViewById(R.id.btn_go_system_set).setOnClickListener(this);
        findViewById(R.id.btn_send_sms).setOnClickListener(this);
        findViewById(R.id.btn_call_tel).setOnClickListener(this);
        findViewById(R.id.btn_photo).setOnClickListener(this);
        findViewById(R.id.btn_camer).setOnClickListener(this);

        tv_content = (TextView) findViewById(R.id.tv_content);
    }

    @Override
    public void requestPermissionsSuccess(String permissions) {
        tv_content.append("这个==>" + permissions + "<==权限申请成功\n");
    }

    @Override
    public void requestPermissionsFail(String permissions, boolean isHavaPopu) {
        tv_content.append("这个==>" + permissions + "<==权限被拒绝:" + (isHavaPopu ? "弹窗" : "不弹窗") + "\n");
    }

    @Override
    public void requestPermissionsOver() {
        tv_content.append("权限申请执行完成\n");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_system_set:
                PermissionsTool.goSystemPermissionsSet(this);
                break;
            case R.id.btn_call_tel:
                tv_content.setText(" ");
                if (PermissionsTool.PhoteTel.isHasTelPermiss(this)) {
                    tv_content.append("已经拥有相册权限\n");
                } else {
                    tv_content.append("正在申请相册权限\n");
                    PermissionsTool.PhoteTel.requstTelPermiss(this);
                }
                break;
            case R.id.btn_send_sms:
                tv_content.setText(" ");
                if (PermissionsTool.PhotoSms.isHasSmsPermiss(this)) {
                    tv_content.append("已经拥有相册权限\n");
                } else {
                    tv_content.append("正在申请相册权限\n");
                    PermissionsTool.PhotoSms.requstSmsPermiss(this);
                }
                break;
            case R.id.btn_photo:
                tv_content.setText(" ");
                if (PermissionsTool.CamerAndPhoto.isHasPhotoPermiss(this)) {
                    tv_content.append("已经拥有相册权限\n");
                } else {
                    tv_content.append("正在申请相册权限\n");
                    PermissionsTool.CamerAndPhoto.requstPhotoPermiss(this);
                }
                break;
            case R.id.btn_camer:
                tv_content.setText(" ");
                if (PermissionsTool.CamerAndPhoto.isHasCameraPermiss(this)) {
                    tv_content.append("已经拥有拍照权限\n");
                } else {
                    tv_content.append("正在申请拍照权限\n");
                    PermissionsTool.CamerAndPhoto.requstCamerPermiss(this);
                }
                break;
        }
    }
}
