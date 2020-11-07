/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.clases.unidos;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.XMLGregorianCalendar;
import static dda.silabo.clases.unidos.EntidadUnidos.XMLGregorianCalendarToString;

/**
 *
 * @author Adry Qp
 */
public class EscuelaUnidos {

    private String director;
    private String codFacultad;
    private String fechaCreacion;
    private String ubicacion;
    private String codEstado;
    private String codEscuela;
    private String nombre;
    private Integer idEscuela;
    private List<CarreraUnidos> carreras = new ArrayList();
    private List<DocenteUnidos> docentes = new ArrayList();

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

    public EscuelaUnidos() {
    }

    /**
     * @return the director
     */
    public String getDirector() {
        return director;
    }

    /**
     * @param director the director to set
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * @return the codFacultad
     */
    public String getCodFacultad() {
        return codFacultad;
    }

    /**
     * @param codFacultad the codFacultad to set
     */
    public void setCodFacultad(String codFacultad) {
        this.codFacultad = codFacultad;
    }

    /**
     * @return the fechaCreacion
     */
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(XMLGregorianCalendar fechaCreacion) {
        this.fechaCreacion = XMLGregorianCalendarToString(fechaCreacion);
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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
     * @return the carreras
     */
    public List<CarreraUnidos> getCarreras() {
        return carreras;
    }

    /**
     * @param carreras the carreras to set
     */
    public void setCarreras(List<CarreraUnidos> carreras) {
        this.carreras = carreras;
    }

    public void addCarrera(CarreraUnidos carrera) {
        try {
            if (this.carreras == null) {
                this.carreras = new ArrayList<>();
            }

            ConcurrentHashMap<String, CarreraUnidos> chmCarreras = this.CarrerasToHashMap();
            chmCarreras.put(carrera.getCodCarrera(), carrera);
            this.setCarreras(chmCarreras);
        } catch (Exception e) {
            Logger.getLogger("Escuela").log(Level.SEVERE, "Escuela|addCarrera: ".concat(e.getMessage()));
        }
    }

    public void addDocente(DocenteUnidos docente) {
        try {
            if (this.docentes == null) {
                this.docentes = new ArrayList<>();
            }

            ConcurrentHashMap<String, DocenteUnidos> chmDocentes = this.DocentesToHashMap();
            chmDocentes.put(docente.getCedula(), docente);
            this.setDocentes(chmDocentes);
        } catch (Exception e) {
            Logger.getLogger("Escuela").log(Level.SEVERE, "Escuela|addDocente: ".concat(e.getMessage()));
        }
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
            result.putIfAbsent(docente.getCedula(), docente);
        }
        return result;
    }

    public void setDocentes(ConcurrentHashMap<String, DocenteUnidos> docentes) {
        try {
            this.docentes.clear();
            Enumeration<String> codDocentes = docentes.keys();
            String codDocente;
            while (codDocentes.hasMoreElements()) {
                codDocente = (String) codDocentes.nextElement();
                DocenteUnidos docente = docentes.get(codDocente);
                this.docentes.add(docente);
            }
        } catch (Exception e) {
            Logger.getLogger("Escuela").log(Level.SEVERE, "Escuela|setDocentes: ".concat(e.getMessage()));
        }
    }

    public ConcurrentHashMap<String, CarreraUnidos> CarrerasToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de carreras
        for (CarreraUnidos carrera : carreras) {
            result.putIfAbsent(carrera.getCodCarrera(), carrera);
        }
        return result;
    }

    public void setCarreras(ConcurrentHashMap<String, CarreraUnidos> carreras) {
        try {
            this.carreras.clear();
            Enumeration<String> codCarreras = carreras.keys();
            String codCarrera;
            while (codCarreras.hasMoreElements()) {
                codCarrera = (String) codCarreras.nextElement();
                CarreraUnidos carrera = carreras.get(codCarrera);
                this.carreras.add(carrera);
            }
        } catch (Exception e) {
            Logger.getLogger("Escuela").log(Level.SEVERE, "Escuela|setCarreras: ".concat(e.getMessage()));
        }
    }

    /**
     * @return the idEscuela
     */
    public Integer getIdEscuela() {
        return idEscuela;
    }

    /**
     * @param idEscuela the idEscuela to set
     */
    public void setIdEscuela(Integer idEscuela) {
        this.idEscuela = idEscuela;
    }
}
