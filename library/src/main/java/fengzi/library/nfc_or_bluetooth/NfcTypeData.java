package fengzi.library.nfc_or_bluetooth;

import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Build;
import android.support.annotation.AnyRes;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import fengzi.library.tool.LogUtil;
import fengzi.library.tool.ToastUtil;

/**
 * 作者: {yff} time: 2017/4/6  15:18
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{nfc 一些类型数据工具方法}
 */
//      MifareClassic
//    log("==========="+nfctypeData.getMifareClassicType(mclass));
//    log("=====当前扇区的第1块的块号======"+mclass.sectorToBlock(0));
//    log("=====获得当前扇区的所包含块的数量======"+mclass.getBlockCountInSector(0));
//    log("====获得标签的容量======="+mclass.getSize());
//    log("=====获得标签总共有的的块数量======"+mclass.getBlockCount());
//    log("=====获得标签总共有的扇区数量======"+mclass.getSectorCount());
//    try{
//    log("=====读取当前块的数据======"+mclass.readBlock(0));
//    log("=====获得标签总共有的扇区数量======"+mclass.authenticateSectorWithKeyA(3,MifareClassic.KEY_DEFAULT));
//    log("=====获得标签总共有的扇区数量======"+mclass.authenticateSectorWithKeyB(1,MifareClassic.KEY_DEFAULT));
//    }catch(IOException e){
//    e.printStackTrace();
//    log("====异常======="+e.getMessage());
//    }
public class NfcTypeData{

    public static  final String NdefFormatable = "android.nfc.tech.NdefFormatable";



    public String getMifareClassicType(MifareClassic classic){
        String type = "";
        if(classic == null){
            return  type;
        }

        switch (classic.getType()){

            case MifareClassic.TYPE_CLASSIC:
                type = "TYPE_CLASSIC";
                break;
            case MifareClassic.TYPE_PLUS:
                type = "TYPE_PLUS";
                break;
            case MifareClassic.TYPE_PRO:
                type = "TYPE_PRO";
                break;
            case MifareClassic.TYPE_UNKNOWN:
                type = "TYPE_UNKNOWN";
                break;
            default:
                type = "TYPE_UNKNOWN";
                break;
        }
        return type;
    }

