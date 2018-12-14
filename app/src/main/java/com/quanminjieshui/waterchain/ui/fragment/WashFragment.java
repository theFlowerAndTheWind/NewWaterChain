package com.quanminjieshui.waterchain.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quanminjieshui.waterchain.R;

/**
 * Created by songxiaotao on 2018/12/5.
 * Class Note:洗涤
 */

public class WashFragment extends BaseFragment {

    private View rootView;
    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView==null){
            rootView = inflater.inflate(R.layout.fragment_wash, container, false);
        }

            return rootView;
    }
}
