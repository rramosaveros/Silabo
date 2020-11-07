/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global swal */

function RemoverUsuario(span) {
    var inputGroup = $(span).parents('.form-group').attr('id');
    ///AÑADIR CUADRO DE TEXTO 
    $("#" + inputGroup + "").remove();
}

function  editarInformacion(idCedula) {
    $("#contenidoPie").html("");
    var docente = {id: idCedula};
    var jsonUsuario = JSON.stringify(docente);
    $.ajax({
        url: "Silabo/Administrador/UsuariosGestion/UsuariosGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonUsuario: jsonUsuario, opcion: "editarInformacion"},
        success: function (result) {
            var data = JSON.parse(result);
            $("#contenidosModal").html(data.modalEditar);
            $("#modalUsuariosEditar").modal("show");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarKO();
        }
    });
}

function agregarNuevoUsuario() {
    $("#contenidoPie").html("");
    $.ajax({
        url: "Silabo/Administrador/UsuariosGestion/UsuariosGestionControlador.jsp",
        type: "GET",
        data: {opcion: 'show', vista: 'modalUsuarios'},
        success: function (result) {
            var data = JSON.parse(result);
            $("#contenidosModal").html(data.modalUsuarios);
            $("#modalUsuarios").modal("show");
        },
        error: function (jqXHR, textStatus, errorThrown) {

        }

    });
}

