<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--引入自定义的标签--%>
<%@taglib prefix="e" uri="http://zzqjava/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%--引入css样式表--%>
<link href="${path}/css/index.css" rel="stylesheet" type="text/css" />
<title>兰州工业学院供销存管理系统-系统主页</title>
    <%--定义全局相对路径  记得用双引号括住(踩过的小坑) 相当于都在一个文件里面--%>
<script type="text/javascript">
    var path="${path}";
</script>
    <%--一引入Jquery的库--%>
<script type="text/javascript" src="${path}/js/jquery-1.8.3.js"></script>
    <%--引入日历插件--%>
<script type="text/javascript" src="${path}/js/Calendar.js"></script>
    <%--引入zdialog弹窗插件--%>
<script type="text/javascript" src="${path}/js/zDialog/zDrag.js"></script>
<script type="text/javascript" src="${path}/js/zDialog/zDialog.js"></script>
    <%--引入Jquery的form插件(可以通过jquery进行表单提交)--%>
    <%--这儿遇到一个超级大的坑 这个文件直接放在js下引不到(?????????????)--%>
<script type="text/javascript" src="${path}/js/jquery.form.js"></script>
    <%--引入zTree的相关插件--%>
<script type="text/javascript" src="${path}/js/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${path}/js/zTree/js/jquery.ztree.excheck.js"></script>
</head>
<body>

</body>
</html>
