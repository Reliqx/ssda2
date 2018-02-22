<%-- Alex Tetervak, Sheridan College, Ontario --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Failed</title>
        <link rel="stylesheet" href="<c:url value="/css/main.css" />" type="text/css"/>
        <link rel="stylesheet" href="<c:url value="/css/smiles.css" />" type="text/css"/>
    </head>
    <body>
        <main>
            <h1 class="surprise">Login Failed</h1>
            <p class="error">You did not login successfully.</p>
            <form action="j_security_check" method="post">
                <p>
                    <label>Username</label>
                    <input type="text" name="j_username" maxlength='15' size='15' required/><my:Required/>
                </p>
                <p>
                    <label>Password</label>
                    <input type="password" name="j_password" maxlength='15' size='15' required/><my:Required/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="submit" value="Login" class="margin_left">    
                </p>
            </form>
        </main>
    </body>
</html>
