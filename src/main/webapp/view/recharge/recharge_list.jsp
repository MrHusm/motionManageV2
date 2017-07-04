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
    <script type="text/javascript" src="../../My97DatePicker/WdatePicker.js" defer="defer"></script>
    <link href="../../css/my.css" rel="stylesheet" type="text/css" >
    
    <script>
        function delsure(id){
           if(confirm("确认删除?")){
        	 window.location.href="<%=basePath%>/recharge/deleteRecharge?firstFlag=${condition.firstFlag }&id="+id;
           }
        }
        
        function search(dqPage){
        	$("#dqPage").val(dqPage);
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
				<c:if test="${condition.firstFlag==1 }">首充</c:if>
				<c:if test="${condition.firstFlag!=1 }">充值</c:if>赠送管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="btn-info" name="bt" value="重置缓存" onclick="javascript:window.location.href='<%=basePath%>/recharge/resetRedisCache?firstFlag=${condition.firstFlag }'">
			  </div>	
                <form id ="form" action="<%=basePath%>/recharge/listRecharge" method="post">
                	<input type="hidden" name="firstFlag" id="firstFlag" value="${condition.firstFlag }"/>
	                <input type="hidden" name="dqPage" id="dqPage" value="1"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:10%">名称：</th>
	                		<td style="width:23%"><input type="text" id="name" name="name"  value="${condition.name }"></td>
	                		<th style="width:10%">充值方式：</th>
	                		<td style="width:23%">
			              	  <select name="rechargeType">
			              	   	   <option value=""></option>	
			              	   	   <option value="1" ${condition.rechargeType=="1" ? "selected" : "" }>移动话费</option>
			              	   	   <option value="2" ${condition.rechargeType=="2" ? "selected" : "" }>联通话费</option>
			              	   	   <option value="3" ${condition.rechargeType=="3" ? "selected" : "" }>电信话费</option>
			              	   	   <option value="4" ${condition.rechargeType=="4" ? "selected" : "" }>支付宝</option>
			              	   	   <option value="5" ${condition.rechargeType=="5" ? "selected" : "" }>银行卡</option>
			              	   	   <option value="6" ${condition.rechargeType=="6" ? "selected" : "" }>充值卡 </option>
			              	   	   <option value="7" ${condition.rechargeType=="7" ? "selected" : "" }>微信</option>
			              	   </select>
							</td>
							<th style="width:10%">赠送类型：</th>
	                		<td style="width:24%">
			              	  <select name="repayType">
			              	   	   <option value=""></option>	
			              	   	   <option value="1" ${condition.repayType=="1" ? "selected" : "" }>铜币</option>	
			              	   	   <option value="2" ${condition.repayType=="2" ? "selected" : "" }>代金券</option>	
			              	   </select>
							</td>
	                	</tr>
	                	<tr>
	                		<th> 渠道ID：</th>
	                		<td><input type="text" id="channelId" name="channelId"  value="${condition.channelId }"></td>
	                		<th>活动类型：</th>
	                		<td>
	                		   <select name="activityType">
			              	   	   <option value=""></option>	
			              	   	   <option value="1" ${condition.activityType=="1" ? "selected" : "" }>默认</option>	
			              	   	   <option value="2" ${condition.activityType=="2" ? "selected" : "" }>活动</option>	
			              	   </select>	
	                		</td>
	                		<th>起止时间：</th>
	                		<td>
								<input type="text" class="Wdate input" style="width:40%" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})" name="startDate" id="startDate" value="${condition.startDate }">
	                			-<input type="text" class="Wdate input" style="width:40%" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})" name="endDate" id="endDate" value="${condition.endDate }">
							</td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="搜索" onclick="search('1');">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="window.location.href='<%=basePath%>/recharge/toAddRecharge?firstFlag=${condition.firstFlag}'">
	                </div>
                </form> 
			  
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
				<th class="tb_title" width="100">名称</th>
				<th class="tb_title" width="70">充值方式</th>
				<th class="tb_title" width="100">赠送类型</th>
				<th class="tb_title" width="100">活动类型</th>
				<th class="tb_title" width="100">开始时间</th>
				<th class="tb_title" width="100">结束时间</th>
				<th class="tb_title" width="100">修改时间</th>
				<th class="tb_title" width="70">操作</th>
        </tr>
      </thead>
    	<tbody>
			<c:forEach var="recharge" items="${list }">
	    	    <tr class="odd gradeX warning">
					<td class="center">${recharge.name }</td>
					<td class="center">
						<c:if test="${recharge.rechargeType=='1'}">移动话费</c:if>
						<c:if test="${recharge.rechargeType=='2'}">联通话费</c:if>
						<c:if test="${recharge.rechargeType=='3'}">电信话费</c:if>
						<c:if test="${recharge.rechargeType=='4'}">支付宝</c:if>
						<c:if test="${recharge.rechargeType=='5'}">银行卡</c:if>
						<c:if test="${recharge.rechargeType=='6'}">充值卡</c:if>
						<c:if test="${recharge.rechargeType=='7'}">微信</c:if>
					</td>
					<td class="center">
						<c:if test="${recharge.repayType=='1'}">铜币</c:if>
						<c:if test="${recharge.repayType=='2'}">代金券</c:if>
					</td>
					<td class="center">
						<c:if test="${recharge.activityType=='1'}">默认</c:if>
						<c:if test="${recharge.activityType=='2'}">活动</c:if>
					</td>
					<td class="center">${recharge.startDate}</td>
					<td class="center">${recharge.endDate }</td>
					<td class="center">${recharge.updateDate }</td>
					<td class="center">
						<a href="<%=basePath%>/recharge/toUpdateRecharge?id=${recharge.id}&firstFlag=${recharge.firstFlag}">修改</a>&nbsp;
						<a href="javascript:void(delsure('${recharge.id }'))">删除</a>
					</td>
				  </tr>
			</c:forEach>
    	  </tbody>
    </table>
	<div align="center">
	共 ${condition.totalRow} 条, ${condition.totalPage} 页&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:void(search('${condition.upPageNum}'))"> 上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	${condition.dqPage }
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(search('${condition.downPageNum }'))">下一页</a> </div>
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
