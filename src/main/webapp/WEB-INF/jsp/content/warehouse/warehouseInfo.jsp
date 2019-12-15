<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../../parts/header.jsp'%>
<div id="content">
    <form action="" method="post">
        <label for="address">Адрес склада</label>
        <input id="address" name="address" placeholder="Адрес" value="${warehouse.address}">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Сохранить"><br>
    </form>
    <br/>
    <form action="/warehouse/addProduct/${warehouse.id}" method="post">
        <label>Продукт<select name="productData">
            <c:forEach items="${productList}" var="product">
                <option>${product.id}<br/>${product.name}<br/>${product.type}<br/></option>
            </c:forEach>
        </select></label><br/>
        <label>Точка заказа<input type="number" step="1" min="0" name="level" required></label>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Добавить продукт на склад"><br>
    </form>
    <br/>
    <table id="stocksTable" border="1">
        <tr>
            <th>id</th>
            <th>Продукция</th>
            <th>Тип продукции</th>
            <th>Запас</th>
            <th>Точка заказа</th>
        </tr>
        <c:forEach items="${stockList}" var="stock">
            <tr>
                <td>${stock.id}</td>
                <td>${stock.product.name}</td>
                <td>${stock.product.type}</td>
                <td>${stock.quantity}</td>
                <td>${stock.reorder_level}</td>
                <td><a href="<c:url value='/warehouse/remove/${warehouse.id}?id=${stock.id}'/>">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file='../../parts/footer.jsp'%>
