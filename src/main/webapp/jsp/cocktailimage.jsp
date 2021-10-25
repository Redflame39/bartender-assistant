<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="cocktail.image.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<c:import url="header.jsp"/>
<form action="upload" enctype="multipart/form-data" method="post">
    <div class="form-group">
        <input type="hidden" name="file_for" value="cocktail_image">
        <input type="hidden" name="id" value="${requestScope.id}">
        <label for="cocktailImageFileUpload"><fmt:message key="cocktail.image.select"/>${requestScope.cocktail_name}</label>
        <input type="file" name="cocktail_image" accept="image/png,image/jpeg" class="form-control-file chooseFileInput" id="cocktailImageFileUpload">
    </div>
    <button disabled type="submit" class="btn btn-primary fileUploadSubmit"><fmt:message key="cocktail.image.submit"/></button>
</form>
<c:import url="footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/js/fileinput.js"></script>
</body>
</html>
