/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.hilos;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adry Qp
 */
public class CarreraHilo extends Thread {

    private String codCarrera;
    private ec.edu.espoch.academico.UnidadAcademica carrera;

    private ConcurrentHashMap carreras;
    private ConcurrentHashMap escuelas;

    public CarreraHilo(String codCarrera, ec.edu.espoch.academico.UnidadAcademica carrera,
            ConcurrentHashMap carreras, ConcurrentHashMap escuelas) {
        try {
            this.codCarrera = codCarrera;
            this.carrera = carrera;
            this.carreras = carreras;
            this.escuelas = escuelas;
        } catch (Exception e) {
            Logger.getLogger("CarreraHilo").log(Level.SEVERE, "ERROR: ".concat(e.getMessage()));
        }
    }

    @Override
    public void run() {
        try {
            if (this.codCarrera != null
                    && this.carrera != null
                    && this.carreras != null) {

                unidos.comunes.Carrera carrera = new unidos.comunes.Carrera();

                unidos.comunes.Escuela escuela = (unidos.comunes.Escuela) escuelas.get(carrera.getCodEscuela());
                if (escuela != null) {
                    escuela.addCarrera(carrera);
                }

                this.carreras.putIfAbsent(carrera.getCodCarrera(), carrera);
            }
        } catch (Exception e) {
            Logger.getLogger("CarreraHilo").log(Level.INFO, "CarreraHilo|run".concat(this.carrera.getCodigo()).concat(e.getMessage()));
        }
    }
}
