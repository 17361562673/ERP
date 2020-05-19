/**
 * @ClassName TaskOrderAction
 * @Authror zhouzhiqiang
 * @Date 2020/4/2 17:36
 * @description
 * @version 1.0
 */
package erp.controller;

import com.opensymphony.xwork2.ActionContext;
import erp.model.Dep;
import erp.model.Emp;
import erp.model.OrderModel;
import erp.model.Supplier;
import erp.query.OrderModelQuery;
import erp.service.DepService;
import erp.service.OrderModelService;
import erp.service.ProductService;
import erp.service.SupplierService;
import erp.utils.ERPConstants;
import erp.utils.Page;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TransOrderAction extends BaseAction {
    private OrderModelQuery query = new OrderModelQuery();
    private OrderModel order = new OrderModel();
    private OrderModelService orderModelService;
    private SupplierService supplierService;
    private ProductService productService;
    private DepService depService;

    public void setDepService(DepService depService) {
        this.depService = depService;
    }

    public void setOrderModelService(OrderModelService orderModelService) {
        this.orderModelService = orderModelService;
    }

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public OrderModelQuery getQuery() {
        return query;
    }

    public void setQuery(OrderModelQuery query) {
        this.query = query;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public String transOrder_taskList() {
        Integer pageNo = query.getPageNo();
        if (pageNo == null) {
            query.setPageNo(1);
        }
        Page page = orderModelService.queryObjByCondition(query, exclude);
        List<Supplier> sList = supplierService.list();
        //获取ContextMap
        ActionContext context = ActionContext.getContext();
        context.put("page", page);
        context.put("sList", sList);
        return SUCCESS;
    }

    //跳转到任务指派页面
    public String transOrder_taskDetail() {
        order = orderModelService.get(order.getOrderId());
        Dep dep = depService.get(2);
        ActionContext context = ActionContext.getContext();
        context.put("dep", dep);
        return SUCCESS;
    }

    //任务指派
    public void ajax_transOrder_assginOrder() {
        orderModelService.updateAssignOrder(order);
        try {
            response1.getWriter().write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //做请求转发到运输查询页面
    public String transOrder_tasks() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        Emp emp = (Emp) session.get("user");
        //这儿设置了查询条件只有指派的那个人才可以看到
        query.setCompleter(emp.getEmpId());
        Integer pageNo = query.getPageNo();
        if (pageNo == null) {
            query.setPageNo(1);
        }
        Page page = orderModelService.queryObjByCondition(query, exclude);
        List<Supplier> sList = supplierService.list();
        context.put("page", page);
        context.put("sList", sList);
        return SUCCESS;
    }

    //查询订单然后返回到taskDetailbuying页面
    public String transOrder_taskDetailbuying() {
        order = orderModelService.get(order.getOrderId());
        return SUCCESS;
    }

    //确认去取货
    public void ajax_transOrder_getOrderProduct() throws IOException {
        OrderModel order1 = orderModelService.get(order.getOrderId());
        order1.setOrderState(new Integer(ERPConstants.ORDER_TYPE_TRANS_BUYING));
        orderModelService.update(order1);
        response1.getWriter().write("success");
    }

    //结单
    public void ajax_transOrder_finishTranOrder() throws IOException {
        OrderModel order1 = orderModelService.get(this.order.getOrderId());
        order1.setOrderType(new Integer(ERPConstants.ORDER_TYPE_INSTORAGE));
        order1.setOrderState(new Integer(ERPConstants.ORDER_TYPE_INSTORAGE_WAIT));
        orderModelService.update(order1);
        response1.getWriter().write("success");
    }

    //转发到入库页面
    public String transOrder_inList() {
        ActionContext context = ActionContext.getContext();
        Integer pageNo = query.getPageNo();
        if(pageNo == null ){
            query.setPageNo(1);
        }
        Page page = orderModelService.queryObjByCondition(query, exclude);
        context.put("page",page);
        return "store_success";
    }

    //转发到真正的入库页面
    public String transOrder_inDetail() {
        order= orderModelService.get(query.getOrderId());
        return "store_inDetail";
    }
}
