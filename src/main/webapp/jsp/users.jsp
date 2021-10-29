<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title><fmt:message key="users.title"/></title>
</head>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<div class="d-flex flex-row">
    <form class="form-inline my-2 my-lg-0 mx-3" action="controller" method="post">
        <input type="hidden" name="command" value="search_user_by_name">
        <input name="user_name" value="${requestScope.user_name}" class="form-control mr-sm-2" type="search"
               placeholder="<fmt:message key="users.search.placeholder"/>"
               aria-label="<fmt:message key="users.search.placeholder"/>"
               pattern="[a-zA-Zа-яА-ЯЁё -]*">
        <button class="btn btn-success my-2 my-sm-0" type="submit"><fmt:message key="users.search.submit"/></button>
    </form>
</div>
<c:if test="${requestScope.user_name != null}">
    <p><small><fmt:message key="users.search.found"/>${requestScope.users.size()}</small></p>
</c:if>
<div class="list-group">
    <c:forEach var="user" items="${requestScope.users}">
        <a href="controller?command=profile&id=${user.userId}"
           class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex container-fluid justify-content-between">
                <div class="d-flex flex-row">
                    <img src="${user.avatarSource}" class="img-fluid w-25" alt="">
                    <div class="d-flex flex-column m-2">
                        <strong>${user.firstName} ${user.lastName}</strong>
                        <span><fmt:message key="bartenders.created"/>${user.cocktailsCreated}</span>
                        <span><fmt:message key="bartenders.average"/><fmt:formatNumber type="number"
                                                                                       maxFractionDigits="2"
                                                                                       value="${user.averageCocktailsRate}"/></span>
                    </div>
                </div>
            </div>
        </a>
    </c:forEach>
</div>
<c:if test="${requestScope.user_name == null}">
    <ul class="pagination">
        <li class="page-item <c:if test="${requestScope.current_page - 1 <= 0}">disabled</c:if>"><a class="page-link"
                                                                                                    href="controller?command=all_users_admin&page=${requestScope.current_page - 1}"><fmt:message
                key="pagination.prev"/></a>
        </li>
        <li class="page-item"><a class="page-link"
                                 href="controller?command=all_users_admin&page=${requestScope.current_page}">${requestScope.current_page}</a>
        </li>
        <li class="page-item <c:if test="${requestScope.is_last_page}">disabled</c:if>"><a class="page-link"
                                                                                           href="controller?command=all_users_admin&page=${requestScope.current_page + 1}"><fmt:message
                key="pagination.next"/></a>
        </li>
    </ul>
</c:if>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>
