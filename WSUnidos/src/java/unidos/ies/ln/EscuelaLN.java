/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.ies.ln;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import redis.clients.jedis.Jedis;
import unidos.comunes.Escuela;
import unidos.ies.ad.EscuelaAD;
import unidos.jedis.Global;

/**
 *
 * @author Adry Qp
 */
public class EscuelaLN extends Escuela {

    Gson gson = new Gson();
    Global global = new Global();
    Jedis jedis = global.conexion();

    public EscuelaAD CarrerasLista(String jsonEntidad) {
        EscuelaAD escuelaAD = null;
        try {
            escuelaAD = new EscuelaAD();
            String refresh = "false";
            if (jsonEntidad != null) {
                Properties parametros = gson.fromJson(jsonEntidad, Properties.class);
                refresh = (parametros.getProperty("refresh") == null) ? refresh : parametros.getProperty("refresh");
            }
//            String result = jedis.get("ArrayCarreras");
//            if (refresh.equals("true") || result == null) {
            escuelaAD.CarrerasLista();
//                result = gson.toJson(escuelaAD);
//            }
        } catch (JsonSyntaxException e) {
            Logger.getLogger("IesLN").log(Level.SEVERE, "EscuelaLN|getCarreras: ".concat(e.getMessage()));
        }
        return escuelaAD;
    }

    public String arbolEscuela(String jsonDatos) {
        String result = "";
        try {
            String refresh = "false";
            String codEscuela = "";
            if (jsonDatos != null) {
                Properties parametros = gson.fromJson(jsonDatos, Properties.class);
                codEscuela = (parametros.getProperty("codEscuela") == null) ? codEscuela : parametros.getProperty("codEscuela");
                refresh = (parametros.getProperty("refresh") == null) ? refresh : parametros.getProperty("refresh");
            }

            result = jedis.get("ArbolEscuela_" + codEscuela);
            if (result == null || refresh.equals("true")) { //NO tiene Facultades
                EscuelaAD escuelaAD = new EscuelaAD();
                escuelaAD.arbolEscuela(codEscuela, gson);
                result = gson.toJson(escuelaAD);
                jedis.set("ArbolEscuela_" + codEscuela, result);
            }
        } catch (JsonSyntaxException e) {
            Logger.getLogger("IesLN").log(Level.SEVERE, "IesLN|getArbolEscuela: ".concat(e.getMessage()));
        }
        return result;
    }

}
