/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: LoginStatusChangedEvent
 * Author: sxt
 * Date: 2019/1/1 2:03 AM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.quanminjieshui.waterchain.event;

/**
 *
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.event
 * @ClassName: LoginStatusChangedEvent
 * @Description: 页面切换前后登录状态发生变化，再次切换回来需刷新操作
 * @Author: sxt
 * @CreateDate: 2019/1/1 2:03 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/1 2:03 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginStatusChangedEvent {
    /**
     *
     * 登录成功后通知
     * 格式：
     * 单独通知  login_status_changed_target_operation
     *          login_status_changed_main_personal_refresh_nickname  登录状态与上次显示相比发生改变，MainActivity_PersonalFragment刷新头像右侧用户昵称
     * 全局通知  login_status_changed_notify_all//全局通知可能会有问题（同时产生多个网络请求，对象回收后空指针等）
     */
    private String msg;

    public LoginStatusChangedEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}