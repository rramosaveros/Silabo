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
import unidos.ies.ad.FacultadAD;
import unidos.jedis.Global;

/**
 *
 * @author Adry Qp
 */
public class FacultadLN {

    Gson gson = new Gson();
    Global global = new Global();
    Jedis jedis = global.conexion();

    public FacultadAD EscuelasLista(String jsonEntidad) {
        FacultadAD facultadAD = null;
        try {
            facultadAD = new FacultadAD();
            String refresh = "false";
            if (jsonEntidad != null) {
                Properties parametros = gson.fromJson(jsonEntidad, Properties.class);
                refresh = (parametros.getProperty("refresh") == null) ? refresh : parametros.getProperty("refresh");
            }
            //result = jedis.get("ArrayEscuelas");
            //            if (refresh.equals("true")||result==null) {
            facultadAD.EscuelasLista();
//            result = gson.toJson(facultadAD);
//            }

        } catch (JsonSyntaxException e) {
            Logger.getLogger("IesLN").log(Level.SEVERE, "EscuelaLN|getCarreras: ".concat(e.getMessage()));
        }
        return facultadAD;
    }

    public String arbolFacultad(String jsonDatos) {
        String result = "";
        try {
            String refresh = "false";
            String codFacultad = "";
            if (jsonDatos != null) {
                Properties parametros = gson.fromJson(jsonDatos, Properties.class);
                codFacultad = (parametros.getProperty("codFacultad") == null) ? codFacultad : parametros.getProperty("codFacultad");
                refresh = (parametros.getProperty("refresh") == null) ? refresh : parametros.getProperty("refresh");
            }

            result = jedis.get("ArbolFacultad_" + codFacultad);
            if (result == null || refresh.equals("true")) { //NO tiene Facultades
                FacultadAD facultadAD = new FacultadAD();
                facultadAD.arbolFacultad(codFacultad, gson);
                result = gson.toJson(facultadAD);
                jedis.set("ArbolFacultad_" + codFacultad, result);
                jedis.expire("ArbolFacultad_" + codFacultad, 1440);
            }
        } catch (JsonSyntaxException e) {
            Logger.getLogger("IesLN").log(Level.SEVERE, "IesLN|getArbolEscuela: ".concat(e.getMessage()));
        }
        return result;
    }
}
