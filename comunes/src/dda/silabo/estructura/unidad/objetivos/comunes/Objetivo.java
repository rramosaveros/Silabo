/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidad.objetivos.comunes;

/**
 *
 * @author Jorge Zaruma
 */
public class Objetivo {
    private Integer idObjetivo;
    private String descripcion;
    private Integer idSubseccion;
    private Integer idSilabo;
    private Integer idUnidad;

    /**
     * @return the idObjetivo
     */
    public Integer getIdObjetivo() {
        return idObjetivo;
    }

    /**
     * @param idObjetivo the idObjetivo to set
     */
    public void setIdObjetivo(Integer idObjetivo) {
        this.idObjetivo = idObjetivo;
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

    /**
     * @return the idSubseccion
     */
    public Integer getIdSubseccion() {
        return idSubseccion;
    }

    /**
     * @param idSubseccion the idSubseccion to set
     */
    public void setIdSubseccion(Integer idSubseccion) {
        this.idSubseccion = idSubseccion;
    }

    /**
     * @return the idSilabo
     */
    public Integer getIdSilabo() {
        return idSilabo;
    }

    /**
     * @param idSilabo the idSilabo to set
     */
    public void setIdSilabo(Integer idSilabo) {
        this.idSilabo = idSilabo;
    }

    /**
     * @return the idUnidad
     */
    public Integer getIdUnidad() {
        return idUnidad;
    }

    /**
     * @param idUnidad the idUnidad to set
     */
    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }
}
