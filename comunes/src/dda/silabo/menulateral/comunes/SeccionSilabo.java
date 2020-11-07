/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.menulateral.comunes;

/**
 *
 * @author Jorge Zaruma
 */
public class SeccionSilabo {

    private String tipoSeccion;
    private String estado;
    private Integer idUnidad;
    private String descripcion;
    private Integer idDescripcion;

    /**
     * @return the tipoSeccion
     */
    public String getTipoSeccion() {
        return tipoSeccion;
    }

    /**
     * @param tipoSeccion the tipoSeccion to set
     */
    public void setTipoSeccion(String tipoSeccion) {
        this.tipoSeccion = tipoSeccion;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
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
     * @return the idDescripcion
     */
    public Integer getIdDescripcion() {
        return idDescripcion;
    }

    /**
     * @param idDescripcion the idDescripcion to set
     */
    public void setIdDescripcion(Integer idDescripcion) {
        this.idDescripcion = idDescripcion;
    }
}
