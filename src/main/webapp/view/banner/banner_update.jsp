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
	                startDate:{
	                	required:true
	                },
	                endDate:{
	                	required:true
	                },
	                content:{
	                	required:function(){
	                		if('${type}' == '3' || '${type}' == '4'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}
	                },
	                idx:{
	                	required:true,
	                	digits:true
	                }
	            },
	            messages:{
	                name:{
	                    required:"广告名称不能为空"
	                },
	                startDate:{
	                	required:"开始时间不能为空"
	                },
	                endDate:{
	                	required:"结束时间不能为空",
	                },
	                content:{
	                	required:"请输入广告内容",
	                },
	                idx:{
	                	required:"排序不能为空",
	                	digits:"排序必须为整数"
	                }
	            }
	        });    
	
	    });
	    
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
              <c:if test="${source=='1' }">
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
              <c:if test="${source!='1' }">
	              <jsp:include page="../menu.jsp"></jsp:include>
              </c:if>
            </div>
            <div class="span10">
              <div class="hero-unit">
				广告添加
			  </div>
                <form id ="form" action="<%=basePath%>/jpBanner/updateBanner" method="post" enctype="multipart/form-data">
                	<input type="hidden" name="id" value="${banner.id }" />
                	<input type="hidden" name="bpId" value="${bpId }" />
                	<input type="hidden" name="type" value="${type }" />
                	<input type="hidden" name="source" value="${source }" />
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">广告名称：</th>
	                		<td style="width:35%"><input type="text" id="name" name="name" value="${banner.name }"></td>
	                		<th style="width:15%">链接：</th>
	                		<td style="width:35%"><input type="text" id="url" name="url" value="${banner.url }"></td>
	                	</tr>
	                	<tr>
	                		<th>开始时间：</th>
	                		<td>
								<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01 %H:00:00',dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})" name="startDate" id="startDate" value="${fn:substringBefore(banner.startDate, '.')}">
							</td>
							<th>结束时间：</th>
	                		<td>
		                		<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01 %H:00:00',dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})" name="endDate" id="endDate" value="${fn:substringBefore(banner.endDate, '.')}">
	                		</td>
	                	</tr>
	                	<tr>
	                		<th>排序：</th>
	                		<td>
	                			<input type="text" name="idx" value="${banner.idx }"/>
	                		</td>
	                		<c:if test="${type==3}">
	                			<th>广告内容</th>
	                			<td><input type="text" name="content" value="${banner.content }"/></td>
	                		</c:if>
	                		<c:if test="${type!=3 }">
	                			<th>广告图片</th>
	                			<td>
	                				<img width="30%" src="http://zwsc.ikanshu.cn/jpBannerImage/${banner.imgUrl }">
	                				<input type="file" id="file" name="file">
	                			</td>
	                		</c:if>
	                	</tr>
	               		<c:if test="${type==4}">
	                	<tr>
	                		<th>广告内容</th>
	                		<td colspan="3"><input type="text" name="content" style="width:83%" value="${banner.content }"/></td>
	                	</tr>
	                	</c:if>
	                	<tr>
	                		<th>备注</th>
	                		<td colspan="3"><input type="text" name="remark" style="width:83%" value="${banner.remark }"/></td>
	                	</tr>
	                	<tr>
	                		<th>渠道ID：</th>
	                		<td colspan="3"><input type="text" id="channels" name="channels" style="width:83%" value="${banner.channels }"></td>
	                	</tr>
	                	<tr>
	                		<th>版本选择：</th>
	                		<td colspan="3">
	                			<input type="checkbox" value="全部" id="all" onclick="checkAll()"/>全选&nbsp;
	                			<c:forEach var="ver" items="${versions }">
									<input type="checkbox" name="versions" value="${ver.vcode }" ${fn:contains(banner.versions, ver.vcode) ? "checked" : "" }/>${ver.vcode }&nbsp;
	                			</c:forEach>
	                		</td>
	                	</tr>	
	                </table>
	                
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="修改" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/jpBanner/listBanner?bpId=${bpId }&type=${type }&source=${source }'">
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
</body>
</html>