
package com.quanminjieshui.waterchain.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import com.github.mikephil.charting.data.Entry;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.TradeCenterResponseBean;
import com.quanminjieshui.waterchain.beans.TradeLineResponseBean;
import com.quanminjieshui.waterchain.contract.model.CancleTradeModel;
import com.quanminjieshui.waterchain.contract.model.TradeCenterModel;
import com.quanminjieshui.waterchain.contract.model.TradeLineModel;
import com.quanminjieshui.waterchain.contract.presenter.CancleTradePresenter;
import com.quanminjieshui.waterchain.contract.presenter.TradeCenterPresenter;
import com.quanminjieshui.waterchain.contract.presenter.TradeLinePresenter;
import com.quanminjieshui.waterchain.contract.view.CancleTradeViewImpl;
import com.quanminjieshui.waterchain.contract.view.TradeCenterViewImpl;
import com.quanminjieshui.waterchain.contract.view.TradeLineViewImpl;
import com.quanminjieshui.waterchain.event.LoginStatusChangedEvent;
import com.quanminjieshui.waterchain.ui.activity.LoginActivity;
import com.quanminjieshui.waterchain.ui.activity.TradeListsActivity;
import com.quanminjieshui.waterchain.ui.adapter.BuySellTradeListAdapter;
import com.quanminjieshui.waterchain.ui.adapter.CurrentTradeListsAdapter;
import com.quanminjieshui.waterchain.ui.view.AlertChainDialog;
import com.quanminjieshui.waterchain.ui.widget.chart.ChartUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;
import com.quanminjieshui.waterchain.utils.SPUtil;
import com.quanminjieshui.waterchain.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by songxiaotao on 2018/12/5.
 * Class Note:交易
 */

