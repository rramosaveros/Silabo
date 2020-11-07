/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.menulateral;

import dda.silabo.menulateral.comunes.MenuLateral;
import dda.silabo.menulateral.comunes.SeccionSilabo;
import dda.silabo.menulateral.comunes.Unidad;

/**
 *
 * @author Adry
 */
public class MenuLateralIU extends MenuLateral {

    public String toHTMLDocente(Integer idUnidad, String rolActivo) {

        String result = "";
        if (this.getSecciones().size() > 1) {
            result += " <div>"
                    //<!-- Menú Lateral-->
                    + " <ul id='treeview'>"
                    + "    <li>"
                    + "    <a class='nav-link'  data-tipo='seccion' id='DatosGenerales' onclick='clicContenido(this)'>"
                    + "           1. Datos generales"
                    + "        </a> "
                    + "     </li>";
            if (idUnidad > 0) {
                result += " <li data-expanded='true'>";
            } else {
                result += " <li>";
            }
            if (this.getNumUnidades() != 0) {
                result += "<i class='" + this.getEstado() + "' aria-hidden='true'></i>";
            }
            result += " 2. Estructura y Desarrollo de la asignatura";
            result += "   <ul>";
            if (this.getNumUnidades() != 0) {
                for (Unidad u : this.getUnidades()) {
                    if (idUnidad == u.getIdUnidad()) {
                        result += "<li data-expanded='true'>";
                    } else {
                        result += "<li>";
                    }
                    result += "<i class='" + u.getEstado() + "' aria-hidden='true'></i>";
                    result += "      <a class='nav-link' data-idTipo='" + 1 + "'  id='informacionUnidad' data-id='informacionUnidad' data-titulo='2. Estructura y Desarrollo: " + u.getDescripcion() + "' onclick='clicContenido(this," + (u.getIdUnidad()) + ")'>";
                    result += " 2." + u.getIdUnidad() + " . " + u.getDescripcion();
                    result += "    </a>";
                    result += "  <ul>";
                    for (SeccionSilabo seccionSilabo : u.getSubsecciones()) {
                        result += "    <li>"
                                + "      <a class='nav-link' data-idTipo='" + seccionSilabo.getIdDescripcion() + "' id='" + seccionSilabo.getTipoSeccion() + "' data-id='informacionUnidad' data-titulo='2. Estructura y Desarrollo: " + u.getDescripcion() + "'  onclick='clicContenido(this," + (u.getIdUnidad()) + ")'>";
                        result += "<i class='" + seccionSilabo.getEstado() + "' aria-hidden='true' id='" + seccionSilabo.getIdDescripcion() + "'></i>";
                        result += seccionSilabo.getDescripcion()
                                + "    </a>"
                                + "  </li>";

                    }
                    result += " </ul>"
                            + " </li>";
                }
                result += " <li>"
                        + " <a class='nav-link' id='Exception' onclick='gestionarUnidades();'>";
                result += " Gestionar Unidades"
                        + " </a>"
                        + " </li>";
            } else {
                result += " <li>"
                        + " <a class='nav-link' id='Exception'>";
                result += " Contenido no Disponible"
                        + " </a>"
                        + " </li>";
            }

            result += " </ul>"
                    + " </li>";
            if (idUnidad == -1) {
                result += " <li data-expanded='true'>";
            } else {
                result += " <li>";
            }
            int seccionTres = estadoSeccionTRES(rolActivo);
            int rol = 0;
            if (!rolActivo.equals("Doc")) {
                rol = estadoSeccionTRESAdm();
            }
            if (seccionTres != 0 && seccionTres < 3 && rolActivo.equals("Doc")) {
                result += " <i class='fa fa-exclamation-triangle tag-warning'></i>";
            } else if ((seccionTres == 3 || rol == 3)) {
                rol = 1;
                result += "<i class='fa fa-check' aria-hidden='true'></i>";
            } else if (seccionTres != 0 && !rolActivo.equals("Doc")) {
                result += "<i class='fa fa-question' aria-hidden='true'></i>";
            }
            result += " 3. Escenarios de Aprendizaje"
                    + " <ul>"
                    + " <li>"
                    + "  <a class='nav-link' id='Real' data-tipo='seccion' data-id='Escenarios' data-titulo='3. Escenarios de Aprendizaje' data-idTipo='1' onclick='clicContenido(this)'>";

            for (int i = 0; i < this.getSecciones().size(); i++) {
                SeccionSilabo secciones = this.getSecciones().get(i);
                if (secciones.getTipoSeccion().equals("Real") && secciones.getEstado().equals("Corregir") && rolActivo.equals("Doc")) {
                    result += " <i class='fa fa-exclamation-triangle tag-warning' id='1'></i>";
                }
                if (secciones.getTipoSeccion().equals("Real") && secciones.getEstado().equals("Corregido") && !rolActivo.equals("Doc")) {
                    result += "<i class='fa fa-question' aria-hidden='true' id='1'></i>";
                }
                if (secciones.getTipoSeccion().equals("Real") && secciones.getEstado().equals("Aprobado")) {
                    result += "<i class='fa fa-check' aria-hidden='true' id='1'></i>";
                }
            }

            result += " 3.1. Reales"
                    + " </a>"
                    + " </li>"
                    + " <li>"
                    + " <a class='nav-link' id='Virtual' data-id='Escenarios' data-tipo='seccion' data-idTipo='2' data-titulo='3. Escenarios de Aprendizaje' onclick='clicContenido(this)'>";

            for (int i = 0; i < this.getSecciones().size(); i++) {
                SeccionSilabo secciones = this.getSecciones().get(i);
                if (secciones.getTipoSeccion().equals("Virtual") && secciones.getEstado().equals("Corregir") && rolActivo.equals("Doc")) {
                    result += " <i class='fa fa-exclamation-triangle tag-warning' id='2'></i>";
                }
                if (secciones.getTipoSeccion().equals("Virtual") && secciones.getEstado().equals("Corregido") && !rolActivo.equals("Doc")) {
                    result += "<i class='fa fa-question' aria-hidden='true' id='2'></i>";
                }
                if (secciones.getTipoSeccion().equals("Virtual") && secciones.getEstado().equals("Aprobado")) {
                    result += "<i class='fa fa-check' aria-hidden='true' id='2'></i>";
                }
            }

            result += " 3.2. Virtuales"
                    + "  </a>"
                    + "  </li>"
                    + "  <li>"
                    + " <a class='nav-link' id='Aulico' data-id='Escenarios' data-tipo='seccion' data-idTipo='3' data-titulo='3. Escenarios de Aprendizaje' onclick='clicContenido(this);'>";

            for (int i = 0; i < this.getSecciones().size(); i++) {
                SeccionSilabo secciones = this.getSecciones().get(i);
                if (secciones.getTipoSeccion().equals("Aulico") && secciones.getEstado().equals("Corregir") && rolActivo.equals("Doc")) {
                    result += " <i class='fa fa-exclamation-triangle tag-warning' id='3' ></i>";
                }
                if (secciones.getTipoSeccion().equals("Aulico") && secciones.getEstado().equals("Corregido") && !rolActivo.equals("Doc")) {
                    result += "<i class='fa fa-question' aria-hidden='true' id='3' ></i>";
                }
                if (secciones.getTipoSeccion().equals("Aulico") && secciones.getEstado().equals("Aprobado")) {
                    result += "<i class='fa fa-check' aria-hidden='true' id='3' ></i>";
                }
            }

            result += " 3.3. A&uacute;lico"
                    + " </a>"
                    + " </li>"
                    + " </ul>"
                    + " </li>"
                    + " <li>"
                    + " <a class='nav-link' id='Criterios' data-id='Criterios' data-tipo='seccion' data-idTipo='4' data-titulo='4. Criterios Normativos para la Evaluaci&oacute;n'  onclick='clicContenido(this);'>";

            for (int i = 0; i < this.getSecciones().size(); i++) {
                SeccionSilabo secciones = this.getSecciones().get(i);
                if (secciones.getTipoSeccion().equals("Criterios") && secciones.getEstado().equals("Corregir") && rolActivo.equals("Doc")) {
                    result += " <i class='fa fa-exclamation-triangle tag-warning' id='4'></i>";
                }
                if (secciones.getTipoSeccion().equals("Criterios") && secciones.getEstado().equals("Corregido") && !rolActivo.equals("Doc")) {
                    result += "<i class='fa fa-question' aria-hidden='true' id='4'></i>";
                }
                if (secciones.getTipoSeccion().equals("Criterios") && secciones.getEstado().equals("Aprobado")) {
                    result += "<i class='fa fa-check' aria-hidden='true' id='4'></i>";
                }
            }

            result += " 4. Criterios Normativos para la Evaluaci&oacute;n"
                    + " </a>"
                    + " </li>"
                    + " <li>"
                    + " <a class='nav-link' id='Bibliografias' data-id='Bibliografias'  data-tipo='seccion'  data-idTipo='5'  data-titulo='5. Bibliograf&iacute;a'onclick='clicContenido(this);'>";

            for (int i = 0; i < this.getSecciones().size(); i++) {
                SeccionSilabo secciones = this.getSecciones().get(i);
                if (secciones.getTipoSeccion().equals("Bibliografias") && secciones.getEstado().equals("Corregir") && rolActivo.equals("Doc")) {
                    result += " <i class='fa fa-exclamation-triangle tag-warning' id='5'></i>";
                }
                if (secciones.getTipoSeccion().equals("Bibliografias") && secciones.getEstado().equals("Corregido") && !rolActivo.equals("Doc")) {
                    result += "<i class='fa fa-question' aria-hidden='true' id='5'></i>";
                }
                if (secciones.getTipoSeccion().equals("Bibliografias") && secciones.getEstado().equals("Aprobado")) {
                    result += "<i class='fa fa-check' aria-hidden='true' id='5'></i>";
                }
            }

            result += " 5. Bibliograf&iacute;a"
                    + " </a>"
                    + " </li> "
                    + " <li>  "
                    + " <a class='nav-link' id='Docente' data-idTipo='6' data-tipo='seccion' onclick='clicContenido(this);'>"
                    + " 6. Datos del Profesor"
                    + "     </a>"
                    + "  </li>"
                    + " </ul>"
                    + " </div>"
                    + "<input type='hidden' id='RolUsuario' value='" + rolActivo + "'>";
        } else {
            result += " <div>"
                    //<!-- Menú Lateral-->
                    + " <ul id='treeview'>"
                    + "    <li>"
                    + "    <a class='nav-link' id='DatosGenerales' onclick=''>"
                    + "           1. " + this.getSecciones().get(0).getTipoSeccion()
                    + "        </a> "
                    + "     </li>"
                    + "</ul></div>";
        }
        return result;
    }

