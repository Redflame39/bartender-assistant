<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title><fmt:message key="password.restore.title"/></title>
</head>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<form action="controller" method="post">
    <input type="hidden" name="command" value="restore_password"/>
    <div class="form-group">
        <label for="exampleInputEmail1"><fmt:message key="password.restore.email"/></label>
        <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
               placeholder="<fmt:message key="password.restore.email.placeholder"/>">
    </div>
    <span class="text-danger">${requestScope.errorMessage}</span>
    <div class="d-flex align-items-center m-1">
        <button type="submit" class="btn btn-primary"><fmt:message key="password.restore.submit"/></button>
    </div>
</form>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>
