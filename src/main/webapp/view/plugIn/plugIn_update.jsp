<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	            	name:{
	                    required:true
	                },
	                in_version:{
	                	required:true
	                },
	                out_version:{
	                	required:true
	                }
	            },
	            messages:{
	                name:{
	                    required:"请输入插件名称"
	                },  
	                in_version:{
	                	required:"请输入插件内部版本号"
	                },
	                out_version:{
	                	required:"请输入插件外部版本号"
	                }
	            }
	        });    
	    });
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
				修改
			  </div>	
                <form id ="form" action="<%=basePath%>/plugIn/updatePlugIn" method="post">
                	<input type="hidden" name="id" value="${plugIn.id }" />
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">类型：</th>
	                		<td style="width:35%">
	                			<c:if test="${plugIn.type==1 }">pdf</c:if>
	                			<c:if test="${plugIn.type==2 }">语音插件</c:if>
							</td>
	                		<th style="width:15%">插件名称：</th>
	                		<td style="width:35%">
								<input type="text" name="name" value="${plugIn.name }"/>
							</td>
	                	</tr>
	                	<tr>
	                	    <th style="width:15%">插件内部版本号：</th>
	                		<td style="width:35%">
			              	  <input type="text" name="in_version" value="${plugIn.in_version }"/>
							</td>
	                		<th style="width:15%">插件外部版本号：</th>
	                		<td style="width:35%">
								<input type="text" name="out_version" value="${plugIn.out_version }"/>
							</td>
	                	</tr>	
	                	<tr>
	                		<th>插件上传：</th>
	                		<td>${plugIn.url }</td>
	                		<th>CPU类型：</th>
	                		<td>
		                		<c:set var="isarmeabi" value="false" />
		                		<c:set var="isarmeabi_v7a" value="false" />
		                		<c:set var="isarm64_v8a" value="false" />
		                		<c:set var="isx86" value="false" />
		                		<c:set var="isx86_64" value="false" />
		                		<c:set var="ismips" value="false" />
								<c:forEach var="item" items="${cpuTypes}">   
									<c:if test="${item eq 'armeabi'}">     
										<c:set var="isarmeabi" value="true" />  
									</c:if> 
									<c:if test="${item eq 'armeabi-v7a'}">     
										<c:set var="isarmeabi_v7a" value="true" />  
									</c:if> 
									<c:if test="${item eq 'arm64-v8a'}">     
										<c:set var="isarm64_v8a" value="true" />  
									</c:if> 
									<c:if test="${item eq 'x86'}">     
										<c:set var="isx86" value="true" />  
									</c:if> 
									<c:if test="${item eq 'x86_64'}">     
										<c:set var="isx86_64" value="true" />  
									</c:if> 
									<c:if test="${item eq 'mips'}">     
										<c:set var="ismips" value="true" />  
									</c:if> 
								</c:forEach>
	                		
	                		  <input type="checkbox" name="cpu_type" value="armeabi" <c:if test="${isarmeabi}">checked</c:if> />armeabi&nbsp;
	                		  <input type="checkbox" name="cpu_type" value="armeabi-v7a" <c:if test="${isarmeabi_v7a}">checked</c:if>/>armeabi-v7a&nbsp;
	                		  <input type="checkbox" name="cpu_type" value="arm64-v8a" <c:if test="${isarm64_v8a}">checked</c:if>/>arm64-v8a&nbsp;
	                		  <input type="checkbox" name="cpu_type" value="x86" <c:if test="${isx86}">checked</c:if>/>x86&nbsp;
	                		  <input type="checkbox" name="cpu_type" value="x86_64" <c:if test="${isx86_64}">checked</c:if>/>x86_64&nbsp;
	                		  <input type="checkbox" name="cpu_type" value="mips" <c:if test="${ismips}">checked</c:if>/>mips&nbsp;
			              	</td>
	                	</tr>   
	                	<tr>
	                		<th>应用版本：</th>
	                		<td colspan="3">
	                			<input type="checkbox" value="全部" id="all" onclick="checkAll()"/>全选&nbsp;
	                			<c:forEach var="ver" items="${versions }">
									<input type="checkbox" name="version" value="${ver.vcode }" ${fn:contains(plugIn.version, ver.vcode) ? "checked" : "" }/>${ver.vcode }&nbsp;
	                			</c:forEach>
	                		</td>
	                	</tr>             	
	                </table>
	                <div align="center">
	                   <input type="submit" class="btn-info" name="bt" value="修改">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/plugIn/listPlugIn'">
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
