/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.criteriosevaluaciones.ln;

import com.google.gson.Gson;
import dda.silabo.criteriosevaluaciones.ad.CriteriosEvaluacionesAD;
import dda.silabo.db.AccesoDatos;
import dda.silabo.menulateral.ad.MenuLateralAD;
import dda.silabo.silabo.comunes.Silabo;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author jose-
 */
public class CriteriosEvaluacionesLN {

    AccesoDatos ad = new AccesoDatos();
    Gson G = new Gson();

    public String getEvaluaciones(String jsonSilabo) {
        CriteriosEvaluacionesAD result = new CriteriosEvaluacionesAD();
        Silabo silabo = G.fromJson(jsonSilabo, Silabo.class);

        try {
            if (ad.Connectar() != 0) {
                result.getEvaluaciones(ad, silabo);
                if (result.getActividadesevaluar().isEmpty()) {
                    result.ingresarActividadesEvaluar(ad, silabo.getIdSilabo(), silabo.getCodMateria(), silabo.getCodCarrera());
                    result.getEvaluaciones(ad, silabo);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger("CriteriosEvaluacionesLN").log(java.util.logging.Level.SEVERE, "dda.silabo.criteriosevaluaciones.ln", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        } finally {
            ad.Desconectar();
        }

        return G.toJson(result);
    }
}
