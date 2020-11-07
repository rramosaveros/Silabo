/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.bibliografias.comunes;

import dda.silabo.observaciones.comunes.Observacion;
import dda.silabo.observaciones.comunes.Observaciones;
import dda.silabo.silabo.comunes.Silabo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Bibliografias {

    private List<Bibliografia> bibliografias = new ArrayList<>();
    private Observaciones observacion = new Observaciones();
    private List<Observacion> observaciones = new ArrayList<>();
    private Silabo silabos = new Silabo();
    private String ayuda;
    private String titulo;
    private Bibliografia basica = new Bibliografia();
    private Bibliografia complementaria = new Bibliografia();

    /**
     * @return the bibliografias
     */
    public List<Bibliografia> getBibliografias() {
        return bibliografias;
    }

    /**
     * @param bibliografias the bibliografias to set
     */
    public void setBibliografias(List<Bibliografia> bibliografias) {
        this.bibliografias = bibliografias;
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

    public void addBibliografias(Bibliografia bibliografias) {
        if (bibliografias != null) {
            this.getBibliografias().add(bibliografias);
        }
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
     * @return the basica
     */
    public Bibliografia getBasica() {
        return basica;
    }

    /**
     * @param basica the basica to set
     */
    public void setBasica(Bibliografia basica) {
        this.basica = basica;
    }

    /**
     * @return the complementaria
     */
    public Bibliografia getComplementaria() {
        return complementaria;
    }

    /**
     * @param complementaria the complementaria to set
     */
    public void setComplementaria(Bibliografia complementaria) {
        this.complementaria = complementaria;
    }
}
