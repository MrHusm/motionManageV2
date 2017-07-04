<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container-fluid">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
				
                <a class="brand" style="width: 200px"><img style="height:25px;width:25px" src="../../image/logo.png">运营管理系统</a>
                <div class="nav-collapse collapse">
                    <p class="navbar-text pull-right">
                            <a href="" class="navbar-link">欢迎您：<%=request.getSession().getAttribute("admin") %></a>
                            <a href="../adminLogin.do?method=adminlogout" class="navbar-link">退出</a>
                    </p>
                    <ul class="nav">
                    </ul>
                </div>
            </div>
        </div>
    </div>
