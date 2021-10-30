<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="user.login.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<form action="controller" method="post">
    <input type="hidden" name="command" value="authenticate"/>
    <div class="form-group">
        <label for="exampleInputEmail1"><fmt:message key="user.login.email"/></label>
        <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
               placeholder="<fmt:message key="user.login.email.placeholder"/>">
        <small id="emailHelp" class="form-text text-muted"><fmt:message key="user.login.info"/></small>
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1"><fmt:message key="user.login.password"/></label>
        <input type="password" name="password" class="form-control" id="exampleInputPassword1"
               placeholder="<fmt:message key="user.login.password.placeholder"/>"
               pattern="(.){5,40}">
    </div>
    <span class="text-danger">${requestScope.errorMessage}</span>
    <div class="d-flex align-items-center m-1">
        <button type="submit" class="btn btn-primary"><fmt:message key="user.login.submit"/></button>
        <a href="controller?command=sign_up"><fmt:message key="user.login.signup"/></a>
        <a href="controller?command=restore_password_page"><fmt:message key="user.login.forgot"/></a>
    </div>
</form>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>
