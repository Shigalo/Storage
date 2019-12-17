<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../../parts/header.jsp'%>

<div id="content">

    <c:if test="${empty list}"><h2>Нет информации о хранении продукции на складах</h2></c:if>
    <c:if test="${!empty list}"><h2>Информация о хранении продукции на складах</h2></c:if>
    <c:if test="${!empty list}">
        <br/><br/>
        <table border="1">
            <tr>
                <th rowspan="2">ID</th>
                <th colspan="2">Склад</th>
                <th colspan="3">Продукция</th>
                <th rowspan="2">Запас</th>
                <th rowspan="2">Точка заказа</th>
            </tr>
            <tr>
                <th>ID</th>
                <th>Адрес</th>
                <th>ID</th>
                <th>Наименование</th>
                <th>Тип</th>
            </tr>
            <c:forEach items="${list}" var="stock">
                <tr>
                    <td>${stock.id}</td>
                    <td>${stock.warehouse.id}</td>
                    <td>${stock.warehouse.address}</td>
                    <td>${stock.product.id}</td>
                    <td>${stock.product.name}</td>
                    <td>${stock.product.type}</td>
                    <td>${stock.quantity}</td>
                    <td>${stock.reorder_level}</td>
                    <td><a href="<c:url value='/stocks/use/${stock.id}'/>">Использовать</a></td>
                    <td><a href="<c:url value='/stocks/get/${stock.id}'/>">Пополнить</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<%@include file='../../parts/footer.jsp'%>

