<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bartender Assistant</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<body>
<c:import url="/jsp/header.jsp" charEncoding="utf-8"/>
<form action="controller" method="post">
    <input type="hidden" name="command" value="_test">
    <input type="text" name="mail_to">
    <input type="text" name="subject">
    <input type="text" name="message_text">
    <button type="submit" class="btn btn-light">Execute test command</button>
</form>
</body>
</html>