/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.criteriosevaluaciones.comunes;

import dda.silabo.observaciones.comunes.Observacion;
import dda.silabo.silabo.comunes.Silabo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Calificaciones {

    private List<Calificacion> evaluaciones = new ArrayList<>();
    private List<Observacion> observaciones = new ArrayList<>();
    private Silabo silabos = new Silabo();
    private String ayuda;
    private String titulo;

    /**
     * @return the evaluaciones
     */
    public List<Calificacion> getEvaluaciones() {
        return evaluaciones;
    }

    /**
     * @param evaluaciones the evaluaciones to set
     */
    public void setEvaluaciones(List<Calificacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
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
}
