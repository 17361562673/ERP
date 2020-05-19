
/**
 * @ClassName OrderDetailDaoImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 20:27
 * @description
 * @version 1.0
 */
package erp.dao.daoImp;

import erp.dao.OrderDetailDao;
import erp.model.OrderDetail;
import erp.query.OrderDetailQuery;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

public class OrderDetailDaoImp extends BaseDaoImp<OrderDetail, OrderDetailQuery> implements OrderDetailDao {
    @Override
    public String getHql(OrderDetailQuery orderDetailQuery) {
        String hql = "from OrderDetail where 1=1 ";

        return hql;
    }

    @Override
    public String getHqlCount(OrderDetailQuery orderDetailQuery) {
        return null;
    }

    @Override
    public String createHqlCondition(OrderDetailQuery orderDetailQuery) {
        String hql = "";
        //第一步给hql语句进行动态的拼接
     /*   if (StringUtils.isNotBlank(orderDetailQuery)) {
            hql = hql + "and ";
        }
        if (StringUtils.isNotBlank(orderDetailQuery)) {
            hql = hql + "and ";
        }
        if (StringUtils.isNotBlank(orderDetailQuery)) {
            hql = hql + "and ";
        }
        if (StringUtils.isNotBlank(orderDetailQuery)) {
            hql = hql + "and ";
        }*/
        return null;
    }

    @Override
    public void deleteOrderTailByOrderId(Integer orderId) {
        String hql = "delete from OrderDetail o where o.orderId=:orderId";
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                /*
                * 这儿的orderId要处理一下，因为orderDetail的orderId是String类型的所以要进行转换
                * */
                query.setParameter("orderId",String.valueOf(orderId));
                query.executeUpdate();
                return null;
            }
        });
    }
}