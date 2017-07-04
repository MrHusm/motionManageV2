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
	            
	            rules:{
	            	version:{
	                    required:true
	                },
	                versionCode:{
	                	required:true,
	                	digits:true
	                },
	                channelId:{
	                	required:true
	                },
	                description:{
	                	required:true
	                }
	            },
	            messages:{
	                version:{
	                    required:"请选择版本"
	                },
	                versionCode:{
	                	required:"请输入版本号",
	                	digits:"版本号必须为数字"
	                },
	                channelId:{
	                	required:"请输入渠道号"
	                },
	                description:{
	                    required:"文案不能为空"
	                }
	            }
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
				版本升级添加
			  </div>
                <form id="form" action="<%=basePath%>/versionUp/addVersionUp" method="post">
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">版本：</th>
	                		<td style="width:35%">
			              	  <select name="version" id="version">
			              	  	<option value=""></option>
								<c:forEach var="ver" items="${versions }">
									<option value="${ver.vcode }">${ver.vcode }</option>
	                			</c:forEach>
			              	  </select>
							</td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">版本号：</th>
	                		<td style="width:35%">
	                			<input type="name" name="versionCode">
							</td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">渠道 ：</th>
	                		<td style="width:35%">
	                			<textarea rows="10" name="channelId" style="width:50%"></textarea>
							</td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">文案 ：</th>
	                		<td style="width:35%">
	                			<textarea rows="15" name="description" style="width:50%"></textarea>
							</td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/versionUp/listVersionUp'">
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
</body>
</html>