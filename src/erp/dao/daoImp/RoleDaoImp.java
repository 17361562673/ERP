
/**
 * @ClassName RoleDaoImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 20:27
 * @description
 * @version 1.0
 */
package erp.dao.daoImp;

import erp.dao.RoleDao;
import erp.model.Role;
import erp.query.RoleQuery;
import org.apache.commons.lang3.StringUtils;

public class RoleDaoImp extends BaseDaoImp<Role, RoleQuery> implements RoleDao {
    @Override
    public String getHql(RoleQuery roleQuery) {
        String hql = "from Role r where 1=1 ";
        String hqlCondition = createHqlCondition(roleQuery);
        hql = hql + hqlCondition + " order by r.roleId desc";
        return hql;
    }

    @Override
    public String getHqlCount(RoleQuery roleQuery) {
        String hql="select count(roleId) from Role r where 1=1 ";
        String hqlCondition = createHqlCondition(roleQuery);
        hql=hql+hqlCondition;
        return hql;
    }

    @Override
    public String createHqlCondition(RoleQuery roleQuery) {
        String hql="";
        //第一步给hql语句进行动态的拼接
        if (StringUtils.isNotBlank(roleQuery.getName())) {
            hql = hql + " and r.name like:name";
        }
        if (StringUtils.isNotBlank(roleQuery.getCode())) {
            hql = hql + "and r.code like:code";
        }
        return hql;
    }
}