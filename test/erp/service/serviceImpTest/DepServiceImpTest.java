
/**
 * @ClassName DepServiceImpTest
 * @Authror zhouzhiqiang
 * @Date 2020/3/21 0:44
 * @description
 * @version 1.0
 */
package erp.service.serviceImpTest;

import erp.model.Dep;
import erp.service.DepService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class DepServiceImpTest {
    @Autowired
    private DepService depService;
    @Test
    public void testSaveDep() {
        Dep dep = new Dep();
        dep.setName("开发部门");
        dep.setTel("1231564654");
        depService.save(dep);
    }
    @Test
    public void testDeleteDep() {
        depService.delete(13);
    }
    @Test
    public void testUpdateDep() {
        Dep dep = depService.get(12);
        dep.setTel("17361562373");
        depService.update(dep);
    }
    @Test
    public void testGetDep() {
        Dep dep = depService.get(12);
        System.out.println(dep);
    }
    @Test
    public void testQueryDepByCondition() {

    }
    @Test
    public void testList() {

    }
}