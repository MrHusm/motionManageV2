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
        function delsure(id,dqPage){
           if(confirm("确认删除?")){
        	 window.location.href="<%=basePath%>/jpBangd/deleteBangD?id="+id+"&dqPage="+dqPage+"&source="+$("#source").val();
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
              <c:if test="${condition.source=='1' }">
				<style type="text/css">
					#my_menu{
						width:140px;
						background:#F7F2E4;
						height:100%;
					}
					div.sdmenu div.collapsed {
					height: 25px;
				    }
					div.sdmenu div{
						overflow: hidden;
					}
					.spanstyle{
					  cursor: pointer;background:#CCCCCC;
						border:1px solid #ffffff;
						border-left:6px solid #CCCCCC;
						width:140px;
						height:23px;
						display:block;
						line-height:23px;
						padding-left:20px;
						font-size: 14px;
						font-weight: bold;
					}
					.astyle{
					    padding:3px 0 3px 40px;
						display:block;
						font-size: 14px;
						font-weight: bold;
						line-height: 30px;
						color: #999999;
					}
				</style>
				<div style="float:left" id="my_menu" class="sdmenu">
					<div>
						<span class="spanstyle">精品管理</span>
						<a href="<%=basePath%>/jpPage/listPage?source=1" class="astyle">精品页管理</a>
						<a href="<%=basePath%>/jpBangd/listBangD?source=1" class="astyle">榜单管理</a>
						<a href="<%=basePath%>/jpBannerPosition/listBannerPosition?source=1" class="astyle">广告位管理</a>
					</div>
				</div>              
              </c:if>
              <c:if test="${condition.source!='1' }">
	              <jsp:include page="../menu.jsp"></jsp:include>
              </c:if>
            </div>
            <div class="span10">
              <div class="hero-unit">
				榜单管理&nbsp;&nbsp;
			  </div>	
                <form id="form" action="<%=basePath%>/jpBangd/listBangD" method="post">
	                <input type="hidden" name="dqPage" id="dqPage" value="1"/>
	                <input type="hidden" name="source" id="source" value="${condition.source }"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:10%">名称：</th>
	                		<td style="width:23%"><input type="text" id="name" name="name"  value="${condition.name }"></td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="搜索" onclick="search('1');">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="window.location.href='<%=basePath%>/jpBangd/toAddBangD?source=${condition.source }'">
	                </div>
                </form> 
			  
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
			<th class="tb_title" width="10%">ID</th>
			<th class="tb_title" width="15%">名称</th>
			<th class="tb_title" width="15%">备注</th>
			<th class="tb_title" width="10%">书籍来源</th>
			<th class="tb_title" width="10%">榜单图书</th>
			<th class="tb_title" width="10%">是否刷新可变</th>
			<th class="tb_title" width="10%">是否一键下载</th>
			<th class="tb_title" width="10%">是否有更多</th>
			<th class="tb_title" width="10%">操作</th>
        </tr>
      </thead>
   	  <tbody>
		<c:forEach var="bang" items="${list }">
    	    <tr class="odd gradeX warning">
	    	    <td class="center">${bang.id }</td>
				<td class="center">${bang.name }</td>
				<td class="center">${bang.remark }</td>
				<td class="center">
					<c:if test="${bang.bookFrom==1 }">人工</c:if>
					<c:if test="${bang.bookFrom==2 }">${bang.driveName }</c:if>
				</td>
				<td class="center"><a href="<%=basePath%>/jpBangDBook/listBangDBook?bangDanId=${bang.id}&source=${bang.source}">查看</a>
				</td>
				<td class="center">
					<c:if test="${bang.freshFlag=='0'}">否</c:if>
					<c:if test="${bang.freshFlag=='1'}">是</c:if>
				</td>
				<td class="center">
					<c:if test="${bang.downFlag=='0'}">否</c:if>
					<c:if test="${bang.downFlag=='1'}">是</c:if>
				</td>
				<td class="center">
					<c:if test="${bang.moreFlag=='0'}">否</c:if>
					<c:if test="${bang.moreFlag=='1'}">加载当前</c:if>
					<c:if test="${bang.moreFlag=='2'}">跳转</c:if>
					<c:if test="${bang.moreFlag=='3'}">换一批</c:if>
					<c:if test="${bang.moreFlag=='4'}">跳转频道</c:if>
				</td>
				<td class="center">
					<a href="<%=basePath%>/jpBangd/toUpdateBangD?id=${bang.id}&dqPage=${condition.dqPage}">修改</a>&nbsp;
					<a href="javascript:void(delsure('${bang.id }','${condition.dqPage}'))">删除</a>
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
