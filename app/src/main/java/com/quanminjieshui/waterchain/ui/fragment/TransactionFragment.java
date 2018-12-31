package com.quanminjieshui.waterchain.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.BuyResponseBean;
import com.quanminjieshui.waterchain.beans.SellResponseBean;
import com.quanminjieshui.waterchain.beans.TradeCenterResponseBean;
import com.quanminjieshui.waterchain.contract.model.TradeCenterModel;
import com.quanminjieshui.waterchain.contract.presenter.TradeCenterPresenter;
import com.quanminjieshui.waterchain.contract.view.TradeCenterViewImpl;
import com.quanminjieshui.waterchain.event.LoginStatusChangedEvent;
import com.quanminjieshui.waterchain.ui.activity.LoginActivity;
import com.quanminjieshui.waterchain.ui.adapter.BuySellTradeListAdapter;
import com.quanminjieshui.waterchain.ui.adapter.CurrentTradeListsAdapter;
import com.quanminjieshui.waterchain.ui.widget.WarningFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by songxiaotao on 2018/12/5.
 * Class Note:交易
 */

public class TransactionFragment extends BaseFragment implements TradeCenterViewImpl, WarningFragment.OnWarningDialogClickedListener {

    @BindView(R.id.tv_trade_status)
    TextView tvTradeStatus;
    @BindView(R.id.tv_cur_price)
    TextView tvCurPrice;
    @BindView(R.id.tv_price_limit)
    TextView tvPriceLimit;
    @BindView(R.id.tablayout1)
    TabLayout tabLayout1;
    @BindView(R.id.rl_trade_type)
    View rlTradeType;
    @BindView(R.id.tv_trade_type)
    TextView tvTradeType;
    @BindView(R.id.ll_trade_price)
    LinearLayout llTradePrice;
    @BindView(R.id.edt_price)
    EditText edtPrice;
    @BindView(R.id.tv_highest)
    TextView tvHighest;
    @BindView(R.id.tv_user_account)
    TextView tvUserAccount;
    @BindView(R.id.edt_total)
    EditText edtTotal;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    @BindView(R.id.btn_sell)
    Button btnSell;

