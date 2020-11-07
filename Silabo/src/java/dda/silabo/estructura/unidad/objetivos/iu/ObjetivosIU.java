package dda.silabo.estructura.unidad.objetivos.iu;

import dda.silabo.estructura.unidad.objetivos.comunes.Objetivo;
import dda.silabo.estructura.unidad.objetivos.comunes.Objetivos;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TOSHIBA
 */
public class ObjetivosIU extends Objetivos {

    public String toHTML3() {
        String result = "", estadoContenido = "";
//        if (this.getSilabos().getRol().equals("Coordinador")) {
//            estadoContenido = "disabled";
//        }
        result += "<form>"
                + "     <div class='form-group row'>"
                + "          <div id='seccionContenido' class='col-xs-12'>"
                + "               <div class='float-xs-right pr-0 unidad-nueva'>"
//                + "<button type='button' class='btn btn-primary' onclick='agregarObjetivo();' data-toggle='collapse' href='#collapseObjetivo' aria-expanded='false' aria-controls='collapseObjetivo'>"
                + "                    <button type='button' class='btn btn-primary' onclick='agregarObjetivo(this);' >"
                + "                       <i class='fa fa-plus-circle'></i>"
                + "                       Nuevo Objetivo"
                + "                    </button>"
                + "               </div>"
                + "          </div>"
                + "          <div class='col-xs-12 Objetivos' style='margin-top: 2%;'>"
                + "               <table multiple class='record_table table-hover' id='exampleSelect2' width='100%'>"
                + "               <tbody>";
//                + "     </div>"
//                + "<div id='ContenidoObjetivo'>"
//        result += "<br><br><div class='collapse unidad' id='collapseObjetivo'>"
//                + "<div class='card card-block input-group'>"
//                + "<input type='text' autofocus class='form-control Objetivos nuevo' onkeyup='verificarCambiosInput(this);' id='0' placeholder='Descripci&oacute;n del Objetivo' value=''/>"
//                //                + "<button title='' class='btn btn-primary float-xs-right gObjetivos' id=''  type='button' data-original-title='Agregar Objetivo' data-toggle='tooltip' data-placement='top'>"
//                //                + "Agregar"
//                //                + "</button>"
//                + "</div>"
//                + "</div>";
        for (Integer i = 0; i < this.getObjetivos().size(); i++) {
            Objetivo objetivo = this.getObjetivos().get(i);
            result += "<div class='unidad'>"
                    + "<div class='input-group'>"
                    + "<input type='text' class='form-control Objetivos' onkeypress='return soloLetras(event);' onkeyup='verificarCambiosInput(this);' id='" + objetivo.getIdObjetivo() + "' placeholder='Descripci&oacute;n del Objetivo' value='" + objetivo.getDescripcion() + "'/>";
            result += "<span onclick='eliminarObjetivo(this);' class='input-group-addon deleteRecurso' data-toggle='tooltip' data-placement='bottom' title='Eliminar Objetivo' id='" + objetivo.getIdObjetivo() + "'>"
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
//        result += "</div>"
//                + "<div id='contenidoObservaciones'></div>"
//                + "</div>"
//                + "</div>"
//                + "<input type='hidden' id='idUnidad' value='" + this.getSilabos().getIdUnidad() + "'"
//                + "</form>";

        return result;

    }
    
    
    public String toHTML() throws ScriptException, NoSuchMethodException {
        String result = "", estadoContenido = "";
//        if (this.getSilabos().getRol().equals("Coordinador")) {
//            estadoContenido = "disabled";
//        }
        result += "<form>"
                + "     <div class='form-group row'>"
                + "          <div id='seccionContenido' class='col-xs-12'>"
                + "               <div class='float-xs-right pr-0 unidad-nueva'>"
//                + "<button type='button' class='btn btn-primary' onclick='agregarObjetivo();' data-toggle='collapse' href='#collapseObjetivo' aria-expanded='false' aria-controls='collapseObjetivo'>"
                + "                    <button type='button' class='btn btn-primary' onclick='agregarObjetivo(this);' >"
                + "                       <i class='fa fa-plus-circle'></i>"
                + "                       Nuevo Objetivo"
                + "                    </button>"
                + "               </div>"
                + "          </div>"
                + "          <div class='col-xs-12 Objetivos' style='margin-top: 2%;'>"
                + "               <table multiple class='record_table table-hover' id='exampleSelect2' width='100%'>"
                + "               <tbody>";
//                + "     </div>"
//                + "<div id='ContenidoObjetivo'>"
//        result += "<br><br><div class='collapse unidad' id='collapseObjetivo'>"
//                + "<div class='card card-block input-group'>"
//                + "<input type='text' autofocus class='form-control Objetivos nuevo' onkeyup='verificarCambiosInput(this);' id='0' placeholder='Descripci&oacute;n del Objetivo' value=''/>"
//                //                + "<button title='' class='btn btn-primary float-xs-right gObjetivos' id=''  type='button' data-original-title='Agregar Objetivo' data-toggle='tooltip' data-placement='top'>"
//                //                + "Agregar"
//                //                + "</button>"
//                + "</div>"
//                + "</div>";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        String script1 = (String) "function unescapeTexto(texto) {return unescape(texto);}";
        engine.eval(script1);
        
        Invocable invocar = (Invocable) engine;
        
        for (Integer i = 0; i < this.getObjetivos().size(); i++) {
            Objetivo objetivo = this.getObjetivos().get(i);
            
            String desObjetivo = objetivo.getDescripcion(); 
            Object object = invocar.invokeFunction("unescapeTexto", desObjetivo);
            desObjetivo = (String) object;
            desObjetivo = desObjetivo.replaceAll("\n","&#13;&#10;");
            
            result += "<div class='unidad'>"
                    + "<div class='input-group'>"
                    + "<textarea class='form-control Objetivos' onkeypress='return soloLetras(event);' onkeyup='verificarCambiosInput(this);' id='" + objetivo.getIdObjetivo() + "' placeholder='Descripci&oacute;n del Objetivo'>"+ desObjetivo + "</textarea>"
                    + "<span onclick='eliminarObjetivo(this);' class='input-group-addon deleteRecurso' data-toggle='tooltip' data-placement='bottom' title='Eliminar Objetivo' id='" + objetivo.getIdObjetivo() + "'>"
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
//        result += "</div>"
//                + "<div id='contenidoObservaciones'></div>"
//                + "</div>"
//                + "</div>"
//                + "<input type='hidden' id='idUnidad' value='" + this.getSilabos().getIdUnidad() + "'"
//                + "</form>";

        return result;

    }
   

    public String seleccionadostoHTML() {
        String result = "";
        for (Objetivo o : this.getObjetivos()) {
            if (o.getDescripcion() != null) {
                result = o.getDescripcion();
            }
        }
        return result;
    }
}
