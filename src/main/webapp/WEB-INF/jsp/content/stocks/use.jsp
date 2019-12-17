<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../../parts/header.jsp'%>
<div id="content">
    <h2>Запас продукции на складе</h2>
    <div>
        <h3>Продукция</h3>
        <ul>
            <li>ID: ${stock.product.id}</li>
            <li>Наименование: ${stock.product.name}</li>
            <li>Тип: ${stock.product.type}</li>
        </ul>
        <h3>Склад</h3>
        <ul>
            <li>ID: ${stock.warehouse.id}</li>
            <li>Адрес: ${stock.warehouse.address}</li>
        </ul>
        <h4>Оставшийся запас: ${stock.quantity}</h4>
        <h4>Точка заказа: ${stock.reorder_level}</h4>
    </div>
    <form action="" method="post" align="center">
        <label>ID запаса: ${stock.id}</label><br/>
        <label>Используемое количество:
            <input type="number" name="quantity" min="1" max="${stock.quantity}" step="1" required>
        </label><br/>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Использовать">
    </form>

</div>
<%@include file='../../parts/footer.jsp'%>
