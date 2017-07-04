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
	                iconn:{
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
	                iconn:{
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
        		
        		$("#qdId").attr("disabled",true);
        	}else if($(param).val() == "2"){
        		$("[name='showTypeOne']").each(function(){
        			$(this).show();
        		});
        		$("#qdId").attr("disabled",false);
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
        	var icon1s=document.getElementsByName('icon1').length;
        	var name1s=document.getElementsByName('name1').length;
        	var qdId1s=document.getElementsByName('qdId1').length;
        	var name2s=document.getElementsByName('name2').length;
        	var qdId2s=document.getElementsByName('qdId2').length;
        	var keywords=document.getElementsByName('keyword').length;
        	var qdId3s=document.getElementsByName('qdId3').length;
        	var name3s=document.getElementsByName('name3').length;
        	if(icon1s!=name1s||icon1s!=qdId1s||name1s!=qdId1s){
        	  alert("请正确填写小图榜单信息");
        	  return false;
        	}
        	if(name2s!=qdId2s){
        	  alert("请正确填写子榜单信息");
           	  return false;
        	}
        	if($("#css").val() == '4'){
        		if(keywords<=0){
        			alert("指数排行榜请至少填写一条指数榜单信息");
                    return false;
        		}
        		if(keywords!=qdId3s||keywords!=name3s||qdId3s!=name3s){
        			alert("请正确填写指数榜单信息");
                    return false;
              	}	
        	}
        	
        	$("#form").submit();
        }
        
        function add_tr(obj) { 

            var tr = $(obj).parent().parent(); 

            tr.after(tr.clone()); 

          }
        
        function add_trz(obj) { 
        	var zs=document.getElementsByName('zphb').length;
            var tr = $(obj).parent().parent(); 
            if(zs<3){
            tr.after(tr.clone()); 
            }
          }
        function deletezRow(param){
        	var zs=document.getElementsByName('zphb').length;
        	if(zs>1){
      	   $(param).parent().parent().remove();
        	}
         }
        
        function deletezsRow(param){
        	var zs=document.getElementsByName('showTypeFour').length;
        	if(zs>3){
      	   $(param).parent().parent().remove();
        	}
         }
        
        
        function deletextRow(param){
        	var zs=document.getElementsByName('showTypeThree').length;
        	if(zs>3){
      	   $(param).parent().parent().remove();
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
				排行榜添加
			  </div>	
                <form id ="form" action="<%=basePath%>/phb/addPhb" method="post"  enctype = "multipart/form-data">
	                <table class="table table-bordered table-hover" id ="phbBean">
	                	<tr>
	                		<th style="width:15%">榜单名称：</th>
	                		<td style="width:35%"><input type="text" id="name" name="name"></td>
							<th style="width:15%">榜单icon:</th>
	                		<td style="width:35%"><input type="file" id="iconn" name="iconn">
			    			</td>
	                	</tr>
	                	
	                	<tr>
	                		<th>榜单描述：</th>
	                		<td style="width:40%">
	                		   <input type="text" id="intro" name="intro" style="width:82%"/>
	                		</td>
	                		<th style="width:40%">展示书籍数量:</th>
	                		<td style="width:35%"><input type="text" id="cc" name="cc"></td>
	                	</tr>
	                	
	                	<tr>
	                		<th style="width:15%">榜单书籍来源：</th>
	                		<td style="width:35%">
			              	  <select name="dataType" id="dataType" onchange="showType(this)">
			              	   	   <option value="1">人工</option>	
			              	   	   <option value="2">驱动</option>	
			              	   </select>
							</td>
							<th name="showTypeOne"  style="display:none">驱动规则：</th>
	                		<td name="showTypeOne" style="display:none">
	                		<select name="qdId" id="qdId" >
	                		<c:forEach var="qd" items="${qdList}" >
			              	   	   <option value="${qd.id}">${qd.name}</option>	
	                		</c:forEach>
			              	   </select>
	                	</tr>
	                	<tr>
	                		<th style="width:15%">榜单样式：</th>
	                		<td style="width:35%">
			              	  <select name="css" id="css" onchange="showCss(this)">
			              	   	   <option value="1">展示书籍</option>	
			              	   	   <option value="2">列表</option>	
			              	   	   <option value="3">小图</option>	
			              	   	   <option value="4">指数图</option>	
			              	   </select>
	                	</tr>
	                	<tr name="showTypeFour" style="display:none">
							<th>指数排行榜：</th>
	                	</tr>
	                	<tr name="showTypeFour" style="display:none">
	                				<th>指数榜单名称</th>
			                		<th>指数关键词</th>
			                		<th>驱动规则</th>
			                		<th></th>
	                	</tr>
	                	<tr name="showTypeFour" style="display:none">
	                				<td><input type='text' id='name3' name='name3'></td>
	                				<td><input type='text' id='keyword' name='keyword'></td>
	                				<td>
	                				<select name="qdId3" id="qdId3" >
				                		<c:forEach var="qd" items="${qdList}" >
						              	   	   <option value="${qd.id}">${qd.name}</option>	
				                		</c:forEach>
			              	   		</select>
			              	   		</td>
	                				<td><input type='button' class='btn-info' value='删除' onclick='deletezsRow(this)'><input type="button" class="btn-info" name="bt" value="新增" onclick="add_tr(this)"></td>
	                	</tr>
	                	
	                	
	                	
	                	<tr name="showTypeThree" style="display:none">
							<th>小图排行榜：</th>
						</tr>
	                	<tr name="showTypeThree" style="display:none">
			                		<th>小图名称</th>
			                		<th>榜单图片</th>
			                		<th>驱动规则</th>
			                		<th></th>
	                	</tr>
	                	<tr name="showTypeThree" style="display:none">
	                				<td><input type='text' id='name1' name='name1'></td>
	                				<td><input type='file' id='icon1' name='icon1'></td>
	                				<td>
	                				<select name="qdId1" id="qdId1" >
				                		<c:forEach var="qd" items="${qdList}" >
						              	   	   <option value="${qd.id}">${qd.name}</option>	
				                		</c:forEach>
			              	   		</select>
			              	   		</td>
	                				<td><input type='button' class='btn-info' value='删除' onclick='deletextRow(this)'><input type="button" class="btn-info" name="bt" value="新增" onclick="add_tr(this)"></td>
	                	</tr>
	                	
	                	
	                	<tr name="showTypeTwo" >
							<th>子榜单：</th>
	                	</tr>
	                	<tr>
			                		<th>子榜单名称</th>
			                		<th>驱动规则</th>
			                		<th></th>
	                	</tr>
	                	<tr  name="zphb">
	                				<td><input type='text' id='name2' name='name2'></td>
	                				<td>
	                				<select name="qdId2" id="qdId2" >
				                		<c:forEach var="qd" items="${qdList}" >
						              	   	   <option value="${qd.id}">${qd.name}</option>	
				                		</c:forEach>
			              	   		</select>
			              	   		</td>
	                				<td><input type='button' class='btn-info' value='删除' onclick='deletezRow(this)'><input type="button" class="btn-info" name="bt" value="新增" onclick="add_tr(this)"></td>
	                	</tr>
	                	
	                	
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="添加" onclick="save()">
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
