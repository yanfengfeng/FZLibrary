package fengzi.library.interfaces;

/**
 * 作者: {yff} time: 2017/4/19  17:24
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{}
 */

public interface PermissionsCallBack {
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
}
