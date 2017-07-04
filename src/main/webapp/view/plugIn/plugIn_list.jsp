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
        function delsure(id){
           if(confirm("确认删除?")){
        	 window.location.href="<%=basePath%>/plugIn/deletePlugIn?id="+id;
           }
        }
        
        function add(){
			var url = "<%=basePath%>/plugIn/toAddPlugIn";
			window.location.href=url;
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
				插件管理
			  </div>
                <form id ="form" action="<%=basePath%>/plugIn/listPlugIn" method="post">
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th>类型：</th>
	                		<td>
			              	  <select name="type" id="type" onchange="javascript:$('#form').submit()">
			              	  	   <option value=""></option>
			              	   	   <option value="1" ${condition.type=="1" ? "selected" : ""}>pdf</option>	
			              	   	   <option value="2" ${condition.type=="2" ? "selected" : ""}>语音插件</option>
			              	   </select>
							</td>
							<th>名称：</th>
	                		<td>
			              	  <input name="name" id="name" value="${condition.name }">
							</td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="submit" class="btn-info" name="bt" value="搜索">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="add()">
	                </div>
                </form> 
			  
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
				<th class="tb_title" width="10%">插件类型</th>
				<th class="tb_title" width="20%">名称</th>
				<th class="tb_title" width="15%">大小</th>
				<th class="tb_title" width="15%">内部版本号</th>
				<th class="tb_title" width="20%">外部版本号</th>
				<th class="tb_title" width="20%">操作</th>
        </tr>
    </thead>
    	<tbody>
			<c:forEach var="plugIn" items="${list }">
	    	    <tr class="odd gradeX warning">
					<td class="center">
						<c:if test="${plugIn.type == 1 }">
							pdf
						</c:if>
						<c:if test="${plugIn.type == 2 }">
							语音插件
						</c:if>	
					</td>
					<td class="center">${plugIn.name }</td>
					<td class="center">${plugIn.size }</td>
					<td class="center">${plugIn.in_version }</td>
					<td class="center">${plugIn.out_version }</td>
					<td class="center">
						<a href="<%=basePath%>/plugIn/toUpdatePlugIn?id=${plugIn.id}">修改</a>&nbsp;
						<a href="javascript:void(delsure('${plugIn.id }'))">删除</a>
					</td>
				  </tr>
			</c:forEach>
    	  </tbody>
    </table>
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
</body>
</html>
