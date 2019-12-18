<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../../parts/header.jsp'%>
<div id="content">
    <div>
        <h2>Информация о пользователе</h2>
        <div id="profile">
            <ul>
                <li>ID: ${user.id}</li>
                <li>Фамилия (логин): ${user.name}</li>
                <li>Имя: ${user.surname}</li>
                <c:if test="${isAdmin}"><li>Статус: Администратор</li></c:if>
                <c:if test="${!isAdmin}"><li>Статус: Пользователь</li></c:if>
                <li>Телефон: ${user.phone}</li>
                <li>Почта: ${user.mail}</li>
            </ul>
            <button onclick="edit()">Изменить</button><br/><br/>
        </div>

        <h3>Список отслеживаемых складов</h3>
        <c:if test="${empty subscribeList}">
            <p>Подписки отсутстуют</p>
        </c:if>
        <c:if test="${!empty subscribeList}">
            <ul>
                <c:forEach items="${subscribeList}" var="subscribe">
                    <li>${subscribe.warehouse.id}) ${subscribe.warehouse.address}</li>
                </c:forEach>
            </ul>
        </c:if>
        <br>
        <a href="<c:url value='/userRemove'/>">Удалить аккаунт</a><br/><br/>

    </div>
</div>
<script>
    function edit() {
        let prof = document.getElementById("profile");
        prof.innerHTML = "<form action=\"${pageContext.request.contextPath}/edit\" method=\"get\">\n" +
            "<label>Фамилия <input type=\"text\" name=\"name\" value=\"${user.name}\"></label><br/>\n" +
            "<label>Имя <input type=\"text\" name=\"surname\" value=\"${user.surname}\"></label><br/>\n" +
            "<input type=\"submit\" value=\"Сохранить\">\n" +
            "</form>";
    }
</script>
<%@include file='../../parts/footer.jsp'%>
