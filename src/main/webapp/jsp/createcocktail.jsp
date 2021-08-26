<%--
  Created by IntelliJ IDEA.
  User: vanya
  Date: 26.08.2021
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header></header>
<form name="createCocktailForm" method="POST" action="controller">
    <input type="hidden" name="command" value="create_cocktail">
    <div class="form-group">
        <label for="cocktailNameFormInput">Cocktail name</label>
        <input type="text" class="form-text" id="cocktailNameFormInput" placeholder="Write cocktail name here">
    </div>
    <div class="form-group">
        <label for="cocktailDescriptionFormTextArea">Example textarea</label>
        <textarea class="form-control" id="cocktailDescriptionFormTextArea" rows="3"></textarea>
    </div>
    <button type="submit" class="btn btn-primary" value="create_cocktail">Create</button>
</form>
<script src="${pageContext.request.contextPath}/js/headloader.js"></script>
</body>
</html>
