/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FindPassPresenter
 * Author: sxt
 * Date: 2018/12/9 2:17 AM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.FindPassModel;
import com.quanminjieshui.waterchain.contract.view.FindPassViewImpl;

import java.util.Map;

/**
 * @ClassName: FindPassPresenter
 * @Description: java类作用描述
 * @Author: sxt
 * @Date: 2018/12/9 2:17 AM
 */
public class FindPassPresenter extends BasePresenter<FindPassViewImpl>  {

    private FindPassModel model;

    public FindPassPresenter(FindPassModel model) {
        this.model = model;
    }

    public void verify(String mobile) {
        model.verify(mobile);
    }

    public void verify(final String mobile, final String pwd, final String confirm, final String sms) {
        model.verify(mobile, pwd, confirm, sms);
    }

    public void getSms(BaseActivity activity, String mobile) {
        if (model == null) {
            model = new FindPassModel();
        }


        model.getSms(activity, mobile, new FindPassModel.FindPassCallback() {


            @Override
            public void onEdtContentsLegal() {
                if (mView != null) {
                    mView.onEdtContentsLegal();
                }
            }

            @Override
            public void onEdtContentsIllegal(Map<String, Boolean> verify) {
                if (mView != null) {
                    mView.onEdtContentsIllegal(verify);
                }
            }

            @Override
            public void onGetSmsSuccess() {
                if (mView != null) {
                    mView.onGetSmsSuccess();
                }
            }

            @Override
            public void onGetSmsFailed(String msg) {
                if (mView != null) {
                    mView.onGetSmsFailed(msg);
                }
            }

            @Override
            public void onFindPassSuccess() {
            }

            @Override
            public void onFindPassFaild(String msg) {
            }

        });
    }

    public void reset(BaseActivity activity, final String mobile, final String pwd, final String confirm, final String sms) {
        if (model == null) {
            model = new FindPassModel();
        }
        model.findPass(activity, mobile, pwd, confirm, sms, new FindPassModel.FindPassCallback() {
            @Override
            public void onEdtContentsLegal() {
                if (mView != null) {
                    mView.onEdtContentsLegal();
                }
            }

            @Override
            public void onEdtContentsIllegal(Map<String, Boolean> verify) {
                if (mView != null) {
                    mView.onEdtContentsIllegal(verify);
                }
            }

            @Override
            public void onGetSmsSuccess() {
            }

            @Override
            public void onGetSmsFailed(String msg) {
            }

            @Override
            public void onFindPassSuccess() {
                if (mView != null) {
                    mView.onFindPassSuccess();
                }
            }

            @Override
            public void onFindPassFaild(String msg) {
                if (mView != null) {
                    mView.onFindPassFaild(msg);
                }
            }
        });
    }
}
