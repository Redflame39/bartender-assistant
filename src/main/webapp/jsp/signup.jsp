<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="user.create.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
<form action="controller" method="post">
    <input type="hidden" name="command" value="create_account"/>
    <span class="text-danger">${requestScope.errorMessage}</span>
    <div class="form-group">
        <label for="usernameInputForm"><fmt:message key="user.create.username"/></label>
        <input type="text" name="username" class="form-control" id="usernameInputForm" aria-describedby="usernameHelp" placeholder="Enter username">
        <small id="usernameHelp" class="form-text text-muted"><fmt:message key="user.create.username.info"/></small>
    </div>
    <div class="form-group">
        <label for="emailInputForm"><fmt:message key="user.create.email"/></label>
        <input type="email" name="email" class="form-control" id="emailInputForm" aria-describedby="emailHelp" placeholder="Enter email">
        <small id="emailHelp" class="form-text text-muted"><fmt:message key="user.create.email.info"/></small>
    </div>
    <div class="form-group">
        <label for="firstNameInputForm">First name</label>
        <input type="text" name="first_name" class="form-control" id="firstNameInputForm" aria-describedby="firstnameHelp" placeholder="Enter first name">
    </div>
    <div class="form-group">
        <label for="lastNameInputForm">Last name</label>
        <input type="text" name="last_name" class="form-control" id="lastNameInputForm" aria-describedby="lastnameHelp" placeholder="Enter last name">
    </div>
    <div class="form-group">
        <label for="passwordInputForm"><fmt:message key="user.create.password"/></label>
        <input type="password" name="password" class="form-control" id="passwordInputForm" placeholder="Password">
    </div>
    <div class="form-group">
        <label for="rePasswordInputForm"><fmt:message key="user.create.password.confirm"/></label>
        <input type="password" name="re_password" class="form-control" id="rePasswordInputForm" placeholder="Re-password">
    </div>
    <div class="d-flex align-items-center m-1">
        <button type="submit" class="btn btn-primary"><fmt:message key="user.create.submit"/></button>
        <a href="controller?command=login"><fmt:message key="user.create.signin"/></a>
    </div>
</form>
</body>
</html>
