package dda.silabo.estructura.unidad.recursos.iu;

import dda.silabo.estructura.unidad.recursos.comunes.Recurso;
import dda.silabo.estructura.unidad.recursos.comunes.Recursos;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TOSHIBA
 */
public class RecursosGestionIU extends Recursos {

    public String toHTML() {
        String result = "";
        result += "<form>"
                + "<div class='form-group row'>"
                + "<div id='seccionContenido' class='col-xs-12'>"
                + "<div class='float-xs-right pr-0 unidad-nueva'>"
                + "<button type='button' class='btn btn-secondary active' id='addRecurso'>"
                + "<i class='fa fa-plus-circle'></i>"
                + "Nuevo Recurso"
                + "</button>"
                + "</div>"
                + "<div id='ContenidoGestion'>";
        for (Integer i = 0; i < this.getRecursos().size(); i++) {
            Recurso recurso = this.getRecursos().get(i);
            if (recurso.getEstado().equals("H")) {
                result += "<div class='unidad'>"
                        + "<div class='input-group'>"
                        + "<input type='text' class='form-control Recursos' id='" + recurso.getIdRecurso() + "' onkeypress='return soloLetras(event);' placeholder='Descripci&oacute;n del recurso' value='" + recurso.getStrDescripcion() + "'/>";
                result += "<span class='input-group-addon deleteRecurso' data-toggle='tooltip' data-placement='bottom' title='Eliminar RECURSO' id='" + recurso.getIdRecurso() + "'>"
                        + "<i class='fa fa-minus-circle'> </i>"
                        + "</span>";
                result += "</div>"
                        + "</div>";
            } else {
                result += "<div class='unidad'>"
                        + "<div class='input-group'>"
                        + "<input type='hidden' id='tipo' value='Recurso'>"
                        + "<input type='text' class='form-control' disabled placeholder='Descripci&oacute;n del recurso' value='" + recurso.getStrDescripcion() + "'/>";
                result += "<span class='input-group-addon habilitarRecurso' data-toggle='tooltip' data-placement='bottom' title='Habilitar RECURSO' id='" + recurso.getIdRecurso() + "'>"
                        + "<input type='checkbox'>"
                        + "</span>";
                result += "</div>"
                        + "</div>";
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
                + "<button type='button' id='btnGuardar' class='btn btn-secondary float-xs-right gRecursos' data-toggle='tooltip' data-placement='top' title='Guardar cambios'>"
                + "Guardar | <i class='fa fa-fw'></i>"
                + "</button>"
                + "</div>"
                + "</div>"
                + "<script src='Silabo/Administrador/RecursosGestion/RecursosGestionJS.js' type='text/javascript'></script>";
        return result;
    }

}
