/**
 * @ClassName Page
 * @Authror zhouzhiqiang
 * @Date 2020/3/23 11:22
 * @description  分页 (封装分页对象)
 * @version 1.0
 */
package erp.utils;

import java.util.List;

public class Page {
    /*
    * 翻页计算公式(每页的开始行号)：startNum=(pageNo-1)*pageSize,翻页其实是每页的开始行号在不断变化，
    * select *from 表 limit (pageNo-1)*pageSize ,pageSize
    * */
    //当前页码（默认从第一页开始）
    private Integer pageNo=1;
    //每页的记录数
    private Integer pageSize=6;
    //指定查询条件下的记录数(这个数据需要从后台数据库查询出来)
    private Integer totalCount=0;
    //指定查询条件下的页数
    private Integer totalPage=1;
    //设置记录的集合(查出来了得有容器装吧 必须通过数据进行查询)
    private List<?> list;
    /*
    开始行号(因为数据库表的开始行号是从0开始的)
    * startNum=(pageNo-1)*pageSize
    * */
    private Integer startNum=0;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /*
    * pageSize     totalCount     totalPage
    *  10               0           1
    *  10               100         10
    *  10                55         6
    *  10                75          8
    * */
    public Integer getTotalPage() {
        totalPage=totalCount/pageSize;
        if (totalCount == 0 || totalCount % pageSize != 0) {
            totalPage++;
        }
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    //根据页码和每页的记录数计算开始行号(根据已知条件算出未知条件)
    public Integer getStartNum() {
        return (pageNo-1)*pageSize;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
