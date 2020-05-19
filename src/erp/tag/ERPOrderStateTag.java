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

public class ERPOrderStateTag extends TagSupport {

    /*
    * 使用标签之前真正做的事情
    * */
    private String orderType;
    private String orderState;

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

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
            case ERPConstants.ORDER_TYPE_BUY:
                switch (orderState){
                    case ERPConstants.ORDER_TYPE_BUY_AUDIT:text=ERPConstants.ORDER_TYPE_BUY_AUDIT_Text;break;
                    case ERPConstants.ORDER_TYPE_BUY_AUDIT_REJECT:text=ERPConstants.ORDER_TYPE_BUY_AUDIT_REJECT_Text;break;
                    case ERPConstants.ORDER_TYPE_BUY_AUDIT_PASS:text=ERPConstants.ORDER_TYPE_BUY_AUDIT_PASS_Text;break;
                }break;
            case ERPConstants.ORDER_TYPE_TRANS:
                switch (orderState) {
                    case ERPConstants.ORDER_TYPE_TRANS_BUY:text=ERPConstants.ORDER_TYPE_TRANS_BUY_TEXT;break;
                    case ERPConstants.ORDER_TYPE_TRANS_ASSIGN:text=ERPConstants.ORDER_TYPE_TRANS_ASSIGN_TEXT;break;
                    case ERPConstants.ORDER_TYPE_TRANS_BUYING:text=ERPConstants.ORDER_TYPE_TRANS_BUYING_TEXT;break;
                }break;
            case ERPConstants.ORDER_TYPE_INSTORAGE:
                switch (orderState) {
                    case ERPConstants.ORDER_TYPE_INSTORAGE_WAIT:text=ERPConstants.ORDER_TYPE_INSTORAGE_WAIT_TEXT;break;
                    case ERPConstants.ORDER_TYPE_INSTORAGE_INING:text=ERPConstants.ORDER_TYPE_INSTORAGE_INING_TEXT;break;
                    case ERPConstants.ORDER_TYPE_INSTORAGE_FINISH:text=ERPConstants.ORDER_TYPE_INSTORAGE_FINISH_TEXT;break;
                }break;
        }
        try {
            out.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
}
