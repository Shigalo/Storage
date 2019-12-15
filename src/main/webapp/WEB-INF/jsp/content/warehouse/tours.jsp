<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../../parts/header.jsp'%>

<div id="content">
    <c:if test="${empty tourList}"><h3 style="left: 23%; position: relative">Записи предоставляемых услуг не найдены</h3></c:if>
    <a id="addTour" style="left: 35%; position: relative" href="${pageContext.request.contextPath}/tours/add">Добавить</a>
    <c:if test="${!empty tourList}">
        <table border="1" style="left: 10%; position: relative;">
            <br/><br/>
            <h2>Предложения</h2>
            <tr>
                <th>ID тура</th>
                <th>Название</th>
                <th>Страна</th>
                <th>Количество свободных мест</th>
                <th>Дата</th>
                <th>Статус</th>
            </tr>

            <c:forEach items="${tourList}" var="tour">
                <tr id="${tour.id}">
                    <td>${tour.id}</td>
                    <td>${tour.name}</td>
                    <td>${tour.target}</td>
                    <td>${tour.places}</td>
                    <td>${tour.date.toLocalDate()}</td>
                    <c:if test="${tour.post}"><td>Размещено</td></c:if>
                    <c:if test="${!tour.post}"><td>Скрыто</td></c:if>
                    <td>
                        <form action="/tours/tourInfo/${tour.id}" method="get" style="display:inline">
                            <a href="#" onclick="this.parentNode.submit()">Подробнее</a>
                        </form></td>
                    <td><a href="<c:url value='/tours/remove/${tour.id}'/>">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<%@include file='../../parts/footer.jsp'%>