    private Integer estadoUnidad(int idUnidad, String rolActivo) {
        Integer estadoUnidad = 0;
        if (rolActivo.equals("Doc")) {
            int contCorregir = 0, contAprobado = 0;
            for (int sbs = 0; sbs < this.getSubsecciones().size(); sbs++) {
                if (this.getSubsecciones().get(sbs).getIdUnidad() == idUnidad && this.getSubsecciones().get(sbs).getEstado().equals("Corregir")) {
                    contCorregir++;
                }
                if (this.getSubsecciones().get(sbs).getIdUnidad() == idUnidad && this.getSubsecciones().get(sbs).getEstado().equals("Aprobado")) {
                    contAprobado++;
                }
            }
            if (contCorregir != 0) {
                estadoUnidad = contCorregir;
            }
            if (contAprobado == 6 && contCorregir == 0) {
                estadoUnidad = contAprobado;
            }
        } else {
            int contCorregido = 0;
            for (int sbs = 0; sbs < this.getSubsecciones().size(); sbs++) {
                if (this.getSubsecciones().get(sbs).getIdUnidad() == idUnidad && this.getSubsecciones().get(sbs).getEstado().equals("Corregido")) {
                    contCorregido++;
                }
            }
            if (contCorregido != 0) {
                estadoUnidad = contCorregido;
            }
        }
        return estadoUnidad;

    }

