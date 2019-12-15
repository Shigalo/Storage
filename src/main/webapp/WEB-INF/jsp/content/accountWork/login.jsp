<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../../parts/header.jsp'%>

<div id="content">
    <div style="position: relative; left: -200px" align="center">
        <h3>Авторизация</h3>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <table>
                <tr>
                    <td colspan="2">Логин : </td><td><input type="text" name="username" required></td>
                </tr>
                <tr>
                    <td colspan="2">Пароль : </td><td><input type="password" name="password" required></td>
                </tr><input type="hidden" name="_csrf" value="${_csrf.token}" />
                <tr><td></td><td></td><td><input type="submit" value="Войти"/></td></tr>
                <%--<tr><td></td><td></td><td id="errorDiv"></td></tr>--%>
            </table>
        </form>
        <a href="${pageContext.request.contextPath}/registration">Регистрация</a>
    </div>
</div>
<script>
    var sPageURL = window.location.search.substring(1);
    if(sPageURL == "error") {
        // document.getElementById("errorDiv").innerHTML = "Ошибка входа! Пользователь не найден.";
        alert("Пользователь не найден");
    }
</script>
<%@include file='../../parts/footer.jsp'%>
