<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">

	/*
	*
	* AJXX处理二级联动
	*
	* */
	$(function() {
		$("#supplierId").change(function () {
			//获得供应商id
			var suppplierId=$(this).val();
			$.ajax({
				url:"${path}/ajax_supplier_getSupplier.action",
				type:"post",
				data:{
					"query.supplierId":suppplierId
				},
				async:false,
				dataType:"text",
				success:function (responseText) {
					//清空option
					$("#productType").empty();
					//追加提示信息
					$("#productType").append("\t<option value=\"-1\">----请-选-择----</option>")
					var jsonArray=$.parseJSON(responseText);
					for (var i = 0; i < jsonArray.length; i++) {
						//开始给option追加我们需要的
						$("#productType").append("<option value='"+jsonArray[i].productTypeId+"'>"+jsonArray[i].name+"</option>")
					}
				}
			});
		});


		$("#productInputForm").find("[regr]").keyup(function () {
			var valueStr=$(this).val();
			var regr=$(this).attr("regr");
			var reg=new RegExp(regr);
			var tipStr=$(this).attr("tip");
			if (!reg.test(valueStr)) {
				$("#divTip").html(tipStr);
				$("#divTip").show();
				$(this).css({background: "#ff1026"});
				$(this).next("span").hide();
			} else {
				$(this).css({background: "#63ff52"});
				$(this).next("span").show();
			}
		});


	});


	//处理表单校验
	function validProductForm() {
		var isSubmit=true;
		$("#productInputForm").find("[regr]").each(function () {
			var valueStr=$(this).val();
			var regr=$(this).attr("regr");
			var reg=new RegExp(regr);
			var tipStr=$(this).attr("tip");
			if (!reg.test(valueStr)) {
				$("#divTip").html(tipStr);
				$("#divTip").show();
				$(this).css({background: "#ff1026"});
				$(this).next("span").hide();
				return false;
				isSubmit = false;
			} else {
				$(this).css({background: "#46ff53"});
				$("#divTip").hide();
				$(this).next("span").show();
			}
		});
		return isSubmit;
	}


	//ajax提交表单
	function submitProductForm() {
		var isOk=validProductForm();
		//设置正确的标志result
		var result="";
		if (isOk) {
			$("#productInputForm").ajaxSubmit({
				async:false,
				dataType:"text",
				success:function(responseText){
					//如果后台添加emp正确返回success
					result = responseText;
				}
			})
		}
		return result;
	}

</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品管理--商品添加</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${path}/ajax_product_addProduct.action" method="post" id="productInputForm">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					<tr bgcolor="#FFFFFF">
						<td align="center">
							<div id="divTip" style="text-align: center;border: 1px solid  #FFC2DB; margin: 5px;padding: 5px;width: 700px;background:#FFE791;color: red;display: none;font-size: 20px"></div>
						</td>
					</tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供&nbsp;应&nbsp;商</td>
				      <td width="32%">
						  <s:select list="#sList" id="supplierId" name="product.productType.supplier.supplierId" headerKey="" headerValue="----请-选-择----" listKey="supplierId" listValue="name" style="width:190px"/>
					  </td>
				      <td width="18%"align="center">商品类别</td>
				      <td width="32%">
				      		<select style="width:190px" id="productType" name="product.productType.productTypeId">
								<%--<option value="-1">----请-选-择----</option>--%>
							</select>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td align="center">商品名称</td>
				      <td>
						  <s:textfield type="text" size="25" name="product.name" regr="^[\u4e00-\u9fa5\w]{3,50}$" tip="请输入3-50字以内的中英文"></s:textfield>
						  <span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
				      </td>
				      <td  align="center">产&nbsp;&nbsp;&nbsp;&nbsp;地</td>
				      <td>
						  <s:textfield type="text" size="25" name="product.origin" regr="^[\u4e00-\u9fa5\w]{2,20}$" tip="请输入2-20字以内的中英文"></s:textfield>
						  <span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
				      </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">生产厂家</td>
				      <td>
						  <s:textfield type="text" size="25" name="product.producer" regr="^[\u4e00-\u9fa5\w]{2,20}$" tip="请输入2-20字以内的中英文"></s:textfield>
						  <span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
				      <td align="center">单&nbsp;&nbsp;&nbsp;&nbsp;位</td>
				      <td>
						  <s:textfield type="text" size="25" name="product.unit" regr="^[\u4e00-\u9fa5\w]{1,10}$" tip="请输入1-10字以内的中英文"></s:textfield>
						  <span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
					  </td>
				     </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">进货单价</td>
				      <td>
						  <s:textfield type="text" size="25" name="product.inPrice"></s:textfield>
					  </td>
				      <td align="center">销售单价</td>
				      <td>
						  <s:textfield type="text" size="25" name="product.outPrice"></s:textfield>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				</table>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