    @BindView(R.id.tv_cur_trade)
    TextView tvCurTrade;
    @BindView(R.id.xrv_cur_trade_list)
    XRecyclerView xrvCurTradeList;
    @BindView(R.id.tv_go_login)
    TextView tvGoLogin;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.btn_unfold)
    Button btnUnfold;

    @BindView(R.id.container_bottom)
    View containerBottom;
    @BindView(R.id.tablayout2)
    TabLayout tabLayout2;
    @BindView(R.id.ll_fold)
    LinearLayout llFold;
    @BindView(R.id.lineChart)
    LineChart lineChart;
    @BindView(R.id.gray_space_header_above)
    View graySpaceHeaderAbove;
    @BindView(R.id.ll_list_header)
    LinearLayout llListHeader;
    @BindView(R.id.xrl_trade_list)
    XRecyclerView xrvTradeList;


    Unbinder unbinder;

    private TradeCenterPresenter tradeCenterPresenter;
    private TradeCenterResponseBean.TradeListEntry trade_list;
    private List<TradeCenterResponseBean.BuySellEntity> buy;
    private List<TradeCenterResponseBean.BuySellEntity> sell;
    private List<TradeCenterResponseBean.BuySellEntity> tempList = new ArrayList<>();
    private List<TradeCenterResponseBean.TradeDetailEntity> trade_detail_list = new ArrayList<>();//PC端显示，移动端不显示
    private List<TradeCenterResponseBean.UserCurrentTradeEntity> user_cur_trade;
    private List<Object> user_history_trade;
    private TradeCenterResponseBean.UserAccountEntity user_account;
    private TradeCenterResponseBean tradeCenterResponseBean;
    private int buyOrSell = 0;//0  buy       1 sell
    private int tradeType = 1;//1限价交易  2市价交易
    private Float tradePrice = 0.0f;//交易单价
    private int visibility;//tvHighest是否可见
    private int visibility1;//llTradePrice是否可见
    private int is_login = 0;//后台返回是否登录  0未登录    1已登录
    /**
     * 是否登录
     * 记录当前是否登录，fargment切换后有登录、退出登录操作后，下次再显示时用该变量与本地SP所存结果比对，不一致时做刷新操作
     */
    private boolean isLogin = false;
    private CurrentTradeListsAdapter currentTradeListsAdapter;

    private String duration = "today";//today 取今天数据 week 近一周 year 一年

    private BuySellTradeListAdapter buySellTradeListAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transction, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        tradeCenterPresenter = new TradeCenterPresenter(new TradeCenterModel());
        tradeCenterPresenter.attachView(this);
        tradeCenterPresenter.getTradeCenter(getBaseActivity());


        initView();
        return rootView;
    }

    private void initView() {
        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    buyOrSell = 0;
                    btnBuy.setVisibility(View.VISIBLE);
                    btnSell.setVisibility(View.GONE);
                    if (user_account != null && !TextUtils.isEmpty(user_account.getDs()))
                        tvUserAccount.setText(new StringBuilder("可用 ").append(tradeCenterResponseBean.getUser_account().getDs()).append(" 节水指标"));
                } else if (position == 1) {
                    buyOrSell = 1;
                    btnBuy.setVisibility(View.GONE);
                    btnSell.setVisibility(View.VISIBLE);
                    if (user_account != null && !TextUtils.isEmpty(user_account.getJsl()))
                        tvUserAccount.setText(new StringBuilder("可用 ").append(tradeCenterResponseBean.getUser_account().getJsl()).append(" JSL"));

                }
                edtPrice.setText("");//清空上次输入值
                edtTotal.setText("");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        /****************************************************************/


