<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp"%>
<script type="text/javascript">
	function getNote() {
	   return $("#note").val();
	}
</script>
<div>
	<textarea id="note" rows="15" cols="80">请填入审核意见...</textarea>
</div>
