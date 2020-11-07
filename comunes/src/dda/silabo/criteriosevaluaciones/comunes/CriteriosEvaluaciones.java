/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.criteriosevaluaciones.comunes;

import dda.silabo.observaciones.comunes.Observacion;
import dda.silabo.observaciones.comunes.Observaciones;
import dda.silabo.silabo.comunes.Silabo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose-
 */
public class CriteriosEvaluaciones {

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

    private String Titulo;
    private String Ayuda;
    private List<ActividadEvaluar> actividadesevaluar = new ArrayList();
    private Observaciones observacion = new Observaciones();
    private List<Observacion> observaciones = new ArrayList();
    private Silabo silabos = null;
    private String Vigencia; 

    /**
     * @return the Titulo
     */
    public String getTitulo() {
        return Titulo;
    }

    /**
     * @param Titulo the Titulo to set
     */
    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    /**
     * @return the Ayuda
     */
    public String getAyuda() {
        return Ayuda;
    }

    /**
     * @param Ayuda the Ayuda to set
     */
    public void setAyuda(String Ayuda) {
        this.Ayuda = Ayuda;
    }

    /**
     * @return the actividadesevaluar
     */
    public List<ActividadEvaluar> getActividadesevaluar() {
        return actividadesevaluar;
    }

    /**
     * @param actividadesevaluar the actividadesevaluar to set
     */
    public void setActividadesevaluar(List<ActividadEvaluar> actividadesevaluar) {
        this.actividadesevaluar = actividadesevaluar;
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

    public String getVigencia() {
        return Vigencia;
    }

    public void setVigencia(String Vigencia) {
        this.Vigencia = Vigencia;
    }
}
