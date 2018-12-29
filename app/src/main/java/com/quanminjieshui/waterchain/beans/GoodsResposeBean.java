/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.beans
 * @ClassName: GoodsResposeBean
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/26 11:11 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/26 11:11 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.quanminjieshui.waterchain.beans;

/**
 *
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.beans
 * @ClassName: GoodsResposeBean
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/26 11:11 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/26 11:11 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GoodsResposeBean {
    private String order_sn;
    private String g_name;
    private String c_name;
    private String total_price;
    private String createtime;
    private String status_view;

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getStatus_view() {
        return status_view;
    }

    public void setStatus_view(String status_view) {
        this.status_view = status_view;
    }
}
