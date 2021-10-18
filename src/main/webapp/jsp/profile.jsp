<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>${user.username}</title>
</head>
<body>
<c:import url="header.jsp"/>
<c:choose>
    <c:when test="${owner}">
        <div class="d-flex flex-column">
            <span>${user.username}</span>
            <div class="d-flex flex-row">
                <span>${user.firstName}</span> <span>${user.lastName}</span>
            </div>
        </div>
        <img src="${user.avatarSource}" class="img-fluid">
        <form action="upload" enctype="multipart/form-data" method="post">
            <div class="form-group">
                <input type="hidden" name="file_for" value="user_image">
                <input type="hidden" name="id" value="${user.userId}">
                <label for="userImageFileUpload">Update your avatar</label>
                <input type="file" name="user_image" accept="image/png,image/jpeg" class="form-control-file"
                       id="userImageFileUpload">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </c:when>
    <c:otherwise>
        <span>${requestedUser.username}</span>
        <img src="${requestedUser.avatarSource}" class="img-fluid">
    </c:otherwise>
</c:choose>
<c:if test="${sessionScope.user.role == 'ADMIN'}">
    <a type="button" href="controller?command=admin_page" class="btn btn-primary">Admin page</a>
</c:if>
</body>
</html>
