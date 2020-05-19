/**
 * @ClassName Dep
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:29
 * @description
 * @version 1.0
 */
package erp.model;

import java.util.Objects;
import java.util.Set;

public class Dep {
    private Integer depId;
    private String name;
    private String tel;
    //指定和员工一对多的关系
    private Set<Emp> emps;

    public Set<Emp> getEmps() {
        return emps;
    }

    public void setEmps(Set<Emp> emps) {
        this.emps = emps;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dep dep = (Dep) o;
        return depId == dep.depId &&
                Objects.equals(name, dep.name) &&
                Objects.equals(tel, dep.tel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depId, name, tel);
    }
}
