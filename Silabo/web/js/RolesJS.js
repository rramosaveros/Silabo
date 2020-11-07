/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// otro código que necesite esperar que el DOM esté cargado totalmente
function  agregarUsuariosRol(objeto, idTipoEntidad) {
    $("#contenidoPie").html("");
    var idRol = parseInt($(objeto).attr('id'));
    sessionStorage.setItem('idRol', idRol);
    var obj = $(objeto);
    var textRol = $(obj).parent().find('.Roles').val();
    var Rol = {idRol: idRol, descRol: textRol, idTipoEntidad: idTipoEntidad};
    var jsonRol = JSON.stringify(Rol);
    obj.html("<i class='fa fa-spinner fa-pulse fa-fw' aria-hidden='true'></i>");
    $.ajax({
        url: "Silabo/Administrador/RolesGestion/RolesGestionControlador.jsp",
        type: "POST",
        data: {opcion: 'usuariosRol', jsonRol: jsonRol},
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
                    template: "caret: '<span class=\"fa-chevron-down\"></span>'}"

                });
            });
            $("#ingresarUsuario").html("<h4><b>" + textRol + "</b></h4>");
            $("#modalRolUsuarios").modal('show');
            obj.html("<i class='fa fa-users' aria-hidden='true'></i>");
        },

        error: function (error) {
            $("#contenidoDinamico").html("<div style='text-align: center; line-height: 250px;'><b>Se ha presentado un error en el Servidor....</b></div>");
        }
    });
}

function agregarNuevoRol() {
    $("#contenidoPie").html("");
    var nuevoRol = "<div class='unidad'>"
            + "<div class='input-group'>"
            + "<input type='text' id='0' class='form-control Roles' placeholder='Descripci&oacute;n de Rol'>"
            + "<span class='input-group-addon' onclick='eliminarRol(0,this); return false;' data-toggle='tooltip' data-placement='bottom' title='Eliminar Rol'> <i class='fa fa-minus-circle'></i></span>"
            + "<span class='input-group-addon agregarUsuariosRol' data-toggle='tooltip' data-placement='bottom' title='Agregar Usuarios' id='0'>"
            + "<i class='fa fa-users' aria-hidden='true'></i>"
            + "</span>"
            + "<span class='input-group-addon agregarOpcionesRol' data-toggle='tooltip' data-placement='bottom' title='Agregar Opciones' id='0'>"
            + "<i class='fa fa-bars' aria-hidden='true'></i>"
            + "</span>";
    +"</div></div> ";



    $("#ContenidoGestion").prepend(nuevoRol);

}

function  agregarOpcionesRol(objeto) {
    $("#contenidoPie").html("");
    var id = $(objeto).attr('id');
    var obj = $(objeto);
    var textRol = $(objeto).parent().find('.Roles').val();
    var Rol = {idRol: id, descRol: textRol};
    var json = JSON.stringify(Rol);
    obj.html("<i class='fa fa-spinner fa-pulse fa-fw' aria-hidden='true'></i>");
    $.ajax({
        url: "Silabo/Administrador/OpcionesGestion/OpcionesControlador.jsp",
        type: "GET",
        data: {opcion: 'rolOpciones', jsonRol: json},
        success: function (contenido) {
            var data = JSON.parse(contenido);
            if (data.tipo === 'html') {
                $("#contenidosModal").html(data.contenidoOpciones);
                $("#modalRolOpciones").modal("show");
                obj.html("<i class='fa fa-bars' aria-hidden='true'></i>");
            } else {
                window.location = data.tipo;
            }
        },

        error: function (error) {
            $("#contenidoDinamico").html("<div style='text-align: center; line-height: 250px;'><b>Se ha presentado un error en el Servidor....</b></div>");
        }
    });
}

