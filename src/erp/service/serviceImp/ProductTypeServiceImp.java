
/**
 * @ClassName ProductTypeServiceImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:49
 * @description
 * @version 1.0
 */
package erp.service.serviceImp;

import erp.dao.ProductTypeDao;
import erp.model.ProductType;
import erp.query.ProductTypeQuery;
import erp.service.ProductTypeService;

public class ProductTypeServiceImp extends BaseServiceImp<ProductType, ProductTypeQuery> implements ProductTypeService {
   private ProductTypeDao productTypeDao;
    public void setProductTypeDao(ProductTypeDao productTypeDao) {
        this.productTypeDao = productTypeDao;
        //当我们使用公用的dao的时候必须要一个具体的dao赋值给baseDao
        this.baseDao = productTypeDao;
    }

    @Override
    public ProductType getProductTypeBySupplierIdAndName(ProductType pType) {
        return productTypeDao.getProductTypeBySupplierIdAndName(pType);
    }
}