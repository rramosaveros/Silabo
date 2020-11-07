/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.opciones.comunes;

/**
 *
 * @author Jorge Zaruma
 */
public class Opcion {

    private Integer idOpcion;
    private String descOpcion;
    private String estado;
    private String check;

    /**
     * @return the descRol
     */
    public String getDescRol() {
        return descOpcion;
    }

    /**
     * @param descRol the descRol to set
     */
    public void setDescRol(String descRol) {
        this.descOpcion = descRol;
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
     * @return the idOpcion
     */
    public Integer getIdOpcion() {
        return idOpcion;
    }

    /**
     * @param idOpcion the idOpcion to set
     */
    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    /**
     * @return the check
     */
    public String getCheck() {
        return check;
    }

    /**
     * @param check the check to set
     */
    public void setCheck(String check) {
        this.check = check;
    }

}
