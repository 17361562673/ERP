<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
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
			$("#tasksListForm").submit();
		});
		var orderState=$("#orderState").val();
		if (orderState=="1") {
			$("#li1").css({background:"#9DFF56"})
		}
		if (orderState=="3") {
			$("#li3").css({background:"#9DFF56"})
		}
	});


	//展示待采购详细列表
	function viewOrderDetail(orderId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height =400;
		diag.ShowButtonRow=true;
		diag.Title = "订单明细";
		diag.URL = "${path}/transOrder_taskDetailbuying.action?order.orderId="+orderId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.getOrderProduct();
			if (result=="success") {
				//把页面关闭再刷新
				diag.close();
				//相当于刷新
				window.location.href="${path}/transOrder_tasks.action?query.orderType=2&query.orderState=1";
			}
		};
		diag.show();
		diag.okButton.value="确认去取货";
	}

	//展示采购中详细列表
	function viewOrderDetails(orderId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height =400;
		diag.ShowButtonRow=true;
		diag.Title = "订单明细";
		diag.URL = "${path}/transOrder_taskDetailbuying.action?order.orderId="+orderId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.finishTranOrder();
			if (result=="success") {
				//把页面关闭再刷新
				diag.close();
				//相当于刷新
				window.location.href="${path}/transOrder_tasks.action?query.orderType=2&query.orderState=3";
			}
		};
		diag.show();
		diag.okButton.value="结单"
	}

</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">任务查询</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/transOrder_tasks.action?query.orderType=2&query.orderState=1" method="post" id="tasksListForm">
			<%--这儿是设置了隐藏域 因为该页面是带了参数进行访问的 orderModel_list.action?query.orderType=1
			 在访问的时候oderType就被封装进了query对象中 这儿取的时候是从query对象中取不用加#
			 这儿的两个隐藏域是为了查询的时候也跟着变化
			 也就是点击 待采购 和采购中的时候 颜色会跟着变化
			 --%>
			<input type="hidden" name="query.orderType" value="<s:property value="query.orderType"/>">
			<input id="orderState" type="hidden" name="query.orderState" value="<s:property value="query.orderState"/>">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="8%">供&nbsp;应&nbsp;商:</td>
						<td width="29%">
							<s:select list="#sList" name="query.supplierId" headerKey="" headerValue="----请-选-择----" cssStyle="width: 137px" listKey="supplierId" listValue="name"></s:select>
						</td>
						<td width="8%">发货方式:</td>
						<td width="45%">
							<s:select list="#{'1':'自提','0':'送货'}" name="query.needs" headerKey="" headerValue="----请-选-择----" cssStyle="width: 137px"></s:select>
						</td>
						<td width=""><a id="query" href="javascript:void(0)">
							<img src="${path}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<ul>
					<li id="li1"><a href="${path}/transOrder_tasks.action?query.orderType=2&query.orderState=1" style="text-decoration: none;">待采购</a></li>
					<li id="li3"><a href="${path}/transOrder_tasks.action?query.orderType=2&query.orderState=3" style="text-decoration: none;">采购中</a></li>
				</ul>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="8%" height="30">订单类别</td>
						<td width="11%">供应商</td>
						<td width="7%">发货方式</td>
						<td width="6%">联系人</td>
						<td width="12%">联系方式</td>
						<td width="40%">地址</td>
						<td width="14%">详情</td>
					</tr>
					<s:iterator value="#page.list" var="order">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">
								<c:set var="orderType" value="${order.orderType}"></c:set>
								<e:orderTypeText orderType="${orderType}"></e:orderTypeText>
							</td>
							<td>
								<s:property value="#order.supplier.name"></s:property>
							</td>
							<td>
								<s:property value="#order.supplier.needs==1?'自提':'送货'"></s:property>
							</td>
							<td>
								<s:property value="#order.supplier.contact"></s:property>
							</td>
							<td>
								<s:property value="#order.supplier.tel"></s:property>
							</td>
							<td align="left">&nbsp;
								<s:property value="#order.supplier.address"></s:property>
							</td>
							<td>
								<s:if test="#order.orderState==1">
									<a href="javascript:void(0)" onclick="viewOrderDetail(<s:property value="#order.orderId"/>)">
										<img src="${path}/images/icon_3.gif" />详情
									</a>
								</s:if>
								<s:elseif test="#order.orderState==3">
									<a href="javascript:void(0)" onclick="viewOrderDetails(<s:property value="#order.orderId"/>)">
										<img src="${path}/images/icon_3.gif" />详情
									</a>
								</s:elseif>
							</td>
						</tr>
					</s:iterator>
				</table>


				<%--引入分页条的页面--%>
				<%@include file="../tools/paging.jsp"%>


			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
