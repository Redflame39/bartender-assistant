<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create cocktail</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<c:import url="header.jsp"/>
<form action="upload" enctype="multipart/form-data" method="post">
    <div class="form-group">
        <input type="hidden" name="image_for" value="cocktail">
        <input type="hidden" name="id" value="${cocktail.id}">
        <label for="cocktailImageFileUpload">Select image fow cocktail ${cocktail.name}</label>
        <input type="file" name="cocktail_image" accept="image/png,image/jpeg" class="form-control-file" id="cocktailImageFileUpload">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>