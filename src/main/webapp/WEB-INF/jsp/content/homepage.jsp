<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../parts/header.jsp'%>
<div id="content">
    <c:forEach items="${warehouses}" var="warehouse">
        <a href="/warehouse/warehouseInfo/${warehouse.id}"><div class="warehouse">
            <p>${warehouse.address}</p>
        </div></a>
    </c:forEach>
    <c:if test="${isAdmin}">
    <div class="warehouse" id="addWarehouse" onclick="add()">
        <h3>Добавить</h3>
    </div>
    </c:if>
</div>
<script>
    function add() {
        let box = document.getElementById("addWarehouse");
        box.setAttribute("onclick", "");
        box.innerHTML = "<form action=\"${pageContext.request.contextPath}/warehouse/add\" method=\"post\">\n" +
            "<input type=\"text\" name=\"address\" placeholder=\"Адресс склада\"/>\n" +
            "<input type=\"hidden\" name=\"_csrf\" value=\"${_csrf.token}\" />\n" +
            "<input type=\"submit\" value=\"Добавить\"/>\n" +
            "</form>";
    }
</script>
<%@include file='../parts/footer.jsp'%>
