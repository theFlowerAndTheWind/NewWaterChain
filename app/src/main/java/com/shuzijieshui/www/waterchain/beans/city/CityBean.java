/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.beans.city
 * @ClassName: CityBean
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/25 4:37 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/25 4:37 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.shuzijieshui.www.waterchain.beans.city;

import java.util.ArrayList;

/**
 *
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.beans.city
 * @ClassName: CityBean
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/25 4:37 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/25 4:37 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CityBean {
    private String n;
    private ArrayList<Area> a;

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public ArrayList<Area> getA() {
        return a;
    }

    public void setA(ArrayList<Area> a) {
        this.a = a;
    }

    public class Area{
        private String s;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }
    }
}
