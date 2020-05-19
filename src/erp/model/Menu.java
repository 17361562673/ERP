/**
 * @ClassName Menu
 * @Authror zhouzhiqiang
 * @Date 2020/4/6 15:32
 * @description
 * @version 1.0
 */
package erp.model;

import java.util.Objects;
import java.util.Set;

public class Menu {
    private Integer menuId;
    private Integer parentMenuId;
    private String name;
    private String url;
    //设置和自己一对多的关系
    private Set<Menu> menus;

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return menuId == menu.menuId &&
                Objects.equals(parentMenuId, menu.parentMenuId) &&
                Objects.equals(name, menu.name) &&
                Objects.equals(url, menu.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, parentMenuId, name, url);
    }
}
