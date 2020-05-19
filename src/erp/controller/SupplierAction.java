
/**
 * @ClassName SupplierAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/23 23:27
 * @description
 * @version 1.0
 */
package erp.controller;

import com.opensymphony.xwork2.ActionContext;
import erp.model.ProductType;
import erp.model.Supplier;
import erp.query.SupplierQuery;
import erp.service.SupplierService;
import erp.utils.JsonUtils;
import erp.utils.Page;

import java.io.IOException;
import java.util.Set;

public class SupplierAction extends BaseAction{
    //supplierService接口
    private SupplierService supplierService;
    //定义接收对象
    private Supplier supplier=new Supplier();

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    //定义接收查询对象
    private SupplierQuery query=new SupplierQuery();

    public SupplierQuery getQuery() {
        return query;
    }

    public void setQuery(SupplierQuery query) {
        this.query = query;
    }

    
    public String supplier_list() {
        Integer pageNo = query.getPageNo();
        if (pageNo==null) {
            query.setPageNo(1);
        }
        Page page = supplierService.queryObjByCondition(query, exclude);
        //获取ContextMap
        ActionContext context = ActionContext.getContext();
        context.put("page",page);
        return SUCCESS;
    }
  
    public String supplier_input() {
        return SUCCESS;
    }

    //ajax方式添加supplier
    public void ajax_supplier_addSupplier() throws IOException {
        supplierService.save(supplier);
        response1.getWriter().write("success");
    }


    //查询供应商
    public void ajax_supplier_getSupplier() {
        Supplier supplier = supplierService.get(query.getSupplierId());
        //获得供应商下的所有类别(当指定一对多关系后直接就可以查询出来)
        Set<ProductType> productTypes = supplier.getProductTypes();
        //调用printJsonArray回写 这儿必须把supplier这个属性排除掉 不然延迟加载会报错
        JsonUtils.printJsonArray(response1,productTypes,new String[]{"supplier","products"});
    }

    //跳转到供应商修改页面
    public String supplier_edit() {
        supplier=supplierService.get(supplier.getSupplierId());
        return SUCCESS;
    }

    //ajax方式修改供应商
    public void ajax_supplier_editSupplier() throws IOException {
        supplierService.update(supplier);
        response1.getWriter().write("success");
    }

    //供应商删除
    public String supplier_delete() {
        supplierService.delete(supplier.getSupplierId());
        return LIST;
    }
}