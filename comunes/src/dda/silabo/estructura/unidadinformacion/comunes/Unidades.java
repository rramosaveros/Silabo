/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidadinformacion.comunes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Unidades {

    private String desc;
    private String id;
    private Integer numUnidad;
    private String accion;
    private List<Temas> temas = new ArrayList<>();

    /**
     * @return the temas
     */
    public List<Temas> getTemas() {
        return temas;
    }

    /**
     * @param temas the temas to set
     */
    public void setTemas(List<Temas> temas) {
        this.temas = temas;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return desc;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.desc = titulo;
    }

    public String getIdUnidad() {
        return id;
    }

    public void setIdUnidad(String id) {
        this.id = id;
    }

    /**
     * @return the numUnidad
     */
    public Integer getNumUnidad() {
        return numUnidad;
    }

    /**
     * @param numUnidad the numUnidad to set
     */
    public void setNumUnidad(Integer numUnidad) {
        this.numUnidad = numUnidad;
    }

    /**
     * @return the accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }
}
