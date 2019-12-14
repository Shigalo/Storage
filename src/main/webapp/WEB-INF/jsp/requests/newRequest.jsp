<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../parts/header.jsp'%>

<div id="content">
    <table border="1"><tr>
        <td width="400px"><img style="width: 100%" src="/tourImage/<c:out value='${tour.id}'/>" /><br/></td>
        <td width="800px"><h3 align="center">${tour.name} - ${tour.target}</h3><p align="justify">${tour.about}</p></td>
    </tr></table>

    <form action="" method="post" align="center"><br/>
        <label>Осталось мест : ${tour.places}<br/>
            Количество билетов : <input id="places" type="number" name="places" min="0" value="1" step="1" oninput="setCost()"><br/>
            Цена : <input id="cost" value="0" disabled><br/><br/>
            <input id="costH" hidden name="cost" value="0"><br/><br/>
        </label>
        <label>
            <br/>Авиаперелёт<br/>
            <input type="radio" name="flights" id="with" value="with" onclick="setCost()">
            <label for="with">Заказать</label><br>
            <input type="radio" name="flights" id="without" checked value="without" onclick="setCost()">
            <label for="without">Не заказывать</label><br>
        </label>


        <div id="flightDiv" hidden>
            <c:if test="${flightList.size() != 0}">
                <label for="flight">Вылет из:</label>
                <select id="flight" onchange="setCost()">
                    <c:forEach items="${flightList}" var="flight"><option>${flight.out}</option><option hidden>${flight.id}</option></c:forEach>
                </select>
                <div id="flightInfo">
                    <input name="flightId" hidden value="${flightList.get(0).id}">
                    Пункт вылета: ${flightList.get(0).out}<br/>
                    Стоимость билета: ${flightList.get(0).cost}<br/>
                    Дата вылета: ${flightList.get(0).arrival.toLocalDate()}<br/>
                    Время вылета: ${flightList.get(0).arrival.toLocalTime()}<br/>
                </div>
            </c:if>
            <c:if test="${flightList.size() == 0}">Рейсы отсутствуют<select id="flight" name="flight" hidden></select></c:if>
        </div>
        <p>Дополнительные вопросы</p>
        <textarea name="questions">
            </textarea>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div style="position:relative; padding: 5px"><input type="submit" value="Подтвердить"/></div><br/><br/>
    </form>
</div>
<script>
    document.getElementById("places").setAttribute("max", ${tour.places});
    document.getElementById("cost").setAttribute("value", ${tour.cost});
    function setCost() {
        var places = document.getElementById("places");
        var flightCost = 0;
        <c:if test="${flightList != null}">
        if(document.getElementById("with").checked) {
            var flightDiv = document.getElementById("flightDiv");
            flightDiv.hidden = false;
            var flight = document.getElementById("flight");

            <c:forEach items="${flightList}" var="flight">
            if('${flight.id}' == flight.options[flight.selectedIndex+1].innerHTML) {
                flightCost = ${flight.cost};
                var flightInfo = document.getElementById("flightInfo");
                flightInfo.innerHTML = "<input name=\"flightId\" hidden value=\"${flight.id}\"><br/>\n" +
                    "Пункт вылета: ${flight.out}<br/>\n" +
                    "Стоимость билета: ${flight.cost}<br/>\n" +
                    "Дата вылета: ${flight.arrival.toLocalDate()}<br/>\n" +
                    "Время вылета: ${flight.arrival.toLocalTime()}"
            }
            </c:forEach>
        }
        else {
            document.getElementById("flightDiv").hidden = true;
            flightCost = 0;
        }
        </c:if>
        document.getElementById("cost").setAttribute("value", places.value*(Number(${tour.cost})+Number(flightCost)));
        document.getElementById("costH").setAttribute("value", places.value*(Number(${tour.cost})+Number(flightCost)));

    }
    setCost();
</script>
<%@include file='../parts/footer.jsp'%>
