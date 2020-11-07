/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function agregarContenidoBibliografia(id) {
    var pordefecto = '  <div class="input-group autor">'
            + ' <span class="input-group-addon" style="width: 15%; text-align: left;" id="basic-addon1">Autor</span>'
            + '  <input type="text" class="form-control" onkeypress="return validaLetras(event);" id="autor" placeholder="Nombres de Autor" aria-describedby="basic-addon1">'
            + ' </div>'
            + '<input type="hidden" class="tipoBibliografia" value="sitio">'
            + '    <div class="input-group sitio">'
            + '     <span class="input-group-addon" style="width: 15%; text-align: left;" id="basic-addon1">Nombre de Sitio Web</span>'
            + '     <input type="text" onkeypress="return validaLetras(event);"  id="sitio" class="form-control" placeholder="Titulo del Libro" aria-describedby="basic-addon1">'
            + '  </div>'
            + ' <div class="input-group fecha">'
            + '         <span class="input-group-addon" style="width: 15%; text-align: left;" id="basic-addon1">A&ntilde;o</span>'
            + '        <input type="text" class="form-control" id="anio" onkeypress="return validarNumeros(event);" placeholder="2018" aria-describedby="basic-addon1">'
            + '        <span class="input-group-addon"  style="width: 15%; text-align: left;" id="basic-addon1">Mes</span>'
            + '        <input type="text" id="mes" onkeypress="return validaLetras(event);" class="form-control" placeholder="Febrero" aria-describedby="basic-addon1">'
            + '        <span class="input-group-addon"  style="width: 15%; text-align: left;" id="basic-addon1">Dia</span>'
            + '        <input type="text" id="dia" onkeypress="return validarNumeros(event);" class="form-control" placeholder="12" aria-describedby="basic-addon1">'
            + '    </div>'
            + '    <div class="input-group url">'
            + '        <span class="input-group-addon" style="width: 15%; text-align: left;" id="basic-addon1">Url</span>'
            + '        <input type="text" class="form-control" id="url" placeholder="Url del Sitio web" aria-describedbDiay="basic-addon1">'
            + '    </div>';
    if (id === 'basica') {
        var campos = '<div class="nb"><div class="form-group">'
                + '<div class="input-group delete" style="background-color: rgb(0,65,127); color: #ffffff;">'
                + '  <label>Nueva Bilbiografia</label>'
                + '<span class="input-group-addon deleteid" aria-hidden="true" id="0" onclick="eliminarBilbiografia(this);">X</span>'
                + '</div>'
                + ' <select class="form-control" id="selectBibliografia" onchange="agregarContenidoSelect(this);">'
                + '   <option value="S">Campos Bibliogr&aacute;ficos Sitio Web</option>'
                + ' <option value="L">Campos Bibliogr&aacute;ficos Libros</option>'
                + '</select>'
                + '</div><div class="contenidoSelectBibliografia">' + pordefecto + '</div><br>';
        $("#BibliografiaBasica").prepend(campos);
    } else {
        var campos = '<div class="nb"><div class="form-group">'
                + '<div class="input-group delete" style="background-color:rgb(0,65,127); color: #ffffff;">'
                + '  <label>Nueva Bilbiografia</label>'
                + '<span class="input-group-addon deleteid" aria-hidden="true" id="0" onclick="eliminarBilbiografia(this);">X</span>'
                + '</div>'
                + ' <select class="form-control" id="selectBibliografia" onchange="agregarContenidoSelect(this);">'
                + '   <option value="S">Campos Bibliogr&aacute;ficos Sitio Web</option>'
                + ' <option value="L">Campos Bibliogr&aacute;ficos Libros</option>'
                + '</select>'
                + '</div><div class="contenidoSelectBibliografia">' + pordefecto + '</div></div><br>';
        $("#BibliografiaComplementaria").prepend(campos);
    }
}

