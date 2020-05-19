
/**
 * @ClassName SupplierDaoImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 20:27
 * @description
 * @version 1.0
 */
package erp.dao.daoImp;

import erp.dao.SupplierDao;
import erp.model.Supplier;
import erp.query.SupplierQuery;
import org.apache.commons.lang3.StringUtils;

public class SupplierDaoImp extends BaseDaoImp<Supplier, SupplierQuery> implements SupplierDao {
    @Override
    public String getHql(SupplierQuery supplierQuery) {
        String hql = "from Supplier s where 1=1 ";
        String hqlCondition = createHqlCondition(supplierQuery);
        hql=hql+hqlCondition+" order by s.supplierId desc";
        return hql;
    }

    @Override
    public String getHqlCount(SupplierQuery supplierQuery) {
        String hql="select count(supplierId) from Supplier s where 1=1";
        String hqlCondition = createHqlCondition(supplierQuery);
        hql=hql+hqlCondition;
        return hql;
    }
    @Override
    public String createHqlCondition(SupplierQuery supplierQuery) {
        String hql="";
        //第一步给hql语句进行动态的拼接
        if (StringUtils.isNotBlank(supplierQuery.getName())) {
            hql = hql + " and s.name like:name";
        }
        if (StringUtils.isNotBlank(supplierQuery.getContact())) {
            hql = hql + " and s.contact like:contact";
        }
        if (StringUtils.isNotBlank(supplierQuery.getTel())) {
            hql = hql + " and s.tel like:tel";
        }
        if (supplierQuery.getNeeds() != null) {
            hql=hql+" and s.needs=:needs";
        }
        return hql;
    }
}