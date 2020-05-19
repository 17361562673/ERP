package erp.dao;

import java.util.List;

//T:要操作的对象(emp) Q:查询对象(EmpQuery)
public interface BaseDao<T,Q> {
    public void save(T t);
    public void update(T t);
    public T get(Integer objId);
    public void delete(Integer objId);
    public void delete(T t);
    public List<T> list();
    /*
    * 查询的数据是每一页的记录
    * */
    public List<T> queryObjByCondition(Q q,List<String> exclude);
    /*
     *查询指定条件下的总记录数
     * */
    public Long queryCountByCondition(Q q,List<String> exclude);

    //不分页的条件查询
    public List<T> queryObjByConditionNoPage(Q q,List<String> exclude);
}