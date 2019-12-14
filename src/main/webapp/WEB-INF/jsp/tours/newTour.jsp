<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../parts/header.jsp'%>

<div id="content">
    <form action="" method="post" style="position: relative; left: 27%" >
        <table border="1">
            <tr><td>Страна</td><td><input type="text" name="target" required></td></tr>
            <tr><td>Название тура</td><td><input type="text" name="name" required></td></tr>
            <tr><td>Количество мест</td><td><input type="number" name="places" step="1" min="1" required></td></tr>
            <tr><td>Дата начала</td><td><input id="date" type="datetime-local" name="date" required max="2021-01-01"></td></tr>
            <tr><td>Длительность</td><td><input type="number" name="length" step="1" min="0" required></td></tr>
            <tr><td>Тип тура</td><td><input type="text" name="type" required></td></tr>
            <tr><td>Стоимость</td><td><input type="number" name="cost" step="0,01" min="0" required></td></tr>
        </table>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Добавить" style="position: relative; left: 100px; top: 20px;"/>
    </form>
</div>
<script>document.getElementById("date").setAttribute("min", new Date().toISOString().split('T')[0]);</script>
<%@include file='../parts/footer.jsp'%>
