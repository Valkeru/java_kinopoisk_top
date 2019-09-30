<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<%--            <select id="datepicker" onchange="window.location = '{{ url('datetop') }}?date=' + this.value">--%>
            <select id="datepicker" onchange="console.log('Test')">
                {% set selected_date = app.request.query.get('date', date().format('Y-m-d')) %}
                {% set current_date = date('now').format('Y-m-d') %}
                <option disabled {% if selected_date is same as(null) or top|length is same as(0) %} selected {% endif
                        %}>Выберите дату
                </option>
                <option value="{{ current_date }}" {% if current_date is same as(selected_date) %} selected {% endif %}>
                    Сегодня
                </option>
                {% for date in dateList %}
                {% set value = date.format('Y-m-d') %}
                <option value="{{ value }}" {% if value is same as(selected_date) %} selected {% endif %}>{{
                    date.format('d.m.Y') }}
                </option>
                {% endfor %}
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
