
/**
 * @ClassName StoreDetailServiceImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:49
 * @description
 * @version 1.0
 */
package erp.service.serviceImp;

import erp.dao.StoreDetailDao;
import erp.model.StoreDetail;
import erp.query.StoreDetailQuery;
import erp.service.StoreDetailService;

public class StoreDetailServiceImp extends BaseServiceImp<StoreDetail, StoreDetailQuery> implements StoreDetailService {
   private StoreDetailDao storeDetailDao;
    public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
        this.storeDetailDao = storeDetailDao;
        //当我们使用公用的dao的时候必须要一个具体的dao赋值给baseDao
        this.baseDao = storeDetailDao;
    }
}