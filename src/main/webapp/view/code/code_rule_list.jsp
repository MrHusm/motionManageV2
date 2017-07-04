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
       	 	var url = "<%=basePath%>/code/exportCode?type=1";
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
	                <input type="hidden" name="type" id="type" value="2"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th>名称：</th>
	                		<td><input type="text" id="name" name="name"  value="${condition.name }"></td>
	                		<th>兑换码：</th>
	                		<td><input type="text" id="code" name="code"  value="${condition.code }"></td>
	                		<th> 渠道ID：</th>
	                		<td><input type="text" id="channelId" name="channelId"  value="${condition.channelId }"></td>
	                	</tr>
	                	<tr>
	                		<th>开始时间：</th>
	                		<td>
								<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})" name="startDate" id="startDate" value="${condition.startDate }">
							</td>
	                		<th>结束时间：</th>
	                		<td>
	                			<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})" name="endDate" id="endDate" value="${condition.endDate }">
	                		</td>
	                		<th></th>
	                		<td></td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" name="bt" class="btn-info" value="搜索" onclick="search('1');">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" name="bt" class="btn-info" value="添加" onclick="window.location.href='<%=basePath%>/code/toAddCode'">
	                </div>
                </form> 
			  
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
			<th class="tb_title" width="11%">名称</th>
			<th class="tb_title" width="11%">生成兑换码数量</th>
			<th class="tb_title" width="10%">兑换码前X位</th>
			<th class="tb_title" width="11%">单个码可兑换次数</th>
			<th class="tb_title" width="15%">单个码单个用户兑换限定</th>
			<th class="tb_title" width="10%">已兑换数量</th>
			<th class="tb_title" width="11%">开始时间</th>
			<th class="tb_title" width="11%">结束时间</th>
			<th class="tb_title" width="10%">操作</th>
        </tr>
    </thead>
    	<tbody>
			<c:forEach var="codeRule" items="${list }">
	    	    <tr class="odd gradeX warning">
					<td class="center"><a href="<%=basePath%>/code/listCode?ecrId=${codeRule.id}&type=1">${codeRule.name }</a></td>
					<td class="center">${codeRule.cc }</td>
					<td class="center">${codeRule.top_name }</td>
					<td class="center">${codeRule.allCc}</td>
					<td class="center">${codeRule.userCc }</td>
					<td class="center">${codeRule.exchgNum}</td>
					<td class="center">${codeRule.startDate }</td>
					<td class="center">${codeRule.endDate }</td>
					<td class="center">
						<a href="<%=basePath%>/code/toUpdateCode?id=${codeRule.id}&type=${condition.type}">修改</a>&nbsp;
						<a href="javascript:void(delsure('${codeRule.id }','${condition.type}'))">删除</a>
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
</body></html>