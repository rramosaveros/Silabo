/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.roles.iu;

import dda.silabo.opciones.comunes.Opcion;
import dda.silabo.roles.comunes.Rol;
import dda.silabo.usuarios.comunes.Usuario;
import dda.silabo.usuarios.iu.UsuarioIU;

/**
 *
 * @author Jorge Zaruma
 */
public class RolIU extends Rol {

    public String toHTMLRolOpciones() {
        String result = "";
        result += " <div class='modal fade' id='modalRolOpciones' role='dialog'>"
                + " <div class='modal-dialog'>"
                + " <div class='modal-content'>"
                + "     <div class='modal-header'>"
                + "         <button type='button' class='close' onclick='cerrarModalContenido();' data-dismiss='modal' aria-hidden='true'>&times;</button>"
                + "         <div id='ingresarUsuario' style='width: 100%; text-align: center;'><h4><b>Opciones para " + this.getDescRol() + "</b></h4></div>"
                + "     </div>"
                + "     <div class='modal-body'>";
        result += "<form>"
                + "<div class='form-group row'>"
                // + "<label id='seccionEtiqueta' for='example-text-input' class='col-xs-2 form-control-label'></label>"
                + "<div id='seccionContenido' class='col-xs-12'>"
                + "<div class='float-xs-right pr-0 unidad-nueva'>"
                + "</div>"
                + "<div id='ContenidoGestion'>";
        for (int i = 0; i < this.getOpciones().size(); i++) {
            result += "<div class='unidad'>"
                    + "<div class='input-group'>"
                    //+ "<span class='input-group-addon'>Opci&oacute;n: </span>"
                    + "<input type='text' class='form-control' readonly placeholder='Descripci&oacute;n de Opci&oacute;n' value='" + this.getOpciones().get(i).getDescRol() + "'/>";
            result += "<span class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='Habilitar Opcion' id=''>"
                    + "<input type='checkbox' class='Opciones' " + this.getOpciones().get(i).getCheck() + " id='" + this.getOpciones().get(i).getIdOpcion() + "'>"
                    + "</span>";
            result += "</div></div> ";
        }
        result += "</div>"
                + "</div>"
                + "</div>"
                + "</form>"
                + "       </div>"
                + "       <div class='modal-footer'>"
                + "           <button type='button' id='btnGuardar' class='btn btn-secondary float-xs-right' onclick='guardarRolOpciones(" + this.getIdRol() + "); return false;'>"
                + "            Guardar |  <i class='fa fa-fw'></i>"
                + "</button>"
                + "       </div>"
                + "   </div>"
                + "</div>"
                + "</div>"
                + "  </div>";

        return result;
    }

    public String contenidoInfoHTML(String opc, String codMateria, String codCarrera, String codPeriodo) {
        String result = "";
        Opcion opcion = null;
        if (!opc.equals("reportes")) {
            opcion = this.getOpciones().stream().filter(op -> op.getIdOpcion() == 1).findFirst().orElse(null);
            if (opcion != null) {
                if (opcion.getCheck() != null) {
                    result += "<a id='1' onclick='' class='fa cambios' aria-hidden='true' data-toggle='tooltip' data-placement='bottom' title=''></a>";
                }
            }
            opcion = this.getOpciones().stream().filter(op -> op.getIdOpcion() == 2).findFirst().orElse(null);
            if (opcion != null) {
                if (opcion.getCheck() != null) {
                    result += "&nbsp;&nbsp;|&nbsp;&nbsp;<a id='2' onclick='subirSilabo(); return false;' class='nav-link text-white fa fa-cloud-upload'  data-toggle='tooltip' data-placement='bottom' title='Subir PDF'></a>";
                }
            }
            opcion = this.getOpciones().stream().filter(op -> op.getIdOpcion() == 3).findFirst().orElse(null);
            if (opcion != null) {
                if (opcion.getCheck() != null) {
                    result += "&nbsp;&nbsp;|&nbsp;&nbsp;<a id='3' onclick=descargarSilabo('" + codCarrera + "','" + codMateria + "','" + codPeriodo + "'); class='nav-link text-white fa fa-cloud-download'  data-toggle='tooltip' data-placement='bottom' title='Descargar PDF'></a>";
                }
            }
        }
//        opcion = this.getOpciones().stream().filter(op -> op.getIdOpcion() == 4).findFirst().orElse(null);
//        if (opcion != null) {
//            if (opcion.getCheck() != null) {
        result += "&nbsp;&nbsp;|&nbsp;&nbsp;<a id='lnkAyuda'  onmouseover='mostrarAyudaGeneral(this);' class='nav-link text-white fa fa-question' tabindex='0' role='button' data-trigger='focus' data-placement='left'></a>";
//            }
//        }
        return result;
    }

    public String toHTMLUsuariosAsignacion(String contenidoUsuarios) {
        String result = "";

        result += " <div class='modal fade' id='modalRolUsuarios' role='dialog' 'display: block;'>"
                + " <div class='modal-dialog'>"
                + " <div class='modal-content'>"
                + "     <div class='modal-header'>"
                + "         <button type='button' class='close' onclick='cerrarModalContenido();' data-dismiss='modal' aria-hidden='true'>&times;</button>"
                + " <div id='ingresarUsuario' style='width: 100%; text-align: center;'><h4><b>Usuarios (" + this.getDescRol() + ")</b></h4></div>"
                + "     </div>"
                + "     <div class='modal-body'>"
                + contenidoUsuarios;
        for (int i = 0; i < this.getUsuarios().size(); i++) {
            result += "<div class='unidad' id='asignarUsuario'>"
                    //                    + usuariosC
                    + "<div class='input-group'>"
                    + "<input type='text' id='" + this.getUsuarios().get(i).getId() + "' class='form-control Usuarios' placeholder='Nombre de Usuario' value='" + this.getUsuarios().get(i).getNombre() + " " + this.getUsuarios().get(i).getApellido() + "'/>"
                    + "<span class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='Quitar Usuario'> <i class='fa fa-minus-circle'></i></span>"
                    + "</div>"
                    + "</div>";
        }
        result += "       </div>"
                + "       <div class='modal-footer'>"
                + "           <button type='button' onclick='guardarAsignacion(); return false;' class='btn btn-secondary'>Guardar</button>"
                + "           <button type='button' class='btn btn-default' onclick='cerrarModalContenido();' data-dismiss='modal'>Cerrar</button>"
                + "       </div>"
                + "   </div>"
                + "</div>"
                + "</div>"
                + "  </div>";

        result += "<script src='Silabo/Administrador/RolesUsuariosOpcionesGestion/UsuariosOpcionesJS.js' type='text/javascript'></script>";

        return result;
    }

}
