<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html lang="en"><head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>运营管理平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="../../css_files/mybootstrap.css" rel="stylesheet">
    <link href="../../css_files/bootstrap-responsive.css" rel="stylesheet">
    <link href="../../css_files/style.css" rel="stylesheet" type="text/css" >
    <script type="text/javascript" src="../../js/jquery.js"></script>
    <script type="text/javascript" src="../../My97DatePicker/WdatePicker.js" defer="defer"></script>
    <link href="../../css/my.css" rel="stylesheet" type="text/css" >
    
    <script>
        function delsure(id,type){
           if(confirm("确认删除?")){
        	 var url = "<%=basePath%>/code/deleteCode?id="+id+"&type="+type;
        	 window.location.href= url;
           }
        }
        
        function search(dqPage){
        	$("#dqPage").val(dqPage);
        	$("#form").submit();
        }
        
        function exportCode(){
       	 	var url = "<%=basePath%>/code/exportCode?type=1&ecrId=${condition.ecrId }";
    	 	window.location.href= url;
        }
    </script>
    
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar">
<div id="wrap">
    <!-- 头部引用 -->
	<jsp:include page="../top.jsp"></jsp:include>
	<div class="container-fluid">
        <div class="row-fluid">
            <div class="span2" style="width: 140px;">
              <!-- 菜单引用 -->
              <jsp:include page="../menu.jsp"></jsp:include>
            </div>
            <div class="span10">
              <div class="hero-unit">
				兑换码信息列表&nbsp;&nbsp;
			  </div>
                <form id ="form" action="<%=basePath%>/code/listCode" method="post">
	                <input type="hidden" name="dqPage" id="dqPage" value="1"/>
	                <input type="hidden" name="type" id="type" value="1"/>
	                <input type="hidden" name="ecrId" id="ecrId" value="${condition.ecrId }"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th>兑换码：</th>
	                		<td><input type="text" id="code" name="code"  value="${condition.code }"></td>
	                	</tr>
	                </table>
	                <div align="center">
		               <input type="button" name="bt" class="btn-info" value="导出" onclick="exportCode();">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" name="bt" class="btn-info" value="搜索" onclick="search('1');">&nbsp;&nbsp;&nbsp;&nbsp;
	                </div>
                </form> 
			  
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
			<th class="tb_title" width="11%">兑换码</th>
			<th class="tb_title" width="11%">单个码可兑换次数</th>
			<th class="tb_title" width="15%">单个码单个用户兑换限定</th>
			<th class="tb_title" width="10%">已兑换数量</th>
        </tr>
    </thead>
    	<tbody>
			<c:forEach var="code" items="${list }">
	    	    <tr class="odd gradeX warning">
					<td class="center">${code.code }</td>
					<td class="center">${code.cc}</td>
					<td class="center">${code.userCc }</td>
					<td class="center">${code.exchgNum}</td>
				</tr>
			</c:forEach>
    	  </tbody>
    </table>
	<div align="center">
	共 ${condition.totalRow} 条, ${condition.totalPage} 页&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:void(search('${condition.upPageNum}'))"> 上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	${condition.dqPage }
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(search('${condition.downPageNum }'))">下一页</a> </div>
            </div><!--/span-->
          </div><!--/row-->
        </div><!--/.fluid-container-->
	<footer>
		<div id="footer">
		<div class="container">
			<p class="muted credit">© Copyright © 2005-2023  All Rights Reserved.</p>
		</div>
		</div>
	</footer>
</div>
</body></html>
