
package erp.service;

import erp.model.Store;
import erp.query.StoreQuery;

public interface StoreService extends BaseService<Store,StoreQuery>{
    public void updateInstock(Integer storeId,Integer productId,Integer productNum,Integer orderDetailId);
}