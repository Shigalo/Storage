<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='../../parts/header.jsp'%>
<div id="content">
    <div>
        <form style="position: relative; left: 30%;"><a id="addTour" href="<c:url value='/requests/addRequest/${tour.id}'/>">Заказать</a></form>
    </div>
    <table><tr>
        <td width="400px"><img style="width: 100%" src="/tourImage/<c:out value='${tour.id}'/>" /><br/></td>
        <td width="800px"><h3 align="center">${tour.name} - ${tour.target}</h3><p align="justify">${tour.about}</p></td>
    </tr></table>
    <table id="infoTable" border="1">
        <c:forEach items="${infoList}" var="info">
            <tr>
                <td width="100px"><img src="/infoImage/<c:out value='${info.id}'/>" style="width: 150px" /><br/></td>
                <td width="1050px"><h3 align="center">${info.header}</h3><p align="justify">${info.about}</p></td>
            </tr>
        </c:forEach>
    </table><br/>
    <div><form style="position: relative; left: 30%;"><a id="addTour" href="<c:url value='/requests/addRequest/${tour.id}'/>">Заказать</a></form></div>
</div>
<%@include file='../../parts/footer.jsp'%>
