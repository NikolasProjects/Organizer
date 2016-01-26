<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Страница авторизации</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/signin.css" rel="stylesheet">
</head>
<body onload='document.f.j_username.focus();'>
<div class="container" style="width: 96%">
    <form class="form-signin" action="<c:url value='j_spring_security_check'/>" name="f" method="POST">
        <h3 class="form-signin-heading">Авторизируйтесь:</h3>
        <input type="text" class="form-control" name="j_username" placeholder="Логин" autofocus>
        <input type="password" class="form-control" name="j_password" placeholder="Пароль">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
    </form>
    <c:if test="${not empty loginFailed}">
        <div class="alert alert-danger"><h4>Ошибка! ${loginFailed}</h4>
        </div>
    </c:if>
    <%@include file="../jspf/footer.jspf" %>
</div>
</body>
</html>