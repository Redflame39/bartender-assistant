<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Create new password</title>
</head>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<form action="controller" method="post">
    <input type="hidden" name="command" value="change_password">
    <input type="hidden" name="user_id" value="${user_id}">
    <div class="form-group">
        <label for="passwordInputForm">New password</label>
        <input type="password" name="password" class="form-control" id="passwordInputForm" placeholder="Password">
    </div>
    <div class="form-group">
        <label for="rePasswordInputForm">Repeat new password</label>
        <input type="password" name="re_password" class="form-control" id="rePasswordInputForm"
               placeholder="Re-password">
    </div>
    <span class="text-danger">${errorMessage}</span>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>
