<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../../parts/header.jsp'%>

<div id="content">
    <table border="1">
        <tr>
            <td>Тур</td>
            <td>Количество билетов</td>
            <td>Пункт вылета</td>
            <td>Стоимость</td>
            <td>Статус</td>
        </tr>
        <c:forEach items="${requestsList}" var="request">
            <tr>
                <td>${request.tour.name}</td>
                <td>${request.places}</td>
                <c:if test="${request.flight != null}"><td>${request.flight.out}</td></c:if>
                <c:if test="${request.flight == null}"><td>Без полёта</td></c:if>
                <td>${request.cost}</td>
                <c:if test="${request.confirm}"><td>Подтверждено</td></c:if>
                <c:if test="${!request.confirm}"><td>
                    <a href="<c:url value='/requests/confirm/${request.id}'/>">Подтвердить</a>
                </td></c:if>
            </tr>
        </c:forEach>
    </table>
<%@include file='../../parts/footer.jsp'%>
