package erp.service;

import erp.utils.Page;

import java.util.List;

public interface BaseService<T,Q> {
    public void save(T t);
    public void update(T t);
    public T get(Integer objId);
    public void delete(Integer objId);
    public void delete(T t);
    public List<T> list();
    //查询page
    public Page queryObjByCondition(Q q,List<String> exclude);

    //不分页的条件查询
    public List<T> queryObjByConditionNoPage(Q q,List<String> exclude);
}
