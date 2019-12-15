<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<meta http-equiv="Refresh" content="10" />--%>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Складские запасы</title>
</head>
<body>
<header>
    <div class="log">
        <c:if test="${isLogin}"><form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <a href="#" onclick="this.parentNode.submit()">Выход</a>
        </form></c:if>
        <c:if test="${!isLogin}"><a href="${pageContext.request.contextPath}/login">Вход</a></c:if>
    </div>
    <a href="${pageContext.request.contextPath}/"><div class="mainHref">
        <div id="mainText"><h2 style="font-style: italic">Главная</h2></div>
    </div></a>
</header>
<article>
    <%@include file='../parts/sidebar.jsp'%>
