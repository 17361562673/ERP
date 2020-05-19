
/**
 * @ClassName StoreAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/23 23:27
 * @description
 * @version 1.0
 */
package erp.controller;

import com.opensymphony.xwork2.ActionContext;
import erp.model.Store;
import erp.model.StoreDetail;
import erp.query.StoreQuery;
import erp.service.OrderModelService;
import erp.service.StoreService;
import erp.utils.Page;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class StoreAction extends BaseAction {
    //storeService接口
    private StoreService storeService;

    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    private OrderModelService orderModelService;

    public void setOrderModelService(OrderModelService orderModelService) {
        this.orderModelService = orderModelService;
    }

    //定义接收查询对象
    private StoreQuery query = new StoreQuery();

    //定义入库商品的数量
    private Integer productNum;
    //定义商品id用来接收
    private Integer productId;
    //定义订单明细id用来接收
    private Integer orderDetailId;

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public StoreQuery getQuery() {
        return query;
    }

    public void setQuery(StoreQuery query) {
        this.query = query;
    }


    public String store_sList() {
        Integer pageNo = query.getPageNo();
        if (pageNo == null) {
            query.setPageNo(1);
        }
        Page page = storeService.queryObjByCondition(query, exclude);
        //获取ContextMap
        ActionContext context = ActionContext.getContext();
        context.put("page", page);
        return SUCCESS;
    }

    public String store_input() {
        return SUCCESS;
    }

    //查看仓库明细
    public String store_storeDetail() {
        Store store = storeService.get(query.getStoreId());
        //通过仓库和仓库明细一对多的关系就可以直接拿到
        Set<StoreDetail> storeDetails = store.getStoreDetails();
        ActionContext context = ActionContext.getContext();
        context.put("details", storeDetails);
        return SUCCESS;
    }


    //转发到入库页面
    public String store_inStock() {
        List<Store> sList = storeService.list();
        ActionContext context = ActionContext.getContext();
        context.put("sList",sList);
        return SUCCESS;
    }

    //通过ajax做真正的入库操作
    public void ajax_store_inStock() throws IOException {
        storeService.updateInstock(query.getStoreId(),productId,productNum,orderDetailId);
        response1.getWriter().write("success");
    }
}