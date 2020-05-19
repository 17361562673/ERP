package erp.service;

import erp.model.Emp;
import erp.model.Role;
import erp.query.EmpQuery;

import java.util.List;

public interface EmpService extends BaseService<Emp,EmpQuery>{
    //通过用户名获取用户
    public Emp getEmpByUsername(String username);

    //如果调用basedao里的update方法会导致在更新的时候密码消失
    // 因为给后台不传password所以更新的时候就不会包含password(很严重的问题)所以它必须有个性化的update方法
    public void updateEmp(Emp emp);
    //获得所有的角色
    public List<Role> getStateRoles(Integer empId);

    //分配角色
    public void updateEmpRole(Integer empId,String roleIds);


    public Emp getEmpByusernameAndpassword(String username,String password);
}
