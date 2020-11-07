package dda.silabo.estructura.unidad.recursos.iu;

import dda.silabo.estructura.unidad.recursos.comunes.Recurso;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TOSHIBA
 */
public class RecursoIU extends Recurso {

    public String toHTML(Recurso recurso) {
        String result = "";
        result += "        <tr>"
                + "            <td><input type='checkbox' id='" + recurso.getIdRecurso() + "' class='Recursos' value='" + recurso.getIdRecurso() + "' " + recurso.getChv_check() + ">" + recurso.getStrDescripcion() + "</td>"
                + "        </tr>";
        return result;
    }

}
