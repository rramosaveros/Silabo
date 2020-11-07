/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.importacion.comunes;

import dda.silabo.clases.unidos.EntidadUnidos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class Periodo {

    private String codPeriodo;
    private String descPeriodo;
    private List<EntidadUnidos> entidades = new ArrayList<>();

    /**
     * @return the codPeriodo
     */
    public String getCodPeriodo() {
        return codPeriodo;
    }

    /**
     * @param codPeriodo the codPeriodo to set
     */
    public void setCodPeriodo(String codPeriodo) {
        this.codPeriodo = codPeriodo;
    }

    /**
     * @return the descPeriodo
     */
    public String getDescPeriodo() {
        return descPeriodo;
    }

    /**
     * @param descPeriodo the descPeriodo to set
     */
    public void setDescPeriodo(String descPeriodo) {
        this.descPeriodo = descPeriodo;
    }

    /**
     * @return the entidades
     */
    public List<EntidadUnidos> getEntidades() {
        return entidades;
    }

    /**
     * @param entidades the entidades to set
     */
    public void setEntidades(List<EntidadUnidos> entidades) {
        this.entidades = entidades;
    }
}
