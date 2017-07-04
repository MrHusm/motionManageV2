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
	                content:{
	                	required:function(){
	                		if($("#isImg").val() == '1'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}
	                },
	                file:{
	                	required:function(){
	                		if($("#isImg").val() == '0'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}
	                },
	            	btnContent:{
	                    required:true
	                },
	            	btnUrl:{
	                    required:true
	                },
	                startDate:{
	                	required:function(){
	                		if($("#type").val() == '2'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}
	                },
	                endDate:{
	                	required:function(){
	                		if($("#type").val() == '2'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}
	                }
	            },
	            messages:{
	                name:{
	                    required:"名称不能为空"
	                },  
	                content:{
	                    required:"文案不能为空"
	                },
	                file:{
	                	required:"请上传图片"
	                },
	            	btnContent:{
	                    required:"按钮文案不能为空"
	                },
	            	btnUrl:{
	                    required:"按钮链接不能为空"
	                },
	                startDate:{
	                	required:"请输入开始时间"
	                },
	                endDate:{
	                	required:"请输入结束时间"
	                }
	            }
	        });    
	
	    });
        
        function showImg(param){
        	if($(param).val() == "0"){
        		$("[name='imgNo']").each(function(){
        			$(this).hide();
        		});
        		$("[name='imgYes']").each(function(){
        			$(this).show();
        		});
        	}else if($(param).val() == "1"){
        		$("[name='imgYes']").each(function(){
        			$(this).hide();
        		});
        		$("[name='imgNo']").each(function(){
        			$(this).show();
        		});
        	}
        }
        
        function showType(param){
        	if($(param).val() == "0"||$(param).val() == "1"){
        		$("[name='showDate']").each(function(){
        			$(this).hide();
        		});
        	}else if($(param).val() == "2"){
        		$("[name='showDate']").each(function(){
        			$(this).show();
        		});
        	}
        }
        
        function save(){
        	var type = $("#type").val();
        	var submitFlag = true;
        	if(type == "2"){
	        	var startDate = $("#startDate").val();
	        	var endDate = $("#endDate").val();
	        	var url="/remind/isValidDate?startDate="+startDate+"&endDate="+endDate;
	            $.ajax({
	        	       url:url,
	        	       type:'GET',
	        	       async: false,
	        		   timeout: 10000,
	        		   error: function(){},
	        		   success: function(data){
	        		   		var json = JSON.parse(data);
	        				var flag = json.flag;
	        				if(flag){
	        					alert("请输入有效的起止日期");
	        					submitFlag = false;
	        				}
	        		   }
	             });
        	}
        	if(submitFlag){
	        	$("#form").submit();
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
				退出提醒添加
			  </div>	
                <form id ="form" action="<%=basePath%>/remind/addRemind" method="post" enctype="multipart/form-data">
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">类型：</th>
	                		<td style="width:35%">
			              	  <select name="type" id="type" onchange="showType(this)">
			              	  	<c:if test="${mrFlag }">
			              	   	   <option value="0">默认</option>	
			              	  	</c:if>
			              	  	<c:if test="${qdFlag }">
			              	   	   <option value="1">签到活动</option>	
			              	  	</c:if>
			              	   	   <option value="2">运营活动</option>
			              	   </select>
							</td>
	                		<th style="width:15%">名称：</th>
	                		<td style="width:35%"><input type="text" id="name" name="name"></td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">是否图片 ：</th>
	                		<td style="width:35%">
			              	  <select name="isImg" id="isImg" onchange="showImg(this)">
			              	   	   <option value="0">是</option>	
			              	   	   <option value="1">否</option>	
			              	   </select>
							</td>
                			<th name="imgNo" style="display:none">文案</th>
                			<td name="imgNo" style="display:none"><input type="text" name="content" /></td>
                			<th name="imgYes">图片</th>
                			<td name="imgYes"><input type="file" id="file" name="file"></td>
	                	</tr>
	                	<tr>
	                		<th>按钮文案：</th>
	                		<td><input type="text" name="btnContent" /></td>
	                		<th>按钮链接：</th>
	                		<td><input type="text" name="btnUrl" /></td>
	                	</tr>
	                	
	                	<tr name="showDate" ${(mrFlag || qdFlag) ? "style='display:none'" : "" }>
	                		<th>开始时间：</th>
	                		<td>
								<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})" name="startDate" id="startDate">
							</td>
							<th>结束时间：</th>
	                		<td>
		                		<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})" name="endDate" id="endDate">
	                		</td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/remind/listRemind'">
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
