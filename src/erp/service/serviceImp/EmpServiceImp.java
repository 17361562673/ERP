/**
 * @ClassName EmpServiceImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 14:26
 * @description
 * @version 1.0
 */
package erp.service.serviceImp;

import erp.dao.EmpDao;
import erp.dao.RoleDao;
import erp.model.Emp;
import erp.model.Role;
import erp.query.EmpQuery;
import erp.service.EmpService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

public class EmpServiceImp extends BaseServiceImp<Emp,EmpQuery> implements EmpService {
    private EmpDao empDao;
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setEmpDao(EmpDao empDao) {
        this.empDao = empDao;
        //当我们使用公用的dao的时候必须要一个具体的dao赋值给baseDao
        this.baseDao=empDao;
    }

    @Override
    public Emp getEmpByUsername(String username) {
        return empDao.getEmpByUsername(username);
    }

    /*@Override
    public void updateEmp(Emp emp) {
              //通过get方法拿到的emp1本身是持久对象
            Emp emp1 = empDao.get(emp.getEmpId());
            //先把传过来的emp的password赋值然后再做更新，这样就不会出现password消失问题
            emp.setPassword(emp1.getPassword());
            //调用empdao把emp进行更新

            //当update的时候emp 也就变成持久对象了 也就是 emp1和emp这两个对象共用了主键就会报错
            empDao.update(emp);
    }*/


    @Override
    public void updateEmp(Emp emp) {
        /*
        * 这儿是一个特别的大坑需要格外注意
         *
         * 两个不同的emp对象不能共用一个主键
         * 用属性拷贝的方法解决
        * */
        Emp emp1 = empDao.get(emp.getEmpId());
        //先把传过来的emp的password赋值然后再做更新，这样就不会出现password消失问题
        //因为emp的password属性为空必须先给上再拷贝 这时候emp1就有emp的所有属性了
        emp.setPassword(emp1.getPassword());
        try {
            //属性拷贝(把emp的所有属性拷贝到emp1)
            BeanUtils.copyProperties(emp1,emp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用empdao把emp进行更新
        empDao.update(emp1);
    }

    @Override
    public List<Role> getStateRoles(Integer empId) {
        //查询所有角色
        List<Role> roleList = roleDao.list();
        Emp emp = empDao.get(empId);
        /*
        * 实现思路 把roleList进行遍历 让后和我们查出来的roles进行对比
        * 如果roleList里面有roles的角色那么就给role加一个标识
        *
        * */
        Set<Role> roles = emp.getRoles();
        //进行对比如果roles里面有roleList里面的角色那么就给role的select属性设置为yes
        for (Role r : roleList) {
            for (Role er : roles) {
                if (r.getRoleId().intValue()==er.getRoleId().intValue()) {
                    r.setSelect("yes");
                }
            }
        }
        return roleList;
    }

    @Override
    public void updateEmpRole(Integer empId, String roleIds) {
        //根据id获得用户
        Emp emp = empDao.get(empId);
        //获得原有用户的角色集合
        Set<Role> roles = emp.getRoles();
        //清理掉原来的角色集合
        roles.clear();
        if (StringUtils.isNotBlank(roleIds)) {
            String[] roleIds1 = roleIds.split(",");
            for (String role : roleIds1) {
                //根据id获得要分配的role对象
                Role role1 = roleDao.get(new Integer(role));
                roles.add(role1);
            }
        }
    }

    @Override
    public Emp getEmpByusernameAndpassword(String username, String password) {
        return empDao.getEmpByusernameAndpassword(username,password);
    }
}
