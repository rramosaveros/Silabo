/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.datosdocentes.iu;

import dda.silabo.opciones.comunes.Opcion;
import dda.silabo.roles.comunes.Rol;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class RolUsuarioIU extends Rol {

    public String toHTMLPanel(List<Rol> roles) {
        String result = "";
        if (roles.isEmpty()) {
            result += "<li>"
                    + "                <a class='nav-link' id='' >";
            result += "Roles no Definidos";
            result += " </a>"
                    + " </li>";
        }
        for (Rol rolUsuario : roles) {

            result += "<li>"
                    + "                <a class='nav-link' id='" + rolUsuario.getRolChar() + "' onclick='cambioRolesPanel(this);'>";
            result += rolUsuario.getDescRol();
            result += " </a>"
                    + " </li>";
        }
        result += "<li class='dropdown-divider'></li>"
                + "<li>"
                + "  <a class='nav-link' onclick='cerrarSession();'>Salir <i class='fa fa-sign-out' aria-hidden='true'></i></a>"
                + "</li>";
        return result;
    }

    public String toHTMLTrabajo(List<Rol> roles) {
        String result = "";
        for (Rol rolUsuario : roles) {

            result += "<li>"
                    + "                <a class='nav-link' id='" + rolUsuario.getRolChar() + "' onclick='cambioRolesTrabajo(this);'>";
            result += rolUsuario.getDescRol();
            result += " </a>"
                    + " </li>";
        }
        result += "<li class='dropdown-divider'></li>"
                + "<li>"
                + "  <a class='nav-link' onclick='cerrarSession();'>Salir <i class='fa fa-sign-out' aria-hidden='true'></i></a>"
                + "</li>";
        return result;
    }

    public String toHTMLRolActivo(List<Rol> roles, String RolActivo) {
        String result = "";
        for (Rol rol : roles) {
            if (rol.getRolChar().equals(RolActivo)) {
                result = rol.getDescRol();
                break;
            }
        }
        return result;
    }

    public String toHTMLMenuTipo(List<Rol> roles, String rolActivo, List<Opcion> opciones) {
        String result = "";
        Opcion opcion = opciones.stream().filter(op -> op.getIdOpcion() == 9).findFirst().orElse(null);
        if (opcion != null) {
            if (opcion.getCheck() != null) {
                result += "<span id='lnkReportes' onclick=loadEntidadPanel('todos'); class='dda-link'>"
                        + "      <i class='fa fa-pie-chart'></i>"
                        + "</span>";
            }
        }
        opcion = opciones.stream().filter(op -> op.getIdOpcion() == 10).findFirst().orElse(null);
        if (opcion != null) {
            if (opcion.getCheck() != null) {
                result += "<span id='lnkDocumentos' onclick=loadEntidadTrabajo('getDocenteInicio','todos','todos','todos','" + rolActivo + "'); class='dda-link'>"
                        + "      <i class='fa fa-file-text'></i>"
                        + "</span>";
            }
        }
//        Rol rol = roles.stream().filter(rolA -> rolA.getRolChar().equals("Adm")).findFirst().orElse(null);
//        opcion = opciones.stream().filter(op -> op.getIdOpcion() == 11).findFirst().orElse(null);
//        if (rol != null && opcion != null) {
//            if (opcion.getCheck() != null) {
//                result += "<span id='lnkConfigurar' onclick='loadEntidadAdministrador();' class='dda-link'>";
//                result += "      <i class='fa fa-cog'></i>";
//                result += "</span>";
//            }
//        }
        return result;
    }

    public String toHTMLMenuTipoEstudiante() {
        String result = "";

//        result += "<span id='lnkReportes' class='dda-link dda-link-selected'>"
//                + "      <i class='fa fa-pie-chart'></i>"
//                + "</span>";
        return result;
    }

}
