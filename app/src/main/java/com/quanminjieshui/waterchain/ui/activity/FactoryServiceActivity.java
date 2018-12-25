package com.quanminjieshui.waterchain.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.FactoryServiceResponseBean;
import com.quanminjieshui.waterchain.contract.presenter.FactoryServiceParsenter;
import com.quanminjieshui.waterchain.contract.view.FactoryServiceViewImpl;
import com.quanminjieshui.waterchain.ui.adapter.TableViewpagerAdapter;
import com.quanminjieshui.waterchain.ui.fragment.CommonProblemFragment;
import com.quanminjieshui.waterchain.ui.fragment.PriceSystemFragment;
import com.quanminjieshui.waterchain.ui.fragment.ProcessDescFragment;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.ToastUtils;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2018/12/22.
 * Class Note:
 */

public class FactoryServiceActivity extends BaseActivity implements FactoryServiceViewImpl{


    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.factory_service_tablayout)
    TabLayout factoryTabLayout;
    @BindView(R.id.factory_service_viewpager)
    ViewPager factoryViewpager;
    @BindView(R.id.factory_service_title)
    TextView factory_service_title;
    @BindView(R.id.factory_service_des)
    TextView factory_service_des;
    @BindView(R.id.service_img)
    ImageView service_img;




    private FactoryServiceParsenter serviceParsenter;
    private TableViewpagerAdapter adapter;

    private String[] titles=new String[]{"价格体系","流程介绍","常见问题"};
    private List<Fragment> fragments=new ArrayList<>();
    private List<FactoryServiceResponseBean.WashFatoryCageGory> list = new ArrayList<>();
    private String s_name,description,img;
    private int fsid,service_id,factory_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this,titleBar);
        serviceParsenter = new FactoryServiceParsenter();
        serviceParsenter.attachView(this);
        initView();

    }

    private void initView() {
        tvTitleCenter.setText("宾馆酒店系列");
        if(getIntent()!= null){
            fsid = getIntent().getIntExtra("fsid",-1);
        }
        serviceParsenter.getFactoryService(this,fsid);



    }

    @OnClick({R.id.img_title_left})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.img_title_left:
                goBack(v);
                finish();
                break;
        }
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_factory_service);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    public List<FactoryServiceResponseBean.WashFatoryCageGory> getList(){

        return this.list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        serviceParsenter.getFactoryService(this,fsid);
        showLoadingDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onFactoryServiceSuceess(FactoryServiceResponseBean factoryServiceResponseBean) {
        s_name = factoryServiceResponseBean.getDetail().getS_name();
        description = factoryServiceResponseBean.getDetail().getDescription();
        img = factoryServiceResponseBean.getDetail().getImg();
        service_id = factoryServiceResponseBean.getDetail().getService_id();
        factory_id = factoryServiceResponseBean.getDetail().getFactory_id();

        factory_service_title.setText(s_name);
        factory_service_des.setText(description);
        GlidImageManager.getInstance().loadImageUri(this,img,service_img,R.drawable.ic_default_image);
        list.clear();
        list.addAll(factoryServiceResponseBean.getCate_lists());
        dismissLoadingDialog();

        fragments.add(new PriceSystemFragment());
        fragments.add(new ProcessDescFragment());
        fragments.add(new CommonProblemFragment());

        adapter = new TableViewpagerAdapter(getSupportFragmentManager(),fragments,titles);
        factoryViewpager.setAdapter(adapter);
        factoryViewpager.setCurrentItem(0);//默认选中第一项
        factoryTabLayout.setupWithViewPager(factoryViewpager,false);
        factoryViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0://默示什么都没做
                        break;
                    case 1://默认正在滑动
                        break;
                    case 2://默认滑动完毕
                        break;
                    default:break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        factoryTabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position= (int) view.getTag();
                boolean isSelect = factoryTabLayout.getTabAt(position).isSelected();
                if (position==0 && isSelect){
                    factoryViewpager.setCurrentItem(0);
                    ToastUtils.showCustomToast("点击了价格体系");
                }else if (position==1 && isSelect){
                    factoryViewpager.setCurrentItem(1);
                    ToastUtils.showCustomToast("点击了流程介绍");
                }else {
                    TabLayout.Tab tab = factoryTabLayout.getTabAt(position);
                    if (tab != null) {
                        tab.select();
                    }
                    factoryViewpager.setCurrentItem(2);
                    ToastUtils.showCustomToast("点击了常见问题");
                }

            }
        });

    }

    @Override
    public void onFactoryServiceFailed(String msg) {
        dismissLoadingDialog();
    }
}
