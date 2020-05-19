
/**
 * @ClassName StoreDetailAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/23 23:27
 * @description
 * @version 1.0
 */
package erp.controller;

import com.opensymphony.xwork2.ActionContext;
import erp.query.StoreDetailQuery;
import erp.service.StoreDetailService;
import erp.utils.Page;

public class StoreDetailAction extends BaseAction{
    //storeDetailService接口
    private StoreDetailService storeDetailService;
    public void setStoreDetailService(StoreDetailService storeDetailService) {
        this.storeDetailService = storeDetailService;
    }

    //定义接收查询对象
    private StoreDetailQuery query=new StoreDetailQuery();

    public StoreDetailQuery getQuery() {
        return query;
    }

    public void setQuery(StoreDetailQuery query) {
        this.query = query;
    }

    
    public String storeDetail_list() {
        Integer pageNo = query.getPageNo();
        if (pageNo==null) {
            query.setPageNo(1);
        }
        Page page = storeDetailService.queryObjByCondition(query, exclude);
        //获取ContextMap
        ActionContext context = ActionContext.getContext();
        context.put("page",page);
        return SUCCESS;
    }
  
    public String storeDetail_input() {
        return SUCCESS;
    }
}