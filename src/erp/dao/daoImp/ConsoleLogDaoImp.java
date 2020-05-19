
/**
 * @ClassName ConsoleLogDaoImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 20:27
 * @description
 * @version 1.0
 */
package erp.dao.daoImp;

import erp.dao.ConsoleLogDao;
import erp.model.ConsoleLog;
import erp.query.ConsoleLogQuery;

public class ConsoleLogDaoImp extends BaseDaoImp<ConsoleLog, ConsoleLogQuery> implements ConsoleLogDao {
    @Override
    public String getHql(ConsoleLogQuery consoleLogQuery) {
        String hql = "from ConsoleLog c where 1=1 ";
        String hqlCondition = createHqlCondition(consoleLogQuery);
        hql=hql+hqlCondition+" order by c.logId desc";
        return hql;
    }

    @Override
    public String getHqlCount(ConsoleLogQuery consoleLogQuery) {
        String hql="";
        String hqlCondition = createHqlCondition(consoleLogQuery);
        hql=hql+hqlCondition;
        return hql;
    }

    @Override
    public String createHqlCondition(ConsoleLogQuery consoleLogQuery) {
        String hql = " and c.entityId = :entityId and c.tableName like :tableName and c.optType like :optType";
        return hql;
    }
}