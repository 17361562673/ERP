<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("#supplierListForm").submit();
		});

		//进行添加操作
		$("#addSupplier").click(function () {
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 450;
			diag.ShowButtonRow=true;
			diag.Title = "供应商添加";
			diag.URL = "${path}/supplier_input.action";
			diag.OKEvent = function(){
				var win=diag.innerFrame.contentWindow;
				var result=win.submitSupplierForm();
				if (result == "success") {
					diag.close();
					window.location.href="${path}/supplier_list.action";
				}
			};
			diag.show();
		})
	});


	//供应商修改
	function editSupplier(supplierId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 450;
		diag.ShowButtonRow=true;
		diag.Title = "供应商修改";
		diag.URL = "${path}/supplier_edit.action?supplier.supplierId="+supplierId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.submitSupplierForm();
			if (result == "success") {
				//把页面关闭再刷新
				diag.close();
				window.location.href="${path}/supplier_list.action";
			}
		};
		diag.show();
	}

	//供应商删除
	function deleteSupplierForm(supplierId) {
		Dialog.confirm('警告：您确认要删除这条数据吗?',function(){
			Dialog.alert("好的，数据已经被删除")
			window.location.href="${path}/supplier_delete?supplier.supplierId="+supplierId;
		});
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">供应商管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/supplier_list.action" method="post" id="supplierListForm">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="28%" height="30">&nbsp;</td>
						<td width="8%">供应商:</td>
						<td width="17%">
							<s:textfield type="text" size="18" name="query.name"></s:textfield>
						</td>
						<td width="8%">联系人:</td>
						<td width="17%">
							<s:textfield type="text" size="18" name="query.contact"></s:textfield>
						</td>
						<td width="12%">
							<a id="query" href="javascript:void(0)"><img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
					</tr>
					<tr>
						<td height="30">&nbsp;</td>
						<td>电话:</td>
						<td>
							<s:textfield type="text" size="18" name="query.tel"></s:textfield>
						</td>
						<td>提货方式：</td>
						<td>
							<s:select list="#{'1':'自提','0':'送货'}" name="query.needs" headerKey="" headerValue="----请-选-择----" cssClass="kuan"></s:select>
						</td>
						<td>
							<a href="javascript:void(0)" id="addSupplier"><img src="${path}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">供应商</td>
						<td width="20%">地址</td>
						<td width="20%">联系人</td>
						<td width="12%">电话</td>
						<td width="12%">送货方式</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="supplier">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30"><s:property value="#supplier.name"></s:property></td>
							<td><s:property value="#supplier.address"></s:property></td>
							<td><s:property value="#supplier.contact"></s:property></td>
							<td><s:property value="#supplier.tel"></s:property></td>
							<td><s:property value="#supplier.needs==1?'自提':'送货'"></s:property></td>
							<td>
								<img src="${path}/images/icon_3.gif" />
								<span style="line-height:12px; text-align:center;">
								<a href="javascript:void(0)" onclick="editSupplier(<s:property value="#supplier.supplierId"/>)" class="xiu">修改</a>
							</span>
								<img src="${path}/images/icon_04.gif" />
								<span style="line-height:12px; text-align:center;">
								<a href="javascript:void(0)" class="xiu" onclick="deleteSupplierForm(<s:property value="#supplier.supplierId"/>)">删除</a>
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
