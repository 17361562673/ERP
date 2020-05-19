<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			//翻页查询后，还是把页码回归到1
			$("#pageNo").val(1);
			//提交表单
			$("#pTypeListForm").submit();
		});
		$("#addProductTypeButton").click(function () {
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 450;
			diag.ShowButtonRow=true;
			diag.Title = "商品类别管理";
			diag.URL = "${path}/productType_input.action";
			diag.OKEvent = function(){
				var win=diag.innerFrame.contentWindow;

				//因为前面关联了input.jsp所以它可以调用input.jsp里的方法
				//调用提交表单的方法获得返回值result
				var result=win.submitPTypeForm();
				//如果添加成功刷新页面(重新定向)
				if (result == "success") {
					//把页面关闭再刷新
					diag.close();
					window.location.href="${path}/productType_list.action";
				}
			};
			diag.show();
		})
	});

	//商品类别修改
	function editProductType(productTypeId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 450;
		diag.ShowButtonRow=true;
		diag.Title = "供应商修改";
		diag.URL = "${path}/productType_edit.action?pType.productTypeId="+productTypeId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.submitPTypeForm();
			if (result == "success") {
				//把页面关闭再刷新
				diag.close();
				window.location.href="${path}/productType_list.action";
			}
		};
		diag.show();
	}

	//商品类别删除
	function deleteProductTypeForm(productTypeId) {
		Dialog.confirm('警告：您确认要删除这条数据吗?',function(){
			Dialog.alert("好的，数据已经被删除")
			window.location.href="${path}/productType_delete?pType.productTypeId="+productTypeId;
		});
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品类别管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/productType_list.action" method="post" id="pTypeListForm">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">供应商:</td>
						<td width="142">
								<s:select list="#sList" name="query.supplierId" headerKey="" headerValue="----请-选-择----"
										  listKey="supplierId" listValue="name" cssClass="kuan"/>
						</td>
						<td width="60">类别名称:</td>
						<td width="149">
							<s:textfield type="text" size="18" name="query.name"></s:textfield>
						</td>
						<td width="70"><a id="query" href="javascript:void(0)"> <img
                                src="${path}/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a id="addProductTypeButton" href="javascript:void(0)"><img
                                src="${path}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="30%" height="30">供应商</td>
						<td width="30%">类别名称</td>
						<td width="40%">操作</td>
					</tr>

					<s:iterator value="#page.list" var="pType">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="30%" height="30">
								<s:property value="#pType.supplier.name"></s:property>
							</td>
							<td>
								<s:property value="#pType.name"></s:property>
							</td>
							<td>
								<img src="${path}/images/icon_3.gif" />
								<span style="line-height:12px; text-align:center;">
								<a href="javascript:void(0)" class="xiu" onclick="editProductType(<s:property value="#pType.productTypeId"/>)">修改</a>
							</span>
								<img src="${path}/images/icon_04.gif" />
								<span style="line-height:12px; text-align:center;">
								<a href="javascript:void(0)" class="xiu" onclick="deleteProductTypeForm(<s:property value="#pType.productTypeId"/>)">删除</a>
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
