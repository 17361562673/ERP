
/**
 * @ClassName RoleServiceImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:49
 * @description
 * @version 1.0
 */
package erp.service.serviceImp;

import erp.dao.MenuDao;
import erp.dao.RoleDao;
import erp.model.Menu;
import erp.model.Role;
import erp.query.RoleQuery;
import erp.service.RoleService;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class RoleServiceImp extends BaseServiceImp<Role, RoleQuery> implements RoleService {
   private RoleDao roleDao;
   private MenuDao menuDao;

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
        //当我们使用公用的dao的时候必须要一个具体的dao赋值给baseDao
        this.baseDao = roleDao;
    }

    //真正进行权限分配的
    @Override
    public void updateGrantPerm(Integer roleId, String permIds) {
        Role role = roleDao.get(roleId);
        //通过多对多关系获得菜单
        Set<Menu> menus = role.getMenus();
        //首先将这个role拥有的menu置空
        menus.clear();
        if (StringUtils.isNotBlank(permIds)) {
            String[] menuIds = permIds.split(",");
            for (String menuId : menuIds) {
                Menu menu = menuDao.get(new Integer(menuId));
                menus.add(menu);
            }
        }
    }
}