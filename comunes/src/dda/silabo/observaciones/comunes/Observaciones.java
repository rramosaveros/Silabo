/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.observaciones.comunes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class Observaciones {

    private List<Observacion> secciones = new ArrayList<>();
    private Observacion observacion = new Observacion();
    private List<FechaObservacion> fechas = new ArrayList<>();
    private List<Observacion> subsecciones = new ArrayList<>();
    private Integer numUnidades;
    private Integer idTipo;
    private String accionBoton;

    /**
     * @return the secciones
     */
    public List<Observacion> getSecciones() {
        return secciones;
    }

    /**
     * @param secciones the secciones to set
     */
    public void setSecciones(List<Observacion> secciones) {
        this.secciones = secciones;
    }

    /**
     * @return the subsecciones
     */
    public List<Observacion> getSubsecciones() {
        return subsecciones;
    }

    /**
     * @param subsecciones the subsecciones to set
     */
    public void setSubsecciones(List<Observacion> subsecciones) {
        this.subsecciones = subsecciones;
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
     * @return the observacion
     */
    public Observacion getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(Observacion observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the fechas
     */
    public List<FechaObservacion> getFechas() {
        return fechas;
    }

    /**
     * @param fechas the fechas to set
     */
    public void setFechas(List<FechaObservacion> fechas) {
        this.fechas = fechas;
    }

    /**
     * @return the idTipo
     */
    public Integer getIdTipo() {
        return idTipo;
    }

    /**
     * @param idTipo the idTipo to set
     */
    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    /**
     * @return the accionBoton
     */
    public String getAccionBoton() {
        return accionBoton;
    }

    /**
     * @param accionBoton the accionBoton to set
     */
    public void setAccionBoton(String accionBoton) {
        this.accionBoton = accionBoton;
    }

   }
