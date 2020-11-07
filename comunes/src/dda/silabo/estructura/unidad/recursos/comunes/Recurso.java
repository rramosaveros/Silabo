/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidad.recursos.comunes;

import java.io.Serializable;

/**
 *
 * @author Jorge
 */
public class Recurso implements Serializable {

    private Integer idRecurso;
    private String strDescripcion;
    private String chv_check;
    private String estado;

    /**
     * @return the idRecurso
     */
    public Integer getIdRecurso() {
        return idRecurso;
    }

    /**
     * @param idRecurso the idRecurso to set
     */
    public void setIdRecurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    /**
     * @return the strDescripcion
     */
    public String getStrDescripcion() {
        return strDescripcion;
    }

    /**
     * @param strDescripcion the strDescripcion to set
     */
    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    /**
     * @return the chv_check
     */
    public String getChv_check() {
        return chv_check;
    }

    /**
     * @param chv_check the chv_check to set
     */
    public void setChv_check(String chv_check) {
        this.chv_check = chv_check;
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
