
/**
 * @ClassName ConsoleLogServiceImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:49
 * @description
 * @version 1.0
 */
package erp.service.serviceImp;

import erp.dao.ConsoleLogDao;
import erp.model.ConsoleLog;
import erp.query.ConsoleLogQuery;
import erp.service.ConsoleLogService;

public class ConsoleLogServiceImp extends BaseServiceImp<ConsoleLog, ConsoleLogQuery> implements ConsoleLogService {
   private ConsoleLogDao consoleLogDao;
    public void setConsoleLogDao(ConsoleLogDao consoleLogDao) {
        this.consoleLogDao = consoleLogDao;
        //当我们使用公用的dao的时候必须要一个具体的dao赋值给baseDao
        this.baseDao = consoleLogDao;
    }
}