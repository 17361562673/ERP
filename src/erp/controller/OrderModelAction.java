
/**
 * @ClassName OrderModelAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/23 23:27
 * @description
 * @version 1.0
 */
package erp.controller;

import com.opensymphony.xwork2.ActionContext;
import erp.model.*;
import erp.query.OrderModelQuery;
import erp.service.OrderModelService;
import erp.service.ProductService;
import erp.service.SupplierService;
import erp.utils.ERPConstants;
import erp.utils.Page;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderModelAction extends BaseAction {
    //orderModelService接口
    private OrderModelService orderModelService;

    public void setOrderModelService(OrderModelService orderModelService) {
        this.orderModelService = orderModelService;
    }

    //supplierService接口
    private SupplierService supplierService;

    //productService接口
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private Integer[] productTypeId;
    private Integer[] productId;
    private Integer[] detailNum;
    private Double[] detailPrice;
    //设置note做接收
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer[] getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer[] productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer[] getProductId() {
        return productId;
    }

    public void setProductId(Integer[] productId) {
        this.productId = productId;
    }

    public Integer[] getDetailNum() {
        return detailNum;
    }

    public void setDetailNum(Integer[] detailNum) {
        this.detailNum = detailNum;
    }

    public Double[] getDetailPrice() {
        return detailPrice;
    }

    public void setDetailPrice(Double[] detailPrice) {
        this.detailPrice = detailPrice;
    }

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    //设置订单对象
    private OrderModel order = new OrderModel();

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    //定义接收查询对象
    private OrderModelQuery query = new OrderModelQuery();

    public OrderModelQuery getQuery() {
        return query;
    }

    public void setQuery(OrderModelQuery query) {
        this.query = query;
    }


    public String orderModel_list() {
        Integer pageNo = query.getPageNo();
        if (pageNo == null) {
            query.setPageNo(1);
        }
        Page page = orderModelService.queryObjByCondition(query, exclude);
        //获取ContextMap
        ActionContext context = ActionContext.getContext();
        context.put("page", page);
        return SUCCESS;
    }

    public String orderModel_input() {
        List<Supplier> sList = supplierService.list();
        ActionContext context = ActionContext.getContext();
        context.put("sList", sList);
        return SUCCESS;
    }


    public String orderModel_orderDetail() {
        order = orderModelService.get(query.getOrderId());
        return SUCCESS;
    }

    //采购单入库
    public void ajax_orderModel_submitOrder() {
        //设置参数
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        Emp emp = (Emp) session.get("user");
        Supplier supplier = supplierService.get(order.getSupplierId());
        order.setSupplier(supplier);
        order.setOrderNum(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        order.setOrdercreater(emp);
        order.setCreateTime(new Date());
        order.setOrderType(new Integer(ERPConstants.ORDER_TYPE_BUY));
        order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_BUY_AUDIT));
        Set<OrderDetail> ods=new HashSet<>();
        Integer totalNum=0;
        Double totalPrice=0.0;
        for (int i = 0; i < productTypeId.length; i++) {
            OrderDetail od = new OrderDetail();
            od.setDetailNum(detailNum[i]);
            od.setDetailPrice(detailPrice[i]);
            Product product = productService.get(productId[i]);
            od.setProduct(product);
            od.setSurplus(detailNum[i]);
            ods.add(od);
            totalNum=totalNum+detailNum[i];
            totalPrice = totalPrice + detailNum[i]*detailPrice[i];
        }
        order.setTotalNum(totalNum);
        order.setTotalPrice(totalPrice);
        order.setOrderDetails(ods);
        orderModelService.submitOrder(order);
        try {
            response1.getWriter().write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //采购审批list页面
    public String orderModel_checkList() {
        Integer pageNo = query.getPageNo();
        if (pageNo == null) {
            query.setPageNo(1);
        }
        Page page = orderModelService.queryObjByCondition(query, exclude);
        //获取ContextMap
        ActionContext context = ActionContext.getContext();
        context.put("page", page);
        return SUCCESS;
    }

    //单独做页面跳转的
    public String orderModel_auditText() {
        return SUCCESS;
    }

    //做采购审批
    public void ajax_orderModel_auditOrder() throws IOException {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        Emp emp = (Emp) session.get("user");
        orderModelService.updateAuditOrder(emp,order,note);
        response1.getWriter().write("success");
    }

    public String orderModel_update() {
        List<Supplier> sList = supplierService.list();
        ActionContext context = ActionContext.getContext();
        context.put("sList", sList);
        order=orderModelService.get(query.getOrderId());
        return SUCCESS;
    }


    //订单修改
    public void ajax_orderModel_updateOrder() {
        //设置参数
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        Emp emp = (Emp) session.get("user");
        Supplier supplier = supplierService.get(order.getSupplier().getSupplierId());
        order.setSupplier(supplier);
        //order.setOrderNum(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        order.setOrdercreater(emp);
        order.setCreateTime(new Date());
        order.setOrderType(new Integer(ERPConstants.ORDER_TYPE_BUY));
        order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_BUY_AUDIT));
        Set<OrderDetail> ods=new HashSet<>();
        Integer totalNum=0;
        Double totalPrice=0.0;
        for (int i = 0; i < productTypeId.length; i++) {
            OrderDetail od = new OrderDetail();
            od.setDetailNum(detailNum[i]);
            od.setDetailPrice(detailPrice[i]);
            Product product = productService.get(productId[i]);
            od.setProduct(product);
            od.setSurplus(detailNum[i]);
            ods.add(od);
            totalNum=totalNum+detailNum[i];
            totalPrice = totalPrice + detailNum[i] * detailPrice[i];
        }
        order.setTotalNum(totalNum);
        order.setTotalPrice(totalPrice);
        order.setOrderDetails(ods);
        orderModelService.updateOrder(order);

        try {
            response1.getWriter().write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}