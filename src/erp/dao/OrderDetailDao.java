
package erp.dao;

import erp.model.OrderDetail;
import erp.query.OrderDetailQuery;

public interface OrderDetailDao extends BaseDao<OrderDetail,OrderDetailQuery>{
    public void deleteOrderTailByOrderId(Integer orderId);
}