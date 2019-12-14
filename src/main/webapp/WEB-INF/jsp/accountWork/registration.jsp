<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../parts/header.jsp'%>

<div id="content" align="center" style="left: -200px">
    <form action="" method="post">
        <table>
            <tr><td>Введите имя</td><td>
                <input type="text" name="username" value="${username}"
                       required pattern="[A-Za-zА-Яа-яЁё]{2,10}" placeholder="2-10 букв"></td></tr>
            <tr><td>Введите фамилию</td><td>
                <input type="text" name="surname" value="${surname}"
                       required pattern="[A-Za-zА-Яа-яЁё]{2,10}" placeholder="2-10 букв"></td></tr>
            <tr><td>Введите пароль</td><td>
                <input type="password" name="password" value="${password}"
                       required pattern="[A-Za-zА-Яа-яЁё0-9]{4,16}" placeholder="4-16 букв и цифр"></td></tr>
            <tr><td>Подтвердите пароль</td><td>
                <input type="password" name="passwordConfirm" required></td></tr>
            <tr><td>Введите контакт для связи</td><td>
                <input type="tel" name="phone" id="phone" class="masked" required
                       placeholder="(XX) XXX-XX-XX" pattern="[(][0-9]{2}[)]\s[0-9]{3}-[0-9]{2}-[0-9]{2}" ></tr>
        </table>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <span>${massage}</span>
        <div style="position:relative; padding: 5px"><input type="submit" value="Регистрация"/></div>
    </form>
</div>
<script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/3/masking-input.js" data-autoinit="true"></script>
<%@include file='../parts/footer.jsp'%>
