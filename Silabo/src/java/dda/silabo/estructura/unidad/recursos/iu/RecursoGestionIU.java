/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidad.recursos.iu;

import dda.silabo.estructura.unidad.recursos.comunes.Recurso;


/**
 *
 * @author Jorge Zaruma
 */
public class RecursoGestionIU {

    public String toHTML(Recurso recurso) {
        String result = "";
        result += "<div class='unidad'>"
                + "<div class='input-group'>"
                + "<input type='hidden' id='tipo' value='Recurso'>"
                + "<span class='input-group-addon'>Recurso: </span>"
                + "<input type='text' class='form-control Recursos' id='" + recurso.getIdRecurso() + "' placeholder='Descripci&oacute;n del recurso' value='" + recurso.getStrDescripcion() + "'/>"
                + "<span class='input-group-addon deleteRecurso' data-toggle='tooltip' data-placement='bottom' title='Eliminar RECURSO' id='" + recurso.getIdRecurso() + "'>"
                + "<i class='fa fa-minus-circle'></i>"
                + "</span>"
                + "</div>"
                + "</div>";
        return result;
    }
}
