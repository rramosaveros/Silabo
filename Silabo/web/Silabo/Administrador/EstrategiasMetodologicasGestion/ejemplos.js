function addMetodologia(objeto, accion) {
    var nivel = $(objeto).data('nivel');
    var descripcion = $(objeto).parent().find('.informacion').data('descripcion');
    $(objeto).parent().parent().data('select', 'true');
    var nombre = $(objeto).parent().find('.informacion').val();
    if (descripcion === undefined || $(objeto).data('accion') === 'agregar') {
        nombre = "";
        descripcion = "";
    }
    var t = $(objeto).data('nombre');
    var id = $(objeto).parent().find('.informacion').attr('id');
    var r = '<div class="modal fade" id="modalR" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">'
            + '<div class="modal-dialog" role="document">'
            + '  <div class="modal-content">'
            + '    <div class="modal-header">'
            + '      <h5 style="text-align:center;" class="modal-title"><b>' + "Información de " + t + '</b></h5>'
            + '    </div>'
            + '    <div class="modal-body">'
            + ' <form>'
            + ' <div class="form-group">'
            + '<label for="formGroupExampleInput">Nombre</label>'
            + '<input type="text" onkeypress="return soloLetras(event);" value="' + nombre + '" class="form-control" id="nombreM"  placeholder="Nombre">'
            + '</div>'
            + ' <div class="form-group">'
            + '  <label for="formGroupExampleInput2">Descripción</label>'
            + '<textarea class="form-control" rows="5" onkeypress="return soloLetras(event);" id="descripcionM">' + descripcion + '</textarea>'
            + ' </div>'
            + '</form>'
            + '    </div>'
            + '    <div class="modal-footer">'
            + '      <button type="button" class="btn btn-primary" onclick=saveMetodologia("' + nivel + '","' + id + '");>Agregar</button>'
            + '      <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>'
            + '    </div>'
            + '  </div>'
            + '</div>'
            + '</div>';
    $("#contenidosModal").html(r);
    $("#modalR").modal('show');
}

function addMetodologiaEditar(objeto) {
    var nivel = $(objeto).parent().parent().attr('class');
    $(objeto).parent().find('.informacion').addClass('agregarInformacion');
    var descripcion = $(objeto).parent().find('.informacion').attr('data-descripcion');
    descripcion = unescape(descripcion);
//    $(objeto).parent().parent().data('select', 'true');
    var nombre = $(objeto).parent().find('.informacion').val();
    var id = $(objeto).parent().find('.informacion').attr('id');
    var r = '<div class="modal fade" id="modalR" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">'
            + '<div class="modal-dialog" role="document">'
            + '  <div class="modal-content">'
            + '    <div class="modal-header">'
            + '      <h5 style="text-align:center;" class="modal-title"><b>' + "Editar Información" + '</b></h5>'
            + '    </div>'
            + '    <div class="modal-body">'
            + ' <form>'
            + ' <div class="form-group">'
            + '<label for="formGroupExampleInput">Nombre</label>'
            + '<input type="text" value="' + nombre + '" class="form-control" id="nombreM" onkeypress="return soloLetras(event);" placeholder="Nombre">'
            + '</div>'
            + ' <div class="form-group">'
            + '  <label for="formGroupExampleInput2">Descripción</label>'
            + '<textarea class="form-control" rows="5" onkeypress="return soloLetras(event);" id="descripcionM">' + descripcion + '</textarea>'
            + ' </div>'
            + '</form>'
            + '    </div>'
            + '    <div class="modal-footer">'
            + '      <button type="button" class="btn btn-primary" onclick=saveEdicionMetodologia("' + nivel + '","' + id + '");>agregar</button>'
            + '      <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>'
            + '    </div>'
            + '  </div>'
            + '</div>'
            + '</div>';
    $("#contenidosModal").html(r);
    $("#modalR").modal('show');
}
function saveEdicionMetodologia(nivel, id) {
    var metodologia = {};
    metodologia.idpadre = 0;
    metodologia.nombre = $("#nombreM").val();
    metodologia.descripcion = escape($("#descripcionM").text());

    if (metodologia.nombre !== '') {
        $(".agregarInformacion").val(metodologia.nombre);
        $(".agregarInformacion").attr('data-descripcion', metodologia.descripcion);
        $(".agregarInformacion").parent().find('.informacion').removeClass('agregarInformacion');
//        if (nivel === 'nivel1') {
//            $('.contenidoM .nivel1').each(
//                    function () {
//
//                        if ($(this).data('select') === 'true') {
//                            $(this).find('.input-group').find('.informacion').attr('data-descripcion', metodologia.descripcion);
//                            $(this).find('.input-group').find('.informacion').val(metodologia.nombre);
//                            $(this).data('select', 'false');
//                        }
//
//                    }
//            );
//        } else if (nivel === 'nivel2') {
//
//            $('.contenidoM .nivel1 .nivel2').each(
//                    function () {
//
//                        if ($(this).data('select') === 'true') {
//                            $(this).find('.input-group').find('.informacion').attr('data-descripcion', metodologia.descripcion);
//                            $(this).find('.input-group').find('.informacion').val(metodologia.nombre);
//                            $(this).data('select', 'false');
//                        }
//
//                    }
//            );
//
//
//        } else {
//
//            $('.contenidoM .nivel1 .nivel2 .nivel3').each(
//                    function () {
//                        if ($(this).data('select') === 'true') {
//                            $(this).find('.input-group').find('.informacion').attr('data-descripcion', metodologia.descripcion);
//                            $(this).find('.input-group').find('.informacion').val(metodologia.nombre);
//                            $(this).data('select', 'false');
//                        }
//
//                    }
//            );
//        }
        $("#modalR").modal('hide');
    } else {
        alert("Escribir la informacion necesaria");
    }
}
function saveMetodologia(nivel, id) {
    var metodologia = {};
    metodologia.idpadre = 0;
    metodologia.nombre = $("#nombreM").val();
    metodologia.descripcion = escape($("#descripcionM").text());
    if (metodologia.nombre !== '') {
        var contenido = '';
        var comun = '       <input readonly type="text" value="' + metodologia.nombre + '" data-descripcion="' + metodologia.descripcion + '" class="form-control informacion" id="' + metodologia.idpadre + '" placeholder="Example input">'
                + '       <span class="input-group-addon" onclick="addMetodologiaEditar(this); return false;"><i class="fa fa-edit"></i></span>'
                + '       <span class="input-group-addon" onclick=deleteMetodologia(this);><i class="fa fa-minus-circle"></i></span>';
        if (nivel === 'contenidoM') {
            contenido = ' <div class="nivel1" data-select="false">'
                    + '   <div class="input-group">'
                    + comun
                    + '       <span class="input-group-addon"  data-nivel="nivel2" data-nombre="' + metodologia.nombre + '" data-accion="agregar"  onclick="addMetodologia(this); return false;"><i class="fa fa-plus-circle"></i></span>'
                    + '   </div>'



                    + '</div>';

            $("." + nivel).append(contenido);
        } else if (nivel === 'nivel2') {
            contenido = '   <div class="nivel2" data-select="false">'
                    + '<div class="input-group" style="width: 95%; float: right;">'
                    + comun
                    + '  <span class="input-group-addon"  data-select="false" data-nombre="' + metodologia.nombre + '" data-nivel="nivel3" data-accion="agregar" onclick="addMetodologia(this); return false;"><i class="fa fa-plus-circle"></i></span>'
                    + '</div>'
                    + '   </div>';
            $('.contenidoM .nivel1').each(
                    function () {
                        if ($(this).data('select') === 'true') {
                            $(this).data('select', 'false');
                            $(this).append(contenido);
                        }

                    }
            );


        } else {
            contenido = ' <div class="nivel3" data-select="false">'
                    + '<div class="input-group" style="width: 90%; float: right;">'
                    + comun
                    + '</div>' + '</div>';

            $('.contenidoM .nivel1 .nivel2').each(
                    function () {
                        if ($(this).data('select') === 'true') {
                            $(this).data('select', 'false');
                            $(this).append(contenido);
                        }

                    }
            );
        }
        $("#modalR").modal('hide');
    } else {
        alert("Escribir la informacion necesaria");
    }
}

