<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			//翻页查询后，还是把页码回归到1
			$("#pageNo").val(1);
			$("#sListForm").submit();
		});
	});

	//展示仓库详细
	function viewStoreDetail(storeId,storeName) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height =400;
		diag.ShowButtonRow=true;
		diag.Title =storeName;
		diag.URL = "${path}/store_storeDetail.action?query.storeId="+storeId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.detail();
			if (result=="success") {
				//把页面关闭再刷新
				diag.close();
				//相当于刷新
				window.location.href="${path}/store_sList.action";
			}
		};
		diag.show();
	}


</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">仓库货物明细</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/store_sList.action" method="post" id="sListForm">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="10%">仓库名称:</td>
						<td width="20%">
							<s:textfield size="20" name="query.name"></s:textfield>
						</td>
						<td>管理员:</td>
						<td>
							<s:textfield size="20" name="query.storeAdminName"></s:textfield>
						</td>
						<td>地址:</td>
						<td>
							<s:textfield size="20" name="query.address"></s:textfield>
						</td>
						<td width="">
							<a id="query" href="javascript:void(0)"><img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">仓库名称</td>
						<td width="25%">仓库管理员</td>
						<td width="25%">地址</td>
						<td width="25%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="store">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">
								<s:property value="#store.name"></s:property>
							</td>
							<td>
								<s:property value="#store.storeAdmin.name"></s:property>
							</td>
							<td>
								<s:property value="#store.address"></s:property>
							</td>
							<td>
								<a href="javascript:void(0)" onclick="viewStoreDetail(<s:property value="#store.storeId"/>,'<s:property value="#store.name"/>')" class="xiu">详情</a>
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
