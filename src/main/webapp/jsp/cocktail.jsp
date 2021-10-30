<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>${requestScope.cocktail.name}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<script src="${pageContext.request.contextPath}/js/textareavalidation.js"></script>
<c:import url="header.jsp" charEncoding="utf-8"/>
<div class="container-fluid">
    <div class="d-flex flex-row justify-content-between">
        <div class="d-flex flex-row mb-3">
            <img src="${requestScope.cocktail.imageSource}" alt="/img/unknown.png"/>
            <div class="d-flex flex-column">
                <h1>${requestScope.cocktail.name}</h1>
                <span><fmt:message key="cocktail.author"/>
                    <a href="controller?command=profile&id=${requestScope.author.userId}">
                    ${requestScope.author.username}</a></span>
                <span>${requestScope.cocktail.averageMark}</span>
                <span>${requestScope.cocktail.instructions}</span>
            </div>
        </div>
        <c:if test="${(requestScope.cocktail.userId == sessionScope.user.userId) || sessionScope.user.role == 'ADMIN'}">
            <div class="d-flex flex-column align-items-start">
                <a href="controller?command=update_cocktail&id=${requestScope.cocktail.id}"
                   class="btn btn-primary"><fmt:message key="cocktail.update.info"/></a>
                <a href="controller?command=cocktail_image&id=${requestScope.cocktail.id}" class="btn btn-primary my-3"><fmt:message
                        key="cocktail.update.image"/></a>
                <a href="controller?command=delete_cocktail&id=${requestScope.cocktail.id}"
                   class="btn btn-danger"><fmt:message key="cocktail.delete"/></a>
            </div>
        </c:if>
    </div>
    <br>
    <form name="addReviewForm" method="POST" action="controller">
        <input type="hidden" name="command" value="post_review">
        <input type="hidden" name="id" value="${requestScope.cocktail.id}">
        <div class="form-group">
            <label for="reviewTextFormTextArea"><fmt:message key="cocktail.review.text"/></label>
            <textarea class="form-control" name="review_text" id="reviewTextFormTextArea" rows="3"
                      minlength="10" maxlength="300" onchange="checkTextarea(this)"></textarea>
        </div>
        <label for="cocktailMark"><fmt:message key="cocktail.review.rate"/></label>
        <select name="review_mark" class="form-select" aria-label="Mark" id="cocktailMark">
            <option value="5"><fmt:message key="cocktail.review.rate.5"/></option>
            <option value="4"><fmt:message key="cocktail.review.rate.4"/></option>
            <option value="3"><fmt:message key="cocktail.review.rate.3"/></option>
            <option value="2"><fmt:message key="cocktail.review.rate.2"/></option>
            <option value="1"><fmt:message key="cocktail.review.rate.1"/></option>
        </select>
        <button type="submit" class="btn btn-primary"
                <c:if test="${sessionScope.user.userId == requestScope.cocktail.userId}">disabled</c:if>><fmt:message
                key="cocktail.review.submit"/>
        </button>
    </form>
    <span class="text-danger">${requestScope.errorMessage}</span>
    <div class="list-group">
        <c:forEach var="review" items="${requestScope.reviews}">
            <div class="list-group-item d-flex justify-content-between align-items-start">
                <div class="d-flex flex-row align-items-center">
                    <img src="${review.authorImage}" class="mx-3" style="width: 5vw; height: 5vh" alt="">
                    <div class="d-flex flex-column">
                        <strong><a
                                href="controller?command=profile&id=${review.authorId}">${review.authorName}</a></strong>
                        <span>${review.commentText}</span>
                        <strong>${review.rate}</strong>
                    </div>
                </div>
                <c:if test="${review.authorId == sessionScope.user.userId || sessionScope.user.role == 'ADMIN'}">
                    <a type="button"
                       href="controller?command=delete_review&id=${review.reviewId}&cocktail_id=${requestScope.cocktail.id}"
                       class="btn btn-danger btn-sm"><fmt:message key="cocktail.review.delete"/></a>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>
