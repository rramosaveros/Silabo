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
public class CampoFormacion {

    private String codCampoF;
    private String descCampoF;

    private List<Docente> docentes = new ArrayList<>();
    private List<Asignatura> asignaturas = new ArrayList<>();

    public CampoFormacion() {
    }

    public CampoFormacion(MateriaPensum materia) {
        this.codCampoF = materia.getCodArea();
        this.descCampoF = materia.getArea();
    }

    public CampoFormacion(String codCampoF, String descCampoF) {
        this.codCampoF = codCampoF;
        this.descCampoF = descCampoF;
    }

    public void addAsignatura(Asignatura asignatura) {
        if (this.asignaturas == null) {
            this.asignaturas = new ArrayList<>();
        }
        this.asignaturas.add(asignatura);
    }

    public void addDocente(Docente docente) {
        if (this.docentes == null) {
            this.docentes = new ArrayList<>();
        }
        this.docentes.add(docente);
    }

    /**
     * @return the codCampoF
     */
    public String getCodCampoF() {
        return codCampoF;
    }

    /**
     * @param codCampoF the codCampoF to set
     */
    public void setCodCampoF(String codCampoF) {
        this.codCampoF = codCampoF;
    }

    /**
     * @return the descCampoF
     */
    public String getDescCampoF() {
        return descCampoF;
    }

    /**
     * @param descCampoF the descCampoF to set
     */
    public void setDescCampoF(String descCampoF) {
        this.descCampoF = descCampoF;
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

    public ConcurrentHashMap<String, Asignatura> AsignaturasToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de Asignaturas
        for (Asignatura asignatura : asignaturas) {
            result.put(asignatura.getCodMateria(), asignatura);
        }
        return result;
    }

    public void setAsignaturas(ConcurrentHashMap<String, Asignatura> asignaturas) {
        try {
            this.asignaturas.clear();
            Enumeration<String> codMaterias = asignaturas.keys();
            String codMateria;
            while (codMaterias.hasMoreElements()) {
                codMateria = (String) codMaterias.nextElement();
                Asignatura asignatura = asignaturas.get(codMateria);
                this.asignaturas.add(asignatura);
            }
        } catch (Exception e) {
            Logger.getLogger("CampoFormacion").log(Level.SEVERE, "CampoFormacion|setAsignaturas: ".concat(e.getMessage()));
        }
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
            Logger.getLogger("CampoFormacion").log(Level.SEVERE, "CampoFormacion|setDocentes: ".concat(e.getMessage()));
        }
    }
}
