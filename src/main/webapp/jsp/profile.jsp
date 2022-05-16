<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>${requestScope.requested_user.username}</title>
</head>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<div class="d-flex flex-row justify-content-between">
    <div class="d-flex flex-row mx-4 my-2">
        <img src="${requestScope.requested_user.avatarSource}" class="img-fluid w-25" alt="">
        <div class="d-flex flex-column">
            <h3>${requestScope.requested_user.username}</h3>
            <span>${requestScope.requested_user.firstName} ${requestScope.requested_user.lastName}</span>
        </div>
    </div>
    <c:if test="${requestScope.requested_user.userId == sessionScope.user.userId}">
        <div class="d-flex flex-column align-items-start">
            <form action="upload" enctype="multipart/form-data" method="post">
                <div class="form-group">
                    <input type="hidden" name="file_for" value="user_image">
                    <input type="hidden" name="id" value="${sessionScope.user.userId}">
                    <label for="userImageFileUpload"><fmt:message key="user.profile.update.avatar"/></label>
                    <input type="file" name="user_image" accept="image/png,image/jpeg"
                           class="chooseFileInput form-control-file"
                           id="userImageFileUpload">
                </div>
                <button disabled type="submit" class="btn btn-primary fileUploadSubmit"><fmt:message
                        key="user.profile.update.avatar.submit"/></button>
            </form>
            <a href="controller?command=edit_profile&id=${sessionScope.user.userId}"
               class="btn btn-primary"><fmt:message key="user.profile.update.profile"/></a>
            <c:if test="${sessionScope.user.role == 'ADMIN'}">
                <div class="d-flex flex-row m-3">
                    <a type="button" href="controller?command=all_users_admin" class="btn btn-primary mx-3"><fmt:message
                            key="user.profile.admin.users"/></a>
                    <a type="button" href="controller?command=unapproved_cocktails" class="btn btn-primary"><fmt:message
                            key="user.profile.admin.unapproved"/></a>
                </div>
            </c:if>
        </div>
    </c:if>
</div>
<c:if test="${sessionScope.user.role == 'ADMIN' && (requestScope.requested_user.userId != sessionScope.user.userId)}">
    <form>
        <input type="hidden" name="command" value="edit_user_role">
        <input type="hidden" name="id" value="${requestScope.requested_user.userId}">
        <select name="new_role" class="form-select" aria-label="Role" id="userRoleSelect">
            <option value="client"><fmt:message key="user.profile.admin.role.user"/></option>
            <option value="bartender"><fmt:message key="user.profile.admin.role.bartender"/></option>
        </select>
        <button type="submit" class="btn btn-primary"><fmt:message key="user.profile.admin.role.confirm"/></button>
    </form>
</c:if>
<div>
    <div class="list-group" style="width: 35%">
        <h3><fmt:message key="user.profile.best"/></h3>
        <c:forEach var="cocktail" items="${requestScope.cocktails}">
            <a href="controller?command=show_cocktail&id=${cocktail.id}"
               class="list-group-item list-group-item-action flex-column align-items-start">
                <div class="d-flex container-fluid justify-content-between">
                    <div class="d-flex flex-row">
                        <img src="${cocktail.imageSource}" class="img-fluid w-25" style="width: 25%; height:auto;" alt="">
                        <div class="d-flex flex-column m-2">
                            <h5 class="mb-1">${cocktail.name}</h5>
                            <p class="mb-1" style="white-space: pre-line">${fn:substring(cocktail.instructions, 0, 100)}</p>
                        </div>
                    </div>
                    <small><fmt:formatNumber type="number" maxFractionDigits="2"
                                             value="${cocktail.averageMark}"/></small>
                </div>
            </a>
        </c:forEach>
    </div>
    <a href="controller?command=all_user_cocktails&id=${requestScope.requested_user.userId}"><fmt:message
            key="user.profile.cocktails"/></a>
</div>
<c:import url="footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/js/fileinput.js"></script>
</body>
</html>
