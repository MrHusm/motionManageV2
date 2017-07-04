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
	                bookNum:{
	                	required:true,
	                	digits:true
	                },
	                downNum:{
	                	required:function(){
	                		if($("#downFlag").val() == '1'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	},
	                	digits:function(){
	                		if($("#downFlag").val() == '1'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	}	
	                },
	                moreContent:{
	                	required:function(){
	                		if($("#moreFlag").val() != '0'){
		                		return true;
	                		}else{
	                			return false;
	                		}
	                	},
	                	maxlength:8
	                },
	                moreUrl:{
	                	required:function(){
	                		if($("#moreFlag").val() == '2'){
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
	                    required:"名称不能为空"
	                },
	                bookNum:{
	                	required:"显示书籍数量不能为空",
	                	digits:"显示书籍数量必须为整数"
	                },
	                downNum:{
	                	required:"下载章节数不能为空",
	                	digits:"下载章节数必须为整数"
	                },
	                moreContent:{
	                	required:"更多文案不能为空",
	                	maxlength:"更多文案长度不超过8个字"
	                },
	                moreUrl:{
	                	required:"更多链接不能为空",
	                },
	                idx:{
	                	required:"排序不能为空",
	                	digits:"排序必须为整数"
	                }
	            }
	        });    
	
	    });
    
	    function showMore(param){
	    	var flag = $(param).val();
	    	if(flag == '0'){
	    		$("tr[name='showMoreFlag']").hide();
        		$("[name='moreContentFlag']").each(function(){
        			$(this).hide();
        		});
        		$("[name='moreUrlFlag']").each(function(){
        			$(this).hide();
        		});
        		$("#moreContent").attr("disabled",true);
        		$("#moreUrl").attr("disabled",true);
	    	}else if(flag == '1'){
	    		$("tr[name='showMoreFlag']").show();
        		$("[name='moreContentFlag']").each(function(){
        			$(this).show();
        		});
        		$("[name='moreUrlShow']").each(function(){
        			$(this).hide();
        		});
        		$("[name='moreUrlHide']").each(function(){
        			$(this).show();
        		});
        		$("#moreContent").attr("disabled",false);
        		$("#moreUrl").attr("disabled",true);
	    	}else if(flag == '2'){
	    		$("tr[name='showMoreFlag']").show();
        		$("[name='moreUrlHide']").each(function(){
        			$(this).hide();
        		});
        		$("[name='moreContentFlag']").each(function(){
        			$(this).show();
        		});
        		$("[name='moreUrlShow']").each(function(){
        			$(this).show();
        		});
        		$("#moreContent").attr("disabled",false);
        		$("#moreUrl").attr("disabled",false);
	    	}else if(flag == '3'){
	    		$("tr[name='showMoreFlag']").show();
        		$("[name='moreContentFlag']").each(function(){
        			$(this).show();
        		});
        		$("[name='moreUrlShow']").each(function(){
        			$(this).hide();
        		});
        		$("[name='moreUrlHide']").each(function(){
        			$(this).show();
        		});
        		$("#moreContent").attr("disabled",false);
        		$("#moreUrl").attr("disabled",true);
	    	}else if(flag == '4'){
	    		$("tr[name='showMoreFlag']").show();
        		$("[name='moreUrlHide']").each(function(){
        			$(this).hide();
        		});
        		$("[name='moreContentFlag']").each(function(){
        			$(this).show();
        		});
        		$("[name='moreUrlShow']").each(function(){
        			$(this).show();
        		});
        		$("#moreContent").attr("disabled",false);
        		$("#moreUrl").attr("disabled",false);
	    	}
	    }	    
	    
	    
	    function showDrive(param){
	    	var flag = $(param).val();
	    	if(flag == '1'){
        		$("[name='driveShow']").each(function(){
        			$(this).hide();
        		});
        		$("[name='driveHide']").each(function(){
        			$(this).show();
        		});
	    	}else if(flag == '2'){
	    		$("[name='driveHide']").each(function(){
        			$(this).hide();
        		});
        		$("[name='driveShow']").each(function(){
        			$(this).show();
        		});
	    	}
	    }
	    
        function save(){
        	var flag = true;
        	$("[name='names']").each(function(){
    			if($(this).val() == ''){
					alert("请输入标签名称");
					$(this).focus();
					flag = false;
					return false;
    			}
    			if($(this).val() !=null && $(this).val().length>6){
					alert("标签名称不能超过6个字");
					$(this).focus();
					flag = false;
					return false;
    			}
    		});
        	
        	$("[name='urls']").each(function(){
    			if($(this).val() == ''){
					alert("请输入url");
					$(this).focus();
					flag = false;
					return false;
    			}
    		});
        	if(flag){
	        	$("#form").submit();
        	}
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
			  <c:if test="${bang.source=='1' }">
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
              <c:if test="${bang.source!='1' }">
	              <jsp:include page="../menu.jsp"></jsp:include>
              </c:if>
            </div>
            <div class="span10">
              <div class="hero-unit">
				榜单添加
			  </div>
                <form id ="form" action="<%=basePath%>/jpBangd/updateBangD" method="post">
                	<input type="hidden" name="id" value="${bang.id }" />
                	<input type="hidden" name="dqPage" value="${dqPage }" />
                	<input type="hidden" name="source" id="source" value="${bang.source }" />
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">榜单名称：</th>
	                		<td style="width:35%"><input type="text" id="name" name="name" value="${bang.name }"></td>
	                		<th style="width:15%">显示书籍数量：</th>
	                		<td style="width:35%"><input type="text" id="bookNum" name="bookNum" value="${bang.bookNum }"></td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">榜单名称是否显示：</th>
	                		<td style="width:35%">
			              	  <select name="showNameFlag" id="showNameFlag">
			              	   	   <option value="0" ${bang.showNameFlag == '0' ? "selected" : "" } >否</option>	
			              	   	   <option value="1" ${bang.showNameFlag == '1' ? "selected" : "" }>是</option>	
			              	   </select>
							</td>
	                		<th style="width:15%">刷新可变：</th>
	                		<td style="width:35%">
			              	  <select name="freshFlag" id="freshFlag" onchange="freshMore(this)">
			              	   	   <option value="0" ${bang.freshFlag == '0' ? "selected" : "" }>否</option>	
			              	   	   <option value="1" ${bang.freshFlag == '1' ? "selected" : "" }>是</option>	
			              	   </select>
							</td>
	                	</tr>
	                	<tr>
	                		<th>是否一键下载：</th>
	                		<td>
	                		   <select name="downFlag" id="downFlag">
			              	   	   <option value="0" ${bang.downFlag == '0' ? "selected" : "" }>否</option>	
			              	   	   <option value="1" ${bang.downFlag == '1' ? "selected" : "" }>是</option>	
			              	   </select>
	                		</td>
	                		<th>是否有更多：</th>
	                		<td>
	                		   <select name="moreFlag" id="moreFlag" onchange="showMore(this)">
			              	   	   <option value="0" ${bang.moreFlag == '0' ? "selected" : "" }>否</option>	
			              	   	   <option value="1" ${bang.moreFlag == '1' ? "selected" : "" }>加载当前</option>
			              	   	   <option value="2" ${bang.moreFlag == '2' ? "selected" : "" }>跳转</option>
			              	   	   <option value="3" ${bang.moreFlag == '3' ? "selected" : "" }>换一批</option>
			              	   	   <option value="4" ${bang.moreFlag == '4' ? "selected" : "" }>跳转频道</option>
			              	   </select>
	                		</td>
	                	</tr>
	                	<tr name="showMoreFlag" style="display:none">
	                		<th name="moreContentFlag" style="display:none">更多文案：</th>
	                		<td name="moreContentFlag" style="display:none"><input type="text" disabled="disabled" id="moreContent" value="${bang.moreContent }" name="moreContent"></td>
	                		<th name="moreUrlShow" style="display:none">更多链接：</th>
	                		<td name="moreUrlShow" style="display:none"><input type="text" disabled="disabled" id="moreUrl" name="moreUrl" value="${bang.moreUrl }">(1重磅2男频3女频4出版)</td>
	                		<th name="moreUrlHide"></th>
	                		<td name="moreUrlHide"></td>
	                	</tr>
	                	<tr>
	                		<th>书籍来源：</th>
	                		<td>
	                		   <select name="bookFrom" id="bookFrom" onchange="showDrive(this)">
			              	   	   <option value="1" ${bang.bookFrom == '1' ? "selected" : "" }>人工</option>
			              	   	   <option value="2" ${bang.bookFrom == '2' ? "selected" : "" }>驱动</option>
			              	   </select>
	                		</td>
	                		<th name="driveShow" style="display:none">驱动规则：</th>
	                		<td name="driveShow" style="display:none">
								<select name="driveId" id="driveId" >
		                			<c:forEach var="drive" items="${driveList}" >
				              	   	   	 <option value="${drive.id}" ${bang.driveId == drive.id ? "selected" : "" }>${drive.name}</option>	
		                			</c:forEach>
			              	    </select>
							</td>
	                		<th name="driveHide"></th>
	                		<td name="driveHide"></td>
	                	</tr>
	                	
	                	<tr>
	                		<th>榜单样式：</th>
	                		<td>
	                		   <select name="bangStyle" id="bangStyle">
			              	   	   <option value="1" ${bang.bangStyle == '1' ? "selected" : "" }>竖版</option>	
			              	   	   <option value="2" ${bang.bangStyle == '2' ? "selected" : "" }>横版</option>
			              	   	   <option value="3" ${bang.bangStyle == '3' ? "selected" : "" }>图带文字</option>
			              	   	   <option value="4" ${bang.bangStyle == '4' ? "selected" : "" }>纯文字</option>
			              	   </select>
	                		</td>
	                		<th>备注</th>
	                		<td><input type="text" name="remark" value="${bang.remark }"/></td>
	                	</tr>
	                	<tr>
	                		<th>排重榜单ID：</th>
		                	<td colspan="3"><input type="text" name="otherIds" value="${bang.otherIds }"/></td>
	                	</tr>
	                </table>
	                
	                <div>
	                   <input type="button" class="btn-info" name="bt" value="添加标签" onclick="addLabel()">
	                </div>
	                <table class="table table-bordered table-hover" id="label">
	                	<tr>
	                		<th style="width:30%">标签名称</th>
	                		<th style="width:30%">类型</th>
	                		<th style="width:30%">跳转</th>
	                		<th style="width:10%"></th>
	                	</tr>
	                	<c:forEach var="label" items="${labels }">
	                	  <tr>
	                	 	<td><input type="text" id="names" name="names" value="${label.name }"/></td>
   							<td>
   							  <select name="types" id="types">
   								<option value="1" ${label.type == '1' ? "selected" : "" }>跳转链接</option>
   								<option value="2" ${label.type == '2' ? "selected" : "" }>榜单</option>
   								<option value="3" ${label.type == '3' ? "selected" : "" }>标签</option>
   							  </select>
   							</td>
   							<td><input type="text" id="urls" name="urls" value="${label.url }" /></td>
   							<td><input type="button" class="btn-info" value="删除" onclick="deleteRow(this)" /></td>
		                  </tr>
	                	</c:forEach>
	                </table>	                
	                
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="修改" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/jpBangd/listBangD?dqPage=${dqPage }&source=${bang.source }'">
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
<script type="text/javascript">
function addLabel(){
   var table = $("#label");
   var row = $("<tr></tr>"); 
   var td1 = $("<td></td>"); 
   var td2 = $("<td></td>");
   var td3 = $("<td></td>");
   var td4 = $("<td></td>");
   td1.append("<input type='text' id='names' name='names' />");
   td2.append("<select name='types' id='types'><option value='1'>跳转链接</option><option value='2'>榜单</option><option value='3'>标签</option></select>");
   td3.append("<input type='text' id='urls' name='urls' />");
   td4.append("<input type='button' class='btn-info' value='删除' onclick='deleteRow(this)' />");
   row.append(td1);
   row.append(td2);
   row.append(td3);
   row.append(td4);
   table.append(row);
}

function deleteRow(param){
   $(param).parent().parent().remove();
}

var moreFlag = $("#moreFlag").val();
if(moreFlag == '0'){
	$("tr[name='showMoreFlag']").hide();
	$("[name='moreContentFlag']").each(function(){
		$(this).hide();
	});
	$("[name='moreUrlFlag']").each(function(){
		$(this).hide();
	});
	$("#moreContent").attr("disabled",true);
	$("#moreUrl").attr("disabled",true);
}else if(moreFlag == '1'){
	$("#moreContent").attr("disabled",false);
	$("tr[name='showMoreFlag']").show();
	$("[name='moreContentFlag']").each(function(){
		$(this).show();
	});
	$("[name='moreUrlShow']").each(function(){
		$(this).hide();
	});
	$("[name='moreUrlHide']").each(function(){
		$(this).show();
	});
	$("#moreUrl").attr("disabled",true);
}else if(moreFlag == '2'){
	$("#moreContent").attr("disabled",false);
	$("#moreUrl").attr("disabled",false);
	$("tr[name='showMoreFlag']").show();
	$("[name='moreUrlHide']").each(function(){
		$(this).hide();
	});
	$("[name='moreContentFlag']").each(function(){
		$(this).show();
	});
	$("[name='moreUrlShow']").each(function(){
		$(this).show();
	});
}else if(moreFlag == '3'){
	$("#moreContent").attr("disabled",false);
	$("tr[name='showMoreFlag']").show();
	$("[name='moreContentFlag']").each(function(){
		$(this).show();
	});
	$("[name='moreUrlShow']").each(function(){
		$(this).hide();
	});
	$("[name='moreUrlHide']").each(function(){
		$(this).show();
	});
	$("#moreUrl").attr("disabled",true);
}else if(moreFlag == '4'){
	$("#moreContent").attr("disabled",false);
	$("#moreUrl").attr("disabled",false);
	$("tr[name='showMoreFlag']").show();
	$("[name='moreUrlHide']").each(function(){
		$(this).hide();
	});
	$("[name='moreContentFlag']").each(function(){
		$(this).show();
	});
	$("[name='moreUrlShow']").each(function(){
		$(this).show();
	});
}

var bookFrom = $("#bookFrom").val();
if(bookFrom == '1'){
	$("[name='driveShow']").each(function(){
		$(this).hide();
	});
	$("[name='driveHide']").each(function(){
		$(this).show();
	});
}else if(bookFrom == '2'){
	$("[name='driveHide']").each(function(){
		$(this).hide();
	});
	$("[name='driveShow']").each(function(){
		$(this).show();
	});
}

function freshMore(param){
	if($(param).val()=="1"){
		if($("#moreFlag").val()=="1"){
			$("#moreFlag").val("0");
			$("#moreFlag").trigger("onchange",$("#moreFlag"));
		}
		$("#moreFlag option[value='1']").attr("disabled","disabled");
	}else{
		$("#moreFlag option[value='1']").removeAttr("disabled");
	}
}
</script>
</html>
