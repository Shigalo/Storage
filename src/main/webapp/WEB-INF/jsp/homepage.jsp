<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<div id="sidebar">
    <label>Страна:<br><select id="target" onchange="filter()">
        <option>Любая</option>
        <c:forEach items="${targetSet}" var="target"><option>${target}</option></c:forEach>
    </select></label><br>
    <label>Дата тура: От<br>
        <input id="dateS" type="date" onchange="filter()"><br>
        По<br><input id="dateE" type="date" onchange="filter()">
    </label>
    <label>Продолжительность:<br>
        <select id="length" onchange="filter()">
            <option>Любая</option>
            <c:forEach items="${lengthSet}" var="length"><option>${length}</option></c:forEach>
        </select>
    </label><br>
    <label>Тип тура:<br><select id="type" onchange="filter()">
        <option>Все</option>
        <c:forEach items="${typeSet}" var="type"><option>${type}</option></c:forEach>
    </select></label><br>
</div>
<div id="content">
    <c:forEach items="${tourList}" var="tour">
        <c:if test="${tour.post && tour.places !=0}"><table style="left: 100px; position:relative;">
            <tr onclick="document.location = '/tours/getTour/${tour.id}'">
                <td><img src="/tourImage/<c:out value='${tour.id}'/>" style="width: 200px" /><br/></td>
                <td width="950px"><h3 align="center">${tour.name} - ${tour.target}</h3><p align="justify">${tour.about}</p>
                <p>${tour.type}: ${tour.length} ночей за ${tour.cost} р.</p></td>
            </tr>
        </table></c:if>
    </c:forEach>
    <c:if test="${tourList.size() == 0}">
        <h2 style="left: 200px; position:relative">Извините, услуги не предоставляются</h2>
    </c:if>
</div>
<script>
    document.getElementById("dateS").setAttribute("min", new Date().toISOString().split('T')[0]);
    document.getElementById("dateE").setAttribute("min", new Date().toISOString().split('T')[0]);
    function filter() {
        var target = document.getElementById("target");
        var dateS = document.getElementById("dateS");
        var dateE = document.getElementById("dateE");
        var length = document.getElementById("length");
        var type = document.getElementById("type");

        var content = document.getElementById("content");
        content.innerHTML = "";
        <c:forEach items="${tourList}" var="tour">
        <c:if test="${tour.post && tour.places !=0}">

        var table = document.createElement('table');
        table.style.left = "100px";
        table.style.position = "relative";
        var tr = document.createElement('tr');
        var td1 = document.createElement('td');
        td1.innerHTML = "<img src=\"/tourImage/<c:out value='${tour.id}'/>\" style=\"width: 150px\" /><br/>";
        var td2 = document.createElement('td');
        td2.setAttribute("width", "1000px");
        td2.innerHTML = "<h3 align=\"center\">${tour.name} - ${tour.target}</h3><p align=\"justify\">${tour.about}</p>"+
            "<p>${tour.type}: ${tour.length} ночей за ${tour.cost} р.</p>";

        if(target.selectedIndex !== 0) {if(target.options[target.selectedIndex].innerHTML !== '${tour.target}') {table.setAttribute("hidden", "hidden");}}
        if(length.selectedIndex !== 0) {if(length.options[length.selectedIndex].innerHTML !== '${tour.length}') {table.setAttribute("hidden", "hidden");}}
        if(type.selectedIndex !== 0) {if(type.options[type.selectedIndex].innerHTML !== '${tour.type}') {table.setAttribute("hidden", "hidden");}}
        if(dateS.value !== "") {
            var dateT = new Date(0);dateT.setFullYear(${tour.date.year}, ${tour.date.monthValue}, ${tour.date.dayOfMonth});
            var date = new Date(0);date.setFullYear(dateS.value.split("-")[0], dateS.value.split("-")[1], dateS.value.split("-")[2]);
            if(dateT < date) { table.setAttribute("hidden", "hidden"); }
        }
        if(dateE.value !== "") {
            dateT = new Date(0);dateT.setFullYear(${tour.date.year}, ${tour.date.monthValue}, ${tour.date.dayOfMonth});
            date = new Date(0);date.setFullYear(dateE.value.split("-")[0], dateE.value.split("-")[1], dateE.value.split("-")[2]);
            if(dateT > date) { table.setAttribute("hidden", "hidden"); }
        }

        tr.appendChild(td1);
        tr.appendChild(td2);
        table.appendChild(tr);
        content.appendChild(table);
        </c:if>
        </c:forEach>
    }
</script>
<%@include file='parts/footer.jsp'%>
