<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Restore password</title>
</head>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<form action="controller" method="post">
    <input type="hidden" name="command" value="restore_password"/>
    <div class="form-group">
        <label for="exampleInputEmail1">Email of account to restore</label>
        <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
               placeholder="Enter email">
    </div>
    <span class="text-danger">${errorMessage}</span>
    <div class="d-flex align-items-center m-1">
        <button type="submit" class="btn btn-primary">Submit</button>
    </div>
</form>
</body>
</html>