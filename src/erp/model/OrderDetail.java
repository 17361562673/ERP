/**
 * @ClassName OrderDetail
 * @Authror zhouzhiqiang
 * @Date 2020/3/29 20:36
 * @description
 * @version 1.0
 */
package erp.model;

import java.util.Objects;

public class OrderDetail {
    private Integer orderDetailId;
    private Integer detailNum;
    private Double detailPrice;
    private Integer productId;
    private String orderId;
    private Integer surplus;
    //指定订单明细和商品之间多对一的关系
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getDetailNum() {
        return detailNum;
    }

    public void setDetailNum(Integer detailNum) {
        this.detailNum = detailNum;
    }

    public Double getDetailPrice() {
        return detailPrice;
    }

    public void setDetailPrice(Double detailPrice) {
        this.detailPrice = detailPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getSurplus() {
        return surplus;
    }

    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return orderDetailId == that.orderDetailId &&
                Objects.equals(detailNum, that.detailNum) &&
                Objects.equals(detailPrice, that.detailPrice) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(surplus, that.surplus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDetailId, detailNum, detailPrice, productId, orderId, surplus);
    }
}
