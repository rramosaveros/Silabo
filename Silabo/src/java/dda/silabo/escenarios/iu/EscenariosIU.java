/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.escenarios.iu;

import com.google.gson.Gson;
import dda.silabo.clases.unidos.FacultadUnidos;
import dda.silabo.escenarios.comunes.Escenario;
import dda.silabo.escenarios.comunes.Escenarios;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class EscenariosIU extends Escenarios {

    public String toHTML() {
        String result = "", estadoContenido = "", tipo = "";
//        if (this.getSilabos().getRol().equals("Coordinador")) {
//            estadoContenido = "disabled";
//        }
        Gson G = new Gson();
        result += " <div class='form-group row'>"
                //                + " <label for='exampleSelect2' class='col-xs-2 col-form-label'>Escenarios:</label>"
                + "<div class='col-xs-12'>"
                + "<span onclick='seleccionarTodosItems(this);'>"
                + "                                            <div class='selectstrategies'> Seleccionar todos <input type='checkbox'> </div>"
                + "</span>"
                + " <table class='record_table table-hover' id='exampleSelect2' width='100%'>";

        for (Integer i = 0; i < this.getEscenarios().size(); i++) {
            Escenario escenario = (Escenario) this.getEscenarios().get(i);
            EscenarioIU ecHTML = new EscenarioIU();
            if (escenario.getDescripcion() != null) {
                result += ecHTML.toHTML(escenario, estadoContenido);
                tipo = escenario.getTipoE();
            }
        }

        result += " </table>"
                + "<input type='hidden' id='idUnidad' value='" + this.getSilabos().getIdUnidad() + "'>"
                + "<input type='hidden' id='tipoE' value='" + tipo + "' >"
                + "</div>"
                + "</div>"
                  + "<div id='contenidoObservaciones'></div>";

        return result;
    }

    public String seleccionadostoHTML() {
        String result = "";
        for (Integer i = 0; i < this.getEscenarios().size(); i++) {
            if (this.getEscenarios().get(i).getCheck() != null) {
                result += this.getEscenarios().get(i).getDescripcion();
            }
        }
        return result;
    }

    public String toHTMLImportacion(List<Escenario> reales, List<Escenario> aulicos, List<Escenario> virtuales) {
        String result = "";
        result += "<div id='accordion'>"
                + "<div class='card'>"
                + "<div class='card-header' id='headingOne'>"
                + "  <h5 class='mb-0'>"
                + "    <button class='btn btn-link' data-toggle='collapse' data-target='#collapseR' aria-expanded='true' aria-controls='collapseR'>"
                + "      Escenarios de Aprendizaje Reales"
                + "    </button>"
                + "  </h5>"
                + "</div>"
                + "<div id='collapseR' class='collapse' aria-labelledby='headingOne' data-parent='#accordion'>"
                + "<div class='card-body'>";
        result += "<ul>";
        for (Escenario r : reales) {
            if (r.getCheck() != null) {
                result += "<li>" + r.getDescripcion() + "</li>";
            }

        }
        result += "</ul>";
        result += " </div>"
                + "</div>"
                + " </div>"
                + "<div class='card'>"
                + "<div class='card-header' id='headingOne'>"
                + "  <h5 class='mb-0'>"
                + "    <button class='btn btn-link' data-toggle='collapse' data-target='#collapseV' aria-expanded='true' aria-controls='collapseV'>"
                + "      Escenarios de Aprendizaje Virtuales"
                + "    </button>"
                + "  </h5>"
                + "</div>"
                + "<div id='collapseV' class='collapse' aria-labelledby='headingOne' data-parent='#accordion'>"
                + "<div class='card-body'>";
        result += "<ul>";
        for (Escenario v : virtuales) {
            if (v.getCheck() != null) {
                result += "<li>" + v.getDescripcion() + "</li>";
            }

        }
        result += "</ul>"
                + " </div>"
                + "</div>"
                + " </div>"
                + "<div class='card'>"
                + "<div class='card-header' id='headingOne'>"
                + "  <h5 class='mb-0'>"
                + "    <button class='btn btn-link' data-toggle='collapse' data-target='#collapseA' aria-expanded='true' aria-controls='collapseA'>"
                + "      Escenarios de Aprendizaje Aúlicos"
                + "    </button>"
                + "  </h5>"
                + "</div>"
                + "<div id='collapseA' class='collapse' aria-labelledby='headingOne' data-parent='#accordion'>"
                + "<div class='card-body'>";
        result += "<ul>";
        for (Escenario a : aulicos) {
            if (a.getCheck() != null) {
                result += "<li>" + a.getDescripcion() + "</li>";
            }

        }
        result += "</ul>"
                + " </div>"
                + "</div>"
                + " </div>"
                + " </div>";

        return result;
    }
}
