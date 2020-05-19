
/**
 * @ClassName StoreDetailDaoImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 20:27
 * @description
 * @version 1.0
 */
package erp.dao.daoImp;

import erp.dao.StoreDetailDao;
import erp.model.StoreDetail;
import erp.query.StoreDetailQuery;

public class StoreDetailDaoImp extends BaseDaoImp<StoreDetail, StoreDetailQuery> implements StoreDetailDao {
    @Override
    public String getHql(StoreDetailQuery storeDetailQuery) {
        String hql = "from StoreDetail where 1=1 ";

        return hql;
    }

    @Override
    public String getHqlCount(StoreDetailQuery storeDetailQuery) {
        return null;
    }

    @Override
    public String createHqlCondition(StoreDetailQuery storeDetailQuery) {
        String hql="";
        //第一步给hql语句进行动态的拼接
       /* if (StringUtils.isNotBlank(storeDetailQuery.getStartNum())) {
            hql = hql + "and ";
        }
        if (StringUtils.isNotBlank(storeDetailQuery)) {
            hql = hql + "and ";
        }
        if (StringUtils.isNotBlank(storeDetailQuery)) {
            hql = hql + "and ";
        }
        if (StringUtils.isNotBlank(storeDetailQuery)) {
            hql = hql + "and ";
        }*/
        return null;
    }
}