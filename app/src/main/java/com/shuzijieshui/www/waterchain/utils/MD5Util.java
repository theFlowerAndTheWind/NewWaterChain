/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.utils
 * @ClassName: MD5Util
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/24 4:22 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/24 4:22 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.shuzijieshui.www.waterchain.utils;

import java.security.MessageDigest;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.utils
 * @ClassName: MD5Util
 * @Description: MD5加密工具类
 * @Author: sxt
 * @CreateDate: 2018/12/24 4:22 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/24 4:22 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MD5Util {
    /*
     * MD5加密，32位
     */

    public static String getMD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
