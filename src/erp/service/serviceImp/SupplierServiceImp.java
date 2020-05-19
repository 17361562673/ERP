
/**
 * @ClassName SupplierServiceImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:49
 * @description
 * @version 1.0
 */
package erp.service.serviceImp;

import erp.dao.SupplierDao;
import erp.model.Supplier;
import erp.query.SupplierQuery;
import erp.service.SupplierService;

public class SupplierServiceImp extends BaseServiceImp<Supplier, SupplierQuery> implements SupplierService {
   private SupplierDao supplierDao;
    public void setSupplierDao(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
        //当我们使用公用的dao的时候必须要一个具体的dao赋值给baseDao
        this.baseDao = supplierDao;
    }
}