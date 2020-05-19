
/**
 * @ClassName StoreDaoImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 20:27
 * @description
 * @version 1.0
 */
package erp.dao.daoImp;

import erp.dao.StoreDao;
import erp.model.Store;
import erp.query.StoreQuery;
import org.apache.commons.lang3.StringUtils;

public class StoreDaoImp extends BaseDaoImp<Store, StoreQuery> implements StoreDao {
    @Override
    public String getHql(StoreQuery storeQuery) {
        String hql = "from Store s where 1=1 ";
        String hqlCondition = createHqlCondition(storeQuery);
        hql=hql+hqlCondition+" order by s.storeId desc";
        return hql;
    }

    @Override
    public String getHqlCount(StoreQuery storeQuery) {
        String hql="select count(storeId) from Store s where 1=1 ";
        String hqlCondition = createHqlCondition(storeQuery);
        hql=hql+hqlCondition;
        return hql;
    }

    @Override
    public String createHqlCondition(StoreQuery storeQuery) {
        String hql="";
        //第一步给hql语句进行动态的拼接
        if (StringUtils.isNotBlank(storeQuery.getName())) {
            hql=hql+" and s.name like:name";
        }
        if (StringUtils.isNotBlank(storeQuery.getAddress())) {
            hql=hql+ " and s.address like:address";
        }
        if (StringUtils.isNotBlank(storeQuery.getStoreAdminName())) {
            hql=hql+ " and s.storeAdmin.name like:storeAdminName";
        }
        return hql;
    }
}