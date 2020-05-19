
/**
 * @ClassName DepQuery
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:30
 * @description
 * @version 1.0
 */
package erp.query;

import erp.model.Store;

public class StoreQuery extends Store {
    //开始行号
    private Integer startNum;
    //页码
    private Integer pageNo;

    //仓库管理员
    private String storeAdminName;

    public String getStoreAdminName() {
        return storeAdminName;
    }

    public void setStoreAdminName(String storeAdminName) {
        this.storeAdminName = storeAdminName;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}