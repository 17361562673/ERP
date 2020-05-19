/**
 * @ClassName DepAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/23 23:27
 * @description
 * @version 1.0
 */
package erp.controller;

import com.opensymphony.xwork2.ActionContext;
import erp.model.Dep;
import erp.query.DepQuery;
import erp.service.DepService;
import erp.utils.Page;

import java.io.IOException;

public class DepAction extends BaseAction{
    //depService接口
    private DepService depService;
    public void setDepService(DepService depService) {
        this.depService = depService;
    }
    private Dep dep=new Dep();

    public Dep getDep() {
        return dep;
    }

    public void setDep(Dep dep) {
        this.dep = dep;
    }

    //定义接收查询对象
    private DepQuery query=new DepQuery();

    public DepQuery getQuery() {
        return query;
    }

    public void setQuery(DepQuery query) {
        this.query = query;
    }

    //获取empList的action它要跳转到emp-->list页面
    public String dep_list() {
        Integer pageNo = query.getPageNo();
        if (pageNo==null) {
            query.setPageNo(1);
        }
        Page page = depService.queryObjByCondition(query, exclude);
        //获取ContextMap
        ActionContext context = ActionContext.getContext();
        context.put("page",page);
        return SUCCESS;
    }
    //添加emp的action它要跳转到emp-->input页面
    public String dep_input() {
        return SUCCESS;
    }

    //ajax方式添加部门信息
    public void ajax_dep_addDep() throws IOException {
        depService.save(dep);
        response1.getWriter().write("success");
    }

    //跳转到部门修改页面
    public String dep_edit() {
        dep=depService.get(dep.getDepId());
        return SUCCESS;
    }

    //ajax方式进行dep的修改
    public void ajax_dep_editDep() throws IOException {
        depService.update(dep);
        response1.getWriter().write("success");
    }

    //dep的删除
    public String dep_delete() {
        depService.delete(dep.getDepId());
        return LIST;
    }
}
