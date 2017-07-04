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
				抽奖活动管理&nbsp;&nbsp;
			  </div>	
                <form id ="form" action="<%=basePath%>/campaign/listLottery" method="post">
	                <input type="hidden" name="dqPage" id="dqPage" value="1"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th>奖品名称：</th>
	                		<td><input type="text" id="prizeName" name="prizeName"  value="${condition.prizeName}"></td>
                            <th>奖品类型：</th>
                            <td>
                              <select name="prizeType">
                                   <option value="-">全部</option>    
                                   <option value="0" ${condition.prizeType=="0" ? "selected" : "" }>充值卡</option>    
                                   <option value="1" ${condition.prizeType=="1" ? "selected" : "" }>铜币</option>    
                                   <option value="2" ${condition.prizeType=="2" ? "selected" : "" }>实体物</option>
                                   <option value="3" ${condition.prizeType=="3" ? "selected" : "" }>VIP</option>
                                   <option value="4" ${condition.prizeType=="4" ? "selected" : "" }>铜币代金券</option>
                               </select>
                            </td>
                            <th></th>
                            <td></td>
	                	</tr>
                        <tr>
                            <th>用户账号：</th>
                            <td><input type="text" id="userName" name="userName"  value="${condition.userName}"></td>
                            <th>用户昵称：</th>
                            <td><input type="text" id="userNickName" name="userNickName"  value="${condition.userNickName}"></td>
                            <th>用户信息：</th>
                            <td><input type="text" id="userTel" name="userTel"  value="${condition.userTel}"></td>
                        </tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="搜索" onclick="search('1');">&nbsp;&nbsp;&nbsp;&nbsp;
<%-- 	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="window.location.href='<%=basePath%>/coupon/toAddCoupon'"> --%>
	                </div>
                </form> 
			  
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
				<th class="tb_title" width="50">序号</th>
				<th class="tb_title" width="100">用户账号</th>
				<th class="tb_title" width="70">用户昵称</th>
				<th class="tb_title" width="100">奖品类型</th>
				<th class="tb_title" width="100">奖品名称</th>
				<th class="tb_title" width="100">中奖时间</th>
				<th class="tb_title" width="70">用户信息</th>
				<th class="tb_title" width="70">方案名称</th>
				<th class="tb_title" width="70">方案id</th>
        </tr>
    </thead>
    	<tbody>
			<c:forEach var="lottery" items="${list}">
	    	    <tr class="odd gradeX warning">
					<td class="center">${lottery.id }</td>
					<td class="center">${lottery.userName }</td>
					<td class="center">${lottery.nickName }</td>
					<td class="center">
						<c:if test="${lottery.prizeType=='0'}">充值卡</c:if>
						<c:if test="${lottery.prizeType=='1'}">铜币</c:if>
						<c:if test="${lottery.prizeType=='2'}">实体物</c:if>
						<c:if test="${lottery.prizeType=='3'}">VIP</c:if>
						<c:if test="${lottery.prizeType=='4'}">铜币代金券</c:if>
					</td>	
					<td class="center">${lottery.prizeName}</td>
					<td class="center">${lottery.dhTime }</td>
					<td class="center">${lottery.linktel}<br/>${lottery.trueName}<br/>${lottery.address}</td>
					<td class="center">${lottery.hdName}</td>
					<td class="center">${lottery.hdId}</td>
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
