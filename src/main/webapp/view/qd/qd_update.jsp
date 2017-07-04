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
	                datee:{
	                	digits:true
	                }
	                
	            },
	            messages:{
	                name:{
	                    required:"名称不能为空"
	                },  
	                datee:{
	                	digits:"时间范围必须为整数"
	                }
	            }
	        });    
	
	    });
    
        function showType(param){
        	if($(param).val() == "2"){
        		$("[name='showTypeTwo']").each(function(){
        			$(this).hide();
        		});
        		$("[name='showTypeOne']").each(function(){
        			$(this).show();
        		});
        		$("#categoryId").attr("disabled",true);
        		$("#categoryIdTwo").attr("disabled",true);
        	}else if($(param).val() == "1"){
        		$("[name='showTypeTwo']").each(function(){
        			$(this).show();
        		});
        		$("[name='showTypeOne']").each(function(){
        			$(this).hide();
        		});
        		$("#categoryId").attr("disabled",false);
        		$("#categoryIdTwo").attr("disabled",false);
        	}
        }
        
        

        

        
        function showBy(param){
        	if($(param).val() == "1"){
        		$("[name='showBy']").hide();
        		$("#byId").attr("disabled",true);
        		$("#day").attr("disabled",true);
        	}else if($(param).val() == "2"){
        		$("[name='showBy']").show();
	        	$("#byId").attr("disabled",false);
	    		$("#day").attr("disabled",false);
        	}
        }
        
        
        function save(){

        	$("#form").submit();
        }
        
        function startLoad(xid){
        	var s=0;
        	if($("#qdType").val()=="1"){
        		$("[name='showTypeTwo']").each(function(){
        			$(this).show();
        		});
        		$("[name='showTypeOne']").each(function(){
        			$(this).hide();
        		});
        		$("#categoryId").attr("disabled",false);
        		$("#categoryIdTwo").attr("disabled",false);
        		
        		s = $("#categoryId").val()
        		
        	 	if(s != -1){
            		
            		var categoryIdTwo=document.getElementById('categoryIdTwo');
                	categoryIdTwo.length   =   0;   //初始化下拉列表   清空下拉数据     
            		if(s==0){
            			categoryIdTwo.options[0]   =   new   Option("不限",0); 
            		}else{
            			$.post(
                  				"<%=basePath%>/qd/getBookTwoType",
                  				{"id":s},
                  				function(data){
                  					var ps=eval(data);
                  					categoryIdTwo.options[0]   =   new   Option("不限",0); 
                  					for ( var i = 0; i < ps.length; i++) {
                  						categoryIdTwo.options[i+1]   =   new   Option(ps[i].name,ps[i].id); 
                  						if(categoryIdTwo.options[i+1].value==xid){
                  							categoryIdTwo.options[i+1].selected = true;  
                  						}
                  					}
                  				}
                  			)
            		}
            		
            	
            	
            	}
        	}else{
        		$("[name='showTypeTwo']").each(function(){
        			$(this).hide();
        		});
        		$("[name='showTypeOne']").each(function(){
        			$(this).show();
        		});
        		$("#categoryId").attr("disabled",true);
        		$("#categoryIdTwo").attr("disabled",true);
        		s = $("#categoryIdTwo3").val()
        		if(s != -1){
                	var categoryIdTwo=document.getElementById('categoryIdTwo2');
                	categoryIdTwo.length   =   0;   //初始化下拉列表   清空下拉数据     
                	
                	if(s==0){
            			categoryIdTwo.options[0]   =   new   Option("不限",0); 
            		}else{
                	$.post(
              				"<%=basePath%>/qd/getBookTwoType",
              				{id:s},
              				function(data){
              					var ps=eval(data);
              					categoryIdTwo.options[0]   =   new   Option("不限",0); 
              					for ( var i = 0; i < ps.length; i++) {
              						categoryIdTwo.options[i+1]   =   new   Option(ps[i].authorCategory,ps[i].id);  
              						if(categoryIdTwo.options[i+1].value==xid){
              							categoryIdTwo.options[i+1].selected = true;  
              						}
              					}
              				}
              			)
            		}
                	}
        		
        	}
        }
        
        
        function twoType(param,xid){
        	var id=$(param).val();
        	if($(param).val() != -1){
        		
        		var categoryIdTwo=document.getElementById('categoryIdTwo');
            	categoryIdTwo.length   =   0;   //初始化下拉列表   清空下拉数据     
        		if($(param).val()==0){
        			categoryIdTwo.options[0]   =   new   Option("不限",0); 
        		}else{
        			$.post(
              				"<%=basePath%>/qd/getBookTwoType",
              				{"id":id},
              				function(data){
              					var ps=eval(data);
              					categoryIdTwo.options[0]   =   new   Option("不限",0); 
              					for ( var i = 0; i < ps.length; i++) {
              						categoryIdTwo.options[i+1]   =   new   Option(ps[i].name,ps[i].id); 
              						if(categoryIdTwo.options[i+1].value==xid){
              							categoryIdTwo.options[i+1].selected = true;  
              						}
              					}
              				}
              			)
        		}
        		
        	
        	
        	}
        }
        function twoType2(param,xid){
        	var id=$(param).val();
        	if($(param).val() != -1){
        	var categoryIdTwo=document.getElementById('categoryIdTwo2');
        	categoryIdTwo.length   =   0;   //初始化下拉列表   清空下拉数据     
        	
        	if($(param).val()==0){
    			categoryIdTwo.options[0]   =   new   Option("不限",0); 
    		}else{
        	$.post(
      				"<%=basePath%>/qd/getBookTwoType",
      				{id:id},
      				function(data){
      					var ps=eval(data);
      					categoryIdTwo.options[0]   =   new   Option("不限",0); 
      					for ( var i = 0; i < ps.length; i++) {
      						categoryIdTwo.options[i+1]   =   new   Option(ps[i].authorCategory,ps[i].id);  
      						if(categoryIdTwo.options[i+1].value==xid){
      							categoryIdTwo.options[i+1].selected = true;  
      						}
      					}
      				}
      			)
    		}
        	}
        }
        
    </script>
    
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar" onload="startLoad('${qd.categoryId}')">
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
				驱动规则修改
			  </div>	
                <form id ="form" action="<%=basePath%>/qd/updateQd" method="post">
                <input type="hidden" id="id" name="id" value="${qd.id }">
	                <table class="table table-bordered table-hover">
	                	<tr>
	                		<th style="width:15%">驱动名称：</th>
	                		<td style="width:35%"><input type="text" id="name" name="name" value="${qd.name }"></td>
	                		<th style="width:15%">驱动类型：</th>
	                		<td style="width:35%">
			              	  <select id="qdType"name="qdType" onchange="showType(this)">
			              	   	   <option value="1" ${qd.qdType == '1' ? "selected" : "" }>图书驱动</option>	
			              	   	   <option value="2" ${qd.qdType == '2' ? "selected" : "" }>作者驱动</option>	
			              	   </select>
							</td>
	                	</tr>
	                	<tr name="showTypeTwo"   style="display:none">
	                		<th style="width:15%">图书一级分类：</th>
	                		<td style="width:35%">
			              	  <select name="categoryId" id="categoryId" onchange="twoType(this,'${qd.categoryId}')">
			              	  		<option value='0'  ${qd.twoId == '0'? "selected" : "" }>不限</option>
			              	  		<option value='1'  ${qd.twoId == '1'? "selected" : "" }>男频</option>
			              	  		<option value='2'  ${qd.twoId == '2'? "selected" : "" }>女频</option>
			              	  		<option value='3'  ${qd.twoId == '3'? "selected" : "" }>出版物</option>	
			              	   </select>
							</td>
							<th style="width:15%">图书二级分类：</th>
	                		<td style="width:35%">
			              	  <select name="categoryIdTwo" id="categoryIdTwo" >
			              	  	   <option value='0'>不限</option>	
			              	   </select>
							</td>
	                	</tr>
	                	<tr name="showTypeOne" style="display:none">
	                		<th style="width:15%">作者一级分类：</th>
	                		<td style="width:35%">
			              	  <select name="categoryIdTwo3" id="categoryIdTwo3" onchange="twoType2(this,'${qd.categoryId}')">
			              	  		<option value='0'  ${qd.twoId == '0'? "selected" : "" }>不限</option>
			              	  		<option value='1'  ${qd.twoId == '1'? "selected" : "" }>男频</option>
			              	  		<option value='2'  ${qd.twoId == '2'? "selected" : "" }>女频</option>
			              	  		<option value='3'  ${qd.twoId == '3'? "selected" : "" }>出版物</option>
			              	   </select>
							</td>
							<th style="width:15%">作者二级分类：</th>
	                		<td style="width:35%">
			              	  <select name="categoryIdTwo2" id="categoryIdTwo2" >
			              	  	   <option value='0'>不限</option>	
			              	   </select>
							</td>
	                	</tr>


	                	
	                	
	                	
	                	
	                	
	                	<tr name="showTypeTwo" >
							<th>是否收费：</th>
	                		<td>
	                		   <select name="free" id="free" >
			              	   	   <option value="1" ${qd.free == '1' ? "selected" : "" }>不限</option>	
			              	   	   <option value="2" ${qd.free == '2' ? "selected" : "" }>是</option>	
			              	   	   <option value="3" ${qd.free == '3' ? "selected" : "" }>否</option>	
			              	   </select>
	                		</td>
	                		<th>是否完结:</th>
	                		<td>
	                			<select name="status" id="status"  >
			              	   	   <option value="1" ${qd.status == '1' ? "selected" : "" }>不限</option>	
			              	   	   <option value="2" ${qd.status == '2' ? "selected" : "" }>是</option>	
			              	   	   <option value="3" ${qd.status == '3' ? "selected" : "" }>否</option>	
			              	    </select>
	                		</td>
	                	</tr>
	                	
	                	<tr>
	                		<th>时间段：</th>
	                		<td colspan="3">
	                			<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',alwaysUseStartDate:true})" name="startDate" id="startDate"  value="${qd.startDate}">
	                			——————
	                			<input type="text" class="Wdate input" onFocus="WdatePicker({lang:'en',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',alwaysUseStartDate:true})" name="endDate" id="endDate" value="${qd.endDate }">
	                		</td>
	                	</tr>
	                	<tr>
	                		<th>时间范围:</th>
	                		<td colspan="3">
	                			<input type="text" id="datee" name="datee" value="${qd.datee}"> 天
	                		</td>
	                	</tr>
	                	
	                	<tr >
	                		<th>排序规则：</th>
	                		<td>
	                		   <select name="rule" id="rule"  >
			              	   	   <option value="1" ${qd.rule == '1' ? "selected" : "" }>销售金额降序</option>	
			              	   	   <option value="2" ${qd.rule == '2' ? "selected" : "" }>转化率降序</option>	
			              	   	   <option value="3" ${qd.rule == '3' ? "selected" : "" }>书籍打赏金额降序</option>	
			              	   	   <option value="4" ${qd.rule == '4' ? "selected" : "" }>书籍月票张数降序</option>	
			              	   	   <option value="5" ${qd.rule == '5' ? "selected" : "" }>作者总打赏金额降序</option>	
			              	   	   <option value="6" ${qd.rule == '6' ? "selected" : "" }>作者作品总销售金额降序</option>	
			              	   	   <option value="7" ${qd.rule == '7' ? "selected" : "" }>点击量</option>
			              	   	   <option value="8" ${qd.rule == '8' ? "selected" : "" }>付费arpu</option>	
			              	    </select>
	                		</td>
	                	</tr>
	                	
	                	
	                </table>
	                <div align="center">
	                   <input type="button" class="btn-info" name="bt" value="保存修改" onclick="save()">
	                   <input type="button" class="btn-info" name="bt" value="返回" onclick="window.location.href='<%=basePath%>/bind/listBind'">
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
