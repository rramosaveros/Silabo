package unidos.ies.ln;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import redis.clients.jedis.Jedis;
import unidos.comunes.Docente;
import unidos.comunes.Entidad;
import unidos.ies.ad.CarreraAD;
import unidos.ies.ad.EscuelaAD;
import unidos.ies.ad.FacultadAD;
import unidos.ies.ad.IesAD;
import unidos.jedis.Global;

public class IesLN extends Entidad {

    Gson gson = new Gson();
    Global global = new Global();
    Jedis jedis = global.conexion();

    public String redisFlushAll() {
        try {
            this.jedis.flushAll();
            return "Cache Eliminada";
        } catch (Exception e) {
            return null;
        }
    }

    //******************************************************************************************************
    //*                                  DOCENTES POR CARRERA                                              *
    //******************************************************************************************************
    public CarreraAD DocentesCarrera(String jsonCarrera) {
        String result = null;
        CarreraAD carreraAD = new CarreraAD();
        try {
            String refresh = "false";
            String codCarrera = "";
            if (jsonCarrera != null) {
                Properties parametros = gson.fromJson(jsonCarrera, Properties.class);
                codCarrera = (parametros.getProperty("codCarrera") == null) ? codCarrera : parametros.getProperty("codCarrera");
                refresh = (parametros.getProperty("refresh") == null) ? refresh : parametros.getProperty("refresh");
            }

            result = jedis.get("DocentesCarrera_" + codCarrera);
            if (result == null || refresh.equals("true")) {
                carreraAD.setCodCarrera(codCarrera);
                ConcurrentHashMap<String, Docente> docentes = new ConcurrentHashMap<>();
                carreraAD.DocentesCarrera(docentes);
                result = gson.toJson(carreraAD);
                jedis.set("DocentesCarrera_" + codCarrera, result);
                jedis.expire("DocentesCarrera_" + codCarrera, 1440);
            } else {
                carreraAD = gson.fromJson(result, CarreraAD.class);
            }

        } catch (JsonSyntaxException e) {
            Logger.getLogger("IesLN").log(Level.SEVERE, "IesLN|getCarreraDocentes: ".concat(e.getMessage()));
        }
        return carreraAD;
    }

    //******************************************************************************************************
    //*                                  DOCENTES POR CARRERA                                              *
    //******************************************************************************************************
    public EscuelaAD DocentesEscuela(String jsonEscuela) {
        String result = null;
        EscuelaAD escuelaAD = new EscuelaAD();
        try {
            String refresh = "false";
            String codEscuela = "";
            if (jsonEscuela != null) {
                Properties parametros = gson.fromJson(jsonEscuela, Properties.class);
                codEscuela = (parametros.getProperty("codEscuela") == null) ? codEscuela : parametros.getProperty("codEscuela");
                refresh = (parametros.getProperty("refresh") == null) ? refresh : parametros.getProperty("refresh");
            }
            result = jedis.get("DocentesEscuela_" + codEscuela);
            if (result == null || refresh.equals("true")) {
                EscuelaLN escuelaLN = new EscuelaLN();
                String jsonCarreras = escuelaLN.arbolEscuela(jsonEscuela);
                escuelaAD = gson.fromJson(jsonCarreras, EscuelaAD.class);
                escuelaAD.DocentesEscuela();
                result = gson.toJson(escuelaAD);
                jedis.set("DocentesEscuela_" + codEscuela, result);
                jedis.expire("DocentesEscuela_" + codEscuela, 1440);
            } else {
                escuelaAD = gson.fromJson(result, EscuelaAD.class);
            }
        } catch (JsonSyntaxException e) {
            Logger.getLogger("IesLN").log(Level.SEVERE, "IesLN|getCarreraDocentes: ".concat(e.getMessage()));
        }
        return escuelaAD;
    }

    //******************************************************************************************************
    //*                               ASIGNATURAS POR CARRERA                                              *
    //******************************************************************************************************
    public CarreraAD AsignaturasCarrera(String jsonCarrera) throws FileNotFoundException, IOException {
        CarreraAD carreraAD = null;
        try {
            String refresh = "false";
            String codCarrera = "";
            if (jsonCarrera != null) {
                Properties parametros = gson.fromJson(jsonCarrera, Properties.class);
                codCarrera = (parametros.getProperty("codCarrera") == null) ? codCarrera : parametros.getProperty("codCarrera");
                refresh = (parametros.getProperty("refresh") == null) ? refresh : parametros.getProperty("refresh");
            }
            Gson G = new Gson();
            carreraAD = null;// jedis.get("AsignaturasCarrera_" + codCarrera);
            if (carreraAD == null || refresh.equals("true")) {
                carreraAD = new CarreraAD();
                carreraAD.setCodCarrera(codCarrera);
                carreraAD.carreraAsignaturasFromOASis();
//                result = G.toJson(carreraAD);
//                jedis.set("AsignaturasCarrera_" + codCarrera, result);
//                jedis.expire("AsignaturasCarrera_" + codCarrera, 1440);
            }
        } catch (JsonSyntaxException e) {
            Logger.getLogger("IesLN").log(Level.SEVERE, "IesLN|getCarreraDocentes: ".concat(e.getMessage()));
        }
        return carreraAD;
    }

