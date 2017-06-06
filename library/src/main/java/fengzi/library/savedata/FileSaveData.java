package fengzi.library.savedata;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static fengzi.library.savedata.FileSaveData.FileSaveMode.*;

/**
 * 作者: {yff} time: 2017/3/24  16:48
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{通过文件形式保存数据}
 *
 MODE_PRIVATE：为默认操作模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容，如果想把新写入的内容追加到原文件中。可   以使用Context.MODE_APPEND

 MODE_APPEND：模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件。

 MODE_WORLD_READABLE：表示当前文件可以被其他应用读取；

 MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入。
 */

public class FileSaveData {

    static FileSaveData data;

    static String file_save_name = "FileSaveData.txt";

    int saveMode = Context.MODE_PRIVATE;

    public static FileSaveData getIntance(){
        if(data == null){
            data = new FileSaveData();
        }
        return  data;
    }

    public FileSaveData buildeMode(FileSaveMode mode){
        switch (mode){
            case M_PRIVATE:
                saveMode = Context.MODE_PRIVATE;
                break;
            case M_APPEND:
                saveMode = Context.MODE_APPEND;
                break;
            case M_WRITEABLE:
                saveMode = Context.MODE_WORLD_WRITEABLE;
                break;
            case M_READABLE:
                saveMode = Context.MODE_WORLD_READABLE;
                break;
        }
        return  data;
    }

    public   String read(Context context) {
        try {
            FileInputStream inStream = context.openFileInput(file_save_name);
            byte[] buffer = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder();
            while ((hasRead = inStream.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, hasRead));
            }

            inStream.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public   void write(Context context, String msg) {
        if (msg == null) return;
        try {
            FileOutputStream fos = context.openFileOutput(file_save_name,
                    context.MODE_APPEND);
            fos.write(msg.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  enum FileSaveMode{
        /**
         * 为默认操作模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，
         * 写入的内容会覆盖原文件的内容，如果想把新写入的内容追加到原文件中
         */
        M_PRIVATE,

        /**
         * 模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件。
         */
        M_APPEND,

        /**
         * 表示当前文件可以被其他应用读取；
         */
        M_READABLE,

        /**
         * 表示当前文件可以被其他应用写入。
         */
        M_WRITEABLE
    }

}
