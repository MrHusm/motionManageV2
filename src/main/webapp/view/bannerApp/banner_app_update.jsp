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
	                appName:{
	                	required:function(){
	                		if($("#type").val() == '1' && $("#appType").val() == '1'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}	
	                },
	                appVersion:{
	                	required:function(){
	                		if($("#type").val() == '1' && $("#appType").val() == '1'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}	
	                },
	                file1:{
	                	required:function(){
	                		if($("#type").val() == '1' && $("#appType").val() == '1'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}	
	                },
	                url1:{
	                	required:function(){
	                		if($("#type").val() == '1' && $("#appType").val() == '1'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}	
	                },
	                appPackName:{
	                	required:function(){
	                		if($("#type").val() == '1' && $("#appType").val() == '1'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}	
	                },
	                file2:{
	                	required:function(){
	                		if($("#type").val() == '1' && $("#appType").val() == '2'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}	
	                },
	                url2:{
	                	required:function(){
	                		if($("#type").val() == '1' && $("#appType").val() == '2'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}	
	                },
	                sdkInfo:{
	                	required:function(){
	                		if($("#type").val() == '2'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}	
	                },	                
	                money:{
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
	                startDate:{
	                	required:true
	                },
	                endDate:{
	                	required:true
	                },
	                channelIds:{
	                	required:function(){
	                		if($("#channelType").val() == '1'||$("#channelType").val() == '2'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}	
	                }
	            },
	            messages:{
	                name:{
	                    required:"请输入广告名称"
	                },
	                appName:{
	                	required:"请输入应用名称"
	                },
	                appVersion:{
	                	required:"请输入应用版本"
	                },
	                file1:{
	                	required:"请上传应用logo"
	                },
	                url1:{
	                	required:"请输入应用地址"
	                },
	                appPackName:{
	                	required:"请输入应用包名"
	                },
	                file2:{
	                	required:"请上传广告图片"
	                },
	                url2:{
	                	required:"请输入应用地址"
	                },
	                sdkInfo:{
	                	required:"请输入SDK信息"
	                },	                
	                money:{
	                	required:"请输入奖励额度",
	                	digits:"奖励额度必须为数字"
	                },	                
	                cashCouponId:{
	                	required:"请输入代金券ID",
	                	digits:"代金券ID必须为数字"
	                },                
	                startDate:{
	                	required:"开始时间不能为空"
	                },
	                endDate:{
	                	required:"结束时间不能为空",
	                },
	                channelIds:{
	                	required:"请输入渠道ID"
	                }
	            }
	        });    
	
	    });
        
        
        function showRepayType(param){
        	if($(param).val() == '1'){
        		$("[name='repayTypeTwo']").each(function(){
        	        $(this).hide();
        	    })
        	    $("[name='repayTypeOne']").each(function(){
        	        $(this).show();
        	    })
        	}else{
        		$("[name='repayTypeOne']").each(function(){
        	        $(this).hide();
        	    })
        	    $("[name='repayTypeTwo']").each(function(){
        	        $(this).show();
        	    })
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
				广告修改
			  </div>
                <form id="form" action="<%=basePath%>/bannerApp/updateBannerApp" method="post" enctype="multipart/form-data">
                	<input type="hidden" name="id" value="${bannerApp.id }" />
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">显示页面：</th>
	                		<td style="width:35%">
			              	  <select name="pageType" id="pageType">
								<option value="1" ${bannerApp.pageType == '1' ? 'selected' : '' }>应用推荐页面</option>
								<option value="2" ${bannerApp.pageType == '2' ? 'selected' : '' }>每日福利页面</option>
							  </select>	 
							</td>
	                		<th style="width:15%">广告名称：</th>
	                		<td style="width:35%">
	                			<input type="name" name="name" value="${bannerApp.name }">
							</td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">方式：</th>
	                		<td style="width:35%">
	                			<input type="hidden" name="type" id="type" value="${bannerApp.type }" />
	                			<c:if test="${bannerApp.type==1 }">直投</c:if>
	                			<c:if test="${bannerApp.type==2 }">SDK</c:if>
							</td>							
	                		<th style="width:15%;">广告类型：</th>
	                		<td style="width:35%;">
	                			<input type="hidden" name="appType" id="appType" value="${bannerApp.appType }" />
	                		    <c:if test="${bannerApp.appType==1 }">应用推荐</c:if>
	                			<c:if test="${bannerApp.appType==2 }">图片</c:if>
							</td>
	                	</tr>
	                </table>	
	                <table class="table table-bordered table-hover" id="type_1" style="display:none">
	                	<tr>
	                		<th style="width:15%">应用名称：</th>
	                		<td style="width:35%">
	                			<input type="appName" name="appName" value="${bannerApp.appName }">
							</td>
	                		<th style="width:15%">应用版本：</th>
	                		<td style="width:35%">
	                			<input type="appVersion" name="appVersion" value="${bannerApp.appVersion }">
							</td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">应用简介：</th>
	                		<td style="width:35%">
	                			<input type="appIntro" name="appIntro" value="${bannerApp.appIntro }">
							</td>
	                		<th style="width:15%">应用logo：</th>
	                		<td style="width:35%">
	                			<img src="http://zwsc.ikanshu.cn/bannerAppImage/${bannerApp.img}"  style="width:80%;height:20%"/>
							</td>
	                	</tr>
	                	<tr>
                		    <th>应用地址：</th>
                			<td><input type="text" id="url1" name="url1" value="${bannerApp.url }"></td>
                			<th>应用大小：</th>
                			<td><input type="text" id="size" name="size" value="${bannerApp.size }"></td>
	                	</tr>
	                	<tr>
                		    <th>应用包名：</th>
                			<td><input type="text" id="appPackName" name="appPackName" value="${bannerApp.appPackName }"></td>
                			<th></th>
                			<td></td>
	                	</tr>
	                </table>	
	                <table class="table table-bordered table-hover" id="type_2" style="display:none">	                	
	                    <tr>
	                		<th style="width:15%">广告图片：</th>
	                		<td style="width:35%">
	                			<img src="http://zwsc.ikanshu.cn/bannerAppImage/${bannerApp.img}"  style="width:80%;height:20%"/>
							</td>
                		    <th style="width:15%">应用地址：</th>
                			<td style="width:35%"><input type="text" id="url2" name="url2"  value="${bannerApp.url }"></td>								
	                	</tr>
	                </table>	
	                <table class="table table-bordered table-hover" id="type_3" style="display:none">
	                	<tr>
	                		<th style="width:15%">SDK信息：</th>
                			<td><input type="text" id="sdkInfo" name="sdkInfo" value="${bannerApp.sdkInfo }" style="width:83%"></td>
	                	</tr>
	                </table>
	                <table class="table table-bordered table-hover">
						<tr>
                		    <th style="width:15%">奖励内容：</th>
                			<td style="width:35%">
                			   <select name="repayType" id="repayType" onchange="showRepayType(this)">
			              	   	   <option value="1" ${bannerApp.repayType == '1' ? 'selected' : '' }>铜币</option>	
			              	   	   <option value="2" ${bannerApp.repayType == '2' ? 'selected' : '' }>代金券</option>	
			              	   </select>
			              	</td>
                			<th name="repayTypeOne" style="width:15%">奖励额度：</th>
	                		<td name="repayTypeOne" style="width:35%"><input type="text" id="money" name="money" value="${bannerApp.money }"></td>
	                		<th name="repayTypeTwo" style="display:none" style="width:15%">代金券ID：</th>
	                		<td name="repayTypeTwo" style="display:none" style="width:35%"><input type="text" id="cashCouponId" name="cashCouponId" value="${bannerApp.cashCouponId }"></td>
	                	</tr>
	                	<tr>
	                		<th>开始时间：</th>
	                		<td>
								<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01 %H:00:00',dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})" name="startDate" id="startDate" value="${fn:substringBefore(bannerApp.startDate, '.')}">
							</td>
							<th>结束时间：</th>
	                		<td>
		                		<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01 %H:00:00',dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})" name="endDate" id="endDate" value="${fn:substringBefore(bannerApp.endDate, '.')}">
	                		</td>
	                	</tr>                	
	                	<tr>
	                		<th>渠道限制：</th>
	                		<td>
	                			<select name="channelType" id="channelType" onchange="showChannel(this)">
			              	   	   <option value="0" ${bannerApp.channelType == '0' ? 'selected' : '' }>全部</option>
			              	   	   <option value="1" ${bannerApp.channelType == '1' ? 'selected' : '' }>仅在</option>
			              	   	   <option value="2" ${bannerApp.channelType == '2' ? 'selected' : '' }>仅不在</option>	
			              	   </select>
	                		</td>
	                		<th>渠道ID：</th>
	                		<td><input type="text" id="channelIds" name="channelIds" value="${bannerApp.channelIds }"></td>
	                	</tr>
	                	<tr>
	                		<th>版本选择：</th>
	                		<td colspan="3">
	                			<input type="checkbox" value="全部" id="all" onclick="checkAll()"/>全选&nbsp;
	                			<c:forEach var="ver" items="${versions }">
									<input type="checkbox" name="versions" value="${ver.vcode }" ${fn:contains(bannerApp.versions, ver.vcode) ? "checked" : "" }/>${ver.vcode }&nbsp;
	                			</c:forEach>
	                		</td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/bannerApp/listBannerApp?pageType=${bannerApp.pageType }'">
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
if($("#type").val() == '1'){
	$("#type_3").hide();
	if($("#appType").val()=='1'){
		$("#type_2").hide();
   		$("#type_1").show();
	}else{
		$("#type_1").hide();
   		$("#type_2").show();
	}
}else{
	$("#type_1").hide();
	$("#type_2").hide();
	$("#type_3").show();
}

if($("#type").val() == '1'){
	if($("#appType").val() == '1'){
		$("#type_2").hide();
		$("#type_1").show();
	}else{
		$("#type_1").hide();
		$("#type_2").show();
	}
}

if($("#repayType").val() == '1'){
	$("[name='repayTypeTwo']").each(function(){
        $(this).hide();
    })
    $("[name='repayTypeOne']").each(function(){
        $(this).show();
    })
}else{
	$("[name='repayTypeOne']").each(function(){
        $(this).hide();
    })
    $("[name='repayTypeTwo']").each(function(){
        $(this).show();
    })
}
</script>
</body>
</html>