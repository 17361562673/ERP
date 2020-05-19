/**
 * @ClassName LoginInterceptor
 * @Authror zhouzhiqiang
 * @Date 2020/3/27 23:28
 * @description
 * @version 1.0
 */
package erp.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import erp.controller.BaseAction;
import erp.model.Emp;

import java.util.Map;


/*
*
* 拦截器也是一种特殊的action
*
* */
public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation ai) throws Exception {
        //获得action的容器
        ActionContext context = ai.getInvocationContext();
        //获得session
        Map<String, Object> session = context.getSession();
        //获得session中的用户
        Emp empUser = (Emp) session.get("user");
        String result=null;
        if (empUser != null) {
            //让拦截器继续向下走
            result = ai.invoke();
        } else {
            result= BaseAction.LOGIN;
        }
        return result;
    }
}
