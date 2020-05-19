/**
 * @ClassName StoreDetail
 * @Authror zhouzhiqiang
 * @Date 2020/4/3 22:50
 * @description
 * @version 1.0
 */
package erp.model;

import java.util.Objects;

public class StoreDetail {
    private Integer detailId;
    private Integer storeId;
    private Integer productId;
    private Integer num;

    //设置和商品对多一点关系
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreDetail that = (StoreDetail) o;
        return detailId == that.detailId &&
                Objects.equals(storeId, that.storeId) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(num, that.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailId, storeId, productId, num);
    }
}
