<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("#roleListForm").submit();
		});
	});

	//角色分配的方法
	function gRole() {
		 var result="";
		//获得角色列表中被选中的checkBox
		$("tr input:checked").each(function () {
			result = result + $(this).val() + ",";
		});
		return result;
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">员工管理---角色分配</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/role_list.action" method="post" id="roleListForm">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center" style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="5" height="30">选择</td>
						<td width="40%" height="30">角色名称</td>
						<td width="40%">角色编码</td>
					</tr>


					<s:iterator value="#roleList" var="role">
						<tr align="center" bgcolor="#FFFFFF">
							<td>
								<%--转折点<s:if test="#role.select=='yes'">checked</s:if>--%>
								<input type="checkbox" <s:if test="#role.select=='yes'">checked</s:if> value="<s:property value="#role.roleId"/>">
							</td>
							<td height="30"><s:property value="#role.name"></s:property></td>
							<td><s:property value="#role.code"></s:property></td>
						</tr>
					</s:iterator>

				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
