
package erp.service;

import erp.model.Emp;
import erp.model.OrderModel;
import erp.query.OrderModelQuery;

public interface OrderModelService extends BaseService<OrderModel,OrderModelQuery>{
    public void submitOrder(OrderModel orderModel);
    public void updateAuditOrder(Emp checker, OrderModel orderModel, String note);
    public void updateOrder(OrderModel orderModel);
    //订单指派
    public void updateAssignOrder(OrderModel orderModel);
}