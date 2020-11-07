/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.vigencia;

import dda.silabo.datosgenerales.comunes.DatosGenerales;
import java.util.List;

/**
 *
 * @author Lenin Ramos 
 */
public class Vigencia extends DatosGenerales {
    
    public String determinarVigenciaCarrera(String nombreCarrera) {
        char vigente = nombreCarrera.charAt(nombreCarrera.length()-1);
        if (vigente == '.') {
            return "vigente"; 
        }
        return "no vigente";
    }
}
