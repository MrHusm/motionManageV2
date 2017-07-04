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
        	 window.location.href="<%=basePath%>/jpBannerPosition/deleteBannerPosition?id="+id+"&dqPage=${condition.dqPage}&source=${condition.source}";
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
				广告位管理&nbsp;&nbsp;
			  </div>
                <form id="form" action="<%=basePath%>/jpBannerPosition/listBannerPosition" method="post">
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
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="window.location.href='<%=basePath%>/jpBannerPosition/toAddBannerPosition?source=${condition.source }'">
	                </div>
                </form> 
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
			<th class="tb_title" width="15%">ID</th>
			<th class="tb_title" width="25%">名称</th>
			<th class="tb_title" width="25%">广告类型</th>
			<th class="tb_title" width="15%">广告</th>
			<th class="tb_title" width="20%">操作</th>
        </tr>
      </thead>
   	  <tbody>
		<c:forEach var="bannerPosition" items="${list }">
    	    <tr class="odd gradeX warning">
	    	    <td class="center">${bannerPosition.id }</td>
				<td class="center">${bannerPosition.name }</td>
				<td class="center">
					<c:if test="${bannerPosition.type=='1'}">大图</c:if>
					<c:if test="${bannerPosition.type=='2'}">小图</c:if>
					<c:if test="${bannerPosition.type=='3'}">文字</c:if>
					<c:if test="${bannerPosition.type=='4'}">推广</c:if>
				</td>
				<td class="center">
					<a href="<%=basePath%>/jpBanner/listBanner?bpId=${bannerPosition.id}&type=${bannerPosition.type}&source=${bannerPosition.source}">查看</a>
				</td>
				<td class="center">
					<a href="<%=basePath%>/jpBannerPosition/toUpdateBannerPosition?id=${bannerPosition.id}&dqPage=${condition.dqPage}">修改</a>&nbsp;
					<a href="javascript:void(delsure('${bannerPosition.id }'))">删除</a>
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
