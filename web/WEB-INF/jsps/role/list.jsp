<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			//翻页查询后，还是把页码回归到1
			$("#pageNo").val(1);
			$("#roleListForm").submit();
		});


		//角色添加
		$("#addRole").click(function () {
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 450;
			diag.ShowButtonRow=true;
			diag.Title = "角色添加";
			diag.URL = "${path}/role_input.action";
			diag.OKEvent = function(){
				var win=diag.innerFrame.contentWindow;
				var result=win.submiRoleInputForm();
				if (result=="success") {
					diag.close();
					window.location.href="${path}/role_list.action";
				}
			};
			diag.show();
		});
	});

	//角色修改
	function editRole(roleId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 450;
		diag.ShowButtonRow=true;
		diag.Title = "角色修改";
		diag.URL = "${path}/role_edit.action?role.roleId="+roleId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.submiRoleEditForm();
			if (result == "success") {
				//把页面关闭再刷新
				diag.close();
				window.location.href="${path}/role_list.action";
			}
		};
		diag.show();
	}

	//角色删除
	function deleteRole(roleId) {
		Dialog.confirm('警告：您确认要删除这条数据吗?',function(){
			Dialog.alert("好的，数据已经被删除")
			window.location.href="${path}/role_delete?role.roleId="+roleId;
		});
	}


	//跳转到权限分配页面
	function grantPerm(roleId) {
		var diag = new Dialog();
		diag.Width = 300;
		diag.Height = 450;
		diag.ShowButtonRow=true;
		diag.Title = "角色权限分配";
		diag.URL = "${path}/role_listPerm.action?query.roleId="+roleId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.getNodes(roleId);
			if (result == "success") {
				Dialog.alert("权限分配成功",function () {
					//把页面关闭再刷新
					diag.close();
					//相当于刷新
					window.location.href="${path}/role_list.action";
				});
			}
		};
		diag.show();
	}

</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/role_list.action" method="post" id="roleListForm">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">角色名称</td>
						<td width="142"><s:textfield type="text" size="18" name="query.name"></s:textfield></td>
						<td width="60">角色编码</td>
						<td width="149"><s:textfield type="text" size="18" name="query.code"></s:textfield></td>
						<td width="70"><a id="query" href="javascript:void(0)"> <img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a href="javascript:void(0)" id="addRole"><img src="${path}/images/can_b_02.gif" border="0" /></a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="40%" height="30">角色名称</td>
						<td width="40%">角色编码</td>
						<td width="20%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="role">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30"><s:property value="#role.name"></s:property></td>
							<td><s:property value="#role.code"></s:property></td>
							<td>
								<img src="${path}/images/icon_3.gif" />
							<span style="line-height:12px; text-align:center;">
								<a href="javascript:void(0)" class="xiu" onclick="grantPerm(<s:property value="#role.roleId"/>)">分配权限</a>
							</span>
								<img src="${path}/images/icon_3.gif" />
							<span style="line-height:12px; text-align:center;">
								<a href="javascript:void(0)" class="xiu" onclick="editRole(<s:property value="#role.roleId"/>)">修改</a>
							</span>
								<img src="${path}/images/icon_04.gif" />
							<span style="line-height:12px; text-align:center;">
								<a href="javascript:void(0)" class="xiu" onclick="deleteRole(<s:property value="#role.roleId"/>)">删除</a>
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
