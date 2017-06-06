package com.fengzi.ui.permissions;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.fengzi.test.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.util.List;

import fengzi.library.base.activity.FBaseActivity;
import fengzi.library.permissions.PermissionsTool;

/**
 * AndPermissionAc 第三方权限
 * 备注{
 *     在app的buildgradle文件中添加依赖：
 dependencies {
 ...
 //运行时权限PermissionGen依赖库
 compile 'com.yanzhenjie:permission:1.0.4'
 }
 * }
 */
public class AndPermissionAc extends FBaseActivity implements View.OnClickListener {

    TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_and_permission);
        setTitle("AndPermission");
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_system_set:
                toast("暂时么找到跳到这个系统设置页面的方法");
                PermissionsTool.goSystemPermissionsSet(this);
                break;
            case R.id.btn_call_tel:
                tv_content.setText(" ");
                if (AndPermission.hasPermission(this, Manifest.permission.CALL_PHONE)) {
                    tv_content.append("已经拥有拨打电话权限\n");
                } else {
                    tv_content.append("正在申请拨打电话权限\n");
                    AndPermission.with(this)
                            .requestCode(100)
                            .permission(Manifest.permission.CALL_PHONE)
                            .send();
                }
                break;
            case R.id.btn_send_sms:
                tv_content.setText(" ");
                if (AndPermission.hasPermission(this,Manifest.permission.SEND_SMS)) {
                    tv_content.append("已经拥有短信权限\n");
                } else {
                    tv_content.append("正在申请短信权限\n");
                    AndPermission.with(this)
                            .requestCode(100)
                            .permission(Manifest.permission.SEND_SMS)
                            .send();
                }
                break;
            case R.id.btn_photo:
                tv_content.setText(" ");
                if (AndPermission.hasPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    tv_content.append("已经拥有相册权限\n");
                } else {
                    tv_content.append("正在申请相册权限\n");
                    AndPermission.with(this)
                            .requestCode(100)
                            .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .send();
                }
                break;
            case R.id.btn_camer:
                tv_content.setText(" ");
                if (AndPermission.hasPermission(this,Manifest.permission.CAMERA)) {
                    tv_content.append("已经拥有拍照权限\n");
                } else {
                    tv_content.append("正在申请拍照权限\n");
                    AndPermission.with(this)
                            .requestCode(100)
                            .permission(Manifest.permission.CAMERA)
                            .send();
                }
                break;
        }
    }

    //使用了注解@PermissionYes(100)这里的100就是你申请权限时的请求码,这样实现也十分简洁清晰了
    @PermissionYes(100)
    private void getYes(List<String> grantedPermissions){
        tv_content.append("权限申请成功\n");
    }

    @PermissionNo(100)
    private void getNo(List<String> deniedPermissions){
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            // 第一种：用默认的提示语。
            tv_content.append("权限申请被拒绝,不在弹出提示\n");
            AndPermission.defaultSettingDialog(this, 1).show();
        }else{
            tv_content.append("权限申请被拒绝\n");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
