/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidad.estrategias.comunes;

import dda.silabo.observaciones.comunes.Observacion;
import dda.silabo.observaciones.comunes.Observaciones;
import dda.silabo.silabo.comunes.Silabo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class EstrategiasMetodologicas {

    private Observaciones observacion = new Observaciones();
    private List<Observacion> observaciones = new ArrayList();
    private String ayuda;
    private String titulo;
    private Silabo silabos = new Silabo();
    private List<Estrategia> nivel1 = new ArrayList<>();

    /**
     * @return the nivel1
     */
    public List<Estrategia> getNivel1() {
        return nivel1;
    }

    /**
     * @param nivel1 the nivel1 to set
     */
    public void setNivel1(List<Estrategia> nivel1) {
        this.nivel1 = nivel1;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the ayuda
     */
    public String getAyuda() {
        return ayuda;
    }

    /**
     * @param ayuda the ayuda to set
     */
    public void setAyuda(String ayuda) {
        this.ayuda = ayuda;
    }

    /**
     * @return the observacion
     */
    public Observaciones getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(Observaciones observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the observaciones
     */
    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the silabos
     */
    public Silabo getSilabos() {
        return silabos;
    }

    /**
     * @param silabos the silabos to set
     */
    public void setSilabos(Silabo silabos) {
        this.silabos = silabos;
    }

}
