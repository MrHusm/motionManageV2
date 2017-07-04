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
        	 window.location.href="<%=basePath%>/jpBangDBook/deleteBangDBook?ids="+id + "&bangDanId=${condition.bangDanId }&dqPage=${condition.dqPage }&source=${condition.source}";
           }
        }
        
        function delsureBatch(){
            if(confirm("确认批量删除?")){
            	var ids = "";
            	var deleteFlag = false;
            	$("input[name='id']").each(function(){
            		if($(this).is(':checked')){
            			ids += $(this).val()+",";
            			deleteFlag = true;
            		}
            	});
            	if(deleteFlag){
	            	window.location.href="<%=basePath%>/jpBangDBook/deleteBangDBook?ids="+ids + "&bangDanId=${condition.bangDanId }&dqPage=${condition.dqPage }&source=${condition.source}";
            	}else{
            		alert("请勾选需要删除的图书");
            	}
            }
         }
        
        function add(bdId){
			var url = "<%=basePath%>/jpBangDBook/toAddBangDBook?bangDanId="+bdId+"&source=${condition.source}";
			window.location.href=url;
        }
        
        function toUpdateIdx(id){
        	var btn="#btn_"+id;
        	var idx="#idx_"+id;
        	var txt = "#txt_"+id;
        	$(txt).hide();
        	$(idx).show();
        	$(btn).show();
        }
        
        function updateIdx(id){
        	var idx="#idx_"+id;
        	var url = '<%=basePath%>/jpPage/updateJpPage?id='+id+'&idx='+$(idx).val()+'&pageType='+$("#pageType").val();
        	window.location.href=url;
        }
        
        function search(dqPage){
        	$("#dqPage").val(dqPage);
        	$("#form").submit();
        }
        
        function checkAll(){
        	$("input[name='id']").each(function(){
        		if($(this).is(':checked')){
        			$(this).attr("checked", false);
        		}else{
        			$(this).attr("checked", true);
        		}
        	});
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
				榜单图书管理&nbsp;&nbsp;
			  </div>	
                <form id ="form" action="<%=basePath%>/jpBangDBook/listBangDBook" method="post">
                	<input type="hidden" name="dqPage" id="dqPage" value="1"/>
                	<input type="hidden" name="bangDanId" value="${condition.bangDanId }" />
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th>图书ID：</th>
	                		<td>
								<input type="text" name="bookId" value="${condition.bookId }" />
							</td>
							<c:if test="${bookFrom==1 }">
							  <th>图书名称：</th>
		                	  <td>
								<input type="text" name="bookName" value="${condition.bookName }" />
							  </td>
							</c:if>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="搜索" onclick="search('1');">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="add('${condition.bangDanId}')">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" class="btn-info" name="bt" value="批量删除" onclick="delsureBatch('${condition.bangDanId}')">
	                </div>
                </form> 
    <h4>
    	榜单名称：${bangName }&nbsp;&nbsp;
    </h4>
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
    		<c:if test="${bookFrom==1 }">
    			<th class="tb_title" width="10%"><input type="button" class="btn-info" name="bt" value="全选" onclick="checkAll();"></th>
			</c:if>
			<th class="tb_title" width="10%">资产ID</th>
			<th class="tb_title" width="15%">书名</th>
			<c:if test="${bookFrom==2 }">
				<th class="tb_title" width="10%">驱动名称</th>
			</c:if>
			<th class="tb_title" width="10%">封面</th>
			<c:if test="${bookFrom==1 }">
				<th class="tb_title" width="10%">轮播开始时间</th>
				<th class="tb_title" width="10%">轮播结束时间</th>
				<th class="tb_title" width="10%">排序</th>
				<th class="tb_title" width="10%">角标</th>
				<th class="tb_title" width="10%">角标颜色</th>
				<th class="tb_title" width="10%">操作</th>
			</c:if>
        </tr>
    </thead>
    	<tbody>
			<c:forEach var="book" items="${list }">
	    	    <tr class="odd gradeX warning">
	    	    	<c:if test="${bookFrom==1 }">
	    	    		<td class="center"><input type="checkbox" class="btn-info" name="id" value="${book.id }"></td>
	    	    	</c:if>
					<td class="center">${book.bookId}</td>
					<td class="center">${book.bookName}</td>
					<c:if test="${bookFrom==2 }">
						<td class="center">${driveName }</td>
					</c:if>
					<td class="center"><img height="50%" src="http://cdn.ikanshu.cn/211/images/${book.imgUrl }" alt="${book.imgUrl }"></td>
					<c:if test="${bookFrom==1 }">
						<td class="center">${book.startDate }</td>
						<td class="center">${book.endDate }</td>
						<td class="center">${book.idx }</td>
						<td class="center">${book.mark }</td>
						<td class="center">${book.markColor }</td>
						<td class="center">
							<a href="<%=basePath%>/jpBangDBook/toUpdateBangDBook?id=${book.id}&bangDanId=${condition.bangDanId }&dqPage=${condition.dqPage }&source=${condition.source}">修改</a>&nbsp;
							<a href="javascript:void(delsure('${book.id }'))">删除</a>
						</td>
					</c:if>
				  </tr>
			</c:forEach>
    	  </tbody>
    </table>
	<div align="center">
		共 ${condition.totalRow} 条, ${condition.totalPage} 页&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(search('${condition.upPageNum}'))"> 上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${condition.dqPage }
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(search('${condition.downPageNum }'))">下一页</a> 
	</div>    
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
