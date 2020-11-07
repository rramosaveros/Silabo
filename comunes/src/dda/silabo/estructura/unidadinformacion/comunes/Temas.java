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
 * @author TOSHIBA
 */
public class Temas {
    private Integer id;
    private String desc;
    private String sistema;
    private List<Subtemas> subtemas = new ArrayList<>();

    /**
     * @return the id_temas
     */
    public Integer getId_temas() {
        return id;
    }

    /**
     * @param id_temas the id_temas to set
     */
    public void setId_temas(Integer id_temas) {
        this.id = id_temas;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return desc;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.desc = descripcion;
    }

    /**
     * @return the subtemas
     */
    public List<Subtemas> getSubtemas() {
        return subtemas;
    }

    /**
     * @param subtemas the subtemas to set
     */
    public void setSubtemas(List<Subtemas> subtemas) {
        this.subtemas = subtemas;
    }

    /**
     * @return the sistema
     */
    public String getSistema() {
        return sistema;
    }

    /**
     * @param sistema the sistema to set
     */
    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

}
