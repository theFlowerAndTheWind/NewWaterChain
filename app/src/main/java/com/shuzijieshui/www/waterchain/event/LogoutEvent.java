//package com.quanminjieshui.www.waterchain.event;
//
///**
// * create by sxt
// * 退出登录后发出，目前由MainActivity/UserDetailActivity发出
// * 接收者接收到后做相应处理
// */
//public class LogoutEvent {
//    /**
//     *
//     * 退出登录后通知
//     * 格式：
//     * 单独通知  logout_target_event
//     *          logout_main_personal_refresh_nickname  退出登录，MainActivity_PersonalFragment刷新头像右侧用户昵称
//     * 全局通知  logout_notify_all//全局通知可能会有问题（同时产生多个网络请求，对象回收后空指针等）
//     */
//    private String msg;
//
//    public LogoutEvent(String msg) {
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
//}
