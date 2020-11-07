/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.usuarios.iu;

import dda.silabo.usuarios.comunes.Usuario;
import dda.silabo.usuarios.comunes.Usuarios;

/**
 *
 * @author Jorge Zaruma
 */
public class UsuariosIU extends Usuarios {

    public String toHTML() {
        String result = "";
        result += "<form>"
                + "<div class='form-group row'>"
                + "<div id='seccionContenido' class='col-xs-12'>"
                + "<div class='float-xs-right pr-0 unidad-nueva'>"
                + "<button type='button' class='btn btn-secondary active' onclick='agregarNuevoUsuario(); return false;'>"
                + "<i class='fa fa-plus-circle'></i>"
                + "Nuevo Usuario"
                + " </button>"
                + "</div>"
                + "<div class='float-xs-left pr-0 unidad-nueva'>"
                + " <input type='text' class='search form-control' onkeyup='BusquedaUsuario()' placeholder='Introduzca nombre o CI'>"
                + "<span class='counter pull-right'>" + this.getUsuarios().size() + " Usuarios </span>"
                + "</div>"
                + "<br><br>"
                + "<div id='ContenidoGestion'>";

        int input = 1;
        if (this.getUsuarios().size() > 0) {
            for (Integer i = 0; i < this.getUsuarios().size(); i++) {
                Usuario usuario = this.getUsuarios().get(i);
                input = i;
                if (usuario.getTipo().equals("OASIS")) {
                    result += "<div class='unidad'>"
                            + "<div class='input-group'>"
                            + "<span style='width: 18%;'  class='input-group-addon usuariocedula'>" + usuario.getCedula() + "</span>"
                            + "<input type='text' class='form-control usuarioInfo' readonly placeholder='Nombre de Usuario' value='" + usuario.getNombre() + " " + usuario.getApellido() + "'/>";
                    result += "<span class='input-group-addon' data-toggle='tooltip' onclick='VisualizarRoles(this); return  false;' data-placement='bottom' title='Roles' id='" + usuario.getId() + "'>"
                            + "<i class='fa fa-list-alt' aria-hidden='true'> </i>"
                            + "</span>";
                    if (usuario.getEstado().equals("H")) {
                        result += "<span class='input-group-addon' data-toggle='tooltip' data-name='D' onclick='cambioUsuarioEstado(this); return  false;' data-placement='bottom' title='Deshabilitar Usuario' id='" + usuario.getId() + "'>"
                                + "<input type='checkbox' class='estadoUsuario' checked id='D'>"
                                + "</span>";
                    } else {
                        result += "<span class='input-group-addon' data-name='H' onclick='cambioUsuarioEstado(this); return  false;' data-toggle='tooltip' data-placement='bottom' title='Habilitar Usuario' id='" + usuario.getId() + "'>"
                                + "<input type='checkbox' class='estadoUsuario' id='H'>"
                                + "</span>";
                    }
                    result += "</div></div> ";

                } else {
                    result += "   <div class='unidad'>"
                            + "     <div class='input-group'>"
                            + "         <input type='hidden' id='tipo' value=''>"
                            + "         <span style='width: 18%;' class='input-group-addon usuariocedula'>" + usuario.getCedula() + "</span>"
                            + "         <input type='text' readonly class='form-control usuarioInfo' id='" + usuario.getCedula() + "' placeholder='Nombre de Usuario' value='" + usuario.getNombre() + " " + usuario.getApellido() + "'/>";
                    if (usuario.getEstado().equals("H")) {
                        result += "         <span class='input-group-addon' onclick='EliminarUsuario(this); return  false;' data-toggle='tooltip' data-placement='bottom' title='Eliminar Usuario' id='" + usuario.getId() + "'>"
                                + "             <i class='fa fa-minus-circle'></i>"
                                + "         </span>";
                    } else {
                        result += "<span class='input-group-addon' data-name='H' onclick='cambioUsuarioEstado(this); return  false;' data-toggle='tooltip' data-placement='bottom' title='Habilitar Usuario' id='" + usuario.getId() + "'>"
                                + "<input type='checkbox' class='estadoUsuario' id='H'>"
                                + "</span>";
                    }
                    result += "         <span class='input-group-addon' onclick='VisualizarRoles(this); return  false;' data-toggle='tooltip' data-placement='bottom' title='Roles' id='" + usuario.getId() + "'>"
                            + "             <i class='fa fa-list-alt' aria-hidden='true'></i>"
                            + "         </span>"
                            + "         <span class='input-group-addon' onclick='editarInformacion(this.id); return  false;' data-toggle='tooltip' data-placement='bottom' title='Editar Informaci&oacute;n' id='" + usuario.getId() + "'>"
                            + "             <i class='fa fa-pencil-square-o' aria-hidden='true'></i>"
                            + "         </span>"
                            + "     </div>"
                            + " </div>";
//                    
                }
            }
        }

        result += "</div>"
                + "<input type='hidden' id='groups' value='" + input + "'>"
                + "</div>"
                + "</div>"
                + "</form>";
        return result;
    }

    public String toHTMLUsuariosOpciones() {
        String result = "";

        result += "<div id='seccionContenido' class='col-xs-12'>"
                + "<select class='selectpicker dda-select' id='usuario' data-max-options='2'>";
        for (int d = 0; d < this.getUsuarios().size(); d++) {

            result += " <option data-icon='' data-subtext='' value='" + this.getUsuarios().get(d).getId() + "'>" + this.getUsuarios().get(d).getNombre() + " " + this.getUsuarios().get(d).getApellido() + "</option>";
        }
        result += " </select>"
                + "</div>"
                + " <div class='row'>"
                + "<div id='contenidoDinamicoScrollbar' class='col-xs-12'>"
                + "<div id='contenidoDinamico' class='mt-1'>"
                + "</div>"
                + "</div>"
                + "</div>";

        return result;
    }

    public String modalUsuarios() {
//        onkeypress='verificarUsuarioEnter(event)' 
        String result = "";
        result += "<div class='modal fade' id='modalUsuarios' role='dialog'>"
                + " <div class='modal-dialog'>"
                + "     <div class='modal-content'>"
                + "         <div class='modal-header'>"
                + "             <button type='button' class='close' onclick='cerrarModalContenido();' data-dismiss='modal' aria-hidden='true'>&times;</button>"
                + "             <h4 style='text-align:center;'><b>Agregar Usuario</b></h4>"
                + "         </div>"
                + "         <div class='modal-body'>"
                + "                                    <div id='ingresarUsuario' style='width: 100%; text-align: left;'>"
                + "                                        <div class='unidad'>"
                + "                                            <div class='input-group'>"
                + "                                                <span  style='width: 18%;' class='input-group-addon'>Cedula: </span>"
                + "                                                <input type='text'  onkeyup='return validarNumerosVerificar(event);' class='form-control' id='Cedula' maxlength='10' placeholder='0202519369' value=''/>"
                + "                                                <span class='input-group-addon' onclick='verificarUsuario(); return false;' data-toggle='tooltip' data-placement='bottom' title='Verificar Usuario' id=''>"
                + "                                                    <i class='fa fa-search' aria-hidden='true'></i>"
                + "                                                </span>"
                + "                                            </div><div id='informacionUsuario'></div></div> "
                + "                                    </div>"
                + "                                    <div class='modal-footer'>"
                + "<div id='accionesModal'>"
                + "</div>"
                + "                                    </div>"
                + "                                </div>"
                + "                            </div>"
                + "                        </div>"
                + "                    </div>";

        return result;
    }

}
