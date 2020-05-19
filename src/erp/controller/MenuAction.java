
/**
 * @ClassName MenuAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/23 23:27
 * @description
 * @version 1.0
 */
package erp.controller;

import com.opensymphony.xwork2.ActionContext;
import erp.query.MenuQuery;
import erp.service.MenuService;
import erp.utils.Page;

public class MenuAction extends BaseAction{
    //menuService接口
    private MenuService menuService;
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    //定义接收查询对象
    private MenuQuery query=new MenuQuery();

    public MenuQuery getQuery() {
        return query;
    }

    public void setQuery(MenuQuery query) {
        this.query = query;
    }

    
    public String menu_list() {
        Integer pageNo = query.getPageNo();
        if (pageNo==null) {
            query.setPageNo(1);
        }
        Page page = menuService.queryObjByCondition(query, exclude);
        //获取ContextMap
        ActionContext context = ActionContext.getContext();
        context.put("page",page);
        return SUCCESS;
    }
  
    public String menu_input() {
        return SUCCESS;
    }
}