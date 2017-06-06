package fengzi.library.abstractBean;

import fengzi.library.interfaces.PermissionsCallBack;

/**
 * 作者: {yff} time: 2017/4/25  19:16
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{}
 */

public abstract   class PermissionsImage implements PermissionsCallBack{

    @Override
    public void requestPermissionsSuccess(String permissions) {

    }

    @Override
    public void requestPermissionsFail(String permissions, boolean isHavaPopu) {

    }

    @Override
    public void requestPermissionsOver() {

    }

    public abstract void photoPermiss(boolean isSuccess);

    public abstract void camerPermiss(boolean isSuccess);
}
