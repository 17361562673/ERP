/**
 * @ClassName EmpDaoImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 20:27
 * @description
 * @version 1.0
 */
package erp.dao.daoImp;

import erp.dao.EmpDao;
import erp.model.Emp;
import erp.query.EmpQuery;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import java.util.List;

public class EmpDaoImp extends BaseDaoImp<Emp, EmpQuery> implements EmpDao {
    @Override
    public String getHql(EmpQuery empQuery) {
        String hql = "from Emp e where 1=1 ";
        String hqlCondition = createHqlCondition(empQuery);
        hql=hql+hqlCondition+" order by e.empId desc";
        return hql;
    }

    @Override
    public String getHqlCount(EmpQuery empQuery) {
        String hql = "select count(empId) from Emp e where 1=1 ";
        String hqlCondition = createHqlCondition(empQuery);
        hql=hql+hqlCondition;
        return hql;
    }

    @Override
    public String createHqlCondition(EmpQuery query) {
        String hql = "";
        //给hql语句进行动态的拼接
        if (StringUtils.isNotBlank(query.getUsername())) {
            hql = hql + " and e.username like:username";
        }
        if (StringUtils.isNotBlank(query.getName())) {
            hql = hql + " and e.name like:name";
        }
        if (StringUtils.isNotBlank(query.getEmail())) {
            hql = hql + " and e.email like:email";
        }
        if (StringUtils.isNotBlank(query.getTel())) {
            hql = hql + " and e.tel like:tel";
        }
        if (query.getGender() != null) {
            hql = hql + " and e.gender =:gender";
        }
        if (query.getStartBir() != null) {
            hql = hql + " and e.birthday>=:startBir";
        }
        if (query.getEndBir() != null) {
            hql = hql + " and e.birthday<=:endBir";
        }
        if (query.getDepId() != null) {
            //取值的时候要这么取
            hql=hql+" and e.dep.depId=:depId";
        }
        return hql;
    }
    /*查询用户名是否重复*/
    @Override
    public Emp getEmpByUsername(String username) {
        String hql="from Emp e where e.username=?";
        Emp emp=null;
        List<?> list = this.getHibernateTemplate().find(hql, username);
        if (list.size() > 0) {
            emp= (Emp) list.get(0);
        }
        return emp;
    }

    //通过用户名和密码获取用户
    @Override
    public Emp getEmpByusernameAndpassword(String username, String password) {
        String hql="from Emp e where e.username=:username and e.password=:password";
        Emp emp = this.getHibernateTemplate().execute(new HibernateCallback<Emp>() {
            @Override
            public Emp doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                query.setParameter("username",username);
                query.setParameter("password",password);
                return (Emp) query.uniqueResult();
            }
        });
        return emp;
    }
}
