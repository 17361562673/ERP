<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<script type="text/javascript">
	$(function () {
		/*
            * 这是抽取的分页条
            *
            * */
		//获得总页数和当前的页码(并把字符串类型转换成数值类型)
		var totalPage = parseInt($("#totalPage").val());
		var pageNo=parseInt($("#cpageNo").val());
		//进行判断
		if (pageNo==1&&totalPage==1) {
			//让首页按钮隐藏
			$("#fir").hide();
			//让上一页按钮隐藏
			$("#pre").hide();
			//让下一页按钮隐藏
			$("#next").hide();
			//让末页按钮隐藏
			$("#last").hide();
		}else if (pageNo==1&&totalPage>pageNo) {
			$("#fir").hide();
			$("#pre").hide();
			$("#next").show();
			$("#last").show();
		}else if (pageNo>1&&totalPage>pageNo) {
			$("#fir").show();
			$("#pre").show();
			$("#next").show();
			$("#last").show();
		}else if (pageNo>1&&totalPage==pageNo) {
			$("#fir").show();
			$("#pre").show();
			$("#next").hide();
			$("#last").hide();
		}

		//如果只有一页就不必有跳转按钮
		 if (totalPage==1) {
			$("#sPageNo").hide();
			$("#jump").hide();
		}
		//下一页的事件
		$("#next").click(function () {
			$("#pageNo").val(pageNo+1);
			$("form:first").submit();
		});
		//上一页的事件
		$("#pre").click(function () {
			$("#pageNo").val(pageNo-1);
			$("form:first").submit();
		})
		//首页的事件
		$("#fir").click(function () {
			$("#pageNo").val(1);
			$("form:first").submit();
		})
		//末页的事件
		$("#last").click(function () {
			$("#pageNo").val(totalPage);
			$("form:first").submit();
		})



		//点击跳转到某个页
		$("#jump").click(function () {
			//获得文本框中输入的页码
		  var skipPage=$("#sPageNo").val();
		  var reg=/^\d{1,}$/;
			if (!reg.test(skipPage)||(parseInt(skipPage))>totalPage||(parseInt(skipPage))<1) {
				alert("请输入1-" + totalPage + "的数字");
			} else {
				var skipPageNo=parseInt($("#sPageNo").val());
				//将该页码赋值给pageNo并提交到后台
				$("#pageNo").val(skipPageNo);
				//将表单提交到后台
				$("form:first").submit();
			}
		})




	})

</script>
<br/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="51%">&nbsp;
			<%--用两个隐藏域计算是否展示哪些按钮--%>
			<input type="hidden" id="totalPage" value="<s:property value="#page.totalPage"/>">
			<input type="hidden" id="cpageNo" value="<s:property value="#page.pageNo"/>">
			<input type="hidden" id="pageNo" name="query.pageNo" value="<s:property value="#page.pageNo"/>">
		</td>
		<td width="13%">共<s:property value="#page.totalCount"/>条记录
		<td width="6%">
		<input type="button" class="but" id="fir" value="首页">
		</td>
		<td width="6%">
			<input type="button" class="but" id="pre" value="上一页">
		</td>
		<td width="6%">
				<input type="button" class="but" id="next" value="下一页">
		</td>
		<td width="6%">
			<input type="button" class="but" id="last" value="尾页">
		</td>
		<%--填入要跳转的数字--%>
		<td><input type="text" id="sPageNo" size="1"></td>
		<%--跳转按钮--%>
		<td><input type="button" class="but" id="jump" value="跳转"></td>
		<td width="12%">当前第<span style="color:red;"><s:property value="#page.pageNo"/></span>/<s:property value="#page.totalPage"/>页</td>
	</tr>
</table>
