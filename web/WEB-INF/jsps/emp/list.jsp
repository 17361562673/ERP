<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			//翻页查询后，还是把页码回归到1
			$("#pageNo").val(1);
			//提交表单
			$("#empQueryForm").submit();
		});


		//引用dialog插件进行添加数据操作
		$("#addEmpButton").click(function () {
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 450;
			diag.ShowButtonRow=true;
			diag.Title = "员工添加";
			diag.URL = "${path}/emp_input.action";
			diag.OKEvent = function(){
				var win=diag.innerFrame.contentWindow;

				//因为前面关联了input.jsp所以它可以调用input.jsp里的方法
				//调用提交表单的方法获得返回值result
				var result=win.submitEmpForm();
				//如果添加成功刷新页面(重新定向)
				if (result == "success") {
					//把页面关闭再刷新
					diag.close();
					window.location.href="${path}/emp_list.action";
				}
			};
			diag.show();
		});

	});

	//引用dialog插件进行修改数据操作
	function editEmp(empId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 450;
		diag.ShowButtonRow=true;
		diag.Title = "员工修改";
		diag.URL = "${path}/emp_edit.action?emp.empId="+empId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;

			//因为前面关联了input.jsp所以它可以调用input.jsp里的方法
			//调用提交表单的方法获得返回值result
			var result=win.submitForm();
			//如果添加成功刷新页面(重新定向)
			if (result == "success") {
				//把页面关闭再刷新
				diag.close();
				window.location.href="${path}/emp_list.action";
			}
		};
		diag.show();
	}


	//是否删除的function
	function deleteEmpForm(empId) {
		Dialog.confirm('警告：您确认要删除这条数据吗?',function(){
			Dialog.alert("好的，数据已经被删除")
			window.location.href="${path}/emp_delete?emp.empId="+empId;
		});
	}

	//预分配角色的function(得到子页面选中的项)
	function grantPopRole(empId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height =400;
		diag.ShowButtonRow=true;
		diag.Title = "员工角色分配";
		diag.URL = "${path}/emp_listPop.action?emp.empId="+empId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result=win.gRole();
		    var roleResult=grantRole(empId,result);
			if (roleResult=="success") {
				Dialog.alert("角色分配成功",function () {
					//把页面关闭再刷新
					diag.close();
					//相当于刷新
					window.location.href="${path}/emp_list.action";
				});
			}
		};
		diag.show();
	}


	//真正进行分配角色(通过ajax的方式给后台传数据)
	function grantRole(empId,roleIds) {
		var result="yes";
		$.ajax({
			url:"${path}/ajax_emp_grantRole.action",
			type:"post",
			data:{
				"emp.empId":empId,
				//roleIds是选择的项是set集合
				"roleIds":roleIds
			},
			async:false,
			dataType:"text",
			success:function (responseText) {
				result=responseText;
			}
		})
		return result;
	}




</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/emp_list.action" method="post" id="empQueryForm">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					   style="font-size:14px; font-weight:bold; font-family:"黑体";">
				<tr>
					<td height="30">用&nbsp;户&nbsp;名</td>
					<td>
						<%--用作回显的(用了struts2的标签)--%>
						<s:textfield name="query.username" type="text" size="14"/>
					</td>
					<td>真实姓名</td>
					<td>
						<s:textfield name="query.name" type="text" size="14"/>
					</td>
					<td>电&nbsp;&nbsp;&nbsp;&nbsp;话</td>
					<td>
						<s:textfield name="query.tel" type="text" size="14"/>
					</td>
					<td>性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
					<td>
						<%--<select class="kuan">
                            <option value="-1">----请-选-择----</option>
                            <option value="1">男</option>
                            <option value="0">女</option>
                        </select>--%>
						<s:select list="#{'1':'男','0':'女'}" name="query.gender" cssClass="kuan" headerKey="" headerValue="----请-选-择----"/>
					</td>

					<td width="70"><a id="addEmpButton" href="javascript:void(0)"> <img src="${path}/images/can_b_02.gif" border="0" /> </a></td>
				</tr>
				<tr>
					<td  height="30">电子邮件</td>
					<td>
						<s:textfield name="query.email" type="text" size="14"/>
					</td>
					<td>出生日期</td>
					<td>
						<s:textfield name="query.startBir" type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
					</td>
					<td>出生日期</td>
					<td>
						<s:textfield name="query.endBir" type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
					</td>
					<td>部门名称</td>
					<td>
						<!--
                                使用struts的select标签从后台的数据库的数据来查询展示
                                list：取Action中传递来的集合
                                cssClass：样式
                                name：用于后端接收
                                headerKey：空选项的value
                                headerValue：空选项的文本
                                listKey：选项的value从集合中的一个属性;给后台传的就是listkey
                                listValue：选项的文本从集合中的实体类中的一个属性来取
                             -->
						<%--

						这儿是一个大坑 ，name必须设置成empQuery.depId 如果设置成empQuery.dep.depId
						那么还需要给dep进行赋值
						--%>
						<s:select list="#dList" name="query.depId" headerKey="" headerValue="----请-选-择----" listKey="depId" listValue="name" cssClass="kuan"/>
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
						<td width="8%" height="30">用户名</td>
						<td width="10%">真实姓名</td>
						<td width="5%">性别</td>
						<td width="12%">出生日期</td>
						<td width="10%">电话</td>
						<td width="12%">电子邮件</td>
						<td width="9%">所属部门</td>
						<td width="20%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="emp">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30"><s:property value="#emp.username"/></td>
							<td><s:property value="#emp.name"/></td>
							<td><s:property value="#emp.gender==1?'男':'女'"/></td>
							<td><s:date name="#emp.birthday" format="yyyy-MM-dd"/></td>
							<td><s:property value="#emp.tel"/></td>
							<td><s:property value="#emp.email"/></td>
							<td><s:property value="#emp.dep.name"/></td>
							<td>
								<img src="${path}/images/grant.png" width="23"/>
							<span style="line-height:12px; text-align:center;">
								<a href="javascript:void(0)" class="xiu" onclick="grantPopRole(<s:property value="#emp.empId"/>)">分配角色</a>
							</span>

								<img src="${path}/images/icon_3.gif" />
							<span style="line-height:12px; text-align:center;">
								<a href="javascript:void(0)" class="xiu" onclick="editEmp(<s:property value="#emp.empId"/>)">修改</a>
							</span>

								<img src="${path}/images/icon_04.gif"/>
							<span style="line-height:12px; text-align:center;">
								<a href="javascript:void(0)" class="xiu" onclick="deleteEmpForm(<s:property value="#emp.empId"/>)">删除</a>
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
