/**
 * @ClassName Product
 * @Authror zhouzhiqiang
 * @Date 2020/3/28 19:40
 * @description
 * @version 1.0
 */
package erp.model;

import java.util.Objects;

public class Product {
    private Integer productId;
    private Integer productTypeId;
    private String name;
    private String origin;
    private String producer;
    private String unit;
    private Double inPrice;
    private Double outPrice;

    //指定商品和商品类别多对一的关系
    private ProductType productType;

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getInPrice() {
        return inPrice;
    }

    public void setInPrice(Double inPrice) {
        this.inPrice = inPrice;
    }

    public Double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(Double outPrice) {
        this.outPrice = outPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId &&
                Objects.equals(productTypeId, product.productTypeId) &&
                Objects.equals(name, product.name) &&
                Objects.equals(origin, product.origin) &&
                Objects.equals(producer, product.producer) &&
                Objects.equals(unit, product.unit) &&
                Objects.equals(inPrice, product.inPrice) &&
                Objects.equals(outPrice, product.outPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productTypeId, name, origin, producer, unit, inPrice, outPrice);
    }
}
