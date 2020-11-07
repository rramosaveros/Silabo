/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.comunes;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.XMLGregorianCalendar;
import static unidos.comunes.Entidad.XMLGregorianCalendarToString;

/**
 *
 * @author Adry Qp
 */
public class Escuela {

    private String director;
    private String codFacultad;
    private String fechaCreacion;
    private String ubicacion;
    private String codEstado;
    private String codEscuela;
    private String nombre;

    private List<Carrera> carreras = new ArrayList();
    private List<Docente> docentes = new ArrayList();

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

    public Escuela() {
    }

    public Escuela(ec.edu.espoch.academico.Escuela escuelaOASis) {
        this.setCodEscuela(escuelaOASis.getCodigo());
        this.setNombre(escuelaOASis.getNombre());
        this.setDirector(escuelaOASis.getDirector());
        this.setCodFacultad(escuelaOASis.getCodFacultad());
        this.setFechaCreacion(escuelaOASis.getFechaCreacion());
        this.setUbicacion(escuelaOASis.getUbicacion());
        this.setCodEstado(escuelaOASis.getCodEstado());
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
    public List<Carrera> getCarreras() {
        return carreras;
    }

    /**
     * @param carreras the carreras to set
     */
    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    public void addCarrera(Carrera carrera) {
        try {
            if (this.carreras == null) {
                this.carreras = new ArrayList<>();
            }

            ConcurrentHashMap<String, Carrera> chmCarreras = this.CarrerasToHashMap();
            chmCarreras.put(carrera.getCodCarrera(), carrera);
            this.setCarreras(chmCarreras);
        } catch (Exception e) {
            Logger.getLogger("Escuela").log(Level.SEVERE, "Escuela|addCarrera: ".concat(e.getMessage()));
        }
    }

    public void addDocente(Docente docente) {
        try {
            if (this.docentes == null) {
                this.docentes = new ArrayList<>();
            }

            ConcurrentHashMap<String, Docente> chmDocentes = this.DocentesToHashMap();
            chmDocentes.put(docente.getCedula(), docente);
            this.setDocentes(chmDocentes);
        } catch (Exception e) {
            Logger.getLogger("Escuela").log(Level.SEVERE, "Escuela|addDocente: ".concat(e.getMessage()));
        }
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
            result.putIfAbsent(docente.getCedula(), docente);
        }
        return result;
    }

    public void setDocentes(ConcurrentHashMap<String, Docente> docentes) {
        try {
            this.docentes.clear();
            Enumeration<String> codDocentes = docentes.keys();
            String codDocente;
            while (codDocentes.hasMoreElements()) {
                codDocente = (String) codDocentes.nextElement();
                Docente docente = docentes.get(codDocente);
                this.docentes.add(docente);
            }
        } catch (Exception e) {
            Logger.getLogger("Escuela").log(Level.SEVERE, "Escuela|setDocentes: ".concat(e.getMessage()));
        }
    }

    public ConcurrentHashMap<String, Carrera> CarrerasToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de carreras
        for (Carrera carrera : carreras) {
            result.putIfAbsent(carrera.getCodCarrera(), carrera);
        }
        return result;
    }

    public void setCarreras(ConcurrentHashMap<String, Carrera> carreras) {
        try {
            this.carreras.clear();
            Enumeration<String> codCarreras = carreras.keys();
            String codCarrera;
            while (codCarreras.hasMoreElements()) {
                codCarrera = (String) codCarreras.nextElement();
                Carrera carrera = carreras.get(codCarrera);
                this.carreras.add(carrera);
            }
        } catch (Exception e) {
            Logger.getLogger("Escuela").log(Level.SEVERE, "Escuela|setCarreras: ".concat(e.getMessage()));
        }
    }
}
