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
	                money1:{
	                	required:function(){
	                		if($("#repayType").val() == '1'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	},
	                	digits:function(){
	                		if($("#repayType").val() == '1'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}	
	                },
	                cashCouponId:{
	                	required:function(){
	                		if($("#repayType").val() == '2'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	},
	                	digits:function(){
	                		if($("#repayType").val() == '2'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}
	                },
	                money2:{
	                	required:function(){
	                		if($("#repayType").val() == '2' && $("#isCashMoney").val() == '2'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	},
	                	digits:function(){
	                		if($("#repayType").val() == '2' && $("#isCashMoney").val() == '2'){
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
	                },
	                channelIds:{
	                	required:function(){
	                		if($("#channelType").val() != '0'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}
	                },
	                byId:{
	                	required:function(){
	                		if($("#byType").val() == '2'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}
	                },
	                day:{
	                	required:function(){
	                		if($("#byType").val() == '2'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	},
	                	digits:function(){
	                		if($("#byType").val() == '2'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}	
	                },
	                content:{
	                	required:true
	                }
	            },
	            messages:{
	                name:{
	                    required:"名称不能为空"
	                },  
	                money1:{
	                	required:"赠送金额不能为空",
	                	digits:"赠送金额必须为整数"
	                },
	                cashCouponId:{
	                	required:"代金券ID不能为空",
	                	digits:"代金券ID必须为整数"
	                },
	                money2:{
	                	required:"赠送金额不能为空",
	                	digits:"赠送金额必须为整数"
	                },
	                startDate:{
	                	required:"请输入开始时间"
	                },
	                endDate:{
	                	required:"请输入结束时间"
	                },
	                channelIds:{
	                	required:"渠道ID不能为空"
	                },
	                byId:{
	                	required:"请输入包月ID"
	                },
	                day:{
	                	required:"请输入赠送时间",
	                	digits:"赠送时间必须为整数"
	                },
	                content:{
	                	required:"请输入顶部文案"
	                }
	            }
	        });    
	
	    });
    
        function showType(param){
        	if($(param).val() == "1"){
        		$("[name='showTypeTwo']").each(function(){
        			$(this).hide();
        		});
        		$("[name='showTypeOne']").each(function(){
        			$(this).show();
        		});
        		$("#cashCouponId").attr("disabled",true);
        		$("#isCashMoney").attr("disabled",true);
        	}else if($(param).val() == "2"){
        		$("[name='showTypeTwo']").each(function(){
        			$(this).show();
        		});
        		$("[name='showTypeOne']").each(function(){
        			$(this).hide();
        		});
        		$("#cashCouponId").attr("disabled",false);
        		$("#isCashMoney").attr("disabled",false);
        	}
        }
        
        
        function showMoney(param){
        	if($(param).val() == "1"){
        		$("[name='showTypeThree']").each(function(){
        			$(this).hide();
        		});
        		$("[name='showTypeFour']").each(function(){
        			$(this).show();
        		});
        	}else if($(param).val() == "2"){
        		$("[name='showTypeThree']").each(function(){
        			$(this).show();
        		});
        		$("[name='showTypeFour']").each(function(){
        			$(this).hide();
        		});
        	}
        }
        
        function showChannel(param){
        	if($(param).val() == "0"){
        		$("[name='showChannelOne']").each(function(){
        			$(this).hide();
        		});
        		$("[name='showChannelTwo']").each(function(){
        			$(this).show();
        		});
        		$("#channelIds").attr("disabled",true);
        	}else if($(param).val() == "1"){
        		$("[name='showChannelOne']").each(function(){
        			$(this).show();
        		});
        		$("[name='showChannelTwo']").each(function(){
        			$(this).hide();
        		});
        		$("#channelIds").attr("disabled",false);
        	}
        }
        
        function showBy(param){
        	if($(param).val() == "1"){
        		$("[name='showBy']").hide();
        		$("#byId").attr("disabled",true);
        		$("#day").attr("disabled",true);
        	}else if($(param).val() == "2"){
        		$("[name='showBy']").show();
	        	$("#byId").attr("disabled",false);
	    		$("#day").attr("disabled",false);
        	}
        }
        
        
        function save(){
        	if($("#repayType").val() == '1'){
        		$("#money").val($("#money1").val());
        	}else{
        		if($("#isCashMoney").val() == '2'){
	        		$("#money").val($("#money2").val());
        		}else{
        			$("#money").attr("disabled",true);
        		}
        	}
        	$("#form").submit();
        }
        function checkAll(){
        	if($("#all").is(':checked')){
        	    $("[name='version']").each(function(){
        	        $(this).attr("checked", true);
        	    })
        	}else{
        		$("[name='version']").each(function(){
        	        $(this).attr("checked", false);
        	    })
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
				绑定赠送添加
			  </div>	
                <form id ="form" action="<%=basePath%>/bind/addBind" method="post">
                	<input type="hidden" id="money" name="money">
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">名称：</th>
	                		<td style="width:35%"><input type="text" id="name" name="name"></td>
	                		<th style="width:15%">绑定类型：</th>
	                		<td style="width:35%">
			              	  <select name="bindType">
			              	   	   <option value="1">手机</option>	
			              	   	   <option value="2">QQ</option>	
			              	   	   <option value="3">微博</option>
			              	   	   <option value="4">微信</option>
			              	   </select>
							</td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">赠送类型：</th>
	                		<td style="width:35%">
			              	  <select name="repayType" id="repayType" onchange="showType(this)">
			              	   	   <option value="1">铜币</option>	
			              	   	   <option value="2">代金券</option>	
			              	   </select>
							</td>
							<th name="showTypeOne">赠送金额（单位：铜币）：</th>
	                		<td name="showTypeOne"><input type="text" id="money1" name="money1"></td>
	                		<th name="showTypeTwo" style="display:none">代金券ID：</th>
	                		<td name="showTypeTwo" style="display:none"><input type="text" id="cashCouponId" disabled="disabled" name="cashCouponId"></td>
	                	</tr>
	                	<tr name="showTypeTwo" style="display:none">
							<th>是否使用代金券金额：</th>
	                		<td>
	                		   <select name="isCashMoney" id="isCashMoney" disabled="disabled" onchange="showMoney(this)">
			              	   	   <option value="1">使用</option>	
			              	   	   <option value="2">不使用</option>	
			              	   </select>
	                		</td>
	                		<th name="showTypeFour"></th>
	                		<td name="showTypeFour"></td>
	                		<th name="showTypeThree" style="display:none">赠送金额（单位：铜币）：</th>
	                		<td name="showTypeThree" style="display:none"><input type="text" id="money2" name="money2"></td>
	                	</tr>
	                	
	                	<tr>
	                		<th>是否赠送包月：</th>
	                		<td>
	                		   <select name="byType" id="byType" onchange="showBy(this)">
			              	   	   <option value="1">不赠送</option>	
			              	   	   <option value="2">赠送</option>	
			              	   </select>
	                		</td>
	                		<th></th>
	                		<td></td>
	                	</tr>
	                	<tr name="showBy" style="display:none">
	                		<th>包月ID：</th>
	                		<td>
	                		   <input type="text" disabled="disabled" id="byId" name="byId"/>
	                		</td>
	                		<th>赠送时间：</th>
	                		<td><input type="text" disabled="disabled" id="day" name="day"/>&nbsp;天</td>
	                	</tr>
	                	
	                	<tr>
	                		<th>开始时间：</th>
	                		<td>
								<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})" name="startDate" id="startDate">
							</td>
							<th>结束时间：</th>
	                		<td>
		                		<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})" name="endDate" id="endDate">
	                		</td>
	                	</tr>
	                	
	                	<tr>
	                		<th>顶部文案：</th>
	                		<td colspan="3">
	                		   <input type="text" id="content" name="content" style="width:82%"/>
	                		</td>
	                	</tr>
	                	
	                	<tr>
	                		<th>渠道限制：</th>
	                		<td>
	                		   <select name="channelType" id="channelType" onchange="showChannel(this)">
			              	   	   <option value="0">无</option>
			              	   	   <option value="1">仅在</option>	
			              	   </select>
	                		</td>
	                		<th name="showChannelTwo"></th>
	                		<td name="showChannelTwo"></td>
	                		<th name="showChannelOne" style="display:none">渠道ID：</th>
	                		<td name="showChannelOne" style="display:none"><input type="text" id="channelIds" disabled="disabled" name="channelIds"></td>
	                	</tr>
	                	<tr>
	                		<th>版本选择：</th>
	                		<td colspan="3">
	                			<input type="checkbox" value="全部" id="all" onclick="checkAll()"/>全选&nbsp;	  
	                			<c:forEach var="ver" items="${versions }">
									<input type="checkbox" name="version" value="${ver.vcode }" />${ver.vcode }&nbsp;
	                			</c:forEach>
	                		</td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/bind/listBind'">
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
