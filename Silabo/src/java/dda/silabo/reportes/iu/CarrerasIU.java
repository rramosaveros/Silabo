/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.reportes.iu;

import dda.silabo.clases.unidos.AsignaturaUnidos;
import dda.silabo.clases.unidos.CarreraUnidos;
import dda.silabo.clases.unidos.DocenteUnidos;

/**
 *
 * @author Jorge Zaruma
 */
public class CarrerasIU extends CarreraUnidos {

    public String toHTMLmateriasF(String codMateria, String rolActivo) {
        String result = "";
        result += "&nbsp;&nbsp;|&nbsp;&nbsp;<select class='selectpicker' id='slcMateria'  onchange='cambioMateria();'>";
        int i = 0;
        String subtext = "";
        if (!rolActivo.equals("Cor")) {
            subtext = this.getCodCarrera();
        } else {
            subtext = this.getAsignaturas().get(0).getCodArea();
        }
        for (AsignaturaUnidos asignaturaComun : this.getAsignaturas()) {
            String selected = "";
            String icon = getIconoMateria(asignaturaComun.getEstadoSilabo(), rolActivo);
            if (i == 0 && codMateria.equals("todos")) {
                selected = "selected";
                i++;
            } else if (codMateria.equals(asignaturaComun.getCodMateria())) {
                selected = "selected";
            }
            result += "<option data-subtext='(" + subtext + ")' data-icon='" + icon + "' value='" + asignaturaComun.getCodMateria() + "' " + selected + ">" + asignaturaComun.getNombreMateria() + "</option>";
        }
        result += "</select>";
        return result;
    }

    public String getPrimeraMateria() {
        String result = "";
        result = this.getAsignaturas().get(0).getCodMateria();
        return result;
    }

    public String getMateriaActual(String codMateria) {
        String result = "";
        if (codMateria.equals("todos")) {
            result = this.getAsignaturas().get(0).getNombreMateria();
        } else {
            result = this.getAsignaturas().stream().filter(as -> as.getCodMateria().equals(codMateria)).findFirst().get().getNombreMateria();
        }
        return result;
    }

    private String getIconoMateria(String estadoSilabo, String rolActivo) {
        String result = "fa fa-fw";
        if (estadoSilabo.equals("Revision") && !rolActivo.equals("Doc")) {
            result += "fa fa-question";
        }
        if (estadoSilabo.equals("Corregir") && rolActivo.equals("Doc")) {
            result += "fa fa-exclamation-triangle";
        }
        if (estadoSilabo.equals("Aprobado")) {
            result += "fa fa-check";
        }
        return result;
    }

    public String contenidoDocentes() {
        String result = "";
        try {
            result += "<div style='margin-left: 10%;'>"
                    + "                                       <div class='input-group'>"
                    + "<nav aria-label='breadcrumb'>"
                    + "  <ol class='breadcrumb'>"
                    + "<select onchange='guardarAsignacionDocente(this);' data-show-subtext='true' data-live-search='true' class='form-control selectpicker form-control-lg' id='contenidoDocentes'>";

            int cont = 0;
            for (DocenteUnidos d : this.getDocentes()) {
                if (d.getSelected() != null) {
                    cont++;
                }
                String datainfo = d.getNombres() + "," + d.getApellidos() + "," + d.getCorreo() + "," + d.getCedula();
                result += "<option " + d.getSelected() + " data-subtext='" + d.getCedula() + "' data-idEntidad='" + this.getIdCarrera() + "' data-info='" + datainfo + "' value='" + d.getCedula() + "'>" + d.getNombres() + " " + d.getApellidos() + "</option>";
            }
            if (cont == 0) {
                result += "<option selected='true' disabled='disabled'>SELECCIONE UNA OPCIÓN</option>";
            }
            result += "  </select>"
                    + "</ol>"
                    + "</nav>"
                    + "</div>"
                    + "</div>";
        } catch (Exception e) {
        }
        return result;
    }
}
