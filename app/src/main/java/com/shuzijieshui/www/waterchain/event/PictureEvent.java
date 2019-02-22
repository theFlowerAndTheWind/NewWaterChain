/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.beans.eventbus
 * @ClassName: PictureEvent
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/25 12:10 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/25 12:10 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.shuzijieshui.www.waterchain.event;

import android.net.Uri;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.beans.eventbus
 * @ClassName: PictureEvent
 * @Description: eventbus消息类，用于获取图片
 * @Author: sxt
 * @CreateDate: 2018/12/25 12:10 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/25 12:10 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PictureEvent {
    private Uri uri;
    private String imgStr;
    private int view_no;

    public PictureEvent(Uri uri) {
        this.uri = uri;
    }

    public PictureEvent(Uri uri, int view_no) {
        this.uri = uri;
        this.view_no = view_no;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    public int getView_no() {
        return view_no;
    }

    public void setView_no(int view_no) {
        this.view_no = view_no;
    }
}
