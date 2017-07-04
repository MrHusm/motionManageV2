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
	                }
	            },
	            messages:{
	                name:{
	                    required:"名称不能为空"
	                },  
	                startDate:{
	                	required:"请输入开始时间"
	                },
	                endDate:{
	                	required:"请输入结束时间"
	                },
	                channelIds:{
	                	required:"渠道ID不能为空"
	                }
	            }
	        });    
	
	    });
	    
	    function showAmountType(param){
	    	if($(param).val() == "1"){
        		$("#type2").hide();
        		$("#type3").hide();
        		$("#type4").hide();
        		$("#type5").hide();
        		$("#type6").hide();
        		$("#type7").hide();
        		$("#type1").show();
	    	}else if($(param).val() == "2"){
        		$("#type1").hide();
        		$("#type3").hide();
        		$("#type4").hide();
        		$("#type5").hide();
        		$("#type6").hide();
        		$("#type7").hide();
        		$("#type2").show();
	    	}else if($(param).val() == "3"){
        		$("#type1").hide();
        		$("#type2").hide();
        		$("#type4").hide();
        		$("#type5").hide();
        		$("#type6").hide();
        		$("#type7").hide();
        		$("#type3").show();
	    	}else if($(param).val() == "4"){
        		$("#type1").hide();
        		$("#type2").hide();
        		$("#type3").hide();
        		$("#type5").hide();
        		$("#type6").hide();
        		$("#type7").hide();
        		$("#type4").show();
	    	}else if($(param).val() == "5"){
        		$("#type1").hide();
        		$("#type2").hide();
        		$("#type3").hide();
        		$("#type4").hide();
        		$("#type6").hide();
        		$("#type7").hide();
        		$("#type5").show();
	    	}else if($(param).val() == "6"){
        		$("#type1").hide();
        		$("#type2").hide();
        		$("#type3").hide();
        		$("#type4").hide();
        		$("#type5").hide();
        		$("#type7").hide();
        		$("#type6").show();
	    	}else if($(param).val() == "7"){
        		$("#type1").hide();
        		$("#type2").hide();
        		$("#type3").hide();
        		$("#type4").hide();
        		$("#type5").hide();
        		$("#type6").hide();
        		$("#type7").show();
	    	}
	    }
    
        function showType(param){
        	if($(param).val() == "1"){
        		$("[name='showTypeOne']").each(function(){
        			$(this).hide();
        		});
        		$("[name='showTypeTwo']").each(function(){
        			$(this).show();
        		});
        		$("[name='showTypeThree']").each(function(){
        			$(this).show();
        		});
        		
        		$("#isCashMoney").attr("disabled",true);
        	}else if($(param).val() == "2"){
        		if($("#isCashMoney").val() == "1"){
	        		$("[name='showTypeTwo']").each(function(){
	        			$(this).hide();
	        		});
        		}
        		$("[name='showTypeThree']").each(function(){
        			$(this).hide();
        		});
        		$("[name='showTypeOne']").each(function(){
        			$(this).show();
        		});
        		
        		$("#isCashMoney").attr("disabled",false);
        	}
        }
        
        function showMoney(param){
        	if($(param).val() == "1"){
        		$("[name='showTypeTwo']").each(function(){
        			$(this).hide();
        		});
        	}else if($(param).val() == "2"){
        		$("[name='showTypeTwo']").each(function(){
        			$(this).show();
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
        
        function save(){
        	var flag = true;
        	var cashCouponIds = "";
        	var moneys = "";
        	var amounts = "";
        	var amountParam = "#type"+$("#rechargeType").val()+" [name='amount']";
        	$(amountParam).each(function(){
        		amounts = amounts + $(this).val()+",";
    		});
        	if($("#repayType").val() == '1'){
        		var param = "#type"+$("#rechargeType").val()+" [name='money']";
        		$(param).each(function(){
        			if($(this).val() == ''){
        				moneys = "";
        				alert("请输入赠送金额");
        				$(this).focus();
        				flag = false;
        				retrun;
        			}else if(!checkNum($(this).val())){
        				moneys = "";
        				alert("赠送金额必须为整数");
        				$(this).focus();
        				flag = false;
        				retrun;
        			}else{
        				moneys = moneys + $(this).val()+",";
        			}
        		});
        	}else{
        		var param = "#type"+$("#rechargeType").val()+" [name='cashCouponId']";
        		$(param).each(function(){
        			if($(this).val() == ''){
        				cashCouponIds = "";
        				alert("请输入代金券ID");
        				$(this).focus();
        				flag = false;
        				retrun;
        			}else if(!checkNum($(this).val())){
        				cashCouponIds = "";
        				alert("代金券ID必须为整数");
        				$(this).focus();
        				flag = false;
        				retrun;
        			}else{
        				cashCouponIds = cashCouponIds + $(this).val() +",";
        			}
        		});
        		
        		if($("#isCashMoney").val() == '2'){
            		var paramMoney = "#type"+$("#rechargeType").val()+" [name='money']";
            		$(paramMoney).each(function(){
            			if($(this).val() == ''){
            				moneys = "";
            				alert("请输入赠送金额");
            				$(this).focus();
            				flag = false;
            				retrun;
            			}else if(!checkNum($(this).val())){
            				moneys = "";
            				alert("赠送金额必须为整数");
            				$(this).focus();
            				flag = false;
            				retrun;
            			}else{
            				moneys = moneys + $(this).val() + ",";
            			}
            		});
        		}
        	}
        	if(flag){
        		$("#cashCouponIds").val(cashCouponIds);
        		$("#moneys").val(moneys);
        		$("#amounts").val(amounts);
	        	$("#form").submit();
        	}
        }
        
        function checkNum(param){  
            var reg = new RegExp("^[0-9]*$");  
         	if(!reg.test(param)){  
            	return false;  
         	}else{
         		return true;
         	}  
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
				充值赠送添加
			  </div>	
                <form id ="form" action="<%=basePath%>/recharge/addRecharge" method="post">
                	<input type="hidden" name="firstFlag" value="0"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">名称：</th>
	                		<td style="width:35%"><input type="text" id="name" name="name"></td>
	                		<th style="width:15%">充值类型：</th>
	                		<td style="width:35%">
			              	  <select name="rechargeType" id="rechargeType" onchange="showAmountType(this)">
			              	   	   <option value="1">移动话费</option>
			              	   	   <option value="2">联通话费</option>
			              	   	   <option value="3">电信话费</option>
			              	   	   <option value="4">支付宝</option>
			              	   	   <option value="5">银行卡</option>
			              	   	   <option value="6">充值卡 </option>
			              	   	   <option value="7">微信</option>
			              	   </select>
							</td>
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
		                	<th style="width:15%">活动类型：</th>
	                		<td style="width:35%">
			              	  <select name="activityType" id="activityType">
			              	   	   <option value="1">默认</option>	
			              	   	   <option value="2">活动</option>
			              	   </select>
							</td>
							<th style="width:15%">赠送类型：</th>
	                		<td style="width:35%">
			              	  <select name="repayType" id="repayType" onchange="showType(this)">
			              	   	   <option value="1">铜币</option>
			              	   	   <option value="2">代金券</option>
			              	   </select>
							</td>
	                	</tr>
	                	<tr>
	                		<th>版本选择：</th>
	                		<td>
	                			<input type="checkbox" value="全部" id="all" onclick="checkAll()"/>全选&nbsp;
	                			<c:forEach var="ver" items="${versions }">
									<input type="checkbox" name="version" value="${ver.vcode }" />${ver.vcode }&nbsp;
	                			</c:forEach>
	                		</td>
	                		<th name="showTypeThree"></th>
	                		<td name="showTypeThree"></td>
							<th name="showTypeOne" style="display:none">是否使用代金券金额：</th>
	                		<td name="showTypeOne" style="display:none">
					  		    <select name="isCashMoney" id="isCashMoney" disabled="disabled" onchange="showMoney(this)">
					  	   	   		<option value="1">使用</option>	
					  	   	   		<option value="2">不使用</option>
					  	   		</select>
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
	                		<td name="showChannelTwo"></th>
	                		<td name="showChannelTwo"></td>
	                		<th name="showChannelOne" style="display:none">渠道ID：</th>
	                		<td name="showChannelOne" style="display:none"><input type="text" id="channelIds" disabled="disabled" name="channelIds"></td>
	                	</tr>
	                </table>
	                <jsp:include page="amount_type.jsp" flush="true" ></jsp:include>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/recharge/listRecharge'">
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