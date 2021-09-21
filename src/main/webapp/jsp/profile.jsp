<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vanya
  Date: 9/18/2021
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>${user.username}'s profile</title>
</head>
<body>
<c:import url="header.jsp"/>
<span>${user.username}</span>
<span>${user.avatarSource}</span>
<c:if test="${owner}">
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
</c:if>
</body>
</html>
