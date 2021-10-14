<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Bartenders</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="list-group">
    <c:forEach var="bartender" items="${bartenders}">
        <a href="controller?command=profile&id=${bartender.userId}" class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex container-fluid justify-content-between">
                <div class="d-flex flex-row">
                    <img src="${bartender.avatarSource}" class="img-fluid w-25" alt="<%--todo alt--%>">
                    <div class="d-flex flex-column m-2">
                        <h5 class="mb-1">${bartender.username}</h5>
                        <p class="mb-1"></p>
                    </div>
                </div>
                <small><fmt:formatNumber type="number" maxFractionDigits="2" value=""/></small>
            </div>
        </a>
    </c:forEach>
</div>
</body>
</html>
