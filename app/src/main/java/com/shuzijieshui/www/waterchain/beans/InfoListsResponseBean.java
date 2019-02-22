/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InfoListsResponseBean
 * Author: sxt
 * Date: 2019/1/1 4:25 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.shuzijieshui.www.waterchain.beans;

import java.util.List;

/**
 *
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.beans
 * @ClassName: InfoListsResponseBean
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/1 4:25 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/1 4:25 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class InfoListsResponseBean {
    private List<InfoEntity> lists;

    public List<InfoEntity> getLists() {
        return lists;
    }

   public class InfoEntity{
        private int id;
        private String title;
        private String img;
        private String publishtime;
        private String content;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImg() {
            return img;
        }

        public String getPublishtime() {
            return publishtime;
        }

        public String getContent() {
            return content;
        }
    }

}