function  eliminarRol(idRolU, obj) {
    $("#contenidoPie").html("");
    var rol = {'idRol': parseInt(idRolU)};
    if (parseInt(idRolU) === 0) {
        $(obj).parent().remove();
    } else {
        var rolId = JSON.stringify(rol);
        $(obj).html("<i class='fa fa-spinner fa-pulse fa-fw' aria-hidden='true'></i>");
        $.ajax({
            url: "Silabo/Administrador/RolesGestion/RolesGestionControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonRol: rolId, opcion: 'deleteRol'},
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
}
function habilitarRol(idRol, obj) {
    $("#contenidoPie").html("");
    var rol = {'idRol': parseInt(idRol)};
    $(obj).html("<i class='fa fa-spinner fa-pulse fa-fw' aria-hidden='true'></i>");
    var rolID = JSON.stringify(rol);
    $.ajax({
        url: "Silabo/Administrador/RolesGestion/RolesGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonRol: rolID, opcion: 'habilitarRol'},
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



function  guardarRolUsuarios(idRol) {
    $("#contenidoPie").html("");
    fncBtnGuardar();
    var rol = {};
    rol.usuarios = [];
    rol.idRol = idRol;
    $('.grupo').each(
            function () {
                var usuario = {};
                ($(this).find('.input-group').find('.rolUsuarios:checked')).each(
                        function () {
                            usuario.id = parseInt($(this).attr('id'));
                        }
                );
                if (usuario.id !== undefined) {
                    usuario.entidades = [];
                    $(this).find('.EntidadesSelect').find('#rolesEntidad option:selected').each(function () {
                        var entidad = {};
                        entidad.id = $(this).val();
                        usuario.entidades.push(entidad);
                    });

                    rol.usuarios.push(usuario);
                }
            }
    );
    var jsonRol = JSON.stringify(rol);
    $.ajax({
        url: "Silabo/Administrador/RolesGestion/RolesGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonRol: jsonRol, opcion: 'saverolUsuarios'},
        success: function (result) {
            _fncBtnGuardarOK();
            var data = JSON.parse(result);
            if (data.saveRolesUsuario === "ingresado") {
                $("#modalCargarContenido").modal("hide");

                $("#modalRolUsuarios").modal("hide");
            } else {
                $("#modalCargarContenido").modal("hide");
                $("#modalRolUsuarios").modal("hide");
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;No se Agregr&oacute; Usuarios al Rol");
            }
        },
        error: function (err) {
            _fncBtnGuardarOK();
            $("#modalCargarContenido").modal("hide");
            $("#modalRolUsuarios").modal("hide");
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;No se Agregr&oacute; Usuarios al Rol");
        }
    });
}
function mostrarDocentes(objeto, idEntidad) {

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
    var codEntidad = $(objeto).attr('id');
    if (idEntidad === 5) {
        var idCarrera = $(objeto).parent().parent().parent().parent().parent().find('div').find('div').attr('id');
        codEntidad += "." + idCarrera;

    }
    var idRol = sessionStorage.getItem('idRol');
    $.ajax({
        url: "Silabo/Administrador/RolesGestion/RolesGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {opcion: 'rolesDocentes', codEntidad: codEntidad, idEntidad: idEntidad, idRol: idRol},
        success: function (result) {
            var data = JSON.parse(result);
            $(objeto).parent().parent().find('#collapse' + $(objeto).attr('id')).find('.card-body').html(data.contenidoDocentes);
            $(objeto).parent().parent().find('#collapse' + $(objeto).attr('id')).find('.card-body').find("div").find(".input-group").find('#contenidoDocentes').selectpicker({
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
function mostrarSiguienteNivel(objeto, idEntidad, nivel) {
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
    if ($(objeto).attr('data-gestion') === 'rol' || $(objeto).attr('data-gestion') === undefined) {

        $.ajax({
            url: "Silabo/Administrador/RolesGestion/RolesGestionControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {opcion: 'nivelInferior', codEntidad: $(objeto).attr('id'), idEntidad: idEntidad, nivel: nivel},
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
    } else {
        mostarEntidadesFacultad(objeto, idEntidad, nivel);
    }
}

function guardarAsignacionDocente(objeto) {
    esperaProceso('Procesando Asignación', 'El proceso puede tardar unos minutos');
    var idRol = sessionStorage.getItem('idRol');
    var idEntidad = ($(objeto).find(':selected').attr('data-idEntidad'));
    var noombres = ($(objeto).find(':selected').attr('data-info'));
    var AsignacionRol = {};
    AsignacionRol.informacion = noombres;
    AsignacionRol.idRol = idRol;
    AsignacionRol.idEntidad = idEntidad;
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
            _fncBtnGuardarOK();
            errorReporte("Al realizar la Asignación");
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;No se Agregr&oacute; Usuarios al Rol");
        }
    });
}