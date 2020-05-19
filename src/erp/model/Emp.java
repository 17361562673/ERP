/**
 * @ClassName Emp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 14:21
 * @description
 * @version 1.0
 */
package erp.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class Emp implements java.io.Serializable{
    private Integer empId;
    private Integer depId;
    private String name;
    private String username;
    private String email;
    private String tel;
    private Integer gender;
    private String address;
    private Date birthday;
    private String password;
    //设置多对一的关系
    //从员工角度看，员工和部门是多对一的关系
    private Dep dep;


    //设置员工和角色多对多的关系
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Dep getDep() {
        return dep;
    }

    public void setDep(Dep dep) {
        this.dep = dep;
    }

  public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return empId == emp.empId &&
                Objects.equals(depId, emp.depId) &&
                Objects.equals(name, emp.name) &&
                Objects.equals(username, emp.username) &&
                Objects.equals(email, emp.email) &&
                Objects.equals(tel, emp.tel) &&
                Objects.equals(gender, emp.gender) &&
                Objects.equals(address, emp.address) &&
                Objects.equals(birthday, emp.birthday) &&
                Objects.equals(password, emp.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, depId, name, username, email, tel, gender, address, birthday, password);
    }
}
