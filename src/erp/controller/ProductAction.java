
/**
 * @ClassName ProductAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/23 23:27
 * @description
 * @version 1.0
 */
package erp.controller;

import com.opensymphony.xwork2.ActionContext;
import erp.model.Product;
import erp.model.Supplier;
import erp.query.ProductQuery;
import erp.service.ProductService;
import erp.service.SupplierService;
import erp.utils.JsonUtils;
import erp.utils.Page;

import java.io.IOException;
import java.util.List;

public class ProductAction extends BaseAction{
    //productService接口
    private ProductService productService;
    //定义supplierService接口注入
    private SupplierService supplierService;
    private Product product=new Product();

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    //定义接收查询对象
    private ProductQuery query=new ProductQuery();

    public ProductQuery getQuery() {
        return query;
    }

    public void setQuery(ProductQuery query) {
        this.query = query;
    }

    public String product_list() {
        List<Supplier> sList = supplierService.list();
        Integer pageNo = query.getPageNo();
        if (pageNo==null) {
            query.setPageNo(1);
        }
       Page page = productService.queryObjByCondition(query, exclude);
        //获取ContextMap
        ActionContext context = ActionContext.getContext();
        context.put("page",page);
        context.put("sList",sList);
        return SUCCESS;
    }
  
    public String product_input() {
        List<Supplier> sList = supplierService.list();
        ActionContext context = ActionContext.getContext();
        context.put("sList",sList);
        return SUCCESS;
    }

    public void ajax_product_getProduct() {
        product = productService.get(query.getProductId());
        JsonUtils.printJsonObj(response1,product,new String[]{"productType"});
    }
    //ajax添加商品
    public void ajax_product_addProduct() {
        productService.save(product);
        try {
            response1.getWriter().write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //商品修改
    public String product_edit() {
        List<Supplier> sList = supplierService.list();
        ActionContext context = ActionContext.getContext();
        context.put("sList",sList);
        product=productService.get(product.getProductId());
        return SUCCESS;
    }

    //ajax修改商品
    public void ajax_product_editProduct() throws IOException {
        productService.update(product);
        response1.getWriter().write("success");
    }

    //商品删除
    public String product_delete() {
        productService.delete(product.getProductId());
        return LIST;
    }
}