/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.observaciones.ln;

import com.google.gson.Gson;
import dda.silabo.db.AccesoDatos;
import dda.silabo.estructura.unidadinformacion.ln.EstructuraDesarrolloLN;
import dda.silabo.ln.SilaboLN;
import dda.silabo.menulateral.ad.MenuLateralAD;
import dda.silabo.observaciones.ad.ObservacionesAD;
import dda.silabo.silabo.comunes.Silabo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Zaruma
 */
public class ObservacionesLN {

    AccesoDatos ad = new AccesoDatos();
    Gson G = new Gson();

    public String getObservacionesSeccion(String jsonAsignaturaInfo) {
        ObservacionesAD observaciones = new ObservacionesAD();
        SilaboLN silabo = new SilaboLN();

        try {
            if (ad.Connectar() != 0) {
                Integer idSilabo = silabo.getIdSilabo(jsonAsignaturaInfo, ad);
                observaciones.getObservacionesSeccion(idSilabo, ad);
                EstructuraDesarrolloLN estructura = new EstructuraDesarrolloLN();
                int numUnidades = estructura.getNumeroUnidades(jsonAsignaturaInfo);
                observaciones.getObservacionesSubseccion(idSilabo, ad, numUnidades);
            }
        } catch (Exception e) {

        } finally {
            ad.Desconectar();
        }
        return G.toJson(observaciones);
    }

    public String ObservacionesSeccionesCargar(String jsonSilabo) {
        Silabo silabo = G.fromJson(jsonSilabo, Silabo.class);
        ObservacionesAD observacionesAD = new ObservacionesAD();
        try {
            if (silabo != null && ad.Connectar() != 0) {
                Integer idSeccion = silabo.getIdTipo();
                String tipo = silabo.getTipo();
                observacionesAD.setIdTipo(idSeccion);
                MenuLateralAD menuLateralAD = new MenuLateralAD();
                if (silabo.getTipoSeccion() != null) {
                    observacionesAD.setAccionBotonSeccion(idSeccion);
                    if ((silabo.getRol().equals("Cor") || silabo.getRol().equals("Dir")) && menuLateralAD.getEstadoSeccion(silabo.getIdSilabo(), idSeccion, ad, "seccion").equals("Corregido")) {
                        menuLateralAD.updateSeccionesMenuSilabo(silabo, idSeccion, ad, "Aprobado");
                    }
                    observacionesAD.getListaObservacionesSeccion(tipo, silabo, idSeccion, ad);
                } else {
                    observacionesAD.setAccionBotonSubseccion(idSeccion);
                    if ((silabo.getRol().equals("Cor") || silabo.getRol().equals("Dir")) && menuLateralAD.getEstadoSeccion(silabo.getIdSilabo(), idSeccion, ad, "subseccion").equals("Corregido")) {
                        menuLateralAD.updateSubseccionesMenuSilabo(silabo, idSeccion, ad, "Aprobado");
                    }
                    observacionesAD.getListaObservacionesSubseccion(tipo, silabo, idSeccion, ad);
                }
//                if (!silabo.getRol().equals("Adm")) {
//                    result.obtenerObservaciones(tipo, silabo, ad);
//                }

            }
        } catch (Exception e) {
            Logger.getLogger("EscenariosLN").log(Level.SEVERE, "dda.silabos.ln", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        } finally {
            ad.Desconectar();
        }
        return G.toJson(observacionesAD);
    }
}
