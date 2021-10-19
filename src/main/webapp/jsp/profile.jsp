<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>${requestScope.requested_user.username}</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="d-flex flex-row justify-content-between">
    <div class="d-flex flex-row mx-4 my-2">
        <img src="${requestScope.requested_user.avatarSource}" class="img-fluid w-25" alt="/img/unknown.png">
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
                    <label for="userImageFileUpload">Update your avatar</label>
                    <input type="file" name="user_image" accept="image/png,image/jpeg" class="form-control-file"
                           id="userImageFileUpload">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <a href="controller?command=edit_profile&id=${sessionScope.user.userId}" class="btn btn-primary">Edit
                profile
                data</a>
            <c:if test="${sessionScope.user.role == 'ADMIN'}">
                <div class="d-flex flex-row m-3">
                    <a type="button" href="controller?command=all_users_admin" class="btn btn-primary mx-3">All users</a>
                    <a type="button" href="controller?command=unapproved_cocktails" class="btn btn-primary">Unapproved
                        cocktails</a>
                </div>
            </c:if>
        </div>
    </c:if>
</div>
<c:if test="${sessionScope.user.role == 'ADMIN' && (requestScope.requested_user.userId != sessionScope.user.userId)}">
    <select name="new_role" class="form-select" aria-label="Role" id="userRoleSelect">
        <option value="user">User</option>
        <option value="bartender">Bartender</option>
    </select>
    <a href="controller?command=edit_user_role&id=${requestScope.requested_user.userId}" class="btn btn-primary">Confirm
        role</a>
</c:if>
</body>
</html>
