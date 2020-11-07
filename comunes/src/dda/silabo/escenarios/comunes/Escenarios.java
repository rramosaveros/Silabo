/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.escenarios.comunes;

import dda.silabo.observaciones.comunes.Observacion;
import dda.silabo.observaciones.comunes.Observaciones;
import dda.silabo.silabo.comunes.Silabo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TOSHIBA
 */
public class Escenarios {

    private List<Escenario> escenarios = new ArrayList<>();
    private Observaciones observacion = new Observaciones();
    private List<Observacion> observaciones = new ArrayList<>();
    private Silabo silabos = new Silabo();
    private String ayuda;
    private String titulo;

    public void addEscenarios(Escenario escenarios) {
        if (escenarios != null) {
            this.getEscenarios().add(escenarios);
        }
    }

    public void addObservaciones(Observacion ob) {
        if (ob != null) {
            this.getObservaciones().add(ob);
        }
    }

    /**
     * @return the escenarios
     */
    public List<Escenario> getEscenarios() {
        return escenarios;
    }

    /**
     * @param escenarios the escenarios to set
     */
    public void setEscenarios(List<Escenario> escenarios) {
        this.escenarios = escenarios;
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
