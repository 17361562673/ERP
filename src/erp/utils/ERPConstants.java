package erp.utils;

public interface ERPConstants {
    /*
    *
    * 采购单
    * */
    public static final String ORDER_TYPE_BUY="1";
    public static final String ORDER_TYPE_BUY_TEXT="采购单";
    /*
    * 采购单状态
    * */
    //采购单审核(处于审核的状态)
    public static final String ORDER_TYPE_BUY_AUDIT="1";
    public static final String ORDER_TYPE_BUY_AUDIT_Text="未审核";
    //采购单审核通过
    public static final String ORDER_TYPE_BUY_AUDIT_PASS="2";
    public static final String ORDER_TYPE_BUY_AUDIT_PASS_Text="审核通过";
    //采购单审核不通过
    public static final String ORDER_TYPE_BUY_AUDIT_REJECT="3";
    public static final String ORDER_TYPE_BUY_AUDIT_REJECT_Text="审核不通过";
//***************************************************************************************************
    /*
    * 运输单
    * */
    public static final String ORDER_TYPE_TRANS="2";
    public static final String ORDER_TYPE_TRANS_TEXT="运输单";

    /*
    * 运输单状态
    * */
    //运输带采购单子(已经通过审核还没有去取的单子)
    public static final String ORDER_TYPE_TRANS_BUY="1";
    public static final String ORDER_TYPE_TRANS_BUY_TEXT="待采购";
    //运输带采购单子进行指派哪个人去取
    public static final String ORDER_TYPE_TRANS_ASSIGN="2";
    public static final String ORDER_TYPE_TRANS_ASSIGN_TEXT="派送单";
    //这个人去取了(运输中)
    public static final String ORDER_TYPE_TRANS_BUYING="3";
    public static final String ORDER_TYPE_TRANS_BUYING_TEXT="采购中";
//****************************************************************************************************
    /*
     * 入库单
     * */
    public static final String ORDER_TYPE_INSTORAGE="3";
    public static final String ORDER_TYPE_INSTORAGE_TEXT="入库单";
    /*
    * 入库单状态
    * */
    //单子等待入库(等待中)
    public static final String ORDER_TYPE_INSTORAGE_WAIT="1";
    public static final String ORDER_TYPE_INSTORAGE_WAIT_TEXT="待入库";
    //入库中
    public static final String ORDER_TYPE_INSTORAGE_INING="2";
    public static final String ORDER_TYPE_INSTORAGE_INING_TEXT="入库中";
    //入库完成(入库完成)
    public static final String ORDER_TYPE_INSTORAGE_FINISH="3";
    public static final String ORDER_TYPE_INSTORAGE_FINISH_TEXT="入库完成";
//****************************************************************************************************
    /*
    * 销售单
    * */
    public static final String ORDER_TYPE_SALES="4";
    public static final String ORDER_TYPE_SALES_TEXT="销售单";

}
