/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.reportes.iu;

import com.google.gson.Gson;
import dda.silabo.autentificarse.iu.LoginIU;
import dda.silabo.clases.unidos.CarreraUnidos;
import dda.silabo.clases.unidos.EntidadUnidos;
import dda.silabo.clases.unidos.EscuelaUnidos;
import dda.silabo.clases.unidos.FacultadUnidos;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class EntidadIU extends EntidadUnidos {

    LoginIU logueo;

    public String nombreDocenteToHTML(LoginIU logueo) {
        this.logueo = logueo;
        String result = "";
//        result += logueo.getNombres();
        result += logueo.getNombres().split(" ")[0].trim() +" "+ logueo.getApellidos().split(" ")[0].trim();
        return result;
    }

    public String getPrimeraCarrera() {
        String result = "";
        result = this.getFacultades().get(0).getEscuelas().get(0).getCarreras().get(0).getCodCarrera();
        return result;
    }

    public String getPrimerObjetoCarrera(String codFacultad, String codCarrera) {
        String result = "";
        FacultadUnidos facultadComun = null;
        if (codFacultad.equals("todos")) {
            facultadComun = this.getFacultades().stream().filter(fa -> fa != null).findFirst().orElse(null);
        } else {
            facultadComun = this.getFacultades().stream().filter(fa -> fa.getCodFacultad().equals(codFacultad)).findFirst().orElse(null);
        }
        CarreraUnidos carreraComun = null;
        if (codCarrera.equals("todos")) {
            for (EscuelaUnidos escuelaComun : facultadComun.getEscuelas()) {
                carreraComun = escuelaComun.getCarreras().stream().filter(ca -> ca != null).findFirst().orElse(null);
                if (carreraComun != null) {
                    break;
                }
            }

        } else {
            for (EscuelaUnidos escuelaComun : facultadComun.getEscuelas()) {
                carreraComun = escuelaComun.getCarreras().stream().filter(ca -> ca.getCodCarrera().equals(codCarrera)).findFirst().orElse(null);
                if (carreraComun != null) {
                    break;
                }
            }
        }
        Gson G = new Gson();
        result = G.toJson(carreraComun);
        return result;
    }

    public String toHTMLfacultades(String codFacultad) {
        String result = "";
        int i = 0;
        result += "<select class='selectpicker dda-select' id='slcFacultad'  onchange='cambioFacultad();'>";

        List<FacultadUnidos> facultadComunes = this.getFacultades();
        for (FacultadUnidos facultadComun : facultadComunes) {
            String selected = "";
            if (i == 0 && codFacultad.equals("todos")) {
                selected = "selected";
                i++;
            } else if (codFacultad.equals(facultadComun.getCodFacultad())) {
                selected = "selected";
            }
            result += "<option data-subtext='(" + facultadComun.getCodFacultad() + ")' value='" + facultadComun.getCodFacultad() + "' " + selected + ">" + facultadComun.getNombre() + "</option>";
        }
        result += "</select>";
        return result;
    }

    public String toHTMLcarrerasF(String codFacultad, String codCarrera) {
        String result = "";
        result += "&nbsp;&nbsp;|&nbsp;&nbsp;<select class='selectpicker dda-select' id='slcCarrera'  onchange='cambioCarrera();'>";
        if (codFacultad.equals("todos")) {
            codFacultad = this.getFacultades().get(0).getCodFacultad();
        }
        if (codCarrera.equals("todos")) {
            codCarrera = this.getFacultades().get(0).getEscuelas().get(0).getCarreras().get(0).getCodCarrera();
        }
        List<FacultadUnidos> facultadComunes = this.getFacultades();
        for (FacultadUnidos facultadComun : facultadComunes) {
            if (facultadComun.getCodFacultad().equals(codFacultad)) {
                List<EscuelaUnidos> escuelaComunes = facultadComun.getEscuelas();
                for (EscuelaUnidos escuelaComun : escuelaComunes) {
                    List<CarreraUnidos> carreraComunes = escuelaComun.getCarreras();
                    for (CarreraUnidos carreraComun : carreraComunes) {
                        String selected = "";
                        if (carreraComun.getCodCarrera().equals(codCarrera)) {
                            selected = "selected";
                        }
                        result += "<option data-subtext='(" + escuelaComun.getCodEscuela() + ")' value='" + carreraComun.getCodCarrera() + "' " + selected + ">" + carreraComun.getNombre() + "</option>";
                    }
                }
                break;
            }
        }
        result += "</select>";
        return result;
    }

    public String docenteInfoToHTML() {
        String result = "";
        result += "Ing. " + logueo.getNombres() + "<br>"
                + "C&eacute;dula: " + logueo.getCedula() + "<br>"
                + "e-mail: " + logueo.getEmail();
        return result;
    }

    public String modalRolUsuarios() {
        String result = "";
        result += " <div class='modal fade' id='modalRolUsuarios' role='dialog'  style='display: block;'>"
                + " <div class='modal-dialog modal-lg'>"
                + " <div class='modal-content'>"
                + "     <div class='modal-header'>"
                + "         <button type='button' class='close' onclick='cerrarModalContenido();' data-dismiss='modal' aria-hidden='true'>&times;</button>"
                + "         <div id='ingresarUsuario' style='width: 100%; text-align: center;'><h4><b>Roles de Usuario</b></h4></div>"
                + "     </div>"
                + "     <div class='modal-body'>"
                + " <div id='accordion'>";
        for (FacultadUnidos facultadUnidos : this.getFacultades()) {
            result += " <div class='card' style='height:10%;' >"
                    + "                        <div class='card-header' id='heading" + facultadUnidos.getCodFacultad() + "' >"
                    + "                            <h5 class='mb-0' id='" + facultadUnidos.getCodFacultad() + "' onclick=" + facultadUnidos.getFncClick() + ">"
                    + "                                <button class='btn btn-link collapsed'  data-toggle='collapse' data-target='#collapse" + facultadUnidos.getCodFacultad() + "' aria-expanded='false' aria-controls='collapse" + facultadUnidos.getCodFacultad() + "'>"
                    + facultadUnidos.getNombre()
                    + "                                </button>"
                    + "                            </h5>"
                    + "                        </div>"
                    + "                        <div id='collapse" + facultadUnidos.getCodFacultad() + "' class='collapse' aria-labelledby='heading" + facultadUnidos.getCodFacultad() + "' data-parent='#accordion'>"
                    + "                            <div class='card-body'>"
                    + "Cargando Contenido <i class='fa fa-spinner fa-pulse fa-fw fa-1x' style='color: #00417F;' aria-hidden='true'></i>"
                    + "                            </div>"
                    + "                        </div>"
                    + "                    </div>";
        }
        result += "    </div></div>"
                + "       <div class='modal-footer'>"
                + "       </div>"
                + "   </div>"
                + "</div>"
                + "</div>"
                + "  </div>";
        return result;
    }

    public String contenidoNivel() {
        String result = "";
        try {
            for (FacultadUnidos f : this.getFacultades()) {
                for (EscuelaUnidos e : f.getEscuelas()) {
                    for (CarreraUnidos c : e.getCarreras()) {
                        if (c.getFncClick() == null) {
                            c.setFncClick("nuevo();");
                        }
                        result += " <div style='margin-left: 10%;'>"
                                + "<div>"
                                + "<div class='input-group' data-gestion='rol' id='" + c.getCodCarrera() + "' onclick=" + c.getFncClick() + " >"
                                + "<nav aria-label='breadcrumb'>"
                                + "  <ol class='breadcrumb'>"
                                + "    <li class='breadcrumb-item active' aria-current='page'>" + c.getNombre() + "</li>"
                                + "</ol>"
                                + "</nav>"
                                + "</div>"
                                + "</div>"
                                + "<div id='collapse" + c.getCodCarrera() + "' style='margin-left: 10%;'>"
                                + "     <div class='input-group card-body'>"
                                + "    </div>"
                                + "</div>"
                                + "</div>";
                    }
                }
            }
        } catch (Exception e) {
        }
        return result;
    }

    public String contenidoNivelEntidades() {
        String result = "";
        try {
            for (FacultadUnidos f : this.getFacultades()) {
                if (f.getFncClick() == null) {
                    f.setFncClick("nuevo();");
                }
                result += " <div style='margin-left: 10%;'>"
                        + "<div>"
                        + "<div class='input-group' data-gestion='usuario' id='" + f.getCodFacultad() + "' onclick=" + f.getFncClick() + " >"
                        + "<nav aria-label='breadcrumb'>"
                        + "  <ol class='breadcrumb'>"
                        + "    <li class='breadcrumb-item active' aria-current='page'>" + f.getNombre() + "</li>"
                        + "</ol>"
                        + "</nav>"
                        + "</div>"
                        + "</div>"
                        + "<div id='collapse" + f.getCodFacultad() + "' style='margin-left: 10%;'>"
                        + "     <div class='input-group card-body'>"
                        + "    </div>"
                        + "</div>"
                        + "</div>";
            }
        } catch (Exception e) {
        }
        return result;
    }

    public String contenidoEntidades() {
        String result = "";
        try {
            result += "<div style='margin-left: 10%;'>"
                    + "                                       <div class='input-group'>"
                    + "<nav aria-label='breadcrumb'>"
                    + "  <ol class='breadcrumb'>"
                    + "<select onchange='guardarAsignacionEntidad(this);' class='form-control selectpicker form-control-lg' id='contenidoEntidades'>";
            result += "<option selected='true' disabled='disabled'>SELECCIONE UNA OPCIÓN</option>";
            for (FacultadUnidos d : this.getFacultades()) {
                result += "<option data-idEntidad='" + d.getIdFacultad() + "' value='" + d.getCodFacultad() + "'>" + d.getNombre() + "</option>";
            }
            result += "  </select>"
                    + "</ol>"
                    + "</nav>"
                    + "</div>"
                    + "</div>";
        } catch (Exception e) {
        }
        return result;
    }

    public String contenidoEntidadesCarreras() {
        String result = "";
        try {
            result += "<div style='margin-left: 10%;'>"
                    + "                                       <div class='input-group'>"
                    + "<nav aria-label='breadcrumb'>"
                    + "  <ol class='breadcrumb'>"
                    + "<select onchange='guardarAsignacionEntidad(this);' data-show-subtext='true' data-live-search='true' class='form-control selectpicker form-control-lg' id='contenidoEntidades'>";
            int cont = 0;
            for (FacultadUnidos d : this.getFacultades()) {
                for (EscuelaUnidos e : d.getEscuelas()) {
                    for (CarreraUnidos c : e.getCarreras()) {
                        if (c.getSelected() != null) {
                            cont++;
                        }
                        result += "<option  " + c.getSelected() + " data-subtext='" + d.getCodFacultad() + " - " + e.getCodEscuela() + "' data-idEntidad='" + c.getIdCarrera() + "' value='" + c.getCodCarrera() + "'>" + c.getNombre() + "</option>";
                    }
                }
            }
            if (cont == 0) {
                result += "<option selected='true' disabled='disabled'>SELECCIONE UNA OPCIÓN</option>";
            }
            result += "  </select>"
                    + "</ol>"
                    + "</nav>"
                    + "</div>"
                    + "</div>";
        } catch (Exception e) {
        }
        return result;
    }
}
