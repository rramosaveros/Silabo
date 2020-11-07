/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.reportes.comunes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class ReporteGrafico { 
   
    private String titulo;
    private String subtitulo;
    private String ayuda;
    private List<Elemento> elementos = new ArrayList<>();
    private String fncClick;
    private Integer avance;

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
     * @return the subtitulo
     */
    public String getSubtitulo() {
        return subtitulo;
    }

    /**
     * @param subtitulo the subtitulo to set
     */
    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
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

    public List<Elemento> getElementos() {
        return elementos;
    }

    /**
     * @param elementos the elementos to set
     */
    public void setElementos(List<Elemento> elementos) {
        this.elementos = elementos;
    }

    /**
     * @return the fncClick
     */
    public String getFncClick() {
        return fncClick;
    }

    /**
     * @param fncClick the fncClick to set
     */
    public void setFncClick(String fncClick) {
        this.fncClick = fncClick;
    }

    /**
     * @return the avance
     */
    public Integer getAvance() {
        return avance;
    }

    /**
     * @param avance the avance to set
     */
    public void setAvance(Integer avance) {
        this.avance = avance;
    }
}
