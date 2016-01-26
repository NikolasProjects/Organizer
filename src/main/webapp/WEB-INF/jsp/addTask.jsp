<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Task</title>
    <meta charset="utf-8">
    <meta name="description" content="New Task">
    <link href="../resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="../resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body>
<%@include file="../jspf/header.jspf" %>
<div class="container">
    <form action="/add" method="post">
        <input type="hidden" name="id" value="${task.id}">
        <input type="hidden" name="creationDate" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${task.creationDate}" />">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="${task.name}">
        </div>
        <div class="form-group">
            <label for="targetDate">Date</label>
            <div class="input-group date" id="datetimepicker" style="width: 200px;">
                <input type="text" class="form-control" id="targetDate" name="targetDate"
                       value="<fmt:formatDate pattern="dd/MM/yyyy" value="${task.targetDate}" />"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
        <button type="submit" class="btn btn-success">Submit</button>
    </form>
</div>
<%@include file="../jspf/footer.jspf" %>
<script>

    $(function () {
        $('#datetimepicker').datetimepicker({
            format: "DD/MM/YYYY"
        });

    });
</script>
</body>
</html>
