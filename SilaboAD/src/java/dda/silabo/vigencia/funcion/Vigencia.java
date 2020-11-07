/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.vigencia.funcion;

import dda.silabo.silabo.comunes.Silabo;
import ec.edu.espoch.academico.ArrayOfEscuelaEntidad;
import ec.edu.espoch.academico.EscuelaEntidad;
import java.util.List;

/**
 *
 * @author RRA
 */
public class Vigencia {
    
    public String determinarVigenciaCarrera(String nombreCarrera) {
        char vigente = nombreCarrera.charAt(nombreCarrera.length()-1);
        if (vigente == '.') {
            return "vigente"; 
        }
        return "no vigente";
    }
    
    public String obtenerVigenciaCarreraF(String codCarrera) {
        String vigencia = "no definido";
        try {
            List<EscuelaEntidad> listaEscuelas = arrayOfEscuelaEntidad().getEscuelaEntidad();
            EscuelaEntidad escuelaParaSacarNombre = listaEscuelas.stream().filter(es -> es.getCodCarrera().equals(codCarrera)).findFirst().orElse(null);
            vigencia = determinarVigenciaCarrera(escuelaParaSacarNombre.getCarrera());
        } catch (Exception e) {
        }
        return vigencia;
    }
    
    private static ArrayOfEscuelaEntidad arrayOfEscuelaEntidad() {
        si.sw.locales.SwUnidosSI_Service service = new si.sw.locales.SwUnidosSI_Service();
        si.sw.locales.SwUnidosSI port = service.getSwUnidosSIPort();
        return port.arrayOfEscuelaEntidad();
    }
}
