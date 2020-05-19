package erp.dao;

import erp.model.Emp;
import erp.query.EmpQuery;

public interface EmpDao extends BaseDao<Emp,EmpQuery>{
    public Emp getEmpByUsername(String username);
    //通过username和password获取emp
    public Emp getEmpByusernameAndpassword(String username,String password);
}
