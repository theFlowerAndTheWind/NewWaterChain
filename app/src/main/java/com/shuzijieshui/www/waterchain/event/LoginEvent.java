///**
// * Copyright (C), 2015-2019, XXX有限公司
// * FileName: LoginEvent
// * Author: sxt
// * Date: 2019/1/1 12:22 AM
// * Description:
// * History:
// * <author> <time> <version> <desc>
// * 作者姓名 修改时间 版本号 描述
// */
//
//package com.quanminjieshui.www.waterchain.event;
//
///**
// *
// * @ProjectName: NewWaterChain
// * @Package: com.quanminjieshui.www.waterchain.event
// * @ClassName: LoginEvent
// * @Description: 登录成功后发出，接收者受到后做相应处理
// *              目前由MainActivity/LoginActivity发出
// * @Author: sxt
// * @CreateDate: 2019/1/1 12:22 AM
// * @UpdateUser: 更新者
// * @UpdateDate: 2019/1/1 12:22 AM
// * @UpdateRemark: 更新说明
// * @Version: 1.0
// */
//public class LoginEvent {
//    /**
//     *
//     * 登录成功后通知
//     * 格式：
//     * 单独通知  login_success_target_event
//     *          login_success_main_personal_refresh_nickname  登录成功，MainActivity_PersonalFragment刷新头像右侧用户昵称
//     * 全局通知  login_success_notify_all//全局通知可能会有问题（同时产生多个网络请求，对象回收后空指针等）
//     */
//    private String msg;
//
//    public LoginEvent(String msg) {
//        this.msg = msg;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//
//}