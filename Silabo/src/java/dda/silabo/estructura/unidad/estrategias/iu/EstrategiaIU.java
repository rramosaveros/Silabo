package dda.silabo.estructura.unidad.estrategias.iu;

import dda.silabo.estructura.unidad.estrategias.comunes.Estrategia;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TOSHIBA
 */
public class EstrategiaIU extends Estrategia {

    public String toHTML() {
        String result = "";
        result += "<tr>"
                + "        <td><input type='checkbox' id='" + this.getId_estrategia() + "' class='Estrategias' value='" + this.getId_estrategia() + "' " + this.getChv_check() + ">" + this.getDescripcion() + "</td>"
                + "</tr>";
        return result;
    }

}
