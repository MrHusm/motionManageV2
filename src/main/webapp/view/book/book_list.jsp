<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
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
        function delsure(bid,id){
           if(confirm("确认删除?")){
        	 window.location.href="<%=basePath%>/phb/updatePhbBids?bid="+bid+"&id="+id;
           }
        }
        
        function search(dqPage){
        	$("#dqPage").val(dqPage);
        	$("#form").submit();
        }
        function selectAll() {
         var checkboxs=document.getElementsByName("checkboxid");
         for (var i=0;i<checkboxs.length;i++) {
          var e=checkboxs[i];
          e.checked=!e.checked;
         }
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
				榜单图书管理&nbsp;&nbsp;
			  </div>	
                <form id ="form" action="<%=basePath%>/book/listBook" method="get">
	                <input type="hidden" name="dqPage" id="dqPage" value="1"/>
	                <input type="hidden" name="phbid" id="phbid" value="${phb.id }"/>
	                <input type="hidden" name="phb_name" id="phb_name" value="${condition.phb_name }"/>
	                <input type="hidden" name="ids" id="ids" value="${condition.ids }"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:20%">资产ID：</th>
	                		<td style="width:80%"><input type="text" id="id" name="id"  value="${condition.id }"></td>
	                	</tr>
	                	
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="搜索" onclick="search('1');">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="window.location.href='<%=basePath%>/book/toAddBook?id=${phb.id }'">
	                </div>
                </form> 
			  
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
				<th class="tb_title" width="70"><input type="button" class="btn-info" name="bt" value="全选" onclick="selectAll()"></th>
				<th class="tb_title" width="100">榜单名称</th>
				<th class="tb_title" width="100">资产ID</th>
				<th class="tb_title" width="100">书名</th>
				<th class="tb_title" width="100">封面</th>
				<th class="tb_title" width="100">排序</th>
				<th class="tb_title" width="70">操作</th>
        </tr>
    </thead>
    	<tbody>
			<c:forEach var="book" items="${list }">
	    	    <tr class="odd gradeX warning">
	    	    	<td class="center"><input type="checkbox"  name="checkboxid" id="checkboxid" value="${book.id }"></td>
	    	    	<td class="center">${condition.phb_name }</td>
	    	    	<td class="center">${book.zc_id }</td>
	    	    	<td class="center">${book.book_name }</td>
					<td class="center"><img  src="http://cdn.ikanshu.cn/211/images/${book.img_url }"></td>
					<td class="center">${book.xl }</td>
					<td class="center">
						<a href="javascript:void(delsure('${book.zc_id }','${phb.id }'))">删除</a>
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