package fengzi.library.base.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import fengzi.library.interfaces.PermissionsCallBack;
import fengzi.library.permissions.PermissionsTool;

/**
 * 作者: {yff} time: 2017/4/18  17:18
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{权限申请必须继承这个activity}
 */

public abstract class PermissionsActivity extends FBaseActivity implements PermissionsCallBack{

    public boolean isHasPermission(String... permissions){

        return PermissionsTool.isGetPermission(this,permissions);
    }

    public void getPermission(String... permissions){

      PermissionsTool.requestPermission(this,permissions);
    }

    /**
     * 权限申请成功
     * @param permissions 权限组
     */
    public abstract void requestPermissionsSuccess(String permissions);

    /**
     * 权限申请失败
     * @param permissions 权限组
     * @param isHavaPopu 是否在弹出授权窗口(用户选择点击不在提示,isHavaPopu为false)
     */
    public abstract void requestPermissionsFail(String permissions,boolean isHavaPopu);

    /**
     * 权限申请完毕
     */
    public abstract void requestPermissionsOver();



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsTool.onRequestPermissionsResult(requestCode,permissions,grantResults,PermissionsActivity.this,this);
//        switch (requestCode){
//            case PermissionsTool.REQUSETCODE: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    checkPermission(permissions,true);
//                    log("==========授权成功==========");
//                } else {
//                    log("==========授权失败！==========");
//                    checkPermission(permissions,false);
//                }
//                break;
//            }
//        }
    }

    void checkPermission(String[] permissions,boolean isSuccess){
        if(permissions != null && permissions.length > 0){
            for (String ss : permissions){
                if(isSuccess){
                    requestPermissionsSuccess(ss);
                }else{
                    requestPermissionsFail(ss,ActivityCompat.shouldShowRequestPermissionRationale(this,ss));
                }
            }
            requestPermissionsOver();
        }
    }
}
