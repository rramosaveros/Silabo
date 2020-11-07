/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.observaciones.ad;

import dda.silabo.db.AccesoDatos;
import dda.silabo.menulateral.ad.MenuLateralAD;
import dda.silabo.observaciones.comunes.Observacion;
import dda.silabo.silabo.comunes.Silabo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author RRA
 */
public class ObservacionAD extends Observacion {

    public void getObservacionSeccion(ResultSet rsObservacion) {
        try {
            this.setId_observacion(rsObservacion.getInt("id_observacion_seccion"));
            this.setDescsec(rsObservacion.getString("tipo"));
            this.setEstado(rsObservacion.getString("estado"));
            this.setObservacion(rsObservacion.getString("descripcion_s"));
            this.setFecha(rsObservacion.getDate("fecha").toString());
        } catch (SQLException e) {

        }
    }

    public void getObservacionSubseccion(ResultSet rsObservacion) {
        try {
            this.setId_observacion(rsObservacion.getInt("id_observacion_unidad"));
            this.setDescsec(rsObservacion.getString("tipo"));
            this.setEstado(rsObservacion.getString("estado"));
            this.setObservacion(rsObservacion.getString("descripcion_s"));
            this.setFecha(rsObservacion.getDate("fecha").toString());
            this.setIdUnidad(rsObservacion.getInt("id_unidad"));
        } catch (SQLException e) {
            Logger.getLogger("ObservacionAD").log(java.util.logging.Level.SEVERE, "dda.silabo.estructura.unidad.estrategias.ln", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        }
    }

    public void updateObservacionSubseccion(AccesoDatos ad, List<Observacion> observaciones, Silabo silabo, Integer idSubseccion) throws SQLException {
        String SQL = "";
        if (observaciones.size() > 0) {
            Date myDate = new Date();
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
            try {
                PreparedStatement ps = null;
                if (silabo.getRol().equals("Doc") && observaciones.get(0).getId_observacion() != null) {
                    SQL = "UPDATE t_observaciones_unidades SET estado='Corregido' WHERE (id_observacion_unidad=?)";
                    ps = ad.getCon().prepareStatement(SQL);
                    ps.setInt(1, observaciones.get(0).getId_observacion());
                    ps.executeUpdate();
                    ad.getCon().commit();
                    ps.close();
                } else if (observaciones.get(0) != null) {
                    if (observaciones.get(0).getId_observacion() != null && !observaciones.get(0).getObservacion().equals("")) {
                        SQL = "UPDATE t_observaciones_unidades SET descripcion_s=? WHERE (id_observacion_unidad=?)";
                        ps = ad.getCon().prepareStatement(SQL);
                        ps.setString(1, observaciones.get(0).getObservacion());
                        ps.setInt(2, observaciones.get(0).getId_observacion());
                        ps.executeUpdate();
                        ps.close();
                        ad.getCon().commit();
                    } else if (observaciones.get(0).getId_observacion() != null && observaciones.get(0).getObservacion().equals("")) {
                        SQL = "delete from t_observaciones_unidades WHERE (id_observacion_unidad=?) ";
                        ps = ad.getCon().prepareStatement(SQL);
                        ps.setInt(1, observaciones.get(0).getId_observacion());
                        ps.executeUpdate();
                        ps.close();
                        ad.getCon().commit();
                        updateSubseccionSilabo(ad, silabo, idSubseccion, "Aprobado");
                    } else if (observaciones.get(0).getId_observacion() == null && !observaciones.get(0).getObservacion().equals("")) {
                        int idUnidad = getIdUnidadSilabo(silabo.getIdSilabo(),ad,silabo.getIdUnidad()); 
                        SQL = "insert into t_observaciones_unidades (fecha,descripcion_s,tipo,estado,id_silabo,id_unidad) values ('" + fecha + "','" + observaciones.get(0).getObservacion() + "','" + silabo.getTipo() +"','Pendiente','" + silabo.getIdSilabo() + "','" + idUnidad + "')";
//                        SQL = "insert into t_observaciones_unidades values (?,?,?,?,?,?)";
//                        ps = ad.getCon().prepareStatement(SQL);
//                        ps.setString(1, fecha);
//                        ps.setString(2, descripcionOb);
//                        ps.setString(3, tipo);
//                        ps.setString(4, "Pendiente");
//                        ps.setInt(5, idSilabo);
//                        ps.setInt(6, idUnidad);
//                        ps.executeUpdate(); 
//                        ps.close();
//                        ad.getCon().commit();   
//                        updateSubseccionSilabo(ad, silabo, idSubseccion, "Corregir");
                        PreparedStatement smt = ad.getCon().prepareStatement(SQL);
                        smt.executeUpdate();
                        smt.close();
                        ad.getCon().commit();
                        updateSubseccionSilabo(ad, silabo, idSubseccion, "Corregir");
                    }
                }

            } catch (SQLException e) {
                ad.getCon().rollback();
                ad.Desconectar();
                Logger.getLogger("ObservacionAD").log(java.util.logging.Level.SEVERE, "dda.silabo.estructura.unidad.estrategias.ln", e.getClass().getName() + "*****" + e.getMessage());
                System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
            }
        }
    }

    public void updateObservacionSeccion(AccesoDatos ad, List<Observacion> observaciones, Silabo silabo, Integer idSeccion) throws SQLException {
        String SQL = "";
        if (observaciones.size() > 0) {
            Date myDate = new Date();
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
            try {
                PreparedStatement ps = null;
                if (silabo.getRol().equals("Doc") && observaciones.get(0).getId_observacion() != null) {
                    SQL = "UPDATE t_observaciones_secciones SET estado='Corregido' WHERE (id_observacion_seccion=?)";
                    ps = ad.getCon().prepareStatement(SQL);
                    ps.setInt(1, observaciones.get(0).getId_observacion());
                    ps.executeUpdate();
                    ad.getCon().commit();
                    ps.close();
                } else if (observaciones.get(0) != null) {
                    if (observaciones.get(0).getId_observacion() != null && !observaciones.get(0).getObservacion().equals("")) {
                        SQL = "UPDATE t_observaciones_secciones SET descripcion_s=? WHERE (id_observacion_seccion=?)";
                        ps = ad.getCon().prepareStatement(SQL);
                        ps.setString(1, observaciones.get(0).getObservacion());
                        ps.setInt(2, observaciones.get(0).getId_observacion());
                        ps.executeUpdate();
                        ad.getCon().commit();
                        ps.close();

                    } else if (observaciones.get(0).getId_observacion() != null && observaciones.get(0).getObservacion().equals("")) {
                        SQL = "delete from t_observaciones_secciones WHERE (id_observacion_seccion=?)";
                        ps = ad.getCon().prepareStatement(SQL);
                        ps.setInt(1, observaciones.get(0).getId_observacion());
                        ps.executeUpdate();
                        ad.getCon().commit();
                        ps.close();

                        updateSeccionesSilabo(silabo, ad, idSeccion, "Aprobado");
                    } else if (observaciones.get(0).getId_observacion() == null && !observaciones.get(0).getObservacion().equals("")) {
                        SQL = "insert into t_observaciones_secciones (descripcion_s,fecha,estado,tipo,id_silabo) values ('" + observaciones.get(0).getObservacion() + "','" + fecha + "','Pendiente','" + silabo.getTipo() + "','" + silabo.getIdSilabo() + "')";
                        Statement smt = ad.getCon().createStatement();
                        smt.executeUpdate(SQL);
                        smt.close();
                        ad.getCon().commit();
//                        ps = ad.getCon().prepareStatement(SQL);
//                        ps.setString(1, observaciones.get(0).getObservacion());
//                        ps.setString(2, fecha);
//                        ps.setString(3, "Pendiente");
//                        ps.setString(4, silabo.getTipo());
//                        ps.setInt(5, silabo.getIdSilabo());
//                        ps.executeUpdate();
//                        ps.close();
                        updateSeccionesSilabo(silabo, ad, idSeccion, "Corregir");
                    }
                }

            } catch (SQLException e) {
                ad.getCon().rollback();
                ad.Desconectar();
                Logger.getLogger("ObservacionAD").log(java.util.logging.Level.SEVERE, "dda.silabo.estructura.unidad.estrategias.ln", e.getClass().getName() + "*****" + e.getMessage());
                System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
            }
        }
    }

    public void updateSubseccionSilabo(AccesoDatos ad, Silabo silabo, int idSubseccion, String estado) {
        try {
            MenuLateralAD menulateralln = new MenuLateralAD();
            menulateralln.updateSubseccionesMenuSilabo(silabo, idSubseccion, ad, estado);
//            menulateralln.updateEstadoSilabo(silabo, ad, estado);
        } catch (Exception e) {

        }

    }

    public void updateSeccionesSilabo(Silabo silabo, AccesoDatos ad, int idSeccion, String estado) {
        try {
            MenuLateralAD menulateralln = new MenuLateralAD();
            menulateralln.updateSeccionesMenuSilabo(silabo, idSeccion, ad, estado);
//            menulateralln.updateEstadoSilabo(silabo, ad, estado);
        } catch (Exception e) {

        }
    }
    
    private Integer getIdUnidadSilabo(Integer idSilabo, AccesoDatos ad, Integer idUnidadSilabo) throws SQLException {
        Integer idUnidad = 0;
        try {
            String SQL = "select id_unidad from t_unidades where (id_silabo=? and numero_unidad=?) \n"
                    + "ORDER BY id_unidad DESC LIMIT 1";
            PreparedStatement ps = ad.getCon().prepareStatement(SQL);
            ps.setInt(1, idSilabo);
            ps.setInt(2, idUnidadSilabo);
            ResultSet rsUnidades = ps.executeQuery();
            while (rsUnidades.next()) {
                idUnidad = rsUnidades.getInt("id_unidad");
            }
            ad.getCon().commit();
            ps.close();
        } catch (SQLException e) {
            ad.getCon().rollback();
            ad.Desconectar();
            Logger.getLogger("UnidadesAD").log(java.util.logging.Level.SEVERE, "dda.silabo.ln", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        }
        return idUnidad;
    }

}
