/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.opciones.iu;

import dda.silabo.opciones.comunes.Opcion;
import dda.silabo.opciones.comunes.Opciones2;

/**
 *
 * @author Jorge Zaruma
 */
public class OpcionesIU extends Opciones2 {

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
        if (this.getOpciones().size() > 0) {
            for (Integer i = 0; i < this.getOpciones().size(); i++) {
                Opcion opcion = this.getOpciones().get(i);
                if (opcion.getEstado().equals("H")) {
                    result += "<div class='unidad'>"
                            + "<div class='input-group'>"
                            + "<input type='text' class='form-control Opciones' id='" + opcion.getIdOpcion() + "' onkeypress='return soloLetras(event);' placeholder='Descripci&oacute;n de Escenario' value='" + opcion.getDescRol() + "'/>";
//                    result += "<span class='input-group-addon deleteOpcion' data-toggle='tooltip' data-placement='bottom' title='Eliminar Opcion' id='" + opcion.getIdOpcion() + "'>"
//                            + "<i class='fa fa-minus-circle'></i>"
//                            + "</span>";
                    result += "</div></div> ";
                } else {
                    result += "<div class='unidad'>"
                            + "<div class='input-group'>"
                            + "<input type='text' class='form-control' readonly placeholder='Descripci&oacute;n de Opci&oacute;n' value='" + opcion.getDescRol() + "'/>";
                    result += "<span class='input-group-addon habilitarOpcion' data-toggle='tooltip' data-placement='bottom' title='Habilitar Opcion' id='" + opcion.getIdOpcion() + "'>"
                            + "<input type='checkbox'>"
                            + "</span>";
                    result += "</div></div> ";
                }
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
                + "<button type='button' id='btnGuardar' class='btn btn-secondary float-xs-right' onclick='guardarOpciones(); return false;' data-toggle='tooltip' data-placement='top' title='Guardar cambios'>"
                + "Guardar | <i class='fa fa-fw'></i>"
                + "</button>"
                + "</div>"
                + "</div>";
        return result;
    }
}
