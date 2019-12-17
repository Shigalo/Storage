<%@ page language="java" pageEncoding="UTF-8"%>
<div id="sidebar">

    <c:if test="${isLogin}"><a href="<c:url value='/profile'/>">Профиль</a><br/><br/></c:if>
    <a href="<c:url value='/products/list'/>">Номенклатуры</a><br/><br/>
    <a href="<c:url value='/warehouse/list'/>">Склады</a><br/><br/>
    <a href="<c:url value='/stocks/list'/>">Список хранения</a><br/><br/>

</div>