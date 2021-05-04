<%@ page import="ru.job4j.model.Advertisement" %>
<%@ page import="ru.job4j.placement.ActionAdvertisement" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="addPhoto.css"/>

    <%
        int id = Integer.parseInt(request.getParameter("id"));
        Advertisement advertisement = ActionAdvertisement.getInstance().getById(id);
        request.getSession().setAttribute("advertisement", advertisement);
    %>
    <title>Добавление фото</title>
</head>
<body>
<div class="container">
    <h3>Сохранение фото</h3>
    <form action="<%=request.getContextPath()%>/addPhoto" method="post" enctype="multipart/form-data">
        <div class="checkbox">
            <input type="file" name="file">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>
</body>
</html>