//        currentTradeListsAdapter=new CurrentTradeListsAdapter(getActivity(),)
//        xrvCurTradeList.setAdapter();


        /****************************************************************/
        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    duration = "today";
                } else if (position == 1) {
                    duration = "week";
                } else if (position == 2) {
                    duration = "year";
                }
                //todo
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /****************************************************************/
        buySellTradeListAdapter = new BuySellTradeListAdapter(getActivity(), tempList);
        xrvTradeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrvTradeList.setNestedScrollingEnabled(false);//禁止滑动
        xrvTradeList.setAdapter(buySellTradeListAdapter);
        xrvTradeList.setLoadingMoreEnabled(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
    }


    @Override
    public void onTradeCenterSuccess(TradeCenterResponseBean bean) {
        tradeCenterResponseBean = bean;
        if (tradeCenterResponseBean != null) {
            trade_list = tradeCenterResponseBean.getTrade_list();
            if (trade_list != null) {
                buy = trade_list.getBuy();
                sell = trade_list.getSell();
            }
            trade_detail_list = tradeCenterResponseBean.getTrade_detail_list();
            user_cur_trade = tradeCenterResponseBean.getUser_cur_trade();
            user_history_trade = tradeCenterResponseBean.getUser_history_trade();
            user_account = tradeCenterResponseBean.getUser_account();
            is_login = tradeCenterResponseBean.getIs_login();
            //该处应进行校验，本地sp值与后台返回is_login对比
            if (is_login == 0) {
                isLogin = false;
            } else if (is_login == 1) {
                isLogin = true;
            }
        }
        /************************************************************************************************/
        tvTradeStatus.setText(tradeCenterResponseBean.getTrade_status());
        tvCurPrice.setText(tradeCenterResponseBean.getCur_price());
//        edtPrice.setHint(new StringBuilder("兑换价 ").append(tradeCenterResponseBean.getCur_price()));//需求不明白
        String price_limit_color = tradeCenterResponseBean.getPrice_limit_color();
        if (price_limit_color.equals("red")) {
            tvPriceLimit.setTextColor(getResources().getColor(R.color.primary_red));
            tvPriceLimit.setText(new StringBuilder("+").append(tradeCenterResponseBean.getPrice_limit()).toString());
        } else if (price_limit_color.equals("green")) {
            tvPriceLimit.setTextColor(getResources().getColor(R.color.text_green));
            tvPriceLimit.setText(new StringBuilder("-").append(tradeCenterResponseBean.getPrice_limit()).toString());
        } else {
            tvPriceLimit.setTextColor(getResources().getColor(R.color.text_black));
            tvPriceLimit.setText(tradeCenterResponseBean.getPrice_limit());
        }

        if (user_account != null) {
            if (buyOrSell == 0)
                tvUserAccount.setText(new StringBuilder("可用 ").append(user_account.getDs()).append(" 节水指标"));
            else if (buyOrSell == 1)
                tvUserAccount.setText(new StringBuilder("可用 ").append(user_account.getJsl()).append(" JSL"));
        }
        /************************************************************************************************/

        if (is_login == 1) {
            tvGoLogin.setVisibility(View.GONE);
            if (user_cur_trade != null && user_history_trade.size() > 0) {
                xrvCurTradeList.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
                //todo

            } else {
                xrvCurTradeList.setVisibility(View.GONE);
                tvNoData.setVisibility(View.VISIBLE);
            }

        } else if (is_login == 0) {
            xrvCurTradeList.setVisibility(View.GONE);
            tvNoData.setVisibility(View.GONE);
            tvGoLogin.setVisibility(View.VISIBLE);
        }

        /************************************************************************************************/


        /************************************************************************************************/
        if (trade_list != null) {
            tempList.clear();
            if (buy != null)
                tempList.addAll(buy);

            if (sell != null)
                tempList.addAll(sell);

            if (tempList.size() > 0) {
                unfoldBottom();
                buySellTradeListAdapter.notifyDataSetChanged();
                xrvTradeList.refreshComplete();
            } else {
                foldBottom();
            }
        } else {
            foldBottom();
        }
    }

    @Override
    public void onTradeCenterFailed(String msg) {

    }

    @Override
    public void onBuySuccess(BuyResponseBean buyResponseBean) {

    }

    @Override
    public void onBuyFailed(String msg) {

    }

    @Override
    public void onSellSuccess(SellResponseBean sellResponseBean) {

    }

    @Override
    public void onSellFailed(String msg) {

    }

    @Override
    public void onPositiveClicked(String tag) {
        switch (tag) {
            case "checkLoinBeforBuy":
                jumpLogin();
                break;
        }
    }

    @Override
    public void onNegativeClicked(String tag) {

    }

    @OnClick({R.id.rl_trade_type, R.id.btn_buy, R.id.btn_sell, R.id.tv_go_login, R.id.ll_fold
            , R.id.btn_unfold})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rl_trade_type:
                showPopupWindown();
                break;

            case R.id.btn_buy:
                if (checkIsLogin()) {
                    visibility = tvHighest.getVisibility();
                    visibility1 = llTradePrice.getVisibility();
                    if (visibility == View.VISIBLE && visibility1 == View.GONE) {
                        tradePrice = 0.0f;
                    } else if (visibility == View.GONE && visibility1 == View.VISIBLE) {
                        tradePrice = Float.valueOf(edtPrice.getText().toString());
                    }
                    tradeCenterPresenter.buy(getBaseActivity(), tradeType, tradePrice, Float.valueOf(edtTotal.getText().toString()));
                    break;
                }
            case R.id.btn_sell:
                if (checkIsLogin()) {
                    visibility = tvHighest.getVisibility();
                    visibility1 = llTradePrice.getVisibility();
                    if (visibility == View.VISIBLE && visibility1 == View.GONE) {
                        tradePrice = 0.0f;
                    } else if (visibility == View.GONE && visibility1 == View.VISIBLE) {
                        tradePrice = Float.valueOf(edtPrice.getText().toString());
                    }
                    tradeCenterPresenter.sell(getBaseActivity(), tradeType, tradePrice, Float.valueOf(edtTotal.getText().toString()));
                    break;
                }
            case R.id.tv_history_trade:
                checkIsLogin();
                break;
            case R.id.tv_go_login:
                jumpLogin();
                break;
            case R.id.ll_fold:
