<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function () {
		$("#productTypeInputForm").find("[regr]").keyup(function () {
			//获得value
			var valStr = $(this).val();
			//获得正则表达式
			var regrStr=$(this).attr("regr");
			//将正则表达式转换成正则表达式对象
			var reg=new RegExp(regrStr);
			//获得tip
			var tipStr=$(this).attr("tip");
			//进行校验
			if (!reg.test(valStr)) {
				$(this).css({background: "#ff2621"});
				$("#divTip").html(tipStr);
				$("#divTip").show();
				$(this).next("span").hide();
			} else {
				$(this).css({background: "#41ff22"})
				$(this).next("span").show();
			}
		});
	});


	//通过AJax校验username是否重复
	 function validProduct(supplierId,name) {
		//结果的返回 如果是yes:不重复 no:重复
		var result="yes";
		$.ajax({
			url:"${path}/ajax_productType_valid.action",
			//通过post方式传还是通过get方式传
			type:"post",
			data:{
				//让emp进行接收
				"pType.supplierId":supplierId,
				"pType.name":name,
			},
			//设置同步的ajax 让ajax执行完再返回(很重要挖过的坑)
			//ajax默认是异步的(可以和主程序一起执行)，一旦需要使用ajax的结果作为返回值，就必须来把ajax变成同步async:false;
			async:false,
			//返回的结果的类型
			dataType:"text",
			//responseText是从后台接收到的数据,这儿必须是success,不能是ajaxSuccess
			success:function (responseText) {
				result=responseText;
			}
		});
		return result;
	}

	//表单校验
	function validForm() {
		var isSubmit=true;
		$("#productTypeInputForm").find("[regr]").each(function () {
			//获得value
			var valStr = $(this).val();
			//获得正则表达式
		 var regrStr=$(this).attr("regr");
		 //将正则表达式转换成正则表达式对象
			var reg=new RegExp(regrStr);
		 //获得tip
		 var tipStr=$(this).attr("tip");

		 //进行校验
			if (!reg.test(valStr)) {
				$(this).css({background: "#ff2621"});
				$("#divTip").html(tipStr);
				$("#divTip").show();
				$(this).next("span").hide();
				isSubmit=false;
				return false;
			} else {
				$(this).css({background: "#41ff22"});
				$(this).next("span").show();
			}
		});
		 return isSubmit;
	}

		//通过ajax的方式提交表单
		function submitPTypeForm() {
	 	   var isOk=validForm();
			//设置正确的标志result
			var result="";
			var supplierId=$("#supplierId").val();
			var name = $("#ptName").val();
			var isReapeate=validProduct(supplierId,name);
			if (isOk) {
				if (isReapeate == 'yes') {
					$("#productTypeInputForm").ajaxSubmit({
						async: false,
						dataType: "text",
						success: function (responseText) {
							//如果后台添加emp正确返回success
							result = responseText;
						}
					});
				} else {
					Dialog.alert("警告：当前供应商下的商品类别已经存在；请重新输入！")
					$(this).next("span").hide();
				}
			}
			return result;
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品类别管理--商品类别添加</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${path}/ajax_productType_add.action" method="post" id="productTypeInputForm">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					<tr bgcolor="#FFFFFF">
						<td align="center">
							<div id="divTip" style="text-align: center;border: 1px solid  #FFC2DB; margin: 5px;padding: 5px;width:500px;background:#FFE791;display:none;color: red;font-size: 20px"></div>
						</td>
					</tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供应商</td>
				      <td width="82%" colspan="3">
						  <s:select id="supplierId" list="#sList" name="pType.supplier.supplierId" headerKey="" headerValue="----请-选-择----"
									listKey="supplierId" listValue="name" cssClass="kuan" style="width:220px"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">商品类别名称</td>
				      <td width="82%" colspan="3" id="Pname">
						  <s:textfield  id="ptName" name="pType.name" type="text" size="25" regr="^[\u4e00-\u9fa5\w]{2,50}$" tip="请输入2-50字以内的中英文"></s:textfield>
						  <span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
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
