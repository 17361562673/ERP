
/**
 * @ClassName ProductTypeDaoImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 20:27
 * @description
 * @version 1.0
 */
package erp.dao.daoImp;

import erp.dao.ProductTypeDao;
import erp.model.ProductType;
import erp.query.ProductTypeQuery;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

public class ProductTypeDaoImp extends BaseDaoImp<ProductType, ProductTypeQuery> implements ProductTypeDao {
    @Override
    public String getHql(ProductTypeQuery productTypeQuery) {
        String hql = "from ProductType p where 1=1 ";
        String hqlCondition = createHqlCondition(productTypeQuery);
        hql=hql+hqlCondition+" order by p.productTypeId desc";
        return hql;
    }

    @Override
    public String getHqlCount(ProductTypeQuery productTypeQuery) {
        String hql="select count(productTypeId) from ProductType p where 1=1 ";
        String hqlCondition = createHqlCondition(productTypeQuery);
        hql=hql+hqlCondition;
        return hql;
    }

    @Override
    public String createHqlCondition(ProductTypeQuery productTypeQuery) {
        String hql="";
        //第一步给hql语句进行动态的拼接
        if (StringUtils.isNotBlank(productTypeQuery.getName())) {
            hql = hql + " and p.name like:name ";
        }
        if (productTypeQuery.getSupplierId() != null) {
            hql=hql+" and p.supplier.supplierId=:supplierId";
        }
        return hql;
    }

    //校验商品供应商和商品类别是否重复
    @Override
    public ProductType getProductTypeBySupplierIdAndName(ProductType pType) {
        String hql="from ProductType p where p.supplier.supplierId=:supplierId and p.name=:name";
        ProductType productType = this.getHibernateTemplate().execute(new HibernateCallback<ProductType>() {
            @Override
            public ProductType doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                query.setParameter("supplierId",pType.getSupplierId());
                query.setParameter("name",pType.getName());
                return (ProductType) query.uniqueResult();
            }
        });
        return productType;
    }
}