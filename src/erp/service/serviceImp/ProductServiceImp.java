
/**
 * @ClassName ProductServiceImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:49
 * @description
 * @version 1.0
 */
package erp.service.serviceImp;

import erp.dao.ProductDao;
import erp.model.Product;
import erp.query.ProductQuery;
import erp.service.ProductService;

public class ProductServiceImp extends BaseServiceImp<Product, ProductQuery> implements ProductService {
   private ProductDao productDao;
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
        //当我们使用公用的dao的时候必须要一个具体的dao赋值给baseDao
        this.baseDao = productDao;
    }
}