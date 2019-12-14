<%@ page language="java" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Туры</title>
</head>
<body>
<div class="log" style="z-index: 100">
    <c:if test="${isLogin}"><form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <a href="#" onclick="this.parentNode.submit()">Выход</a>
    </form></c:if>
    <c:if test="${!isLogin}"><a href="${pageContext.request.contextPath}/login">Вход</a></c:if>
    <c:if test="${isAdmin}">
        <a href="${pageContext.request.contextPath}/tours">Туры</a><br/>
        <a href="${pageContext.request.contextPath}/flights">Полёты</a><br/>
        <a href="${pageContext.request.contextPath}/requests/requests">Заявки</a><br/>
    </c:if>
</div>
<header>
    <a href="${pageContext.request.contextPath}/"><div class="mainHref">
        <div id="mainText"><h2 style="font-style: italic">Главная</h2></div>
    </div></a>
</header>
<article>