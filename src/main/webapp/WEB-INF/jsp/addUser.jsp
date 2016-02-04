<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New User</title>
    <meta charset="utf-8">
    <meta name="description" content="New User">
    <link href="../resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="../resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body>
<%@include file="../jspf/header.jspf" %>
<div class="container">
    <form action="/add/user" method="post" enctype="multipart/form-data">

        <input id="userId" type="hidden" name="id" value="${user.id}"/>

        <div class="form-group">
            <label for="userLogin">Login</label>
            <input type="text" class="form-control" id="userLogin" name="login" value="${user.login}"/>
        </div>

        <div class="form-group">
            <label for="userPassword">Password</label>
            <input type="password" class="form-control" id="userPassword" name="password" value="${user.password}"/>
        </div>

        <div class="form-group">
            <label for="userName">Name</label>
            <input type="text" class="form-control" id="userName" name="name" value="${user.name}"/>
        </div>

        <div class="form-group">
            <label for="userRole">Role</label>
            <select class="form-control" id="userRole" name="role.id" style="width: 15%">
                <c:choose>
                    <c:when test="${not empty user.role.id}">
                        <option value="${user.role.id}" selected>${user.role.authority}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="0" selected disabled>Select role</option>
                    </c:otherwise>
                </c:choose>
                <c:forEach items="${roles}" var="role">
                    <option value="${role.id}">${role.authority}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="userEnabled">Enabled</label>
            <input type="checkbox" class="form-control" id="userEnabled" name="enabled" value="${user.enabled}"
                   style="width: 18px; height: 18px;"/>
        </div>

        <div class="form-group">
            <label for="userPhoto">Photo</label>
            <input id="userPhoto" type="file" name="photo" accept="image"/>
        </div>

        <button type="submit" class="btn btn-success">Submit</button>
        <a href="/users" class="btn btn-warning" style="margin-left: 10px;">Cancel</a>
    </form>
</div>
<%@include file="../jspf/footer.jspf" %>
<script>
    var enabledCheckBox = $("#userEnabled");
    var userLoginInput = $("#userLogin");
    var isUserEditing = $("#userId").val() !== '' ? true : false;

    $(function () {

        userLoginInput.prop("disabled", isUserEditing);

        if (enabledCheckBox.val()) {
            enabledCheckBox.prop("checked", true);
        }

        enabledCheckBox.click(function () {
            if ($(this).is(':checked')) {
                enabledCheckBox.val(true);
            } else {
                enabledCheckBox.val(false);
            }
        })

    });
</script>
</body>
</html>
