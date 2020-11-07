/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.criteriosevaluaciones.iu;

import dda.silabo.criteriosevaluaciones.comunes.ActividadEvaluar;
import dda.silabo.criteriosevaluaciones.comunes.Aporte;
import dda.silabo.criteriosevaluaciones.comunes.CriteriosEvaluaciones;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class CriteriosEvaluacionesIU extends CriteriosEvaluaciones {

    public String toHTML() {
        String result = "", estadoContenido = "";
//        if (this.getSilabos().getRol().equals("Coordinador")) {
//            estadoContenido = "disabled";
//        }
        if (!this.getActividadesevaluar().isEmpty()) {
            List<ActividadEvaluar> listactividades = this.getActividadesevaluar();
            List<Aporte> listaportes = listactividades.get(0).getAportes();
            String vigencia = this.getVigencia(); 
            result += "<table class='table-bordered table-hover' width='100%' " + estadoContenido + ">"
                    + "  <tr>"
                    + " <td colspan='2'></td>";
            for (int i = 0; i < listaportes.size(); i++) {
                result += "<td style='text-align: center;' id=" + listaportes.get(i).getId_aportes() + " class='aportes'>";
                if((("vigente").equals(vigencia)) && ((listaportes.get(i).getDescripcion()).equals("REMEDIAL"))){
                    result += "<b>RECUPERACI&Oacute;N</b>";
                }else{
                    result += "<b>" + listaportes.get(i).getDescripcion() + "</b>";
                    result += "</td>";
                }
            }
            result += "</tr>";
            for (int ae = 0; ae < listactividades.size(); ae++) {
                List<Aporte> aporte = listactividades.get(ae).getAportes();
                if (ae == 0) {
                    result += "<td colspan='2' id='Ex' data-id='"+listactividades.get(ae).getId_actividades()+"'> <b>" + listactividades.get(ae).getDescripcion() + "</b></td><td colspan='3'></td>"
                            + " <td style='text-align: center;'><input type='text' id='NP' value='12' size='8' readonly='false' style='text-align: center;'></td>"
                            + "  <td style='text-align: center;'><input type='text' id='NS' value='20' size='10' readonly='false' style='text-align: center;'></td>";
                } else {
                    result += "<tr>";
                    for (int j = 0; j < aporte.size() - 2; j++) {
                        if (j == 0) {
                            result += "<td colspan='2' id=" + listactividades.get(ae).getId_actividades() + " class='actividades'>";
                            result += "<b>" + listactividades.get(ae).getDescripcion() + "</b>";
                            result += "</td>"
                                    + "<td style='text-align: center;'>"
                                    + "<select " + estadoContenido + " style='width: 80px; text-align: center;' onchange='cambiosColumna(this);' id='a'>";
                            for (int op = 0; op < 7; op++) {
                                String select = "";
                                if (aporte.get(j).getNota().getNota() == op) {
                                    select = "selected";
                                }
                                result += "<option " + select + " style='text-align: center;'>" + op + "</option>";
                            }
                            result += "</select>"
                                    + "</td>";
                        } else {
                            result += " <td style='text-align: center;'>"
                                    + "<select " + estadoContenido + " style='width: 80px; text-align: center;' onchange='cambiosColumna" + j + "(this);' id='a" + j + "' >";
                            for (int op = 0; op < 9; op++) {
                                String select = "";
                                if (aporte.get(j).getNota().getNota() == op) {
                                    select = "selected";
                                }
                                result += "<option " + select + " style='text-align: center;'>" + op + "</option>";
                            }
                            result += "</select>"
                                    + "</td>";
                        }
                    }
                }
                result += "</tr>";
            }
            result += "<tr>"
                    + "<td colspan='2'><b>Total</b></td><td style='text-align: center;'><input type='text' id='parcial1' value='" + listaportes.get(0).getNotaTotal() + "' size='5' readonly='false' style='text-align: center;'></td>"
                    + "<td style='text-align: center;'><input  type='text' id='parcial2' value='" + listaportes.get(1).getNotaTotal() + "' size='5' readonly='false' style='text-align: center;'></td>"
                    + "<td style='text-align: center;'><input type='text' id='parcial3' value='" + listaportes.get(2).getNotaTotal() + "' size='5' readonly='false' style='text-align: center;'></td>"
                    + "<td style='text-align: center;'><input type='text' value='12' size='8' readonly='false' style='text-align: center;'></td>"
                    + "<td style='text-align: center;'><input type='text' value='20' size='10' readonly='false' style='text-align: center;'></td>"
                    + "</tr>"
                    + "</table>"
                    + "<div id='contenidoObservaciones'></div>";
        } else {
            result += "<div style='text-align:center;'>Contenido no Disponible</div>";
        }
        return result;
    }

    public String toHTMLImportacion() {
        String result = "";
        result += "<div id='accordion'>"
                + "<div class='card'>"
                + "<div class='card-header' id='headingOne'>"
                + "  <h5 class='mb-0'>"
                + "    <button class='btn btn-link' data-toggle='collapse' data-target='#collapseAc' aria-expanded='true' aria-controls='collapseAc'>"
                + "      Actividades a Evaluar"
                + "    </button>"
                + "  </h5>"
                + "</div>"
                + "<div id='collapseAc' class='collapse' aria-labelledby='headingOne' data-parent='#accordion'>"
                + "<div class='card-body'>";
        result += "<ul>";
        for (ActividadEvaluar a : this.getActividadesevaluar()) {
            result += "<li>" + a.getDescripcion() + "</li>";

        }
        result += "</ul>";
        result += " </div>"
                + "</div>"
                + " </div>"
                + "</div>";
        return result;
    }
}
