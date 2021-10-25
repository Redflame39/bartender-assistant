<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="user.profile.edit.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<form action="controller" method="post">
    <input type="hidden" name="command" value="save_updated_profile"/>
    <input type="hidden" name="id" value="${requestScope.requested_user.userId}"/>
    <span class="text-danger" id="error">${requestScope.errorMessage}</span>
    <div class="form-group">
        <label for="usernameInputForm"><fmt:message key="user.profile.edit.username"/></label>
        <input type="text" name="username" value="${requestScope.requested_user.username}" class="form-control"
               id="usernameInputForm" aria-describedby="usernameHelp"
               placeholder="<fmt:message key="user.profile.edit.username.placeholder"/>">
        <small id="usernameHelp" class="form-text text-muted"><fmt:message key="user.profile.edit.username.info"/></small>
    </div>
    <div class="form-group">
        <label for="firstNameInputForm"><fmt:message key="user.profile.edit.firstname"/></label>
        <input type="text" name="first_name" value="${requestScope.requested_user.firstName}" class="form-control"
               id="firstNameInputForm"
               aria-describedby="firstnameHelp" placeholder="<fmt:message key="user.profile.edit.firstname.placeholder"/>">
    </div>
    <div class="form-group">
        <label for="lastNameInputForm"><fmt:message key="user.profile.edit.lastname"/></label>
        <input type="text" name="last_name" value="${requestScope.requested_user.lastName}" class="form-control"
               id="lastNameInputForm" aria-describedby="lastnameHelp"
               placeholder="<fmt:message key="user.profile.edit.lastname.placeholder"/>">
    </div>
    <button type="submit" class="btn btn-primary" id="submitBtn"><fmt:message key="user.profile.edit.submit"/></button>
</form>
<c:import url="footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/js/editing.js"></script>
</body>
</html>
