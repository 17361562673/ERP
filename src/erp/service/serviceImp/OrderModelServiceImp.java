
/**
 * @ClassName OrderModelServiceImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:49
 * @description
 * @version 1.0
 */
package erp.service.serviceImp;

import erp.dao.ConsoleLogDao;
import erp.dao.OrderDetailDao;
import erp.dao.OrderModelDao;
import erp.model.ConsoleLog;
import erp.model.Emp;
import erp.model.OrderModel;
import erp.query.OrderModelQuery;
import erp.service.OrderModelService;
import erp.utils.ERPConstants;

import java.sql.Timestamp;
import java.util.Date;

public class OrderModelServiceImp extends BaseServiceImp<OrderModel, OrderModelQuery> implements OrderModelService {
   private OrderModelDao orderModelDao;
   private ConsoleLogDao consoleLogDao;
   private OrderDetailDao orderDetailDao;

    public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
    }

    public void setConsoleLogDao(ConsoleLogDao consoleLogDao) {
        this.consoleLogDao = consoleLogDao;
    }

    public void setOrderModelDao(OrderModelDao orderModelDao) {
        this.orderModelDao = orderModelDao;
        //当我们使用公用的dao的时候必须要一个具体的dao赋值给baseDao
        this.baseDao = orderModelDao;
    }

    @Override
    public void submitOrder(OrderModel orderModel) {
        orderModelDao.save(orderModel);
    }

    @Override
    public void updateAuditOrder(Emp checker,OrderModel orderModel, String note) {
        OrderModel order = orderModelDao.get(orderModel.getOrderId());
        order.setOrderState(orderModel.getOrderState());
        order.setCheckTime(new Date());
        //当设置了多对一的关系后才能set上
        order.setOrderChecker(checker);
        ConsoleLog cl = new ConsoleLog();
        cl.setEmp(checker);
        cl.setEntityId(orderModel.getOrderId());
        cl.setNote(note);
        cl.setOptTime(new Timestamp(new Date().getTime()));
        cl.setOptType("审核订单");
        cl.setTableName("orderModel");
        consoleLogDao.save(cl);
    }

    @Override
    public void updateOrder(OrderModel orderModel) {
        orderDetailDao.deleteOrderTailByOrderId(orderModel.getOrderId());
        orderModelDao.update(orderModel);
    }

    @Override
    public void updateAssignOrder(OrderModel orderModel) {
        OrderModel order = orderModelDao.get(orderModel.getOrderId());

        order.setOrderCompleter(orderModel.getOrderCompleter());

        order.setOrderType(new Integer(ERPConstants.ORDER_TYPE_TRANS));
        order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_TRANS_BUY));
        //不需要再修改事务提交的时候会自动的修改
    }
}