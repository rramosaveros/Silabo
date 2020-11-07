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
public class RecursosIU extends Recursos {

    public String toHTML() {
        String result = "", estadoContenido = "";
//        if (this.getSilabos().getRol().equals("Coordinador")) {
//            estadoContenido = "disabled";
//        }
        result += " <div class='form-group row'>"
                //                + " <label for='exampleSelect2' class='col-xs-2 col-form-label'>Recursos:</label>"
                + "<div class='col-xs-12'>"
                + "<span onclick='seleccionarTodosItems(this);'>"
                + "                                            <div class='selectstrategies'> Seleccionar todos <input type='checkbox'> </div>"
                + "</span>"
                + " <table multiple class='record_table table-hover' id='exampleSelect2' width='100%'>";
        for (Integer i = 0; i < this.getRecursos().size(); i++) {
            Recurso recurso = (Recurso) this.getRecursos().get(i);
            if (recurso.getStrDescripcion() != null) {
                result += "<tr onclick='seleccionarItem(this);'>"
                        //                        + "<td><input type='checkbox' id='" + recurso.getIdRecurso() + "' class='Recursos'" + estadoContenido + " value='" + recurso.getStrDescripcion() + "' " + recurso.getChv_check() + ">" + recurso.getStrDescripcion() + "</td>"
                        + "<td>"
                        + "<div class='input-group'>"
                        + "                                         <input type='text' readonly class='form-control' value='" + recurso.getStrDescripcion() + "' placeholder='Recurso' aria-label='recurso' aria-describedby='basic-addon1'>"
                        + "                                        <div class='input-group-addon'>"
                        + "                                            <input type='checkbox'  id='" + recurso.getIdRecurso() + "' class='Recursos'" + estadoContenido + " value='" + recurso.getStrDescripcion() + "' " + recurso.getChv_check() + ">"
                        + "                                        </div>"
                        + "                                    </div>"
                        + "</td>"
                        + "</tr>";
            }

        }
        result += "</table>"
                + "<div id='contenidoObservaciones'></div>"
                + "<input type='hidden' id='idUnidad' value='" + this.getSilabos().getIdUnidad() + "'"
                + "</div>"
                + "</div>";

        return result;
    }

    public String seleccionadostoHTML() {
        String result = "";
        for (Integer i = 0; i < this.getRecursos().size(); i++) {
            if (this.getRecursos().get(i).getChv_check() != null) {
                result += this.getRecursos().get(i).getStrDescripcion();
            }
        }
        return result;
    }

}
