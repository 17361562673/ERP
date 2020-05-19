<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#depInputForm").find("[regr]").keyup(function () {
			//获得正则表达式
			var regStr=$(this).attr("regr");
			//将正则表达式转换成正则表达式对象
			var reg=new RegExp(regStr);
			//获得tip
			var tipStr=$(this).attr("tip");
			//获得提交的内容
			var valStr=$(this).val();
			if (!reg.test(valStr)) {
				$(this).css({background: "#ff2621"});
				$("#divTip").html(tipStr);
				$("#divTip").show();
				//让图片隐藏
				$(this).next("span").hide();
				validResult = false;
			} else {
				$(this).css({background: "#21ff15"});
				//让图片显示
				$(this).next("span").show();
			}
		});
	});



	//校验表单
	function validDepForm() {
		var validResult=true;
		//通过属性选择器拿到需要校验的项
		$("#depInputForm").find("[regr]").each(function () {
			//获得正则表达式
		var regStr=$(this).attr("regr");
		//将正则表达式转换成正则表达式对象
			var reg=new RegExp(regStr);
			//获得tip
			var tipStr=$(this).attr("tip");
			//获得提交的内容
			var valStr=$(this).val();
			if (!reg.test(valStr)) {
				$(this).css({background: "#ff2621"});
				$("#divTip").html(tipStr);
				$("#divTip").show();
				//让图片隐藏
				$(this).next("span").hide();
				validResult = false;
				return false;
			} else {
				$(this).css({background: "#21ff15"});
				//让图片显示
				$(this).next("span").show();
			}
		});
		return validResult;
	}



	//是否提交表单
	function submitDepForm() {
		var isOk=validDepForm();
		//设置正确的标志result
		var result="";
		if (isOk) {
			$("#depInputForm").ajaxSubmit({
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
			<span class="page_title">部门管理--部门添加</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${path}/ajax_dep_addDep.action" method="post" id="depInputForm">
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
				      <td width="18%" height="30" align="center">部门名称</td>
				      <td width="32%">
						  <s:textfield type="text" size="25" name="dep.name" regr="^[\u4e00-\u9fa5\w]{3,10}$"  tip="请输入3-10字以内的中英文"/>
						  <span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
				      </td>
				      <td width="18%" align="center">电话</td>
				      <td width="32%">
						  <s:textfield type="text" size="25" name="dep.tel" regr="(^(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)" tip="请输入正确的11位电话号"/>
						  <span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
					  </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
				
			</div>
			<%--<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:void(0)" id="commit"><img src="${path}/images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${path}/images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${path}/images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>--%>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
