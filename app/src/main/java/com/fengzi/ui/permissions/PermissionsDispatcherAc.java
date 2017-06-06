package com.fengzi.ui.permissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fengzi.test.R;

import fengzi.library.base.activity.FBaseActivity;
import fengzi.library.intent.IntentUtil;
import fengzi.library.permissions.PermissionsTool;
import fengzi.library.tool.AppUtil;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 备注{
 * PermissionsDispatcher 第三方权限
 * <p>
 * app的build.gradle配置如下:
 * apply plugin: 'android-apt'
 * dependencies {
 * compile 'com.github.hotchemi:permissionsdispatcher:${latest.version}'
 * apt 'com.github.hotchemi:permissionsdispatcher-processor:${latest.version}'
 * }
 * <p>
 * project的 build.gradle配置如下:
 * buildscript {
 * dependencies {
 * classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
 * }
 * }
 * }
 */

@RuntimePermissions
public class PermissionsDispatcherAc extends FBaseActivity implements View.OnClickListener {

    TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions_dispatcher);
        setTitle("PermissionsDispatcher");
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
                PermissionsTool.goSystemPermissionsSet(this);
                break;
            case R.id.btn_call_tel:
                tv_content.setText(" ");

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                        tv_content.setText("电话权限正在申请中...");
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.CALL_PHONE}, 100);
                    } else {
                        tv_content.setText("电话权限禁止l,并且不在弹出提示框");
                        PermissionsTool.goSystemPermissionsSet(this);
                    }
                } else {
                    tv_content.setText("电话权限已申请");
                }
                break;
            case R.id.btn_send_sms:
                tv_content.setText(" ");
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
                        tv_content.setText("短信权限正在申请中...");
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_SMS}, 100);
                    } else {
                        tv_content.setText("短信权限禁止l,并且不在弹出提示框");
                        PermissionsTool.goSystemPermissionsSet(this);
                    }
                } else {
                    tv_content.setText("短信权限已申请");
                }

                break;
            case R.id.btn_photo:
                tv_content.setText(" ");
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        tv_content.setText("相册权限正在申请中...");
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                    } else {
                        tv_content.setText("相册权限禁止l,并且不在弹出提示框");
                        PermissionsTool.goSystemPermissionsSet(this);
                    }
                } else {
                    tv_content.setText("相册权限已申请");
                }
                break;
            case R.id.btn_camer:
                tv_content.setText(" ");
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                        tv_content.setText("拍照权限正在申请中...");
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.CAMERA}, 100);
                    } else {
                        tv_content.setText("拍照权限禁止l,并且不在弹出提示框");
                        PermissionsTool.goSystemPermissionsSet(this);
                    }
                } else {
                    tv_content.setText("拍照权限已申请");
                }

                break;
        }
    }

    @NeedsPermission({Manifest.permission.CAMERA})
    void aa() {
        tv_content.append("\n权限申请成功");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsDispatcherAcPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.CAMERA})
    void bb(final PermissionRequest request) {
        request.proceed();
        tv_content.append("\nrequest");
    }

    @OnPermissionDenied({Manifest.permission.CAMERA})
    void cc() {
        tv_content.append("\n权限申请被拒绝");
    }

    @OnNeverAskAgain({Manifest.permission.CAMERA})
    void dd() {
        tv_content.append("\n权限再次申请");
    }
}
