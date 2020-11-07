/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.criteriosevaluaciones.ad;

import dda.panalitico.ws.ProcedimientoComunes;
import dda.panalitico.ws.ProcedimientosAD;
import dda.silabo.criteriosevaluaciones.comunes.CriteriosEvaluaciones;
import dda.silabo.db.AccesoDatos;
import dda.silabo.observaciones.ad.ObservacionesAD;
import dda.silabo.silabo.comunes.Silabo;
import dda.silabo.vigencia.funcion.Vigencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Jorge Zaruma
 */
public class CriteriosEvaluacionesAD extends CriteriosEvaluaciones {

    public void getEvaluaciones(AccesoDatos ad, Silabo silabo) throws SQLException {
        String SQL2 = "select * from t_actividades where id_silabo=? order by id_actividades";
        try {
            PreparedStatement ps = ad.getCon().prepareStatement(SQL2);
            ps.setInt(1, silabo.getIdSilabo());
            ResultSet rsActividad = ps.executeQuery();
            while (rsActividad.next()) {
                ActividadEvaluarAD actividad = new ActividadEvaluarAD();
                actividad.getActividadesAporte(rsActividad, ad, silabo.getIdSilabo());
                this.getActividadesevaluar().add(actividad);
            }
            ad.getCon().commit();
            ps.close();
            this.setSilabos(silabo);
            this.setAyuda("El docente implementará la evaluación tomando como referencia las características de su asignatura y seleccionará al menos "
                    + " tres actividades para cada evaluación parcial, las calificaciones se anotarán con números enteros."
                    + " <br><b>Primer Parcial:</b> la sumatoria final de las evaluaciones debe tener maximo 8 puntos"
                    + " <br><b>Segundo y Tercer Parcial:</b> la sumatoria final de las evaluaciones debe tener maximo 10 puntos "
                    + " <br><b>Exámen Principal:</b> esta establecido como una nota maxima de 12"
                    + " <br><b>Exámen Remedial:</b> esta establecido como una nota maxima de 20"
                    + " <br><table border='1' border­radius='10px'>"
                    + "     <tr>"
                    + "         <th><strong>Componente de docencia presencial</strong></th>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>50%…..60%</td>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <th><strong>Componente de pr&aacute;cticas de aplicaci&oacute;n y experimentaci&oacute;n de los aprendizajes</strong></th>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>25%....30%</td>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <th><strong>Componente de aprendizaje autónomo</strong></th>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>15%....25%</td>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <th><strong>Total</strong></th>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>100%</td>"
                    + "     </tr>"
                    + "</table>");
            Vigencia vigenciaAyudaCriteriosNormativosEvaluacion = new Vigencia(); 
            if("vigente".equals(vigenciaAyudaCriteriosNormativosEvaluacion.obtenerVigenciaCarreraF(silabo.getCodCarrera()))){
                this.setAyuda("El docente implementará la evaluación tomando como referencia el Programa Analítico de la asignatura y seleccionará al menos "
                    + " tres actividades para cada evaluación parcial, las calificaciones se anotarán con números enteros."
//                    + " <br>Se debe seleccionar las calificaciones que corresponda a cada parcial teniendo en cuenta lo siguiente: "
                    + " <br><b>Primer Parcial:</b> la sumatoria final de las evaluaciones debe tener maximo 8 puntos"
                    + " <br><b>Segundo y Tercer Parcial:</b> la sumatoria final de las evaluaciones debe tener maximo 10 puntos "
                    + " <br><b>Exámen principal:</b> esta establecido como una nota maxima de 12"
                    + " <br><b>Exámen de Recuperación:</b> esta establecido como una nota maxima de 20"
//                    + " <br>Se instituye los siguientes valores en rangos porcentuales para dicho fin, cuya sumatoria deberá ser del 100%: "
                    + " <br><table border='1' border­radius='10px'>"
                    + "     <tr>"
                    + "         <th><strong>Componente de docencia presencial</strong></th>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>50%…..60%</td>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <th><strong>Componente de pr&aacute;cticas de aplicaci&oacute;n y experimentaci&oacute;n de los aprendizajes</strong></th>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>25%....30%</td>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <th><strong>Componente de aprendizaje autónomo</strong></th>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>15%....25%</td>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <th><strong>Total</strong></th>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>100%</td>"
                    + "     </tr>"
                    + "</table>");
            }
            this.setVigencia(vigenciaAyudaCriteriosNormativosEvaluacion.obtenerVigenciaCarreraF(silabo.getCodCarrera()));
            this.setTitulo("Criterios normativos para la evaluación de la asignatura");
        } catch (SQLException e) {
            ad.getCon().rollback();
            ad.Desconectar();
            Logger.getLogger("CriteriosEvaluacionesAD").log(java.util.logging.Level.SEVERE, "dda.silabo.criteriosevaluaciones.ad", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        }

    }

    public void obtenerObservaciones(String tipo, Silabo silabo, AccesoDatos ad) {
        ObservacionesAD observacion = new ObservacionesAD();
        observacion.getListaObservacionesSeccion(tipo, silabo, 4, ad);
        this.setObservacion(observacion);

    }

    public void ingresarActividadesEvaluar(AccesoDatos ad, Integer idSilabo, String codAsginatura, String codCarrera) throws SQLException {
        try {
            ProcedimientosAD procedimientosAD = programaAnaliticoProcedimientosAsignatura(codAsginatura, codCarrera);
            if (!procedimientosAD.getCodPrograma().equals("0")) {
                List<ProcedimientoComunes> procedimientos = procedimientosAD.getObjListaProcedimientos().stream().filter(pr -> pr.getIntCodigoPrograma() == Integer.parseInt(procedimientosAD.getCodPrograma())).collect(Collectors.toList());
                PreparedStatement ps = null;
                String SQL = "";
                for (ProcedimientoComunes p : procedimientos) {
                    SQL = "INSERT INTO t_actividades(\n"
                            + "            descripcion, id_silabo)\n"
                            + "    VALUES (?, ?);";
                    ps = ad.getCon().prepareStatement(SQL);
                    ps.setString(1, p.getStrDescripcion());
                    ps.setInt(2, idSilabo);
                    ps.executeUpdate();
                    ps.close();
                    ad.getCon().commit();
                }
            }
        } catch (SQLException e) {
            ad.getCon().rollback();
            ad.Desconectar();
        }
    }

    private static ProcedimientosAD programaAnaliticoProcedimientosAsignatura(java.lang.String codAsginatura, java.lang.String codCarrera) {
        unidos.sw.WsUnidosEspoch_Service service = new unidos.sw.WsUnidosEspoch_Service();
        unidos.sw.WsUnidosEspoch port = service.getWsUnidosEspochPort();
        return port.programaAnaliticoProcedimientosAsignatura(codAsginatura, codCarrera);
    }
}