//                foldBottom();
                containerBottom.setVisibility(View.GONE);
                btnUnfold.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_unfold:
//                unfoldBottom();
                containerBottom.setVisibility(View.VISIBLE);
                btnUnfold.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void showPopupWindown() {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getBaseActivity()).inflate(
                R.layout.layout_trade_type_popup, null);
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        // 设置按钮的点击事件
        contentView.findViewById(R.id.tv_xj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tradeType = 1;
                tvTradeType.setText("限价交易");
                llTradePrice.setVisibility(View.VISIBLE);
                tvHighest.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        });
        contentView.findViewById(R.id.tv_sj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tradeType = 2;
                tvTradeType.setText("市价交易");
                llTradePrice.setVisibility(View.GONE);
                tvHighest.setVisibility(View.VISIBLE);
                popupWindow.dismiss();
            }
        });
        popupWindow.setTouchable(true);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(rlTradeType);

    }

    private boolean checkIsLogin() {
        if (is_login == 0) {
//            showToast("您还未登录，请登录后继续操作");
            WarningFragment fragment = new WarningFragment("提示消息", "您当前未登录，请登录后继续操作", "登录", "取消", "checkLoinBeforBuy", this);
            fragment.show(getActivity().getSupportFragmentManager(), "warning_fragment");
            return false;
        } else if (is_login == 1) {
            return true;
        } else {
            return false;
        }
    }

    private void jump(Class<?> cls, Intent intent) {
        if (intent == null) {
            startActivity(new Intent(getBaseActivity(), cls));
        } else {
            intent.setClass(getBaseActivity(), cls);
            startActivity(intent);
        }
    }

    private void jumpLogin() {
        Intent intent = new Intent();
        intent.putExtra("target", "main_transaction");
        jump(LoginActivity.class, intent);
    }

    private void foldBottom() {
        graySpaceHeaderAbove.setVisibility(View.GONE);
        llListHeader.setVisibility(View.GONE);
        xrvTradeList.setVisibility(View.GONE);
    }

    private void unfoldBottom(){
        graySpaceHeaderAbove.setVisibility(View.VISIBLE);
        llListHeader.setVisibility(View.VISIBLE);
        xrvTradeList.setVisibility(View.VISIBLE);
    }



    public boolean getIsLogin() {
        return this.isLogin;
    }
//    public void onEventMainThread(LoginEvent event) {
//        if (event != null && event.getMsg().equals("login_success_main_transaction_reconnect")) {
//            if (tradeCenterPresenter == null) {
//                tradeCenterPresenter = new TradeCenterPresenter(new TradeCenterModel());
//            }
//            tradeCenterPresenter.getTradeCenter(getBaseActivity());
//        }
//    }
//
//    public void onEventMainThread(LogoutEvent event) {
//        if (event != null && event.getMsg().equals("logout_main_transaction_reconnect")) {
//            if (tradeCenterPresenter == null) {
//                tradeCenterPresenter = new TradeCenterPresenter(new TradeCenterModel());
//            }
//            tradeCenterPresenter.getTradeCenter(getBaseActivity());
//        }
//    }

    public void onEventMainThread(LoginStatusChangedEvent event) {
        if (event != null && event.getMsg().equals("login_status_changed_main_transaction_reconnect")) {
            if (tradeCenterPresenter == null) {
                tradeCenterPresenter = new TradeCenterPresenter(new TradeCenterModel());
            }
            tradeCenterPresenter.getTradeCenter(getBaseActivity());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
