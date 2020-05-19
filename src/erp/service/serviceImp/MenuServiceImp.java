
/**
 * @ClassName MenuServiceImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:49
 * @description
 * @version 1.0
 */
package erp.service.serviceImp;

import erp.dao.MenuDao;
import erp.model.Menu;
import erp.query.MenuQuery;
import erp.service.MenuService;

public class MenuServiceImp extends BaseServiceImp<Menu, MenuQuery> implements MenuService {
   private MenuDao menuDao;
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
        //当我们使用公用的dao的时候必须要一个具体的dao赋值给baseDao
        this.baseDao = menuDao;
    }
}