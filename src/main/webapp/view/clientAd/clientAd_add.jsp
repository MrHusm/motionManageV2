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
	            	name:{
	                    required:true
	                },
	            	pauseTime:{
	                    required:true,
	                    digits:true
	                },
	                file:{
	                	required:function(){
	                		if($("#type").val() == '0'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}
	                },
	                url:{
	                	required:function(){
	                		if($("#type").val() == '0'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}
	                },
	                startDate:{
	                	required:true
	                },
	                endDate:{
	                	required:true
	                }
	            },
	            messages:{
	                name:{
	                    required:"请输入启动图名称"
	                },
	                pauseTime:{
	                	required:"请输入停顿时间",
	                	digits:"停顿时间必须为整数"
	                },
	                file:{
	                	required:"请上传图片"
	                },
	                url:{
	                	required:"请输入跳转链接"
	                },
	                startDate:{
	                	required:"开始时间不能为空"
	                },
	                endDate:{
	                	required:"结束时间不能为空",
	                }
	            }
	        });    
	
	    });
        
        function showType(param){
        	if($(param).val() == '0'){
        		$("#api").hide();
        		$("#yuny").show();
        	}else{
        		$("#yuny").hide();
        		$("#api").show();
        	}
        }
        
        
        function checkAll(){
        	if($("#all").is(':checked')){
        	    $("[name='versions']").each(function(){
        	        $(this).attr("checked", true);
        	    })
        	}else{
        		$("[name='versions']").each(function(){
        	        $(this).attr("checked", false);
        	    })
        	}
        }
	    
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
				启动图添加
			  </div>
                <form id="form" action="<%=basePath%>/clientAd/addClientAd" method="post" enctype="multipart/form-data">
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">启动图名称：</th>
	                		<td style="width:35%">
	                			<input type="name" name="name">
							</td>
	                		<th style="width:15%">类型：</th>
	                		<td style="width:35%">
			              	  <select name="type" id="type" onchange="showType(this)">
								<option value="0">运营配置</option>
								<option value="1">广告API</option>
							   </select>	 
							</td>							
	                	</tr>
	                	<tr id="yuny">
                		    <th>广告图片</th>
                			<td><input type="file" id="file" name="file"></td>
                		    <th>跳转链接</th>
                			<td><input type="text" id="url" name="url"></td>	                	
	                	</tr>
	                	<tr id="api" style="display:none">
	                		<th style="width:15%">选择API：</th>
	                		<td style="width:35%">
			              	  <select name="apiId" id="apiId">
			              	  	<c:forEach  var="api" items="${apis }">
			              	  		<option value="${api.id }">${api.name }</option>
			              	  	</c:forEach>
							  </select>	 
							</td>
                		    <th></th>
                			<td></td>	                	
	                	</tr>
	                	<tr>
                		    <th>停顿时间</th>
                			<td><input type="text" id="pauseTime" name="pauseTime"></td>
                			<th></th>
                			<td></td>
	                	</tr>
	                	<tr>
	                		<th>开始时间：</th>
	                		<td>
								<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01 %H:00:00',dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})" name="startDate" id="startDate">
							</td>
							<th>结束时间：</th>
	                		<td>
		                		<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01 %H:00:00',dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})" name="endDate" id="endDate">
	                		</td>
	                	</tr>	                	
	                	<tr>
	                		<th>渠道ID：</th>
	                		<td colspan="3"><input type="text" id="channelIds" name="channelIds" style="width:83%"></td>
	                	</tr>
	                	<tr>
	                		<th>版本选择：</th>
	                		<td colspan="3">
	                			<input type="checkbox" value="全部" id="all" onclick="checkAll()"/>全选&nbsp;
	                			<c:forEach var="ver" items="${versions }">
									<input type="checkbox" name="versions" value="${ver.vcode }" />${ver.vcode }&nbsp;
	                			</c:forEach>
	                		</td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/clientAd/listClientAd'">
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