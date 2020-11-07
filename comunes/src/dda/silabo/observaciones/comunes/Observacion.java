/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.observaciones.comunes;

/**
 *
 * @author Jorge Zaruma
 */
public class Observacion {

    private Integer id_observacion;
    private String descsec;
    private String estado;
    private String observacion;
    private String Fecha;
    private Integer idUnidad;

    /**
     * @return the descsec
     */
    public String getDescsec() {
        return descsec;
    }

    /**
     * @param descsec the descsec to set
     */
    public void setDescsec(String descsec) {
        this.descsec = descsec;
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
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the Fecha
     */
    public String getFecha() {
        return Fecha;
    }

    /**
     * @param Fecha the Fecha to set
     */
    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    /**
     * @return the id_observacion
     */
    public Integer getId_observacion() {
        return id_observacion;
    }

    /**
     * @param id_observacion the id_observacion to set
     */
    public void setId_observacion(Integer id_observacion) {
        this.id_observacion = id_observacion;
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
