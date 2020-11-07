/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.reportes.comunes;

/**
 *
 * @author Jorge Zaruma
 */
public class CriterioSilabo {

    private Integer idCriterio;
    private String descripcion;
    private String tipo;
    private String enlace;
    private Integer cantidad;

    /**
     * @return the idCriterio
     */
    public Integer getIdCriterio() {
        return idCriterio;
    }

    /**
     * @param idCriterio the idCriterio to set
     */
    public void setIdCriterio(Integer idCriterio) {
        this.idCriterio = idCriterio;
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
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the enlace
     */
    public String getEnlace() {
        return enlace;
    }

    /**
     * @param enlace the enlace to set
     */
    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
