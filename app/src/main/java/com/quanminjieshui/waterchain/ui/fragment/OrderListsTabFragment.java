package com.quanminjieshui.waterchain.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderListsTabFragment extends BaseFragment {


    @BindView(R.id.tv_)
    TextView tv;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order_lists_tab, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void setTxt(String txt) {
        if (tv == null) {
            LogUtils.e("TAG", "NULL!NULL!NULL!NULL!NULL!NULL!");
        } else {
            tv.setText(txt);
        }
    }

}
