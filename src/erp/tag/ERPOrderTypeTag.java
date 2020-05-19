/**
 * @ClassName ERPTag
 * @Authror zhouzhiqiang
 * @Date 2020/3/29 22:31
 * @description
 * @version 1.0
 */
package erp.tag;

import erp.utils.ERPConstants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ERPOrderTypeTag extends TagSupport {

    /*
    * 使用标签之前真正做的事情
    * */
    private String orderType;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public int doStartTag() throws JspException {
        //获得jspWriter 以向jsp页面写文本
        JspWriter out = pageContext.getOut();
        String text = "";
        switch (orderType) {
            case ERPConstants.ORDER_TYPE_BUY:text=ERPConstants.ORDER_TYPE_BUY_TEXT;break;
            case ERPConstants.ORDER_TYPE_TRANS:text=ERPConstants.ORDER_TYPE_TRANS_TEXT;break;
            case ERPConstants.ORDER_TYPE_INSTORAGE:text=ERPConstants.ORDER_TYPE_INSTORAGE_TEXT;break;
            case ERPConstants.ORDER_TYPE_SALES:text=ERPConstants.ORDER_TYPE_SALES_TEXT;break;
        }
        try {
            out.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
}
