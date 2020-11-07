package dda.silabo.estructura.unidad.estrategias.iu;

import dda.silabo.estructura.unidad.estrategias.comunes.Estrategia;
import dda.silabo.estructura.unidad.estrategias.comunes.Estrategias;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TOSHIBA
 */
public class EstrategiasGestionIU extends Estrategias {

    public String toHTML() {
        String result = "";

        result += "<form>"
                + "<div class='form-group row'>"
                + "<div id='seccionContenido' class='col-xs-12'>"
                + "<div class='float-xs-right pr-0 unidad-nueva'>"
                + "<button type='button' class='btn btn-secondary active' id='addEstrategias'>"
                + "<i class='fa fa-plus-circle'></i>"
                + "Nueva Estrategia"
                + "</button>"
                + "</div>"
                + "<div id='ContenidoGestion'>";
        for (Integer i = 0; i < this.getEstrategias().size(); i++) {
            Estrategia estrategia = (Estrategia) this.getEstrategias().get(i);
            if (estrategia.getEstado().equals("H")) {
                result += " <div class='unidad'>"
                        + "<div class='input-group'>"
                        + "<input type='hidden' id='tipo' value='Estrategia'>"
                        + "<input type='text' class='form-control Estrategias' id='" + estrategia.getId_estrategia() + "' placeholder='Descripci&oacute;n de la Estrategia' value='" + estrategia.getDescripcion() + "'/>";
                result += "<span class='input-group-addon deleteEstrategia' data-toggle='tooltip' data-placement='bottom' title='Eliminar Estrategia' id='" + estrategia.getId_estrategia() + "'>"
                        + "<i class='fa fa-minus-circle'></i>"
                        + "</span>";
                result += "</div>"
                        + "</div>";
            } else {
                result += " <div class='unidad'>"
                        + "<div class='input-group'>"
                        + "<span class='input-group-addon'>Estrategia: </span>"
                        + "<input type='text' class='form-control' disabled placeholder='Descripci&oacute;n de la Estrategia' value='" + estrategia.getDescripcion() + "'/>";
                result += "<span class='input-group-addon habilitarEstrategia' data-toggle='tooltip' data-placement='bottom' title='Habilitar Estrategia' id='" + estrategia.getId_estrategia() + "'>"
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
                + "<div class='form-group row'>"
                + "<div class='col-xs-9'>"
                + "</div>"
                + "<div class='col-xs-3'>"
                + "<button type='button' id='btnGuardar' class='btn btn-secondary float-xs-right gEstrategias' data-toggle='tooltip' data-placement='top' title='Guardar cambios'>"
                + "Guardar | <i class='fa fa-fw'></i>"
                + "</button>"
                + "</div>"
                + "</div>"
                + "<div id='visualizador'></div>"
                + "<script src='Silabo/Administrador/EstrategiasMetodologicasGestion/EstrategiasGestionJS.js' type='text/javascript'></script>"
                + "<script src='js/validaciones.js' type='text/javascript'></script>";
        return result;
    }

}
