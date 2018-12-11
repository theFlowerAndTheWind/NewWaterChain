package com.quanminjieshui.waterchain.contract;

import android.support.annotation.NonNull;

/**
 * Created by WanghongHe on 2018/12/3 11:36.
 */

public abstract class BasePresenter <V extends IBaseViewImpl>{

    protected V mView;

    public void attachView(@NonNull V view) {
        this.mView = view;
    }

    public void detachView() {
        this.mView = null;
    }
}
