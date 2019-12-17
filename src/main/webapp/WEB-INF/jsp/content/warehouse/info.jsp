<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../../parts/header.jsp'%>
<div id="content">
    <c:if test="${isLogin}">
        <label style="border: 1px solid black">
            <c:if test="${!subscribe}">
                Склад не отслеживается<br/>
                <a href="<c:url value='/warehouse/subscribe/${warehouse.id}'/>">Начать отслеживать</a></c:if>
            <c:if test="${subscribe}">
                Склад отслеживается<br/>
                <a href="<c:url value='/warehouse/subscribe/${warehouse.id}'/>">Прекратить отслеживать</a>
            </c:if>
        </label><br/>
    </c:if>

    <c:if test="${isAdmin}">
        <form action="" method="post" align="center">
            <h2>Данные склада</h2>
            <label for="address">Адрес склада</label><br>
            <input id="address" name="address" placeholder="Адрес" value="${warehouse.address}"><br>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="submit" value="Сохранить"><br>
        </form></c:if>
    <c:if test="${!isAdmin}">
        <div align="center">
            <h2>Данные склада</h2>
            <label>Адрес склада: ${warehouse.address}</label><br>
        </div></c:if><br/>

    <c:if test="${isAdmin}">
        <form action="/warehouse/addProduct/${warehouse.id}" method="post" align="center">
            <h3>Добавление номенклатуры на склад</h3>
            <c:if test="${empty productList}"><br/>Отсутствуют номенклатуры для добавления<br/></c:if>
            <c:if test="${!empty productList}">
                <label>Номенклатура
                    <select name="productData">
                        <c:forEach items="${productList}" var="product">
                            <option>${product.id} ${product.name} ${product.type}</option>
                        </c:forEach>
                    </select>
                </label><br/>
                <label>Точка заказа<input type="number" step="1" min="0" name="level" required></label><br/>
                <label>Начальный запас<input type="number" step="1" min="0" name="initialStock" required></label><br/>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="submit" value="Добавить продукт на склад"><br>
            </c:if>
        </form>
    </c:if><br/>


    <h2>Складская продукция</h2>
    <c:if test="${empty stockList}">
        <h3>На складе не храниться ни одна номенклатура</h3>
    </c:if>
    <c:if test="${!empty stockList}">
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
                    <td><a href="<c:url value='/warehouse/use/${warehouse.id}?id=${stock.id}'/>">Использовать</a></td>
                    <td><a href="<c:url value='/warehouse/get/${warehouse.id}?id=${stock.id}'/>">Пополнить</a></td>
                    <c:if test="${isAdmin}">
                        <td><a href="<c:url value='/warehouse/remove/${warehouse.id}?id=${stock.id}'/>">Удалить</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<%@include file='../../parts/footer.jsp'%>
