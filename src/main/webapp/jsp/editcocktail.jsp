<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>${cocktail.name}<fmt:message key="cocktail.edit.info.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<form name="edit_cocktail_form" method="POST" action="controller">
    <input type="hidden" name="command" value="save_updated_cocktail">
    <input type="hidden" name="cocktail_id" value="${requestScope.cocktail.id}">
    <div class="form-group">
        <label for="cocktailNameFormInput"><fmt:message key="cocktail.edit.info.name"/></label>
        <input type="text" name="cocktail_name" value="${requestScope.cocktail.name}" class="form-text"
               id="cocktailNameFormInput">
    </div>
    <div class="form-group">
        <label for="cocktailInstructionsFormTextArea"><fmt:message key="cocktail.edit.info.instructions"/></label>
        <textarea class="form-control" name="cocktail_instr" id="cocktailInstructionsFormTextArea"
                  rows="5">${requestScope.cocktail.instructions}</textarea>
    </div>
    <button type="submit" class="btn btn-primary"><fmt:message key="cocktail.edit.info.submit"/></button>
</form>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>
