//package com.shuzijieshui.www.waterchain.ui.widget.popup;
//
//import android.animation.Animator;
//import android.animation.AnimatorListenerAdapter;
//import android.animation.ValueAnimator;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.graphics.drawable.BitmapDrawable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//import com.jcodecraeer.xrecyclerview.XRecyclerView;
//import com.shuzijieshui.www.waterchain.R;
//import com.shuzijieshui.www.waterchain.beans.TotalPriceResponseBean;
//import com.shuzijieshui.www.waterchain.ui.adapter.CreateOrderListAdapter;
//
//import java.util.List;
//
///**
// * Created by WanghongHe on 2019/1/2 10:31.
// * 下单列表
// */
//
//public class CreateOrderListPopupWindow extends PopupWindow {
//
//    private Context mContext;
//    private XRecyclerView recyclerView;
//    private TextView totalCost;
//    private ImageView cancelImg;
//    private List<TotalPriceResponseBean.WashType> arrayList;
//    private String payPrice;
//    private String payJsl;
//    private CreateOrderListAdapter createOrderListAdapter;
//    private boolean isShowAniming;//show动画是否在执行中
//    private boolean isHideAniming;//hide动画是否在执行中
//    private LinearLayout llPopupRoot;
//
//
//    public CreateOrderListPopupWindow(Context context, TotalPriceResponseBean totalPriceResponseBean) {
//        super(null, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
//        this.mContext = context;
//        List<TotalPriceResponseBean.WashType> lists = totalPriceResponseBean.getLists();
//        payPrice = totalPriceResponseBean.getPay_price();
//        payJsl = totalPriceResponseBean.getPay_jsl();
//
//        this.arrayList = lists;
//
//
//        //设置点击空白处消失
//        setTouchable(true);
//        setFocusable(true);
//        setOutsideTouchable(true);
//        setClippingEnabled(false);
////        setAnimationStyle(R.style.Popupwindow);
//        setTouchInterceptor(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                /**
//                 * 判断是不是点击了外部
//                 */
//                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
//                    return true;
//                }
//                //不是点击外部
//                return false;
//            }
//        });
//        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
//        int w = wm.getDefaultDisplay().getWidth();
//        int h = wm.getDefaultDisplay().getHeight();
//        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//        bitmap.eraseColor(Color.parseColor("#88000000"));//填充颜色
//        setBackgroundDrawable(new BitmapDrawable(context.getResources(), bitmap));
//
//        initView();
//    }
//
//    private void initView() {
//
//        View rootView = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_creatorder_list, null);
//        setContentView(rootView);
//        llPopupRoot = rootView.findViewById(R.id.create_order_ll);
//        recyclerView = rootView.findViewById(R.id.cost_info_list);
//        totalCost = rootView.findViewById(R.id.total_cost);
//        cancelImg = rootView.findViewById(R.id.cancel);
//
//        cancelImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dismiss();
//            }
//        });
//
//        LinearLayoutManager layoutManage = new LinearLayoutManager(mContext);
//        recyclerView.setLoadingMoreEnabled(false);
//        recyclerView.setPullRefreshEnabled(false);
//        recyclerView.setLayoutManager(layoutManage);
//        createOrderListAdapter = new CreateOrderListAdapter(mContext, arrayList);
//        recyclerView.setAdapter(createOrderListAdapter);
//        String price = "¥ " + payPrice;
//        if (!TextUtils.isEmpty(payJsl) && Float.valueOf(payJsl) > 0) {
//            price = price + " + " + payJsl + " 水方";
//        }
//        totalCost.setText(price);
//
//    }
//
//    @Override
//    public void showAtLocation(View parent, int gravity, int x, int y) {
//        super.showAtLocation(parent, gravity, x, y);
//        if (!isShowAniming) {
//            isShowAniming = true;
//            popupAnim(llPopupRoot, 0.0f, 1.0f, 300, true);
//        }
//    }
//
//    @Override
//    public void dismiss() {
//        if (!isHideAniming) {
//            isHideAniming = true;
//            popupAnim(llPopupRoot, 1.0f, 0.0f, 300, false);
//        }
//    }
//
//    /**
//     * popupWindow属性动画
//     *
//     * @param view     执行属性动画的view
//     * @param start    start值
//     * @param end      end值
//     * @param duration 动画持续时间
//     * @param flag     true代表show，false代表hide
//     */
//    private void popupAnim(final View view, float start, final float end, int duration, final
//    boolean flag) {
//        ValueAnimator va = ValueAnimator.ofFloat(start, end).setDuration(duration);
//        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (float) animation.getAnimatedValue();
//                view.setPivotX(0);
//                view.setPivotY(view.getMeasuredHeight());
//                view.setTranslationY((1 - value) * view.getHeight());
//            }
//        });
//        va.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//
//                if (!flag) {
//                    isHideAniming = false;
//                    CreateOrderListPopupWindow.super.dismiss();
//                } else {
//                    isShowAniming = false;
//                }
//            }
//        });
//        va.start();
//    }
//
//}
