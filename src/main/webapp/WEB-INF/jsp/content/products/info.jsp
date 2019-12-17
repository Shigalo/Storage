<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../../parts/header.jsp'%>
<div id="content">
    <c:if test="${isAdmin}">
        <form action="" method="post" align="center">
            <h2>Номенклатура</h2>
            <label for="name">Наименование</label>
            <input id="name" name="name" placeholder="Наименование" value="${product.name}"><br/>
            <label>Тип <select name="type" id="type">
                <option>Сырьё</option>
                <option>Побочный продукт</option>
                <option>Готовая продукция</option>
                <option>Полуфабрикат</option>
            </select></label><br/>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="submit" value="Изменить">
        </form>
    </c:if>
    <c:if test="${!isAdmin}">
        <h2>Номенклатура</h2>
        <label>Наименование: ${product.name}</label>
        <label>Тип: ${product.type}</label><br/>
    </c:if><br/>

    <label><h3>Склады хранения</h3>
        <c:if test="${empty stockList}">
        <br/>Продукция нигде не учитывается<br/>
        </c:if>

        <c:if test="${!empty stockList}">
        <table id="stocksTable" border="1">
            <tr>
                <th>id</th>
                <th>Адрес склада</th>
                <th>Запас</th>
                <th>Точка заказа</th>
            </tr>
            <c:forEach items="${stockList}" var="stock">
                <tr>
                    <td>${stock.id}</td>
                    <td>${stock.warehouse.address}</td>
                    <td>${stock.quantity}</td>
                    <td>${stock.reorder_level}</td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
</div>
<script>
    let options = document.getElementById("type").options;
    for(let i = 0; i < options.length; i++) {
        if(options[i].innerText === '${product.type}') {
            options[i].selected = true;
            break;
        }
    }
</script>
<%@include file='../../parts/footer.jsp'%>
