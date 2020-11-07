/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.roles.iu;

import dda.silabo.roles.comunes.Rol;
import dda.silabo.roles.comunes.Roles;

/**
 *
 * @author Jorge Zaruma
 */
public class RolesIU extends Roles {

    public String toHTML() {
        String result = "";
        result += "<form>"
                + "<div class='form-group row'>"
                + "<div id='seccionContenido' class='col-xs-12'>"
//                + "<div class='float-xs-right pr-0 unidad-nueva'>"
//                + "<button type='button' class='btn btn-secondary active' onclick='agregarNuevoRol(); return false;'>"
//                + "<i class='fa fa-plus-circle'></i>"
//                + "Nuevo Rol"
//                + " </button>"
//                + "</div>"
                + "<div id='ContenidoGestion'>";
        if (this.getRoles().size() > 0) {
            for (Integer i = 0; i < this.getRoles().size(); i++) {
                Rol rol = this.getRoles().get(i);
                if (rol.getEstado().equals("H")) {
                    result += "<div class='unidad'>"
                            + "<div class='input-group'>"
                            + "<input type='text' class='form-control Roles' id='" + rol.getIdRol() + "' placeholder='Descripci&oacute;n de Rol' value='" + rol.getDescRol() + "'/>";
//                    result += "<span class='input-group-addon' onclick='eliminarRol(" + rol.getIdRol() + "); return false;' data-toggle='tooltip' data-placement='bottom' title='Eliminar Rol' id='" + rol.getIdRol() + "'>"
//                            + "<i class='fa fa-minus-circle'></i>"
//                            + "</span>"; 
                    result += "<span class='input-group-addon' onclick='agregarUsuariosRol(this," + rol.getIdTipoEntidad() + "); return false;' id='" + rol.getIdRol() + "' data-toggle='tooltip' data-placement='bottom' title='Usuarios'>"
                            + "<i class='fa fa-users' aria-hidden='true'></i>"
                            + "</span>";
                    result += "<span class='input-group-addon' onclick='agregarOpcionesRol(this);'  data-toggle='tooltip' data-placement='bottom' title='Opciones' id='" + rol.getIdRol() + "'>"
                            + "<i class='fa fa-bars' aria-hidden='true'></i>"
                            + "</span>";
                    result += "</div></div> ";
                } else {
                    result += "<div class='unidad'>"
                            + "<div class='input-group'>"
                            + "<input type='text' class='form-control Roles' readonly placeholder='Descripci&oacute;n de Rol' value='" + rol.getDescRol() + "'/>";
                    result += "<span class='input-group-addon' onclick='habilitarRol(" + rol.getIdRol() + ");' data-toggle='tooltip' data-placement='bottom' title='Habilitar Rol'>"
                            + "<input type='checkbox'>"
                            + "</span>";
                    result += "<span class='input-group-addon' onclick='agregarUsuariosRol(this," + rol.getIdTipoEntidad() + "); return false;' id='" + rol.getIdRol() + "' data-toggle='tooltip' data-placement='bottom' title='Usuarios'>"
                            + "<i class='fa fa-users' aria-hidden='true'></i>"
                            + "</span>";
                    result += "<span class='input-group-addon' onclick='agregarOpcionesRol(this);'  data-toggle='tooltip' data-placement='bottom' title='Opciones' id='" + rol.getIdRol() + "'>"
                            + "<i class='fa fa-bars' aria-hidden='true'></i>"
                            + "</span>";
                    result += "</div></div> ";
                }
            }
        }
        result += "</div>"
                + "</div>"
                + "</div>"
                + "</form>";
//                + "<!-- barra de botones -->"
//                + "<div class='form-group row'>"
//                + "<div class='col-xs-9'>"
//                + "</div>"
//                + "<div class='col-xs-3'>"
//                + "<button type='button' id='btnGuardar' onclick='guardarRolUsuarios(); return false;' class='btn btn-secondary float-xs-right' data-toggle='tooltip' data-placement='top' title='Guardar cambios'>"
//                + "Guardar | <i class='fa fa-fw'></i>"
//                + "</button>" 
//                + "</div>"
//                + "</div>";
        return result;
    }

    public String getOpcionesRoles() {
        String result = "";
        try {

        } catch (Exception e) {

        }
        return result;
    }

}
