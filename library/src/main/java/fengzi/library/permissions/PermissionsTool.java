package fengzi.library.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import fengzi.library.abstractBean.PermissionsImage;
import fengzi.library.interfaces.PermissionsCallBack;
import fengzi.library.tool.LogUtil;

/**
 * 作者: {yff} time: 2017/4/18  16:49
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{6.0以后的权限申请和处理}
 */

public class PermissionsTool {

    public final static int REQUSETCODE = 0x1F;

    /**
     * 这个是相册和拍照
     */
    public static class CamerAndPhoto {

        /**
         * 是否有拍照权限
         */
        public static boolean isHasCameraPermiss(Activity activity) {
            return isGetPermission(activity, getPerssin(PermissionsContanst.CAMERA));
        }

        /**
         * 请求拍照权限
         */
        public static void requstCamerPermiss(Activity activity) {
            requestPermission(activity, getPerssin(PermissionsContanst.CAMERA));
        }

        /**
         * 是否有去相册权限权限
         */
        public static boolean isHasPhotoPermiss(Activity activity) {
            return isGetPermission(activity, getPerssin(PermissionsContanst.WRITE_EXTERNAL_STORAGE));
        }

        /**
         * 请求相册权限
         */
        public static void requstPhotoPermiss(Activity activity) {
            requestPermission(activity, getPerssin(PermissionsContanst.WRITE_EXTERNAL_STORAGE));
        }

        /**
         * 是否是相册权限
         * @param manifestMsg
         * @return
         */
        public static boolean isPhotoPermiss(String manifestMsg){

            return getPerssin(PermissionsContanst.WRITE_EXTERNAL_STORAGE).equals(manifestMsg)
                    || getPerssin(PermissionsContanst.READ_EXTERNAL_STORAGE).equals(manifestMsg);
        }

        /**
         * 是否是拍照权限
         * @param manifestMsg
         * @return
         */
        public static boolean isCamerPermiss(String manifestMsg){

            return getPerssin(PermissionsContanst.CAMERA).equals(manifestMsg);
        }
    }

    public static class PhoteTel {
        /**
         * 是否有去相册权限权限
         */
        public static boolean isHasTelPermiss(Activity activity) {
            return isGetPermission(activity, getPerssin(PermissionsContanst.CALL_PHONE));
        }

        /**
         * 请求相册权限
         */
        public static void requstTelPermiss(Activity activity) {
            requestPermission(activity, getPerssin(PermissionsContanst.CALL_PHONE));
        }
    }

    public static class PhotoSms {
        /**
         * 是否有去相册权限权限
         */
        public static boolean isHasSmsPermiss(Activity activity) {
            return isGetPermission(activity, getPerssin(PermissionsContanst.READ_SMS));
        }

        /**
         * 请求相册权限
         */
        public static void requstSmsPermiss(Activity activity) {
            requestPermission(activity, getPerssin(PermissionsContanst.READ_SMS));
        }
    }


    /**
     * 是否获得权限
     *
     * @param activity    当前对象
     * @param permissions 权限组
     * @return
     */
    public static boolean isGetPermission(Activity activity, String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        } else {
            if (permissions != null && permissions.length > 0) {
                for (String perss : permissions) {
                    if (ContextCompat.checkSelfPermission(activity, perss) != PackageManager.PERMISSION_GRANTED) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 跳转到系统设置权限
     *
     * @param activity
     */
    public static void goSystemPermissionsSet(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivity(intent);
    }

    /**
     * 权限申请
     *
     * @param activity
     * @param permissions 权限组
     */
    public static void requestPermission(Activity activity, String... permissions) {
        ActivityCompat.requestPermissions(activity, permissions,
                REQUSETCODE);
    }

    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults,PermissionsCallBack calllBack,Activity activity) {
        switch (requestCode){
            case PermissionsTool.REQUSETCODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermission(permissions,true,calllBack,activity);
                    LogUtil.d("==========授权成功==========");
                } else {
                    LogUtil.d("==========授权失败！==========");
                    checkPermission(permissions,false,calllBack,activity);
                }
                break;
            }
        }
    }

    static void checkPermission(String[] permissions,boolean isSuccess,PermissionsCallBack calllBack,Activity activity){
        if(permissions != null && permissions.length > 0 && calllBack != null){
            for (String ss : permissions){
                if(calllBack instanceof PermissionsImage){
                    if(CamerAndPhoto.isCamerPermiss(ss)){
                        ((PermissionsImage)calllBack).camerPermiss(isSuccess);
                    }else if(CamerAndPhoto.isPhotoPermiss(ss)){
                        ((PermissionsImage)calllBack).photoPermiss(isSuccess);
                    }
                }else{
                    if(isSuccess){
                        calllBack.requestPermissionsSuccess(ss);
                    }else{
                        calllBack.requestPermissionsFail(ss,ActivityCompat.shouldShowRequestPermissionRationale(activity,ss));
                    }
                }
            }
            calllBack.requestPermissionsOver();
        }
    }


    static String getPerssin(int type) {

        return PermissionsContanst.getPermiss(type);
    }
}
