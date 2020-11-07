/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.importacion.iu;

import com.google.gson.Gson;
import dda.silabo.clases.unidos.AsignaturaUnidos;
import dda.silabo.clases.unidos.CarreraUnidos;
import dda.silabo.clases.unidos.EscuelaUnidos;
import dda.silabo.clases.unidos.FacultadUnidos;
import dda.silabo.importacion.comunes.ImportarSilabo;
import dda.silabo.importacion.comunes.Periodo;
import dda.silabo.silabo.comunes.Silabo;
import dda.silabo.reportes.iu.EntidadIU;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class ImportarSilaboIU extends ImportarSilabo {

    public String toHTMLPeriodos(Integer posCarrera) {
        List<Periodo> periodos = this.getCarreras().get(posCarrera).getPeriodos();
        String result = "";
        result += "<label><b>Períodos Académicos</b></label><br>";
        result += "<select class='selectpicker dda-select' id='modalPeriodos'>";
        for (int pP = 0; pP < periodos.size(); pP++) {
            String selected = (result.equals("")) ? "selected" : "";
            result += "<option data-subtext='(" + periodos.get(pP).getCodPeriodo() + ")' value='" + periodos.get(pP).getCodPeriodo() + "' " + selected + ">" + periodos.get(pP).getDescPeriodo() + "</option>";
        }
        result += "</select>";
        return result;
    }

    public String toHTMLCarreras(Integer posPer, EntidadIU entidadIU) {
        String result = "";
        List<CarreraUnidos> carreras = this.getCarreras();
        result += "<select class='selectpicker dda-select' id='modalCarreras'>";
        for (int ca = 0; ca < carreras.size(); ca++) {
            for (FacultadUnidos facultad : entidadIU.getFacultades()) {
                for (EscuelaUnidos escuela : facultad.getEscuelas()) {
                    for (CarreraUnidos carrera : escuela.getCarreras()) {
                        if (carrera.getCodCarrera().equals(carreras.get(ca).getCodCarrera())) {
                            String selected = (result.equals("")) ? "selected" : "";
                            result += "<option data-subtext='(" + carreras.get(ca).getCodCarrera() + ")' value='" + carreras.get(ca).getCodCarrera() + "' " + selected + ">" + carrera.getNombre() + "</option>";
                        }
                    }
                }
            }
        }
        result += "</select>";
        return result;
    }

    public String toHTMLAnteriores(Integer posCa) {
        List<AsignaturaUnidos> asignaturas = this.getCarreras().get(posCa).getAsignaturas();
        String result = "";
        result += " <select class='selectpicker dda-select' id='modalAsignaturas' onchange='cambioAsignaturas();' data-show-subtext='true'>";
        for (int as = 0; as < asignaturas.size(); as++) {
            String selected = (result.equals("")) ? "selected" : "";
            result += "<option " + selected + " data-subtext='(" + asignaturas.get(as).getCodArea() + ")' value='" + asignaturas.get(as).getCodMateria() + "'>" + asignaturas.get(as).getNombreMateria() + "</option>";
        }
        result += "</select>";
        return result;
    }

    public String toHTMLActual(EntidadIU uAcademica) {
        String result = "";
        ec.edu.espoch.academico.Periodo periodo = periodoActual();
        for (FacultadUnidos facultad : uAcademica.getFacultades()) {
            List<EscuelaUnidos> escuelas = facultad.getEscuelas();
            for (EscuelaUnidos escuela : escuelas) {
                List<CarreraUnidos> carreras = escuela.getCarreras();
                for (CarreraUnidos carrera : carreras) {
                    List<AsignaturaUnidos> asignaturas = carrera.getAsignaturas();
                    for (AsignaturaUnidos asignatura : asignaturas) {
                        String selected = (result.equals("")) ? "selected" : "";
//                        Gson G = new Gson();
//                        Silabo SilaboActual = new Silabo();
//                        SilaboActual.setCodCarrera(asignatura.getCodCarrera());
//                        SilaboActual.setCodMateria(asignatura.getCodAsignatura());
//                        SilaboActual.setPeriodo(periodo.getCodigo());
//                        if (silaboEstadoCargar(asignatura.getIdSilabo()).equals("Revision")) {
//                            String icon = "fa fa-question-circle";
//                        }
                        String json = "{'codCarrera':'" + asignatura.getCodCarrera() + "','codMateria':'" + asignatura.getCodMateria() + "','periodo':'" + periodo.getCodigo() + "'}";
                        result += "<option " + selected + " data-icon='fa-refresh' data-subtext='(" + asignatura.getCodCarrera() + ")' value='" + asignatura.getCodMateria() + "'>" + asignatura.getCodCarrera() + "&nbsp;|&nbsp;&nbsp;" + asignatura.getNombreMateria() + "</option>";
                    }
                }
            }
        }
        return result;
    }

    public String getAnteriorSilabo(Integer posPer, Integer posCa, String codMateria) {
        List<AsignaturaUnidos> asignaturas = this.getCarreras().get(posCa).getAsignaturas();
        Gson G = new Gson();
        String result = "";
        for (AsignaturaUnidos asignatura : asignaturas) {
            if (asignatura.getCodMateria().equals(codMateria)) {
                Silabo SilaboAnterior = new Silabo();
                SilaboAnterior.setCodCarrera(asignatura.getCodCarrera());
                SilaboAnterior.setCodMateria(asignatura.getCodMateria());
                result = G.toJson(SilaboAnterior);
            }
        }
        return result;
    }

    public String getActualSilabo(EntidadIU uAcademica, String codMateria) {
        String result = "";
        Gson G = new Gson();
        ec.edu.espoch.academico.Periodo periodo = periodoActual();
        for (FacultadUnidos facultad : uAcademica.getFacultades()) {
            List<EscuelaUnidos> escuelas = facultad.getEscuelas();
            for (EscuelaUnidos escuela : escuelas) {
                List<CarreraUnidos> carreras = escuela.getCarreras();
                for (CarreraUnidos carrera : carreras) {
                    List<AsignaturaUnidos> asignaturas = carrera.getAsignaturas();
                    for (AsignaturaUnidos asignatura : asignaturas) {
                        if (asignatura.getCodMateria().equals(codMateria)) {
                            Silabo SilaboActual = new Silabo();
                            SilaboActual.setCodCarrera(asignatura.getCodCarrera());
                            SilaboActual.setCodMateria(asignatura.getCodMateria());
                            SilaboActual.setPeriodo(periodo.getCodigo());
                            result = G.toJson(SilaboActual);
                        }
                    }
                }
            }
        }
        return result;
    }

    private static String silaboEstadoCargar(java.lang.String arg0) {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        return port.silaboEstadoCargar(arg0);
    }

    private static ec.edu.espoch.academico.Periodo periodoActual() {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        return port.periodoActual();
    }

    public String toHTMLContenido() {
        String result = "";
        try {
            result += "<div style='text-align:center;' id='error'><i class='fa fa-exclamation-triangle fa-3x' style='color: gold;'></i>"
                    + " <p>Informacion Desconocida</p></div>";
        } catch (Exception e) {
        }
        return result;
    }
}
