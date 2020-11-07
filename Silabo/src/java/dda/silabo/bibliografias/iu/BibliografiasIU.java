/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.bibliografias.iu;

import com.google.gson.Gson;
import dda.silabo.bibliografias.comunes.Bibliografia;
import dda.silabo.bibliografias.comunes.BibliografiaLibro;
import dda.silabo.bibliografias.comunes.BibliografiaSitioWeb;
import dda.silabo.bibliografias.comunes.Bibliografias;
import dda.silabo.pdf.SilaboNoVigente;

/**
 *
 * @author TOSHIBA
 */
public class BibliografiasIU extends Bibliografias {

    public String toHTML() {
        String result = "", estadoContenido = "";
        Integer contBas = 0, contComp = 39;
        result += "<form>"
                + "<div class='form-group row'>"
                + "<label id='seccionEtiqueta' for='example-text-input' class='form-control-label'></label>"
                + "<div id='seccionContenido' class='col-xs-12'>"
                + "<div class='float-xs-right pr-0 unidad-nueva'>"
                + "<button type='button' class='btn btn-primary' id='basica' data-toggle='collapse' href='#collapseBibliografiaB' aria-expanded='false' aria-controls='collapseBibliografiaB'>"
                + "<i class='fa fa-plus-circle'></i>"
                + "Agregar Bibliograf&iacute;a B&aacute;sica"
                + "</button>"
                + "</div>";

        result += "<div id='BibliografiaBasica' class=" + this.getBasica().getId_bibliografia() + ">";
        result += "<br><br><div class='collapse nb' id='collapseBibliografiaB'><div class='card card-block form-group'>"
                + "                <div class='input-group delete' style='background-color: rgb(0,65,127); color: #ffffff;'>"
                + "                  <label>Nueva Bilbiografia</label>"
                + "                <span class='deleteid' aria-hidden='true' id='0'></span>"
                + "                </div>"
                + "                 <select class='form-control' id='selectBibliografia' onchange='agregarContenidoSelect(this);'>"
                + "                   <option value='S'>Campos Bibliogr&aacute;ficos Sitio Web</option>"
                + "                 <option value='L'>Campos Bibliogr&aacute;ficos Libros</option>"
                + "                </select>"
                + "<div class='contenidoSelectBibliografia'>"
                + "<div class='' style='text-align: center;' id='placeholder'> </div>"
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
                + "                </div>"
                + "                </div>"
                //                + "<button title='' class='btn btn-primary float-xs-right gBibliografias' id=''  type='button' data-original-title='Agregar Bibliografia' data-toggle='tooltip' data-placement='top'>"
                //                + "Agregar "
                //                + "</button>"
                + "                </div></div>";
        for (BibliografiaLibro libro : this.getBasica().getLibros()) {
            result += getContenidoLibro(libro);
        }
        for (BibliografiaSitioWeb sitio : this.getBasica().getSitios()) {
            result += getContenidoSitio(sitio);
        }
        result += "</div></div>"
                + "</div>"
                + "</form>";
        result += "<form>"
                + "<div class='form-group row'>"
                + "<label id='seccionEtiqueta' for='example-text-input' class='form-control-label'></label>"
                + "<div id='seccionContenido' class='col-xs-12'>"
                + "<div class='float-xs-right pr-0 unidad-nueva'>"
                + "<button type='button' class='btn btn-primary' id='complementaria' data-toggle='collapse' href='#collapseBibliografiaC' aria-expanded='false' aria-controls='collapseBibliografiaC'>"
                + "<i class='fa fa-plus-circle'></i>"
                + "Agregar Bibliograf&iacute;a Complementaria"
                + "</button>"
                + "</div>"
                + "<div id='BibliografiaComplementaria' class=" + this.getComplementaria().getId_bibliografia() + ">";
        result += "<br><br><div class='collapse nb' id='collapseBibliografiaC'><div class='card card-block form-group'>"
                + "                <div class='input-group delete' style='background-color: rgb(0,65,127); color: #ffffff;'>"
                + "                  <label>Nueva Bilbiografia</label>"
                + "                <span class='deleteid' aria-hidden='true' id='0' ></span>"
                + "                </div>"
                + "                 <select class='form-control' id='selectBibliografia' onchange='agregarContenidoSelect(this);'>"
                + "                   <option value='S'>Campos Bibliogr&aacute;ficos Sitio Web</option>"
                + "                 <option value='L'>Campos Bibliogr&aacute;ficos Libros</option>"
                + "                </select>"
                + "<div class='contenidoSelectBibliografia'>"
                + "<div class='' style='text-align: center;' id='placeholder'> </div>"
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
                + "                </div>"
                + "                </div>"
                //                + "<button title='' class='btn btn-primary float-xs-right gBibliografias' id=''  type='button' data-original-title='Agregar Bibliografia' data-toggle='tooltip' data-placement='top'>"
                //                + "Agregar "
                //                + "</button>"
                + "             </div>   </div>";
        for (BibliografiaLibro libro : this.getComplementaria().getLibros()) {
            result += getContenidoLibro(libro);
        }
        for (BibliografiaSitioWeb sitio : this.getComplementaria().getSitios()) {
            result += getContenidoSitio(sitio);
        }
        result += "</div>"
                + "</div>"
                + "</div>"
                + "</form>"
                + "<div id='contenidoObservaciones'></div>";
        return result;
    }

