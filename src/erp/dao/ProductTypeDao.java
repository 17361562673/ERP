
package erp.dao;

import erp.model.ProductType;
import erp.query.ProductTypeQuery;

public interface ProductTypeDao extends BaseDao<ProductType,ProductTypeQuery>{
    public ProductType getProductTypeBySupplierIdAndName(ProductType pType);
}