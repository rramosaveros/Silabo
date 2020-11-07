package dda.silabo.estructura.unidad.estrategias.iu;

import dda.silabo.estructura.unidad.estrategias.comunes.Estrategia;
import dda.silabo.estructura.unidad.estrategias.comunes.Estrategias;
import java.util.List;
import java.util.Objects;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TOSHIBA
 */
public class EstrategiasIU extends Estrategias {
    
    public String toHTML(EstrategiasMetodologicasIU estrategias, Integer id) {
        String result = "", estadoContenido = "";
//        if (this.getSilabos().getRol().equals("Coordinador")) {
//            estadoContenido = "disabled";
//        }
        Estrategia estrategia2 = null;
        if (id == 0) {
            estrategia2 = estrategias.getNivel1().stream().findFirst().orElse(null);
        } else {
            estrategia2 = estrategias.getNivel1().stream().filter(n1 -> Objects.equals(n1.getId_estrategia(), id)).findFirst().orElse(null);
        }
        
        result += "    <table class='record_table table-hover' id='exampleSelect2' width='100%'>";
        
        for (Estrategia e2 : estrategia2.getNivel2()) {
            result += "<tr>"
                    + "                                                    <td>"
                    + "                                                        <label class='form-control'><b>" + e2.getNombre() + "</b></label>"
                    + "                                                    </td>"
                    + "                                                </tr>";
            result += "<tr id ='" + e2.getId_estrategia() + "' onclick='seleccionarTodosItemsEstrategias(this);'>"
                    + "                                                    <td>"
                    + "                                                      <div class='selectstrategies'> Seleccionar todos <input type='checkbox'> </div>"
                    + "                                                    </td>"
                    + "                                                </tr>";
            
            for (Estrategia estrategia : e2.getNivel3()) {
                if (estrategia.getDescripcion() != null) {
//                    String ayuda = "<div style='text-align:justify'>'" + estrategia.getDescripcion() + "'</div>";
                    if (estrategia.getDescripcion().equals("")) {
                        estrategia.setDescripcion("No definido");
                    }
                    result += "<tr class='" + e2.getId_estrategia() + "' onclick='seleccionarItem(this);'>"
                            //                        + "<td><input type='checkbox' id='" + estrategia.getId_estrategia() + "' class='Estrategias'" + estadoContenido + "  value='" + estrategia.getDescripcion() + "' " + estrategia.getChv_check() + ">" + estrategia.getDescripcion() + "</td>"
                            + "<td>"
                            + "<div class='input-group' style='width: 95%; float: right' >"
                            + "                                         <input type='text' readonly class='form-control' value='" + estrategia.getNombre() + "' placeholder='Recurso' aria-label='recurso' aria-describedby='basic-addon1'>"
                            + "                                        <span class='input-group-addon' onmouseover='mostrarAyuda(this);'><a  class='nav-link fa fa-info' tabindex='0' role='button' data-trigger='focus' data-content='" + estrategia.getDescripcion() + "' data-placement='left'></a></span>"
                            + "                                        <span class='input-group-addon'>"
                            + "<input type='checkbox' id='" + estrategia.getId_estrategia() + "' data-idpadre='" + estrategia2.getId_estrategia() + "' class='Estrategias'" + estadoContenido + "  value='" + estrategia.getNombre() + "' " + estrategia.getChv_check() + ">"
                            + "                                        </span>"
                            + "                                    </div>"
                            + "</td>"
                            + "</tr>";
                }
            }
            
        }
        result += "<input type='hidden' id='idEstrategia' value='" + estrategia2.getId_estrategia() + "'>";
        result += "<input type='hidden' id='idUnidad' value='" + estrategias.getSilabos().getIdUnidad() + "'>";
        result += "    </table>";
        
        return result;
    }
    
    public String seleccionadostoHTML() {
        String result = "";
        for (Integer i = 0; i < this.getEstrategias().size(); i++) {
            if (this.getEstrategias().get(i).getChv_check() != null) {
                result += this.getEstrategias().get(i).getDescripcion();
            }
        }
        return result;
    }
}
