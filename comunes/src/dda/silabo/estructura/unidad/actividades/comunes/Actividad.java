/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidad.actividades.comunes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Actividad {

    private Integer id_actividades_aprendizaje;
    private String descripcion;
    private String chv_check;
    private String tipo_actividad;
    private String estado;
    private Integer idpadre;
    private String rol;
    private List<Actividad> nivel2 = new ArrayList<>();

    /**
     * @return the id_actividades_aprendizaje
     */
    public Integer getId_actividades_aprendizaje() {
        return id_actividades_aprendizaje;
    }

    /**
     * @param id_actividades_aprendizaje the id_actividades_aprendizaje to set
     */
    public void setId_actividades_aprendizaje(Integer id_actividades_aprendizaje) {
        this.id_actividades_aprendizaje = id_actividades_aprendizaje;
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
     * @return the tipo_actividad
     */
    public String getTipo_actividad() {
        return tipo_actividad;
    }

    /**
     * @param tipo_actividad the tipo_actividad to set
     */
    public void setTipo_actividad(String tipo_actividad) {
        this.tipo_actividad = tipo_actividad;
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
     * @return the nivel2
     */
    public List<Actividad> getNivel2() {
        return nivel2;
    }

    /**
     * @param nivel2 the nivel2 to set
     */
    public void setNivel2(List<Actividad> nivel2) {
        this.nivel2 = nivel2;
    }

    /**
     * @return the idpadre
     */
    public Integer getIdpadre() {
        return idpadre;
    }

    /**
     * @param idpadre the idpadre to set
     */
    public void setIdpadre(Integer idpadre) {
        this.idpadre = idpadre;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

}
