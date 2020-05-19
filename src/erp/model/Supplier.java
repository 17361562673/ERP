/**
 * @ClassName Supplier
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 21:02
 * @description
 * @version 1.0
 */
package erp.model;

import java.util.Objects;
import java.util.Set;

public class Supplier {
    private Integer supplierId;
    private String name;
    private String address;
    private String contact;
    private String tel;
    private Integer needs;
    //定义一对多的关系
    private Set<ProductType> productTypes;

    public Set<ProductType> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(Set<ProductType> productTypes) {
        this.productTypes = productTypes;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getNeeds() {
        return needs;
    }

    public void setNeeds(Integer needs) {
        this.needs = needs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return supplierId == supplier.supplierId &&
                Objects.equals(name, supplier.name) &&
                Objects.equals(address, supplier.address) &&
                Objects.equals(contact, supplier.contact) &&
                Objects.equals(tel, supplier.tel) &&
                Objects.equals(needs, supplier.needs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, name, address, contact, tel, needs);
    }
}
