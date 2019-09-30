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
            <select id="datepicker" onchange="window.location = '//${pageContext.request.serverName}:${pageContext.request.serverPort}<c:url
                    value="/datetop"/>?date=' + this.value">
                <%
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                %>
                <c:set var="current_date" value="<%= dateFormat.format(date) %>"/>
                <option disabled <c:if test="${selected_date == null || movies.size() == 0}">selected</c:if>>
                    Выберите дату
                </option>
                <option value="${current_date}" <c:if test="${current_date.equals(selected_date)}"> selected </c:if>  >
                    Сегодня
                </option>

                <c:forEach items="${inTopDates}" var="inTopDate">
                    <c:if test="${!inTopDate.toLocaleString().equals(date.toLocaleString())}">
                        <option value="<fmt:formatDate value="${inTopDate}" pattern="yyyy-MM-dd" />">
                            <fmt:formatDate value="${inTopDate}" pattern="yyyy-MM-dd" />
                        </option>
                    </c:if>
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
