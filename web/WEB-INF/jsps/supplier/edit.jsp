<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function () {
		$("#supplierEditForm").find("[regr]").keyup(function () {
			var valStr=$(this).val();
			var regr=$(this).attr("regr");
			var reg=new RegExp(regr);
			var tipStr=$(this).attr("tip");
			if (!reg.test(valStr)) {
				$(this).css({background: "#ff211a"});
				$("#divTip").html(tipStr);
				$("#divTip").show();
				$(this).next("span").hide();
			} else {
				$(this).css({background: "#46ff53"});
				$("#divTip").hide();
				$(this).next("span").show();
			}
		});
	});


	//表单校验
	function validSupplierForm() {
		var isSubmit=true;
		$("#supplierEditForm").find("[regr]").each(function () {
			var valStr=$(this).val();
			var regr=$(this).attr("regr");
			var reg=new RegExp(regr);
			var tipStr=$(this).attr("tip");
			if (!reg.test(valStr)) {
				$(this).css({background: "#ff211a"});
				$("#divTip").html(tipStr);
				$("#divTip").show();
				$(this).next("span").hide();
				return false;
				isSubmit=false;
			} else {
				$(this).css({background: "#46ff53"});
				$("#divTip").hide();
				$(this).next("span").show();
			}
		});
		return isSubmit;
	}

	//ajax方式提交表单
	function submitSupplierForm() {
		var isOk=validSupplierForm();
		//设置正确的标志result
		var result="";
		if (isOk) {
			$("#supplierEditForm").ajaxSubmit({
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
			<span class="page_title">供应商管理--供应商修改</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${path}/ajax_supplier_editSupplier.action" method="post" id="supplierEditForm">
				<input type="hidden" name="supplier.supplierId" value="<s:property value="supplier.supplierId"/>">
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
				      <td width="18%" height="30" align="center">供应商名称</td>
				      <td width="82%" colspan="3">
						  <s:textfield size="82" name="supplier.name" regr="^[\u4e00-\u9fa5\w]{3,25}$" tip="请输入3到25位中英文"></s:textfield>
						  <span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供应商地址</td>
				      <td width="82%" colspan="3">
						  <s:textfield size="82" name="supplier.address" regr="^[\u4e00-\u9fa5\w]{3,50}$" tip="请输入3到50位中英文"></s:textfield>
						  <span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
					  </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">联系人</td>
				      <td width="32%">
						  <s:textfield size="25" name="supplier.contact" regr="^[\u4e00-\u9fa5\w]{3,10}$" tip="请输入3到10位中英文"></s:textfield>
						  <span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
				      </td>
				      <td width="18%" align="center">电话</td>
				      <td width="32%">
						  <s:textfield name="supplier.tel" type="text" size="25" regr="(^(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)" tip="请输入正确的11位电话号"></s:textfield>
						  <span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">送货方式</td>
				      <td width="32%">
						  <s:select list="#{'1':'自提','0':'送货'}" name="supplier.needs" cssStyle="width: 190px"></s:select>
				      </td>
				      <td width="18%" align="center">&nbsp;</td>
				      <td width="32%">
				      	&nbsp;
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
