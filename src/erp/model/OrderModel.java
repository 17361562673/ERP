/**
 * @ClassName OrderModel
 * @Authror zhouzhiqiang
 * @Date 2020/3/29 12:37
 * @description
 * @version 1.0
 */
package erp.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class OrderModel {
    private Integer orderId;
    private String orderNum;
    private Integer creater;
    private Date createTime;
    private Integer checker;
    private Date checkTime;
    //订单负责人id
    private Integer completer;
    private Date endTime;
    private Integer orderType;
    private Integer orderState;
    private Integer totalNum;
    private Double totalPrice;
    private Integer supplierId;

    //设置与订单创建人多对一的关系
    private Emp ordercreater;

    private Emp orderChecker;

    //设置和核查人多对一的关系
    public Emp getOrderChecker() {
        return orderChecker;
    }
    //设置和跟单人多对一的关系
    private Emp orderCompleter;

    public Emp getOrderCompleter() {
        return orderCompleter;
    }

    public void setOrderCompleter(Emp orderCompleter) {
        this.orderCompleter = orderCompleter;
    }

    public void setOrderChecker(Emp orderChecker) {
        this.orderChecker = orderChecker;
    }

    public Emp getOrdercreater() {
        return ordercreater;
    }

    //设置与供应商多对一的关系
    private Supplier supplier;

    //设置和订单明细一对多的关系
    private Set<OrderDetail> orderDetails;

    //设置和操作日志一对多的关系
    private Set<ConsoleLog> consoleLogs;

    public Set<ConsoleLog> getConsoleLogs() {
        return consoleLogs;
    }

    public void setConsoleLogs(Set<ConsoleLog> consoleLogs) {
        this.consoleLogs = consoleLogs;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setOrdercreater(Emp ordercreater) {
        this.ordercreater = ordercreater;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getChecker() {
        return checker;
    }

    public void setChecker(Integer checker) {
        this.checker = checker;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getCompleter() {
        return completer;
    }

    public void setCompleter(Integer completer) {
        this.completer = completer;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel that = (OrderModel) o;
        return orderId == that.orderId &&
                Objects.equals(orderNum, that.orderNum) &&
                Objects.equals(creater, that.creater) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(checker, that.checker) &&
                Objects.equals(checkTime, that.checkTime) &&
                Objects.equals(completer, that.completer) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(orderType, that.orderType) &&
                Objects.equals(orderState, that.orderState) &&
                Objects.equals(totalNum, that.totalNum) &&
                Objects.equals(totalPrice, that.totalPrice) &&
                Objects.equals(supplierId, that.supplierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderNum, creater, createTime, checker, checkTime, completer, endTime, orderType, orderState, totalNum, totalPrice, supplierId);
    }
}
