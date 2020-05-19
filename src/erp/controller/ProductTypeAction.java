
/**
 * @ClassName ProductTypeAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/23 23:27
 * @description
 * @version 1.0
 */
package erp.controller;

import com.opensymphony.xwork2.ActionContext;
import erp.model.Product;
import erp.model.ProductType;
import erp.model.Supplier;
import erp.query.ProductTypeQuery;
import erp.service.ProductTypeService;
import erp.service.SupplierService;
import erp.utils.JsonUtils;
import erp.utils.Page;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ProductTypeAction extends BaseAction {
    //productTypeService接口注入
    private ProductTypeService productTypeService;
    //定义supplierService接口注入
    private SupplierService supplierService;

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public void setProductTypeService(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    //定义接收查询对象
    private ProductTypeQuery query = new ProductTypeQuery();
    //定义接收对象
    private ProductType pType = new ProductType();

    public ProductType getpType() {
        return pType;
    }

    public void setpType(ProductType pType) {
        this.pType = pType;
    }

    public ProductTypeQuery getQuery() {
        return query;
    }

    public void setQuery(ProductTypeQuery query) {
        this.query = query;
    }

    //list的action
    public String productType_list() {
        List<Supplier> sList = supplierService.list();
        Integer pageNo = query.getPageNo();
        if (pageNo == null) {
            query.setPageNo(1);
        }
        Page page = productTypeService.queryObjByCondition(query, exclude);
        //获取ContextMap
        ActionContext context = ActionContext.getContext();
        context.put("page", page);
        context.put("sList", sList);
        return SUCCESS;
    }

    //input的action
    public String productType_input() {
        List<Supplier> sList = supplierService.list();
        ActionContext context = ActionContext.getContext();
        context.put("sList", sList);
        return SUCCESS;
    }

    //用户添加的action
    public void ajax_productType_add() {
        productTypeService.save(pType);
        try {
            response1.getWriter().write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //校验商品供应商和商品是否已经重复
    public void ajax_productType_valid() {
        String result = "yes";
        ProductType productType = productTypeService.getProductTypeBySupplierIdAndName(pType);
        if (productType != null) {
            result = "no";
        } else {
            try {
                response1.getWriter().write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //得到商品
    public void ajax_productType_getProduct() {
        ProductType productType = productTypeService.get(query.getProductTypeId());
        //得到商品因为指定了和商品一对多的关系
        Set<Product> products = productType.getProducts();
        //这儿必须把productType这个属性排除掉 不然延迟加载会报错
        JsonUtils.printJsonArray(response1, products, new String[]{"productType"});
    }

    //跳转到页面编辑
    public String productType_edit() {
        List<Supplier> sList = supplierService.list();
        ActionContext context = ActionContext.getContext();
        context.put("sList", sList);
        pType = productTypeService.get(pType.getProductTypeId());
        return SUCCESS;
    }

    //通过ajax修改商品类别
    public void ajax_productType_edit() throws IOException {
        productTypeService.update(pType);
        response1.getWriter().write("success");
    }

    //删除商品类别
    public String productType_delete() {
        productTypeService.delete(pType);
        return LIST;
    }
}