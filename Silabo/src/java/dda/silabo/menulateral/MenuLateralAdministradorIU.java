/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.menulateral;

import dda.silabo.opciones.comunes.Opcion;
import dda.silabo.roles.comunes.Roles;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class MenuLateralAdministradorIU extends Roles {

    public String toHTMLAdministrador(List<Opcion> opciones) {

        String result = "";
        result += " <div>"
                + " <ul id='treeview'>";
        int cont = 0;
        Opcion opcion = opciones.stream().filter(op -> op.getIdOpcion() == 5).findFirst().orElse(null);
        if (opcion != null) {
            if (opcion.getCheck() != null) {
                result += " <li id='mnuSilabo' data-expanded=''> Gesti&oacute;n de S&iacute;labo"
                        + " <ul>"
                        + " <li data-expanded='true'> Gesti&oacute;n de Estructura y desarrollo"
                        + " <ul>"
                        + " <li>"
                        + " <a class='nav-link' id='Estrategias' onclick='clicContenidoA(this)'>"
                        + "  Estrategias Metodol&oacute;gicas"
                        + "   </a>"
                        + "   </li>"
                        + "   <li>"
                        + "  <a class='nav-link' id='Recursos' onclick='clicContenidoA(this)'>"
                        + "   Recursos"
                        + "  </a>"
                        + "  </li>"
                        + "  <li> "
                        + "  <a class='nav-link' id='Aula' onclick='clicContenidoA(this)'>"
                        + "   Actividades de Aprendizaje en el Aula"
                        + "   </a>"
                        + "   </li>"
                        + "   <li>"
                        + "  <a class='nav-link' id='Autonomas' onclick='clicContenidoA(this)'>"
                        + "    Actividades de Aprendizaje Aut&oacute;nomas"
                        + "   </a>"
                        + "   </li>"
                        + "   </ul>"
                        + "   </li>"
                        + "   <li data-expanded='true'>Gesti&oacute;n de Escenarios de Aprendizaje"
                        + "   <ul>"
                        + "   <li>"
                        + "   <a class='nav-link' id='Real' onclick='clicContenidoA(this)'>"
                        + "   Reales"
                        + "   </a>"
                        + "   </li>"
                        + "   <li>"
                        + "   <a class='nav-link' id='Aulico' onclick='clicContenidoA(this)'>"
                        + "   A&uacute;licos"
                        + "   </a>"
                        + "   </li>"
                        + "   <li>"
                        + "   <a class='nav-link' id='Virtual' onclick='clicContenidoA(this);'>"
                        + "    Virtuales"
                        + "   </a>"
                        + "   </li>"
                        + "   </ul>"
                        + "   </li>"
                        + "   </ul>"
                        + "   </li>";
                cont++;
            }
        }
        opcion = opciones.stream().filter(op -> op.getIdOpcion() == 6).findFirst().orElse(null);
        if (opcion != null) {
            if (opcion.getCheck() != null) {
                result += " <li id='opciones' data-expanded=''> "
                        + "<a class='nav-link' id='usuarios' onclick='clicUsuarios(this);'>"
                        + " Gesti&oacute;n de Usuarios"
                        + "   </a>"
                        + "   </li>";
                cont++;
            }
        }
        opcion = opciones.stream().filter(op -> op.getIdOpcion() == 7).findFirst().orElse(null);
        if (opcion != null) {
            if (opcion.getCheck() != null) {
                result += " <li id='opciones' data-expanded=''> "
                        + "<a class='nav-link' id='roles' onclick='clicContenidoA(this);'>"
                        + " Gesti&oacute;n de Roles"
                        + "   </a>"
                        + "   </li>";
                cont++;
            }
        }
        opcion = opciones.stream().filter(op -> op.getIdOpcion() == 8).findFirst().orElse(null);
        if (opcion != null) {
            if (opcion.getCheck() != null) {
                result += " <li id='opciones' data-expanded=''> "
                        + "<a class='nav-link' id='opciones' onclick='clicContenidoA(this);'>"
                        + " Gesti&oacute;n de Opciones"
                        + "   </a>"
                        + "   </li>";
                cont++;
            }
        }
        
         result += " <li id='opciones' data-expanded=''> "
                    + "<a class='nav-link' id='opciones' onclick='cargarParametros();'>"
                    + " Par&aacute;metros de S&iacute;labo"
                    + "   </a>"
                    + "   </li>";

        if (cont == 0) {
            result += " <li id='opciones' data-expanded=''> "
                    + "<a class='nav-link' id='opciones'  >"
                    + " Sin opciones de Configuración"
                    + "   </a>"
                    + "   </li>";
        }

        result += "   </ul>"
                + "   </div>";
        return result;
    }
}
