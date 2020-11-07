/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.comunes;

import ec.edu.espoch.academico.MateriaPensum;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adry Qp
 */
public class Asignatura {

    private String codMateria;
    private String nombreMateria;
    private String codNivel;
    private String nivel;
    private String codArea;
    private String area;
    private Float creditos;
    private Integer horasTeoricas;
    private Integer horasPracticas;

    private List<Asignatura> prerrequisitos = new ArrayList<>();
    private List<Asignatura> correquisitos = new ArrayList<>();
    private List<Docente> docentes = new ArrayList<>();

    public Asignatura() {
        this.codMateria = "";
        this.nombreMateria = "";
        this.codNivel = "";
        this.nivel = "";
        this.codArea = "";
        this.area = "";
        this.creditos = 0f;
        this.horasTeoricas = 0;
        this.horasPracticas = 0;
    }

    public Asignatura(MateriaPensum materia) {
        if (materia != null) {
            this.codMateria = materia.getCodMateria();
            this.nombreMateria = materia.getMateria();
            this.codNivel = materia.getCodNivel();
            this.nivel = materia.getNivel();
            this.codArea = materia.getCodArea();
            this.area = materia.getArea();
            this.creditos = materia.getCreditos();
            this.horasTeoricas = materia.getHorasTeoricas();
            this.horasPracticas = materia.getHorasPracticas();
        }
    }

    /**
     * @return the codMateria
     */
    public String getCodMateria() {
        return codMateria;
    }

    /**
     * @param codMateria the codMateria to set
     */
    public void setCodMateria(String codMateria) {
        this.codMateria = codMateria;
    }

    /**
     * @return the nombreMateria
     */
    public String getNombreMateria() {
        return nombreMateria;
    }

    /**
     * @param nombreMateria the nombreMateria to set
     */
    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
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

    /**
     * @return the codNivel
     */
    public String getCodNivel() {
        return codNivel;
    }

    /**
     * @param codNivel the codNivel to set
     */
    public void setCodNivel(String codNivel) {
        this.codNivel = codNivel;
    }

    /**
     * @return the nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the codArea
     */
    public String getCodArea() {
        return codArea;
    }

    /**
     * @param codArea the codArea to set
     */
    public void setCodArea(String codArea) {
        this.codArea = codArea;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the creditos
     */
    public Float getCreditos() {
        return creditos;
    }

    /**
     * @param creditos the creditos to set
     */
    public void setCreditos(Float creditos) {
        this.creditos = creditos;
    }

    /**
     * @return the horasTeoricas
     */
    public Integer getHorasTeoricas() {
        return horasTeoricas;
    }

    /**
     * @param horasTeoricas the horasTeoricas to set
     */
    public void setHorasTeoricas(Integer horasTeoricas) {
        this.horasTeoricas = horasTeoricas;
    }

    /**
     * @return the horasPracticas
     */
    public Integer getHorasPracticas() {
        return horasPracticas;
    }

    /**
     * @param horasPracticas the horasPracticas to set
     */
    public void setHorasPracticas(Integer horasPracticas) {
        this.horasPracticas = horasPracticas;
    }

    public ConcurrentHashMap<String, Docente> DocentesToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de docentes
        for (Docente docente : docentes) {
            result.put(docente.getCedula(), docente);
        }
        return result;
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
            Logger.getLogger("Asignatura").log(Level.SEVERE, "Asignatura|setDocentes: ".concat(e.getMessage()));
        }
    }

    public void addPrerrequisito(Asignatura asignaturaPrerrequisito) {
        if (prerrequisitos == null) {
            prerrequisitos = new ArrayList<>();
        }
        prerrequisitos.add(asignaturaPrerrequisito);
    }

    public void addCorrequisito(Asignatura asignaturaCorrequisito) {
        if (correquisitos == null) {
            correquisitos = new ArrayList<>();
        }
        correquisitos.add(asignaturaCorrequisito);
    }

    public void addDocente(Docente docente) {
        if (this.docentes == null) {
            this.docentes = new ArrayList<>();
        }
        this.docentes.add(docente);
    }
}
