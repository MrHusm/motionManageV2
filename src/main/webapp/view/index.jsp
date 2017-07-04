<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/inc/jstl.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no"/>
<meta name="format-detection" content="address=no;email=no" />
<link type="text/css" rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" type="text/css" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

</head>
<body>
<div class="book-list">
  <ul>
  <c:forEach items="${list }" var="item">
  	   <li onclick="url('/detail/${item.bookId}?type=0')">
	      <div class="book-item">
	        <div class="book-img"><img src="${item.cover}"></div>
	        <div class="book-detail">
	          <h3>${item.bookName}</h3>
	          <div class="author"> ${item.author} </div>
	          <div class="item-intro">${item.brief}</div>
	        </div>
	      </div>
	   </li>
  </c:forEach>
  </ul>
</div>
</body>

</html>
