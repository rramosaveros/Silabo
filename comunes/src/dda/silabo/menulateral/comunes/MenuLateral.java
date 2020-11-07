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
public class MenuLateral {

    private List<SeccionSilabo> secciones = new ArrayList<>();
    private List<Unidad> unidades = new ArrayList<>();
    private List<SeccionSilabo> subsecciones = new ArrayList<>();
    private String estado;
    private Integer numUnidades;
    private String rol;

    /**
     * @return the secciones
     */
    public List<SeccionSilabo> getSecciones() {
        return secciones;
    }

    /**
     * @param secciones the secciones to set
     */
    public void setSecciones(List<SeccionSilabo> secciones) {
        this.secciones = secciones;
    }

    /**
     * @return the numUnidades
     */
    public Integer getNumUnidades() {
        return numUnidades;
    }

    /**
     * @param numUnidades the numUnidades to set
     */
    public void setNumUnidades(Integer numUnidades) {
        this.numUnidades = numUnidades;
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

    /**
     * @return the unidades
     */
    public List<Unidad> getUnidades() {
        return unidades;
    }

    /**
     * @param unidades the unidades to set
     */
    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }

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
