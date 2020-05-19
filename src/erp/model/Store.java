/**
 * @ClassName Store
 * @Authror zhouzhiqiang
 * @Date 2020/4/3 22:50
 * @description
 * @version 1.0
 */
package erp.model;

import java.util.Objects;
import java.util.Set;

public class Store {
    private Integer storeId;
    private Integer stockman;
    private String name;
    private String address;

    //指定和仓库管理员多对一的关系
    private Emp storeAdmin;

    public Emp getStoreAdmin() {
        return storeAdmin;
    }

    //指定和仓库明细一对多的关系
    private Set<StoreDetail> storeDetails;

    public Set<StoreDetail> getStoreDetails() {
        return storeDetails;
    }

    public void setStoreDetails(Set<StoreDetail> storeDetails) {
        this.storeDetails = storeDetails;
    }

    public void setStoreAdmin(Emp storeAdmin) {
        this.storeAdmin = storeAdmin;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStockman() {
        return stockman;
    }

    public void setStockman(Integer stockman) {
        this.stockman = stockman;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return storeId == store.storeId &&
                Objects.equals(stockman, store.stockman) &&
                Objects.equals(name, store.name) &&
                Objects.equals(address, store.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, stockman, name, address);
    }
}
