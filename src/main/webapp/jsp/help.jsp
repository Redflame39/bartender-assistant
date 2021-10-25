<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="help.title"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<h1>Bartender Assistant</h1>
<span>Bartender assistant is a website that allows users to create, share and discuss cocktails. There are three types of users:</span>
<ul>
    <li>Client</li>
    <li>Bartender</li>
    <li>Admin</li>
</ul>
<span>Each of them has their rights and functionality. You can find a description of each role below:</span>
<h4>Client</h4>
<span>Client is a regular user of the website. After registration and confirmation of your account you will become a Client.</span>
<span>Client is allowed to do the following actions:</span>
<ul>
    <li>Creating a cocktail recipes that needs to be approved by Admin</li>
    <li>Editing your own cocktail recipes</li>
    <li>Writing reviews on existing cocktails</li>
    <li>Editing your profile data</li>
</ul>
<h4>Bartender</h4>
<span>Bartender has the same possibilities as a User, and some extra rights, which are described below:</span>
<ul>
    <li>Creating cocktails without a need to be approved by Admin</li>
    <li>All bartenders are shown at the Bartenders page, where they are ordered according to the average rate of
        cocktails
        they created and a total number of their cocktails
    </li>
</ul>
<h4>Admin</h4>
<span>Admin has absolute rights on the website:</span>
<ul>
    <li>Deleting cocktails</li>
    <li>Approving cocktails</li>
    <li>Changing user role from Client to Bartender and back</li>
</ul>
<h2>Additional functionality</h2>
<ul>
    <li>Email account confirmation</li>
    <li>All users are able to restore password by email</li>
    <li>Two available localizations: English and Russian</li>
    <li>Searching cocktails and users by name</li>
</ul>
<h2>Technical information</h2>
<ul>
    <li>Java language level: 16</li>
    <li>Jakarta EE 8, JSP, JSTL</li>
    <li>Testing framework: JUnit 5, Mockito</li>
    <li>Utility: Apache Commons Lang 3</li>
    <li>JSON serialization: Jackson</li>
    <li>Logging: Apache Log4J2</li>
    <li>Database: Oracle MySQL</li>
    <li>Hashing: Mindrot jBCrypt</li>
</ul>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>
