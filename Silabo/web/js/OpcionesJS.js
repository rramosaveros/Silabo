/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    //*****************************************************
    //ESCENARIOS
    //*****************************************************

    $("#addOpcion").click(function () {
        var divOpcion = $("<div class=\"unidad\">");
        var sopcion = $("<div class=\"input-group\">");
        var txt = $("<input type=\"text\" id=\"0\" class=\"form-control Opciones\" placeholder=\"Descripci&oacute;n de Opci&oacute;n\"/>");
        var quitarOpcion = $("<span class=\"input-group-addon\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Eliminar Opcion\"> <i class=\"fa fa-minus-circle\"></i></span>");

        sopcion.append(txt);
        sopcion.append(quitarOpcion);
        divOpcion.append(sopcion);

        $("#ContenidoGestion").prepend(divOpcion);
        quitarOpcion.click(function () {
            $(this).parent().remove();
        });
    });

    $(".deleteOpcion").click(function () {
        var rol = {'idRol': 0};
        rol.opciones = [];
        var opcion = {'idOpcion': parseInt($(this).attr('id'))};
        rol.opciones.push(opcion);

        var opc = JSON.stringify(rol);
        $.ajax({
            url: "Silabo/Administrador/OpcionesGestion/OpcionesControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonOpcion: opc, opcion: 'deleteOpcion'},
            success: function (result) {
                var data = JSON.parse(result);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                _fncBtnGuardarOK();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                _fncBtnGuardarKO();
            }
        });
    });
    $(".habilitarOpcion").click(function () {
        var rol = {'idRol': 0};
        rol.opciones = [];
        var opcion = {'idOpcion': parseInt($(this).attr('id'))};
        rol.opciones.push(opcion);
        var opc = JSON.stringify(rol);
        $.ajax({
            url: "Silabo/Administrador/OpcionesGestion/OpcionesControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonOpcion: opc, opcion: 'habilitarOpcion'},
            success: function (result) {
                var data = JSON.parse(result);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                _fncBtnGuardarOK();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                _fncBtnGuardarKO();
            }
        });
    });
});

function guardarOpciones() {
    var opciones = {};
    opciones.opciones = [];
    // escenarios.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': tipoE, 'rol': 'Administrador'};
    $('.Opciones').each(
            function () {
                var opcion = {};
                opcion.descOpcion = $(this).val();
                opcion.idOpcion = $(this).attr('id');
                opciones.opciones.push(opcion);
            }
    );

    var datos = JSON.stringify(opciones);
    $.ajax({
        url: "Silabo/Administrador/OpcionesGestion/OpcionesControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonOpciones: datos, opcion: 'saveOpciones'},
        success: function (result) {
            var data = JSON.parse(result);
            $("#contenidoDinamico").html(data.contenidoDinamico);
            $("#contenidoTitulo").html(data.contenidoTitulo);
            _fncBtnGuardarOK();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarKO();
        }
    });
}

function  guardarRolOpciones(idRol) {
    fncBtnGuardar();
    var Rol = {idRol: idRol};
    Rol.opciones = [];
    $('.Opciones:checked').each(
            function () {
                var opcion = {};
                opcion.idOpcion = $(this).attr('id');
                Rol.opciones.push(opcion);
            }
    );
    var jsonRolOpciones = JSON.stringify(Rol);
    $.ajax({
        url: "Silabo/Administrador/RolesGestion/RolesGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonRolOpciones: jsonRolOpciones, opcion: 'saveRolOpciones'},
        success: function (result) {
            var data = JSON.parse(result);
            if (data.saveRolOpciones === "ingresado") {
                _fncBtnGuardarOK();
                $("#modalRolOpciones").modal("hide");
            } else {
                _fncBtnGuardarKO();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarKO();
        }
    });
}
