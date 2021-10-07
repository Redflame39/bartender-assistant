<%--
  Created by IntelliJ IDEA.
  User: vanya
  Date: 31.08.2021
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
<form action="controller" method="post">
    <input type="hidden" name="command" value="create_account"/>
    <div class="form-group">
        <label for="usernameInputForm">Username</label>
        <input type="text" name="username" class="form-control" id="usernameInputForm" aria-describedby="usernameHelp" placeholder="Enter username">
        <small id="usernameHelp" class="form-text text-muted">5-20 symbols.</small>
    </div>
    <div class="form-group">
        <label for="emailInputForm">Email address</label>
        <input type="email" name="email" class="form-control" id="emailInputForm" aria-describedby="emailHelp" placeholder="Enter email">
        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>
    <div class="form-group">
        <label for="passwordInputForm">Password</label>
        <input type="password" name="password" class="form-control" id="passwordInputForm" placeholder="Password">
    </div>
    <div class="form-group">
        <label for="rePasswordInputForm">Confirm your password</label>
        <input type="password" name="re_password" class="form-control" id="rePasswordInputForm" placeholder="Re-password">
    </div>
    <div class="d-flex align-items-center m-1">
        <button type="submit" class="btn btn-primary">Submit</button>
        <a href="controller?command=login">Already registered?</a>
    </div>
</form>
</body>
</html>
