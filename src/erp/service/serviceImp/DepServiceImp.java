
/**
 * @ClassName DepServiceImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:49
 * @description
 * @version 1.0
 */
package erp.service.serviceImp;

import erp.dao.DepDao;
import erp.model.Dep;
import erp.query.DepQuery;
import erp.service.DepService;

public class DepServiceImp extends BaseServiceImp<Dep, DepQuery> implements DepService {
   private DepDao depDao;
    public void setDepDao(DepDao depDao) {
        this.depDao = depDao;
        //当我们使用公用的dao的时候必须要一个具体的dao赋值给baseDao
        this.baseDao = depDao;
    }
}