<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="login.css"/>
    <script src="login.js"></script>

    <title>Регистрация / Авторизация</title>
</head>
<body>
<div class="container">
    <h1>Регистрация / Авторизация</h1>
    <h5>Если вы уже зарегистрированны, введите только почту и пароль.</h5>
    <h5>Если вы не зарегистрированны, заполните все поля.</h5>

    <label for="name"><b>Имя</b></label>
    <input type="text" placeholder="Введите имя" id="name" name="name" required>

    <label for="surname"><b>Фамилия</b></label>
    <input type="text" placeholder="Введите фамилию" id="surname" name="surname" required>

    <label for="e-mail"><b>Почта</b></label>
    <input type="text" placeholder="Введите почту" id="e-mail" name="e-mail" required>

    <label for="password"><b>Пароль</b></label>
    <input type="password" placeholder="Введите пароль" id="password" name="password" required>

    <div class="clearfix" >
        <button type="button" class="signupbtn" id="login">Зарегистрироваться / Авторизоваться</button>
    </div>
</div>

</body>
</html>



