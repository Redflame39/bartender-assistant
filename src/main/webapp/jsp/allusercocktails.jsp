<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>All user cocktails</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<c:import url="header.jsp"/>
<div class="list-group" style="width: 35%">
    <h4>All cocktails of user <a
            href="controller?command=profile&id=${requestScope.user_id}">${requestScope.username}</a></h4>
    <c:forEach var="cocktail" items="${requestScope.cocktails}">
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
                <small><fmt:formatNumber type="number" maxFractionDigits="2"
                                         value="${cocktail.averageMark}"/></small>
            </div>
        </a>
    </c:forEach>
</div>
<ul class="pagination">
    <li class="page-item <c:if test="${requestScope.current_page - 1 <= 0}">disabled</c:if>"><a class="page-link"
                                                                                                href="controller?command=all_user_cocktails&id=${requestScope.user_id}&page=${requestScope.current_page - 1}">Previous</a>
    </li>
    <li class="page-item"><a class="page-link"
                             href="controller?command=all_user_cocktails&page=${requestScope.current_page}">${requestScope.current_page}</a>
    </li>
    <li class="page-item <c:if test="${requestScope.is_last_page}">disabled</c:if>"><a class="page-link"
                                                                                       href="controller?command=all_user_cocktails&id=${requestScope.user_id}&page=${requestScope.current_page + 1}">Next</a>
    </li>
</ul>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>
