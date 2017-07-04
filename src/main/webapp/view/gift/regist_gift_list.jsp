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
        	 window.location.href="<%=basePath%>/registGift/deleteRegistGift?id="+id;
           }
        }
        
        function add(){
			var url = "<%=basePath%>/registGift/toAddRegistGift";
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
				新手礼包管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  </div>
                <form id ="form" action="<%=basePath%>/registGift/listRegistGift" method="post">
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th>方案名称：</th>
	                		<td><input type="text" id="name" name="name"  value="${condition.name }"></td>
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
			<th class="tb_title" width="10%">ID</th>
			<th class="tb_title" width="20%">方案名称</th>
			<th class="tb_title" width="10%">奖品</th>
			<th class="tb_title" width="20%">弹窗文案</th>
			<th class="tb_title" width="15%">生效时间</th>
			<th class="tb_title" width="15%">失效时间</th>
			<th class="tb_title" width="10%">操作</th>
        </tr>
    </thead>
    	<tbody>
			<c:forEach var="gift" items="${list }">
	    	    <tr class="odd gradeX warning">
					<td class="center">${gift.id }</td>
					<td class="center">${gift.name }</td>
					<td class="center">
						<c:if test="${gift.type == 0 }">
							${gift.num }天VIP
						</c:if>
						<c:if test="${gift.type == 1 }">
							${gift.num }铜币
						</c:if>	
					</td>
					<td class="center">${gift.content }</td>
					<td class="center">${gift.startDate }</td>
					<td class="center">${gift.endDate }</td>
					<td class="center">
						<a href="<%=basePath%>/registGift/toUpdateRegistGift?id=${gift.id}">修改</a>&nbsp;
						<a href="javascript:void(delsure('${gift.id }'))">删除</a>
					</td>
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
</body>
</html>
