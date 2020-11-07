package dda.silabo.clases.unidos;

import dda.silabo.importacion.comunes.Periodo;
import dda.silabo.reportes.comunes.CriterioSilabo;
import dda.silabo.reportes.comunes.Estados;
import dda.silabo.roles.comunes.Rol;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarreraUnidos {

    private String codEscuela;
    private String codEstado;
    private String codCarrera;
    private String nombre;
    private Integer idCarrera;
    private Estados estados = new Estados();
    private List<AsignaturaUnidos> asignaturas = new ArrayList<>();
    private List<DocenteUnidos> docentes = new ArrayList<>();
    private List<CampoFormacionUnidos> camposFormacion = new ArrayList<>();
    private List<CriterioSilabo> criteriosSilabo = new ArrayList<>();
    private String fncClick;
    private Rol roles = new Rol();
    private List<Estados> estadosSilabo = new ArrayList<>();
    private List<Periodo> periodos = new ArrayList<>();
    private String selected;

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

    public CarreraUnidos() {
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
    public List<DocenteUnidos> getDocentes() {
        return docentes;
    }

    /**
     * @param docentes the docentes to set
     */
    public void setDocentes(List<DocenteUnidos> docentes) {
        this.docentes = docentes;
    }

    public ConcurrentHashMap<String, DocenteUnidos> DocentesToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de docentes
        for (DocenteUnidos docente : docentes) {
            result.put(docente.getCedula(), docente);
        }
        return result;
    }

    public ConcurrentHashMap<String, CampoFormacionUnidos> CamposFormacionToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de docentes
        for (CampoFormacionUnidos campoFormacion : camposFormacion) {
            result.put(campoFormacion.getCodCampoF(), campoFormacion);
        }
        return result;
    }

    public DocenteUnidos getDocente(String codCarrera) {
        ConcurrentHashMap docentesCarrera = this.DocentesToHashMap();
        DocenteUnidos docente = (DocenteUnidos) docentesCarrera.get(codCarrera);
        return docente;
    }

    public void setDocentes(ConcurrentHashMap<String, DocenteUnidos> docentes) {
        try {
            this.docentes.clear();
            Enumeration<String> cedulas = docentes.keys();
            String cedula;
            while (cedulas.hasMoreElements()) {
                cedula = (String) cedulas.nextElement();
                DocenteUnidos docente = docentes.get(cedula);
                this.docentes.add(docente);
            }
        } catch (Exception e) {
            Logger.getLogger("Carrera").log(Level.SEVERE, "Carrera|setDocentes: ".concat(e.getMessage()));
        }
    }

    //******************************************************************************************************
    //*                                    CAMPOS DE FORMACION                                             *
    //******************************************************************************************************
    public ConcurrentHashMap<String, CampoFormacionUnidos> CampoFormacionToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de campos de formaciones
        for (CampoFormacionUnidos campo : camposFormacion) {
            result.put(campo.getCodCampoF(), campo);
        }
        return result;
    }

    public CampoFormacionUnidos getCampoFormacion(String codCampoFormacion) {
        ConcurrentHashMap carreraCamposFormacion = this.CampoFormacionToHashMap();
        CampoFormacionUnidos campoFormacion = (CampoFormacionUnidos) carreraCamposFormacion.get(codCampoFormacion);
        return campoFormacion;
    }

    public void setCamposFormacion(ConcurrentHashMap<String, CampoFormacionUnidos> campoformaciones) {
        try {
            this.camposFormacion.clear();
            Enumeration<String> codCampos = campoformaciones.keys();
            String codCampo;
            while (codCampos.hasMoreElements()) {
                codCampo = (String) codCampos.nextElement();
                CampoFormacionUnidos campo = campoformaciones.get(codCampo);
                this.camposFormacion.add(campo);
            }
        } catch (Exception e) {
            Logger.getLogger("Carrera").log(Level.SEVERE, "Carrera|setCamposFormacion: ".concat(e.getMessage()));
        }
    }

    /**
     * @return the campoformaciones
     */
    public List<CampoFormacionUnidos> getCamposFormacion() {
        return camposFormacion;
    }

    /**
     * @param camposFormacion the camposFormacion to set
     */
    public void setCamposFormacion(List<CampoFormacionUnidos> camposFormacion) {
        this.camposFormacion = camposFormacion;
    }

    /**
     * @return the asignaturas
     */
    public List<AsignaturaUnidos> getAsignaturas() {
        return asignaturas;
    }

    /**
     * @param asignaturas the asignaturas to set
     */
    public void setAsignaturas(List<AsignaturaUnidos> asignaturas) {
        this.asignaturas = asignaturas;
    }

    //******************************************************************************************************
    //*                                         ASIGNATURAS                                                *
    //******************************************************************************************************
    public ConcurrentHashMap<String, AsignaturaUnidos> AsignaturasToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de asignaturas
        for (AsignaturaUnidos asignatura : asignaturas) {
            result.put(asignatura.getCodMateria(), asignatura);
        }
        return result;
    }

    public AsignaturaUnidos getAsignatura(String codMateria) {
        ConcurrentHashMap carreraAsignaturas = this.AsignaturasToHashMap();
        AsignaturaUnidos asignatura = (AsignaturaUnidos) carreraAsignaturas.get(codMateria);
        return asignatura;
    }

    public void setAsignaturas(ConcurrentHashMap<String, AsignaturaUnidos> asignaturas) {
        try {
            this.asignaturas.clear();
            Enumeration<String> codMaterias = asignaturas.keys();
            String codMate;
            while (codMaterias.hasMoreElements()) {
                codMate = (String) codMaterias.nextElement();
                AsignaturaUnidos asignatura = asignaturas.get(codMate);
                this.asignaturas.add(asignatura);
            }
        } catch (Exception e) {
            Logger.getLogger("Carrera").log(Level.SEVERE, "Carrera|Asignatura: ".concat(e.getMessage()));
        }
    }

    //******************************************************************************************************
    //*                     ASIGNATURAS DE CAMPOS DE FORMACION                                             *
    //******************************************************************************************************
    public ConcurrentHashMap<String, AsignaturaUnidos> AsignaturaToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de asignaturas
        for (AsignaturaUnidos asignatura : asignaturas) {
            result.put(asignatura.getCodMateria(), asignatura);
        }
        return result;
    }

    public void setAsignaturasCF(ConcurrentHashMap<String, AsignaturaUnidos> asignaturas) {
        try {
            this.asignaturas.clear();
            Enumeration<String> codAsignaturas = asignaturas.keys();
            String codAsignatura;
            while (codAsignaturas.hasMoreElements()) {
                codAsignatura = (String) codAsignaturas.nextElement();
                AsignaturaUnidos asignatura = asignaturas.get(codAsignatura);
                this.asignaturas.add(asignatura);
            }
        } catch (Exception e) {
            Logger.getLogger("Carrera").log(Level.SEVERE, "Carrera|setAsignaturasCF: ".concat(e.getMessage()));
        }
    }

    public List<DocenteUnidos> getCamposFormacionDocentes(String codCampoFormacion) {
        List<DocenteUnidos> result = null;
        try {
            ConcurrentHashMap<String, CampoFormacionUnidos> chmCamposFormacion = this.CamposFormacionToHashMap();
            CampoFormacionUnidos campoFormacion = chmCamposFormacion.get(codCampoFormacion);
            if (campoFormacion != null) {
                result = campoFormacion.getDocentes();
            }
        } catch (Exception e) {
            Logger.getLogger("Carrera").log(Level.SEVERE, "Carrera|getCamposFormacionDocentes: ".concat(e.getMessage()));
        }
        return result;
    }

    public List<AsignaturaUnidos> getCampoFormacionAsignaturas(String codCampoFormacion) {
        List<AsignaturaUnidos> result = null;
        try {
            ConcurrentHashMap<String, CampoFormacionUnidos> chmCamposFormacion = this.CamposFormacionToHashMap();
            CampoFormacionUnidos campoFormacion = chmCamposFormacion.get(codCampoFormacion);
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

    /**
     * @return the estados
     */
    public Estados getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(Estados estados) {
        this.estados = estados;
    }

    /**
     * @return the criteriosSilabo
     */
    public List<CriterioSilabo> getCriteriosSilabo() {
        return criteriosSilabo;
    }

    /**
     * @param criteriosSilabo the criteriosSilabo to set
     */
    public void setCriteriosSilabo(List<CriterioSilabo> criteriosSilabo) {
        this.criteriosSilabo = criteriosSilabo;
    }

    /**
     * @return the fncClick
     */
    public String getFncClick() {
        return fncClick;
    }

    /**
     * @param fncClick the fncClick to set
     */
    public void setFncClick(String fncClick) {
        this.fncClick = fncClick;
    }

    /**
     * @return the roles
     */
    public Rol getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Rol roles) {
        this.roles = roles;
    }

    /**
     * @return the estadosSilabo
     */
    public List<Estados> getEstadosSilabo() {
        return estadosSilabo;
    }

    /**
     * @param estadosSilabo the estadosSilabo to set
     */
    public void setEstadosSilabo(List<Estados> estadosSilabo) {
        this.estadosSilabo = estadosSilabo;
    }

    /**
     * @return the periodos
     */
    public List<Periodo> getPeriodos() {
        return periodos;
    }

    /**
     * @param periodos the periodos to set
     */
    public void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos;
    }

    /**
     * @return the idCarrera
     */
    public Integer getIdCarrera() {
        return idCarrera;
    }

    /**
     * @param idCarrera the idCarrera to set
     */
    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    /**
     * @return the selected
     */
    public String getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(String selected) {
        this.selected = selected;
    }

}