    public String toHTMLCoordinador() {
        String result = "", estadoContenido = "";
        Integer contBas = 0, contComp = 39;
        result += "<form>"
                + "<div class='form-group row'>"
                + "<label id='seccionEtiqueta' for='example-text-input' class='col-xs-2 form-control-label'></label>"
                + "<div id='seccionContenido' class='col-xs-10'>"
                + "<div class='float-xs-right pr-0 unidad-nueva'>"
                + "<button type='button' class='btn btn-primary active' id='' " + estadoContenido + ">"
                //                + "<i class='fa fa-plus-circle'></i>"
                + "Bibliografia Basica"
                + "</button>"
                + "</div>";

        result += "<div id='BibliografiaBasica'>";
        if (this.getBibliografias().size() > 0) {
            for (Bibliografia bibliografia : this.getBibliografias()) {
                if (bibliografia.getTipo().equals("BASICA")) {
                    String bas = bibliografia.getDescripcion();
                    bas = bas.replace("<br>", "");
                    result += "<div class='input-group'>"
                            + "<textarea id='basica' rows='3' style='word-break: break-all;' class='form-control Basicas' id=" + bibliografia.getId_bibliografia() + " placeholder='Descripci&oacute;n de Bibliografia'>" + bas + "</textarea>";

                    result += "<span class='input-group-addon'> <a target='_blank' href=" + bibliografia.getUrl() + "><span><i class='fa fa-share' aria-hidden='true'></i></span></a></span>";
                    result += "</div>";
                    contBas++;
                }
            }
        }
        result += "</div></div>"
                + "</div>"
                + "</form>";
        result += "<input type='hidden' id='nBasica' value=" + contBas + ">";
        result += "<form>"
                + "<div class='form-group row'>"
                + "<label id='seccionEtiqueta' for='example-text-input' class='col-xs-2 form-control-label'></label>"
                + "<div id='seccionContenido' class='col-xs-10'>"
                + "<div class='float-xs-right pr-0 unidad-nueva'>"
                + "<button type='button' class='btn btn-primary active' id='" + estadoContenido + ">"
                //                + "<i class='fa fa-plus-circle'></i>"
                + "Bibliografia Complementaria"
                + "</button>"
                + "</div>"
                + "<div id='BibliografiaComplementaria'>";
        if (this.getBibliografias().size() > 0) {
            for (Bibliografia bibliografia : this.getBibliografias()) {
                if (bibliografia.getTipo().equals("COMPLEMENTARIA")) {
                    String bas = bibliografia.getDescripcion();
                    bas = bas.replace("<br>", "");
                    result += "<div class='input-group'>"
                            + "<textarea id='complementaria' style='word-break: break-all;' rows='3' class='form-control Complementarias' id=" + bibliografia.getId_bibliografia() + " placeholder='Descripci&oacute;n de Bibliografia'>" + bas + "</textarea>";

                    result += " <span class='input-group-addon'><a target='_blank' href=" + bibliografia.getUrl() + "><i class='fa fa-share' aria-hidden='true'></i></a></span>";
                    result += "</div>";
                    contComp++;
                }
            }
        }
        result += "</div>"
                + "</div>"
                + "</div>"
                + "</form>"
                + "<input type='hidden' id='nComplementaria' value=" + contComp + ">"
                + " <script type='text/javascript' src='Silabo/5Bibliografias/pruebas.js'></script>";
//                + " <script type='text/javascript' src='Silabo/5Bibliografias/BibliografiasJS.js'></script>";
        return result;
    }

