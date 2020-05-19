/**
 * @ClassName JsonUtils
 * @Authror zhouzhiqiang
 * @Date 2020/3/29 0:33
 * @description
 * @version 1.0
 */
package erp.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class JsonUtils {
    public static void printJsonArray(HttpServletResponse response, Collection cll,String[] excluede) {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        JsonConfig jc = new JsonConfig();
        //排除不需要的属性
        jc.setExcludes(excluede);
        //把集合转成json格式
        JSONArray jsonArray = JSONArray.fromObject(cll,jc);
        //把json串转成字符串
        String s = jsonArray.toString();
        try {
            response.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void printJsonObj(HttpServletResponse response, Object object,String[] excluede) {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        JsonConfig jc = new JsonConfig();
        //排除不需要的属性
        jc.setExcludes(excluede);
        //把对象转成json格式
        JSONObject jsonObject = JSONObject.fromObject(object, jc);
        //把json串转成字符串
        String s = jsonObject.toString();
        try {
            response.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
