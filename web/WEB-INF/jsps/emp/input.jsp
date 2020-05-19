<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#all").click(function() {
			$("[name=roles]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=roles]:checkbox").each(function () {
				$(this).attr("checked", !$(this).attr("checked"));
			});
		});


		//校验代码(对必填项的校验)
		//通过属性选择器找到每个要校验的
		$("#empInputForm").find("[regr]").keyup(function () {
			//获得它的值
			var val=$(this).val();
			//获得每一个必填项的正则表达式(通过属性获得)
			var regStr=$(this).attr("regr");
			//获得每一个必填项的提示信息(通过属性获得)
			var tipStr=$(this).attr("tip");
			//把正则表达式字符串转成正则表达式对象
			var regr = new RegExp(regStr);
			//获得每个文本框的name
			var vName=$(this).attr("name");
			if (!regr.test(val)) {
				//设置不符合条件框的背景
				$(this).css({background:"#ff2621"});
				//拿到div把里面的内容设置成上面拿到的tipStr
				$("#divTip").html(tipStr);
				//把div展示出来
				$("#divTip").show();
				//让图片隐藏
				$(this).next("span").hide();
			} else {
				if (vName == 'repassword') {
					//这个时候走到了repassword这儿了

					//拿到id=password的值
					var password = $("#password").val();
					//这儿的val是repassword的val
					if (password != val) {
						$(this).css({background: "#ff2621"});
						$("#divTip").html("两次密码不一致");
						$("#divTip").show();
						//让图片隐藏
						$(this).next("span").hide();
					} else {
						$(this).css({background: "#41ff22"});
						$("#divTip").hide();
						//让图片显示
						$(this).next("span").show();
					}
				} else {
					$(this).css({background: "#41ff22"});
					$("#divTip").hide();
					//让图片显示
					$(this).next("span").show();
				}
			}
		});

		//校验代码(对非必填项的校验)
		//通过属性选择器找到每个要校验的
		$("#empInputForm").find("[reg]").keyup(function () {
			var valStr=$(this).val();
			var regStr=$(this).attr("reg");
			var tipStr=$(this).attr("tip");
			var reg=new RegExp(regStr);
			if (valStr!=null&&$.trim(valStr)!=""&&!reg.test(valStr)) {
				$(this).css({background:"#ff2621"});
				$("#divTip").html(tipStr);
				$("#divTip").show();
				//让图片隐藏
				$(this).next("span").hide();
			} else {
				$(this).css({background: "#41ff22"});
				$("#divTip").hide();
				//让图片显示
				$(this).next("span").show();
			}
		});
	});



	//通过AJax校验username是否重复
	function validUsername(username) {
		//结果的返回 如果是yes:不重复 no:重复
		var result="yes";
		$.ajax({
			url:"${path}/ajax_emp_validUsername.action",
			//通过post方式传还是通过get方式传
			type:"post",
			data:{
				//让emp进行接收
				"emp.username":username
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


	//校验方法
	function validForm() {
		//表单的提交标志
		var isSubmit=true;

		//校验代码(对必填项的校验)
		//通过属性选择器找到每个要校验的
		$("#empInputForm").find("[regr]").each(function () {
			//获得它的值
			var val=$(this).val();
			//获得每一个必填项的正则表达式(通过属性获得)
			var regStr=$(this).attr("regr");
			//获得每一个必填项的提示信息(通过属性获得)
			var tipStr=$(this).attr("tip");
			//把正则表达式字符串转成正则表达式对象
			var regr = new RegExp(regStr);
			//获得每个文本框的name
			var vName=$(this).attr("name");
			if (!regr.test(val)) {
				//设置不符合条件框的背景
				$(this).css({background:"#ff2621"});
				//拿到div把里面的内容设置成上面拿到的tipStr
				$("#divTip").html(tipStr);
				//把div展示出来
				$("#divTip").show();
				//让图片隐藏
				$(this).next("span").hide();
				//设置提交标志是false
				isSubmit=false;
				//跳出校验循环
				return false;
			} else {

				//用户名是否重复的校验
				if (vName == "emp.username") {
					var result=validUsername(val);
					if (result == "no") {
						$(this).css({background: "#ff2621"});
						$("#divTip").html("用户名已经存在");
						$("#divTip").show();
						//让图片隐藏
						$(this).next("span").hide();
						isSubmit = false;
						return false;
					} else {
						$(this).css({background: "#41ff22"});
						$("#divTip").hide();
						//让图片显示
						$(this).next("span").show();
					}
				}


				if (vName=='repassword') {
					//这个时候走到了repassword这儿了

					//拿到id=password的值
					var password=$("#password").val();
					//这儿的val是repassword的val
					if (password!=val) {
						$(this).css({background:"#ff2621"});
						$("#divTip").html("两次密码不一致");
						$("#divTip").show();
						//让图片隐藏
						$(this).next("span").hide();
						isSubmit=false;
						return false;
					}
				}
				$(this).css({background: "#41ff22"});
				//让图片显示
				$(this).next("span").show();
			}
		});

		//校验代码(对非必填项的校验)
		//通过属性选择器找到每个要校验的
		$("#empInputForm").find("[reg]").each(function () {
			var valStr=$(this).val();
			var regStr=$(this).attr("reg");
			var tipStr=$(this).attr("tip");
			var reg=new RegExp(regStr);
			if (valStr!=null&&$.trim(valStr)!=""&&!reg.test(valStr)) {
				$(this).css({background:"#ff2621"});
				$("#divTip").html(tipStr);
				$("#divTip").show();
				//让图片隐藏
				$(this).next("span").hide();
				isSubmit=false;
				return false;
			} else {
				$(this).css({background: "#41ff22"});
				//让图片显示
				$(this).next("span").show();
			}
		});
		return isSubmit;
	}

	   //通过ajax的方式提交表单
		function submitEmpForm() {
			var isOk=validForm();
			//设置正确的标志result
            var result="";
            if (isOk) {
				$("#empInputForm").ajaxSubmit({
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
			<span class="page_title">员工维护---员工新建</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form  id="empInputForm" action="${path}/ajax_emp_add.action" method="post">
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
							<td width="18%" height="30" align="center"><font color="red">*</font>用&nbsp;户&nbsp;名</td>
							<td width="32%">
								<s:textfield name="emp.username" type="text" size="25" regr="^\w{3,18}$" tip="请输入3到18位英文用户名"></s:textfield>
								<span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
							</td>
							<td width="18%"align="center"><font color="red">*</font>真实姓名</td>
							<td width="32%">
								<s:textfield name="emp.name" type="text" size="25" regr="^[\u4e00-\u9fa5\w]{2,10}$" tip="请输入2到10位中英文"></s:textfield>
								<span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
							</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr  bgcolor="#FFFFFF">
							<td align="center"><font color="red">*</font>密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
							<td>
								<s:textfield id="password" name="emp.password" type="password" size="25" regr="^[\w!@#$%^&*()_]{6,8}$" tip="请输入6到8位密码"></s:textfield>
								<span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
							</td>
							<td  align="center"><font color="red">*</font>确认密码</td>
							<td >
								<s:textfield name="repassword" type="password" size="25" regr="^[\w!@#$%^&*()_]{6,8}$" tip="请输入6到8位确认密码"></s:textfield>
								<span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
							</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr  bgcolor="#FFFFFF">
							<td height="30" align="center">电子邮箱</td>
							<td>
								<s:textfield name="emp.email" type="text" size="25" reg="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" tip="请输入邮箱格式的文本，如renliang@126.com"></s:textfield>
								<span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
							</td>
							<td align="center">电话号码</td>
							<td>
								<s:textfield name="emp.tel" type="text" size="25" reg="(^(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)" tip="请输入正确的11位电话号"></s:textfield>
								<span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
							</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr  bgcolor="#FFFFFF">
							<td height="30" align="center"><font color='red'>*</font>性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
							<td>
								<%--往后台传的是key值--%>
								<s:select list="#{'1':'男','2':'女' }" name="emp.gender" cssClass="kuan"/>
							</td>
							<td align="center">地&nbsp;&nbsp;&nbsp;&nbsp;址</td>
							<td>
								<s:textfield name="emp.address" type="text" size="25" reg="^[\u4e00-\u9fa5\w]{2,50}$" tip="请输入2-50字以内的中英文"></s:textfield>
								<span style="display: none"><img width="20" src="${path}/images/images/ok.jpg"></span>
							</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr  bgcolor="#FFFFFF">
							<td height="30" align="center">出生日期</td>
							<td>
								<s:textfield name="emp.birthday" type="text" size="25" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
							</td>
							<td align="center"><font color='red'>*</font>所属部门</td>
							<td>
								<%--这儿必须设置成emp.dep.depId因为是通过多对一关系给dep给值的(通过emp.hbm.xml)--%>
								<s:select list="#dList" cssClass="kuan" name="emp.dep.depId"  listKey="depId" listValue="name"/>
							</td>
						</tr>
						<tr  bgcolor="#FFFFFF">
							<td colspan="4">&nbsp;</td>
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
