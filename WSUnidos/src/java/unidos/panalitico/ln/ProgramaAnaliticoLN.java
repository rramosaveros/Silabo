/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.panalitico.ln;

import dda.panalitico.ws.ArchivoAD;
import dda.panalitico.ws.BibliografiasComunes;
import dda.panalitico.ws.ContenidosAD;
import dda.panalitico.ws.ProcedimientosAD;

/**
 *
 * @author Jorge Zaruma
 */
public class ProgramaAnaliticoLN {

    public ContenidosAD programaAnaliticoContenidosAsignatura(java.lang.String codAsginatura, java.lang.String codCarrera) {
        dda.panalitico.ws.WsAnaliticoPublico_Service service = new dda.panalitico.ws.WsAnaliticoPublico_Service();
        dda.panalitico.ws.WsAnaliticoPublico port = service.getWsAnaliticoPublicoPort();
        return port.programaAnaliticoContenidosAsignatura(codAsginatura, codCarrera);
    }

    public ArchivoAD programaAnaliticoLogoEntidad(java.lang.String codEntidad, String tipoEntidad) {
        String jsonEntidad = "";
        ArchivoAD aD = new ArchivoAD();
        aD.setTipoEntidad("LOGO");
        if (tipoEntidad.equals("Instituto")) {
            jsonEntidad = "{'tipoEntidad':'" + tipoEntidad + "','codigoInstitucion':'" + codEntidad + "'}";
        } else if (tipoEntidad.equals("Facultad")) {
            jsonEntidad = "{'tipoEntidad':'" + tipoEntidad + "','codigoFacultad':'" + codEntidad + "'}";
        } else if (tipoEntidad.equals("Escuela")) {
            jsonEntidad = "{'tipoEntidad':'" + tipoEntidad + "','codigoEscuela':'" + codEntidad + "'}";
        } else if (tipoEntidad.equals("Carrera")) {
            jsonEntidad = "{'tipoEntidad':'" + tipoEntidad + "','codigoCarrera':'" + codEntidad + "'}";
        }
        if (!jsonEntidad.equals("")) {
            dda.panalitico.ws.WsAnaliticoPublico_Service service = new dda.panalitico.ws.WsAnaliticoPublico_Service();
            dda.panalitico.ws.WsAnaliticoPublico port = service.getWsAnaliticoPublicoPort();
            aD = port.programaAnaliticoLogoEntidad(jsonEntidad);
        }
        return aD;

    }

    public ProcedimientosAD programaAnaliticoProcedimientosAsignatura(java.lang.String codAsginatura, java.lang.String codCarrera) {
        dda.panalitico.ws.WsAnaliticoPublico_Service service = new dda.panalitico.ws.WsAnaliticoPublico_Service();
        dda.panalitico.ws.WsAnaliticoPublico port = service.getWsAnaliticoPublicoPort();
        return port.programaAnaliticoProcedimientosAsignatura(codAsginatura, codCarrera);
    }

    public static BibliografiasComunes programaAnaliticoBibliografias(java.lang.String codAsignatura, java.lang.String codCarrera) {
        dda.panalitico.ws.WsAnaliticoPublico_Service service = new dda.panalitico.ws.WsAnaliticoPublico_Service();
        dda.panalitico.ws.WsAnaliticoPublico port = service.getWsAnaliticoPublicoPort();
        return port.programaAnaliticoBibliografias(codAsignatura, codCarrera);
    }

    public String programaAnaliticoVigenciaCarrera(java.lang.String codCarrera) {
        dda.panalitico.ws.WsAnaliticoPublico_Service service = new dda.panalitico.ws.WsAnaliticoPublico_Service();
        dda.panalitico.ws.WsAnaliticoPublico port = service.getWsAnaliticoPublicoPort();
        return port.programaAnaliticoVigenciaCarrera(codCarrera);
    }

}
