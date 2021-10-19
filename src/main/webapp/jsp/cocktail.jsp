<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>${cocktail.name}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<c:import url="/jsp/header.jsp" charEncoding="utf-8"/>
<div class="container-fluid">
    <div class="d-flex flex-row justify-content-between">
        <div class="d-flex flex-row mb-3">
            <img src="${requestScope.cocktail.imageSource}" alt="/img/unknown.png"/>
            <div class="d-flex flex-column">
                <h1>${cocktail.name}</h1>
                <span>${cocktail.averageMark}</span>
                <span>${cocktail.instructions}</span>
            </div>
        </div>
        <c:if test="${(cocktail.userId == sessionScope.user.userId) || sessionScope.user.role == 'ADMIN'}">
            <div class="d-flex flex-column align-items-start">
                <a href="controller?command=update_cocktail&id=${cocktail.id}" class="btn btn-primary">Update cocktail info</a>
                <a href="controller?command=cocktail_image&id=${cocktail.id}" class="btn btn-primary my-3">Update cocktail picture</a>
                <a href="controller?command=delete_cocktail&id=${cocktail.id}" class="btn btn-danger">Delete cocktail</a>
            </div>
        </c:if>
    </div>
    <br>
    <form name="addReviewForm" method="POST" action="controller">
        <input type="hidden" name="command" value="post_review">
        <input type="hidden" name="id" value="${cocktail.id}">
        <div class="form-group">
            <label for="reviewTextFormTextArea">Review text</label>
            <textarea class="form-control" name="review_text" id="reviewTextFormTextArea" rows="3"></textarea>
        </div>
        <label for="cocktailMark">Rate this cocktail</label>
        <select name="review_mark" class="form-select" aria-label="Mark" id="cocktailMark">
            <option value="5">5 - Great</option>
            <option value="4">4 - Wonderful</option>
            <option value="3">3 - Good</option>
            <option value="2">2 - Weak</option>
            <option value="1">1 - Bad</option>
        </select>
        <button type="submit" class="btn btn-primary"
                <c:if test="${sessionScope.user.userId == requestScope.cocktail.userId}">disabled</c:if>>Submit
        </button>
    </form>
    <span class="text-danger">${requestScope.errorMessage}</span>
    <div class="list-group">
        <c:forEach var="review" items="${reviews}">
            <div class="list-group-item d-flex justify-content-between align-items-start">
                <div class="d-flex flex-row align-items-center">
                    <img src="${review.authorImage}" class="mx-3" style="width: 5vw; height: 5vh" alt="">
                    <div class="d-flex flex-column">
                        <strong><a
                                href="controller?command=profile&id=${review.authorId}">${review.authorName}</a></strong>
                        <span>${review.comment}</span>
                        <strong>${review.rate}</strong>
                    </div>
                </div>
                <c:if test="${review.authorId == sessionScope.user.userId || sessionScope.user.role == 'ADMIN'}">
                    <a type="button" href="controller?command=delete_review&id=${review.reviewId}&cocktail_id=${cocktail.id}"
                       class="btn btn-danger btn-sm">Delete review</a>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
