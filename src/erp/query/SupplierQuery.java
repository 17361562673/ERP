
/**
 * @ClassName DepQuery
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:30
 * @description
 * @version 1.0
 */
package erp.query;

import erp.model.Supplier;

public class SupplierQuery extends Supplier {
    //开始行号
    private Integer startNum;
    //页码
    private Integer pageNo;

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