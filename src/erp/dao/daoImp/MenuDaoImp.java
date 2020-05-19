
/**
 * @ClassName MenuDaoImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 20:27
 * @description
 * @version 1.0
 */
package erp.dao.daoImp;

import erp.dao.MenuDao;
import erp.model.Menu;
import erp.query.MenuQuery;

public class MenuDaoImp extends BaseDaoImp<Menu, MenuQuery> implements MenuDao {
    @Override
    public String getHql(MenuQuery menuQuery) {
        String hql = "from Menu where 1=1 ";
        return hql;
    }

    @Override
    public String getHqlCount(MenuQuery menuQuery) {
        String hql="";
        return hql;
    }

    @Override
    public String createHqlCondition(MenuQuery menuQuery) {
        String hql="";
        //第一步给hql语句进行动态的拼接
     /*   if (StringUtils.isNotBlank(menuQuery)) {
            hql = hql + "and ";
        }
        if (StringUtils.isNotBlank(menuQuery)) {
            hql = hql + "and ";
        }
        if (StringUtils.isNotBlank(menuQuery)) {
            hql = hql + "and ";
        }
        if (StringUtils.isNotBlank(menuQuery)) {
            hql = hql + "and ";
        }*/
        return hql;
    }
}