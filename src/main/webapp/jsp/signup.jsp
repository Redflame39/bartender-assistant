<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="user.create.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<form action="controller" method="post" class="needs-validation">
    <input type="hidden" name="command" value="create_account"/>
    <span class="text-danger" id="errorMessage">${requestScope.errorMessage}</span>
    <div class="form-group">
        <label for="usernameInputForm"><fmt:message key="user.create.username"/></label>
        <input type="text" name="username" class="form-control" id="usernameInputForm" aria-describedby="usernameHelp"
               placeholder="<fmt:message key="user.create.username.placeholder"/>"
               pattern="${requestScope.username_regexp}" required>
        <small id="usernameHelp" class="form-text text-muted"><fmt:message key="user.create.username.info"/></small>
        <div class="text-danger" id="usernameInvalidFeedback"></div>
    </div>
    <div class="form-group">
        <label for="emailInputForm"><fmt:message key="user.create.email"/></label>
        <input type="email" name="email" class="form-control" id="emailInputForm" aria-describedby="emailHelp"
               placeholder="<fmt:message key="user.create.email.placeholder"/>" required>
        <small id="emailHelp" class="form-text text-muted"><fmt:message key="user.create.email.info"/></small>
        <div class="text-danger" id="emailInvalidFeedback"></div>
    </div>
    <div class="form-group">
        <label for="firstNameInputForm"><fmt:message key="user.create.firstname"/></label>
        <input type="text" name="first_name" class="form-control" id="firstNameInputForm"
               aria-describedby="firstnameHelp" placeholder="<fmt:message key="user.create.firstname.placeholder"/>"
               pattern="${requestScope.first_last_name_regexp}">
        <div class="text-danger" id="firstNameInvalidFeedback"></div>
    </div>
    <div class="form-group">
        <label for="lastNameInputForm"><fmt:message key="user.create.lastname"/></label>
        <input type="text" name="last_name" class="form-control" id="lastNameInputForm" aria-describedby="lastnameHelp"
               placeholder="<fmt:message key="user.create.lastname.placeholder"/>"
               pattern="${requestScope.first_last_name_regexp}">
        <div class="text-danger" id="lastNameInvalidFeedback"></div>
    </div>
    <div class="form-group">
        <label for="passwordInputForm"><fmt:message key="user.create.password"/></label>
        <input type="password" name="password" class="form-control" id="passwordInputForm"
               placeholder="<fmt:message key="user.create.password.placeholder"/>" required
               pattern="${requestScope.password_regexp}">
        <small class="form-text text-muted"><fmt:message key="user.create.password.info"/></small>
        <div class="text-danger" id="passwordInvalidFeedback"></div>
    </div>
    <div class="form-group">
        <label for="rePasswordInputForm"><fmt:message key="user.create.password.confirm"/></label>
        <input type="password" name="re_password" class="form-control" id="rePasswordInputForm"
               placeholder="<fmt:message key="user.create.password.confirm.placeholder"/>" required>
        <div class="text-danger" id="rePasswordInvalidFeedback"></div>
    </div>
    <div class="d-flex align-items-center m-1">
        <button type="submit" class="btn btn-primary" id="submitBtn"><fmt:message key="user.create.submit"/></button>
        <a href="controller?command=login"><fmt:message key="user.create.signin"/></a>
    </div>
</form>
<c:import url="footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/js/signup.js"></script>
</body>
</html>
