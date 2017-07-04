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
	            	type:{
	            		required:true
	            	},
	            	name:{
	                    required:true
	                },
	                cc:{
	                	required:true,
	                	digits:true
	                },
	                userCc:{
	                	required:true,
	                	digits:true
	                },
	                startDate:{
	                	required:true
	                },
	                endDate:{
	                	required:true
	                },
	                cashCouponId:{
	                	digits:true
	                },
	                money:{
	                	digits:true
	                },
	                top_name:{
	                	required:function(){
	                		if($("#type").val() == '2'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	},
	                	maxlength:function(){
	                		if($("#type").val() == '2'){
		                		return 6;
	                		}
	                	}
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
	            	type:{
	            		required:"请选择兑换码类型"
	            	},
	                name:{
	                    required:"名称不能为空"
	                },
	                cc:{
	                	required:"请输入兑换次数或生成个数",
	                	digits:"请输入整数"
	                },
	                userCc:{
	                	required:"请输入用户兑换个数限定",
	                	digits:"用户兑换个数限定必须为整数"
	                },
	                startDate:{
	                	required:"请输入开始时间"
	                },
	                endDate:{
	                	required:"请输入结束时间"
	                },
	                cashCouponId:{
	                	digits:"代金券ID必须为整数"
	                },
	                money:{
	                	digits:"铜币必须为整数"
	                },
	                top_name:{
	                	required:"请设定前6位",
	                	maxlength:"长度必须小于等于6"
	                },
	                channelIds:{
	                	required:"渠道ID不能为空"
	                }
	            }
	        });    
	
	    });
	    
        function save(){
        	var flag = true;
    		$("[name='bookIds']").each(function(){
    			if($(this).val() == ''){
					alert("请输入图书ID");
					$(this).focus();
					flag = false;
					return false;
    			}
    		});
    		if(flag){
    			var reg = new RegExp("^[0-9]*$");
	    		$("[name='chpIds']").each(function(){
	    			if($(this).val() == ''){
						alert("请输包含章节");
						$(this).focus();
						flag = false;
						return false;
	    			}
	    			if(!reg.test($(this).val())){
	    				flag = false;
						$(this).focus();
						flag = false;
						return false; 
	    		    }
	    		});
    		}
    		if(flag){
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
    			if(startDate != '' && endDate != ''){
    				if(!checkDate(startDate,endDate)){
    					alert("结束时间必须大于开始时间");
    					return;
    				}
    			}
				$("#form").submit();
    		}
        }
        
		function checkDate(startDate,endDate){
			var start=new Date(startDate.replace("-", "/").replace("-", "/"));
			var end=new Date(endDate.replace("-", "/").replace("-", "/"));
			if(end<start){
			 	return false;
			}
			return true;
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
        
       function addBook(){
    	   var table = $("#book");
    	   var row = $("<tr></tr>"); 
    	   var td1 = $("<td></td>"); 
    	   var td2 = $("<td></td>");
    	   var td3 = $("<td></td>");
    	   td1.append("<input type='text' id='bookIds' name='bookIds'>");
    	   td2.append("<input type='text' id='chpIds' name='chpIds'>");
    	   td3.append("<input type='button' class='btn-info' value='删除' onclick='deleteRow(this)'>");
    	   row.append(td1);
    	   row.append(td2);
    	   row.append(td3);
    	   table.append(row); 
       }
       
       function deleteRow(param){
    	   $(param).parent().parent().remove();
       }
       
       function goBack(){
    	   window.location.href='<%=basePath%>/code/listCode?type=2';
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
				兑换码修改
			  </div>	
                <form id ="form" method="post" action="<%=basePath%>/code/updateCodeRule">
                	<input type="hidden" name="id" value="${code.id }">
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">名称：</th>
	                		<td style="width:35%"><input type="text" id="name" name="name" value="${code.name }"></td>
	                		<th style="width:15%">生成兑换码数量：</th>
	                		<td style="width:35%">${code.cc }</td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">单个码可兑换次数：</th>
	                		<td style="width:35%">${code.allCc }</td>
	                		<th>单个码单个用户兑换限定：</th>
	                		<td>${code.userCc}</td>
	                	</tr>
	                	<tr>
	                		<th>绑定手机号：</th>
	                		<td>
	                			<select name="telFlag">
			              	   	   <option value="0" ${code.telFlag =='0' ? 'selected' : '' }>否</option>	
			              	   	   <option value="1" ${code.telFlag =='1' ? 'selected' : '' }>是</option>
			              	   </select>
	                		</td>
	                		<th>是否验证imsi号：</th>
	                		<td>
	                			<select name="imsiFlag">
			              	   	   <option value="0" ${code.imsiFlag =='0' ? 'selected' : '' }>否</option>
			              	   	   <option value="1" ${code.imsiFlag =='1' ? 'selected' : '' }>是</option>	
			              	   </select>
	                		</td>
	                	</tr>
	                	<tr>
	                		<th>开始时间：</th>
	                		<td>
			              	  <input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})" name="startDate" id="startDate" value="${fn:substringBefore(code.startDate, ' ')}">
							</td>
	                		<th>结束时间：</th>
	                		<td>
								<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})" name="endDate" id="endDate" value="${fn:substringBefore(code.endDate, ' ')}">
							</td>
	                	</tr>
	                	<tr>
	                		<th>代金券ID：</th>
	                		<td><input type="text" id="cashCouponId" name="cashCouponId" value="${code.cashCouponId }"></td>
	                		<th>铜币（单位：铜币）：</th>
	                		<td><input type="text" id="money" name="money" value="${code.money }"></td>
	                	</tr>
	                	<tr>
	                		<th>VIP（天）：</th>
	                		<td><input type="text" id="vipDay" name="vipDay" value="${code.vipDay }"></td>
	                		<th>设定兑换码前X位：</th>
	                		<td>${topName }</td>
	                	</tr>
	                	<tr>
	                		<th>渠道限制：</th>
	                		<td>
	                		   <select name="channelType" id="channelType" onchange="showChannel(this)">
			              	   	   <option value="0" ${code.channelType == '0' ? 'selected' : '' }>无</option>
			              	   	   <option value="1" ${code.channelType == '1' ? 'selected' : '' }>仅在</option>
			              	   </select>
	                		</td>
	                		<td name="showChannelTwo"></th>
	                		<td name="showChannelTwo"></td>
	                		<th name="showChannelOne" style="display:none"> 渠道ID：</th>
	                		<td name="showChannelOne" style="display:none"><input type="text" id="channelIds" name="channelIds" value="${code.channelIds }"></td>
	                	</tr>
	                </table>
	                
	                <div>
	                   <input type="button" class="btn-info" name="bt" value="添加图书" onclick="addBook()">
	                </div>
	                <table class="table table-bordered table-hover" id="book">
	                	<tr>
	                		<th>图书ID：</th>
	                		<th>包含章节（0表示全本）：</th>
	                		<th></th>
	                	</tr>
	                	<c:forEach var="id" items="${ids }" varStatus="status">
		                	<tr>
	               	    	   <td><input type="text" id="bookIds" name="bookIds" value="${id[0] }"></td>
	  	   					   <td><input type="text" id="chpIds" name="chpIds" value="${id[1] }"></td>
	  	   					   <td><input type='button' class="btn-info" value='删除' onclick='deleteRow(this)'></td>
		                	</tr>
	                	</c:forEach>
	                </table>
	                
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="修改" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="goBack()">
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
<script type="text/javascript">

if($("#channelType").val() == "0"){
	$("[name='showChannelOne']").each(function(){
		$(this).hide();
	});
	$("[name='showChannelTwo']").each(function(){
		$(this).show();
	});
	$("#channelIds").attr("disabled",true);
}else if($("#channelType").val() == "1"){
	$("[name='showChannelOne']").each(function(){
		$(this).show();
	});
	$("[name='showChannelTwo']").each(function(){
		$(this).hide();
	});
	$("#channelIds").attr("disabled",false);
}
</script>
</body></html>
