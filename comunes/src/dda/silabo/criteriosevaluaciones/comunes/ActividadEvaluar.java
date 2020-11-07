/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.criteriosevaluaciones.comunes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose-
 */
public class ActividadEvaluar {

    private Integer id_actividades;
    private String descripcion;
    private List<Aporte> aportes = new ArrayList<>();

    /**
     * @return the id_actividades
     */
    public Integer getId_actividades() {
        return id_actividades;
    }

    /**
     * @param id_actividades the id_actividades to set
     */
    public void setId_actividades(Integer id_actividades) {
        this.id_actividades = id_actividades;
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
     * @return the aportes
     */
    public List<Aporte> getAportes() {
        return aportes;
    }

    /**
     * @param aportes the aportes to set
     */
    public void setAportes(List<Aporte> aportes) {
        this.aportes = aportes;
    }

}
