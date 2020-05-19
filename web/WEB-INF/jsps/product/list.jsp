<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			//翻页查询后，还是把页码回归到1
			$("#pageNo").val(1);
			$("#productListForm").submit();
		});
		//商品添加
		$("#addProductButton").click(function () {
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 450;
			diag.ShowButtonRow=true;
			diag.Title = "商品添加";
			diag.URL = "${path}/product_input.action";
			diag.OKEvent = function(){
				var win=diag.innerFrame.contentWindow;
				var result=win.submitProductForm();
				//如果添加成功刷新页面(重新定向)
				if (result == "success") {
					//把页面关闭再刷新
					diag.close();
					window.location.href="${path}/product_list.action";
				}
			};
			diag.show();
		})
	});

	//商品修改
	function editProduct(productId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 450;
		diag.ShowButtonRow=true;
		diag.Title = "商品修改";
		diag.URL = "${path}/product_edit.action?product.productId="+productId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.submitProductForm();
			if (result == "success") {
				//把页面关闭再刷新
				diag.close();
				window.location.href="${path}/product_list.action";
			}
		};
		diag.show();
	}

	//商品删除
	function deleteProductForm(productId) {
		Dialog.confirm('警告：您确认要删除这条数据吗?',function(){
			Dialog.alert("好的，数据已经被删除")
			window.location.href="${path}/product_delete?product.productId="+productId;
		});
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/product_list.action" method="post" id="productListForm">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>供应商:</td>
						<td>
							<s:select list="#sList" name="query.supplierId" headerKey="" headerValue="----请-选-择----" listKey="supplierId" listValue="name" style="width:113px"/>
						</td>
						<td height="30">商&nbsp;品&nbsp;名</td>
						<td>
							<s:textfield type="text" size="14" name="query.name"></s:textfield>
						</td>
						<td>生产厂家</td>
						<td>
							<s:textfield type="text" size="14" name="query.producer"></s:textfield>
						</td>
						<td>单&nbsp;&nbsp;&nbsp;&nbsp;位</td>
						<td>
							<s:textfield type="text" size="14" name="query.unit"></s:textfield>
						</td>
						<td width="70"><a  id="addProductButton" href="javascript:void(0)"><img src="${path}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
					<tr>
						<td height="30">进货价格</td>
						<td>
							<s:textfield type="text" size="14" name="query.minInPrice"></s:textfield>
						</td>
						<td>到</td>
						<td>
							<s:textfield type="text" size="14" name="query.maxInPrice"></s:textfield>
						</td>
						<td height="30">销售价格</td>
						<td>
							<s:textfield type="text" size="14" name="query.minOutPrice"></s:textfield>
						</td>
						<td>到</td>
						<td>
							<s:textfield type="text" size="14" name="query.maxOutPrice"></s:textfield>
						</td>
						<td><a id="query" href="javascript:void(0)"> <img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="12%" height="30">供应商</td>
						<td width="12%">商品名</td>
						<td width="12%">生产厂家</td>
						<td width="12%">产地</td>
						<td width="12%">进货价格</td>
						<td width="12%">销售价格</td>
						<td width="12%">单位</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="product">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30">
								<s:property value="#product.productType.supplier.name"/>
							</td>
							<td><s:property value="#product.name"/></td>
							<td><s:property value="#product.producer"/></td>
							<td><s:property value="#product.origin"/></td>
							<td align="right"><s:property value="#product.inPrice"/>&nbsp;元&nbsp;</td>
							<td align="right"><s:property value="#product.outPrice"/>&nbsp;元&nbsp;</td>
							<td><s:property value="#product.unit"/></td>
							<td>
								<img src="${path}/images/icon_3.gif" />
								<span style="line-height:12px; text-align:center;">
									<a href="javascript:void(0)" class="xiu" onclick="editProduct(<s:property value="#product.productId"/>)">修改</a>
								</span>
								<img src="${path}/images/icon_04.gif" />
								<span style="line-height:12px; text-align:center;">
									<a href="javascript:void(0)" class="xiu" onclick="deleteProductForm(<s:property value="#product.productId"/>)">删除</a>
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
