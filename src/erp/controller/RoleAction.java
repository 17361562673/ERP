/**
 * @ClassName RoleAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/26 22:15
 * @description
 * @version 1.0
 */
package erp.controller;

import com.opensymphony.xwork2.ActionContext;
import erp.model.Menu;
import erp.model.Role;
import erp.query.RoleQuery;
import erp.service.MenuService;
import erp.service.RoleService;
import erp.utils.Page;
import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.*;

public class RoleAction extends BaseAction {
    //角色对象
    private Role role=new Role();
    //角色查询对象
    private RoleQuery query=new RoleQuery();
    public Role getRole() {
        return role;
    }
    private MenuService menuService;

    //被选中的菜单
    private String permIds;

    public String getPermIds() {
        return permIds;
    }

    public void setPermIds(String permIds) {
        this.permIds = permIds;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public RoleQuery getQuery() {
        return query;
    }

    public void setQuery(RoleQuery query) {
        this.query = query;
    }

    private RoleService roleService;

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public String role_list() {
        Integer pageNo = query.getPageNo();
        if (pageNo==null) {
            query.setPageNo(1);
        }
        Page page = roleService.queryObjByCondition(query, exclude);
        ActionContext context = ActionContext.getContext();
        context.put("page",page);
        return SUCCESS;
    }

    public String role_input() {
        return SUCCESS;
    }



    //转发到权限分配页面
    public String role_listPerm() {
        //根据roleId查询角色对象
        Role role1 = roleService.get(query.getRoleId());
        Set<Menu> roleMenus = role1.getMenus();

        //获得系统菜单
        Menu rootMenu= menuService.get(1);
        List<Map<String,Object>> mapList=new ArrayList<>();
        createTreeMenu(rootMenu,mapList,roleMenus);
        //使用json把集合转换成json格式
        JSONArray ja = JSONArray.fromObject(mapList);
        ActionContext context = ActionContext.getContext();
        context.put("zNodes",ja);
        return SUCCESS;
    }
    

    //判断该角色是否已经选中了该菜单(递归创建菜单)
    public void createTreeMenu(Menu menu,List<Map<String,Object>> mapList,Set<Menu> roleMenus) {
        Map<String,Object> map=new HashMap<>();
        if (menu != null) {
            Integer id = menu.getMenuId();
            Integer pId = menu.getParentMenuId();
            String name = menu.getName();
            if (id != 1) {
                map.put("id",id);
                map.put("pId",pId);
                map.put("name",name);
                for (Menu roleMenu : roleMenus) {
                    if (menu.getMenuId().intValue() == roleMenu.getMenuId().intValue()) {
                        map.put("checked",true);
                        map.put("open",true);
                        break;
                    }
                }
                mapList.add(map);
            }
            Set<Menu> menus = menu.getMenus();
            if (menus != null&&menus.size()>0) {
                for (Menu menu1 : menus) {
                    createTreeMenu(menu1,mapList,roleMenus);
                }
            }
        }
    }


    //ajax方式进行真正权限分配
    public void ajax_role_grantPerm() throws IOException {
        roleService.updateGrantPerm(query.getRoleId(),permIds);
        response1.getWriter().write("success");
    }

    //ajax方式保存表单
    public void ajax_role_addRole() throws IOException {
        roleService.save(role);
        response1.getWriter().write("success");
    }

    //跳转到页面修改
    public String role_edit() {
        role=roleService.get(role.getRoleId());
        return SUCCESS;
    }

    //通过ajax方式进行role的修改
    public void ajax_role_editRole() throws IOException {
        roleService.update(role);
        response1.getWriter().write("success");
    }

    //role的删除
    public String role_delete() {
        roleService.delete(role);
        return LIST;
    }
}