    //******************************************************************************************************
    //*                               CAMPOS DE FORMACION POR CARRERA                                      *
    //******************************************************************************************************
    public CarreraAD AsignaturasCamposFormacion(String jsonCarrera) throws IOException {

        CarreraAD carreraAD = null;
        try {
            String refresh = "false";
            String codCarrera = "";
            if (jsonCarrera != null) {
                Properties parametros = gson.fromJson(jsonCarrera, Properties.class);
                codCarrera = (parametros.getProperty("codCarrera") == null) ? codCarrera : parametros.getProperty("codCarrera");
                refresh = (parametros.getProperty("refresh") == null) ? refresh : parametros.getProperty("refresh");
            }
            if (carreraAD == null || refresh.equals("true")) {
                carreraAD = AsignaturasCarrera(jsonCarrera);
                CarreraLN carreraLN = new CarreraLN();
                CarreraAD carreraAD2 = carreraLN.arbolCarrera(jsonCarrera);
                carreraAD.setCamposFormacion(carreraAD2.getCamposFormacion());
                carreraAD.setCodCarrera(codCarrera);
                carreraAD.CamposFormacionCarrera();
            }
        } catch (JsonSyntaxException e) {
            Logger.getLogger("IesLN").log(Level.SEVERE, "IesLN|getCarreraDocentes: ".concat(e.getMessage()));
        }
        return carreraAD;
    }

    public FacultadAD DocentesFacultad(String jsonFacultad) {
        String result = null;
        FacultadAD facultadAD = new FacultadAD();
        try {
            String refresh = "false";
            String codFacultad = "";
            if (jsonFacultad != null) {
                Properties parametros = gson.fromJson(jsonFacultad, Properties.class);
                codFacultad = (parametros.getProperty("codFacultad") == null) ? codFacultad : parametros.getProperty("codFacultad");
                refresh = (parametros.getProperty("refresh") == null) ? refresh : parametros.getProperty("refresh");
            }
            result = jedis.get("DocentesFacultad_" + codFacultad);
            if (result == null || refresh.equals("true")) {
                FacultadLN facultadLN = new FacultadLN();
                String jsonArbolFacultad = facultadLN.arbolFacultad(jsonFacultad);
                facultadAD = gson.fromJson(jsonArbolFacultad, FacultadAD.class);
                facultadAD.DocentesFacultad();
                result = gson.toJson(facultadAD);
                jedis.set("DocentesFacultad_" + codFacultad, result);
                jedis.expire("DocentesFacultad_" + codFacultad, 7200);
            } else {
                facultadAD = gson.fromJson(result, FacultadAD.class);
                jedis.set("DocentesFacultad_" + codFacultad, result);
                jedis.expire("DocentesFacultad_" + codFacultad, 7200);

            }

        } catch (JsonSyntaxException e) {
            Logger.getLogger("IesLN").log(Level.SEVERE, "IesLN|getCarreraDocentes: ".concat(e.getMessage()));
        }
        return facultadAD;
    }

    public IesAD FacultadesLista(String jsonEntidad) {
        IesAD iesAD = null;
        try {
            iesAD = new IesAD();
            String refresh = "false";
            if (jsonEntidad != null) {
                Properties parametros = gson.fromJson(jsonEntidad, Properties.class);
                refresh = (parametros.getProperty("refresh") == null) ? refresh : parametros.getProperty("refresh");
            }
//            if (refresh.equals("true") || result == null) {
            iesAD.FacultadesLista();
//            }

        } catch (JsonSyntaxException e) {
            Logger.getLogger("IesLN").log(Level.SEVERE, "EscuelaLN|getCarreras: ".concat(e.getMessage()));
        }
        return iesAD;
    }

    public CarreraAD DocentesCamposFormacion(String jsonCarrera) throws IOException {
        CarreraAD carreraAD = null;
        try {
            String refresh = "false";
            String codCarrera = "";
            String codCampo = "";
            if (jsonCarrera != null) {
                Properties parametros = gson.fromJson(jsonCarrera, Properties.class);
                codCarrera = (parametros.getProperty("codCarrera") == null) ? codCarrera : parametros.getProperty("codCarrera");
                codCampo = (parametros.getProperty("codCampo") == null) ? codCarrera : parametros.getProperty("codCampo");

                refresh = (parametros.getProperty("refresh") == null) ? refresh : parametros.getProperty("refresh");
            }
            String result = jedis.get("DocentesCF_" + codCarrera + codCampo);
            if (result == null || refresh.equals("true")) {
                carreraAD = AsignaturasCamposFormacion(jsonCarrera);
                carreraAD.setCodCarrera(codCarrera);
                carreraAD.DocentesCamposFormacion(codCampo);
                result = gson.toJson(carreraAD);
                jedis.set("DocentesCF_" + codCarrera + codCampo, result);
                jedis.expire("DocentesCF_" + codCarrera, 1440);
            } else {
                carreraAD = gson.fromJson(result, CarreraAD.class);
            }

        } catch (JsonSyntaxException e) {
            Logger.getLogger("IesLN").log(Level.SEVERE, "IesLN|getCarreraDocentes: ".concat(e.getMessage()));
        }
        return carreraAD;
    }

}
