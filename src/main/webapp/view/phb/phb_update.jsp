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
	                icon:{
	                    required:true
	                },
	                intro:{
	                    required:true
	                },
	                cc:{
	                	required:function(){
		                		return true;
	                	},
	                	digits:function(){
		                		return true;
	                	}	
	                }
	               
	                
	            },
	            messages:{
	                name:{
	                    required:"榜单名称不能为空"
	                },
	                icon:{
	                    required:"榜单icon不能为空"
	                },  
	                intro:{
	                    required:"榜单描述不能为空"
	                },  
	                cc:{
	                    required:"展示书籍数量不能为空",
	                   	digits:"展示书籍数量必须为整数"
	                },
	                
	            }
	        });    
	
	    });
    
        function showType(param){
        	if($(param).val() == "1"){
        		$("[name='showTypeOne']").each(function(){
        			$(this).hide();
        		});
        		
        		$("#qdId").attr("disabled",false);
        	}else if($(param).val() == "2"){
        		$("[name='showTypeOne']").each(function(){
        			$(this).show();
        		});
        		$("#qdId").attr("disabled",true);
        	}
        }
        
        
        function showCss(param){
        	if($(param).val() == "4"){
        		$("[name='showTypeThree']").each(function(){
        			$(this).hide();
        		});
        		$("[name='showTypeFour']").each(function(){
        			$(this).show();
        		});
        	}else if($(param).val() == "3"){
        		$("[name='showTypeThree']").each(function(){
        			$(this).show();
        		});
        		$("[name='showTypeFour']").each(function(){
        			$(this).hide();
        		});
        	}
        }
        
       
        
        function save(){
        	$("#form").submit();
        }
        
        

        function  updatezPhb(id,fid){
        	window.location.href="<%=basePath%>/phb/toUpdateZphb?id="+id+"&fid="+fid;
        }
        
        function  delzPhb(id,fid){
       	 if(confirm("确认删除?")){
         	window.location.href="<%=basePath%>/phb/deletezPhb?id="+id+"&fid="+fid;
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
              <jsp:include page="../menu.jsp"></jsp:include>
            </div>
            <div class="span10">
              <div class="hero-unit">
				排行榜修改
			  </div>	
                <form id ="form" action="<%=basePath%>/phb/updatePhb" method="post"  enctype = "multipart/form-data">
	                <input type="hidden" id="id" name="id" value="${phb.id }">
	                <table class="table table-bordered table-hover" id ="phbBean">
	                	<tr>
	                		<th style="width:15%">榜单名称：</th>
	                		<td style="width:35%"><input type="text" id="name" name="name" value="${phb.name }"></td>
							<th style="width:15%">榜单icon:</th>
	                		<td style="width:35%">
	                		<img src="http://zwsc.ikanshu.cn/paihangImage/${phb.icon }">
	                		<input type="file" id="iconn" name="iconn" value="">
	                		</td>
	                	</tr>
	                	
	                	<tr>
	                		<th>榜单描述：</th>
	                		<td style="width:40%">
	                		   <input type="text" id="intro" name="intro" style="width:82%" value="${phb.intro }"/>
	                		</td>
	                		<th style="width:40%">展示书籍数量:</th>
	                		<td style="width:35%"><input type="text" id="cc" name="cc" value="${phb.cc }"></td>
	                	</tr>
	                	
	                	<tr>
	                		<th style="width:15%">榜单书籍来源：</th>
	                		<td style="width:35%">
			              	  <select name="dataType" id="dataType" onchange="showType(this)">
			              	   	   <option value="1" ${phb.dataType == '1' ? "selected" : "" }>人工</option>	
			              	   	   <option value="2" ${phb.dataType == '2' ? "selected" : "" }>驱动</option>	
			              	   </select>
						</td>
						<c:if test="${phb.dataType=='2'}">
							<th name="showTypeOne"  >驱动规则：</th>
	                		<td name="showTypeOne" >
	                				<select name="newQdId" id="newQdId" >
				                		<c:forEach var="qd" items="${qdList}" >
						              	   	   <option value="${qd.id}" ${phb.qdId==qd.id ? "selected" :"" }>${qd.name}</option>	
				                		</c:forEach>
			              	   		</select>
	                	    </td>
	                	  </c:if>  
	                	</tr>
	                	<tr>
	                		<th >序列</th>
	                		<td colspan="3"><input type="text" id="idx" name="idx" style="width:20%" value="${phb.idx }"/></td>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">榜单样式：</th>
	                		<td style="width:35%">
			              	  <select name="css" id="css" onchange="showCss(this)" >
			              	   	   <option value="1" ${phb.css == '1' ? "selected" : "" }>展示书籍</option>	
			              	   	   <option value="2" ${phb.css == '2' ? "selected" : "" }>列表</option>	
			              	   	   <option value="3" ${phb.css == '3' ? "selected" : "" }>小图</option>	
			              	   	   <option value="4" ${phb.css == '4' ? "selected" : "" }>指数图</option>	
			              	   </select>
	                	</tr>
	                	<tr>
	                		<th  width="100">子榜单ID</th>
							<th  width="30">榜单名称</th>
							<c:if test="${phb.css=='4'}">
							<th  width="70">指数关键词</th>
							</c:if>
							<c:if test="${phb.css=='3'}">
							<th  width="70">榜单图片</th>
							</c:if>
							<th  width="70">榜单驱动</th>
							<th  width="70">榜单类型</th>
							<th  width="70">操作{<a href="<%=basePath%>/phb/toAddZphb?fid=${phb.id}">添加子榜单</a>}</th>
	                	</tr>
	                	
	                	<c:forEach var="zphb" items="${zPhbList }">
	                	<tr>
	                		<td width="100">${zphb.id}</td>
	                		<td width="100">${zphb.name}</td>
	                		<c:if test="${phb.css=='4'}">
							<td  width="70">${zphb.keyword}</td>
							</c:if>
							<c:if test="${phb.css=='3'}">
							<td  width="70">${zphb.icon}</td>
							</c:if>
							<td  width="70">
							
							<c:forEach var="qd" items="${qdList}" >
							<c:if test="${zphb.qdId==qd.id}">
						              	   	${qd.name}	
							</c:if>
				            </c:forEach>
							
							</td>
							
							<td  width="70">
							<c:if test="${zphb.css=='3'}">
							小图榜单
							</c:if>
							<c:if test="${zphb.css=='4'}">
							指数榜单
							</c:if>
							<c:if test="${zphb.css!='4'&&zphb.css!='3'}">
							子榜单
							</c:if>
							</td>
							<td width="70">
							<input type="button" value="修改" onclick="updatezPhb('${zphb.id}','${phb.id}')" >
							<input type="button" value="删除" onclick="delzPhb('${zphb.id}','${phb.id}')">
							</td>
	                	</tr>
	                	</c:forEach>
	                	
	                	
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="保存修改" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/phb/listPhb'">
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
