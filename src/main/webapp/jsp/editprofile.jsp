<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Edit profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
<form action="controller" method="post">
    <input type="hidden" name="command" value="save_updated_profile"/>
    <input type="hidden" name="id" value="${requestScope.requested_user.userId}"/>
    <span class="text-danger" id="error">${requestScope.errorMessage}</span>
    <div class="form-group">
        <label for="usernameInputForm">Username</label>
        <input type="text" name="username" value="${requestScope.requested_user.username}" class="form-control"
               id="usernameInputForm" aria-describedby="usernameHelp"
               placeholder="Enter username">
        <small id="usernameHelp" class="form-text text-muted">5-20 symbols.</small>
    </div>
    <div class="form-group">
        <label for="firstNameInputForm">First name</label>
        <input type="text" name="first_name" value="${requestScope.requested_user.firstName}" class="form-control"
               id="firstNameInputForm"
               aria-describedby="firstnameHelp" placeholder="Enter first name">
    </div>
    <div class="form-group">
        <label for="lastNameInputForm">Last name</label>
        <input type="text" name="last_name" value="${requestScope.requested_user.lastName}" class="form-control"
               id="lastNameInputForm" aria-describedby="lastnameHelp"
               placeholder="Enter last name">
    </div>
    <button type="submit" class="btn btn-primary" id="submitBtn">Submit</button>
</form>
<script src="${pageContext.request.contextPath}/js/edit-profile.js"></script>
</body>
</html>
