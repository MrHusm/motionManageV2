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
        function delsure(id,pageType){
           if(confirm("确认删除?")){
        	 window.location.href="<%=basePath%>/bannerApp/deleteBannerApp?id="+id+"&pageType="+pageType;
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
                <form id ="form" action="<%=basePath%>/bannerApp/listBannerApp" method="post">
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:10%">页面：</th>
	                		<td style="width:23%">
			              	  <select name="pageType" id="pageType">
								<option value="1" ${condition.pageType == '1' ? 'selected' : '' }>应用推荐页面</option>
							    <option value="2" ${condition.pageType == '2' ? 'selected' : '' }>每日福利页面</option>
			              	  </select>
							</td>
	                		<th style="width:10%">名称：</th>
	                		<td style="width:23%">
	                			<input type="text" name="name" value="${condition.name }" />
	                		</td>
	                	</tr>
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="搜索" onclick="search('1');">&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="window.location.href='<%=basePath%>/bannerApp/toAddBannerApp'">&nbsp;&nbsp;&nbsp;&nbsp;
	                </div>
                </form> 
	<table class="table table-bordered table-hover">
	  <thead>
    	<tr>
    		<th class="tb_title" width="70">序号</th>
    		<th class="tb_title" width="70">排序</th>
			<th class="tb_title" width="70">广告名称</th>
			<th class="tb_title" width="70">方式</th>
			<th class="tb_title" width="70">开始时间</th>
			<th class="tb_title" width="70">结束时间</th>
			<th class="tb_title" width="70">更新时间</th>
			<th class="tb_title" width="70">状态</th>
			<th class="tb_title" width="70">操作</th>
        </tr>
     </thead>
    	<tbody>
			<c:forEach var="bannerApp" items="${list }" varStatus="status">
	    	    <tr class="odd gradeX warning">
	    	    	<td class="center">${status.count}</td>
	    	    	<td class="center" onclick="toUpdateIdx('${bannerApp.id}')">
						<span id="txt_${bannerApp.id }">${bannerApp.idx }</span>
						<input type="text" id="idx_${bannerApp.id }" name="idx" value="${bannerApp.idx }" style="display:none;width:30%"/>
						<input type="button" id="btn_${bannerApp.id }" name="bt" style="display:none" value="修改" onclick="updateIdx('${bannerApp.id}')">
					</td>
					<td class="center">${bannerApp.name}</td>
					<td class="center">
						<c:if test="${bannerApp.type==1 }">直投</c:if>
						<c:if test="${bannerApp.type==2 }">SDK</c:if>
					</td>
					<td class="center">${bannerApp.startDate}</td>
					<td class="center">${bannerApp.endDate}</td>
					<td class="center">${bannerApp.updateDate}</td>
					<td class="center">
						<c:if test="${bannerApp.state==0 }">
							未开始
						</c:if>
						<c:if test="${bannerApp.state==1 }">
							已开始
						</c:if>
						<c:if test="${bannerApp.state==2 }">
							已结束
						</c:if>
					</td>
					<td class="center">
						<a href="<%=basePath%>/bannerApp/toUpdateBannerApp?id=${bannerApp.id}&pageType=${bannerApp.pageType}">修改</a>&nbsp;
						<a href="javascript:void(delsure('${bannerApp.id }','${bannerApp.pageType}'))">删除</a>
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
<script type="text/javascript">
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
	var url = '<%=basePath%>/bannerApp/updateBannerAppIdx?id='+id+'&idx='+$(idx).val()+'&pageType='+$("#pageType").val();
	window.location.href=url;
}
</script>
</html>