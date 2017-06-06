package fengzi.library.tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者: {yff} time: 2017/3/22  19:02
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{一些加密的算法}
 */

public class Md5Util {

    /**
     * MD5加密 32位小写
     *
     * @param sSecret
     * @return
     */
    public static String getMd5Value(String sSecret) {
        try {
            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update(sSecret.getBytes());
            int i;
            StringBuffer buf = new StringBuffer();
            byte[] b = bmd5.digest();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
