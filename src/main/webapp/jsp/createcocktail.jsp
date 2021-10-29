<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="cocktail.create.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<script src="${pageContext.request.contextPath}/js/textareavalidation.js"></script>
<c:import url="/jsp/header.jsp" charEncoding="utf-8"/>
<form name="createCocktailForm" method="POST" action="controller">
    <input type="hidden" name="command" value="create_cocktail">
    <div class="form-group">
        <label for="cocktailNameFormInput"><fmt:message key="cocktail.create.name"/></label>
        <input type="text" name="cocktail_name" class="form-text" id="cocktailNameFormInput" pattern="${requestScope.cocktail_name_regexp}" maxlength="30">
    </div>
    <div class="form-group">
        <label for="cocktailInstructionsFormTextArea"><fmt:message key="cocktail.create.instructions"/></label>
        <textarea class="form-control" name="cocktail_instr" id="cocktailInstructionsFormTextArea" rows="3"
                  minlength="30" maxlength="1000" onchange="checkTextarea(this)"></textarea>
    </div>
    <button type="submit" class="btn btn-primary"><fmt:message key="cocktail.create.submit"/></button>
</form>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>
