/**
 * @ClassName EmpQuery
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 14:21
 * @description
 * @version 1.0
 */
package erp.query;

import erp.model.Emp;

import java.util.Date;

public class EmpQuery extends Emp {
    private Date startBir;
    private Date endBir;
    //开始行号
    private Integer startNum;
    //页码
    private Integer pageNo;
    public Date getStartBir() {
        return startBir;
    }

    public void setStartBir(Date startBir) {
        this.startBir = startBir;
    }

    public Date getEndBir() {
        return endBir;
    }

    public void setEndBir(Date endBir) {
        this.endBir = endBir;
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
