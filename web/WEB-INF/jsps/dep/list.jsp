<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			//翻页查询后，还是把页码回归到1
			$("#pageNo").val(1);
			$("#depQueryForm").submit();
		});

		//部门添加
		$("#addDepButton").click(function () {
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 450;
			diag.ShowButtonRow=true;
			diag.Title = "部门添加";
			diag.URL = "${path}/dep_input.action";
			diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.submitDepForm();
				if (result=="success") {
					diag.close();
					window.location.href="${path}/dep_list.action";
				}
			};
			diag.show();
		})
	});

	//部门修改
	function editDep(depId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 450;
		diag.ShowButtonRow=true;
		diag.Title = "部门修改";
		diag.URL = "${path}/dep_edit.action?dep.depId="+depId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.submitDepForm();
			if (result == "success") {
				//把页面关闭再刷新
				diag.close();
				window.location.href="${path}/dep_list.action";
			}
		};
		diag.show();
	}

	//部门删除
	function deleteDep(depId) {
		Dialog.confirm('警告：您确认要删除这条数据吗?',function(){
			Dialog.alert("好的，数据已经被删除")
			window.location.href="${path}/dep_delete?dep.depId="+depId;
		});
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">部门管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/dep_list.action" method="post" id="depQueryForm">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					   style="font-size:14px; font-weight:bold; font-family:"黑体";">
				<tr>
					<td width="68" height="30">&nbsp;&nbsp;&nbsp;</td>
					<td width="123">&nbsp;</td>
					<td width="62">部门名称:</td>
					<td width="142">
						<s:textfield name="query.name" size="18" type="text"/>
					</td>
					<td width="60">电话:</td>
					<td width="149">
						<s:textfield name="query.tel" size="18" type="text"/>
					</td>
					<td width="70"><a id="query" href="javascript:void(0)"> <img
							src="${path}/images/can_b_01.gif" border="0" /> </a></td>
					<td width="70"><a href="javascript:void(0)" id="addDepButton"><img
							src="${path}/images/can_b_02.gif" border="0" /> </a></td>
				</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center>
					<span style="font-size:20px;color:#96D34A;font-weight:bold"></span>
				</center>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="13%" height="30">编号</td>
						<td width="13%">部门名称</td>
						<td width="16%">电话</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="dep">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30"><s:property value="#dep.depId"/></td>
							<td><s:property value="#dep.name"/></td>
							<td><s:property value="#dep.tel"/></td>
							<td>
								<img src="${path}/images/icon_3.gif" />
								<span style="line-height:12px; text-align:center;">
								<a href="javascript:void(0)" class="xiu" onclick="editDep(<s:property value="#dep.depId"/>)">修改</a>
							</span>
								<img src="${path}/images/icon_04.gif" />
								<span style="line-height:12px; text-align:center;">
								<a href="javascript:void(0)" class="xiu" onclick="deleteDep(<s:property value="#dep.depId"/>)">删除</a>
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
