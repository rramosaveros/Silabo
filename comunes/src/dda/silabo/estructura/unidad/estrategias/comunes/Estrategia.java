/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidad.estrategias.comunes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Estrategia {

    private Integer id_estrategia;
    private String nombre;
    private String nivel;
    private Integer idpadre;
    private String descripcion;
    private String chv_check;
    private String estado;
    private List<Estrategia> nivel2 = new ArrayList<>();
    private List<Estrategia> nivel3 = new ArrayList<>();

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
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
     * @return the id_estrategia
     */
    public Integer getId_estrategia() {
        return id_estrategia;
    }

    /**
     * @param id_estrategia the id_estrategia to set
     */
    public void setId_estrategia(Integer id_estrategia) {
        this.id_estrategia = id_estrategia;
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
    public List<Estrategia> getNivel2() {
        return nivel2;
    }

    /**
     * @param nivel2 the nivel2 to set
     */
    public void setNivel2(List<Estrategia> nivel2) {
        this.nivel2 = nivel2;
    }

    /**
     * @return the nivel3
     */
    public List<Estrategia> getNivel3() {
        return nivel3;
    }

    /**
     * @param nivel3 the nivel3 to set
     */
    public void setNivel3(List<Estrategia> nivel3) {
        this.nivel3 = nivel3;
    }
}
