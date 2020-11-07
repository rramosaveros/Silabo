/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidad.actividades.iu;

import dda.silabo.estructura.unidad.actividades.comunes.Actividad;
import dda.silabo.estructura.unidad.actividades.comunes.Actividades;

/**
 *
 * @author TOSHIBA
 */
public class ActividadesGestionIU extends Actividades {

    public String toHTML(String tipoA) {
        String result = "";

        result += "<form>"
                + "<div class='form-group row'>"
                + "<div id='seccionContenido' class='col-xs-12'>"
                + "<div class='float-xs-right pr-0 unidad-nueva'>"
                + "<button type='button' class='btn btn-secondary active' onclick='addActividades(this);'>"
                + "<i class='fa fa-plus-circle'></i>"
                + "Nueva Actividad"
                + " </button>"
                + "</div>"
                + "<div id='ContenidoGestion'>";
        if (!this.getActividades().isEmpty()) {
            tipoA = this.getActividades().get(0).getTipo_actividad();
        }
        result += "<input type='hidden' id='tipo' value='" + tipoA + "'>";

        for (Integer i = 0; i < this.getActividades().size(); i++) {
            Actividad actividad = (Actividad) this.getActividades().get(i);
            String span = "";
            if (actividad.getTipo_actividad().equals("Aula")) {
                span = "<span class='input-group-addon' onclick='addActividadnivel2(this);' data-toggle='tooltip' id='" + actividad.getId_actividades_aprendizaje() + "' data-placement='bottom' title='Agregar Actividad'> <i class='fa fa-plus-circle'></i></span>";
            }
            String span2 = "";
            String disabled = "";
            if (!actividad.getEstado().equals("H")) {
                disabled = "disabled";
                span2 += "<span class='input-group-addon' onclick='habilitarActividad(this);' data-toggle='tooltip' data-placement='bottom' title='Habilitar Actividad' id='" + actividad.getId_actividades_aprendizaje() + "'><input type='checkbox'></span>";
            } else {
                span2 = "<span class='input-group-addon' onclick='deleteActividad(this);' data-toggle='tooltip' data-placement='bottom' title='Eliminar Actividad' id='" + actividad.getId_actividades_aprendizaje() + "'><i class='fa fa-minus-circle'></i></span>";
            }

            result += "<div class='unidad'>"
                    + "<div class='input-group'>"
                    + "<input type='text' class='form-control Actividades' " + disabled + " id='" + actividad.getId_actividades_aprendizaje() + "' onkeypress='return soloLetras(event);' placeholder='Descripci&oacute;n de la Actividad' value='" + actividad.getDescripcion() + "'/>"
                    + span
                    + span2
                    + " </div> ";
            result += "<div class='nivel2'>";
            for (Actividad nivel2 : actividad.getNivel2()) {
                String disabled2 = "";
                String spanN = "";
                if (!nivel2.getEstado().equals("H")) {
                    disabled2 = "disabled";
                    spanN += "<span class='input-group-addon' onclick='habilitarActividad(this);' data-toggle='tooltip' data-placement='bottom' title='Habilitar Actividad' id='" + nivel2.getId_actividades_aprendizaje() + "'><input type='checkbox'></span>";
                } else {
                    spanN = "<span class='input-group-addon' onclick='deleteActividad(this);' data-toggle='tooltip' data-placement='bottom' title='Eliminar Actividad' id='" + nivel2.getId_actividades_aprendizaje() + "'><i class='fa fa-minus-circle'></i></span>";
                }
                result += "<div class='input-group'  style='width: 95%; float: right;'>"
                        + "<input type='text' class='form-control Actividades2' id='" + nivel2.getId_actividades_aprendizaje() + "' value='" + nivel2.getDescripcion() + "' onkeypress='return soloLetras(event);' placeholder='Descripci&oacute;n de la actividad'/>"
                        + spanN
                        + "</div>";
            }
            result += "</div>";
            result += "</div> ";

        }
        result += "</div> </div> </div> </form>"
                + "<div class='form-group row'>"
                + "<div class='col-xs-9'>"
                + " </div>"
                + "<div class='col-xs-3'>"
                + "<button type='button' id='btnGuardar' class='btn btn-secondary float-xs-right' onclick='gActividadesGestion(this);' data-toggle='tooltip' data-placement='top' title='Guardar cambios'>"
                + "Guardar | <i class='fa fa-fw'></i>"
                + "  </button>    </div>        </div>"
                + "";

        return result;
    }
}
