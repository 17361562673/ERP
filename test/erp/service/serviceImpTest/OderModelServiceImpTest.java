
/**
 * @ClassName OderModelServiceImpTest
 * @Authror zhouzhiqiang
 * @Date 2020/3/21 0:44
 * @description
 * @version 1.0
 */
package erp.service.serviceImpTest;

import erp.model.OrderModel;
import erp.service.OrderModelService;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class OderModelServiceImpTest {
    @Autowired
    private OrderModelService orderModelService;
    @Test
    public void testSaveOderModel() {

    }
    @Test
    public void testDeleteOderModel() {

    }
    @Test
    public void testUpdateOderModel() {

    }
    @Test
    public void testGetOderModel() {
        OrderModel model = orderModelService.get(34);
        System.out.println(model);
    }
    @Test
    public void testQueryOderModelByCondition() {

    }
    @Test
    public void testList() {

    }
}