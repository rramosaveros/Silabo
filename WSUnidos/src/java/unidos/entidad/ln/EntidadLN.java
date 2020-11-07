package unidos.entidad.ln;

import com.google.gson.Gson;
import ec.edu.espoch.academico.ArrayOfEscuela;
import ec.edu.espoch.academico.ArrayOfEscuelaEntidad;
import ec.edu.espoch.academico.ArrayOfFacultad;
import ec.edu.espoch.academico.Escuela;
import ec.edu.espoch.academico.EscuelaEntidad;
import ec.edu.espoch.academico.Facultad;
import java.util.HashMap;
import java.util.List;
import redis.clients.jedis.Jedis;
import unidos.comunes.Carrera;
import unidos.comunes.Entidad;
import unidos.jedis.Global;

public class EntidadLN extends Entidad {

    Gson gson = new Gson();
    Entidad objEntidad;
    Global global = new Global();
    Jedis jedis = global.conexion();

    public EntidadLN() {
        this.objEntidad = new Entidad();
        this.setId("ESPOCH");
        this.setDesc("Escuela Superior Politécnica de Chimborazo");
    }

    public String loadUnidadesAcademicas() {
        String result = null;
        try {

            result = jedis.get("UnidadesAcademicasESPOCH");
            if (result == null) {
                this.objEntidad = new Entidad();
                HashMap<String, unidos.comunes.Facultad> facultadesXcodigo = this.loadFacultades2();
                HashMap<String, unidos.comunes.Escuela> escuelas = this.loadEscuelas(facultadesXcodigo);
                HashMap<String, Carrera> carreras = this.loadCarreras(escuelas);
                result = gson.toJson(this.objEntidad);
                jedis.set("UnidadesAcademicasESPOCH", result);
                jedis.expire("UnidadesAcademicasESPOCH", 1440);
            }
        } catch (Exception e) {
        }
        return result;
    }

    private HashMap<String, unidos.comunes.Facultad> loadFacultades2() {
        List<Facultad> oasisFacultades = EntidadLN.getFacultadesTotales().getFacultad();
        HashMap<String, unidos.comunes.Facultad> facultadesXcodigo = new HashMap<>();

        oasisFacultades.stream().filter((oasisFacultad) -> (oasisFacultad.getCodEstado().equals("ABI"))).forEachOrdered((oasisFacultad) -> {
            unidos.comunes.Facultad facultad = facultadesXcodigo.get(oasisFacultad.getCodigo());
            if (facultad == null) {
                facultad = new unidos.comunes.Facultad();
                facultad.setCodFacultad(oasisFacultad.getCodigo());
                facultad.setNombre(oasisFacultad.getNombre());
                objEntidad.getFacultades().add(facultad);
                facultadesXcodigo.put(oasisFacultad.getCodigo(), facultad);
            }
        });

        return facultadesXcodigo;
    }

    private HashMap<String, unidos.comunes.Escuela> loadEscuelas(HashMap<String, unidos.comunes.Facultad> facultadesXcodigo) {
        List<Escuela> oasisEscuelas = this.getTodasEscuelas().getEscuela();
        HashMap<String, unidos.comunes.Escuela> escuelas = new HashMap<>();

        oasisEscuelas.stream().filter((oasisEscuela) -> (oasisEscuela.getCodEstado().equals("ABI"))).forEachOrdered((oasisEscuela) -> {
            unidos.comunes.Facultad facultad = facultadesXcodigo.get(oasisEscuela.getCodFacultad());
            if (facultad != null) {
                unidos.comunes.Escuela escuela = escuelas.get(oasisEscuela.getCodigo());
                if (escuela == null) {
                    escuela = new unidos.comunes.Escuela();
                    escuela.setCodEscuela(oasisEscuela.getCodigo());
                    escuela.setNombre(oasisEscuela.getNombre());
                    facultad.addEscuela(escuela);
                    escuelas.put(oasisEscuela.getCodigo(), escuela);
                }
            }
        });
        return escuelas;
    }

    private HashMap<String, Carrera> loadCarreras(HashMap<String, unidos.comunes.Escuela> escuelas) {
        List<EscuelaEntidad> oasisCarreras = this.getEscuelaEntidad().getEscuelaEntidad();
        HashMap<String, Carrera> carreras = new HashMap<>();

        oasisCarreras.forEach((oasisCarrera) -> {
            unidos.comunes.Escuela escuela = escuelas.get(oasisCarrera.getCodEscuela());
            if (escuela != null) {
                Carrera carrera = carreras.get(oasisCarrera.getCodCarrera());
                if (carrera == null) {
                    carrera = new Carrera();
                    carrera.setCodCarrera(oasisCarrera.getCodCarrera());
                    carrera.setNombre(oasisCarrera.getCarrera());
                    escuela.addCarrera(carrera);
                    carreras.put(oasisCarrera.getCodCarrera(), carrera);
                }
            }
        });
        eliminarEscuelassinCarreras();
        eliminarFacultadsinEscuelas();
        return carreras;
    }

    private void eliminarFacultadsinEscuelas() {
        try {
            for (int i = 0; i < objEntidad.getFacultades().size(); i++) {
                unidos.comunes.Facultad facultad = (unidos.comunes.Facultad) objEntidad.getFacultades().get(i);
                if (facultad.getEscuelas().isEmpty()) {
                    objEntidad.getFacultades().remove(i);
                    i--;
                }
            }
        } catch (Exception e) {

        }
    }

    private void eliminarEscuelassinCarreras() {
        try {
            for (int i = 0; i < objEntidad.getFacultades().size(); i++) {
                unidos.comunes.Facultad facultad = (unidos.comunes.Facultad) objEntidad.getFacultades().get(i);
                for (int j = 0; j < facultad.getEscuelas().size(); j++) {
                    unidos.comunes.Escuela escuela = (unidos.comunes.Escuela) facultad.getEscuelas().get(j);
                    if (escuela.getCarreras().isEmpty()) {
                        facultad.getEscuelas().remove(j);
                        j--;
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    private static ArrayOfEscuelaEntidad getEscuelaEntidad() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getEscuelaEntidad();
    }

    private static ArrayOfFacultad getFacultadesTotales() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getFacultadesTotales();
    }

    private static ArrayOfEscuela getTodasEscuelas() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getTodasEscuelas();
    }

}
