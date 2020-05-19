<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			//翻页查询后，还是把页码回归到1
			$("#pageNo").val(1);
			$("#inListForm").submit();
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


	//入库
	function inStock(orderId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height =400;
		diag.ShowButtonRow=true;
		diag.Title = "订单入库";
		diag.URL = "${path}/transOrder_inDetail.action?query.orderId="+orderId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.closeInDetail();
			if (result=="success") {
				//把页面关闭再刷新
				diag.close();
				//相当于刷新
				window.location.href="${path}/transOrder_inList.action?query.orderType=3&query.orderState=1";
			}
		};
		diag.show();
	}


</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">入库</span>
		</div>
	</div>
	<div class="content-text">
		<form action="list.jsp" method="post" id="inListForm">
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
						<td width="10%">订单号:</td>
						<td width="40%"><input type="text" size="40"/></td>
						<td width="10%">跟单人:</td>
						<td width="25%"><input type="text" size="20" /></td>
						<td width="15%"><a id="query"> 
							<img src="${path}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<ul>
					<li id="li1"><a href="${path}/transOrder_inList.action?query.orderType=3&query.orderState=1" style="text-decoration: none;">待入库</a></li>
					<li id="li2"><a href="${path}/transOrder_inList.action?query.orderType=3&query.orderState=2" style="text-decoration: none;">入库中</a></li>
					<li id="li3"><a href="${path}/transOrder_inList.action?query.orderType=3&query.orderState=3" style="text-decoration: none;">已入库</a></li>
				</ul>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="30%" height="30">订单号</td>
						<td width="20%">跟单人</td>
						<td width="20%">数量</td>
						<td width="20%">入库</td>
					</tr>
					<s:iterator value="#page.list" var="order">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">
								<s:property value="#order.orderNum"></s:property>
							</td>
							<td>
								<s:property value="#order.orderCompleter.name"></s:property>
							</td>
							<td>
								<s:property value="#order.orderDetails.size"></s:property>
							</td>
							<td>
								<img src="${path}/images/icon_3.gif" />
								<span style="line-height:12px; text-align:center;">
									<a href="javascript:void(0)" class="xiu" onclick="inStock(<s:property value="#order.orderId"/>)">入库
									</a>
								</span>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
