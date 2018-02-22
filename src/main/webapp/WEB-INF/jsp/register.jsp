<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/smiles.css">
        <title>Input</title>
    </head>
    <body>
        <main>
            <c:if test="${not empty errors}">
                <h3 class="error">Input Errors:</h3>
            </c:if>
            <form method="get" action="<c:url value="adminsubmit.do"/>">
                <h2>Enter Your Username</h2>
                <p>
                    <label for="userLogin">Username: </label>
                    <my:TextInput id="userLogin" name="userLogin"/>
                    <my:Required/>
                </p>
                <p>
                    <label for="password">Password: </label>
                    <my:TextInput id="password" name="password"/>
                    <my:Required/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="submit" value="Continue">
                </p>
            </form>
            <my:IfLoggedIn>
                <p><a href="<c:url value="change_password.do"/>">Change password</a></p>
                <p><a href="<c:url value="logout.jsp"/>">Logout</a></p>
            </my:IfLoggedIn>
            <p><my:Required/>) Required inputs.</p>
        </main>
    </body>
</html>
