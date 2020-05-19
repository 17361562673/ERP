
/**
 * @ClassName ProductDaoImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 20:27
 * @description
 * @version 1.0
 */
package erp.dao.daoImp;

import erp.dao.ProductDao;
import erp.model.Product;
import erp.query.ProductQuery;
import org.apache.commons.lang3.StringUtils;

public class ProductDaoImp extends BaseDaoImp<Product, ProductQuery> implements ProductDao {
    @Override
    public String getHql(ProductQuery productQuery) {
        String hql = "from Product p where 1=1 ";
        String hqlCondition = createHqlCondition(productQuery);
        hql=hql+hqlCondition+" order by p.productId desc";
        return hql;
    }

    @Override
    public String getHqlCount(ProductQuery productQuery) {
        String hql = "select count(productId) from Product p where 1=1 ";
        String hqlCondition = createHqlCondition(productQuery);
        hql=hql+hqlCondition;
        return hql;
    }

    @Override
    public String createHqlCondition(ProductQuery productQuery) {
        String hql="";
        //第一步给hql语句进行动态的拼接
        if (productQuery.getSupplierId() != null) {
            hql=hql+" and p.productType.supplier.supplierId=:supplierId";
        }
        if (StringUtils.isNotBlank(productQuery.getName())) {
            hql = hql + " and p.name like:name ";
        }
        if (StringUtils.isNotBlank(productQuery.getProducer())) {
            hql = hql + " and p.producer like:producer";
        }
        if (StringUtils.isNotBlank(productQuery.getUnit())) {
            hql = hql + " and p.unit like:unit";
        }
        if (productQuery.getMinInPrice() != null) {
            hql=hql+" and p.inPrice >=:minInPrice";
        }
        if (productQuery.getMaxInPrice() != null) {
            hql=hql+" and p.inPrice <=:maxInPrice";
        }
        if (productQuery.getMinOutPrice() != null) {
            hql=hql+" and p.outPrice >=:minOutPrice";
        }
        if (productQuery.getMaxOutPrice() != null) {
            hql=hql+" and p.outPrice <=:maxOutPrice";
        }
        return hql;
    }
}