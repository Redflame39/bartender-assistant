<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="controller?command=home"><fmt:message key="header.brand"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=cocktails"><fmt:message key="header.cocktails"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=bartenders"><fmt:message key="header.bartenders"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-outline-success my-2 my-sm-0"
                       href="${pageContext.request.contextPath}/controller?command=create_cocktail_page"><fmt:message key="header.create"/></a>
                </li>
            </ul>
            <ul class="navbar-nav d-flex align-items-center">
                <c:choose>
                    <c:when test="${authenticated}">
                        <li class="nav-item">
                            <a class="nav-item nav-link"
                               href="${pageContext.request.contextPath}/controller?command=profile&id=${user.userId}"><fmt:message
                                    key="header.profile"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message
                                    key="header.logout"
                            /></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/controller?command=login"><fmt:message
                                    key="header.login"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
</header>