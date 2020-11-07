package dda.silabo.archivos.ad;

import dda.silabo.archivos.comunes.ArchivoComunes;
import dda.silabo.db.AccesoDatos;
import dda.silabo.silabo.comunes.Silabo;
import dda.silabo.vigencia.funcion.Vigencia;
import ec.edu.espoch.academico.ArrayOfEscuelaEntidad;
import ec.edu.espoch.academico.EscuelaEntidad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class ArchivoAD extends ArchivoComunes {

    public void SilaboGenerarPDF(String SQL, AccesoDatos ad) throws SQLException {
        try {
            PreparedStatement ps = ad.getCon().prepareStatement(SQL);

            ResultSet rsArchivo = ps.executeQuery();
            if (rsArchivo != null) {
                while (rsArchivo.next()) {
                    byte[] bytes = rsArchivo.getBytes(1);
                    this.setArchivo(bytes);
                }
            }
            ad.getCon().commit();
            ps.close();
        } catch (SQLException e) {
            ad.getCon().rollback();
            ad.Desconectar();
            Logger.getLogger("Gestion Logos.- ArchivoAD Metodo Pedir logo Institucional").log(java.util.logging.Level.SEVERE, "dda.panalitico.archivos.ad", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        }
    }

    public String getCodigoFacultad(AccesoDatos ad, String codEntidad) throws SQLException {
        String result = "";
        try {
            String SQL = "WITH RECURSIVE subEntidad (codigo,nombre,idpadre,tipoEntidad) AS \n"
                    + "                                        (\n"
                    + "                                            SELECT t_entidad.codigo_entidad,t_entidad.nombre,t_entidad.id_padre,te.id_tipo_entidad FROM t_entidad \n"
                    + "                                        JOIN\n"
                    + "                                        	t_tipo_entidad AS te\n"
                    + "                                        	on te.id_tipo_entidad = t_entidad.id_tipo_entidad\n"
                    + "                                        	JOIN t_usuario_entidad AS tue \n"
                    + "                                        on tue.id_entidad = t_entidad.id_entidad\n"
                    + "                                      \n"
                    + "                                            WHERE codigo_entidad = ? AND te.id_tipo_entidad=4\n"
                    + "                                             \n"
                    + "                                           UNION ALL\n"
                    + "                                            SELECT d.codigo_entidad,d.nombre,d.id_padre,te.id_tipo_entidad\n"
                    + "                                            FROM\n"
                    + "                                                t_entidad AS d\n"
                    + "                                           JOIN\n"
                    + "                                              subEntidad AS sd\n"
                    + "                                               ON (d.id_entidad = idpadre) \n"
                    + "                                               JOIN\n"
                    + "                                        	t_tipo_entidad AS te\n"
                    + "                                        on te.id_tipo_entidad = d.id_tipo_entidad\n"
                    + "                                                            \n"
                    + "                                        )\n"
                    + "                                        SELECT distinct on(idpadre)*\n"
                    + "                                        FROM subEntidad\n"
                    + "                                        where tipoEntidad ='2'";

            PreparedStatement ps = ad.getCon().prepareStatement(SQL);
            ps.setString(1, codEntidad);
            ResultSet rsArchivo = ps.executeQuery();
            if (rsArchivo != null) {
                while (rsArchivo.next()) {
                    result = rsArchivo.getString("codigo");
                }
            }
            ad.getCon().commit();
        } catch (SQLException e) {
            ad.getCon().rollback();
            ad.Desconectar();
        }
        return result;
    }

//    public void obtenerVigenciaCarrera(Silabo silabo) {
//        try {
//            String vigencia = programaAnaliticoVigenciaCarrera(silabo.getCodCarrera());
//            if (vigencia == null) {
//                vigencia = "no definido";
//            }
//            this.setVigencia(vigencia);
//        } catch (Exception e) {
//        }
//    }
    
    public void obtenerVigenciaCarrera(Silabo silabo) {
        try {
            List<EscuelaEntidad> listaEscuelas = arrayOfEscuelaEntidad().getEscuelaEntidad();
            EscuelaEntidad escuelaParaSacarNombre = listaEscuelas.stream().filter(es -> es.getCodCarrera().equals(silabo.getCodCarrera())).findFirst().orElse(null);
            Vigencia vigente = new Vigencia(); 
            String vigencia = vigente.determinarVigenciaCarrera(escuelaParaSacarNombre.getCarrera());
            if (vigencia == null) {
                vigencia = "no definido";
            }
            this.setVigencia(vigencia);
        } catch (Exception e) {
        }
    }

    private static String programaAnaliticoVigenciaCarrera(java.lang.String codCarrera) {
        unidos.sw.WsUnidosEspoch_Service service = new unidos.sw.WsUnidosEspoch_Service();
        unidos.sw.WsUnidosEspoch port = service.getWsUnidosEspochPort();
        return port.programaAnaliticoVigenciaCarrera(codCarrera);
    }
    
    private static ArrayOfEscuelaEntidad arrayOfEscuelaEntidad() {
        si.sw.locales.SwUnidosSI_Service service = new si.sw.locales.SwUnidosSI_Service();
        si.sw.locales.SwUnidosSI port = service.getSwUnidosSIPort();
        return port.arrayOfEscuelaEntidad();
    }
}
