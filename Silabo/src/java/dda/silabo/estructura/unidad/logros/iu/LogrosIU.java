package dda.silabo.estructura.unidad.logros.iu;

import dda.silabo.estructura.unidad.logros.comunes.Logro;
import dda.silabo.estructura.unidad.logros.comunes.Logros;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TOSHIBA
 */
public class LogrosIU extends Logros {
    
    public String toHTML() {
        String result = "", estadoContenido = "";
//        if (this.getSilabos().getRol().equals("Coordinador")) {
//            estadoContenido = "disabled";
//        }
        result += " <form>";
                    result += " <div class='form-group row'>"
                            + "      <div id='seccionContenido' class='col-xs-12'>"
                            + "           <div class='float-xs-right pr-0 unidad-nueva'>"
                            + "                <button type='button' class='btn btn-primary' onclick='agregarLogro(this);' >"
                            + "                     <i class='fa fa-plus-circle'></i>"
                            + "                          Logro de Aprendizaje "
                            + "                </button>"
                            + "           </div>"
                            + "      </div>"
                            + " <div class='col-xs-12 Logros' style='margin-top: 2%;'>"
                            + " <table multiple class='record_table table-hover' id='exampleSelect2' width='100%'>"
                            + " <tbody>";
        for (Integer i = 0; i < this.getLogros().size(); i++) {
            Logro logro = this.getLogros().get(i);
            result += "<div class='unidad'>"
                    + "<div class='input-group'>"
                    + "<input type='text' class='form-control Logros' onkeypress='return soloLetras(event);' onkeyup='verificarCambiosInput(this);' id='" + logro.getIdLogro() + "' placeholder='Descripci&oacute;n del Logro de Aprendizaje' value='" + logro.getDescripcion() + "'/>";
            result += "<span onclick='eliminarLogro(this);' class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='Eliminar Logro de Aprendizaje' id='" + logro.getIdLogro() + "'>"
                    + "<i class='fa fa-minus-circle'> </i>"
                    + "</span>";
            result += "</div>"
                    + "</div>"
                    + "</div>";
        }
        result += "</div>"
                + "</tbody></table>"
                + "<input type='hidden' id='idUnidad' value='" + this.getSilabos().getIdUnidad() + "'"
                + "</form>"
                + "<div id='contenidoObservaciones'></div>";
        return result;
    }
    
//    public String toHTML() {
//        String result = "", estadoContenido = "";
////        if (this.getSilabos().getRol().equals("Coordinador")) {
////            estadoContenido = "disabled";
////        }
//
//        result += "<form>"
//                + "<div class='form-group row'>"
//                + "<div id='seccionContenido' class='col-xs-12'>"
//                + "<div class='float-xs-right pr-0 unidad-nueva'>"
////                + "<button type='button' onclick='agregarLogro();' class='btn btn-primary' data-toggle='collapse' href='#collapseLogro' aria-expanded='false' aria-controls='collapseLogro'>"
//                + "<button type='button' onclick='agregarLogro(this);' class='btn btn-primary' data-toggle='collapse' aria-expanded='false' aria-controls='collapseLogro'>"
//                + "<i class='fa fa-plus-circle'></i>"
//                + "Nuevo Logro de Aprendizaje"
//                + "</button>"
//                + "</div>"
//                + "<div id='ContenidoLogro'>";
////                + "<script type=\"text/javascript\" src=\"Silabo/2EstructuraDesarrollo/ActividadesAprendizaje/ActividadesJS.js\"></script>";
////        result += "<br><br><div class='collapse unidad' id='collapseLogro'>"
////                + "<div class='card card-block input-group'>"
////                + "<input type='text' class='form-control Logros nuevo' id='0' onkeyup='verificarCambiosInput(this);' placeholder='Descripci&oacute;n del Logro de Aprendizaje' value=''/>"
//////                + "<button title='' class='btn btn-primary float-xs-right gLogros' id=''  type='button' data-original-title='Agregar Logro' data-toggle='tooltip' data-placement='top'>"
//////                                + "Agregar"
//////                                + "</button>"
////                + "</div>"
////                + "</div>";
//        for (Integer i = 0; i < this.getLogros().size(); i++) {
//            Logro logro = this.getLogros().get(i);
//            result += "<div class='unidad'>"
//                    + "<div class='input-group'>"
//                    + "<input type='text' class='form-control Logros' onkeyup='verificarCambiosInput(this);' id='" + logro.getIdLogro() + "' placeholder='Descripci&oacute;n del Logro de Aprendizaje' value='" + logro.getDescripcion() + "'/>";
//            result += "<span onclick='eliminarLogro(this);' class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='Eliminar Logro de Aprendizaje' id='" + logro.getIdLogro() + "'>"
//                    + "<i class='fa fa-minus-circle'> </i>"
//                    + "</span>";
//            result += "</div>"
//                    + "</div>";
//
//        }
//        result += "</div>"
//                + "</div>"
//                + "</div>"
//                + "<input type='hidden' id='idUnidad' value='" + this.getSilabos().getIdUnidad() + "'"
//                + "</form>"
//                + "<div id='contenidoObservaciones'></div>";
//        return result;
//    }

    public String seleccionadostoHTML() {
        String result = "";
        for (Logro l : this.getLogros()) {
            if (l.getDescripcion() != null) {
                result += l.getDescripcion();
            }
        }
        return result;
    }
}
