package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
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
    private FactoryServiceResponseBean.WashFatoryDetail washFatoryDetail;

    private String[] titles=new String[]{"价格体系","流程介绍","常见问题"};
    private List<Fragment> fragments=new ArrayList<>();
    private List<FactoryServiceResponseBean.WashFatoryCageGory> list = new ArrayList<>();
    private ArrayList<FactoryServiceResponseBean.WashFatoryCageGory> arrayList = new ArrayList<>();
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
        fragments.add(new PriceSystemFragment());
        fragments.add(new ProcessDescFragment());
        fragments.add(new CommonProblemFragment());


    }

    @OnClick({R.id.img_title_left,R.id.factoty_btn_order})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.img_title_left:
                goBack(v);
                finish();
                break;
            case R.id.factoty_btn_order:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("class",1);
                bundle.putParcelable("WashFatoryDetail", washFatoryDetail);
                bundle.putParcelableArrayList("WashFatoryCageGoryList",arrayList);
                intent.putExtras(bundle);
                intent.setClass(FactoryServiceActivity.this,ConfirmOrderActivity.class);
                startActivity(intent);
                break;
            default:break;
        }
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_factory_service);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        doRequest();
    }

    public List<FactoryServiceResponseBean.WashFatoryCageGory> getList(){
        return this.list;
    }

    public void doRequest(){
        serviceParsenter.getFactoryService(this,fsid);
        showLoadingDialog();
    }
    @Override
    protected void onResume() {
        super.onResume();
        doRequest();
    }

    @Override
    public void onFactoryServiceSuceess(FactoryServiceResponseBean factoryServiceResponseBean) {
        dismissLoadingDialog();

        washFatoryDetail = factoryServiceResponseBean.getDetail();
        if(washFatoryDetail!=null) {
            s_name = washFatoryDetail.getS_name();
            description = washFatoryDetail.getDescription();
            img = washFatoryDetail.getImg();
            service_id = washFatoryDetail.getService_id();
            factory_id = washFatoryDetail.getFactory_id();
        }
        tvTitleCenter.setText(s_name);
        factory_service_title.setText(s_name);
        factory_service_des.setText(description);
        GlidImageManager.getInstance().loadImageView(this,img,service_img,R.drawable.ic_default_image);
        list.clear();
        arrayList.clear();
        list.addAll(factoryServiceResponseBean.getCate_lists());
        arrayList.addAll(factoryServiceResponseBean.getCate_lists());

        initViewpager();
        adapter.notifyDataSetChanged();
    }

    public void initViewpager(){
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
                    ToastUtils.showCustomToastMsg("点击了价格体系",150);
                }else if (position==1 && isSelect){
                    factoryViewpager.setCurrentItem(1);
                    ToastUtils.showCustomToastMsg("点击了流程介绍",150);
                }else {
                    TabLayout.Tab tab = factoryTabLayout.getTabAt(position);
                    if (tab != null) {
                        tab.select();
                    }
                    factoryViewpager.setCurrentItem(2);
                    ToastUtils.showCustomToastMsg("点击了常见问题",150);
                }

            }
        });
    }
    @Override
    public void onFactoryServiceFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceParsenter!=null){
            serviceParsenter.detachView();
        }
    }
}
