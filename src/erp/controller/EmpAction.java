/**
 * @ClassName EmpAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/21 12:58
 * @description
 * @version 1.0
 */
package erp.controller;

import com.opensymphony.xwork2.ActionContext;
import erp.model.Dep;
import erp.model.Emp;
import erp.model.Menu;
import erp.model.Role;
import erp.query.EmpQuery;
import erp.service.DepService;
import erp.service.EmpService;
import erp.utils.MD5Utils;
import erp.utils.Page;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.*;

public class EmpAction extends BaseAction{
    //定义接收查询对象
    private EmpQuery query=new EmpQuery();
    //定义封装的对象 emp
    private Emp emp=new Emp();
    //定义前台传过来的员工选中的角色id
    private String roleIds=new String();
    //定义验证码接收对象(用于接收前台传过来的验证码)
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public EmpQuery getQuery() {
        return query;
    }

    public void setQuery(EmpQuery query) {
        this.query = query;
    }

    private EmpService empService;
    private DepService depService;
    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }
    public void setDepService(DepService depService) {
        this.depService = depService;
    }

    //获取empList的action它要跳转到emp-->list页面
    public String emp_list() {
        List<Dep> depList = depService.list();
        ActionContext context = ActionContext.getContext();
        //使用contextMap进行存储数据
        context.put("dList",depList);
        //进行查询
        Integer pageNo = query.getPageNo();
        if (pageNo == null) {
            query.setPageNo(1);
        }
        Page page = empService.queryObjByCondition(query,exclude);
        context.put("page",page);
        return SUCCESS;
    }

    //添加emp的action它要跳转到emp-->input页面
    public String emp_input() {
        List<Dep> depList = depService.list();
        ActionContext context = ActionContext.getContext();
        context.put("dList",depList);
        return SUCCESS;
    }

    //员工保存的方法
    public void ajax_emp_add() {
        //从前台得到密码
        String password = emp.getPassword();
        //通过md5对密码进行加密
        String md5Password = MD5Utils.md5(password);
        emp.setPassword(md5Password);
        //调用save方法保存员工
        empService.save(emp);
        try {
            response1.getWriter().write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //校验用户名是否重复
    public void ajax_emp_validUsername() {
        String result="yes";
        Emp empByUsername = empService.getEmpByUsername(this.emp.getUsername());
        if (empByUsername != null) {
            result = "no";
        }
        try {
            response1.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //用户修改(通过empId获得当前的emp在前台展示)
    public String emp_edit() {
        ActionContext context = ActionContext.getContext();
        List<Dep> depList = depService.list();
        context.put("dList",depList);



        //因为edit.jsp用的是struts2的标签
        // <s:textfield name="emp.name" type="text"/>
        // 所以可以自动回显不需要再把emp放到contextMap中发送到前台进行接收，这儿用的是上面的emp(必须给上面的emp不然无法回显挖过的坑)
        emp = empService.get(this.emp.getEmpId());
        return SUCCESS;
    }

    //用户修改(无法debug就重启服务器)
    public void ajax_emp_edit() {
         empService.updateEmp(emp);
        try {
            response1.getWriter().write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //员工删除
    public String emp_delete() {
        empService.delete(emp.getEmpId());
        return LIST;
    }

    //查询所有角色和当前员工的所有角色
    public String emp_listPop() {
        //获得所有角色
        List<Role> roleList = empService.getStateRoles(emp.getEmpId());
        ActionContext context = ActionContext.getContext();
        context.put("roleList",roleList);
        return SUCCESS;
    }


    //分配角色
    public void ajax_emp_grantRole() {
        empService.updateEmpRole(emp.getEmpId(),roleIds);
        try {
            response1.getWriter().write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成验证码的图片
     * @throws Exception
     */
    public void ajax_emp_getImage() throws Exception{
        System.out.println("#######################生成数字和字母的验证码#######################");
        BufferedImage img = new BufferedImage(68, 22,
                BufferedImage.TYPE_INT_RGB);
        // 得到该图片的绘图对象
        Graphics g = img.getGraphics();
        Random r = new Random();
        Color c = new Color(200, 150, 255);
        g.setColor(c);
        // 填充整个图片的颜色
        g.fillRect(0, 0, 68, 22);
        // 向图片中输出数字和字母
        StringBuffer sb = new StringBuffer();
        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int index, len = ch.length;
        for (int i = 0; i < 4; i++) {
            index = r.nextInt(len);
            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt
                    (255)));
            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
            // 输出的  字体和大小
            g.drawString("" + ch[index], (i * 15) + 3, 18);
            //写什么数字，在图片 的什么位置画
            sb.append(ch[index]);
        }
        //把验证码的值放入session中
        request1.getSession().setAttribute("piccode", sb.toString());
        //把验证码的图片写回浏览器
        ImageIO.write(img, "JPG", response1.getOutputStream());
    }


    //到用户登录的页面
    public String emp_toLogin() {
        return SUCCESS;
    }

    //真正的登录页面
    public String emp_login() {
        ActionContext context = ActionContext.getContext();
        //拿到正确的验证码
        String rightCaptcha = (String) session1.getAttribute("piccode");
        //和前台传过来的验证码进行比较(忽略大小写)
        if (!StringUtils.equalsIgnoreCase(rightCaptcha,captcha)) {
            context.put("tip","caperror");
            return LOGIN;
        }
        //给前台传过来的明文密码进行加密处理
        String newpassword = MD5Utils.md5(emp.getPassword());
        Emp emp1 = empService.getEmpByusernameAndpassword(emp.getUsername(), newpassword);
        if (emp1 == null) {
            context.put("tip","userpasserror");
            return LOGIN;
        }
        //获得session
        Map<String, Object> session = context.getSession();
        //把用户放入session中
        session.put("user",emp1);

        //获得当前用户下的角色
        Set<Role> roles = emp1.getRoles();
        List<Map<String,Object>> mapList=new ArrayList<>();
        //遍历获取当前用户下的菜单
        for (Role role : roles) {
            Set<Menu> menus = role.getMenus();
            createTreeData(mapList,menus);
        }
        JSONArray ja = JSONArray.fromObject(mapList);
        context.getSession().put("zNodes",ja);
        return MAIN;
    }


    //创建菜单
    public void createTreeData(List<Map<String,Object>> mapList,Set<Menu> menus){
        for(Menu m : menus){
            Map<String,Object> map = new HashMap<String, Object>();
            Integer id = m.getMenuId();
            Integer pId = m.getParentMenuId();
            String name = m.getName();
            map.put("id", id);
            map.put("pId", pId);
            map.put("name", name);
            map.put("url", m.getUrl());
            map.put("target", "main");
            mapList.add(map);
        }
    }

    //注销
    public String emp_logOut() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session2 = context.getSession();
        session2.remove("user");
        return MAIN;
    }
}
