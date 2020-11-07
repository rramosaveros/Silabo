/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.observaciones.iu;

import dda.silabo.observaciones.comunes.FechaObservacion;
import dda.silabo.observaciones.comunes.Observacion;
import dda.silabo.observaciones.comunes.Observaciones;

/**
 *
 * @author Jorge Zaruma
 */
public class ObservacionesIU extends Observaciones {

    public void actualizarClase(Observaciones observaciones) {
        this.setFechas(observaciones.getFechas());
        this.setObservacion(observaciones.getObservacion());
    }

    public String toHTML(String clase, String rol, String Title, Integer idTipo) {
        String result = "", estadoObservacion = "disabled";
        if (rol.equals("Dir") || rol.equals("Cor")) {
            estadoObservacion = "";
        }
        if((this.getObservacion().getDescsec() == null) && (rol.equals("Doc"))){
            result += "<br/>";
        }else{
//        if(this.getObservacion().getDescsec() != null){
            result += "<br/>"
                + " <div class='form-group row'>"
                + "<label for='txtObservaciones' class='col-xs-2 col-form-label'>Observaciones:</label>"
                + "<div class='col-xs-10'>"
                + "<textarea class='form-control bg-warning' onkeypress='return soloLetras(event);' onkeyup='verificarCambiosInput(this);' id='txtObservaciones' rows='5' " + estadoObservacion + ">";
        if (this.getObservacion().getDescsec() != null) {
            result += this.getObservacion().getObservacion();
        }
        result += "</textarea>"
                + "</div>"
                + "</div>";
        }
        if (this.getObservacion().getId_observacion() != null) {
            result += "<input type='hidden' id='idObservacion' value='" + this.getObservacion().getId_observacion() + "' >";
        }
        
            result+= "<input type='hidden' id='idTipo' value='" + idTipo + "'>"
                + "<div class='form-group row'>"
                + "    <div class='col-xs-9'>"
                + "    </div>"
                + "    <div class='col-xs-3'>"
                + "        <button type='button' id='btnGuardar' class='btn btn-primary float-xs-right' onclick='" + clase + "(this);' data-toggle='tooltip' data-placement='top' title='" + Title + "'>"
                + "            Guardar | <i class='fa fa-save'></i>"
                + "        </button>"
                + "    </div>"
                + "</div>";
        if (!this.getFechas().isEmpty()) {
            result += "<div class='form-group row'>"
                    + "<label for='txtObservaciones' class='col-xs-2 col-form-label'>Hist&oacute;rico:</label>"
                    + " <div class='col-xs-10'>"
                    + "<dl>";
            for (FechaObservacion fechaObservacion : this.getFechas()) {
                result += "<dt>" + fechaObservacion.getFecha() + "</dt>";
                for (Observacion observacion : fechaObservacion.getObservaciones()) {
                    String Observacion = observacion.getObservacion();
//                    Observacion = Observacion.replaceAll("%20", " ").replaceAll("%0A", "<br>");
                    result += " <dd class='ml-1'>" + Observacion + "</dd>";
                }
            }
            result += "</dl>"
                    + "</div>"
                    + "</div>";
        }
        return result;
    }
}
