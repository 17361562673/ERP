/**
 * @ClassName BaseServiceImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 16:44
 * @description
 * @version 1.0
 */
package erp.service.serviceImp;

import erp.dao.BaseDao;
import erp.service.BaseService;
import erp.utils.Page;

import java.lang.reflect.Field;
import java.util.List;

public class BaseServiceImp<T,Q> implements BaseService<T,Q> {
    BaseDao<T,Q> baseDao;
    @Override
    public void save(T t) {
        baseDao.save(t);
    }

    @Override
    public void update(T t) {
        baseDao.update(t);
    }

    @Override
    public T get(Integer objId) {
        return baseDao.get(objId);
    }

    @Override
    public void delete(Integer objId) {
        baseDao.delete(objId);
    }

    @Override
    public void delete(T t) {
        baseDao.delete(t);
    }

    @Override
    public List<T> list() {
        return baseDao.list();
    }

    @Override
    public Page queryObjByCondition(Q q, List<String> exclude) {
        Page page = new Page();
        //获得查询对象的类对象
        Class<?> aClass = q.getClass();
        //获得pageNo属性
        try {
            Field fieldPageNo = aClass.getDeclaredField("pageNo");
            //把这个属性进行暴力反射(因为它是私有的)
            fieldPageNo.setAccessible(true);
            //获得pageNo属性的值
            Integer pageNo = (Integer) fieldPageNo.get(q);
            //把值赋值给page
            page.setPageNo(pageNo);
            //计算出开始行号startNum
            Integer startNum = page.getStartNum();

            //获得查询对象的startNum
            Field startNumField = aClass.getDeclaredField("startNum");
            //暴力反射
            startNumField.setAccessible(true);
            //给查询对象设置startNum
            startNumField.set(q,startNum);
            //查询结果集
            List<T> tList = baseDao.queryObjByCondition(q, exclude);
            //把结果集合赋值给page里的list
            page.setList(tList);
            //查询出总条数
            Long totalCount = baseDao.queryCountByCondition(q, exclude);
            //给totalCount进行赋值
            page.setTotalCount(new Integer(totalCount+""));
            //获得总页数
            Integer totalPage = page.getTotalPage();
            //给totalPage进行赋值
            page.setTotalPage(totalPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public List<T> queryObjByConditionNoPage(Q q, List<String> exclude) {
        return baseDao.queryObjByConditionNoPage(q,exclude);
    }

}
