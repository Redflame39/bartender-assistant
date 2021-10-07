<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="controller?command=home"><fmt:message key="brand"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=cocktails"><fmt:message key="cocktails"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><fmt:message key="bartenders"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-outline-success my-2 my-sm-0"
                       href="controller?command=create_cocktail_page"><fmt:message key="create"
                    /></a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="search-button"
                /></button>
            </form>
            <ul class="navbar-nav d-flex align-items-center">
                <c:choose>
                    <c:when test="${authenticated}">
                        <li class="nav-item">
                            <a class="nav-item nav-link"
                               href="controller?command=profile&id=${user.userId}"><fmt:message key="profile"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-item nav-link" href="controller?command=logout"><fmt:message key="logout"
                            /></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-item nav-link" href="controller?command=login"><fmt:message key="login"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
</header>