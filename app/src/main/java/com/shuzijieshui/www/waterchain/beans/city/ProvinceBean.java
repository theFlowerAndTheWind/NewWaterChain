/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.beans.city
 * @ClassName: ProvinceBean
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/25 4:36 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/25 4:36 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.shuzijieshui.www.waterchain.beans.city;

import java.util.ArrayList;

/**
 *
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.beans.city
 * @ClassName: ProvinceBean
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/25 4:36 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/25 4:36 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ProvinceBean {
    private String p;
    private ArrayList<CityBean> c;

    public String getP() {
        return p;
    }

    public ArrayList<CityBean> getC() {
        return c;
    }

    public void setC(ArrayList<CityBean> c) {
        this.c = c;
    }

    public void setP(String p) {
        this.p = p;

    }
}
