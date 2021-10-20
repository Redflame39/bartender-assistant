<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Unapproved cocktails</title>
</head>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<div class="list-group">
    <c:forEach var="cocktail" items="${requestScope.cocktails}">
        <div class="list-group-item d-flex justify-content-between align-items-start" id="cocktail${cocktail.id}">
            <div class="d-flex flex-row">
                <img src="${cocktail.imageSource}" class="img-fluid w-25" alt="">
                <div class="d-flex flex-column m-2">
                    <h5><a href="controller?command=show_cocktail&id=${cocktail.id}" class="mb-1">${cocktail.name}</a>
                    </h5>
                    <p class="mb-1 w-25">${fn:substring(cocktail.instructions, 0, 100)}</p>
                </div>
            </div>
            <a type="button" class="btn btn-primary updateApprovedStatusBtn" id="${cocktail.id}">Approve</a>
        </div>
    </c:forEach>
</div>
<ul class="pagination">
    <li class="page-item <c:if test="${requestScope.current_page - 1 <= 0}">disabled</c:if>"><a class="page-link"
                                                                                                href="controller?command=unapproved_cocktails&page=${requestScope.current_page - 1}">Previous</a>
    </li>
    <li class="page-item"><a class="page-link"
                             href="controller?command=unapproved_cocktails&page=${requestScope.current_page}">${requestScope.current_page}</a>
    </li>
    <li class="page-item <c:if test="${requestScope.is_last_page}">disabled</c:if>"><a class="page-link"
                                                                                       href="controller?command=unapproved_cocktails&page=${requestScope.current_page + 1}">Next</a>
    </li>
</ul>
<c:import url="footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/js/approving.js"></script>
</body>
</html>
