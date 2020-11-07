package unidos.comunes;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Carrera {
    
    private String codEscuela;
    private String codEstado;
    private String codCarrera;
    private String nombre;
    private List<Asignatura> asignaturas = new ArrayList<>();
    private List<Docente> docentes = new ArrayList<>();
    private List<CampoFormacion> camposFormacion = new ArrayList<>();

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Carrera() {
        this.setCodCarrera("");
        this.setNombre("");
        this.codEscuela = "";
        this.codEstado = "ABI";
    }

    /**
     * @return the codEscuela
     */
    public String getCodEscuela() {
        return codEscuela;
    }

    /**
     * @param codEscuela the codEscuela to set
     */
    public void setCodEscuela(String codEscuela) {
        this.codEscuela = codEscuela;
    }

    /**
     * @return the codEstado
     */
    public String getCodEstado() {
        return codEstado;
    }

    /**
     * @param codEstado the codEstado to set
     */
    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }

    /**
     * @return the docentes
     */
    public List<Docente> getDocentes() {
        return docentes;
    }

    /**
     * @param docentes the docentes to set
     */
    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }
    
    public ConcurrentHashMap<String, Docente> DocentesToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de docentes
        for (Docente docente : docentes) {
            result.put(docente.getCedula(), docente);
        }
        return result;
    }
    
    public ConcurrentHashMap<String, CampoFormacion> CamposFormacionToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de docentes
        for (CampoFormacion campoFormacion : camposFormacion) {
            result.put(campoFormacion.getCodCampoF(), campoFormacion);
        }
        return result;
    }
    
    public Docente getDocente(String codCarrera) {
        ConcurrentHashMap docentesCarrera = this.DocentesToHashMap();
        Docente docente = (Docente) docentesCarrera.get(codCarrera);
        return docente;
    }
    
    public void setDocentes(ConcurrentHashMap<String, Docente> docentes) {
        try {
            this.docentes.clear();
            Enumeration<String> cedulas = docentes.keys();
            String cedula;
            while (cedulas.hasMoreElements()) {
                cedula = (String) cedulas.nextElement();
                Docente docente = docentes.get(cedula);
                this.docentes.add(docente);
            }
        } catch (Exception e) {
            Logger.getLogger("Carrera").log(Level.SEVERE, "Carrera|setDocentes: ".concat(e.getMessage()));
        }
    }

    //******************************************************************************************************
    //*                                    CAMPOS DE FORMACION                                             *
    //******************************************************************************************************
    public ConcurrentHashMap<String, CampoFormacion> CampoFormacionToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de campos de formaciones
        for (CampoFormacion campo : camposFormacion) {
            result.put(campo.getCodCampoF(), campo);
        }
        return result;
    }
    
    public CampoFormacion getCampoFormacion(String codCampoFormacion) {
        ConcurrentHashMap carreraCamposFormacion = this.CampoFormacionToHashMap();
        CampoFormacion campoFormacion = (CampoFormacion) carreraCamposFormacion.get(codCampoFormacion);
        return campoFormacion;
    }
    
    public void setCamposFormacion(ConcurrentHashMap<String, CampoFormacion> campoformaciones) {
        try {
            this.camposFormacion.clear();
            Enumeration<String> codCampos = campoformaciones.keys();
            String codCampo;
            while (codCampos.hasMoreElements()) {
                codCampo = (String) codCampos.nextElement();
                CampoFormacion campo = campoformaciones.get(codCampo);
                this.camposFormacion.add(campo);
            }
        } catch (Exception e) {
            Logger.getLogger("Carrera").log(Level.SEVERE, "Carrera|setCamposFormacion: ".concat(e.getMessage()));
        }
    }

    /**
     * @return the campoformaciones
     */
    public List<CampoFormacion> getCamposFormacion() {
        return camposFormacion;
    }

    /**
     * @param camposFormacion the camposFormacion to set
     */
    public void setCamposFormacion(List<CampoFormacion> camposFormacion) {
        this.camposFormacion = camposFormacion;
    }

    /**
     * @return the asignaturas
     */
    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    /**
     * @param asignaturas the asignaturas to set
     */
    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    //******************************************************************************************************
    //*                                         ASIGNATURAS                                                *
    //******************************************************************************************************
    public ConcurrentHashMap<String, Asignatura> AsignaturasToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de asignaturas
        for (Asignatura asignatura : asignaturas) {
            result.put(asignatura.getCodMateria(), asignatura);
        }
        return result;
    }
    
    public Asignatura getAsignatura(String codMateria) {
        ConcurrentHashMap carreraAsignaturas = this.AsignaturasToHashMap();
        Asignatura asignatura = (Asignatura) carreraAsignaturas.get(codMateria);
        return asignatura;
    }
    
    public void setAsignaturas(ConcurrentHashMap<String, Asignatura> asignaturas) {
        try {
            this.asignaturas.clear();
            Enumeration<String> codMaterias = asignaturas.keys();
            String codMate;
            while (codMaterias.hasMoreElements()) {
                codMate = (String) codMaterias.nextElement();
                Asignatura asignatura = asignaturas.get(codMate);
                this.asignaturas.add(asignatura);
            }
        } catch (Exception e) {
            Logger.getLogger("Carrera").log(Level.SEVERE, "Carrera|Asignatura: ".concat(e.getMessage()));
        }
    }

    //******************************************************************************************************
    //*                     ASIGNATURAS DE CAMPOS DE FORMACION                                             *
    //******************************************************************************************************
    public ConcurrentHashMap<String, Asignatura> AsignaturaToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de asignaturas
        for (Asignatura asignatura : asignaturas) {
            result.put(asignatura.getCodMateria(), asignatura);
        }
        return result;
    }
    
    public void setAsignaturasCF(ConcurrentHashMap<String, Asignatura> asignaturas) {
        try {
            this.asignaturas.clear();
            Enumeration<String> codAsignaturas = asignaturas.keys();
            String codAsignatura;
            while (codAsignaturas.hasMoreElements()) {
                codAsignatura = (String) codAsignaturas.nextElement();
                Asignatura asignatura = asignaturas.get(codAsignatura);
                this.asignaturas.add(asignatura);
            }
        } catch (Exception e) {
            Logger.getLogger("Carrera").log(Level.SEVERE, "Carrera|setAsignaturasCF: ".concat(e.getMessage()));
        }
    }
    
    public List<Docente> getCamposFormacionDocentes(String codCampoFormacion) {
        List<Docente> result = null;
        try {
            ConcurrentHashMap<String, CampoFormacion> chmCamposFormacion = this.CamposFormacionToHashMap();
            CampoFormacion campoFormacion = chmCamposFormacion.get(codCampoFormacion);
            if (campoFormacion != null) {
                result = campoFormacion.getDocentes();
            }
        } catch (Exception e) {
            Logger.getLogger("Carrera").log(Level.SEVERE, "Carrera|getCamposFormacionDocentes: ".concat(e.getMessage()));
        }
        return result;
    }
    
    public List<Asignatura> getCampoFormacionAsignaturas(String codCampoFormacion) {
        List<Asignatura> result = null;
        try {
            ConcurrentHashMap<String, CampoFormacion> chmCamposFormacion = this.CamposFormacionToHashMap();
            CampoFormacion campoFormacion = chmCamposFormacion.get(codCampoFormacion);
            if (campoFormacion != null) {
                result = campoFormacion.getAsignaturas();
            }
        } catch (Exception e) {
            Logger.getLogger("Carrera").log(Level.SEVERE, "Carrera|getCamposFormacionAsignaturas: ".concat(e.getMessage()));
        }
        return result;
    }

    /**
     * @return the codCarrera
     */
    public String getCodCarrera() {
        return codCarrera;
    }

    /**
     * @param codCarrera the codCarrera to set
     */
    public void setCodCarrera(String codCarrera) {
        this.codCarrera = codCarrera;
    }
    
}
