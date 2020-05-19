<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			//翻页查询后，还是把页码回归到1
			$("#pageNo").val(1);
			$("#orderModeList").submit();
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



		//订单添加
		$("#addOrderModelButton").click(function () {
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 450;
			diag.ShowButtonRow=true;
			diag.Title = "订单添加";
			diag.URL = "${path}/orderModel_input.action";
			diag.OKEvent = function(){
				var win=diag.innerFrame.contentWindow;

				//因为前面关联了input.jsp所以它可以调用input.jsp里的方法
				//调用提交表单的方法获得返回值result
				var result=win.subimtOrder();
				//如果添加成功刷新页面(重新定向)
				if (result == "success") {
					//把页面关闭再刷新
					diag.close();
					window.location.href="${path}/orderModel_list.action?query.orderType=1&query.orderState=1";
				}
			};
			diag.show();
		});

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
				window.location.href="${path}/orderModel_list.action?query.orderType=1&&query.orderState=1";
			}
		};
		diag.show();

		diag.addButton("next","操作日志",function(){
			var win=diag.innerFrame.contentWindow;
			var diag1 = new Dialog();
			diag1.Width =600;
			diag1.Height =350;
			diag1.ShowButtonRow=true;
			diag1.Title = "操作日志";
			diag1.URL = "${path}/consoleLog_consoleLog.action?query.entityId="+orderId+"&query.tableName=orderModel&query.optType=审核订单";
			diag1.OKEvent = function(){
					//把页面关闭再刷新
					diag1.close();
					//相当于刷新
					window.location.href="${path}/orderModel_list.action?query.orderType=1&&query.orderState=1";
			};
			diag1.show();
		});
	}


	//跳转到修改页面
	function toUpdateDetail(orderId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 450;
		diag.ShowButtonRow=true;
		diag.Title = "修改采购单";
		diag.URL = "${path}/orderModel_update.action?query.orderId="+orderId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;

			//因为前面关联了input.jsp所以它可以调用input.jsp里的方法
			//调用提交表单的方法获得返回值result
			var result=win.updateOrder();
			//如果添加成功刷新页面(重新定向)
			if (result == "success") {
				//把页面关闭再刷新
				diag.close();
				window.location.href="${path}/orderModel_list.action?query.orderType=1&query.orderState=1";
			}
		};
		diag.show();
	}



</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/orderModel_list.action" method="post" id="orderModeList">
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
						<td style="position: relative;left:216px"><a id="query" href="javascript:void(0)">
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
						<td style="position: relative;right: 50px">&nbsp;&nbsp;
							<s:textfield type="text" size="14" name="query.maxTotalPrice"></s:textfield>
						</td>
						<td>
							<a href="javascript:void(0)" id="addOrderModelButton">
								<img src="${path}/images/can_b_02.gif" border="0" />
							</a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<ul>
					<li id="li1"><a href="${path}/orderModel_list.action?query.orderType=1&query.orderState=1" style="text-decoration: none;">未审核</a></li>
					<li id="li2"><a href="${path}/orderModel_list.action?query.orderType=1&query.orderState=2" style="text-decoration: none;">审核通过</a></li>
					<li id="li3"><a href="${path}/orderModel_list.action?query.orderType=1&query.orderState=3" style="text-decoration: none;">审核未通过</a></li>
				</ul>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">订单号</td>
						<td width="9%">供应商</td>
						<td width="10%">制单人</td>
						<td width="20%">制单时间</td>
						<td width="10%">订单商品总量</td>
						<td width="12%">订单总金额</td>
						<td width="10%">详情</td>
					</tr>
					<s:iterator value="#page.list" var="order">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30">
								<s:property value="#order.orderNum"></s:property>
							</td>
							<td>
								<s:property value="#order.supplier.name"></s:property>
							</td>
							<td>
								<s:property value="#order.ordercreater.name"></s:property>
							</td>
							<td>
								<s:property value="#order.createTime"></s:property>
							</td>
							<td>
								<s:property value="#order.totalNum"></s:property>
							</td>
							<td align="right"><s:property value="#order.totalPrice"></s:property>元</td>
							<td>
								<a href="javascript:void(0)" onclick="viewDetail(<s:property value="#order.orderId"></s:property>)" class="xiu">详情</a>
								<s:if test="#order.orderState==3">
									<a href="javascript:void(0)" onclick="toUpdateDetail(<s:property value="#order.orderId"></s:property>)" class="xiu">修改</a>
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
