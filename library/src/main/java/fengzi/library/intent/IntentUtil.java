package fengzi.library.intent;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * 作者: {yff} time: 2017/3/22  15:47
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{ 有关intent的调用系统功能和一些常用功能}
 */

public class IntentUtil {

    public static final int OPEN_CROP = 0x198;

    /**
     * 调用系统有的图库
     */
    public static void openPicture(Activity activity, int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 调用系统的图片预览
     */
    public static void toReview(Activity activity, String url) {
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        File file = new File(url);
        intent.setDataAndType(Uri.fromFile(file), "image/*");
        activity.startActivity(intent);
    }

    public static void cropImageUri(Activity act,Uri uri, Uri outputUri, int outputX, int outputY) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        act.startActivityForResult(intent, OPEN_CROP);
    }

    /**
     * 请求打开系统相册功能，上面那个方法有时不起作用，调用这个即可
     * @param context
     * @param requestCode
     */
    public static void openSystemPhoto(Context context, int requestCode){
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((Activity) context).startActivityForResult(i, requestCode);
    }

    /**
     * 请求打开蓝牙权限
     * @param context
     * @param reqestCode
     */
    public static void openBluetoothPermission(Activity context,int reqestCode){
        openPermission(context,BluetoothAdapter.ACTION_REQUEST_ENABLE,reqestCode);
        openPermission(context,"android.bluetooth.adapter.action.REQUEST_BLE_SCAN_ALWAYS_AVAILABLE",reqestCode);
    }

    public static void openGpsPermission(Activity context,int reqestCode){
        openPermission(context, Manifest.permission.ACCESS_FINE_LOCATION,reqestCode);
    }

    /**
     * @param context 当前对象
     * @param action  权限aciton
     * @param reqestCode 请求码
     */
    public  static void openPermission(Activity context,String action,int reqestCode){
        Intent enableBtIntent = new Intent(action);
        context.startActivityForResult(enableBtIntent, reqestCode);
    }

    public static void openTel(Context context,String tel){
        Intent intent = new Intent(Intent.ACTION_DIAL);
//        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + tel);
        intent.setData(data);
        context.startActivity(intent);
    }

}
