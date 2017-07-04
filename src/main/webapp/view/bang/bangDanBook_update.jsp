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
	            	bookId:{
	                    required:true
	                },
	                mark:{
	                	maxlength:2
	                },
	                startDate:{
	                	required:true
	                },
	                endDate:{
	                	required:true
	                }
	            },
	            messages:{
	            	bookId:{
	                    required:"请输入资产ID"
	                }, 
	                mark:{
	                	required:"请输入角标",
	                	maxlength:"角标最大长度为2"
	                },
	                startDate:{
	                	required:"请输入轮播开始时间"
	                },
	                endDate:{
	                	required:"请输入轮播结束时间"
	                }
	            }
	        });    
	    });
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
				修改榜单书籍
			  </div>	
                <form id ="form" action="<%=basePath%>/jpBangDBook/updateBangDBook" method="post">
                	<input type="hidden" name="id" value="${book.id }" />
                	<input type="hidden" name="bangDanId" value="${bangDanId }" />
                	<input type="hidden" name="dqPage" value="${dqPage }" />
                	<input type="hidden" name="source" value="${source }" />
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">资产ID：</th>
	                		<td style="width:35%" colspan="3">
			              	  <input type="text" name="bookId" style="width:81%" value="${book.bookId }"/>
							</td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">角标颜色：</th>
	                		<td style="width:35%">
								<select name="markColor">
									<option value=""></option>
								    <option value="Red" ${book.markColor=="Red" ? "selected" : "" }>Red</option>
								    <option value="Orange" ${book.markColor=="Orange" ? "selected" : "" }>Orange</option>
								    <option value="Blue" ${book.markColor=="Blue" ? "selected" : "" }>Blue</option>
								    <option value="Green" ${book.markColor=="Green" ? "selected" : "" }>Green</option>
								</select>
							</td>
							<th style="width:15%">角标：</th>
	                		<td style="width:35%">
	                			<input type="text" name="mark" value="${book.mark }"/>
							</td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">轮播开始时间：</th>
	                		<td style="width:35%">
								<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})" name="startDate" id="startDate" value="${fn:substringBefore(book.startDate, ' ')}">
							</td>
							<th style="width:15%">轮播结束时间：</th>
	                		<td style="width:35%">
	                			<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})" name="endDate" id="endDate" value="${fn:substringBefore(book.endDate, ' ')}">
							</td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">排序：</th>
	                		<td style="width:35%">
			              	  <input type="text" name="idx" value="${book.idx }"/>
							</td>
							<th style="width:15%"></th>
	                		<td style="width:35%">
							</td>
	                	</tr>           	
	                </table>
	                <div align="center">
	                   <input type="submit" class="btn-info" name="bt" value="修改">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/jpBangDBook/listBangDBook?bangDanId=${bangDanId}&dqPage=${dqPage}&source=${source }'">
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
</body></html>
