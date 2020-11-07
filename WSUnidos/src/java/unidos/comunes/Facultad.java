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
public class Facultad {

    private String decano;
    private String viceDecano;
    private String fechaCreacion;
    private String ubicacion;
    private String codEstado;
    private String codFacultad;
    private String nombre;
    private List<Escuela> escuelas = new ArrayList();
    private List<Docente> docentes = new ArrayList();

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

    public Facultad(ec.edu.espoch.academico.Facultad facultadOASis) {
        this.setCodFacultad(facultadOASis.getCodigo());
        this.setNombre(facultadOASis.getNombre());
        this.setDecano(facultadOASis.getDecano());
        this.setViceDecano(facultadOASis.getVicedecano());
        this.setFechaCreacion(facultadOASis.getFechaCreacion());
        this.setUbicacion(facultadOASis.getUbicacion());
        this.setCodEstado(facultadOASis.getCodEstado());

    }

    public Facultad() {
    }

    /**
     * @return the decano
     */
    public String getDecano() {
        return decano;
    }

    /**
     * @param decano the decano to set
     */
    public void setDecano(String decano) {
        this.decano = decano;
    }

    /**
     * @return the viceDecano
     */
    public String getViceDecano() {
        return viceDecano;
    }

    /**
     * @param viceDecano the viceDecano to set
     */
    public void setViceDecano(String viceDecano) {
        this.viceDecano = viceDecano;
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
     * @param escuelas the escuelas to set
     */
    public void setEscuelas(List<Escuela> escuelas) {
        try {
            this.escuelas = escuelas;
        } catch (Exception e) {
            Logger.getLogger("Facultad").log(Level.SEVERE, "Facultad|setEscuelas: ".concat(e.getMessage()));
        }
    }

    /**
     * @return the escuelas
     */
    public List<Escuela> getEscuelas() {
        List<Escuela> result = null;
        try {
            result = this.escuelas;
        } catch (Exception e) {
            Logger.getLogger("Facultad").log(Level.SEVERE, "Facultad|getEscuelas: ".concat(e.getMessage()));
        }
        return result;
    }

    public void addEscuela(Escuela escuela) {
        try {
            if (this.escuelas == null) {
                this.escuelas = new ArrayList<>();
            }
            ConcurrentHashMap<String, Escuela> chmEscuelas = this.EscuelasToHashMap();
            chmEscuelas.put(escuela.getCodEscuela(), escuela);
            this.setEscuelas(chmEscuelas);
        } catch (Exception e) {
            Logger.getLogger("Facultad").log(Level.SEVERE, "Facultad|addEscuela: ".concat(e.getMessage()));
        }
    }

    public ConcurrentHashMap<String, Escuela> EscuelasToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de docentes
        for (Escuela escuela : escuelas) {
            result.putIfAbsent(escuela.getCodEscuela(), escuela);
        }
        return result;
    }

    public void setEscuelas(ConcurrentHashMap<String, Escuela> escuelas) {
        try {
            this.escuelas.clear();
            Enumeration<String> codEscuelas = escuelas.keys();
            String codEscuela;
            while (codEscuelas.hasMoreElements()) {
                codEscuela = (String) codEscuelas.nextElement();
                Escuela escuela = escuelas.get(codEscuela);
                this.escuelas.add(escuela);
            }
        } catch (Exception e) {
            Logger.getLogger("Carrera").log(Level.SEVERE, "Escuela|setCarreras: ".concat(e.getMessage()));
        }
    }

    /**
     * @param docentes the escuelas to set
     */
    public void setDocentes(List<Docente> docentes) {
        try {
            this.docentes = docentes;
        } catch (Exception e) {
            Logger.getLogger("Facultad").log(Level.SEVERE, "Facultad|setDocentes: ".concat(e.getMessage()));
        }
    }

    /**
     * @return the escuelas
     */
    public List<Docente> getDocentes() {
        List<Docente> result = null;
        try {
            result = this.docentes;
        } catch (Exception e) {
            Logger.getLogger("Facultad").log(Level.SEVERE, "Facultad|getDocentes: ".concat(e.getMessage()));
        }
        return result;
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
            Logger.getLogger("Facultad").log(Level.SEVERE, "Facultad|addDocente: ".concat(e.getMessage()));
        }
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
            Logger.getLogger("Facultad").log(Level.SEVERE, "Facultad|setDocentes: ".concat(e.getMessage()));
        }
    }
}
