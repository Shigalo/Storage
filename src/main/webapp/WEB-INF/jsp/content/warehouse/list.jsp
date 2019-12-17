<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../../parts/header.jsp'%>

<div id="content">

    <c:if test="${empty list}"><h2>Нет информации об имеющихся складах</h2></c:if>
    <c:if test="${!empty list}"><h2>Доступыне склады</h2></c:if>
    <c:if test="${!empty list}">
        <br/><br/>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Адрес</th>
            </tr>
            <c:forEach items="${list}" var="warehouse">
                <tr>
                    <td>${warehouse.id}</td>
                    <td>${warehouse.address}</td>
                    <td>
                        <form action="/warehouse/info/${warehouse.id}" method="get" style="display:inline">
                            <a href="#" onclick="this.parentNode.submit()">Подробнее</a>
                        </form></td>
                    <c:if test="${isAdmin}">
                        <td>
                            <a href="<c:url value='/warehouse/removeWarehouse/${warehouse.id}'/>">Удалить</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<%@include file='../../parts/footer.jsp'%>

