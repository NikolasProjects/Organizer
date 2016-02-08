<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Organizer Home Page</title>
    <meta charset="utf-8">
    <meta name="description" content="HomePage">
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
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${tasks}">
            <tr id="${task.id}" <c:if test="${task.outDated}"> class="outdated-task" </c:if>>
                <td>${task.name}</td>
                <td>${task.creationDate}</td>
                <td>${task.targetDate}</td>
                <td>
                    <a href="/edit/${task.id}" class="btn btn-warning">Edit</a>
                    <button type="button" class="btn btn-danger deleteBtn">Delete</button>
                    <button type="button" class="btn btn-success pull-right completeBtn">Done</button>
                </td>
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
        });

        $(".deleteBtn").on("click", function () {
            var parentTr = $(this).closest("tr");
            var taskId = parentTr.attr("id");
            deleteTask(taskId).done(function (data) {
                if (data == "ok") {
                    parentTr.remove();
                }
            });
        });

        $(".completeBtn").on("click", function () {
            var parentTr = $(this).closest("tr");
            var taskId = parentTr.attr("id");
            completeTask(taskId).done(function (data) {
                if (data == "ok") {
                    parentTr.remove();
                }
            });
        });
    })

    function deleteTask(id) {
        return $.ajax({
            url: "/delete",
            type: "post",
            data: {
                id: id
            }
        });
    }

    function completeTask(id) {
        return $.ajax({
            url: "/complete",
            type: "post",
            data: {
                id: id
            }
        });
    }

</script>
</html>
