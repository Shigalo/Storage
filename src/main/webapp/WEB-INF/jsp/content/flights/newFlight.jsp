<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../../parts/header.jsp'%>

<div id="content">

    <form action="" method="post" style="position: relative; left: 27%" >
        <table border="1">
            <tr><td>Авиаперелёт для тура</td><td><select name="tour">
                <c:forEach items="${tourList}" var="tour"><option>${tour.id}:${tour.target}</option></c:forEach>
            </select></td></tr>
            <tr><td>Пункт отправки</td><td><input type="text" name="out" required></td></tr>
            <tr><td>Стоимость</td><td><input type="number" step="0,01" name="cost" min="0" required></td></tr>
        </table>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div style="position:relative; padding: 5px"><input type="submit" value="Добавить" style="position: relative; left: 100px; top: 20px;"/></div>
    </form>
</div>
<%@include file='../../parts/footer.jsp'%>
