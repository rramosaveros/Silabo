/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidadinformacion.comunes;

/**
 *
 * @author TOSHIBA
 */
public class Subtemas {

    private Integer id;
    private String desc;
    private String sistema;

    /**
     * @return the id_temas_subtemas
     */
    public Integer getId_temas_subtemas() {
        return id;
    }

    /**
     * @param id_temas_subtemas the id_temas_subtemas to set
     */
    public void setId_temas_subtemas(Integer id_temas_subtemas) {
        this.id = id_temas_subtemas;
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
