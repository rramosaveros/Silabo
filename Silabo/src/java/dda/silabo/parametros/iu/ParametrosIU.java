/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.parametros.iu;

import dda.silabo.parametros.comunes.Parametro;
import dda.silabo.parametros.comunes.Parametros;

/**
 *
 * @author Jorge Zaruma
 */
public class ParametrosIU extends Parametros {

    public String toHTML() {
     
        String result = "";
        result += "<form>"
                + "<div class='form-group row'>"
                + "<div id='seccionContenido' class='col-xs-12'>"
                //                + "<div class='float-xs-right pr-0 unidad-nueva'>"
                //                + "<button type='button' class='btn btn-secondary active' id='addOpcion'>"
                //                + "<i class='fa fa-plus-circle'></i>"
                //                + "Nueva Opcion"
                //                + " </button>"
                //                + "</div>"
                + "<div id='ContenidoGestion'>";
        result += "<div class='row'>"
                + " <div class='col-lg-6'>"
                + "<div class='input-group'>"
                + "<input type='text' style='text-align:center;' onkeypress='return soloLetras(event);' class='form-control' readonly placeholder='Descripci&oacute;n de Par&aacute;metro' value='PAR&Aacute;METRO'/>"
                + "</div>"
                + "</div>"
                + " <div class='col-lg-6'>"
                + "<div class='input-group'  >"
                + "<input type='text' style='text-align:center;' onkeypress='return soloLetras(event);' class='form-control' readonly placeholder='Descripci&oacute;n de Opci&oacute;n' value='VALOR'/>"
                + "</div>"
                + "</div>"
                + "</div> ";
        if (this.getParametros().size() > 0) {
            for (Integer i = 0; i < this.getParametros().size(); i++) {
                Parametro parametro = this.getParametros().get(i);

                result += "<div class='row parametros'>"
                        + " <div class='col-lg-6 pdescripcion'>"
                        + "<div class='input-group'>"
                        + "<input type='text' class='form-control descripcion' onkeypress='return soloLetras(event);' id='" + parametro.getId() + "' placeholder='Descripci&oacute;n de Opci&oacute;n' value='" + parametro.getDescripcion() + "'/>"
                        + "</div>"
                        + "</div>"
                        + " <div class='col-lg-6 pvalor'>"
                        + "<div class='input-group'>"
                        + "<input type='text' class='form-control valor' onkeypress='return soloLetras(event);' placeholder='Descripci&oacute;n de Opci&oacute;n' value='" + parametro.getValor() + "'/>"
                        + "</div>"
                        + "</div>"
                        + "</div> ";
            }
        }
        result += "</div>"
                + "</div>"
                + "</div>"
                + "</form>"
                + "<!-- barra de botones -->"
                + "<div class='form-group row'>"
                + "<div class='col-xs-9'>"
                + "</div>"
                + "<div class='col-xs-3'>"
                + "<button type='button' id='btnGuardar' class='btn btn-secondary float-xs-right' onclick='guardarParametros(); return false;' data-toggle='tooltip' data-placement='top' title='Guardar cambios'>"
                + "Guardar | <i class='fa fa-fw'></i>"
                + "</button>"
                + "</div>"
                + "</div>";
        return result;
    }
}
