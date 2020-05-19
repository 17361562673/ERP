
/**
 * @ClassName OrderModelDaoImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 20:27
 * @description
 * @version 1.0
 */
package erp.dao.daoImp;

import erp.dao.OrderModelDao;
import erp.model.OrderModel;
import erp.query.OrderModelQuery;
import org.apache.commons.lang3.StringUtils;

public class OrderModelDaoImp extends BaseDaoImp<OrderModel, OrderModelQuery> implements OrderModelDao {
    @Override
    public String getHql(OrderModelQuery orderModelQuery) {
        String hql = "from OrderModel o where 1=1 ";
        String hqlCondition = createHqlCondition(orderModelQuery);
        hql=hql+hqlCondition+" order by o.orderId desc";
        return hql;
    }

    @Override
    public String getHqlCount(OrderModelQuery orderModelQuery) {
        String hql = "select count(orderId) from OrderModel o where 1=1 ";
        String hqlCondition = createHqlCondition(orderModelQuery);
        hql=hql+hqlCondition;
        return hql;
    }

    @Override
    public String createHqlCondition(OrderModelQuery orderModelQuery) {
        String hql="";
        //第一步给hql语句进行动态的拼接
        if (StringUtils.isNotBlank(orderModelQuery.getCreateName())) {
            hql = hql + " and o.ordercreater.name like:createName";
        }
        if (orderModelQuery.getMinTotalNum() != null) {
            hql=hql+" and o.totalNum>=:minTotalNum";
        }
        if (orderModelQuery.getMaxTotalNum() != null) {
            hql=hql+" and o.totalNum<=:maxTotalNum";
        }
        if (orderModelQuery.getMinCreateTime() != null) {
            hql=hql+" and o.createTime>=:minCreateTime";
        }
        if (orderModelQuery.getMaxCreateTime() != null) {
            hql=hql+" and o.createTime<=:maxCreateTime";
        }
        if (orderModelQuery.getMinTotalPrice() != null) {
            hql=hql+" and o.totalPrice>=:minTotalPrice";
        }
        if (orderModelQuery.getMaxTotalPrice() != null) {
            hql=hql+" and o.totalPrice<=:maxTotalPrice";
        }
        if (orderModelQuery.getOrderType() != null) {
            hql=hql+" and o.orderType=:orderType";
        }
        if (orderModelQuery.getOrderState() != null) {
            hql=hql+" and o.orderState=:orderState";
        }



        if (orderModelQuery.getSupplierId() != null) {
            hql=hql+" and o.supplier.supplierId=:supplierId";
        }
        if (StringUtils.isNotBlank(orderModelQuery.getOrderCreate())) {
            hql=hql+" and o.ordercreater.name like:orderCreate";
        }
        if (orderModelQuery.getMinCheckTime() != null) {
            hql=hql+" and o.checkTime >=:minCheckTime";
        }
        if (orderModelQuery.getMaxCheckTime() != null) {
            hql=hql+" and o.checkTime <=:maxCheckTime";
        }
        if (orderModelQuery.getNeeds() != null) {
            hql=hql+" and o.supplier.needs=:needs";
        }
        if (StringUtils.isNotBlank(orderModelQuery.getOrderCheckerName())) {
            hql=hql+" and o.orderChecker.name like :orderCheckerName";
        }
        if(orderModelQuery.getCompleter() != null){
            hql = hql + " and o.orderCompleter.empId = :completer";
        }
        return hql;
    }
}