public class TransactionFragment extends BaseFragment implements
        TradeCenterViewImpl,
        CancleTradeViewImpl,
        TradeLineViewImpl,
        CurrentTradeListsAdapter.OnCancleClickedListener {

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
    @BindView(R.id.ll_trade_total)
    LinearLayout llTradeTotal;
    @BindView(R.id.edt_total)
    EditText edtTotal;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    @BindView(R.id.btn_sell)
    Button btnSell;

    @BindView(R.id.tv_cur_trade)
    TextView tvCurTrade;
    @BindView(R.id.tv_history_trade)
    TextView tvHistoryTrade;
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

    @BindDrawable(R.drawable.gray_border_bg_shape)
    Drawable grayBgShape;
    @BindDrawable(R.drawable.red_border_illegal_bg_shape)
    Drawable redBgShape;


    Unbinder unbinder;

    private TradeCenterPresenter tradeCenterPresenter;
    private CancleTradePresenter cancleTradePresenter;
    private TradeLinePresenter tradeLinePresenter;
    private TradeCenterResponseBean.TradeListEntry trade_list;
    private List<TradeCenterResponseBean.BuySellEntity> buy;
    private List<TradeCenterResponseBean.BuySellEntity> sell;
    private List<TradeCenterResponseBean.TradeDetailEntity> trade_detail_list;//PC端显示，移动端不显示
    private List<TradeCenterResponseBean.UserCurrentTradeEntity> user_cur_trade;
    private List<Object> user_history_trade;
    private TradeCenterResponseBean.UserAccountEntity user_account;
    private TradeCenterResponseBean tradeCenterResponseBean;
    private int buyOrSell = 0;//0  buy       1 sell
    private int tradeType = 1;//1限价交易  2市价交易
    private Float tradePrice = 0.0f;//交易单价
    private Float tradeTotal = 0.0f;//交易数量
    private int visibility;//tvHighest是否可见
    private int visibility1;//llTradePrice是否可见
    private int is_login = 0;//后台返回是否登录  0未登录    1已登录
    /**
     * 是否登录
     * 记录当前是否登录，fargment切换后有登录、退出登录操作后，下次再显示时用该变量与本地SP所存结果比对，不一致时做刷新操作
     */
    private boolean isLogin = false;
    private String user_login;//用户手机号，作用同isLogin
    private CurrentTradeListsAdapter currentTradeListsAdapter;
    private List<TradeCenterResponseBean.UserCurrentTradeEntity> userCurrentTradeEntityList = new ArrayList<>();

    private String tradeLineType = "today";//today 取今天数据 week 近一周 year 一年
    private TradeLineResponseBean tradeLineResponseBean;

    private BuySellTradeListAdapter buySellTradeListAdapter;
    private List<TradeCenterResponseBean.BuySellEntity> buySellEntityArrayList = new ArrayList<>();
    private Handler handler = new Handler();
    private AlertChainDialog dialog;

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
        cancleTradePresenter = new CancleTradePresenter(new CancleTradeModel());
        cancleTradePresenter.attachView(this);
        tradeLinePresenter = new TradeLinePresenter(new TradeLineModel());
        tradeLinePresenter.attachView(this);
        doTradeCenter();
        reqTradeLineData(tradeLineType);

        showLoadingDialog();

        initView();
        return rootView;
    }

    private void initView() {
        /*************************module2********************************/
        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    buyOrSell = 0;
                    btnBuy.setVisibility(View.VISIBLE);
                    btnSell.setVisibility(View.GONE);
                    tvHighest.setText("已市场最优价贡献");
                    if (user_account != null && !TextUtils.isEmpty(user_account.getDs()))
                        tvUserAccount.setText(new StringBuilder("可用 ").append(tradeCenterResponseBean.getUser_account().getDs()).append(" 节水指标"));
                } else if (position == 1) {
                    buyOrSell = 1;
                    btnBuy.setVisibility(View.GONE);
                    btnSell.setVisibility(View.VISIBLE);
                    tvHighest.setText("已市场最优价获取");
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
        /*************************module3********************************/
        xrvCurTradeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrvCurTradeList.setLoadingMoreEnabled(false);
        xrvCurTradeList.setPullRefreshEnabled(false);
        xrvTradeList.setNestedScrollingEnabled(false);//禁止滑动
        currentTradeListsAdapter = new CurrentTradeListsAdapter(getActivity(), userCurrentTradeEntityList, this);
        xrvCurTradeList.setAdapter(currentTradeListsAdapter);

        /*************************module4*******************************/
        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    tradeLineType = "today";
                } else if (position == 1) {
                    tradeLineType = "week";
                } else if (position == 2) {
                    tradeLineType = "month";
                }
                reqTradeLineData(tradeLineType);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        /*************************module4-trade_list*******************************/
        buySellTradeListAdapter = new BuySellTradeListAdapter(getActivity(), buySellEntityArrayList);
        xrvTradeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrvTradeList.setNestedScrollingEnabled(false);//禁止滑动
        xrvTradeList.setAdapter(buySellTradeListAdapter);
        xrvTradeList.setLoadingMoreEnabled(false);
        xrvTradeList.setPullRefreshEnabled(false);
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
        //解析Bean   仅解析
        try {


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
                user_login = (String) SPUtil.get(getActivity(), SPUtil.USER_LOGIN, "user_login");
            }
            /***********************************module1**********************************************/
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
            /*************************************module3*************************************************/

            if (is_login == 1) {
                tvGoLogin.setVisibility(View.GONE);
                if (user_cur_trade != null && user_cur_trade.size() > 0) {
                    xrvCurTradeList.setVisibility(View.VISIBLE);
                    tvNoData.setVisibility(View.GONE);
                    userCurrentTradeEntityList.clear();
                    if (user_cur_trade.size() > 2) {//仅显示两条
                        userCurrentTradeEntityList.clear();
                        userCurrentTradeEntityList.add(user_cur_trade.get(0));
                        userCurrentTradeEntityList.add(user_cur_trade.get(1));
                    } else {
                        userCurrentTradeEntityList.addAll(user_cur_trade);
                    }
                    currentTradeListsAdapter.notifyDataSetChanged();

                } else {
                    xrvCurTradeList.setVisibility(View.GONE);
                    tvNoData.setVisibility(View.VISIBLE);
                    tvGoLogin.setVisibility(View.GONE);
                }
            } else if (is_login == 0) {
                xrvCurTradeList.setVisibility(View.GONE);
                tvNoData.setVisibility(View.GONE);
                tvGoLogin.setVisibility(View.VISIBLE);
                tvHistoryTrade.setVisibility(View.GONE);
            }

            /***************************************module4-chart***************************************************/


            /***************************************module4-trade_list****************************************************/
            if (trade_list != null) {
                buySellEntityArrayList.clear();
                if (buy != null)
                    buySellEntityArrayList.addAll(buy);

                if (sell != null)
                    buySellEntityArrayList.addAll(sell);

                if (buySellEntityArrayList.size() > 0) {
                    unfoldBottom();
                    buySellTradeListAdapter.notifyDataSetChanged();
                    xrvTradeList.refreshComplete();
                } else {
                    foldBottom();
                }
            } else {
                foldBottom();
            }

            dismissLoadingDialog();
        } catch (Exception e) {
            onTradeCenterFailed("请重新加载");
        }
    }

    @Override
    public void onTradeCenterFailed(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    public void onBuySuccess(Object o) {
        ToastUtils.showCustomToast("报单成功", 1);
        cleanEdt();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoadingDialog();
                doTradeCenter();
            }
        }, 500);
    }

    @Override
    public void onBuyFailed(String msg) {
        cleanEdt();
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    public void onSellSuccess(Object o) {
        ToastUtils.showCustomToast("报单成功", 1);
        cleanEdt();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoadingDialog();
                doTradeCenter();
            }
        }, 500);// is 500 enough?
    }

    @Override
    public void onSellFailed(String msg) {
        cleanEdt();
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    public void onCancle(int tid) {
        doCancle(tid);
//        showLoadingDialog();
    }

    @Override
    public void onCancleSuccess() {
//        dismissLoadingDialog();
        //todo  显示浮层
    }

    @Override
    public void onCancleFailed(String msg) {
//        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    public void onTradeLineSuccess(TradeLineResponseBean tradeLineResponseBean) {
        if (tradeLineResponseBean != null) {
            final List<TradeLineResponseBean.ChartDataEntity> data = tradeLineResponseBean.getData();
            final List<String> xaxis = tradeLineResponseBean.getXasix();
            final List<Entry> entries = ChartUtil.getEntries(data);
            final List<Long> longXaxis = ChartUtil.getLongXaxis(xaxis);
            ChartUtil.initLineChart(lineChart, entries, longXaxis, tradeLineType);
        }
    }

    @Override
    public void onTradeLineFailed(String msg) {

    }


    private void cleanEdt() {
        edtTotal.setText("");
        edtPrice.setText("");
        llTradePrice.setBackground(grayBgShape);
        llTradeTotal.setBackground(grayBgShape);
    }


    @OnClick({R.id.rl_trade_type, R.id.btn_buy, R.id.btn_sell, R.id.tv_history_trade,
            R.id.tv_go_login, R.id.ll_fold, R.id.btn_unfold})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rl_trade_type:
                showPopupWindown();
                break;

            case R.id.btn_buy:
                if (checkIsLogin()) {
                    if (setPriceTotalSucc()) {
                        doBuy(tradeType, tradeTotal, tradePrice);
                        showLoadingDialog();
                    }
                } else {
                    showAlertChainDialog();
                }
                break;

            case R.id.btn_sell:
                if (checkIsLogin()) {
                    if (setPriceTotalSucc()) {
                        doSell(tradeType, tradeTotal, tradePrice);
                        showLoadingDialog();
                    }
                } else {
                    showAlertChainDialog();
                }
                break;
            case R.id.tv_history_trade:
                if (checkIsLogin()) {
                    jump(TradeListsActivity.class, null);
                } else {
                    showAlertChainDialog();
                }
                break;
            case R.id.tv_go_login:
                jumpLogin();
                break;
            case R.id.ll_fold:
                containerBottom.setVisibility(View.GONE);
                btnUnfold.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_unfold:
                containerBottom.setVisibility(View.VISIBLE);
                btnUnfold.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    /**
     * 设置价格，数量
     */
    private boolean setPriceTotalSucc() {
        boolean result=false;
        //区分市价交易/限价交易决定price取值
        if (tradeType == 2) {//市价交易
            tradePrice = 0.0f;
        } else if (tradeType == 1) {//限价交易
            String priceStr = edtPrice.getText().toString();
            if (!TextUtils.isEmpty(priceStr)) {
                if (priceStr.endsWith(".")) {
                    priceStr = priceStr + "0";
                }
                tradePrice = Float.valueOf(priceStr);
                result= true;
            } else {
                llTradePrice.setBackground(redBgShape);
                ToastUtils.showCustomToastMsg("请输入正确的数值", 150);
                result= false;
            }
        }
        //total取值
        String totalStr = edtTotal.getText().toString();
        if (!TextUtils.isEmpty(totalStr)) {
            if (totalStr.endsWith(".")) {
                totalStr = totalStr + "0";
            }
            tradeTotal = Float.valueOf(totalStr);
            result= true;
        } else {
            llTradeTotal.setBackground(redBgShape);
            ToastUtils.showCustomToastMsg("请输入正确的数值", 150);
            result= false;
        }
        return result;
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
                if (buyOrSell == 0) {
                    tvHighest.setText("已市场最优价贡献");
                } else if (buyOrSell == 1) {
                    tvHighest.setText("已市场最优价获取");
                }
                popupWindow.dismiss();
            }
        });
        popupWindow.setTouchable(true);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(rlTradeType);

    }

    private boolean checkIsLogin() {
        if (is_login == 0) {
            return false;
        } else if (is_login == 1) {
            return true;
        } else {
            return false;
        }
    }

    private void showAlertChainDialog() {
        if (dialog == null) {
            dialog = new AlertChainDialog(getActivity());
        }
        dialog.builder().setCancelable(true);
        dialog.setTitle("提示消息")
                .setMsg("您当前未登录，请登录后继续操作")
                .setPositiveButton("登录", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jumpLogin();
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();
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

    private void unfoldBottom() {
        graySpaceHeaderAbove.setVisibility(View.VISIBLE);
        llListHeader.setVisibility(View.VISIBLE);
        xrvTradeList.setVisibility(View.VISIBLE);
    }

    private void doTradeCenter() {
        if (tradeCenterPresenter == null) {
            tradeCenterPresenter = new TradeCenterPresenter(new TradeCenterModel());
        }
        tradeCenterPresenter.getTradeCenter(getBaseActivity());
    }

    private void doBuy(int type, float total, float price) {
        if (tradeCenterPresenter == null) {
            tradeCenterPresenter = new TradeCenterPresenter(new TradeCenterModel());
        }
        tradeCenterPresenter.buy(getBaseActivity(), type, total, price);
    }

    private void doSell(int type, float total, float price) {
        if (tradeCenterPresenter == null) {
            tradeCenterPresenter = new TradeCenterPresenter(new TradeCenterModel());
        }
        tradeCenterPresenter.sell(getBaseActivity(), type, total, price);
    }

    private void doCancle(int tid) {
        if (cancleTradePresenter == null) {
            cancleTradePresenter = new CancleTradePresenter(new CancleTradeModel());
        }
        cancleTradePresenter.cancle(getBaseActivity(), tid);
    }

    private void reqTradeLineData(String type) {
        if (type.equals("today") || type.equals("week") || type.equals("month") || type.equals("year")) {
            if (tradeLinePresenter == null) {
                tradeLinePresenter = new TradeLinePresenter(new TradeLineModel());
            }
            tradeLinePresenter.getTradeLine(getBaseActivity(), type);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {//fragment被hide时保存
            isLogin = (boolean) SPUtil.get(getActivity(), SPUtil.IS_LOGIN, false);
            user_login = (String) SPUtil.get(getActivity(), SPUtil.USER_LOGIN, "token");
        }
    }


    public String getIsLogin() {
        return new StringBuilder().append(isLogin).append(user_login).toString();
    }

    public void onEventMainThread(LoginStatusChangedEvent event) {
        if (event != null && event.getMsg().equals("login_status_changed_main_transaction_reconnect")) {
            doTradeCenter();
            showLoadingDialog();
        }
    }

    @Override
    public void onDestroy() {
        tradeCenterPresenter.detachView();
        cancleTradePresenter.detachView();
        unbinder.unbind();
        super.onDestroy();
    }


}