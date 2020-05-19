
/**
 * @ClassName ConsoleLogAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/23 23:27
 * @description
 * @version 1.0
 */
package erp.controller;

import com.opensymphony.xwork2.ActionContext;
import erp.model.ConsoleLog;
import erp.query.ConsoleLogQuery;
import erp.service.ConsoleLogService;
import erp.utils.Page;

import java.util.List;

public class ConsoleLogAction extends BaseAction{
    //consoleLogService接口
    private ConsoleLogService consoleLogService;
    public void setConsoleLogService(ConsoleLogService consoleLogService) {
        this.consoleLogService = consoleLogService;
    }

    //定义接收查询对象
    private ConsoleLogQuery query=new ConsoleLogQuery();

    public ConsoleLogQuery getQuery() {
        return query;
    }

    public void setQuery(ConsoleLogQuery query) {
        this.query = query;
    }

    
    public String consoleLog_list() {
        Integer pageNo = query.getPageNo();
        if (pageNo==null) {
            query.setPageNo(1);
        }
        Page page = consoleLogService.queryObjByCondition(query, exclude);
        //获取ContextMap
        ActionContext context = ActionContext.getContext();
        context.put("page",page);
        return SUCCESS;
    }
  
    public String consoleLog_input() {
        return SUCCESS;
    }

    public String consoleLog_consoleLog() {
        List<ConsoleLog> logList = consoleLogService.queryObjByConditionNoPage(query, exclude);
        ActionContext context = ActionContext.getContext();
        context.put("logList",logList);
        return  SUCCESS;
    }
}