    private String getContenidoLibro(BibliografiaLibro libro) {
        String result = "";
        String disabled = "";
        String span = "<span class='input-group-addon deleteid' id=" + libro.getId() + " onclick='eliminarBilbiografia(this);'><i class='fa fa-minus-circle'></i></span>"
                + "<span class='input-group-addon'  onclick=editarBibliografia(this,'" + libro.getDisabled() + "');><i class='fa fa-edit'></i></span>";
        if (libro.getDisabled().equals("pa")) {
            disabled = "disabled";
            span = "<span class='input-group-addon'  onclick=editarBibliografia(this,'" + libro.getDisabled() + "');><i class='fa fa-eye'></i></span>";
        }

        result += "<div class='nb2'><div class='card'>";

        SilaboNoVigente spdfln = new SilaboNoVigente();
        String anio = "";
        if (libro.getAnio() != 0) {
            anio = libro.getAnio().toString();
        }
        result += "<div class='input-group'>"
                + "<input type='text' class='form-control' readonly value='" + spdfln.bibliografiasAPAlibro(libro) + "'>"
                + span
                + "</div>"
                + "<div class='contenidoSelectBibliografia'  style='display: none;'> "
                + "<div class='' style='text-align: center;' id='placeholder'> </div>"
                + "<div class='input-group autor'>"
                + "    <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>Autor</span>"
                + "   <input type='text' " + disabled + " class='form-control' onkeypress='return validaLetrasGlobal(event);'  value='" + libro.getAutor() + "' onkeyup='mostrarInformacionPlaceholder(this);' id='autor' placeholder='Apellido1, Nombre1; Apellido2, Nombre2' aria-describedby='basic-addon1'>"
                + "</div>"
                + "<input type='hidden' class='tipoBibliografia' value='libro'>"
                + "<div class='input-group titulo'>"
                + "  <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>Titulo</span>"
                + "  <input type='text' " + disabled + " id='tituloL'    class='form-control'   onkeyup='mostrarInformacionPlaceholder(this);' placeholder='Fundamentos de Ingeniería' aria-describedby='basic-addon1' value='" + libro.getTitulo() + "'>"
                + "</div>"
                + "<div class='input-group anio' >"
                + "  <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>A&ntilde;o</span>"
                + "  <input type='text' " + disabled + " id='anio' class='form-control' onkeypress ='return validaNumerosGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);'  placeholder='2018'aria-describedby='basic-addon1' value='" + anio + "'>"
                + "</div>"
                + "<div class='input-group ciudad'>"
                + "  <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>Ciudad</span>"
                + "  <input type='text' " + disabled + "  id='ciudad' class='form-control' onkeypress='return validaLetrasGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);'  placeholder='Riobamba' aria-describedby='basic-addon1' value='" + libro.getCiudad() + "'>"
                + "</div>"
                + " <div class='input-group editorial'>"
                + "    <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>Editorial</span>"
                + "    <input type='text' " + disabled + "  id='editorial' class='form-control' onkeypress='return validaLetrasGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);'  placeholder='Publicaciones Adventure Works' aria-describedby='basic-addon1' value='" + libro.getEditorial() + "'>"
                + "</div>"
                + "    </div>"
                + "    </div></div>";
        return result;
    }

