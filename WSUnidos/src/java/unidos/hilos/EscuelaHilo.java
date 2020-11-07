/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.hilos;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adry Qp
 */
public class EscuelaHilo extends Thread {

    private String codEscuela;
    private ec.edu.espoch.academico.Escuela escuela;

    private ConcurrentHashMap facultades;
    private ConcurrentHashMap escuelas;
    private ConcurrentHashMap carreras;

    public EscuelaHilo(String codEscuela, ec.edu.espoch.academico.Escuela escuela,
            ConcurrentHashMap escuelas, ConcurrentHashMap facultades, ConcurrentHashMap carreras) {
        try {
            this.codEscuela = codEscuela;
            this.escuela = escuela;
            this.facultades = facultades;
            this.escuelas = escuelas;
            this.carreras = carreras;
        } catch (Exception e) {
            Logger.getLogger("EntidadHilo").log(Level.SEVERE, "ERROR: ".concat(e.getMessage()));
        }
    }

    @Override
    public void run() {
        try {
            if (this.codEscuela != null
                    && this.escuela != null
                    && this.escuelas != null) {

                unidos.comunes.Escuela escuela = new unidos.comunes.Escuela(this.escuela);
                
                Enumeration<String> codCarreras = carreras.keys();
                String codCarrera;
                while (codCarreras.hasMoreElements()) {
                    codCarrera = (String) codCarreras.nextElement();
                    unidos.comunes.Carrera carrera = (unidos.comunes.Carrera) carreras.get(codCarrera);
                    if (carrera.getCodEscuela().equals(escuela.getCodEscuela())) {
                        escuela.addCarrera(carrera);
                    }
                }
                
                unidos.comunes.Facultad facultad = (unidos.comunes.Facultad)facultades.get(escuela.getCodFacultad());
                facultad.addEscuela(escuela);
                
                this.escuelas.putIfAbsent(escuela.getCodEscuela(), escuela);
            }
        } catch (Exception e) {
            Logger.getLogger("EscuelaHilo").log(Level.INFO, "EscuelaHilo|run".concat(this.escuela.getCodigo()).concat(e.getMessage()));
        }
    }

}