function guardarMetodologias() {
    var metodologias = {};
    metodologias.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': 'Estrategias', 'rol': $("#RolUsuario").val()};
    metodologias.nivel1 = [];
    $('.contenidoM .nivel1').each(
        function () {
            var n1 = {};
            n1.chv_check = $(this).attr('data-accion');
            n1.descripcion = $(this).find('.input-group').find('.informacion').attr('data-descripcion');
            n1.nombre = $(this).find('.input-group').find('.informacion').val();
            n1.id_estrategia = $(this).find('.input-group').find('.informacion').attr('id');
            n1.nivel = 'nivel1';
            metodologias.nivel1.push(n1);
            n1.nivel2 = [];
            $(this).find('.nivel2').each(
                function () {
                    var n2 = {};
                    n2.chv_check = $(this).attr('data-accion');
                    n2.descripcion = $(this).find('.input-group').find('.informacion').attr('data-descripcion');
                    n2.nombre = $(this).find('.input-group').find('.informacion').val();
                    n2.id_estrategia = $(this).find('.input-group').find('.informacion').attr('id');
                    n2.nivel = 'nivel2';
                    n1.nivel2.push(n2);
                    n2.nivel3 = [];
                    $(this).find('.nivel3').each(
                        function () {
                            var n3 = {};
                            n3.chv_check = $(this).attr('data-accion');
                            n3.descripcion = $(this).find('.input-group').find('.informacion').attr('data-descripcion');
                            n3.nombre = $(this).find('.input-group').find('.informacion').val();
                            n3.id_estrategia = $(this).find('.input-group').find('.informacion').attr('id');
                            n3.nivel = 'nivel3';
//                                    if (n3.descripcion !== "") {
                                n2.nivel3.push(n3);
//                                    }
                        }
                    );
//                if (n2.descripcion !== "") {
//                    n1.nivel2.push(n2);
//                }
                }
            );
//          if (n1.descripcion !== "") {
//              metodologias.nivel1.push(n1);
//          }
        }
    );
    var datos = JSON.stringify(metodologias);
    $.ajax({
        url: "Silabo/Administrador/EstrategiasMetodologicasGestion/EstrategiasGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonGEstrategias: datos, opcion: 'saveEstrategias'},
        success: function (result) {
            var data = JSON.parse(result);
            $("#contenidoDinamico").html(data.contenidoDinamico);
            $("#contenidoTitulo").html(data.contenidoTitulo);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Problemas al guardar Contenido");
        }
    });
}

function deleteMetodologia(objetos) {

    if ($(objetos).parent().find('.informacion').attr('id') === "0") {
        $(objetos).parent().parent().remove();
    } else {
        var m = {};
        m.id = $(objetos).parent().find('.informacion').attr('id');
        m.nivel = $(objetos).parent().parent().attr('class');
        var json = JSON.stringify(m);
        $(objetos).parent().parent().attr('data-accion', 'eliminar');
        $(objetos).parent().parent().hide();
    }
}