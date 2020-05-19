<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("#transListForm").submit();
		});
	});

	//跳转到任务指派页面
	function taskAssign(orderId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 450;
		diag.ShowButtonRow=true;
		diag.Title = "任务指派";
		diag.URL = "${path}/transOrder_taskDetail?order.orderId="+orderId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.getAssginEmp();
			if (result=="success") {
				diag.close();
				window.location.href="${path}/transOrder_taskList.action?query.orderType=1&query.orderState=2";
			}
		};
		diag.show();
	}


</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品运输管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/transOrder_taskList.action?query.orderType=1&query.orderState=2" method="post" id="transListForm">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>下单时间:</td>
						<td>
							<s:textfield name="query.minCreateTime" size="10" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>
						</td>
						<td>到&nbsp;</td>
						<td>
							<s:textfield name="query.maxCreateTime" size="10" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>
						</td>
						<td>供&nbsp;应&nbsp;商:</td>
						<td>
							<s:select list="#sList" name="query.supplierId" cssStyle="width:115px" headerKey="" headerValue="----请-选-择----" listKey="supplierId" listValue="name"></s:select>
						</td>
						<td>下单人:</td>
						<td>
							<s:textfield size="10" name="query.orderCreate"></s:textfield>
						</td>
						<td>&nbsp;</td>
						<td><a id="query" href="javascript:void(0)">
							<img src="${path}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
					<tr>
						<td>审核时间:</td>
						<td>
							<s:textfield name="query.minCheckTime" size="10" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>
						</td>
						<td>到&nbsp;</td>
						<td>
							<s:textfield name="query.maxCheckTime" size="10" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>
						</td>
						<td>发货方式:</td>
						<td>
							<s:select list="#{'1':'自提','0':'送货'}" name="query.needs" headerKey="" headerValue="----请-选-择----"></s:select>
						</td>
						<td>审核人:</td>
						<td>
							<s:textfield size="10" name="query.orderCheckerName"></s:textfield>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="10%" height="30">订单类别</td>
						<td width="13%">下单时间</td>
						<td width="13%">制单人</td>
						<td width="13%">审核时间</td>
						<td width="13%">审核人</td>
						<td width="15%">供应商</td>
						<td width="13%">发货方式</td>
						<td width="10%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="order">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">
								<%--如果是从集合中取的order不能直接用el表达式用要先进行赋值 jstl不支持ognl的取值方式，
								记住要配套使用，el和jstl;ognl和struts2的标签
								自定义的标签只支持el表达式
								--%>
								<c:set var="orderType" value="${order.orderType}"></c:set>
								<e:orderTypeText orderType="${orderType}"></e:orderTypeText>
							</td>
							<td>
								<s:property value="#order.createTime"></s:property>
							</td>
							<td>
								<s:property value="#order.ordercreater.name"></s:property>
							</td>
							<td>
								<s:property value="#order.checkTime"></s:property>
							</td>
							<td>
								<s:property value="#order.orderChecker.name"></s:property>
							</td>
							<td>
								<s:property value="#order.supplier.name"></s:property>
							</td>
							<td>
								<s:property value="#order.supplier.needs==1?'自提':'送货'"></s:property>
							</td>
							<td>
								<img src="${path}/images/icon_3.gif" />
								<span style="line-height:12px; text-align:center;">
										<a onclick="taskAssign(<s:property value="#order.orderId"/>)" href="javascript:void(0)" class="xiu">任务指派
										</a>
									</span>
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
