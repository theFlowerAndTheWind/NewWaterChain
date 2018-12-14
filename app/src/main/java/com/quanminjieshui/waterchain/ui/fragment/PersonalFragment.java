package com.quanminjieshui.waterchain.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quanminjieshui.waterchain.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by songxiaotao on 2018/12/5.
 * Class Note:我的个人中心
 */

public class PersonalFragment extends BaseFragment {
    private Unbinder unbinder;
    private View rootView;
    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView==null){
            rootView = inflater.inflate(R.layout.fragment_personal, container, false);

        }
        return rootView;
    }
}
