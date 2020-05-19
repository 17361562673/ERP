
/**
 * @ClassName StoreServiceImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:49
 * @description
 * @version 1.0
 */
package erp.service.serviceImp;

import erp.dao.OrderDetailDao;
import erp.dao.OrderModelDao;
import erp.dao.ProductDao;
import erp.dao.StoreDao;
import erp.model.*;
import erp.query.StoreQuery;
import erp.service.StoreService;
import erp.utils.ERPConstants;

import java.util.Set;

public class StoreServiceImp extends BaseServiceImp<Store, StoreQuery> implements StoreService {
   private StoreDao storeDao;
   private ProductDao productDao;
   private OrderDetailDao orderDetailDao;
   private OrderModelDao orderModelDao;

    public void setOrderModelDao(OrderModelDao orderModelDao) {
        this.orderModelDao = orderModelDao;
    }

    public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
        //当我们使用公用的dao的时候必须要一个具体的dao赋值给baseDao
        this.baseDao = storeDao;
    }

    @Override
    public void updateInstock(Integer storeId, Integer productId, Integer productNum,Integer oderDeTailId) {
        //查看要入库的仓库是已经存在该商品
        Store store = storeDao.get(storeId);
        //获得订单明细
        OrderDetail orderDetail = orderDetailDao.get(oderDeTailId);
        //获得仓库明细
        Set<StoreDetail> details = store.getStoreDetails();
        //设置标志false不不存在
        boolean isExist=false;
        for (StoreDetail detail : details) {
            //相等的时候已经存在该商品
            if (detail.getProduct().getProductId().intValue() == productId.intValue()) {
                //已经存在就修改它的数目
                detail.setNum(detail.getNum() + productNum);
                //修改订单详情的剩余数量
                orderDetail.setSurplus(orderDetail.getSurplus() - productNum);
                isExist = true;
                break;
            }
        }
        //如果仓库没有要入库的商品,那么我们要插入一条明细，创建一个明细对象
        if (!isExist) {
            StoreDetail storeDetail = new StoreDetail();
            storeDetail.setStoreId(storeId);
            storeDetail.setNum(productNum);
            Product product = productDao.get(productId);
            storeDetail.setProduct(product);
            orderDetail.setSurplus(orderDetail.getSurplus() - productNum);
            details.add(storeDetail);
        }

        //获得当前明细的所有订单
        OrderModel order = orderModelDao.get(new Integer(orderDetail.getOrderId()));
        //设置是否已经完成入库 默认是true(已经入库完毕)
        boolean isFinish=true;
        //获得这个订单下所有明细
        Set<OrderDetail> orderDetails = order.getOrderDetails();
        for (OrderDetail detail : orderDetails) {
            if (detail.getSurplus().intValue()!=0) {
                order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_INSTORAGE_INING));
                isFinish=false;
                break;
            }
            if (isFinish) {
                order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_INSTORAGE_FINISH));
            }
        }
    }
}