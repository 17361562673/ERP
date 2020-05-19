
/**
 * @ClassName DepQuery
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:30
 * @description
 * @version 1.0
 */
package erp.query;

import erp.model.Product;

public class ProductQuery extends Product {
    //开始行号
    private Integer startNum;
    //页码
    private Integer pageNo;

    //设置供应商id方便进行接收
    private Integer supplierId;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    //设置进货价格的区间
    private Double minInPrice;
    private Double maxInPrice;
    //设置进货价格的区间
    private Double minOutPrice;
    private Double maxOutPrice;

    public Double getMaxInPrice() {
        return maxInPrice;
    }

    public void setMaxInPrice(Double maxInPrice) {
        this.maxInPrice = maxInPrice;
    }

    public Double getMinInPrice() {
        return minInPrice;
    }

    public void setMinInPrice(Double minInPrice) {
        this.minInPrice = minInPrice;
    }

    public Double getMinOutPrice() {
        return minOutPrice;
    }

    public void setMinOutPrice(Double minOutPrice) {
        this.minOutPrice = minOutPrice;
    }

    public Double getMaxOutPrice() {
        return maxOutPrice;
    }

    public void setMaxOutPrice(Double maxOutPrice) {
        this.maxOutPrice = maxOutPrice;
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