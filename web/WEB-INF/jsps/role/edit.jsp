<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#roleEditForm").find("[regr]").keyup(function () {
			//获得表单内容
			var valStr=$(this).val();
			//获得正则表达式
			var regr=$(this).attr("regr");
			//将正则表达式转换成正则表达式对象
			var reg=new RegExp(regr);
			//获得提示内容
			var tip=$(this).attr("tip");
			if (!reg.test(valStr)) {
				$(this).css({background: "#ff1e42"});
				$("#divTip").html(tip);
				$("#divTip").show();
				$(this).next("span").hide();
			} else {
				$(this).css({background: "#3dff45"});
				$("#divTip").hide();
				$(this).next("span").show();
			}
		});
	});

	//表单校验
	function validRoleForm() {
		var isOk=true;
		$("#roleEditForm").find("[regr]").each(function () {
			//获得表单内容
			var valStr=$(this).val();
			//获得正则表达式
			var regr=$(this).attr("regr");
			//将正则表达式转换成正则表达式对象
			var reg=new RegExp(regr);
			//获得提示内容
			var tip=$(this).attr("tip");
			if (!reg.test(valStr)) {
				$(this).css({background: "#ff1e42"});
				$("#divTip").html(tip);
				$("#divTip").show();
				$(this).next("span").hide();
				isOk = false;
				return false;
			} else {
				$(this).css({background: "#3dff45"});
				$("#divTip").hide();
				$(this).next("span").show();
			}
		});
		return isOk;
	}

	//通过ajax方式提交表单
	function submiRoleEditForm() {
		var isOk=validRoleForm();
		//设置正确的标志result
		var result="";
		if (isOk) {
			$("#roleEditForm").ajaxSubmit({
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
			<span class="page_title">角色管理--角色修改</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${path}/ajax_role_editRole.action" method="post" id="roleEditForm">
				<input type="hidden" name="role.roleId" value="<s:property value="role.roleId"/>">
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
				      <td width="18%" height="30" align="center">角色名称</td>
				      <td width="32%">
						  <s:textfield name="role.name" size="25" regr="^[\u4e00-\u9fa5\w]{3,10}$"  tip="请输入3-10字以内的中英文"></s:textfield>
						  <span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
					  </td>
				      <td width="18%" align="center">角色编码</td>
				      <td width="32%">
						  <s:textfield name="role.code" size="25" regr="^[\w!@#$%^&*()_]{3,10}$"  tip="请输入3-10个数字"></s:textfield>
						  <span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
					  </td>
				    </tr>
				</table>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
