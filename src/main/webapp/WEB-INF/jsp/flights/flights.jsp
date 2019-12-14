<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../parts/header.jsp'%>

<div id="content">
    <img style="left: 31%; position: relative; width: 300px; height: auto" src="<c:url value="/resources/images/plane.png"/>" width="120" height="90">
    <c:if test="${empty flightList}"><h3 style="left: 23%; position: relative">Записи предоставляемых услуг авиаеревозки не найдены</h3></c:if>
    <a style="left: 15%; position: relative" id="addTour" href="${pageContext.request.contextPath}/flights/add">Добавить</a>
    <c:if test="${!empty flightList}">
        <table border="1">
            <caption>Предложения авиаперелётов</caption>
            <tr>
                <th>ID</th>
                <th>Пункт отправления</th>
                <th>Дата Возвращения</th>
                <th>Пункт прибытия</th>
                <th>Дата прибытия</th>
                <th>Цена билета</th>
            </tr>

            <c:forEach items="${flightList}" var="flight">
                <tr id="${flight.id}">
                    <td>${flight.id}</td>
                    <td>${flight.out}</td>
                    <td>${flight.arrival.toLocalDate()}</td>
                    <td>${flight.tour.target}</td>
                    <td>${flight.tour.date.toLocalDate()}</td>
                    <td>${flight.cost}</td>
                    <td><a href="<c:url value='/flights/remove/${flight.id}'/>">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<%@include file='../parts/footer.jsp'%>