function agregarContenidoSelect(objeto) {
    var opcion = $(objeto).val();
    var contenido = "<div  style='text-align: center;' id='placeholder'> </div>"
            + "                 <div class='input-group autor'>"
            + "             <span class='input-group-addon'  style='width: 15%; text-align: left;' id='basic-addon1'>Autor</span>"
            + "              <input type='text' class='form-control' onkeypress='return validaLetrasGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);' id='autor' placeholder='Apellido1, Nombre1; Apellido2, Nombre2' aria-describedby='basic-addon1'>"
            + "             </div>"
            + "            <input type='hidden' class='tipoBibliografia' value='sitio'>"
            + "                <div class='input-group sitio'>"
            + "                 <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>Nombre de Sitio Web</span>"
            + "                 <input type='text'   id='sitio' onkeypress='return validaLetrasGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);'  class='form-control' placeholder='Acerca de nosotros: A. Datum Corporation' aria-describedby='basic-addon1'>"
            + "              </div>"
            + "             <div class='input-group fecha'>"
            + "                     <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>A&ntilde;o</span>"
            + "                    <input type='text' class='form-control' id='anio' onkeypress ='return validaNumerosGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);'  placeholder='2018' aria-describedby='basic-addon1'>"
            + "                    <span class='input-group-addon'  style='width: 15%; text-align: left;' id='basic-addon1'>Mes</span>"
            + "                    <input type='text' id='mes'  class='form-control'  onkeypress='return validaLetrasGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);'  placeholder='Febrero' aria-describedby='basic-addon1'>"
            + "                    <span class='input-group-addon'  style='width: 15%; text-align: left;' id='basic-addon1'>Dia</span>"
            + "                    <input type='text' id='dia'  onkeypress ='return validaNumerosGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);'  class='form-control' placeholder='12' aria-describedby='basic-addon1'>"
            + "                </div>"
            + "                <div class='input-group url'>"
            + "                    <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>Url</span>"
            + "                    <input type='url' class='form-control' id='url' onkeyup='mostrarInformacionPlaceholder(this);' placeholder='https://example.com' aria-describedbDiay='basic-addon1'>"
            + "                </div>";
    if (opcion === 'L') {
        contenido = "<div  style='text-align: center;' id='placeholder'> </div>"
                + "<div class='input-group autor'>    "
                + "                                            <span class='input-group-addon' id='basic-addon1' style='width: 15%; text-align: left;'>Autor</span> "
                + "                                            <input class='form-control' id='autor' aria-describedby='basic-addon1' onkeypress='return validaLetrasGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);' type='text' placeholder='Apellido1, Nombre1; Apellido2, Nombre2'>"
                + "                                        </div>"
                + "                                        <input class='tipoBibliografia' type='hidden' value='libro'>"
                + "                                        <div class='input-group titulo'>  "
                + "                                            <span class='input-group-addon' id='basic-addon1' style='width: 15%; text-align: left;'>Titulo</span>  "
                + "                                            <input class='form-control' id='tituloL' aria-describedby='basic-addon1'  type='text' onkeyup='mostrarInformacionPlaceholder(this);' placeholder='Fundamentos de Ingeniería'>"
                + "                                        </div>"
                + "                                        <div class='input-group anio'>  "
                + "                                            <span class='input-group-addon' id='basic-addon1' style='width: 15%; text-align: left;'>Año</span>  "
                + "                                            <input class='form-control' id='anio' aria-describedby='basic-addon1' onkeypress ='return validaNumerosGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);' type='text' placeholder='2012'>"
                + "                                        </div>"
                + "                                        <div class='input-group ciudad'>  "
                + "                                            <span class='input-group-addon' id='basic-addon1' style='width: 15%; text-align: left;'>Ciudad</span>  "
                + "                                            <input class='form-control' id='ciudad' aria-describedby='basic-addon1' onkeypress='return validaLetrasGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);' type='text' placeholder='Riobamba'>"
                + "                                        </div> "
                + "                                        <div class='input-group editorial'>    "
                + "                                            <span class='input-group-addon' id='basic-addon1' style='width: 15%; text-align: left;'>Editorial</span>   "
                + "                                            <input class='form-control' id='editorial' aria-describedby='basic-addon1' onkeypress='return validaLetrasGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);' type='text' placeholder='Publicaciones Adventure Works'>"
                + "                                        </div>";
    }
    $(objeto).parent(".form-group").parent('.nb').find(".contenidoSelectBibliografia").html(contenido);
}

