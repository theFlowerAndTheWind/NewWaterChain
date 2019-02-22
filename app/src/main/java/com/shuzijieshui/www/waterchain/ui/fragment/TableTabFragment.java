package com.shuzijieshui.www.waterchain.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import butterknife.BindView;
import butterknife.Unbinder;

public class TableTabFragment extends BaseFragment {


    @BindView(R.id.tv_content)
    TextView tvContent;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_table_lists_tab, container, false);
//        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }


    public void setTxt(String txt){
        if(tvContent==null){
            LogUtils.e("TAG","NULL!NULL!NULL!NULL!NULL!NULL!");
        }else{
            tvContent.setText(txt);
        }
    }

}
