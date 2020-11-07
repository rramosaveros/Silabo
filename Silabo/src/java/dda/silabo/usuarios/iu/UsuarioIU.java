/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.usuarios.iu;

import dda.silabo.clases.unidos.EntidadUnidos;
import dda.silabo.roles.comunes.Rol;
import dda.silabo.usuarios.comunes.Usuario;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class UsuarioIU extends Usuario {

    public String HTMLInfoUsuario() {
        String result = "";
        String nombre = "", apellido = "", email = "", read = "", tipo = "", acciontipo = "";
        int id = 0;
        if (this.getApellido() != null) {
            nombre = this.getNombre();
            apellido = this.getApellido();
            email = this.getEmail();
            id = this.getId();
            read = "readonly";
            acciontipo = this.getTipo();
            if (this.getEstado().equals("E")) {
                tipo = "EXTERNO";
            } else if (this.getEstado().equals("O")) {
                tipo = "OASIS";
            } else {
                tipo = "BASE";
            }
        }
         tipo = "EXTERNO";
//        if (!tipo.equals("")) {
            result += "<br><div style='width: 100%; text-align: center'>"
                    + "<b>Usuario</b> </div>"
                    + "<input type='hidden' id='tipoUsuario' value='" + tipo + "'>"
                    + "<input type='hidden' id='idUsuario' value='" + id + "'>"
                    + "<div class='input-group'>"
                    + "<span style='width: 18%;' class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='Nombre de Usuario' id=''>Nombres</span>"
                    + "<input type='text' " + read + " id='nombre' onkeyup='upperCaseTexto(this.id);' class='form-control' placeholder='Nombres de Usuario' value='" + nombre + "'>"
                    + " </div>"
                    + "<div class='input-group'>"
                    + "<span style='width: 18%;' class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='Apellido de Usuario' id=''>Apellidos</span>"
                    + "<input type='text' " + read + " id='apellido' onkeyup='upperCaseTexto(this.id);' class='form-control' placeholder='Apellidos de Usuario' value='" + apellido + "'>"
                    + "</div>"
                    + "<div class='input-group'>"
                    + "<span style='width: 18%;' class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='E-mail de Usuario' id=''>E-mail</span>"
                    + "<input type='text' " + read + " id='email' onkeyup='upperCaseTexto(this.id);' class='form-control' placeholder='jorgeos193@hotmail.com' value='" + email + "'>"
                    + "</div>"
                    + "<div class='input-group'>"
                    + "<span style='width: 18%;' class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='E-mail de Usuario' id=''>Tipo</span>"
                    + "<input readonly type='text' " + read + " id='email' class='form-control' placeholder='' value='" + acciontipo + "'>"
                    + "</div>"
                    + "<button title='Agregar' class='btn btn-secondary float-xs-right ' id='btnGuardar' onclick='AgregarUsuarioRegistrado(); return false;' type='button' data-toggle='tooltip' data-placement='top'>Guardar | <i class='fa fa-fw'></i></button>";
//        } else {
//            result += "<input type='hidden' id='tipoUsuario' value='" + tipo + "'>";
//            result += "Información no disponible";
//        }
        return result;
    }

    public String modalHTML() {
        String result = "";
        result += " <div class='modal fade' id='modalUsuariosEditar' role='dialog'>"
                + " <div class='modal-dialog'>"
                + " <div class='modal-content'>"
                + "     <div class='modal-header'>"
                + "         <button type='button' class='close' onclick='cerrarModalContenido();' data-dismiss='modal' aria-hidden='true'>&times;</button>"
                + "         <div id='ingresarUsuario' style='width: 100%; text-align: center;'><h4><b>Informaci&oacute;n de Usuario</b></h4></div>"
                + "     </div>"
                + "     <div class='modal-body'>"
                + "         <div id='ingresarUsuario' style='width: 100%; text-align: left;'>"
                + "             <div class='unidad'>"
                + "                 <div class='input-group'>"
                + "                     <span style='width: 18%;' class='input-group-addon' title='C&eacute;dula de Usuario'>C&eacute;dula: </span>"
                + "                     <input type='text' class='form-control' readonly id='Cedula' maxlength='11' placeholder='020251936-9' value='" + this.getCedula() + "'/>"
                + "                 </div>"
                + "                 <div class='input-group'>"
                + "                     <span style='width: 18%;' class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='Nombre de Usuario' id=''>Nombres</span>"
                + "                     <input type='text' id='nombre' class='form-control' onkeyup='upperCaseTexto(this.id);' placeholder='Nombres de Usuario' value='" + this.getNombre() + "'>"
                + "                 </div>"
                + "                <div class='input-group'>"
                + "                    <span style='width: 18%;' class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='Apellido de Usuario' id=''>Apellidos</span>"
                + "                   <input type='text' id='apellido' class='form-control' onkeyup='upperCaseTexto(this.id);' placeholder='Apellidos de Usuario' value='" + this.getApellido() + "'>"
                + "               </div>"
                + "               <div class='input-group'>"
                + "                   <span style='width: 18%;' class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='E-mail de Usuario' id=''>E-mail</span>"
                + "                   <input type='text' id='email' class='form-control' onkeyup='upperCaseTexto(this.id);' placeholder='jorgeos193@hotmail.com' value='" + this.getEmail() + "'>"
                + "               </div>"
                + "           </div> "
                + "       </div>"
                + "       <div class='modal-footer'>"
                + "           <button type='button' id='btnGuardar' onclick='guardarUsuario(" + this.getId() + "); return false;' class='btn btn-secondary float-xs-right'>"
                + "Guardar | <i class='fa fa-fw'></i>"
                + "</button>"
                + "       </div>"
                + "   </div>"
                + "</div>"
                + "</div>"
                + "  </div>";
        return result;
    }

    public String modalRolesUsuario() {
        String result = "";
        result += " <div class='modal fade' id='modalRolesUsuario' role='dialog'>"
                + " <div class='modal-dialog modal-lg' >"
                + " <div class='modal-content'>"
                + "     <div class='modal-header'>"
                + "         <button type='button' class='close' onclick='cerrarModalContenido();' data-dismiss='modal' aria-hidden='true'>&times;</button>"
                + "         <div id='ingresarUsuario' style='width: 100%; text-align: center;'><h4><b>Roles de Usuario</b></h4></div>"
                + "     </div>"
                + "     <div class='modal-body'>"
                + " <div id='accordion'>";
        for (Rol rol : this.getRoles()) {
            result += " <div class='card' style='height:10%;' >"
                    + "                        <div class='card-header' id='heading" + rol.getIdRol() + "' >"
                    + "                            <h5 class='mb-0' id='" + rol.getIdRol() + "' data-gestion='rol' onclick=" + rol.getFncClick() + ">"
                    + "                                <button class='btn btn-link collapsed'  data-toggle='collapse' data-target='#collapse" + rol.getIdRol() + "' aria-expanded='false' aria-controls='collapse" + rol.getIdRol() + "'>"
                    + rol.getDescRol()
                    + "                                </button>"
                    + "                            </h5>"
                    + "                        </div>"
                    + "                        <div id='collapse" + rol.getIdRol() + "' class='collapse' aria-labelledby='heading" + rol.getIdRol() + "' data-parent='#accordion'>"
                    + "                            <div class='card-body'>"
                    + selectEntidadesRol(rol.getEntidades(), "")
                    + "                            </div>"
                    + "                        </div>"
                    + "                    </div>";
        }
        result += "    </div></div>"
                + "       <div class='modal-footer'>"
                + "        <button type='button' id='btnGuardar' onclick='guardarRolesUsuario(" + this.getId() + "); return false;' class='btn btn-secondary float-xs-right' data-toggle='tooltip' data-placement='top'>"
                + "            Guardar |  <i class='fa '></i>"
                + "        </button>"
                + "       </div>"
                + "   </div>"
                + "</div>"
                + "</div>"
                + "  </div>";
        return result;
    }
//asignar

    public String selectEntidadesRol(List<EntidadUnidos> entidades, String disabled) {
        String result = "";
        result += "<select multiple id='rolesEntidad' " + disabled + " class='roles selectpicker form-control'>";
        result = entidades.stream().map((entidadComun) -> "<option data-subtext='(" + entidadComun.getTipo() + ")' value='" + entidadComun.getId() + "' " + entidadComun.getFncClick() + ">" + entidadComun.getNombre() + "</option>").reduce(result, String::concat);
        result += "</select>";

        return result;

    }

    public String saveRolesUsuario() {
        String result = "";
        for (int i = 0; i < this.getRoles().size(); i++) {
            Rol rol = this.getRoles().get(i);
            result += "               <div class='input-group'>"
                    + "                   <span class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='E-mail de Usuario' id=''><i class='fa fa-user' aria-hidden='true'></i></span>"
                    + "                   <input type='text' readonly id='rol' class='form-control' placeholder='Rol de Usuario' value='" + rol.getDescRol() + "'>"
                    + "                   <span class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='Asignar Rol' id=''><input type='checkbox' class='rolesUsuario' " + rol.getCheck() + " id='" + rol.getIdRol() + "'></span>"
                    + "               </div>";
        }
        return result;
    }

}
