<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="addAdvertisement.css"/>
    <script src="addAdvertisement.js"></script>

    <title>Добавление объявления</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-25"><label for="brand">Бренд</label></div>
        <div class="col-75"><input type="text" id="brand" name="brand" placeholder="Бренд машины"></div>
    </div>
    <div class="row">
        <div class="col-25"><label for="model">Модель</label></div>
        <div class="col-75"><input type="text" id="model" name="model" placeholder="Модель машины"></div>
    </div>
    <div class="row">
        <div class="col-25"><label for="price">Цена</label></div>
        <div class="col-75"><input type="number" id="price" name="price" placeholder="Цена машины"></div>
    </div>
    <div class="row">
        <div class="col-25"><label for="description">Описание</label></div>
        <div class="col-75"><textarea id="description" name="description" placeholder="Описание машины"
                                      style="height:200px"></textarea></div>
    </div>
    <div class="row">
        <input type="submit" id="addAdvertisement" value="Опубликовать">
    </div>
</div>
</body>
</html>