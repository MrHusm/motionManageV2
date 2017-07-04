<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<style type="text/css">
		#my_menu{
			width:140px;
			background:#F7F2E4;
			height:100%;
		}
		div.sdmenu div.collapsed {
		height: 20px;
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
			line-height: 22px;
			color: #999999;
		}
</style>

<div style="float:left" id="my_menu" class="sdmenu">
	<div>
		<span class="spanstyle">礼品管理</span>
		<a href="<%=basePath%>/coupon/listCoupon" class="astyle">代金券管理</a>
		<a href="<%=basePath%>/code/listCode?type=2" class="astyle">兑换码</a>
	</div>
	<div>
		<span class="spanstyle">赠送设置</span>
		<a href="<%=basePath%>/bind/listBind" class="astyle">绑定赠送</a>
		<a href="<%=basePath%>/recharge/listRecharge?firstFlag=0" class="astyle">充值赠送</a>
		<a href="<%=basePath%>/recharge/listRecharge?firstFlag=1" class="astyle">首充赠送</a>
	</div>
	<div>
		<span class="spanstyle">排行榜配置</span>
		<a href="<%=basePath%>/phb/listPhb" class="astyle">排行榜</a>
		<a href="<%=basePath%>/qudong/listQd" class="astyle">驱动规则</a>
	</div>
	<div>
		<span class="spanstyle">精品管理</span>
		<a href="<%=basePath%>/jpPage/listPage" class="astyle">精品页管理</a>
		<a href="<%=basePath%>/jpBangd/listBangD" class="astyle">榜单管理</a>
		<a href="<%=basePath%>/jpBannerPosition/listBannerPosition" class="astyle">广告位管理</a>
	</div>
	<div>
		<span class="spanstyle">提醒管理</span>
		<a href="<%=basePath%>/remind/listRemind" class="astyle">退出提醒管理</a>
		<a href="<%=basePath%>/versionUp/listVersionUp" class="astyle">版本升级管理</a>
		<a href="<%=basePath%>/plugIn/listPlugIn" class="astyle">插件管理</a>
		<a href="<%=basePath%>/clientAd/listClientAd" class="astyle">启动图管理</a>
		<a href="<%=basePath%>/bannerApp/listBannerApp?pageType=1" class="astyle">广告应用管理</a>
	</div>
    <div>
        <span class="spanstyle">活动配置管理</span>
        <a href="<%=basePath%>/campaign/listLottery" class="astyle">抽奖活动配置</a>
        <a href="<%=basePath%>/registGift/listRegistGift" class="astyle">新手礼包管理</a>
    </div>
</div>
