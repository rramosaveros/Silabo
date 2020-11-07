/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.reportes.comunes;

import dda.silabo.clases.unidos.FacultadUnidos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class ReporteComun {

    private String titulo;
    private String subtitulo;
    private String ayuda;
    private List<Elemento> elementos = new ArrayList<>();
    private List<Estados> datos = new ArrayList<>();
    private List<FacultadUnidos> facultades = new ArrayList<>();

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

    /**
     * @return the datos
     */
    public List<Estados> getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(List<Estados> datos) {
        this.datos = datos;
    }

    /**
     * @return the facultades
     */
    public List<FacultadUnidos> getFacultades() {
        return facultades;
    }

    /**
     * @param facultades the facultades to set
     */
    public void setFacultades(List<FacultadUnidos> facultades) {
        this.facultades = facultades;
    }

    /**
     * @return the elementos
     */
    public List<Elemento> getElementos() {
        return elementos;
    }

    /**
     * @param elementos the elementos to set
     */
    public void setElementos(List<Elemento> elementos) {
        this.elementos = elementos;
    }

}
