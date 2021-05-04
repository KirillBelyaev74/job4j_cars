<%@ page import="ru.job4j.model.Customer" %>
<%@ page import="ru.job4j.model.Advertisement" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.model.Car" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="index.css"/>
    <link rel="stylesheet" href="addAdvertisement.css"/>

    <title>Продажа автомобилей</title>
</head>
<body>
<% Customer customer = (Customer) request.getSession().getAttribute("customer");
    if (customer == null) { %>
<div class="topnav" id="myTopnav">
    <a href="<%=request.getContextPath()%>/login.jsp" class="notLogin">Регистрация / Авторизация</a>
</div>
<% } else { %>
<div class="topnav" id="myTopnav">
    <a href="<%=request.getContextPath()%>/exit" class="active"><%=customer.getName() + " " + customer.getSurname()%>.
        Выйти</a>
</div>
<% } %>
<div class="container">
    <div class="row"><input type="button" onclick="location.href='<%=request.getContextPath()%>/addAdvertisement.jsp'"
                            value="Добавить объявление"></div>
    <br/><br/>
    <table class="table">
        <thead>
        <tr>
            <th>Бренд-Модель</th>
            <th>Цена</th>
            <th>Описание</th>
            <th>Фото</th>
            <th>Дата создания</th>
            <th></th>
        </tr>
        </thead>
        <tbody id="table">
        <% List<Advertisement> advertisements = (List<Advertisement>) request.getAttribute("advertisements");
            for (int index = 0; index != advertisements.size(); index++) {
                Advertisement advertisement = advertisements.get(index);
                Customer owner = advertisement.getCustomer();
                Car car = advertisement.getCar();
                String tr = "<tr>";
                String submit = "";
                String photo = "";
                if (advertisement.getPhoto() != null) {
                    photo = "<p><img src=\"" + request.getContextPath() + "/showPhoto?photo=" + advertisement.getPhoto()
                            + "\" width=\"200px\"/></p>";
                } else if (owner.equals(customer) && advertisement.getPhoto() == null) {
                    photo = "<input type=\"button\" onclick=\"location.href='"
                            + request.getContextPath() + "/addPhoto.jsp?id=" + advertisement.getId() + "'\" value=\"Добавить фото\">";
                }
                if (advertisement.isSold()) {
                    tr = "<tr class=\"danger\">";
                    submit = "Проданно";
                } else if (!advertisement.isSold() && owner.equals(customer)) {
                    submit = "<input type=\"button\" onclick=\"location.href='"
                            + request.getContextPath() + "/updateAdvertisement.jsp?id=" + advertisement.getId() + "'\" value=\"Изменить\">";

                }
        %>
        <%=tr%>
        <td><%=car.getBrand() + "-" + car.getModel()%></td>
        <td><%=advertisement.getPrice()%></td>
        <td><%=advertisement.getDescription()%></td>
        <td><%=photo%></td>
        <td><%=advertisement.getCreated()%></td>
        <td><%=submit%></td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>