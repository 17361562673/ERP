/**
 * @ClassName BaseAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/23 17:24
 * @description
 * @version 1.0
 */
package erp.controller;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BaseAction extends ActionSupport {
     HttpServletRequest request1;
     HttpServletResponse response1;
     HttpSession session1;
     ServletContext applicationContext1;
     List<String> exclude = new ArrayList<>();
     String LIST="list";
     String MAIN="main";
     String LOGOUT="out";
    //放在构造器里的初始化的东西子类可以直接拿来用
    public BaseAction() {
        exclude.add("startNum");
        exclude.add("pageNo");
        request1= ServletActionContext.getRequest();
        response1=ServletActionContext.getResponse();
        session1=request1.getSession();
        applicationContext1=ServletActionContext.getServletContext();
    }
}