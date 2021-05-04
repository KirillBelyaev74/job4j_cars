$(document).ready(function () {
    $("#addAdvertisement").on("click", function () {
        let brand = $("#brand").val();
        let model = $("#model").val();
        let price = $("#price").val();
        let description = $("#description").val();
        if (brand !== "" && model !== "" && price !== "" && description !== "") {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/job4j_cars/addAdvertisement",
                dataType: "json",
                data: {"brand": brand, "model": model, "price": price, "description": description}
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