
$(document).ready(function () {
    $("#ddaCuenta").keypress(function (e) {
        if (e.which === 13) {
            $("#ddaClave").focus();
        }
    });
    $("#ddaClave").keypress(function (e) {
        if ($("#ddaClave").val() !== "" && e.which === 13) {
            fncBtnLogin();
        }
    });
});


function fncBtnLogin() {
    var cuenta = $("#ddaCuenta").val();
    var password = $("#ddaClave").val();
    var base64 = window.btoa(password);

    $.ajax({
        url: "loginControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {ddaCuenta: cuenta, ddaClave: base64},
        success: function (msg) {
            window.location = "loginControlador.jsp";
        },
        error: function (msg) {
            var lblError = document.getElementById('lblError');
            lblError.innerHTML = 'Credenciales incorrectas';
        }
    });
}
;