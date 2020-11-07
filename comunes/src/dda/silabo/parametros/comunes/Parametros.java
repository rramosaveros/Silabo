/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.parametros.comunes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class Parametros {
    private List<Parametro> parametros = new ArrayList<>();
    private String Titulo;
    private String Ayuda;

    /**
     * @return the parametros
     */
    public List<Parametro> getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }

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
    
    
}
