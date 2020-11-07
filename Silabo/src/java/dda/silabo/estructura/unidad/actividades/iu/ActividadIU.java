/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidad.actividades.iu;

import dda.silabo.estructura.unidad.actividades.comunes.Actividad;

/**
 *
 * @author Jorge
 */
public class ActividadIU extends Actividad{

    public String toHTML() {
        String result = "";
        result += "        <tr>"
                + "            <td><input type='checkbox' id='" + this.getId_actividades_aprendizaje() + "' class='Actividades' value='" + this.getId_actividades_aprendizaje() + "' " + this.getChv_check() + ">" + this.getDescripcion() + "</td>"
                + "        </tr>";
        return result;
    }
}
