<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>兰州工业学院供销存管理系统-系统主页</title>
	<%--这个样式必须去掉不然会有样式的冲突--%>
	<%--<link rel="stylesheet" href="${path }/js/zTree/css/demo.css" type="text/css">--%>
	<link rel="stylesheet" href="${path }/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript">
		$(document).ready(function(){
			var setting = {
				data: {
					simpleData: {
						enable: true
					}
				}
			};
			var zNodes =${zNodes}
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	</script>
</head>
<body>
<div class="container">
	<div class="head">
		<div class="head-left">
			<span style="font-weight:bold; color:#1f4906">欢迎您-</span><br />
			<span style="color:#4a940d">
					<%--从session中把name取到(取的是真实的姓名)--%>
					<s:property value="#session.user.name"></s:property>
				</span>
		</div>
		<div class="head-right">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="32%">
						<a href="emp/changePwd.jsp" target="main">
							<img src="${path}/images/head-l.gif" border="0" />
						</a>
					</td>
					<td width="26%">
						<a href="${path}/emp_logOut.action">
							<img src="${path}/images/head-m.gif" border="0" />
						</a>
					</td>
					<td width="7%">&nbsp;</td>
					<td width="35%"><a href="#"><img src="${path}/images/head-r.gif"
													 border="0" />
					</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!--"head"end-->

	<div class="content">
		<div class="left">
			<div style="margin-left:2px;">
				<img src="${path}/images/left-top.gif" width="162" height="25" />
			</div>
			<div class="left-bottom">
				<div class="zTreeDemoBackground left" style="height: 600px; width: 100%">
					<ul id="treeDemo" style="height: 600px;" class="ztree" ></ul>
				</div>
				<%--<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td><a class="hei" target="main" href="#">商品管理</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="${path}/supplier_list.action">&nbsp;&nbsp;&nbsp;&nbsp;供应商</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="${path}/productType_list.action">&nbsp;&nbsp;&nbsp;&nbsp;商品类别</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="${path}/product_list.action">&nbsp;&nbsp;&nbsp;&nbsp;商品</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="#">采购管理</a></td>
					</tr>
					<tr>
						&lt;%&ndash;让它跳转到采购订单并且是未审核的&ndash;%&gt;
						<td><a class="hei" target="main" href="${path}/orderModel_list.action?query.orderType=1&query.orderState=1">&nbsp;&nbsp;&nbsp;&nbsp;采购订单</a></td>
					</tr>
					&lt;%&ndash;<tr>
                        <td><a class="hei" target="main" href="">&nbsp;&nbsp;&nbsp;&nbsp;采购退货</a></td>
                    </tr>&ndash;%&gt;
					<tr>
						<td><a class="hei" target="main" href="${path}/orderModel_checkList.action?query.orderType=1&query.orderState=1">&nbsp;&nbsp;&nbsp;&nbsp;采购审批</a></td>
					</tr>
					&lt;%&ndash;<tr>
                        <td><a class="hei" target="main" href="#">销售管理</a></td>
                    </tr>
                    <tr>
                        <td><a class="hei" target="main">&nbsp;&nbsp;&nbsp;&nbsp;销售订单</a></td>
                    </tr>
                    <tr>
                        <td><a class="hei" target="main">&nbsp;&nbsp;&nbsp;&nbsp;销售退货</a></td>
                    </tr>
                    <tr>
                        <td><a class="hei" target="main">&nbsp;&nbsp;&nbsp;&nbsp;销售审批</a></td>
                    </tr>&ndash;%&gt;
					<tr>
						<td><a class="hei" target="main" href="#">商品运输</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="${path}/transOrder_taskList.action?query.orderType=1&query.orderState=2">&nbsp;&nbsp;&nbsp;&nbsp;运输任务指派</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="${path}/transOrder_tasks.action?query.orderType=2&query.orderState=1">&nbsp;&nbsp;&nbsp;&nbsp;运输任务查询</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="#">仓库管理</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="${path}/store_sList.action">&nbsp;&nbsp;&nbsp;&nbsp;库存查询</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="${path}/transOrder_inList.action?query.orderType=3&query.orderState=1">&nbsp;&nbsp;&nbsp;&nbsp;入库</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main">&nbsp;&nbsp;&nbsp;&nbsp;出库</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="store/oper/list.jsp">&nbsp;&nbsp;&nbsp;&nbsp;仓库操作明细</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="#">报表中心</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="bill/buy/buyBill.jsp">&nbsp;&nbsp;&nbsp;&nbsp;进货报表</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main">&nbsp;&nbsp;&nbsp;&nbsp;销售报表</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main">&nbsp;&nbsp;&nbsp;&nbsp;仓库报表</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="#">基础维护</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="${path}/dep_list.action">&nbsp;&nbsp;&nbsp;&nbsp;部门维护</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="${path}/emp_list.action">&nbsp;&nbsp;&nbsp;&nbsp;员工维护</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="${path}/role_list.action">&nbsp;&nbsp;&nbsp;&nbsp;角色维护</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="resource/list.jsp">&nbsp;&nbsp;&nbsp;&nbsp;资源维护</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="menu/list.jsp">&nbsp;&nbsp;&nbsp;&nbsp;菜单维护</a></td>
					</tr>
					<tr>
						<td><a class="hei" target="main" href="store/list.jsp">&nbsp;&nbsp;&nbsp;&nbsp;仓库管理</a></td>
					</tr>
				</table>--%>
			</div>
			<!--"left-bottom"end-->
		</div>
		<!--"left"end-->

		<iframe id="frame-contect" src="${path}/erp_context.action"
				style="width:848px;float:right;height:530px" scrolling="no"
				name="main" frameborder="0"></iframe>
		<!--"content-right"end-->
	</div>
	<!--"content"end-->
	<div class="footer">
		<div style="margin-top:5px;">
			<table width="98%" border="0" cellpadding="0" cellspacing="0"
				   align="center">
				<tr>
					<td width="82%"><img src="${path}/images/icon_1.gif" />&nbsp; <a
							class="lanyo" href="http://www.baidu.com">兰州工业学院技术委员会 2020</a></td>
					<td width="18%" valign="middle"><img src="${path}/images/icon_2.gif" />&nbsp;
						<a class="lanyo" href="#">如有疑问请与技术人员联系</a></td>
				</tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>