function eliminarBilbiografia(objeto) {
    var idValor = $(objeto).attr('id');
    if (parseInt(idValor) === 0) {
        $(objeto).parent('.input-group').parent('.form-group').parent('.nb').remove();
    } else {
        var tipo = $(objeto).parent().parent().find('.contenidoSelectBibliografia').find('.tipoBibliografia').val();
        var bibliografias = {};
        bibliografias.bibliografias = [];
        bibliografias.silabos = {'tipoSeccion': 'seccion', 'idSilabo': parseInt($("#idSilabo").val()), 'tipo': 'Bibliografias', 'idTipo': parseInt($("#idTipo").val()), 'rol': $("#RolUsuario").val()};
        var bibliografia = {};
        bibliografia.id_bibliografia = idValor;
        bibliografia.tipo = tipo;
        bibliografias.bibliografias.push(bibliografia);
        var datos = JSON.stringify(bibliografias);
        $.ajax({
            url: "Silabo/5Bibliografias/BibliografiasControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonDBibliografias: datos, opcion: "deleteBibliografia"},
            success: function (datos) {
                var data = JSON.parse(datos);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                agregarObservacionesContenido(JSON.stringify(bibliografias.silabos));

                fncInitLnkAyuda(data.lnkAyuda);
                _fncBtnGuardarOK();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                _fncBtnGuardarKO();
            }

        });
    }
}
function gBibliografias() {
    $('[data-toggle="tooltip"]').tooltip('hide');
    fncBtnGuardar();
    var bibliografias = {};
    bibliografias.silabos = {'tipoSeccion': 'seccion', 'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'tipo': 'Bibliografias', 'rol': $("#RolUsuario").val()};
    bibliografias.basica = {};
    bibliografias.basica.descripcion = "BASICA";
    var idBasica = $("#BibliografiaBasica").attr('class');
    if (idBasica === "null") {
        idBasica = 0;
    }
    bibliografias.basica.id_bibliografia = idBasica;
    bibliografias.complementaria = {};
    bibliografias.complementaria.descripcion = "COMPLEMENTARIA";
    var idCom = $("#BibliografiaComplementaria").attr('class');
    if (idCom === "null") {
        idCom = 0;
    }
    bibliografias.complementaria.id_bibliografia = idCom;
    bibliografias.basica.libros = [];
    bibliografias.basica.sitios = [];
    bibliografias.complementaria.libros = [];
    bibliografias.complementaria.sitios = [];
    $("#BibliografiaBasica .nb").each(
            function () {
                var tipo = $(this).find(".card").find('.contenidoSelectBibliografia').find('.tipoBibliografia').val();
                if (tipo === 'sitio') {
                    var sitio = obtenerObjetoSitio(this);
                    if (sitio !== null) {
                        bibliografias.basica.sitios.push(sitio);
                    }
                } else {
                    var libro = obtenerObjetoLibro(this);
                    if (libro !== null) {
                        bibliografias.basica.libros.push(libro);
                    }
                }
            });
    $("#BibliografiaComplementaria .nb").each(
            function () {
                var tipo = $(this).find(".card").find('.contenidoSelectBibliografia').find('.tipoBibliografia').val();
                if (tipo === 'sitio') {
                    var sitio = obtenerObjetoSitio(this);
                    if (sitio !== null) {
                        bibliografias.complementaria.sitios.push(sitio);
                    }
                } else {
                    var libro = obtenerObjetoLibro(this);
                    if (libro !== null) {
                        bibliografias.complementaria.libros.push(libro);
                    }
                }
            });
    if ($("#RolUsuario").val() !== 'Doc' || $("#idObservacion").val() !== undefined) {
        bibliografias.observaciones = [];
        var observacion = {};
        var observacionText = $("#txtObservaciones").val();
        observacionText = escape(observacionText);
        observacion.observacion = observacionText;
        observacion.id_observacion = $("#idObservacion").val();
        bibliografias.observaciones.push(observacion);
    }
    var jsonBibliografias = JSON.stringify(bibliografias);
    $.ajax({
        url: "Silabo/5Bibliografias/BibliografiasControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonBibliografias: jsonBibliografias, opcion: "saveBibliografias"},
        success: function (datos) {
            var data = JSON.parse(datos);
            $("#contenidoDinamico").html(data.contenidoDinamico);
            $("#contenidoDinamico").append(data.contenidoObservacion);
            $("#contenidoTitulo").html(data.contenidoTitulo);
            $("#contenidoMenuLateral").html(data.contenidoMenuLateral);
            $("#treeview").shieldTreeView();
            agregarObservacionesContenido(JSON.stringify(bibliografias.silabos));
            fncInitLnkAyuda(data.lnkAyuda);
            _fncBtnGuardarOK();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarKO();
        }

    });
}
function obtenerObjetoSitio(objeto) {
    var sitio = {};
    sitio.id = $(objeto).find('.card').find('.input-group').find('.deleteid').attr('id');
    sitio.autor = $(objeto).find('.card').find('.contenidoSelectBibliografia').find('.autor').find('#autor').val();
    sitio.nombreSitio = $(objeto).find('.card').find('.contenidoSelectBibliografia').find('.sitio').find('#sitio').val();
    sitio.anio = $(objeto).find('.card').find('.contenidoSelectBibliografia').find('.fecha').find('#anio').val();
    sitio.mes = $(objeto).find('.card').find('.contenidoSelectBibliografia').find('.fecha').find('#mes').val();
    sitio.dia = $(objeto).find('.card').find('.contenidoSelectBibliografia').find('.fecha').find('#dia').val();
    sitio.url = $(objeto).find('.card').find('.contenidoSelectBibliografia').find('.url').find('#url').val();
    if (sitio.autor !== "" && sitio.url !== "") {
        if (sitio.anio === "")
        {
            sitio.anio = 0;
        }
        if (sitio.dia === "") {
            sitio.dia = 0;
        }
        return sitio;
    } else {
        return null;
    }
}
function obtenerObjetoLibro(objeto) {
    var libro = {};
    libro.id = $(objeto).find('.card').find('.input-group').find('.deleteid').attr('id');
    libro.autor = $(objeto).find('.card').find('.contenidoSelectBibliografia').find('.autor').find('#autor').val();
    libro.titulo = $(objeto).find('.card').find('.contenidoSelectBibliografia').find('.titulo').find('#tituloL').val();
    libro.anio = $(objeto).find('.card').find('.contenidoSelectBibliografia').find('.anio').find('#anio').val();
    libro.ciudad = $(objeto).find('.card').find('.contenidoSelectBibliografia').find('.ciudad').find('#ciudad').val();
    libro.editorial = $(objeto).find('.card').find('.contenidoSelectBibliografia').find('.editorial').find('#editorial').val();
    if (libro.autor !== "" && libro.titulo !== "") {
        if (libro.anio === "") {
            libro.anio = 0;
        }
        return libro;
    } else {
        return null;
    }
}

function editarBibliografia(objeto, clase) {
    if (clase === 'si') {
        $(objeto).parent().parent().parent().addClass('nb');
    }

    $(objeto).parent().parent().find('.contenidoSelectBibliografia').css('display', 'block');
    $(objeto).html('<i class="fa fa-ban"></i>');
    $(objeto).attr('title', 'Cancelar');
    $(objeto).attr('onclick', 'cancelarEdicion(this,"' + clase + '");');
}
function cancelarEdicion(objeto, clase) {
    var icon = 'fa-edit';
    if (clase === "pa") {
        icon = 'fa-eye';
    }

    $(objeto).parent().parent().parent().removeClass('nb');
    $(objeto).parent().parent().find('.contenidoSelectBibliografia').css('display', 'none');
    $(objeto).html('<i class="fa ' + icon + '"></i>');
    $(objeto).attr('onclick', 'editarBibliografia(this,"' + clase + '");');
    $(objeto).attr('title', 'Editar');
}

