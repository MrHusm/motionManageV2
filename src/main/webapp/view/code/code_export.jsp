<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%
	response.addHeader("Content-Disposition", "attachment;filename="+ new String("兑换码.xls".getBytes("utf-8"), "ISO-8859-1"));
%>  
</head>
<body data-spy="scroll" data-target=".bs-docs-sidebar">
	<table class="table table-bordered table-hover" border="1">
    	<tr>
			<th class="tb_title" width="11%">兑换码</th>
			<th class="tb_title" width="11%">单个码可兑换次数</th>
			<th class="tb_title" width="15%">单个码单个用户兑换限定</th>
			<th class="tb_title" width="10%">已兑换数量</th>
        </tr>
		<c:forEach var="code" items="${list }">
    	    <tr class="odd gradeX warning">
				<td class="center">${code.code } </td>
				<td class="center">${code.cc}</td>
				<td class="center">${code.userCc }</td>
				<td class="center">${code.exchgNum}</td>
			 </tr>
		</c:forEach>
    </table>
</body>
</html>
