/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.clases.unidos;

import dda.silabo.reportes.comunes.Estados;
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
public class CampoFormacionUnidos {

    private String codCampoF;
    private String descCampoF;
    private Estados estados = new Estados();
    private List<DocenteUnidos> docentes = new ArrayList<>();
    private List<AsignaturaUnidos> asignaturas = new ArrayList<>();

    public CampoFormacionUnidos() {
    }

    public CampoFormacionUnidos(String codCampoF, String descCampoF) {
        this.codCampoF = codCampoF;
        this.descCampoF = descCampoF;
    }

    public void addAsignatura(AsignaturaUnidos asignatura) {
        if (this.asignaturas == null) {
            this.asignaturas = new ArrayList<>();
        }
        this.asignaturas.add(asignatura);
    }

    public void addDocente(DocenteUnidos docente) {
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
    public List<DocenteUnidos> getDocentes() {
        return docentes;
    }

    /**
     * @param docentes the docentes to set
     */
    public void setDocentes(List<DocenteUnidos> docentes) {
        this.docentes = docentes;
    }

    public ConcurrentHashMap<String, AsignaturaUnidos> AsignaturasToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de Asignaturas
        for (AsignaturaUnidos asignatura : asignaturas) {
            result.put(asignatura.getCodMateria(), asignatura);
        }
        return result;
    }

    public void setAsignaturas(ConcurrentHashMap<String, AsignaturaUnidos> asignaturas) {
        try {
            this.asignaturas.clear();
            Enumeration<String> codMaterias = asignaturas.keys();
            String codMateria;
            while (codMaterias.hasMoreElements()) {
                codMateria = (String) codMaterias.nextElement();
                AsignaturaUnidos asignatura = asignaturas.get(codMateria);
                this.asignaturas.add(asignatura);
            }
        } catch (Exception e) {
            Logger.getLogger("CampoFormacion").log(Level.SEVERE, "CampoFormacion|setAsignaturas: ".concat(e.getMessage()));
        }
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

    public ConcurrentHashMap<String, DocenteUnidos> DocentesToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de docentes
        for (DocenteUnidos docente : docentes) {
            result.put(docente.getCedula(), docente);
        }
        return result;
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
            Logger.getLogger("CampoFormacion").log(Level.SEVERE, "CampoFormacion|setDocentes: ".concat(e.getMessage()));
        }
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
}
