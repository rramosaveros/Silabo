/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.escenarios.iu;

import dda.silabo.escenarios.comunes.Escenario;

/**
 *
 * @author Jorge
 */
public class EscenarioIU {

    public String toHTML(Escenario escenario, String estadoContenido) {
        String result = "";
        result += "        <tr width='100%' onclick='seleccionarItem(this);'>"
                //                + "            <td width='100%'><input type='checkbox' id='" + escenario.getIdEsc().toString() + "' class='Escenarios'" + estadoContenido + " value='" + escenario.getDescripcion() + "' " + escenario.getCheck() + ">" + escenario.getDescripcion() + "</td>"
                + "<td>"
                + "<div class='input-group'>"
                + "                                         <input type='text' class='form-control' readonly value='" + escenario.getDescripcion() + "' placeholder='Recurso' aria-label='recurso' aria-describedby='basic-addon1'>"
                + "                                        <div class='input-group-addon'>"
                + "                                            <input type='checkbox' id='" + escenario.getIdEsc().toString() + "' class='Escenarios'" + estadoContenido + " value='" + escenario.getDescripcion() + "' " + escenario.getCheck() + ">"
                + "                                        </div>"
                + "                                    </div>"
                + "</td>"
                + "        </tr>";
        return result;
    }
}
