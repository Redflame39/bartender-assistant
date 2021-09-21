<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>${cocktail.name}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<c:import url="/jsp/header.jsp" charEncoding="utf-8"/>
<div class="container-fluid">
    <div class="d-flex">
        <img src="${cocktail.imageSource}" alt=""/>
        <div class="d-flex flex-column">
            <span>${cocktail.name}</span>
            <span>${cocktail.instructions}</span>
        </div>
    </div>
</div>
</body>
</html>
