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
        	 window.location.href="<%=basePath%>/jpPage/deleteJpPage?id="+id+"&pageType="+$("#pageType").val()+"&source="+$("#source").val();
           }
        }
        
        function add(){
			var url = "<%=basePath%>/jpPage/toAddJpPage?pageType="+$("#pageType").val()+"&source="+$("#source").val();
			window.location.href=url;
        }
        
        function toUpdateIdx(id){
        	var btn="#btn_"+id;
        	var cancelBtn="#cancel_btn_"+id;
        	var idx="#idx_"+id;
        	var txt = "#txt_"+id;
        	$(txt).hide();
        	$(idx).show();
        	$(btn).show();
        	$(cancelBtn).show();
        }
        
        function cancelUpdate(id){
        	var btn="#btn_"+id;
        	var cancelBtn="#cancel_btn_"+id;
        	var idx="#idx_"+id;
        	var txt = "#txt_"+id;
        	$(idx).hide();

        	$(btn).show();
        	$(cancelBtn).show();
        }
        
        function updateIdx(id){
        	var idx="#idx_"+id;
        	var url = '<%=basePath%>/jpPage/updateJpPage?id='+id+'&idx='+$(idx).val()+'&pageType='+$("#pageType").val();
        	window.location.href=url;
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
				精选页管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<c:if test="${condition.source!='1' }">
					<input type="button" class="btn-info" name="bt" value="重置缓存" onclick="javascript:window.location.href='<%=basePath%>/jpPage/resetJpRedisCache?pageType=${condition.pageType}'">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<!--<input type="button" class="btn-info" name="bt" value="导入图书评论" onclick="javascript:window.location.href='<%=basePath%>/jpPage/importBookReview?pageType=${condition.pageType}'">-->
			  	</c:if>
			  </div>
                <form id ="form" action="<%=basePath%>/jpPage/listPage" method="post">
                	<input type="hidden" name="source" id="source" value="${condition.source }"/>
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th>页面：</th>
	                		<td>
			              	  <select name="pageType" id="pageType" onchange="javascript:$('#form').submit()">
			              	   	   <option value="1" ${condition.pageType=="1" ? "selected" : ""}>重磅</option>	
			              	   	   <option value="2" ${condition.pageType=="2" ? "selected" : ""}>男频</option>
			              	   	   <option value="3" ${condition.pageType=="3" ? "selected" : ""}>女频</option>
			              	   	   <option value="4" ${condition.pageType=="4" ? "selected" : ""}>出版物</option>	
			              	   </select>
							</td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="submit" class="btn-info" name="bt" value="搜索">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="add()">
	                </div>
                </form> 
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
			<th class="tb_title" width="10%">ID</th>
			<th class="tb_title" width="20%">排序</th>
			<th class="tb_title" width="15%">类型</th>
			<th class="tb_title" width="20%">榜单或者广告位ID</th>
			<th class="tb_title" width="15%">名称</th>
			<th class="tb_title" width="20%">操作</th>
        </tr>
    </thead>
    	<tbody>
			<c:forEach var="page" items="${list }">
	    	    <tr class="odd gradeX warning">
					<td class="center">${page.id }</td>
					<td class="center" onclick="toUpdateIdx('${page.id}')">
						<span id="txt_${page.id }">${page.idx }</span>
						<input type="text" id="idx_${page.id }" name="idx" value="${page.idx }" style="display:none;width:30%"/>
						<input type="button" id="btn_${page.id }" name="bt" style="display:none" value="修改" onclick="updateIdx('${page.id}')">
					</td>
					<td class="center">
						<c:if test="${page.type == 1 }">
							榜单
						</c:if>
						<c:if test="${page.type == 2 }">
							广告位
						</c:if>	
					</td>
					<td class="center">${page.keyId }</td>
					<td class="center">${page.name }</td>
					<td class="center">
						<!-- <a href="<%=basePath%>/jpPage/toUpdateJpPage?id=${page.id}">修改</a>&nbsp; -->
						<a href="javascript:void(delsure('${page.id }'))">删除</a>
					</td>
				  </tr>
			</c:forEach>
    	  </tbody>
    </table>
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
