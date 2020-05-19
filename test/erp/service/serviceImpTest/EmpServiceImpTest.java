
/**
 * @ClassName EmpServiceImpTest
 * @Authror zhouzhiqiang
 * @Date 2020/3/21 0:44
 * @description
 * @version 1.0
 */
package erp.service.serviceImpTest;

import erp.model.Emp;
import erp.query.EmpQuery;
import erp.service.EmpService;
import erp.utils.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class EmpServiceImpTest {
    @Autowired
    private EmpService empService;
    @Test
    public void testSaveEmp() {
        Emp emp = new Emp();
        emp.setUsername("zzq");
        emp.setName("张国荣");
        emp.setTel("17361562673");
        emp.setEmail("17361562673@163.com");
        emp.setGender(1);
        emp.setBirthday(new Date());
        emp.setAddress("北京");
        emp.setPassword("13241564");
        empService.save(emp);
    }
    @Test
    public void testDeleteEmp() {

    }
    @Test
    public void testUpdateEmp() {

    }
    @Test
    public void testGetEmp() {
        Emp emp = empService.get(9);
        System.out.println(emp);
    }
    @Test
    public void testQueryEmpByCondition() {
        EmpQuery empQuery = new EmpQuery();
        List<String> exclude=new ArrayList<>();
        empQuery.setPageNo(1);
        exclude.add("pageNo");
        exclude.add("startNum");
        Page page = empService.queryObjByCondition(empQuery, exclude);
    }
    @Test
    public void testList() {
        List<Emp> list = empService.list();
        System.out.println(list);
    }
}