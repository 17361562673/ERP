
/**
 * @ClassName RoleServiceImpTest
 * @Authror zhouzhiqiang
 * @Date 2020/3/21 0:44
 * @description
 * @version 1.0
 */
package erp.service.serviceImpTest;

import org.junit.Test;
import erp.service.RoleService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class RoleServiceImpTest {
    @Autowired
    private RoleService roleService;
    @Test
    public void testSaveRole() {

    }
    @Test
    public void testDeleteRole() {

    }
    @Test
    public void testUpdateRole() {

    }
    @Test
    public void testGetRole() {

    }
    @Test
    public void testQueryRoleByCondition() {

    }
    @Test
    public void testList() {

    }
}