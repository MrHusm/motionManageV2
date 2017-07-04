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
    <script type="text/javascript" src="../../js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="../../My97DatePicker/WdatePicker.js" defer="defer"></script>
    <link href="../../css/my.css" rel="stylesheet" type="text/css" >
    <style type="text/css">
	    #form label.error{
		    color:Red;
		    font-size:15px;
	    }        
    </style>
    
    <script>
	    $(function(){
	        var validate = $("#form").validate({
	            debug: true, //调试模式取消submit的默认提交功能   
	            //errorClass: "label.error", //默认为错误的样式类为：error   
	            focusInvalid: false, //当为false时，验证无效时，没有焦点响应  
	            onkeyup: false,   
	            submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form   
	                form.submit();   //提交表单   
	            },   
	            
	        });    
	
	    });
    
     
        
        function save(){
        	
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
				子榜单修改
			  </div>	
                <form id ="form" action="<%=basePath%>/phb/updateZphb" method="post" enctype = "multipart/form-data">
                	<input type="hidden" id="id" name="id" value="${phb.id }">
					
	                <input type="hidden" id="fid" name = "fid" value ="${fid }">
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">榜单名称：</th>
	                		<td style="width:35%"><input type="text" id="name" name="name" value="${phb.name }"></td>
	                	</tr>
	                	<c:if test="${phb.css=='4'}">
	                	<tr>
	                		<th style="width:15%">指数关键词：</th>
	                		<td style="width:35%"><input type="text" id="keyword" name="keyword"></td>
	                	</tr>
						</c:if>
						
						<c:if test="${phb.css=='3'}">
							<th style="width:15%">榜单图片：</th>
	                		<td style="width:35%">
								<img src="http://zwsc.ikanshu.cn/paihangImage/${phb.icon }">
	                			<input type="file" id="iconn" name="iconn" value="">
							</td>
						</c:if>
	                	<c:if test="${phb.dataType=='2'}">
	                	<tr>
							<th style="width:15%">榜单驱动：</th>
	                		<td style="width:35%">
	                				<select name="newQdId" id="newQdId" >
				                		<c:forEach var="qd" items="${qdList}" >
						              	   	   <option value="${qd.id}" ${phb.qdId==qd.id ? "selected" :"" }>${qd.name}</option>	
				                		</c:forEach>
			              	   		</select>
	                	    </td>
	                	</tr>
	                	  </c:if>
	               	</table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/phb/toUpdatePhb?id=${fid}'">
	                </div>
                </form> 
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
