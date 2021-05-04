$(document).ready(function () {
    $("#updateAdvertisement").on("click", function () {
        let price = $("#price").val();
        let description = $("#description").val();
        let sold = 0;
        if ( $("#sold").is(':checked') ) {
            sold = 1;
        }
        if (price !== "" && description !== "") {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/job4j_cars/updateAdvertisement",
                dataType: "json",
                data: {"price": price, "description": description, "sold": sold}
            }).done(function (data) {
                let answerString = JSON.stringify(data);
                let answer = JSON.parse(answerString);
                alert(answer.answer);
                window.location.href = 'http://localhost:8080/job4j_cars/index.do';
            }).fail(function (error) {
                alert("Что то пошло не так " + error.val())
            });
        } else {
            alert("Заполните все поля!")
        }
    })
})