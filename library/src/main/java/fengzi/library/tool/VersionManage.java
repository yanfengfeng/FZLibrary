package fengzi.library.tool;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 版本管理
 * Created by Administrator on 2016/11/30.
 */

public class VersionManage {

    final int LOADFAIL = 0x11;

    final int FILEALLSIZE = 0x12;

    final int FILELOADALLSIZE = 0x13;

    FragmentActivity activity ;

    public VersionManage(FragmentActivity activity){
        this.activity = activity;
    }

    public static VersionManage  getIntance(FragmentActivity activity){

        return  new VersionManage(activity);
    }



    File getFileFromServer(String path) throws Exception{
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            URL url = new URL(path);
            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
//            pd.setMax(conn.getContentLength());
            Message msg = new Message();
            msg.what = FILEALLSIZE;
            msg.arg1 = conn.getContentLength();
            handler.sendMessage(msg);
//            handler.obtainMessage(FILEALLSIZE,conn.getContentLength()).sendToTarget();
            InputStream is = conn.getInputStream();
            File file = new File(Environment.getExternalStorageDirectory(), "Railway.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len ;
            int total=0;
            while((len =bis.read(buffer))!=-1){
                fos.write(buffer, 0, len);
                total+= len;
                //获取当前下载量
//                pd.setProgress(total);
                handler.obtainMessage(FILELOADALLSIZE,total).sendToTarget();
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        }
        else{
            return null;
        }
    }

    /*
 *
 * 弹出对话框通知用户更新程序
 *
 * 弹出对话框的步骤：
 *  1.创建alertDialog的builder.
 *  2.要给builder设置属性, 对话框的内容,样式,按钮
 *  3.通过builder 创建一个对话框
 *  4.对话框show()出来
 */
    public void showUpdataDialog(final String apkurl,String msg) {
        downLoadApk(apkurl);
//        OkOrCancleDialog dialog = new OkOrCancleDialog(activity, new OkOrCancleDialog.ClickBackListern() {
//
//            @Override
//            public void clickBack(boolean isOk) {
//                if(isOk){
//                    downLoadApk(apkurl);
//                }
//            }
//        });
//        dialog.show();
//        dialog.setDialogTitle("版本更新提示");
//        dialog.setDialogMsg(msg);
    }


    protected void downLoadApk(final String apkurl) {
//        if(pd == null){
//            pd = new LoadProgressDialog(activity);    //进度条对话框
//            pd.show();
//            pd.setDialogtitle("正在下载中...");
//        } else if (!pd.isShowing()) {
//            pd.show();
//        }

        new Thread(){
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(apkurl);
                    sleep(1000);
                    installApk(file);
//                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    Message msg = new Message();
                    msg.what = LOADFAIL;
                    handler.sendMessage(msg);
                    e.printStackTrace();
//                    pd.dismiss();
                }
            }}.start();
    }


    Handler handler;

    {
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                switch (msg.what) {
                    case LOADFAIL:
                        //下载apk失败
                        Toast.makeText(activity, "下载新版本失败", Toast.LENGTH_SHORT).show();
                        break;

                    case FILEALLSIZE:
//                        if (pd != null) {
//                            LogUtil.d("tag", "=========" + msg.arg1);
//                            pd.setLoadingAll( msg.arg1);
//                        }
                        break;

                    case FILELOADALLSIZE:
//                        if (pd != null) {
//                            LogUtil.d("tag", "=========" + msg.obj);
//                            pd.setLoading((int) msg.obj);
//                        }
                        break;
                }
            }
        };
    }

    //安装apk
    protected void installApk(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        activity.startActivity(intent);
    }




}
