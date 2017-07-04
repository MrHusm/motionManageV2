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
        	 window.location.href="<%=basePath%>/bind/deleteBind?id="+id;
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
				绑定赠送管理&nbsp;&nbsp;
				<input type="button" class="btn-info" name="bt" value="重置缓存" onclick="javascript:window.location.href='<%=basePath%>/bind/resetRedisCache'">
			  </div>	
                <form id ="form" action="<%=basePath%>/bind/listBind" method="post">
	                <input type="hidden" name="dqPage" id="dqPage" value="1"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:10%">名称：</th>
	                		<td style="width:23%"><input type="text" id="name" name="name"  value="${condition.name }"></td>
	                		<th style="width:10%">绑定类型：</th>
	                		<td style="width:23%">
			              	  <select name="bindType">
			              	   	   <option value=""></option>	
			              	   	   <option value="1" ${condition.bindType=="1" ? "selected" : "" }>手机</option>	
			              	   	   <option value="2" ${condition.bindType=="2" ? "selected" : "" }>QQ</option>	
			              	   	   <option value="3" ${condition.bindType=="3" ? "selected" : "" }>微博</option>
			              	   	   <option value="3" ${condition.bindType=="4" ? "selected" : "" }>微信</option>
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
	                		<th>赠送金额：</th>
	                		<td><input type="text" id="money" name="money"  value="${condition.money }"></td>
	                		<th style="width:10%">是否赠送包月：</th>
	                		<td style="width:23%">
	                			<select name="byType">
			              	   	   <option value=""></option>	
			              	   	   <option value="1" ${condition.byType=="1" ? "selected" : "" }>不赠送</option>	
			              	   	   <option value="2" ${condition.byType=="2" ? "selected" : "" }>赠送</option>	
			              	    </select>
	                		</td>
	                	</tr>
	                	
	                	<tr>
	                		<th>开始时间：</th>
	                		<td>
								<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})" name="startDate" id="startDate" value="${condition.startDate }">
							</td>
	                		<th style="width:10%">结束时间：</th>
	                		<td style="width:23%">
	                			<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})" name="endDate" id="endDate" value="${condition.endDate }">
	                		</td>
							<th style="width:10%"></th>
	                		<td style="width:24%"></td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="搜索" onclick="search('1');">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="window.location.href='<%=basePath%>/bind/toAddBind'">
	                </div>
                </form> 
			  
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
				<th class="tb_title" width="100">名称</th>
				<th class="tb_title" width="70">绑定类型</th>
				<th class="tb_title" width="100">赠送类型</th>
				<th class="tb_title" width="100">赠送金额</th>
				<th class="tb_title" width="100">是否赠送包月</th>
				<th class="tb_title" width="100">包月名称</th>
				<th class="tb_title" width="100">开始时间</th>
				<th class="tb_title" width="100">结束时间</th>
				<th class="tb_title" width="100">修改时间</th>
				<th class="tb_title" width="70">操作</th>
        </tr>
    </thead>
    	<tbody>
			<c:forEach var="bind" items="${list }">
	    	    <tr class="odd gradeX warning">
					<td class="center">${bind.name }</td>
					<td class="center">
						<c:if test="${bind.bindType=='1'}">手机</c:if>
						<c:if test="${bind.bindType=='2'}">QQ</c:if>
						<c:if test="${bind.bindType=='3'}">微博</c:if>
						<c:if test="${bind.bindType=='4'}">微信</c:if>
					</td>
					<td class="center">
						<c:if test="${bind.repayType=='1'}">铜币</c:if>
						<c:if test="${bind.repayType=='2'}">代金券</c:if>
					</td>
					<td class="center">${bind.money}</td>
					<td class="center">
						<c:if test="${bind.byType=='1'}">不赠送</c:if>
						<c:if test="${bind.byType=='2'}">赠送</c:if>
					</td>
					<td class="center">${bind.byId}</td>
					<td class="center">${bind.startDate}</td>
					<td class="center">${bind.endDate }</td>
					<td class="center">${bind.updateDate }</td>
					<td class="center">
						<a href="<%=basePath%>/bind/toUpdateBind?id=${bind.id}">修改</a>&nbsp;
						<a href="javascript:void(delsure('${bind.id }'))">删除</a>
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
