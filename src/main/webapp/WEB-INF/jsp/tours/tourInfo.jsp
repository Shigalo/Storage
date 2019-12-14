<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../parts/header.jsp'%>
<div id="content" align="center" style="left: 0">
    <form action="" method="post" enctype="multipart/form-data">
        <div>
            <img src="/tourImage/<c:out value='${tour.id}'/>" style="width: 150px; height: 150px" /><br/>
            <input type="file" accept="image/*" onchange="readURL(this)" name="image" /><br/>
            <table>
                <tr><td><label for="tourName">Название тура</label></td>
                    <td><input id="tourName" name="tourName" placeholder="Название тура" value="${tour.name}"></td></tr>
                <tr><td><label for="tourTarget">Страна назначения</label></td>
                    <td><input id="tourTarget" name="tourTarget" type="text" placeholder="Страна назначения" value="${tour.target}"></td></tr>
                <tr><td><label for="length">Длительность тура</label></td>
                    <td><input type="number" step="1" id="length" name="length" placeholder="Длительность тура" value="${tour.length}"></td></tr>
                <tr><td><label for="tourCost">Стоимость билета</label></td>
                    <td><input type="number" step="0,01" id="tourCost" name="tourCost" placeholder="Стоимсоть" value="${tour.cost}"></td></tr>
                <tr><td><label for="places">Дополнительные билеты</label></td>
                    <td><input type="number" step="1" id="places" name="places" placeholder="Количество мест" value="0"></td></tr>
            </table>
            <table>
                <tr><td><label for="tourText">Описание</label></td><td>
                    <textarea style="width: 1000px; height: 100px" id="tourText" name="tourText" placeholder="Описание">
                        ${tour.about}</textarea></td></tr>
            </table>
        </div>
        <input type="submit" value="Сохранить"><br>
        <input id="post" type="checkbox" name="posting" />Опубликовать<br>
        <input type="button" onclick="addInfo()" value="Добавить строку"><br>
        <table id="infoTable" border="1">
            <tr hidden>
                <td><input name="image" /></td>
                <td><textarea name="header"></textarea></td>
                <td><textarea name="about"></textarea></td>
                <td><input name="sequence"></td>
            </tr>
            <c:forEach items="${infoList}" var="info">
                <tr>
                    <td style="width: 100px">
                        <img src="/infoImage/<c:out value='${info.id}'/>" style="width: 150px" /><br/>
                        <input type="file" accept="image/*" onchange="readURL(this)" name="image" />
                    </td>
                    <td align="center"><p>Заголовок</p><textarea name="header" placeholder="Заголовок">${info.header}</textarea></td>
                    <td align="center"><p>Описание</p><textarea name="about" style="width: 500px; height: 50px" placeholder="Описание">
                            ${info.about}</textarea></td>
                    <td><input type="button" onclick="remove(this)" value="Удалить"></td>
                    <td hidden><input type="number" name="sequence" value="${info.sequence}"></td>
                </tr>
            </c:forEach>
        </table>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</div>
<script>
    if(${tour.post}) {document.getElementById("post").setAttribute("checked", "checked");}

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                var blah = input.parentElement.children[0];
                blah.setAttribute("src", e.target.result);
                blah.style.setProperty("width", "150px");
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
    function addInfo() {
        var table = document.getElementById("infoTable");
        var row = table.insertRow(-1);
        var cell1 = row.insertCell(0);
        cell1.setAttribute("style", "width: 100px");
        var cell2 = row.insertCell(1);
        cell2.setAttribute("align", "center");
        var cell3 = row.insertCell(2);
        cell3.setAttribute("align", "center");
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        cell5.setAttribute("hidden", "");
        cell1.innerHTML = "<img src=\"#\"/><br/>" +
            "<input type=\"file\" accept=\"image/*\" onchange=\"readURL(this)\" name=\"image\" />";
        cell2.innerHTML = "<p>Заголовок</p><textarea name=\"header\" placeholder=\"Заголовок\"></textarea>";
        cell3.innerHTML = "<p>Описание</p><textarea name=\"about\" style=\"width: 500px; height: 50px\" placeholder=\"Описание\"></textarea>";
        cell4.innerHTML = "<input type=\"button\" onclick=\"remove(this)\" value=\"Удалить\">";
        cell5.innerHTML = "<input type=\"number\" name=\"sequence\" value=\"-1\">";
    }
    function remove(element) {
        alert(element.parentElement.parentElement.innerHTML);
        element.parentElement.parentElement.remove();
    }
</script>
<%@include file='../parts/footer.jsp'%>
