<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../../parts/header.jsp'%>

<div id="content">

    <c:if test="${isAdmin}">
        <form action="${pageContext.request.contextPath}/products/add" method="get" align="center">
            <h3>Новая номенклатура</h3>
            <label>Наименование <input type="text" name="name" placeholder="Наименование" required></label><br/><br/>
            <label>Тип
                <select name="type">
                    <option>Сырьё</option>
                    <option>Побочный продукт</option>
                    <option>Готовая продукция</option>
                    <option>Полуфабрикат</option>
                </select></label><br/>
            <a href="#" onclick="this.parentNode.submit()">Добавить</a>
        </form>
    </c:if>
    <c:if test="${empty list}"><h2>Номенклатуры отсутствуют</h2></c:if>
    <c:if test="${!empty list}"><h2>Номенклатуры</h2></c:if>
    <c:if test="${!empty list}">
        <br/><br/>
        <h2>Продукция</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Наименование</th>
                <th>Тип</th>
            </tr>
            <c:forEach items="${list}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.type}</td>
                    <td>
                        <form action="/products/info/${product.id}" method="get" style="display:inline">
                            <a href="#" onclick="this.parentNode.submit()">Подробнее</a>
                        </form></td>
                    <c:if test="${isAdmin}">
                        <td>
                            <a href="<c:url value='/products/remove/${product.id}'/>">Удалить</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<%@include file='../../parts/footer.jsp'%>

