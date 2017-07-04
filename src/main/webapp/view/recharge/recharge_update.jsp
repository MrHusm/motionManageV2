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
        	}else if($(param).val() == "1" || $(param).val() == "2"){
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
        	var amountIds = "";
        	$("#type [name='amountId']").each(function(){
        		amountIds = amountIds + $(this).val()+",";
    		});
        	var amountParam = "#type [name='amount']";
        	$(amountParam).each(function(){
        		amounts = amounts + $(this).val()+",";
    		});
        	if($("#repayType").val() == '1'){
        		var param = "#type [name='money']";
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
        		var param = "#type [name='cashCouponId']";
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
            		var paramMoney = "#type [name='money']";
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
        		$("#amountIds").val(amountIds);
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
				充值赠送修改
			  </div>	
                <form id ="form" action="<%=basePath%>/recharge/updateRecharge" method="post">
                	<input type="hidden" id="id" name="id" value="${recharge.id }">
                	<input type="hidden" name="firstFlag" value="0"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">名称：</th>
	                		<td style="width:35%"><input type="text" id="name" name="name" value="${recharge.name }"></td>
	                		<th style="width:15%">充值类型：</th>
	                		<td style="width:35%">
	                			<input type="hidden" name="rechargeType" id="rechargeType" value="${recharge.rechargeType}"/>
	                			<c:if test="${recharge.rechargeType == '1'}">移动话费</c:if>
	                			<c:if test="${recharge.rechargeType == '2'}">联通话费</c:if>
	                			<c:if test="${recharge.rechargeType == '3'}">电信话费</c:if>
	                			<c:if test="${recharge.rechargeType == '4'}">支付宝</c:if>
	                			<c:if test="${recharge.rechargeType == '5'}">银行卡</c:if>
	                			<c:if test="${recharge.rechargeType == '6'}">充值卡</c:if>
	                			<c:if test="${recharge.rechargeType == '7'}">微信</c:if>
							</td>
	                	</tr>
	                	<tr>
	                		<th>开始时间：</th>
	                		<td>
								<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})" name="startDate" id="startDate" value="${fn:substringBefore(recharge.startDate, ' ')}">
							</td>
							<th>结束时间：</th>
	                		<td>
		                		<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})" name="endDate" id="endDate" value="${fn:substringBefore(recharge.endDate, ' ')}">
	                		</td>
	                	</tr>
	                	<tr>
		                	<th style="width:15%">活动类型：</th>
	                		<td style="width:35%">
			              	  <select name="activityType" id="activityType">
			              	   	   <option value="1" ${recharge.activityType == "1" ? "selected" : "" }>默认</option>	
			              	   	   <option value="2" ${recharge.activityType == "2" ? "selected" : "" }>活动</option>
			              	   </select>
							</td>
							<th style="width:15%">赠送类型：</th>
	                		<td style="width:35%">
			              	  <select name="repayType" id="repayType" onchange="showType(this)">
			              	   	   <option value="1" ${recharge.repayType == "1" ? "selected" : "" }>铜币</option>
			              	   	   <option value="2" ${recharge.repayType == "2" ? "selected" : "" }>代金券</option>
			              	   </select>
							</td>
	                	</tr>
	                	<tr>
	                		<th>版本选择：</th>
	                		<td>
	                			<input type="checkbox" value="全部" id="all" onclick="checkAll()"/>全选&nbsp;
	                			<c:forEach var="ver" items="${versions }">
									<input type="checkbox" name="version" value="${ver.vcode }" ${fn:contains(recharge.version, ver.vcode) ? "checked" : "" }/>${ver.vcode }&nbsp;
	                			</c:forEach>
	                		</td>
	                		<th name="showTypeThree"></th>
	                		<td name="showTypeThree"></td>
							<th name="showTypeOne" style="display:none">是否使用代金券金额：</th>
	                		<td name="showTypeOne" style="display:none">
					  		    <select name="isCashMoney" id="isCashMoney" onchange="showMoney(this)">
					  	   	   		<option value="1" ${recharge.isCashMoney == "1" ? "selected" : "" }>使用</option>	
					  	   	   		<option value="2" ${recharge.isCashMoney == "2" ? "selected" : "" }>不使用</option>
					  	   		</select>
							</td>
	                	</tr>
	                	<tr>
	                		<th>渠道限制：</th>
	                		<td>
	                		   <select name="channelType" id="channelType" onchange="showChannel(this)">
			              	   	   <option value="0" ${recharge.channelType == "0" ? "selected" : "" }>无</option>
			              	   	   <option value="1" ${recharge.channelType == "1" ? "selected" : "" }>仅在</option>	
			              	   </select>
	                		</td>
	                		<td name="showChannelTwo"></th>
	                		<td name="showChannelTwo"></td>
	                		<th name="showChannelOne" style="display:none">渠道ID：</th>
	                		<td name="showChannelOne" style="display:none"><input type="text" id="channelIds" disabled="disabled" name="channelIds" value="${recharge.channelIds }"></td>
	                	</tr>
	                </table>
	                
	                <input type="hidden" name="amountIds" id="amountIds"/>
	                <input type="hidden" name="cashCouponIds" id="cashCouponIds"/>
					<input type="hidden" name="moneys" id="moneys"/>
					<input type="hidden" name="amounts" id="amounts"/>
					<!-- --------------------充值类型-------------------- -->
					<table class="table table-bordered table-hover" id="type">
						<c:forEach var="amount" items="${amounts }">
							<tr>
								<th>充值金额：${amount.amount }元</th>
						  		<th name="showTypeOne" style="display:none">代金券ID：</th>
								<td name="showTypeOne" style="display:none">
									<input type="hidden" name="amountId" value="${amount.id }"/>
									<input type="hidden" name="amount"  value="${amount.amount }"/>
									<input type="text" id="cashCouponId" name="cashCouponId" value="${amount.cashCouponId }"/>
								</td>
						  		<th name="showTypeTwo">赠送金额（单位：铜币）：</th>
								<td name="showTypeTwo"><input type="text" id="money" name="money" value="${amount.money }"/></td>
							</tr>
						</c:forEach>
					</table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="修改" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/recharge/listRecharge?firstFlag=0'">
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
	if($("#repayType").val() == "1"){
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
	}else if($("#repayType").val() == "2"){
		if($("#isCashMoney").val() == "1"){
    		$("[name='showTypeTwo']").each(function(){
    			$(this).hide();
    		});
		}else if($("#isCashMoney").val() == "2"){
    		$("[name='showTypeTwo']").each(function(){
    			$(this).show();
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

	if($("#channelType").val() == "0"){
		$("[name='showChannelOne']").each(function(){
			$(this).hide();
		});
		$("[name='showChannelTwo']").each(function(){
			$(this).show();
		});
		$("#channelIds").attr("disabled",true);
	}else if($("#channelType").val() == "1" || $("#channelType").val() == "2"){
		$("[name='showChannelOne']").each(function(){
			$(this).show();
		});
		$("[name='showChannelTwo']").each(function(){
			$(this).hide();
		});
		$("#channelIds").attr("disabled",false);
	}

</script>

</html>