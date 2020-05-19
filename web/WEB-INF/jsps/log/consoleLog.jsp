<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="8%" height="30">审核人</td>
						<td width="12%">时间</td>
						<td width="30%">备注</td>
					</tr>
					<s:iterator value="#logList" var="log">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30">
								<s:property value="#log.emp.name"></s:property>
							</td>
							<td>
								<s:property value="#log.optTime"></s:property>
							</td>
							<td>
								<s:property value="#log.note"></s:property>
							</td>
					</s:iterator>
				</table>