    private Integer estadoSeccionDOS(String rolActivo) {
        Integer estadoSeccionDOS = 0;
        if (rolActivo.equals("Doc")) {
            int contCorregir = 0, contAprobado = 0;
            for (int sbs = 0; sbs < this.getSubsecciones().size(); sbs++) {
                if (this.getSubsecciones().get(sbs).getEstado().equals("Corregir")) {
                    contCorregir++;
                }
                if (this.getSubsecciones().get(sbs).getEstado().equals("Aprobado")) {
                    contAprobado++;
                }
            }
            if (contCorregir != 0) {
                estadoSeccionDOS = contCorregir;
            }
            if (contAprobado == (this.getNumUnidades() * 6) && contCorregir == 0) {
                estadoSeccionDOS = contAprobado;
            }
        } else {
            int contCorregido = 0;
            for (int sbs = 0; sbs < this.getSubsecciones().size(); sbs++) {
                if (this.getSubsecciones().get(sbs).getEstado().equals("Corregido")) {
                    contCorregido++;
                }
            }
            if (contCorregido != 0) {
                estadoSeccionDOS = contCorregido;
            }
        }
        return estadoSeccionDOS;
    }

    private Integer estadoSeccionTRES(String rolActivo) {
        Integer estadoEscenarios = 0;
        if (rolActivo.equals("Doc")) {
            int contCorregir = 0, contAprobado = 0;
            for (int sbs = 0; sbs < this.getSecciones().size(); sbs++) {
                if (this.getSecciones().get(sbs).getEstado().equals("Corregir") && ((this.getSecciones().get(sbs).getTipoSeccion().equals("Real") || this.getSecciones().get(sbs).getTipoSeccion().equals("Virtual") || this.getSecciones().get(sbs).getTipoSeccion().equals("Aulico")))) {
                    contCorregir++;
                }
                if (this.getSecciones().get(sbs).getEstado().equals("Aprobado") && ((this.getSecciones().get(sbs).getTipoSeccion().equals("Real") || this.getSecciones().get(sbs).getTipoSeccion().equals("Virtual") || this.getSecciones().get(sbs).getTipoSeccion().equals("Aulico")))) {
                    contAprobado++;
                }
            }
            if (contCorregir != 0) {
                estadoEscenarios = contCorregir;
            }
            if (contAprobado == 3 && contCorregir == 0) {
                estadoEscenarios = contAprobado;
            }
        } else {
            int contCorregido = 0;
            for (int sbs = 0; sbs < this.getSecciones().size(); sbs++) {
                if (this.getSecciones().get(sbs).getEstado().equals("Corregido") && ((this.getSecciones().get(sbs).getTipoSeccion().equals("Real") || this.getSecciones().get(sbs).getTipoSeccion().equals("Virtual") || this.getSecciones().get(sbs).getTipoSeccion().equals("Aulico")))) {
                    contCorregido++;
                }
            }
            if (contCorregido != 0) {
                estadoEscenarios = contCorregido;
            }
        }
        return estadoEscenarios;
    }

    private Integer estadoSeccionTRESAdm() {
        Integer estadoEscenarios = 0;
        int contCorregir = 0, contAprobado = 0;
        for (int sbs = 0; sbs < this.getSecciones().size(); sbs++) {
            if (this.getSecciones().get(sbs).getEstado().equals("Corregir") && ((this.getSecciones().get(sbs).getTipoSeccion().equals("Real") || this.getSecciones().get(sbs).getTipoSeccion().equals("Virtual") || this.getSecciones().get(sbs).getTipoSeccion().equals("Aulico")))) {
                contCorregir++;
            }
            if (this.getSecciones().get(sbs).getEstado().equals("Aprobado") && ((this.getSecciones().get(sbs).getTipoSeccion().equals("Real") || this.getSecciones().get(sbs).getTipoSeccion().equals("Virtual") || this.getSecciones().get(sbs).getTipoSeccion().equals("Aulico")))) {
                contAprobado++;
            }
        }
        if (contCorregir != 0) {
            estadoEscenarios = contCorregir;
        }
        if (contAprobado == 3 && contCorregir == 0) {
            estadoEscenarios = contAprobado;
        }

        return estadoEscenarios;
    }
}
