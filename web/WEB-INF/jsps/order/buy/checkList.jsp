<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp"%>
<style type="text/css">
	li{
		list-style-type: none;
		float: left;
		padding: 3px;
		border: #1fb5ff solid 1px;
		width: 80px;
		text-align: center;
		margin-right: 4px;
	}
</style>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("#checkListForm").submit();
		});
		var orderState=$("#orderState").val();
		if (orderState=="1") {
			$("#li1").css({background:"#9DFF56"})
		}
		if (orderState=="2") {
			$("#li2").css({background:"#9DFF56"})
		}
		if (orderState=="3") {
			$("#li3").css({background:"#9DFF56"})
		}
	});


	//展示订单详细
	function viewDetail(orderId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height =400;
		diag.ShowButtonRow=true;
		diag.Title = "订单明细";
		diag.URL = "${path}/orderModel_orderDetail.action?query.orderId="+orderId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.result();
			if (result=="success") {
				//把页面关闭再刷新
				diag.close();
				//相当于刷新
				window.location.href="${path}/orderModel_checkList.action?query.orderType=1&query.orderState=1";
			}
		};
		diag.show();
		diag.addButton("next","操作日志",function(){
			var win=diag.innerFrame.contentWindow;
			var diag1 = new Dialog();
			diag1.Width =650;
			diag1.Height =300;
			diag1.ShowButtonRow=true;
			diag1.Title = "操作日志";
			diag1.URL = "${path}/consoleLog_consoleLog.action?query.entityId="+orderId+"&query.tableName=orderModel&query.optType=审核订单";
			diag1.OKEvent = function(){
					//把页面关闭再刷新
					diag1.close();
					//相当于刷新
					window.location.href="${path}/orderModel_checkList.action?query.orderType=1&query.orderState=1";
			};
			diag1.show();
		});
	}


	//点击跳转到采购审批页面
	function toAuditOrder(orderId) {
		var diag = new Dialog();
		diag.Width =550;
		diag.Height =300;
		diag.ShowButtonRow=true;
		diag.Title = "采购审核";
		diag.URL = "${path}/orderModel_auditText.action";
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			//获得审批意见
			var note=win.getNote();
			var result = auditOrder(orderId, 2, note);
			if (result=="success") {
				diag.close();
				window.location.href="${path}/orderModel_checkList.action?query.orderType=1&query.orderState=1";
			}
		};
		diag.show();
		diag.okButton.value="通过";
		diag.addButton("next","驳回",function(){
			var win=diag.innerFrame.contentWindow;
			var note=win.getNote();
			var result=auditOrder(orderId,3,note);
			if (result=="success") {
				diag.close();
				window.location.href="${path}/orderModel_checkList.action?query.orderType=1&query.orderState=1";
			}
		});
	}

	//发ajax做订单审核
	function auditOrder(orderId,orderState,note) {
		var result="";
		$.ajax({
			url:"${path}/ajax_orderModel_auditOrder.action",
			type:"post",
			data:{
				"order.orderId":orderId,
				"order.orderState":orderState,
				"note":note
			},
			async:false,
			dataType:"text",
			success:function (responseText) {
				result=responseText;
			}
		});
		return result;
	}


</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">采购单审核</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/orderModel_checkList.action" method="post" id="checkListForm">
			<%--这儿是设置了隐藏域 因为该页面是带了参数进行访问的 orderModel_list.action?query.orderType=1
			 在访问的时候oderType就被封装进了query对象中 这儿取的时候是从query对象中取不用加#
			 这儿的两个隐藏域是为了查询的时候也跟着变化
			 --%>
			<input type="hidden" name="query.orderType" value="<s:property value="query.orderType"/>">
			<input id="orderState" type="hidden" name="query.orderState" value="<s:property value="query.orderState"/>">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					   style="font-size:14px; font-weight:bold; font-family:"黑体";">
				<tr>
					<td height="33">下单人:</td>
					<td>
						<s:textfield type="text" size="14" name="query.createName"></s:textfield>
					</td>
					<td>总量:</td>
					<td>
						<s:textfield type="text" size="14" name="query.minTotalNum"></s:textfield>
					</td>
					<td>到 </td>
					<td>&nbsp;&nbsp;
						<s:textfield type="text" size="14" name="query.maxTotalNum"></s:textfield>
					</td>
					<td style="position: relative;left:150px"><a id="query" href="javascript:void(0)">
						<img src="${path}/images/can_b_01.gif" border="0" /> </a>
					</td>
				</tr>
				<tr>
					<td>下单时间:</td>
					<td>
						<s:textfield size="14" type="text" name="query.minCreateTime" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>
					</td>
					<td>到</td>
					<td>
						<s:textfield size="14" type="text" name="query.maxCreateTime" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>
					</td>
					<td>总额:</td>
					<td style="position:relative;left: 8px">
						<s:textfield type="text" size="14" name="query.minTotalPrice"></s:textfield>
					</td>
					<td>到</td>
					<td style="position: relative;right:80px">&nbsp;&nbsp;
						<s:textfield type="text" size="14" name="query.maxTotalPrice"></s:textfield>
					</td>
				</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<ul>
					<li id="li1"><a href="${path}/orderModel_checkList.action?query.orderType=1&query.orderState=1" style="text-decoration: none;">未审核</a></li>
					<li id="li2"><a href="${path}/orderModel_checkList.action?query.orderType=1&query.orderState=2" style="text-decoration: none;">审核通过</a></li>
					<li id="li3"><a href="${path}/orderModel_checkList.action?query.orderType=1&query.orderState=3" style="text-decoration: none;">审核未通过</a></li>
				</ul>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="20%" height="35">订单号</td>
						<td width="15%">供应商</td>
						<td width="10%">制单人</td>
						<td width="15%">制单时间</td>
						<td width="10%">订单商品总量</td>
						<td width="8%">订单总金额</td>
						<td width="15%">详情</td>
					</tr>
					<s:iterator value="#page.list" var="order">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30"><s:property value="#order.orderNum"/></td>
							<td><s:property value="#order.supplier.name"/></td>
							<td><s:property value="#order.ordercreater.name"/></td>
							<td><s:property value="#order.createTime"/></td>
							<td><s:property value="#order.totalNum"/></td>
							<td align="right"><s:property value="#order.totalPrice"/> 元</td>
							<td>
								<input type="button" onclick="viewDetail(<s:property value="#order.orderId"/>)"  value="详情">
								<s:if test="#order.orderState == 1">
									<input type="button" onclick="toAuditOrder(<s:property value="#order.orderId"/>)"  value="审核">
								</s:if>
							</td>
						</tr>
					</s:iterator>
				</table>

				<%--引入分页条的页面--%>
				<%@include file="../../tools/paging.jsp"%>

			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
