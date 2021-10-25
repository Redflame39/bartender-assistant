<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="bartenders.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="d-flex flex-row">
    <form class="form-inline my-2 my-lg-0 mx-3" action="controller" method="post">
        <input type="hidden" name="command" value="search_bartender_name">
        <input name="bartender_name" value="${requestScope.bartender_name}" class="form-control mr-sm-2" type="search"
               placeholder="Search" aria-label="Search">
        <button class="btn btn-success my-2 my-sm-0" type="submit"><fmt:message key="bartenders.search"/></button>
    </form>
</div>
<c:if test="${requestScope.bartender_name != null}">
    <p><small><fmt:message key="bartenders.found"/>${requestScope.bartenders.size()}</small></p>
</c:if>
<div class="list-group">
    <c:forEach var="bartender" items="${requestScope.bartenders}">
        <a href="controller?command=profile&id=${bartender.userId}"
           class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex container-fluid justify-content-between">
                <div class="d-flex flex-row">
                    <img src="${bartender.avatarSource}" class="img-fluid w-25" alt="../img/unknown.png>">
                    <div class="d-flex flex-column m-2">
                        <strong>${bartender.firstName} ${bartender.lastName}</strong>
                        <span><fmt:message key="bartenders.created"/>${bartender.cocktailsCreated}</span>
                        <span><fmt:message key="bartenders.average"/><fmt:formatNumber type="number"
                                                                                       maxFractionDigits="2"
                                                                                       value="${bartender.averageCocktailsRate}"/></span>
                    </div>
                </div>
            </div>
        </a>
    </c:forEach>
</div>
<c:if test="${requestScope.bartender_name == null}">
    <ul class="pagination">
        <li class="page-item <c:if test="${requestScope.current_page - 1 <= 0}">disabled</c:if>"><a class="page-link"
                                                                                                    href="controller?command=bartenders&page=${requestScope.current_page - 1}"><fmt:message
                key="pagination.prev"/></a>
        </li>
        <li class="page-item"><a class="page-link"
                                 href="controller?command=bartenders&page=${requestScope.current_page}">${requestScope.current_page}</a>
        </li>
        <li class="page-item <c:if test="${requestScope.is_last_page}">disabled</c:if>"><a class="page-link"
                                                                                           href="controller?command=bartenders&page=${requestScope.current_page + 1}"><fmt:message
                key="pagination.next"/></a>
        </li>
    </ul>
</c:if>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>