function verificarUsuarioEnter(e) {
    if (e.keyCode === 13) {
        verificarUsuario();
    }
}
function  verificarUsuario() {
    $("#informacionUsuario").html('   <div style="text-align: center;"> <b>Verificando </b><i class="fa fa-spinner fa-pulse"></i> </div>');
    $("#contenidoPie").html("");
    var usuario = {cedula: $("#Cedula").val()};
    var n = $("#Cedula").val().replace("-", "");
//    if (verificarCedula(n)) {
    var jsonusuario = JSON.stringify(usuario);
    $.ajax({
        url: "Silabo/Administrador/UsuariosGestion/UsuariosGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonUsuario: jsonusuario, opcion: 'verificarUsuario'},
        success: function (resultado) {
            var data = JSON.parse(resultado);
            $("#informacionUsuario").html(data.ContenidoUsuario);
            if ($("#tipoUsuario").val() === "Externo") {
                $("#accionesModal").html('<button title="Agregar" class="btn btn-secondary float-xs-right " id="btnGuardar" onclick="AgregarUsuarioRegistrado(); return false;" type="button" data-toggle="tooltip" data-placement="top">Guardar | <i class="fa fa-fw"></i></button>');
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#informacionUsuario").html("Información de Usuario no disponible");
        }
    });
//    } else {
//        alert("Cedula Incorrecta");
//        $("#Cedula").focus();
//    }
}


function AgregarUsuarioRegistrado() {
    $("#contenidoPie").html("");
    fncBtnGuardar();
    var cedula = $("#Cedula").val();
    var cont = 0;
    $('.unidad').each(function () {
        var cedula2 = $(this).find('.input-group').find('.usuariocedula').html();
        if (cedula === cedula2) {
            cont++;
            return true;
        }
    });
    if (cont === 0) {
        var nombres = $("#nombre").val();
        var apellidos = $("#apellido").val();
        var email = $("#email").val();
        var idUsuario = $("#idUsuario").val();
        var usuario = {};
        usuario.id = idUsuario;
        usuario.cedula = cedula;
        usuario.nombres = nombres;
        usuario.apellidos = apellidos;
        usuario.email = email;
        usuario.tipo = $("#tipoUsuario").val();
        var jsonUsuario = JSON.stringify(usuario);
        $.ajax({
            url: "Silabo/Administrador/UsuariosGestion/UsuariosGestionControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonUsuario: jsonUsuario, opcion: 'agregarNuevoUsuario'},
            success: function (result) {
                var data = JSON.parse(result);
                if (data.tipo === 'html') {
                    $("#contenidoDinamico").html(data.contenidoDinamico);
                    $("#titulo").html(data.titulo);
                    _fncBtnGuardarOK();
                    $("#modalCargarContenido").modal("hide");
                    $("#modalUsuarios").modal("hide");
                } else {
                    window.location = data.tipo;
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                _fncBtnGuardarOK();
                 $("#modalCargarContenido").modal("hide");
                $("#modalUsuarios").modal("hide");
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;No se pudo agregar al Usuario....");
            }
        });
    } else {
        _fncBtnGuardarOK();
         $("#modalCargarContenido").modal("hide");
        $("#modalUsuarios").modal("hide");
        $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;El usuario ya se encuentra agregado....");
    }
}

function  verificarCedula(cedula) {
    var total = 0;
    var longitud = cedula.length;
    var longcheck = longitud - 1;
    if (cedula !== "" && longitud === 10) {
        for (var i = 0; i < longcheck; i++) {
            if (i % 2 === 0) {
                var aux = cedula.charAt(i) * 2;
                if (aux > 9)
                    aux -= 9;
                total += aux;
            } else {
                total += parseInt(cedula.charAt(i)); // parseInt o concatenará en lugar de sumar
            }
        }

        total = total % 10 ? 10 - total % 10 : 0;
        var dig = parseInt(cedula.charAt(longitud - 1));
        if (dig === total) {
            return true;
        } else {
            return false;
        }
    }
}


function  guardarUsuario(idCedula) {
    $("#contenidoPie").html("");
    fncBtnGuardar();
    var cedula = $("#Cedula").val();
    var nombres = $("#nombre").val();
    var apellidos = $("#apellido").val();
    var email = $("#email").val();
    var usuario = {};
    usuario.id = idCedula;
    usuario.cedula = cedula;
    usuario.nombres = nombres;
    usuario.apellidos = apellidos;
    usuario.email = email;
    var jsonUsuario = JSON.stringify(usuario);
    $.ajax({
        url: "Silabo/Administrador/UsuariosGestion/UsuariosGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonUsuarios: jsonUsuario, opcion: 'saveUsuarios'},
        success: function (result) {
            var data = JSON.parse(result);
            if (data.tipo === 'html') {
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#titulo").html(data.titulo);
                _fncBtnGuardarOK();
                 $("#modalCargarContenido").modal("hide");
                $("#modalUsuariosEditar").modal('hide');
            } else {
                window.location = data.tipo;
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarKO();
        }
    });
}

function VisualizarRoles(objeto) {
    sessionStorage.setItem('cedula', $(objeto).parent().find('.usuarioInfo').attr('id'));
    $("#contenidoPie").html("");
    var objetoUsuario = $(objeto).parent();
    var nombreUsuario = objetoUsuario.find('.usuarioInfo').val();
    var rolesUsuario = "<h4><b>" + nombreUsuario + "</b></h4>";
    var usuario = {id: $(objeto).attr('id')};
    sessionStorage.setItem('idUsuario', usuario.id);
    $(objeto).html("<i class='fa fa-spinner fa-pulse fa-fw' aria-hidden='true'></i>");
    var jsonUsuario = JSON.stringify(usuario);
    $.ajax({
        url: "Silabo/Administrador/UsuariosGestion/UsuariosGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonUsuario: jsonUsuario, opcion: 'rolesUsuario'},
        success: function (result) {
            var data = JSON.parse(result);
            $("#contenidosModal").html(data.modalRolesUsuario);
            $('.roles').each(function () {
                $(this).selectpicker({
                    showTick: true,
                    showIcon: true,
                    iconBase: 'fa',
                    tickIcon: 'fa-check',
                    size: '7',
                    width: '90%',
                    template: "caret: '<span class=\"fa-chevron-down\"></span>'}",
                    liveSearchStyle: 'contains',
                    noneResultsText: 'No existe coincidencia {0}',
                    title: 'Seleccione Usuario'
                });
            });
            $(objeto).html("<i class='fa fa-list-alt' aria-hidden='true'> </i>");
            $("#ingresarUsuario").html(rolesUsuario);
            $("#modalRolesUsuario").modal("show");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarKO();
        }
    });
}


function  guardarRolesUsuario(idcedula) {
    $("#contenidoPie").html("");
    var usuario = {};
    usuario.roles = [];
    usuario.id = idcedula;
    $('.grupo').each(
            function () {
                var rol = {};
                ($(this).find('.input-group').find('.rolesUsuario:checked')).each(
                        function () {
                            rol.idRol = parseInt($(this).attr('id'));
                        }
                );
                if (rol.idRol !== undefined) {
                    rol.entidades = [];
                    $(this).find('.EntidadesSelect').find('#rolesEntidad option:selected').each(function () {
                        var entidad = {};
                        entidad.id = $(this).val();
                        rol.entidades.push(entidad);
                    });
                    usuario.roles.push(rol);
                }
            }
    );
    var jsonUsuario = JSON.stringify(usuario);
    $.ajax({
        url: "Silabo/Administrador/UsuariosGestion/UsuariosGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonUsuario: jsonUsuario, opcion: 'saverolesUsuario'},
        success: function (result) {
            var data = JSON.parse(result);
            if (data.saveRolesUsuario === "ingresado") {
                 $("#modalCargarContenido").modal("hide");
                $("#modalRolesUsuario").modal("hide");
            } else {
                 $("#modalCargarContenido").modal("hide");
                $("#modalRolesUsuario").modal("hide");
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;No se agreg&aocute; los roles al Usuario");
            }
        },
        error: function (err) {
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;No se agreg&aocute; los roles al Usuario");
        }
    });
}
function  cerrarRolesUsuario(cedula) {
    guardarRolesUsuario(cedula);
     $("#modalCargarContenido").modal("hide");
    $("#modalRolesUsuario").modal("hide");
}


function BusquedaUsuario() {
    $("#contenidoPie").html("");
    var searchTerm = $(".search").val();
    var searchSplit = searchTerm.replace(/ /g, "'):containsi('");
    if (validarSiNumero(searchSplit)) {
        searchSplit = searchSplit.toUpperCase();
        var cont = 0;
        $(".unidad").each(function () {
            var input = $(this).find(".input-group").find('.usuarioInfo').val();
            if (input.indexOf(searchSplit) !== -1) {
                $(this).show();
                cont++;
            } else {
                $(this).hide();
            }

        });
        if (cont === 1) {
            $('.counter').text(cont + ' usuario');
        } else {
            $('.counter').text(cont + ' usuarios');
        }
    } else {
        var cont2 = 0;
        $(".unidad").each(function () {
            var input = $(this).find(".input-group").find('.usuariocedula').html();
            if (input.indexOf(searchSplit) !== -1) {
                $(this).show();
                cont2++;
            } else {
                $(this).hide();
            }

        });
        if (cont2 === 1) {
            $('.counter').text(cont2 + ' usuario');
        } else {
            $('.counter').text(cont2 + ' usuarios');
        }
    }
}

function validarSiNumero(numero) {
    if (!/^([0-9])*$/.test(numero)) {
        return true;
    } else {
        return  false;
    }
}

function  cambioUsuarioEstado(OBJETO) {
    $("#contenidoPie").html("");
    var usuario = {};
    usuario.id = $(OBJETO).attr('id');
    usuario.estado = $(OBJETO).find('.estadoUsuario').attr('id');
    $(OBJETO).html("<i class='fa fa-spinner fa-pulse fa-fw' aria-hidden='true'></i>");
    var jsonUsuario = JSON.stringify(usuario);
    $.ajax({
        url: "Silabo/Administrador/UsuariosGestion/UsuariosGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonUsuarios: jsonUsuario, opcion: 'estadoUsuarios'},
        success: function (result) {
            var data = JSON.parse(result);
            if (data.tipo === 'html') {
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#titulo").html(data.titulo);
            } else {
                window.location = data.tipo;
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (usuario.id === 'H') {
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Usuario no eliminado o deshabilitado");
                $(OBJETO).html("<input type='checkbox' class='estadoUsuario' checked id='D'>");
            } else {
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Usuario no Habilitado");
                $(OBJETO).html("<input type='checkbox' class='estadoUsuario' checked id='H'>");
            }
        }
    });
}

function  EliminarUsuario(objeto) {
    $("#contenidoPie").html("");
    var idUsuario = $(objeto).attr('id');
    var usuario = {};
    usuario.id = idUsuario;
    var jsonUsuario = JSON.stringify(usuario);
    $(objeto).html("<i class='fa fa-spinner fa-pulse fa-fw' aria-hidden='true'></i>");
    $.ajax({
        url: "Silabo/Administrador/UsuariosGestion/UsuariosGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonUsuario: jsonUsuario, opcion: 'deleteUsuario'},
        success: function (result) {
            var data = JSON.parse(result);
            if (data.tipo === 'html') {
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#titulo").html(data.titulo);
            } else {
                window.location = data.tipo;
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarKO();
        }
    });
}

function mostrarEntidades(objeto, idEntidad) {
    if ($(objeto).attr('data-gestion') === 'rol' || $(objeto).attr('data-gestion') === undefined) {
        sessionStorage.setItem('idRol', $(objeto).attr('id'));
    }
    var cargar = "<div style='margin-left: 5%;'>"
            + "                                       <div class='input-group'>"
            + "<nav aria-label='breadcrumb'>"
            + "  <ol class='breadcrumb'>"
            + "Cargando Contenido <i class='fa fa-spinner fa-pulse fa-fw fa-1x' style='color: #00417F;' aria-hidden='true'></i>"
            + "</ol>"
            + "</nav>"
            + "</div>"
            + "</div>";
    $(objeto).parent().parent().find('#collapse' + $(objeto).attr('id')).find('.card-body').html(cargar);
    $(objeto).parent().parent().find('.collapse').collapse();
    $.ajax({
        url: "Silabo/Administrador/UsuariosGestion/UsuariosGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {opcion: 'entidadesRol', idEntidad: idEntidad},
        success: function (result) {
            var data = JSON.parse(result);
            $(objeto).parent().parent().find('#collapse' + $(objeto).attr('id')).find('.card-body').html(data.contenidoEntidades);
            $(objeto).parent().parent().find('#collapse' + $(objeto).attr('id')).find('.card-body').find("div").find(".input-group").find('#contenidoEntidades').selectpicker({
                showTick: true,
                showIcon: true,
                iconBase: 'fa',
                tickIcon: 'fa-check',
                size: '9',
                width: '100%',
                template: "caret: '<span class=\"fa-chevron-down\"></span>'}"
            });
            $(objeto).parent().parent().find('.collapse').collapse();
        },
        error: function (err) {
            _fncBtnGuardarOK();
             $("#modalCargarContenido").modal("hide");
            $("#modalRolUsuarios").modal("hide");
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;No se Agregr&oacute; Usuarios al Rol");
        }
    });
}

function mostrarSiguienteNivelEntidades(objeto, idEntidad, nivel) {
    if ($(objeto).attr('data-gestion') === 'rol' || $(objeto).attr('data-gestion') === undefined) {
        sessionStorage.setItem('idRol', $(objeto).attr('id'));
    }
    var cargar = "<div style='margin-left: 5%;'>"
            + "                                       <div class='input-group'>"
            + "<nav aria-label='breadcrumb'>"
            + "  <ol class='breadcrumb'>"
            + "Cargando Contenido <i class='fa fa-spinner fa-pulse fa-fw fa-1x' style='color: #00417F;' aria-hidden='true'></i>"
            + "</ol>"
            + "</nav>"
            + "</div>"
            + "</div>";
    $(objeto).parent().parent().find('#collapse' + $(objeto).attr('id')).find('.card-body').html(cargar);
    $(objeto).parent().parent().find('.collapse').collapse();
    $.ajax({
        url: "Silabo/Administrador/UsuariosGestion/UsuariosGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {opcion: 'entidadesrol2', codEntidad: $(objeto).attr('id'), idEntidad: idEntidad, nivel: nivel},
        success: function (result) {
            var data = JSON.parse(result);
            $(objeto).parent().parent().find('#collapse' + $(objeto).attr('id')).find('.card-body').html(data.contenidoNivel);
        },
        error: function (err) {
            _fncBtnGuardarOK();
             $("#modalCargarContenido").modal("hide");
            $("#modalRolUsuarios").modal("hide");
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;No se Agregr&oacute; Usuarios al Rol");
        }
    });
}

function mostarEntidadesFacultad(objeto, idEntidad, nivel) {
    var cedula = sessionStorage.getItem('cedula');
    var idRol = sessionStorage.getItem('idRol');
    $.ajax({
        url: "Silabo/Administrador/UsuariosGestion/UsuariosGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {opcion: 'nivelInferior', codEntidad: $(objeto).attr('id'), idEntidad: idEntidad, nivel: nivel, idRol: idRol, cedula: cedula},
        success: function (result) {
            var data = JSON.parse(result);
            $(objeto).parent().parent().find('#collapse' + $(objeto).attr('id')).find('.card-body').html(data.contenidoEntidades);
            $(objeto).parent().parent().find('#collapse' + $(objeto).attr('id')).find('.card-body').find("div").find(".input-group").find('#contenidoEntidades').selectpicker({
                showTick: true,
                showIcon: true,
                iconBase: 'fa',
                tickIcon: 'fa-check',
                size: '9',
                width: '100%',
                template: "caret: '<span class=\"fa-chevron-down\"></span>'}"
            });
            $(objeto).parent().parent().find('.collapse').collapse();
        },
        error: function (err) {
            _fncBtnGuardarOK();
             $("#modalCargarContenido").modal("hide");
            $("#modalRolUsuarios").modal("hide");
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;No se Agregr&oacute; Usuarios al Rol");
        }
    });
}


function guardarAsignacionEntidad(objeto) {
    esperaProceso('Procesando Asignación', 'El proceso puede tardar unos minutos');
    var idRol = sessionStorage.getItem('idRol');
    var idEntidad = ($(objeto).find(':selected').attr('data-idEntidad'));
    var AsignacionRol = {};
    AsignacionRol.informacion = "s";
    AsignacionRol.idRol = idRol;
    AsignacionRol.idEntidad = idEntidad;
    AsignacionRol.idUsuario = sessionStorage.getItem('idUsuario');
    var json = JSON.stringify(AsignacionRol);
    $.ajax({
        url: "Silabo/Administrador/RolesGestion/RolesGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {json: json, opcion: 'saverolUsuarios'},
        success: function (result) {
            swal.close();
            var data = JSON.parse(result);
            if (data.saveRolesUsuario === "ingresado") {
                swal("Asignacion Realizada Correctamente\n");
            } else {
                swal("La Asignacion no se logro completar\n");
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;No se Agregr&oacute; Usuarios al Rol");
            }
        },
        error: function (err) {
            errorReporte("Al realizar la Asignación");
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;No se Agregr&oacute; Usuarios al Rol");
        }
    });
}