<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create cocktail</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<form name="createCocktailForm" method="POST" action="controller">
    <input type="hidden" name="command" value="create_cocktail">
    <div class="form-group">
        <label for="cocktailNameFormInput">Cocktail name</label>
        <input type="text" class="form-text" id="cocktailNameFormInput" placeholder="Write cocktail name here">
    </div>
    <div class="form-group">
        <label for="cocktailDescriptionFormTextArea">Write cocktail description here</label>
        <textarea class="form-control" id="cocktailDescriptionFormTextArea" rows="3"></textarea>
    </div>
    <button type="submit" class="btn btn-primary" value="create_cocktail">Create</button>
</form>
</body>
</html>
