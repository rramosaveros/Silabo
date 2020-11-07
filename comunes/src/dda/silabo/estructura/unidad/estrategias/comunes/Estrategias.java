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
 * @author Jorge
 */
public class Estrategias {

    private List<Estrategia> estrategias = new ArrayList();
    private Observaciones observacion = new Observaciones();
    private List<Observacion> observaciones = new ArrayList();
    private String ayuda;
    private String titulo;
    private Silabo silabos = null;

    /**
     * @return the estrategias
     */
    public List<Estrategia> getEstrategias() {
        return estrategias;
    }

    /**
     * @param estrategias the estrategias to set
     */
    public void setEstrategias(List<Estrategia> estrategias) {
        this.estrategias = estrategias;
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
}
