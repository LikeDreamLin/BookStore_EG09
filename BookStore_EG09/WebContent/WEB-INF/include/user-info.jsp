<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${empty loginUser }">
		<%-- 用户没有登录的时候显示 --%>
		<div>
			<a href="pages/user/login.jsp">登录</a> | 
			<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
			<a href="pages/cart/cart.jsp">购物车</a>
			<a href="pages/manager/manager.jsp">后台管理</a>
			<a href="index.jsp">返回</a>
		</div>
	</c:when>
	<c:otherwise>
		<%--用户登录成功以后显示的内容 --%>
		<div>
			<span>欢迎<span class="um_span">${loginUser.username }</span>光临尚硅谷书城</span>
			<a href="pages/cart/cart.jsp">购物车</a>
			<a href="client/OrderClientServlet?method=orderList">我的订单</a>
			<a href="client/UserServlet?method=logout">注销</a>&nbsp;&nbsp;
			<a href="index.jsp">返回</a>
		</div>
	</c:otherwise>
</c:choose>


