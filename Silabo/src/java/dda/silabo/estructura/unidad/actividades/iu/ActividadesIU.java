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
 * @author Jorge
 */
public class ActividadesIU extends Actividades {

    public String toHTML2(String tipo) {
        String result = "", estadoContenido = "";
        result = " <div class='form-group row'>"
                + "                                            <div id='seccionContenido' class='col-xs-12'>"
                + "                                                <div class='float-xs-right pr-0 unidad-nueva'>"
                + "                                                    <button type='button' class='btn btn-primary' onclick='addActividadUusario(this);' data-id='0'>"
                + "                                                        <i class='fa fa-plus-circle'></i>"
                + " Actividad de Aprendizaje Autónoma"
                + "                                                    </button>"
                + "                                                </div>"
                + "                                            </div>"
                + "<div class='col-xs-12 actividad'style='margin-top: 2%;'>"
//                + "<span onclick='seleccionarTodosItems(this);'>"
//                + "                                            <input type='checkbox'> Seleccionar todos"
//                + "</span>"
                + " <table multiple class='record_table table-hover' id='exampleSelect2' width='100%'>"
                + "<tbody>";

        for (Integer i = 0; i < this.getActividades().size(); i++) {
            Actividad actividad = (Actividad) this.getActividades().get(i);
            if (actividad.getDescripcion() != null) {

                result += "        <tr onclick='seleccionarItem(this);'>"
                        //                        + "            <td><input type='checkbox' id='" + actividad.getId_actividades_aprendizaje() + "' class='Actividades' " + estadoContenido + " value='" + actividad.getDescripcion() + "' " + actividad.getChv_check() + " >" + actividad.getDescripcion() + "</td>"
                        + "<td>"
                        + "<div class='input-group'>";
                if (!actividad.getRol().equals("Doc")) {
                    result += "                                         <input type='text' class='form-control' readonly value='" + actividad.getDescripcion() + "' onkeypress='return soloLetras(event);' placeholder='Recurso' aria-label='recurso' aria-describedby='basic-addon1'>"
                            + "                                        <div class='input-group-addon'>"
                            + "                                            <input type='checkbox' id='" + actividad.getId_actividades_aprendizaje() + "' class='Actividades' " + estadoContenido + " value='" + actividad.getDescripcion() + "' " + actividad.getChv_check() + " >"
                            + "                                        </div>";
                } else {
                    if (actividad.getChv_check() != null) {
                        result += "                                         <input type='text' class='form-control nuevasActividades' id='" + actividad.getId_actividades_aprendizaje() + "' value='" + actividad.getDescripcion() + "' onkeypress='return soloLetras(event);' placeholder='Nueva Actividad' aria-label='actvidad' aria-describedby='basic-addon1'>"
                                + "                                        <span onclick='removerActvidad(this);' class='input-group-addon'>"
                                + "<i class='fa fa-minus-circle'></i>"
                                + "                                        </span>";
                    }
                }
                result += "                                    </div>"
                        + "</td>"
                        + "        </tr>";

            }

        }
        result += "</tbody></table>"
                + "<input type='hidden' id='idUnidad' value='" + this.getSilabos().getIdUnidad() + "'>"
                + "<input type='hidden' id='tipoA' value='" + tipo + "' >"
                + "</div>"
                + "</div>"
                + "<div id='contenidoObservaciones'></div>";

        return result;
    }

    public String toHTML(String tipo) {
        String result = "";
        if (tipo.equals("Aula")) {
            try {
                result += " <form>";
                for (Actividad principal : this.getActividades()) {
                    result += "                                        <div class='form-group row'>"
                            + "                                            <div id='seccionContenido' class='col-xs-12'>"
                            + "                                                <div class='float-xs-right pr-0 unidad-nueva'>"
                            + "                                                    <button type='button' class='btn btn-primary' onclick='addActividadUusario(this);' data-id='" + principal.getId_actividades_aprendizaje() + "'>"
                            + "                                                        <i class='fa fa-plus-circle'></i>   "
                            + "ACTIVIDAD DE APRENDIZAJE EN EL AULA "+principal.getDescripcion()
                            + "                                                    </button>"
                            + "                                                </div>"
                            + "                                            </div>"
                            + "                                            <div class='col-xs-12 actividad' style='margin-top: 2%;'>"
                            + "                                                <table multiple class='record_table table-hover' id='exampleSelect2' width='100%'>"
                            + "<tbody>";
                    for (Actividad nivel2 : principal.getNivel2()) {
                        result += "                                                    <tr onclick='seleccionarItem(this);'>"
                                + "                                                        <td>"
                                + "                                                            <div class='input-group'>";
                        if (!nivel2.getRol().equals("Doc")) {

                            result += "                                         <input type='text' class='form-control' readonly value='" + nivel2.getDescripcion() + "' onkeypress='return soloLetras(event);' placeholder='Recurso' aria-label='recurso' aria-describedby='basic-addon1'>"
                                    + "                                        <div class='input-group-addon'>"
                                    + "                                            <input type='checkbox' id='" + nivel2.getId_actividades_aprendizaje() + "' class='Actividades'  value='" + nivel2.getDescripcion() + "' " + nivel2.getChv_check() + " >"
                                    + "                                        </div>";
                        } else {
                            if (nivel2.getChv_check() != null) {
                                result += "                                         <input type='text' class='form-control nuevasActividades' id='" + nivel2.getId_actividades_aprendizaje() + "' value='" + nivel2.getDescripcion() + "' onkeypress='return soloLetras(event);' placeholder='Nueva Actividad' aria-label='actvidad' aria-describedby='basic-addon1'>"
                                        + "                                        <span onclick='removerActvidad(this);' class='input-group-addon'>"
                                        + "<i class='fa fa-minus-circle'></i>"
                                        + "                                        </span>";
                            }
                        }

                        result += "                                    </div>"
                                + "                                                        </td>"
                                + "                                                    </tr>";
                    }
                    result += "                                               </tbody> </table>"
                            + "<input type='hidden' id='idUnidad' value='" + this.getSilabos().getIdUnidad() + "'>"
                            + "<input type='hidden' id='tipoA' value='" + tipo + "' >"
                            + "                                            </div>"
                            + "                                        </div>";
                }
                result += "                                    </form>"
                        + "<div id='contenidoObservaciones'></div>";

            } catch (Exception e) {
            }
        } else {
            result = toHTML2(tipo);
        }
        return result;
    }

    public String seleccionadostoHTML() {
        String result = "";
        for (Integer i = 0; i < this.getActividades().size(); i++) {
            if (this.getActividades().get(i).getChv_check() != null) {
                result += this.getActividades().get(i).getDescripcion();
            }
        }
        return result;
    }
}


//    public String toHTMLDOCENTE() {
//        String result = "<div class='form-group'>"
//                + "                                        <ul class='nav nav-tabs'>";
//
////        for (Actividades e : this.getNivel1()) {
//            result += "                                            <li  id='' onclick='agregarMetodologiaTipo(this);' class='nav-item items'>"
//                    + "                                                <a class='nav-link' href='#'>Hola Actividades </a>"
//                    + "                                            </li>";
////        }
//        result += "                                        </ul>"
//                + "                                    </div>"
////                + "<div id='contenidoM'>"
////                + estrategiasIU.toHTML(this, 0)
////                + "</div>"
//                + "<div id='contenidoObservaciones'></div>";
//
//        return result;
//    }
    