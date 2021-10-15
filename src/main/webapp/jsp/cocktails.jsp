<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cocktails</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<div class="d-flex flex-row">
    <form class="form-inline my-2 my-lg-0 mx-3">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-success my-2 my-sm-0" type="submit">Search</button>
    </form>
    <div class="d-flex flex-column mx-3">
        <label for="orderSelect">Order</label>
        <select name="order" class="form-select" aria-label="Order" id="orderSelect">
            <option selected value="asc">Ascending</option>
            <option value="desc">Descending</option>
        </select>
    </div>
    <div class="d-flex flex-column">
        <label for="orderBySelect">Order by</label>
        <select name="order_by" class="form-select" aria-label="OrderBy" id="orderBySelect">
            <option selected value="rate">Rate</option>
            <option value="date">Publication date</option>
        </select>
    </div>
</div>
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
                <small><fmt:formatNumber type="number" maxFractionDigits="2" value="${cocktail.averageMark}"/></small>
            </div>
        </a>
    </c:forEach>
</div>
<ul class="pagination">
    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item"><a class="page-link" href="#">Next</a></li>
</ul>
</body>
</html>
