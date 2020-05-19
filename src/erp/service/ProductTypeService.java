
package erp.service;

import erp.model.ProductType;
import erp.query.ProductTypeQuery;

public interface ProductTypeService extends BaseService<ProductType,ProductTypeQuery>{
    public ProductType getProductTypeBySupplierIdAndName(ProductType pType);
}