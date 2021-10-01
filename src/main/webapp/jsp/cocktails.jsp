<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cocktails</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<div class="list-group">
    <c:forEach var="cocktail" items="${cocktails}">
        <a href="controller?command=show_cocktail&id=${cocktail.id}"
           class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex container-fluid justify-content-between">
                <div class="d-flex flex-row">
                    <img src="${cocktail.imageSource}" class="img-fluid w-25" alt="<%--todo alt--%>">
                    <div class="d-flex flex-column m-2">
                        <h5 class="mb-1">${cocktail.name}</h5>
                        <p class="mb-1">${cocktail.instructions}</p>
                    </div>
                </div>
                <small>Average mark</small>
            </div>
        </a>
    </c:forEach>
</div>
</body>
</html>
