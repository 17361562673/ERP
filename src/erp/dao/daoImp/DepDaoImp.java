
/**
 * @ClassName DepDaoImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 20:27
 * @description
 * @version 1.0
 */
package erp.dao.daoImp;

import erp.dao.DepDao;
import erp.model.Dep;
import erp.query.DepQuery;
import org.apache.commons.lang3.StringUtils;

public class DepDaoImp extends BaseDaoImp<Dep, DepQuery> implements DepDao {
    @Override
    public String getHql(DepQuery depQuery) {
        String hql = "from Dep d where 1=1 ";
        String hqlCondition = createHqlCondition(depQuery);
        hql=hql+hqlCondition+" order by d.depId desc";
        return hql;
    }

    @Override
    public String getHqlCount(DepQuery depQuery) {
        String hql = "select count(depId) from Dep d where 1=1 ";
        String hqlCondition = createHqlCondition(depQuery);
        hql=hql+hqlCondition;
        return hql;
    }

    @Override
    public String createHqlCondition(DepQuery depQuery) {
        String hql= "";
        //第一步给hql语句进行动态的拼接
        if (StringUtils.isNotBlank(depQuery.getName())) {
            hql = hql + " and d.name like:name";
        }
        if (StringUtils.isNotBlank(depQuery.getTel())) {
            hql = hql + " and d.tel like:tel";
        }
        return hql;
    }
}