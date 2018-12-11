package com.quanminjieshui.waterchain.http;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by WanghongHe on 2018/12/3 11:42.
 */
public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {
    protected Context mContext;

    public BaseObserver(Context cxt) {
        this.mContext = cxt;
    }

    public BaseObserver() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();

    }

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {
        onRequestEnd();
        if (tBaseEntity.isSuccess()) {
            try {
                onSuccess(tBaseEntity.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {

                onCodeError(tBaseEntity.getCode(), tBaseEntity.getMsg());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.d("onCommitError: ", e);//这里可以打印错误信息
        onRequestEnd();
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccess(T t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param code
     * @param msg
     * @throws Exception
     */
    protected void onCodeError(String code, String msg) throws Exception {
//        if (code.equals("1006") && mContext != null) {
//            Activity lastActivity = ActivityManager.currentActivity();
//            if (!(lastActivity instanceof LoginActivity)) {
//                Intent intent = new Intent(mContext, LoginActivity.class);
//                intent.putExtra("reLogin", true);
//                LogUtils.d("===========Loginactivity==========");
//                mContext.startActivity(intent);
//            }
//        }
    }

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected abstract void onFailure(Throwable e, boolean isNetWorkError) throws Exception;

    protected void onRequestStart() {
    }

    protected void onRequestEnd() {
        closeProgressDialog();
    }

    public void showProgressDialog() {
        //  ProgressDialog.show(mContext, false, "请稍后");
    }

    public void closeProgressDialog() {
        // ProgressDialog.cancle();
    }

}