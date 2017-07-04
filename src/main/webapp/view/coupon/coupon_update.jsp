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
	                money:{
	                	required:true,
	                	digits:true
	                },
	                type:{
	                	required:true
	                },
	                day:{
	                	required:function(){
	                		if($("#type").val() == '1'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	},
	                	digits:function(){
	                		if($("#type").val() == '1'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}	
	                },
	                startDate:{
	                	required:function(){
	                		if($("#type").val() == '0'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}
	                },
	                endDate:{
	                	required:function(){
	                		if($("#type").val() == '0'){
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
	                money:{
	                	required:"金额不能为空",
	                	digits:"金额必须为整数"
	                },
	                type:{
	                	required:"请选择有效期类型"
	                },
	                day:{
	                	required:"请输入自用户激活之日起时间",
	                	digits:"自用户激活之日起时间必须为整数"
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
    
    
        function showDate(param){
        	if($(param).val() == "0"){
        		$("[name='showTwo'],[name='showThree']").each(function(){
        			$(this).hide();
        		});
        		$("[name='showOne']").each(function(){
        			$(this).show();
        		});
        		$("#startDate").attr("disabled",false);
        		$("#endDate").attr("disabled",false);
        		$("#day").attr("disabled",true);
        	}else if($(param).val() == "1"){
        		$("[name='showOne'],[name='showThree']").each(function(){
        			$(this).hide();
        		});
        		$("[name='showTwo']").each(function(){
        			$(this).show();
        		});
	    		$("#startDate").attr("disabled",true);
	    		$("#endDate").attr("disabled",true);
	    		$("#day").attr("disabled",false);
        	}else{
        		$("[name='showOne'],[name='showTwo']").each(function(){
        			$(this).hide();
        		});
        		$("[name='showThree']").each(function(){
        			$(this).show();
        		});
	    		$("#startDate").attr("disabled",true);
	    		$("#endDate").attr("disabled",true);
	    		$("#day").attr("disabled",true);
        	}
        }
        
        function save(){
        	if($("#type").val() == '0'){
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				if(startDate != '' && endDate != ''){
					if(!checkDate(startDate,endDate)){
						alert("结束时间必须大于开始时间");
						return;
					}
				}
        	}
        	$("#form").submit();
        }
        
		function checkDate(startDate,endDate){
			var start=new Date(startDate.replace("-", "/").replace("-", "/"));
			var end=new Date(endDate.replace("-", "/").replace("-", "/"));
			if(end<start){
			 	return false;
			}
			return true;
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
				代金券修改
			  </div>	
                <form id ="form" action="<%=basePath%>/coupon/updateCoupon" method="post">
                	<input type="hidden" name="id" value="${coupon.id }"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">名称：</th>
	                		<td style="width:35%"><input type="text" id="name" name="name" value="${coupon.name }"></td>
	                		<th style="width:15%">类型：</th>
	                		<td style="width:35%">
			              	  <select name="couponType">
			              	   	   <option value="1" ${coupon.couponType=='1' ? "selected" : "" }>全部</option>	
			              	   	   <option value="2" ${coupon.couponType=='2' ? "selected" : "" }>代金券</option>	
			              	   </select>
							</td>
	                	</tr>
	                	<tr>
	                		<th> 有效期：</th>
	                		<td>
			              	  <select name="type" id="type" onchange="showDate(this)">
			              	   	   <option value=""></option>	
			              	   	   <option value="0" ${coupon.type=='0' ? "selected" : "" }>固定期限</option>	
			              	   	   <option value="1" ${coupon.type=='1' ? "selected" : "" }>自用户激活之日起</option>	
			              	   	   <option value="2" ${coupon.type=='2' ? "selected" : "" }>次月失效</option>
			              	   </select>
							</td>
							<th name="showThree"></th>
							<td name="showThree"></td>
	                		<th name="showOne" style="display:none">起止时间：</th>
	                		<td name="showOne"  style="display:none">
								<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})"  style="width:40%" name="startDate" id="startDate" value="${fn:substringBefore(coupon.startDate, ' ')}">
								-<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})"  style="width:40%" name="endDate" id="endDate" value="${fn:substringBefore(coupon.endDate, ' ')}">							
							</td>
							<th name="showTwo"  style="display:none">自用户激活之日起：</th>
	                		<td name="showTwo"  style="display:none"><input type="text" id="day" name="day" value="${coupon.day }"></td>
	                	</tr>
	                	<tr>
	                		<th>金额：</th>
	                		<td><input type="text" id="money" name="money" value="${coupon.money }"></td>
	                		<th></th>
	                		<td>
	                		</td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="修改" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/coupon/listCoupon'">
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
<script>
if($("#type").val() == "0"){
	$("[name='showTwo'],[name='showThree']").each(function(){
		$(this).hide();
	});
	$("[name='showOne']").each(function(){
		$(this).show();
	});
	$("#startDate").attr("disabled",false);
	$("#endDate").attr("disabled",false);
	$("#day").attr("disabled",true);
}else if($("#type").val() == "1"){
	$("[name='showOne'],[name='showThree']").each(function(){
		$(this).hide();
	});
	$("[name='showTwo']").each(function(){
		$(this).show();
	});
	$("#startDate").attr("disabled",true);
	$("#endDate").attr("disabled",true);
	$("#day").attr("disabled",false);
}else{
	$("[name='showOne'],[name='showTwo']").each(function(){
		$(this).hide();
	});
	$("[name='showThree']").each(function(){
		$(this).show();
	});
	$("#startDate").attr("disabled",true);
	$("#endDate").attr("disabled",true);
	$("#day").attr("disabled",true);
}

</script>
</html>
