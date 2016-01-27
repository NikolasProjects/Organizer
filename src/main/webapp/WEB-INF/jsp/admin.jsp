<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Administrator</title>
    <meta charset="utf-8">
    <meta name="description" content="AdministratorPage">
    <link href="../resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="../jspf/header.jspf" %>
<div class="container">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Login</th>
            <th>Name</th>
            <th>Role</th>
            <th>Enabled</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr id="${user.id}">
                <td>${user.login}</td>
                <td>${user.name}</td>
                <td>${user.role.authority}</td>
                <td>${user.enabled}</td>
                <td>
                    <a href="/edit/user?id=${user.id}" class="btn btn-warning">Edit</a>
                    <button type="button" class="btn btn-danger deleteBtn">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
<%@include file="../jspf/footer.jspf" %>
<script type="text/javascript">
    $(function() {
        $(".deleteBtn").on("click", function() {
            var parentTr = $(this).closest("tr");
            var userId = parentTr.attr("id");
            deleteUser(userId).done(function(data){
                if (data == "ok") {
                    parentTr.remove();
                }
            });
        });
    })

    function deleteUser(id) {
        return $.ajax({
            url: "/delete/user",
            type: "post",
            data: {
                id : id
            }
        });
    }

</script>
</html>