    //卡片是否格式化
    public   boolean cardIsFormart(Tag tag){
        if(tag != null){
            String[] techList = tag.getTechList();
            if(techList != null && techList.length > 0){
                for (String s : tag.getTechList()) {
                    LogUtil.d("=========" + s);
                    if (NdefFormatable.equals(s)) {
                        return  false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    //g格式化卡片
    public   boolean formatCard(Tag tag){

        NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                null, null,null);
        NdefMessage message = new NdefMessage(
                new NdefRecord[]{record});
        int size = message.toByteArray().length;
        try {
            //获取Ndef对象
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                //允许对标签进行IO操作
                ndef.connect();
                if (!ndef.isWritable()) {
                    LogUtil.d("NFC Tag是只读的！");
                    close(ndef,null);
                    return false;
                }
                if (ndef.getMaxSize() < size) {
                    LogUtil.d("NFC Tag的空间不足！");
                    close(ndef,null);
                    return false;
                }
                //向标签写入数据
                ndef.writeNdefMessage(message);
                LogUtil.d("已成功写入数据！!!");
                close(ndef,null);
                return true;
            } else {
                //获取可以格式化和向标签写入数据NdefFormatable对象
                android.nfc.tech.NdefFormatable format = android.nfc.tech.NdefFormatable.get(tag);
                //向非NDEF格式或未格式化的标签写入NDEF格式数据
                if (format != null) {
                    try {
                        format.connect();
                        format.format(message);
                        LogUtil.d("已成功写入数据！");
                        close(ndef,format);
                        return true;
                    } catch (Exception e) {
                        LogUtil.d("写入NDEF格式数据失败！");
                        close(ndef,format);
                        return false;
                    }
                } else {
                    LogUtil.d("NFC标签不支持NDEF格式！");
                    return false;
                }
            }
        } catch (Exception e) {
            LogUtil.d("=异常==" + e.getMessage());
            return false;
        }
    }
    public boolean writeNdefData(Tag tag,String msg) throws Exception {

        return writeNdefData(tag,msg,DataType.nokonwn);
    }

    public boolean writeNdefData(Tag tag,String msg,DataType type) throws Exception {
        LogUtil.d("=write=====" + msg);
        boolean isOk = false;
        if (tag != null) {
            //新建NdefRecord数组，本例中数组只有一个元素
            NdefRecord[] records = {createRecord(msg,type)};
            //新建一个NdefMessage实例
            NdefMessage message = new NdefMessage(records);
            // 解析TAG获取到NDEF实例
            Ndef ndef = Ndef.get(tag);
            if(!ndef.isWritable()){
                ToastUtil.show("卡片么有写入权限");
                return isOk;
            }
            if(message.toByteArray().length > ndef.getMaxSize()){
                ToastUtil.show("写入的数据大于卡片限制");
                return isOk;
            }
            ndef.connect();
            ndef.writeNdefMessage(message);
            close(ndef,null);
            isOk = true;
        }
        return isOk;
    }

    //读取ndef数据
    public String readNdefData(Tag tag){
        String msg = null;
        try {
            if (tag != null) {
                //解析Tag获取到NDEF实例
                Ndef ndef = Ndef.get(tag);
                ndef.connect();
                //获取NDEF消息
                NdefMessage message = ndef.getNdefMessage();
                //将消息转换成字节数组
                byte[] data = message.toByteArray();
                if (data != null) {
                    if (data.length > 3) {
                        byte[] dd = new byte[data.length - 3];
                        System.arraycopy(data, 3, dd, 0, dd.length);
                        msg = new String(dd);
                    } else {
                        LogUtil.d("=输出机器码:" + NFCDataDeal.byte2HexString(data));
                    }
                }
                close(ndef,null);
            }
        } catch (Exception e) {
            LogUtil.e("=read==Exception=====" + e.getMessage());
        }
        return msg;
    }

    public String getCardInfo(Tag tag){
        byte[] byteid = tag.getId();
        String uid = NFCDataDeal.byte2HexString(byteid);
        Ndef ndef = Ndef.get(tag);
        try {
            uid = "Id:" + uid + "\n类型:" + ndef.getType()
                    + "\nMaxSize:" + ndef.getMaxSize()
                    + "\n是否可写:" + ndef.isWritable()
                    + "\n是否只读" + ndef.canMakeReadOnly();
            Tag tag1 = ndef.getTag();
            if (tag1 != null) {
                String[] techList = tag1.getTechList();
                if (techList != null && techList.length > 0) {
                    uid += "当前卡片支持的数据类型:";
                    for (String ss : techList) {
                        uid += ";\n" + ss;
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.e("===读取卡片信息异常===" + e.getMessage());
        }
        return  uid;
    }

   public void close(Ndef ndef,NdefFormatable formatable){
        if(ndef != null && ndef.isConnected()){
            try {
                ndef.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(formatable != null && formatable.isConnected()){
            try {
                formatable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //返回一个NdefRecord实例
    private NdefRecord createRecord(String msgTag, DataType type) throws UnsupportedEncodingException {
        //组装字符串，准备好你要写入的信息
        String msg = TextUtils.isEmpty(msgTag) ? "123456" : msgTag;
        byte[] textBytes = msg.getBytes();
        //将字节数组封装到一个NdefRecord实例中去
        NdefRecord textRecord;
        if(type == DataType.packages){
            textRecord =  NdefRecord.createApplicationRecord(msgTag);
        }else if(type == DataType.texts){
            textRecord = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                    NdefRecord.RTD_TEXT, null, textBytes);
        }else if (type == DataType.uri){
            textRecord =  NdefRecord.createUri(msgTag);
        }else{
            textRecord = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                    null, null, textBytes);
        }
        return textRecord;
    }

//    public NdefRecord createTextRecord(String payload, boolean encodeInUtf8) {
//
//        byte[] langBytes = Locale.getDefault().getLanguage().getBytes(Charset.forName("US-ASCII"));
//        Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8") : Charset.forName("UTF-16");
//        byte[] textBytes = payload.getBytes(utfEncoding);
//        int utfBit = encodeInUtf8 ? 0 : (1 << 7);
//        char status = (char) (utfBit + langBytes.length);
//        byte[] data = new byte[1 + langBytes.length + textBytes.length];
//        data[0] = (byte) status;
//        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
//        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
//        NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
//                NdefRecord.RTD_TEXT, new byte[0], data);
//        return record;
//    }

    public enum DataType{
        /**
         * 包名
         */
        packages,
        /**
         * url
         */
        uri,
        /**
         * 文本
         */
        texts,
        /**
         * 默认格式
         */
        nokonwn;
    }

}
