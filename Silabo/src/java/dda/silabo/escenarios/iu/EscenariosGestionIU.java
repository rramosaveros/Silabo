/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.escenarios.iu;

import dda.silabo.escenarios.comunes.Escenario;
import dda.silabo.escenarios.comunes.Escenarios;

/**
 *
 * @author Jorge
 */
public class EscenariosGestionIU extends Escenarios {

    public String toHTML() {
        String result = "";
        result += "<form>"
                + "<div class='form-group row'>"
                + "<div id='seccionContenido' class='col-xs-12'>"
                + "<div class='float-xs-right pr-0 unidad-nueva'>"
                + "<button type='button' class='btn btn-secondary active' id='addEscenario'>"
                + "<i class='fa fa-plus-circle'></i>"
                + "Nuevo Escenario"
                + " </button>"
                + "</div>"
                + "<div id='ContenidoGestion'>";

        for (Integer i = 0; i < this.getEscenarios().size(); i++) {
            Escenario escenario = this.getEscenarios().get(i);
            if (escenario.getEstado().equals("H")) {
                result += "<div class='unidad'>"
                        + "<div class='input-group'>"
                        + "<input type='hidden' id='tipo' value='" + this.getEscenarios().get(0).getTipoE() + "'>"
                        + "<input type='text' class='form-control Escenarios' id='" + escenario.getIdEsc() + "' onkeypress='return soloLetras(event);' placeholder='Descripci&oacute;n de Escenario' value='" + escenario.getDescripcion() + "'/>";
                result += "<span class='input-group-addon deleteEscenario' data-toggle='tooltip' data-placement='bottom' title='Eliminar Escenario' id='" + escenario.getIdEsc() + "'>"
                        + "<i class='fa fa-minus-circle'></i>"
                        + "</span>";
                result += "</div></div> ";
            } else {
                result += "<div class='unidad'>"
                        + "<div class='input-group'>"
                        + "<input type='text' class='form-control' disabled placeholder='Descripci&oacute;n de Escenario' value='" + escenario.getDescripcion() + "'/>";
                result += "<span class='input-group-addon habilitarEscenario' data-toggle='tooltip' data-placement='bottom' title='Habilitar Escenario' id='" + escenario.getIdEsc() + "'>"
                        + "<input type='checkbox'>"
                        + "</span>";
                result += "</div></div> ";
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
                + "<button type='button' id='btnGuardar' class='btn btn-secondary float-xs-right gEscenarios' data-toggle='tooltip' data-placement='top' title='Guardar cambios'>"
                + "Guardar | <i class='fa fa-fw'></i>"
                + "</button>"
                + "</div>"
                + "</div>"
                + "<script src='Silabo/Administrador/EscenariosAprendizajeGestion/EscenariosGestionJS.js' type='text/javascript'></script>";
        return result;
    }
}
