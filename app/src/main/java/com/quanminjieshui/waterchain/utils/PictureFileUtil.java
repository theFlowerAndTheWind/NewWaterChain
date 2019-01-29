/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.utils
 * @ClassName: FileUtil
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/24 4:21 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/24 4:21 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.quanminjieshui.waterchain.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.utils
 * @ClassName: FileUtil
 * @Description: 读取文件工具类
 * @Author: sxt
 * @CreateDate: 2018/12/24 4:21 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/24 4:21 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PictureFileUtil {
    private static final String TAG = "PictureFileUtil";

    public static final String PIC_DIR_NAME = "WaterChain";
    public static final String PIC_NAME_PREFIX = "waterchain_pic";

    /**
     * Convert byte[] to hex string.将byte转换成int，
     * 然后利用Integer.toHexString(int)来转换成16进制字符串。
     *
     * @param src byte[] data
     * @return hex string
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 根据文件名称和路径，获取sd卡中的文件，以File形式返回byte
     */
    public static File getFile(String fileName, String folder)
            throws IOException {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File pathFile = new File(Environment.getExternalStorageDirectory()
                    + folder);
            // && !pathFile .isDirectory()
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            File file = new File(pathFile, fileName);
            return file;
        }
        return null;
    }

    /**
     * 根据文件名称和路径，获取sd卡中的文件，判断文件是否存在，存在返回true
     */
    public static Boolean checkFile(String fileName, String folder)
            throws IOException {

        File targetFile = getFile(fileName, folder);

        if (!targetFile.exists()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore
                    .Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 检查文件是否存在
     */
    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    public static void copyFile(File sourcefile, File targetFile) {
        FileInputStream input = null;
        BufferedInputStream inbuff = null;
        FileOutputStream out = null;
        BufferedOutputStream outbuff = null;

        try {

            input = new FileInputStream(sourcefile);
            inbuff = new BufferedInputStream(input);

            out = new FileOutputStream(targetFile);
            outbuff = new BufferedOutputStream(out);

            byte[] b = new byte[1024 * 5];
            int len = 0;
            while ((len = inbuff.read(b)) != -1) {
                outbuff.write(b, 0, len);
            }
            outbuff.flush();
        } catch (Exception ex) {
        } finally {
            try {
                if (inbuff != null)
                    inbuff.close();
                if (outbuff != null)
                    outbuff.close();
                if (out != null)
                    out.close();
                if (input != null)
                    input.close();
            } catch (Exception ex) {

            }
        }
    }

    /**
     * 保存图片到本机
     *
     * @param context            context
     * @param fileName           文件名
     * @param file               file
     * @param saveResultCallback 保存结果callback
     */
    public static void saveImage(final Context context, final String fileName, final File file,
                                 final SaveResultCallback saveResultCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String dirPath = Environment.getExternalStorageDirectory().getPath() + File.separator + PictureFileUtil.PIC_DIR_NAME;
                    dirPath = PictureFileUtil.checkDirPath(dirPath);
                    String suffix = ".jpg";
                    if (("jpg,JPG,jpeg,JPEG,png,PNG").contains(fileName.substring(fileName.lastIndexOf(".")))) {
                        suffix = fileName.substring(fileName.lastIndexOf("."));
                    }
                    File saveFile = new File(dirPath,PictureFileUtil.PIC_NAME_PREFIX+System.currentTimeMillis()+suffix);

                    InputStream is = new FileInputStream(file);
                    FileOutputStream fos = new FileOutputStream(saveFile);
                    byte[] buffer = new byte[1024 * 1024];//1M缓冲区
                    int count = 0;
                    while ((count = is.read(buffer)) > 0) {
                        fos.write(buffer, 0, count);
                    }
                    fos.close();
                    is.close();
                    saveResultCallback.onSavedSuccess();
                } catch (FileNotFoundException e) {
                    saveResultCallback.onSavedFailed();
                    e.printStackTrace();
                } catch (IOException e) {
                    saveResultCallback.onSavedFailed();
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 保存Bitmap到本机
     *
     * @param context            context
     * @param fileName           bitmap文件名
     * @param bmp                bitmap
     * @param saveResultCallback 保存结果callback
     */
    public static void saveBitmap(final Context context, final String fileName, final Bitmap bmp,
                                  final SaveResultCallback
                                          saveResultCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File appDir = new File(Environment.getExternalStorageDirectory(), PIC_DIR_NAME);
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                //                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                // 设置以当前时间格式为图片名称
                String saveFileName = MD5Util.getMD5(PIC_NAME_PREFIX + fileName) + ".png";
                saveFileName = saveFileName.substring(20);//取前20位作为SaveName
                File file = new File(appDir, saveFileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.flush();
                    fos.close();
                    saveResultCallback.onSavedSuccess();
                } catch (FileNotFoundException e) {
                    saveResultCallback.onSavedFailed();
                    e.printStackTrace();
                } catch (IOException e) {
                    saveResultCallback.onSavedFailed();
                    e.printStackTrace();
                }
                //保存图片后发送广播通知更新数据库
                Uri uri = Uri.fromFile(file);
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            }
        }).start();
    }

    public interface SaveResultCallback {
        void onSavedSuccess();

        void onSavedFailed();
    }

    public static String bitmap2String(Bitmap bitmap) {
        String imgStr = null;
        ByteArrayOutputStream bos=null;
        if (bitmap != null) {
            try {
                bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

                bos.flush();
                bos.close();

                byte[]bytes=bos.toByteArray();
                imgStr=Base64.encodeToString(bytes,Base64.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (bos != null) {
                        bos.flush();
                        bos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imgStr;
    }


    public static File Uri2File(Uri uri,Context context) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA }, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    LogUtils.e("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            // 4.2.2以后
            String[] proj = { MediaStore.Images.Media.DATA };
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();
            return new File(path);
        } else {
            //Log.i(TAG, "Uri Scheme:" + uri.getScheme());
        }
        return null;
    }

}
