/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.reportes.comunes;

import dda.silabo.clases.unidos.AsignaturaUnidos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class Estados {

    private Integer Inicio;
    private Integer Revision;
    private Integer Corregir;
    private Integer Aprobado;
    private List<Estados> estados = new ArrayList<>();
    private List<AsignaturaUnidos> asignatura = new ArrayList<>();
    private String descripcion;

    /**
     * @return the Inicio
     */
    public Integer getInicio() {
        return Inicio;
    }

    /**
     * @param Inicio the Inicio to set
     */
    public void setInicio(Integer Inicio) {
        this.Inicio = Inicio;
    }

    /**
     * @return the Revision
     */
    public Integer getRevision() {
        return Revision;
    }

    /**
     * @param Revision the Revision to set
     */
    public void setRevision(Integer Revision) {
        this.Revision = Revision;
    }

    /**
     * @return the Corregir
     */
    public Integer getCorregir() {
        return Corregir;
    }

    /**
     * @param Corregir the Corregir to set
     */
    public void setCorregir(Integer Corregir) {
        this.Corregir = Corregir;
    }

    /**
     * @return the Aprobado
     */
    public Integer getAprobado() {
        return Aprobado;
    }

    /**
     * @param Aprobado the Aprobado to set
     */
    public void setAprobado(Integer Aprobado) {
        this.Aprobado = Aprobado;
    }

    /**
     * @return the estados
     */
    public List<Estados> getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(List<Estados> estados) {
        this.estados = estados;
    }

    /**
     * @return the asignatura
     */
    public List<AsignaturaUnidos> getAsignatura() {
        return asignatura;
    }

    /**
     * @param asignatura the asignatura to set
     */
    public void setAsignatura(List<AsignaturaUnidos> asignatura) {
        this.asignatura = asignatura;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
