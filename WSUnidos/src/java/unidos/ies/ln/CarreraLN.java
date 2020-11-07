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
import unidos.ies.ad.CarreraAD;
import unidos.comunes.Carrera;
import unidos.jedis.Global;

/**
 *
 * @author Adriana Qp
 */
public class CarreraLN extends Carrera {

    Gson gson = new Gson();
    Global global = new Global();
    Jedis jedis = global.conexion();

    public Boolean loadCarreraDocentes(String refresh) {
        Boolean result = false;
        try {
            result = (this.loadCarreraAsignaturasFromOASis(refresh));
        } catch (JsonSyntaxException e) {
            Logger.getLogger("CarreraLN").log(Level.SEVERE, "CarreraLN|getCarreraDocentes: ".concat(e.getMessage()));
        }
        return result;
    }

    public Boolean loadCarreraAsignaturas(String refresh) {
        Boolean result = false;
        try {
            result = this.loadCarreraAsignaturasFromOASis(refresh);
        } catch (JsonSyntaxException e) {
            Logger.getLogger("CarreraLN").log(Level.SEVERE, "CarreraLN|loadCarreraAsignaturas: ".concat(e.getMessage()));
        }
        return result;
    }

    private Boolean loadCarreraAsignaturasFromOASis(String refresh) {
        Boolean result = false;
        try {
            if (refresh.equals("true") || this.getAsignaturas().isEmpty()) {
                CarreraAD carreraAD = new CarreraAD();
                carreraAD.carreraAsignaturasFromOASis();

                this.setDocentes(carreraAD.getDocentes());
                this.setAsignaturas(carreraAD.getAsignaturas());
                this.setCamposFormacion(carreraAD.getCamposFormacion());
                result = true;
            }
        } catch (JsonSyntaxException e) {
            Logger.getLogger("CarreraLN").log(Level.SEVERE, "CarreraLN|loadCarreraAsignaturas: ".concat(e.getMessage()));
        }
        return result;
    }

    public CarreraAD arbolCarrera(String jsonCarrera) {
        CarreraAD carreraAD = new CarreraAD();
        String result = null;
        try {
            String refresh = "false";
            String codCarrera = "";
            if (jsonCarrera != null) {
                Properties parametros = gson.fromJson(jsonCarrera, Properties.class);
                codCarrera = (parametros.getProperty("codCarrera") == null) ? codCarrera : parametros.getProperty("codCarrera");
                refresh = (parametros.getProperty("refresh") == null) ? refresh : parametros.getProperty("refresh");
            }
            result = null;
            if (result == null) {
                carreraAD.arbolCarrera(codCarrera, gson);
            }
        } catch (JsonSyntaxException e) {
            Logger.getLogger("IesLN").log(Level.SEVERE, "IesLN|getArbolCarrera: ".concat(e.getMessage()));
        }
        return carreraAD;
    }

}
