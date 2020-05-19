
/**
 * @ClassName OrderDetailServiceImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:49
 * @description
 * @version 1.0
 */
package erp.service.serviceImp;

import erp.dao.OrderDetailDao;
import erp.model.OrderDetail;
import erp.query.OrderDetailQuery;
import erp.service.OrderDetailService;

public class OrderDetailServiceImp extends BaseServiceImp<OrderDetail, OrderDetailQuery> implements OrderDetailService {
   private OrderDetailDao orderDetailDao;
    public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
        //当我们使用公用的dao的时候必须要一个具体的dao赋值给baseDao
        this.baseDao = orderDetailDao;
    }
}