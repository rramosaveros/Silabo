/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.escenarios.comunes;

/**
 *
 * @author Usuario
 */
public class Escenario {
    private Integer idEsc;
    private String descripcion; 
    private String tipoE;
    private String check; 
    private String estado;

    /**
     * @return the idEsc
     */
    public Integer getIdEsc() {
        return idEsc;
    }

    /**
     * @param idEsc the idEsc to set
     */
    public void setIdEsc(Integer idEsc) {
        this.idEsc = idEsc;
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
     * @return the tipoE
     */
    public String getTipoE() {
        return tipoE;
    }

    /**
     * @param tipoE the tipoE to set
     */
    public void setTipoE(String tipoE) {
        this.tipoE = tipoE;
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

}
