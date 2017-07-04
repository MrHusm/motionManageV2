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
        	 window.location.href="<%=basePath%>/remind/deleteRemind?id="+id;
           }
        }
        
        function search(dqPage){
        	$("#dqPage").val(dqPage);
        	$("#form").submit();
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
				退出提醒管理&nbsp;&nbsp;
			  </div>	
                <form id ="form" action="<%=basePath%>/remind/listRemind" method="post">
	                <input type="hidden" name="dqPage" id="dqPage" value="1"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:10%">类型：</th>
	                		<td style="width:23%">
			              	  <select name="type">
			              	   	   <option value=""></option>	
			              	   	   <option value="0" ${condition.type=="0" ? "selected" : "" }>默认</option>	
			              	   	   <option value="1" ${condition.type=="1" ? "selected" : "" }>签到提醒</option>	
			              	   	   <option value="2" ${condition.type=="2" ? "selected" : "" }>运营活动</option>
			              	   </select>
							</td>
	                		<th style="width:10%">名称：</th>
	                		<td style="width:23%"><input type="text" id="name" name="name"  value="${condition.name }"></td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="搜索" onclick="search('1');">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="window.location.href='<%=basePath%>/remind/toAddRemind'">
	                </div>
                </form> 
			  
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
			<th class="tb_title" width="70">类型</th>
			<th class="tb_title" width="100">名称</th>
			<th class="tb_title" width="100">是否图片</th>
			<th class="tb_title" width="100">按钮文案</th>
			<th class="tb_title" width="100">开始时间</th>
			<th class="tb_title" width="100">结束时间</th>
			<th class="tb_title" width="70">操作</th>
        </tr>
     </thead>
    	<tbody>
			<c:forEach var="remind" items="${list }">
	    	    <tr class="odd gradeX warning">
					<td class="center">
						<c:if test="${remind.type=='0'}">默认</c:if>
						<c:if test="${remind.type=='1'}">签到提醒</c:if>
						<c:if test="${remind.type=='2'}">运营活动</c:if>
					</td>
					<td class="center">${remind.name }</td>
					<td class="center">
						<c:if test="${remind.isImg=='0'}">是</c:if>
						<c:if test="${remind.isImg=='1'}">否</c:if>
					</td>
					<td class="center">${remind.btnContent}</td>
					<td class="center">${remind.startDate}</td>
					<td class="center">${remind.endDate }</td>
					<td class="center">
						<a href="<%=basePath%>/remind/toUpdateRemind?id=${remind.id}">修改</a>&nbsp;
						<a href="javascript:void(delsure('${remind.id }'))">删除</a>
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
