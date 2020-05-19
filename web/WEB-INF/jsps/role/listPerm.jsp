<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp" %>
<link rel="stylesheet" href="${path }/js/zTree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${path }/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript">
    var tree = null;
	$(function() {
		var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};
		//js中也可以使用el表达式来进行接收
		var zNodes = ${zNodes};
		tree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});


	//获得选中的节点
	function getNodes(roleId){
		//获得被选中的节点
		var nodes = tree.getCheckedNodes(true);
		var permIds = "";
		for(var i = 0; i < nodes.length; i++){
			permIds = permIds + nodes[i].id+",";
		}
		
		var result = grantRolePerm(roleId, permIds);
		return result;
	}

	//往后台发ajax进行权限分配
	function grantRolePerm(roleId, permIds){
		var result = "";
		$.ajax({
			url:"${path}/ajax_role_grantPerm",
			type:"post",
			data:{
				"query.roleId":roleId,
				"permIds":permIds
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				result = responseText;
			}
		});
		return result;
	}
</script>
<div>
	<div>
		<div class="zTreeDemoBackground left" style="height: 450px;">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
</div>
