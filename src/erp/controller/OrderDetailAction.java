
/**
 * @ClassName OrderDetailAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/23 23:27
 * @description
 * @version 1.0
 */
package erp.controller;

import erp.query.OrderDetailQuery;
import erp.service.OrderDetailService;

public class OrderDetailAction extends BaseAction{
    //orderDetailService接口
    private OrderDetailService orderDetailService;
    public void setOrderDetailService(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    //定义接收查询对象
    private OrderDetailQuery query=new OrderDetailQuery();

    public OrderDetailQuery getQuery() {
        return query;
    }

    public void setQuery(OrderDetailQuery query) {
        this.query = query;
    }

    
    public String orderDetail_list() {
        Integer pageNo = query.getPageNo();
        if (pageNo==null) {
            query.setPageNo(1);
        }
      /*  Page page = orderDetailService.queryObjByCondition(query, exclude);
        //获取ContextMap
        ActionContext context = ActionContext.getContext();
        context.put("page",page);*/
        return SUCCESS;
    }
}