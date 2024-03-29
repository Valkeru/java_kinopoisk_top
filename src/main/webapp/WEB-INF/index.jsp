<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Welcome!</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
</head>
<body>
<div id="wrapper">
    <div id="container">
        <div id="welcome">
            <h1>TOP 10 Кинопоиска</h1>
        </div>
        <div id="selector">
            <label for="datepicker">Дата:</label>
            <select id="datepicker" onchange="window.location = '//${pageContext.request.serverName}<c:url
                    value="/datetop"/>?date=' + this.value">
                <option disabled
                        <c:if test="${pageContext.request.getAttribute(\"date\") == null || movies.size() == 0}">selected</c:if>>
                    Выберите дату
                </option>
                <option value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>">
                    Сегодня
                </option>

                <c:forEach items="${inTopDates}" var="inTopDate">
                    <option value="<fmt:formatDate value="${inTopDate}" pattern="yyyy-MM-dd" />"
                            <c:if test="${inTopDate.toGMTString().equals(date.toGMTString())}">selected</c:if> >
                            <fmt:formatDate value="${inTopDate}" pattern="yyyy-MM-dd" />
                        </option>
                </c:forEach>
            </select>
        </div>

        <table>
            <tr>
                <th>
                    Позиция
                </th>
                <th>
                    Название
                </th>
                <th>
                    Год выпуска
                </th>
                <th>
                    Рейтинг
                </th>
                <th>
                    Количество проголосовавших
                </th>
            </tr>
            <c:forEach items="${movies}" var="movie">
                <tr>
                    <td>
                            ${ movie.position }
                    </td>
                    <td>
                            ${ movie.russianName }
                        <c:if test="${movie.originalName != null}">
                            (${ movie.originalName })
                        </c:if>
                    </td>
                    <td>
                            ${ movie.year }
                    </td>
                    <td>
                            ${ movie.rating }
                    </td>
                    <td>
                            ${ movie.votersCount }
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
