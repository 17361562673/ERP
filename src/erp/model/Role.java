/**
 * @ClassName Role
 * @Authror zhouzhiqiang
 * @Date 2020/3/26 21:55
 * @description
 * @version 1.0
 */
package erp.model;

import java.util.Objects;
import java.util.Set;

public class Role {
    private Integer roleId;
    private String name;
    private String code;

    //是否选中的标识
    private String select;

    //设置和菜单之间多对多的关系
    private Set<Menu> menus;

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId == role.roleId &&
                Objects.equals(name, role.name) &&
                Objects.equals(code, role.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, name, code);
    }
}
