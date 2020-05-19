
/**
 * @ClassName DepQuery
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:30
 * @description
 * @version 1.0
 */
package erp.query;

import erp.model.OrderModel;

import java.util.Date;

public class OrderModelQuery extends OrderModel {
    //开始行号
    private Integer startNum;
    //页码
    private Integer pageNo;
    //创建人
    private String createName;

    public String getCreateName() {
        return createName;
    }

    //最小订单量
    private Integer minTotalNum;
    //最大订单量
    private Integer maxTotalNum;

    public Integer getMinTotalNum() {
        return minTotalNum;
    }

    //最低总价格
    private Double minTotalPrice;
    //最高总价格
    private Double maxTotalPrice;

    public Double getMinTotalPrice() {
        return minTotalPrice;
    }

    public void setMinTotalPrice(Double minTotalPrice) {
        this.minTotalPrice = minTotalPrice;
    }

    public Double getMaxTotalPrice() {
        return maxTotalPrice;
    }

    public void setMaxTotalPrice(Double maxTotalPrice) {
        this.maxTotalPrice = maxTotalPrice;
    }

    //开始下单时间
    private Date minCreateTime;
    //截至下单时间
    private Date maxCreateTime;

    //开始审核时间
    private Date minCheckTime;
    //截至审核时间
    private Date maxCheckTime;

    public Date getMinCheckTime() {
        return minCheckTime;
    }

    //下单人
    private String orderCreate;
    //审核人
    private String orderCheckerName;
    //跟单人
    private String orderFollower;

    public String getOrderCreate() {
        return orderCreate;
    }

    public void setOrderCreate(String orderCreate) {
        this.orderCreate = orderCreate;
    }

    public String getOrderCheckerName() {
        return orderCheckerName;
    }

    public void setOrderCheckerName(String orderCheckerName) {
        this.orderCheckerName = orderCheckerName;
    }

    public String getOrderFollower() {
        return orderFollower;
    }

    public void setOrderFollower(String orderFollower) {
        this.orderFollower = orderFollower;
    }

    //送货方式
    private Integer needs;

    public Integer getNeeds() {
        return needs;
    }

    public void setNeeds(Integer needs) {
        this.needs = needs;
    }

    public void setMinCheckTime(Date minCheckTime) {
        this.minCheckTime = minCheckTime;
    }

    public Date getMaxCheckTime() {
        return maxCheckTime;
    }

    public void setMaxCheckTime(Date maxCheckTime) {
        this.maxCheckTime = maxCheckTime;
    }

    public Date getMinCreateTime() {
        return minCreateTime;
    }

    public void setMinCreateTime(Date minCreateTime) {
        this.minCreateTime = minCreateTime;
    }

    public Date getMaxCreateTime() {
        return maxCreateTime;
    }

    public void setMaxCreateTime(Date maxCreateTime) {
        this.maxCreateTime = maxCreateTime;
    }

    public void setMinTotalNum(Integer minTotalNum) {
        this.minTotalNum = minTotalNum;
    }

    public Integer getMaxTotalNum() {
        return maxTotalNum;
    }

    public void setMaxTotalNum(Integer maxTotalNum) {
        this.maxTotalNum = maxTotalNum;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
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