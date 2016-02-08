<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Completed tasks</title>
    <meta charset="utf-8">
    <link href="../resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="../resources/css/organizer.css" rel="stylesheet">
    <link href="../resources/css/datatables.min.css" rel="stylesheet">
</head>
<body>
<%@include file="../jspf/header.jspf" %>
<div class="container">
    <table id="taskTable" class="table table-hover">
        <thead>
        <tr>
            <th>Name</th>
            <th>Created</th>
            <th>Target Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${tasks}">
            <tr id="${task.id}" style="color: #adadad">
                <td>${task.name}</td>
                <td>${task.creationDate}</td>
                <td>${task.targetDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
<%@include file="../jspf/footer.jspf" %>
<script type="text/javascript" src="../resources/js/datatables.min.js"></script>
<script type="text/javascript">
    $(function () {

        $("#taskTable").dataTable({
            "orderMulti": false,
            "orderClasses": false
        })

    })
</script>
</html>
