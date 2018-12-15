package com.quanminjieshui.waterchain.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quanminjieshui.waterchain.R;

/**
 * Created by songxiaotao on 2018/12/5.
 * Class Note:
 */

public class FindFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_find,container,false);
        return rootView;
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }
}
