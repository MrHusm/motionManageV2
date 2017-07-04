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
				退出提醒添加
			  </div>	
                <form id ="form" action="<%=basePath%>/remind/updateRemind" method="post">
                	<input type="hidden" name="id" value="${remind.id }"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">类型：</th>
	                		<td style="width:35%">
	                			<input type="hidden" name="type" id="type" value="${remind.type }"/>
	                			<c:if test="${remind.type=='0'}">默认</c:if>
	                			<c:if test="${remind.type=='1'}">签到活动</c:if>
	                			<c:if test="${remind.type=='2'}">运营活动</c:if>
							</td>
	                		<th style="width:15%">名称：</th>
	                		<td style="width:35%"><input type="text" id="name" name="name" value="${remind.name }"></td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">是否图片 ：</th>
	                		<td style="width:35%">
	                			<c:if test="${remind.isImg=='0'}">是</c:if>
	                			<c:if test="${remind.isImg=='1'}">否</c:if>
							</td>
							<c:if test="${remind.isImg == '0'}">
	                			<th name="imgYes">图片</th>
	                			<td name="imgYes"><img width="30%" src="http://zwsc.ikanshu.cn/remindImage/${remind.imgUrl }"></td>
							</c:if>
							<c:if test="${remind.isImg == '1'}">
	                			<th name="imgNo">文案</th>
	                			<td name="imgNo"><input type="text" name="content" value="${remind.content }"/></td>
							</c:if>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">按钮文案：</th>
	                		<td style="width:35%"><input type="text" name="btnContent" value="${remind.btnContent }"/></td>
	                		<th style="width:15%">按钮链接：</th>
	                		<td style="width:35%"><input type="text" name="btnUrl" value="${remind.btnUrl }"/></td>
	                	</tr>
	                	<c:if test="${remind.type=='2' }">
		                	<tr name="showDate">
		                		<th style="width:15%">开始时间：</th>
		                		<td style="width:35%">
									${fn:substringBefore(remind.startDate, ' ')}
								</td>
								<th style="width:15%">结束时间：</th>
		                		<td style="width:35%">
			                		${fn:substringBefore(remind.endDate, ' ')}
		                		</td>
		                	</tr>
	                	</c:if>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="修改" onclick="save()">
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
<script type="text/javascript">
</script>        
</html>