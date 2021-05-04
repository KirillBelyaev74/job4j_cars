$(document).ready(function () {
    $("#login").on("click", function () {
        let name = $("#name").val();
        let surname = $("#surname").val();
        let email = $("#e-mail").val();
        let password = $("#password").val();
        if (name !== "" && surname !== "" && email !== "" && password !== "") {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/job4j_cars/login",
                dataType: "json",
                data: {"name": name, "surname": surname, "email": email, "password": password}
            }).done(function (data) {
                parse(data);
            }).fail(function (error) {
                alert("Что то пошло не так " + error.val())
            });
        } else if (name === "" && surname === "" && email !== "" && password !== "") {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/job4j_cars/login",
                dataType: "json",
                data: {"email": email, "password": password}
            }).done(function (data) {
                parse(data);
            }).fail(function (error) {
                alert("Что то пошло не так " + error.val())
            });
        } else {
            alert("Введите данные! \n " +
                "Если вы уже зарегистрированны, введите только почту и пароль. \n " +
                "Если вы не зарегистрированны, заполните все поля.")
        }
    })
});

function parse(data) {
    let answerString = JSON.stringify(data);
    let answer = JSON.parse(answerString);
    alert(answer.answer);
    window.location.href = answer.url;
}