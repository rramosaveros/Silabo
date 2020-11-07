/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.menulateral.comunes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class Unidad {

    private Integer idUnidad;
    private String descripcion;
    private String estado;
    private List<SeccionSilabo> subsecciones = new ArrayList<>();

    /**
     * @return the subsecciones
     */
    public List<SeccionSilabo> getSubsecciones() {
        return subsecciones;
    }

    /**
     * @param subsecciones the subsecciones to set
     */
    public void setSubsecciones(List<SeccionSilabo> subsecciones) {
        this.subsecciones = subsecciones;
    }

    /**
     * @return the idUnidad
     */
    public Integer getIdUnidad() {
        return idUnidad;
    }

    /**
     * @param idUnidad the idUnidad to set
     */
    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
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
