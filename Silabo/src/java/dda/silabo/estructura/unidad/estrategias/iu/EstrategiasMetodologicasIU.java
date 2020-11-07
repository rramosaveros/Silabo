/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidad.estrategias.iu;

import dda.silabo.estructura.unidad.estrategias.comunes.Estrategia;
import dda.silabo.estructura.unidad.estrategias.comunes.EstrategiasMetodologicas;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Jorge Zaruma
 */
public class EstrategiasMetodologicasIU extends EstrategiasMetodologicas {

    public String toHTMLDOCENTE() {
        EstrategiasIU estrategiasIU = new EstrategiasIU();
        String result = "<div class='form-group'>"
                + "                                        <ul class='nav nav-tabs'>";

        for (Estrategia e : this.getNivel1()) {
            result += "                                            <li  id='" + e.getId_estrategia() + "' onclick='agregarMetodologiaTipo(this);' class='nav-item items'>"
                    + "                                                <a class='nav-link' href='#'>" + e.getNombre() + "</a>"
                    + "                                            </li>";
        }
        result += "                                        </ul>"
                + "                                    </div>"
                + "<div id='contenidoM'>"
                + estrategiasIU.toHTML(this, 0)
                + "</div>"
                + "<div id='contenidoObservaciones'></div>";

        return result;
    }

    public String toHTML() {
        String result = "<form>"
                + "                                        <div class='form-group row'>"
                + "                                            <div class='col-xs-12' id='seccionContenido'>"
                + "   <div class='float-xs-right pr-0 unidad-nueva' style='margin-bottom: 2%;'>"
                + "   <button class='btn btn-primary active' onclick='addMetodologia(this); return false;' type='button' data-accion='agregar' data-nombre='Metodología' data-nivel='contenidoM' data-select='false' data-id='0'>"
                + "       <i class='fa fa-plus-circle'></i>"
                + "       Nueva Estrategia"
                + "   </button>"
                + "   </div>"
                + "<div class='contenidoM'>";
        for (Estrategia nivel1 : this.getNivel1()) {
            result += "<div class='nivel1' data-select='false'>   "
                    + "     <div class='input-group'>       "
                    + "    <input class='form-control informacion' id='" + nivel1.getId_estrategia() + "' type='text' readonly='' placeholder='Example input' value='" + nivel1.getNombre() + "' data-descripcion='" + nivel1.getDescripcion() + "'>       "
                    + "    <span class='input-group-addon' onclick='addMetodologiaEditar(this); return false;'><i class='fa fa-edit'></i></span>       "
                    + "    <span class='input-group-addon' onclick=deleteMetodologia(this);><i class='fa fa-minus-circle'></i></span>      "
                    + "    <span class='input-group-addon' onclick='addMetodologia(this); return false;' data-accion='agregar' data-nombre='dsadas' data-nivel='nivel2'><i class='fa fa-plus-circle'></i></span>   "
                    + "     </div>   ";
            for (Estrategia nivel2 : nivel1.getNivel2()) {
                result += "<div class='nivel2' data-select='false'>"
                        + "     <div class='input-group' style='width: 95%; float: right;'>       "
                        + "    <input class='form-control informacion' id='" + nivel2.getId_estrategia() + "' type='text' readonly='' placeholder='Example input' value='" + nivel2.getNombre() + "' data-descripcion='" + nivel2.getDescripcion() + "'>       "
                        + "     <span class='input-group-addon' onclick='addMetodologiaEditar(this); return false;'><i class='fa fa-edit'></i></span>       "
                        + "     <span class='input-group-addon' onclick=deleteMetodologia(this);><i class='fa fa-minus-circle'></i></span> "
                        + "     <span class='input-group-addon' onclick='addMetodologia(this); return false;' data-accion='agregar' data-nombre='sdsadasd' data-nivel='nivel3' data-select='false'><i class='fa fa-plus-circle'></i></span>"
                        + "     </div>";
                for (Estrategia nivel3 : nivel2.getNivel3()) {
                    result += "<div class='nivel3' data-select='false'>"
                            + "     <div class='input-group' style='width: 90%; float: right;'>       "
                            + "    <input class='form-control informacion' id='" + nivel3.getId_estrategia() + "' type='text' readonly='' placeholder='Example input' value='" + nivel3.getNombre() + "' data-descripcion='" + nivel3.getDescripcion() + "'>       "
                            + "     <span class='input-group-addon' onclick='addMetodologiaEditar(this); return false;'><i class='fa fa-edit'></i></span>      "
                            + "     <span class='input-group-addon' onclick=deleteMetodologia(this);><i class='fa fa-minus-circle'></i></span>"
                            + "     </div>"
                            + "</div>";
                }
                result += "</div>";
            }

            result += "</div>";
        }
        result += "</div>"
                + "   <br>"
                + "   <br>"
                + "   <div class='form-group row'>"
                + "   <div class='col-xs-9'>"
                + "   </div>"
                + "   <div class='col-xs-3'>"
                + "       <button title='' class='btn btn-primary float-xs-right' id='btnGuardar' onclick='guardarMetodologias();' type='button' data-toggle='tooltip' data-placement='top'>"
                + "           Guardar | <i class='fa fa-fw'></i>"
                + "       </button>"
                + "   </div>"
                + "   </div>"
                + ""
                + "                                            </div>"
                + "                                        </div>"
                + "                                    </form>"
                + "<div id='contenidoObservaciones'></div>";
        try {
        } catch (Exception e) {
        }
        return result;
    }
}
