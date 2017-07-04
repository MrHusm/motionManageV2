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
	                num:{
	                	required:true,
	                	digits:true
	                },
	                content:{
	                    required:true
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
	                    required:"请输入方案名称"
	                },  
	                num:{
	                	required:"请输入奖励数量",
	                	digits:"奖励数量必输为整数"
	                },
	                content:{
	                    required:"请输入弹窗文案"
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
                <form id ="form" action="<%=basePath%>/registGift/updateRegistGift" method="post">
                	<input type="hidden" name="id" value="${gift.id }" />
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">方案名称：</th>
	                		<td style="width:35%"><input type="text" name="name" value="${gift.name }"/></td>
	                		<th style="width:15%">奖励类型：</th>
	                		<td style="width:35%">
			              	  <select name="type">
			              	   	   <option value="0" ${gift.type=='0' ? 'selected' : '' }>VIP</option>	
			              	   	   <option value="1" ${gift.type=='1' ? 'selected' : '' }>铜币</option>	
			              	   </select>
							</td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">奖励数量：</th>
	                		<td style="width:35%"><input type="text" name="num" value="${gift.num }"/></td>
	                		<th style="width:15%">弹窗文案：</th>
	                		<td style="width:35%"><input type="text" name="content" value="${gift.content }"/></td>
	                	</tr>
	                	<tr>
	                		<th>开始时间：</th>
	                		<td>
								<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})" name="startDate" id="startDate" value="${fn:substringBefore(gift.startDate, ' ')}">
							</td>
							<th>结束时间：</th>
	                		<td>
		                		<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})" name="endDate" id="endDate" value="${fn:substringBefore(gift.endDate, ' ')}">
	                		</td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">渠道列表：</th>
	                		<td colspan="3">
								<input type="text" name="channelIds" style="width:83%" value="${gift.channelIds }"/>
							</td>
	                	</tr>	                	
	                </table>
	                <div align="center">
	                   <input type="submit" class="btn-info" name="bt" value="修改">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/registGift/listRegistGift'">
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
