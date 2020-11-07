/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.opciones.comunes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class Opciones2 {

    private List<Opcion> opciones = new ArrayList<>();
    private String titulo;
    private String ayuda;

    public Opciones2() {
        this.titulo = "Opciones del Sistema";
        this.ayuda = "Agregar opciones disponibles en el sistema";
    }

    /**
     * @return the opciones
     */
    public List<Opcion> getOpciones() {
        return opciones;
    }

    /**
     * @param opciones the opciones to set
     */
    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
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

}
