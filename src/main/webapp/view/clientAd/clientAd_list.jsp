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
        	 window.location.href="<%=basePath%>/clientAd/deleteClientAd?id="+id;
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
				启动图管理&nbsp;&nbsp;
			  </div>	
                <form id ="form" action="<%=basePath%>/clientAd/listClientAd" method="post">
	                <input type="hidden" name="dqPage" id="dqPage" value="1"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:10%">名称：</th>
	                		<td style="width:23%">
	                			<input type="text" name="name" value="${condition.name }" />
	                		</td>
	                		<th style="width:10%">类型：</th>
	                		<td style="width:23%">
			              	  <select name="type" id="type">
			              	  		<option value=""></option>
									<option value="0" ${condition.type == '0' ? 'selected' : '' }>运营配置</option>
									<option value="1" ${condition.type == '1' ? 'selected' : '' }>广告API</option>
			              	  </select>
							</td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="搜索" onclick="search('1');">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="window.location.href='<%=basePath%>/clientAd/toAddClientAd'">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" class="btn-info" name="bt" value="查看广告API" onclick="window.location.href='<%=basePath%>/apiAd/listApiAd'">
	                </div>
                </form> 
			  
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
			<th class="tb_title" width="70">名称</th>
			<th class="tb_title" width="70">类型</th>
			<th class="tb_title" width="70">图片</th>
			<th class="tb_title" width="70">跳转链接</th>
			<th class="tb_title" width="70">开始时间</th>
			<th class="tb_title" width="70">结束时间</th>
			<th class="tb_title" width="70">更新时间</th>
			<th class="tb_title" width="70">状态</th>
			<th class="tb_title" width="70">操作</th>
        </tr>
     </thead>
    	<tbody>
			<c:forEach var="clientAd" items="${list }">
	    	    <tr class="odd gradeX warning">
					<td class="center">${clientAd.name}</td>
					<td class="center">
						<c:if test="${clientAd.type==0 }">
							运营配置
						</c:if>
						<c:if test="${clientAd.type==1 }">
							广告API
						</c:if>
					</td>
					<td class="center">
						<c:if test="${clientAd.type==0 }">
							<img src="http://zwsc.ikanshu.cn/startImage/${clientAd.imgUrl}" style="width:100%;height:10%"/>
						</c:if>	
					</td>
					<td class="center">${clientAd.url}</td>
					<td class="center">${clientAd.startDate}</td>
					<td class="center">${clientAd.endDate}</td>
					<td class="center">${clientAd.updateDate}</td>
					<td class="center">
						<c:if test="${clientAd.state==0 }">
							未开始
						</c:if>
						<c:if test="${clientAd.state==1 }">
							已开始
						</c:if>
						<c:if test="${clientAd.state==2 }">
							已结束
						</c:if>
					</td>
					<td class="center">
						<a href="<%=basePath%>/clientAd/toUpdateClientAd?id=${clientAd.id}">修改</a>&nbsp;
						<a href="javascript:void(delsure('${clientAd.id }'))">删除</a>
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
</body>
</html>