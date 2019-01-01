/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.utils
 * @ClassName: GsonUtil
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/25 4:52 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/25 4:52 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.quanminjieshui.waterchain.utils;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.quanminjieshui.waterchain.WaterChainApplication;
import com.quanminjieshui.waterchain.beans.city.ProvinceBean;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.utils
 * @ClassName: GsonUtil
 * @Description: Gson解析城市
 * @Author: sxt
 * @CreateDate: 2018/12/25 4:52 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/25 4:52 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GsonUtil {

    public static String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = WaterChainApplication.getInstance().getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();

    }

    public static ArrayList<ProvinceBean>getCities(String jsonStr){
        ProvinceBean[]beans=new Gson().fromJson(jsonStr,ProvinceBean[].class);
        List<ProvinceBean> provinceBeans = Arrays.asList(beans);
        ArrayList<ProvinceBean>arrayList=new ArrayList<ProvinceBean>(provinceBeans);
        return arrayList;
    }

    public static ArrayList<ProvinceBean> parseData(String jsonStr) {//Gson 解析
        ArrayList<ProvinceBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(jsonStr);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                ProvinceBean entity = gson.fromJson(data.optJSONObject(i).toString(), ProvinceBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }


}
