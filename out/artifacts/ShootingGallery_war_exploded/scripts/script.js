$(document).ready(function() {

    $(".X").click(function() {
        $(".X").removeClass("active");
        $(this).addClass("active");
        $("#X_field").val( $(this).val() );
    });

    $(".R").click(function() {
        let html = "";
        let checked = $(".R:checked");
        checked.each( function() {
            let val = $(this).val();
            html += `<option class="radius" value="${val}">${val}</option>`
        } );

        if (checked.length > 0) paint(+checked.val());
        else paint();

        $("#radius-selector").html(html);
    });

    $("radius").click(function (e) {
        paint(+e.target.value);
    });


    $("#main-form").on("submit", function() {
        let text = $("#textfield").val().substr(0, 16);
        if (text === "" || isNaN(text) || +text <= -5 || +text >= 5) {
            alert("Введите корректное значение Y.");
            return false;
        }

        let radiusSet = false;
        $(".R:checked").each(() => radiusSet = true);
        if (!radiusSet) {
            alert("Выберите R.");
            return false;
        }
        return true;
    });

    paint(radius);
});