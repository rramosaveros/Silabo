/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.menulateral;

import dda.silabo.roles.comunes.Rol;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class ContenidoInicioIU {

    public String menuPanel(List<Rol> roles) {
        String result = "";
        if (!roles.isEmpty()) {
            result += " <div><ul id='treeview'>"
                    + "  <li data-expanded='true'>Reportes"
                    + "   <ul>"
                    + "     <li>"
                    + "         <a class='nav-link' id='bibliografias' onclick='reporteCriteriosEntidadUsuario(this.id);'>"
                    + "             Bibliograf&iacute;as B&aacute;sicas "
                    + "         </a>"
                    + "     </li>"
                    + "     <li>"
                    + "         <a class='nav-link' id='criterios' onclick='reporteCriteriosEntidadUsuario(this.id);'>"
                    + "             Criterios de Evaluaci&oacute;n"
                    + "         </a>"
                    + "     </li>"
                    + "     <li>"
                    + "       <a class='nav-link' id='recursos' onclick='reporteCriteriosEntidadUsuario(this.id);'>"
                    + "           Recursos "
                    + "       </a>"
                    + "   </li>"
                    + "     <li>"
                    + "       <a class='nav-link' id='logros' onclick='reporteCriteriosEntidadUsuario(this.id);'>"
                    + "           Logros de Aprendizaje "
                    + "       </a>"
                    + "   </li>"
                    + "</ul>"
                    + "</li>"
                    + "<li data-expanded='true'>"
                    + "       <a class='nav-link' id='todos' onclick='cargarEstructuraCurricular(this.id,this.id);'>"
                    + "          Estructura Curricular "
                    + "       </a>"
                    + "   </li>"
                    + "<li data-expanded='true'>"
                    + "       <a class='nav-link' id='todos' onclick='cargarEstadoSilabos();'>"
                    + "          Estado de S&iacute;labos "
                    + "       </a>"
                    + "   </li>"
                    + "</ul>"
                    + "</div>";
        } else {
            result += " <div><ul id='treeview'>"
                    + "<li data-expanded='true'>"
                    + "       <a class='nav-link' id='todos' onclick='cargarEstructuraCurricular(this.id,this.id);'>"
                    + "          Estructura Curricular "
                    + "       </a>"
                    + "   </li>"
                    + "</ul></div>";
        }
        return result;
    }
}
