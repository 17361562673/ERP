<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <title>兰州工业学院供销存管理系统-系统登录页</title>
    <script>
        $(function() {
            $("#login_ok").click(function() {
                $("#loginForm").submit();
            });

            //登录页跳出irame
            if (top.location.href != location.href) {
                top.location.href=location.href;
            }

            //点击生成验证码
            $("#changeImg").click(function () {
                //重新发送一次这个action 后面必须加new date()它会认为你重新发了一个不同的action
                var srcPath="${path}/ajax_emp_getImage.action?date="+new Date();
                //把src重置一下(让src再去访问后台action)
                $(this).attr("src",srcPath);
            });


            //如果验证码错误则显示提示信息
           var tip=$("#tip").val();
            if (tip == "caperror") {
                //让tipDiv进行显示
                $("#tipDiv").show(300);
                $("#tipDiv").html("验证码错误请重新获得并输入");
            }
            if (tip == "userpasserror") {
                //让tipDiv进行显示
                $("#tipDiv").show(300);
                $("#tipDiv").html("用户名或者密码错误");
            }


        });

    </script>
</head>
<body>
<div class="container-login">
    <input type="hidden" id="tip" value="<s:property value="#tip"/>"/>
    <div class="login-pic">
        <div class="login-text">
            <form action="${path }/emp_login.action" method="post" id="loginForm">

                <table width="100%" border="0" cellpadding="0" cellspacing="0">

                    <tr align="center">
                        <td colspan="3" >
                            <div id="tipDiv" style="text-align: center;border: 1px solid  #FFC2DB; padding: 2px;width: 180px;background:#FFE791;color: red;display: none"></div>
                        </td>
                    </tr>
                    <tr>
                        <td >用户名:</td>
                        <td colspan="2">
                            <input name="emp.username" type="text" size="18" />
                        </td>
                    </tr>
                    <tr>
                        <td >密&nbsp;&nbsp;码:</td>
                        <td colspan="2">
                            <input name="emp.password" type="password" size="18" />
                        </td>
                    </tr>
                    <tr>
                        <td >验证码:</td>
                        <td >
                            <input name="captcha" type="text" size="9" />
                        </td>
                        <td>
                            <a href="javascript:void(0)"><img src="${path}/ajax_emp_getImage.action" id="changeImg" /></a>
                        </td>
                    </tr>
                    <tr>
                        <td height="30">&nbsp;</td>
                        <td colspan="2">
                            <a href="javascript:void(0)" id="login_ok">
                                <img src="${path}/images/denglu_bg_03.gif"
                                     name="Image1" width="40"
                                     height="22" border="0"
                                     onmouseover="MM_swapImage(this,'${path}/images/denglu_h_03.gif')"
                                     onmouseout="MM_swapImage(this,'${path}/images/denglu_bg_03.gif')" /></a>
                            <a href="#">
                                <img src="${path}/images/giveup_bg_03.gif"
                                     name="Image2" width="42"
                                     height="22" border="0"
                                     onmouseover="MM_swapImage(this,'${path}/images/giveup_h_03.gif')"
                                     onmouseout="MM_swapImage(this,'${path}/images/giveup_bg_03.gif')" /></a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
