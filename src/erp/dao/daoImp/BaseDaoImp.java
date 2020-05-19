/**
 * @ClassName BaseDaoImp
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 15:36
 * @description
 * @version 1.0
 */
package erp.dao.daoImp;

import erp.dao.BaseDao;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseDaoImp<T,Q> extends HibernateDaoSupport implements BaseDao<T,Q>{
    @Override
    public void save(T t) {
        this.getHibernateTemplate().save(t);
    }

    @Override
    public void update(T t) {
        this.getHibernateTemplate().update(t);
    }

    @Override
    public T get(Integer objId) {
        Class<?> class1 = getGenericClass();
        return (T) this.getHibernateTemplate().get(class1,objId);
    }

    @Override
    public void delete(Integer objId) {
        T t = get(objId);
        this.getHibernateTemplate().delete(t);
    }

    @Override
    public void delete(T t) {
        this.getHibernateTemplate().delete(t);
    }

    @Override
    public List<T> list() {
        Class<?> aClass = getGenericClass();
        String hql="from "+aClass.getName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }






   /**
    * @Author zhouzhiqiang
    * @Description 通过多条件进行模糊查询 查询的数据是每页的记录
    * @Date 12:07 2020/3/22
    * @Param
    * @return
    **/
    @Override
    public List<T> queryObjByCondition(Q q,List<String> exclude) {
        List<T> tList = this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            //Session是Spring开启的代理session,可以自动的开事务和关闭事务
            @Override
            public List<T> doInHibernate(Session session) throws HibernateException {
                //调用getHql进行创建sql
                String hql = getHql(q);
                //预编译sql
                Query query = session.createQuery(hql);
                //调用setDynamicParam给参数进行赋值
                setDynamicParam(query, q,exclude);
                //获得查询对象的类对象
                Class<?> aClass = q.getClass();
                Object val=null;
                try {
                    //获得这个查询对象的startNum属性
                    Field startNumFiled = aClass.getDeclaredField("startNum");
                    //暴力把startNumFiled进行反射(因为这个属性是private的每次反射的时候记得不要忘了)
                    startNumFiled.setAccessible(true);
                    //获得私有属性startNum的值
                     val= (Integer) startNumFiled.get(q);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //执行sql得到结果
                List<T> tList = query.setFirstResult((Integer) val).setMaxResults(6).list();
                return tList;
            }
        });
        return tList;
    }


    /**
     * @Author zhouzhiqiang
     * @Description 查询指定条件下的总记录数
     * @Date 12:07 2020/3/22
     * @Param
     * @return
     **/
    @Override
    public Long queryCountByCondition(Q q,List<String> exclude) {
        Long totalCount = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException {
                //调用getHqlCount进行创建sql
                String hqlCount = getHqlCount(q);
                //预编译sql
                Query query = session.createQuery(hqlCount);
                //调用setDynamicParam给参数进行赋值
                setDynamicParam(query,q,exclude);
                Long count = (Long) query.uniqueResult();
                return count;
            }
        });
        return totalCount;
    }




    //设置获取hql的抽象方法(查询每一页的记录)
    public abstract String getHql(Q q);

    //设置获取hql的抽象方法(查询指定条件下的记录数)
    public abstract String getHqlCount(Q q);

    //创建query对象的查询条件
    public abstract String createHqlCondition(Q q);


    /**
     * @Author zhouzhiqiang
     * @Description
     * @Date 14:17 2020/3/23
     * @Param query  q:要查询的对象(empQuery)  exclude(要排除的某些属性)
     * @return
     **/

    //通过反射进行动态赋值
    public void setDynamicParam(Query query, Q q,List<String> exclude) {
        //通过反射获取查询对象的类
        Class<?> aClass = q.getClass();
        //通过查询对象的类获取属性
        Field[] fields = aClass.getDeclaredFields();
        //获取它的父类的属性
        Field[] fields1 = aClass.getSuperclass().getDeclaredFields();
        //把数组转换成集合
        List<Field> fieldList = Arrays.asList(fields);
        List<Field> fieldList1 = Arrays.asList(fields1);
        //定义一个大的集合存储属性
        List<Field> fList=new ArrayList<>();
        //把两个属性小集合放到大集合里面
        fList.addAll(fieldList);
        fList.addAll(fieldList1);
        //遍历这个大集合获取属性
        for (Field field : fList) {
            //暴力反射(可以获取私有属性)
            field.setAccessible(true);
            //获取属性的名字
            String fieldName = field.getName();
            //如果exclude不为空并且exclude里面包含了fieldName里的一些值那么把这些值排除掉，然后在继续
            if (exclude != null&&exclude.contains(fieldName)) {
                continue;
            }
            Object val=null;
            try {
                //获取属性的值
                val=field.get(q);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (val != null) {
                if (val.getClass() == String.class) {
                    if (StringUtils.isNotBlank(val.toString())) {
                        query.setParameter(fieldName, "%" + val + "%");
                    }
                } else {
                    query.setParameter(fieldName,val);
                }
            }
        }
    }




   /**
    * @Author zhouzhiqiang
    * @Description 获取泛型的类
    * @Date 19:22 2020/3/23
    * @Param
    * @return
    **/
    public Class<?> getGenericClass() {
        //获得泛型的父类
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        //把泛型的父类做强制转换
        ParameterizedType pt= (ParameterizedType) genericSuperclass;
        //根据parameterizedType获得当前类的所有泛型的类型
        Type[] actualTypeArguments = pt.getActualTypeArguments();
        //获得T的具体类
        Class<?> aClass= (Class<?>) actualTypeArguments[0];
        return aClass;
    }

    //不分页的查询
    @Override
    public List<T> queryObjByConditionNoPage(Q q, List<String> exclude) {
        List<T> tList = this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            //Session是Spring开启的代理session,可以自动的开事务和关闭事务
            @Override
            public List<T> doInHibernate(Session session) throws HibernateException {
                //调用getHql进行创建sql
                String hql = getHql(q);
                //预编译sql
                Query query = session.createQuery(hql);
                //调用setDynamicParam给参数进行赋值
                setDynamicParam(query, q,exclude);
                //执行sql得到结果
                List<T> tList = query.list();
                return tList;
            }
        });
        return tList;
    }
}
