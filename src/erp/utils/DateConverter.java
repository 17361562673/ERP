/**
 * @ClassName DateConverter
 * @Authror zhouzhiqiang
 * @Date 2020/3/22 16:04
 * @description 日期转换函数
 * @version 1.0
 */
package erp.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DateConverter extends StrutsTypeConverter {
    /**
     * @Author zhouzhiqiang
     * @Description  将字符串数据类型转换成其他数据类型以便在后端进行处理
     * @Date 16:13 2020/3/22
     * @Param
     * @return
     **/

    @Override
    public Object convertFromString(Map map, String[] strings, Class aClass) {
        Date myDate=null;
        if (strings != null&&strings.length>0) {
            String str=strings[0];
            if (StringUtils.isNotBlank(str)&&aClass==Date.class) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    myDate = format.parse(str);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return myDate;
    }

    /**
     * @Author zhouzhiqiang
     * @Description  将其他数据类型转换成字符串数据类型以便在前台页面进行展示
     * @Date 16:14 2020/3/22
     * @Param
     * @return
     **/

    @Override
    public String convertToString(Map map, Object o) {
        String myStr="";
        if (o!=null&&o.getClass() == Date.class||o.getClass()== Timestamp.class) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            myStr = dateFormat.format(o);
        }
        return myStr;
    }
}