    private String getContenidoSitio(BibliografiaSitioWeb sitio) {
        String result = "";
        String span = "<span class='input-group-addon deleteid' id=" + sitio.getId() + " onclick='eliminarBilbiografia(this);'><i class='fa fa-minus-circle'></i></span>"
                + "<span class='input-group-addon'  onclick=editarBibliografia(this,'" + sitio.getDisabled() + "');><i class='fa fa-edit'></i></span>";
        if (sitio.getDisabled().equals("pa")) {
            span = "<span class='input-group-addon'  onclick=editarBibliografia(this,'" + sitio.getDisabled() + "');><i class='fa fa-eye'></i></span>";
        }

        result += "<div class='nb2'><div class='card'>";
        SilaboNoVigente spdfln = new SilaboNoVigente();
        String anio = "", dia = "";
        if (sitio.getAnio() != 0) {
            anio = sitio.getAnio().toString();
        }
        if (sitio.getDia() != 0) {
            dia = sitio.getDia().toString();
        }
        String disabled = "";
        result += "<div class='input-group'>"
                + "<input type='text' class='form-control' readonly value='" + spdfln.bibliografiasAPASitio(sitio) + "'>"
                + span
                + "</div>"
                + "<div class='contenidoSelectBibliografia' style='display: none;'> "
                + "<div class='' style='text-align: center;' id='placeholder'> </div>"
                + " <div class='input-group autor'>"
                + " <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>Autor</span>"
                + "  <input type='text' " + disabled + " class='form-control'  onkeypress='return validaLetrasGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);' id='autor' placeholder='Apellido1, Nombre1; Apellido2, Nombre2'  aria-describedby='basic-addon1' value='" + sitio.getAutor() + "'>"
                + " </div>"
                + "<input type='hidden' class='tipoBibliografia' value='sitio'>"
                + "    <div class='input-group sitio'>"
                + "     <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>Nombre de Sitio Web</span>"
                + "     <input type='text' " + disabled + " id='sitio'   onkeypress='return validaLetrasGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);'  class='form-control' placeholder='Acerca de nosotros: A. Datum Corporation' aria-describedby='basic-addon1' value='" + sitio.getNombreSitio() + "'>"
                + "  </div>"
                + " <div class='input-group fecha'>"
                + "         <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>A&ntilde;o</span>"
                + "        <input type='text' " + disabled + "  class='form-control' id='anio' onkeypress ='return validaNumerosGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);'  placeholder='2018'  aria-describedby='basic-addon1' value=" + anio + ">"
                + "        <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>Mes</span>"
                + "        <input type='text' " + disabled + "  id='mes' class='form-control' onkeypress='return validaLetrasGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);'  placeholder='Febrero' value=" + sitio.getMes() + ">"
                + "        <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>Dia</span>"
                + "        <input type='text' " + disabled + " id='dia' onkeypress ='return validaNumerosGlobal(event);'  onkeyup='mostrarInformacionPlaceholder(this);'  class='form-control' placeholder='12' aria-describedby='basic-addon1' value=" + dia + ">"
                + "    </div>"
                + "    <div class='input-group url'>"
                + "        <span class='input-group-addon' style='width: 15%; text-align: left;' id='basic-addon1'>Url</span>"
                + "        <input type='url' " + disabled + " class='form-control' id='url' onkeyup='mostrarInformacionPlaceholder(this);' placeholder='https://example.com' aria-describedbDiay='basic-addon1' value='" + sitio.getUrl() + "'>"
                + "    </div>"
                + "    </div>"
                + "    </div></div>";
        return result;
    }

    public String toHTMLImportacion() {
        SilaboNoVigente spdfln = new SilaboNoVigente();
        String result = "";
        result += "<div id='accordion'>";
        result += "<div class='card'>"
                + "<div class='card-header' id='headingOne'>"
                + "  <h5 class='mb-0'>"
                + "    <button class='btn btn-link' data-toggle='collapse' data-target='#collapseBB' aria-expanded='true' aria-controls='collapseBB'>"
                + "      Bibliografía Básica"
                + "    </button>"
                + "  </h5>"
                + "</div>"
                + "<div id='collapseBB' class='collapse' aria-labelledby='headingOne' data-parent='#accordion'>"
                + "<div class='card-body'>";
        result += "<ul>";
        result += "<li><b>Sitios Web</b></li>";
        result += "<ul>";
        for (BibliografiaSitioWeb sitio : this.getBasica().getSitios()) {
            result += "<li >" + spdfln.bibliografiasAPASitio(sitio) + "</li>";
        }
        result += "</ul>";
        result += "<li><b>Libros</b></li>";
        result += "<ul>";
        for (BibliografiaLibro libro : this.getBasica().getLibros()) {
            result += " <li>" + spdfln.bibliografiasAPAlibro(libro) + "</li>";
        }
        result += "</ul>";
        result += "</ul>"
                + " </div>"
                + "</div>"
                + " </div>";

        result += "<div class='card'>"
                + "<div class='card-header' id='headingOne'>"
                + "  <h5 class='mb-0'>"
                + "    <button class='btn btn-link' data-toggle='collapse' data-target='#collapseBC' aria-expanded='true' aria-controls='collapseBC'>"
                + "      Bibliografía Complementaria"
                + "    </button>"
                + "  </h5>"
                + "</div>"
                + "<div id='collapseBC' class='collapse' aria-labelledby='headingOne' data-parent='#accordion'>"
                + "<div class='card-body'>";
        result += "<ul>";
        result += "<li><b>Sitios Web</b></li>";
        result += "<ul>";
        for (BibliografiaSitioWeb sitio : this.getComplementaria().getSitios()) {
            result += "<li >" + spdfln.bibliografiasAPASitio(sitio) + "</li>";
        }
        result += "</ul>";
        result += "<li><b>Libros</b></li>";
        result += "<ul>";
        for (BibliografiaLibro libro : this.getComplementaria().getLibros()) {
            result += " <li>" + spdfln.bibliografiasAPAlibro(libro) + "</li>";
        }
        result += "</ul>";
        result += "</ul>"
                + " </div>"
                + "</div>"
                + " </div>";
        result += "</div>";
        return result;
    }

}
