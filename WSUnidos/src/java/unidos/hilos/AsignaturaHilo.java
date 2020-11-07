/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.hilos;

import ec.edu.espoch.academico.MateriaPensum;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import unidos.comunes.Asignatura;
import unidos.comunes.CampoFormacion;

/**
 *
 * @author Adry Qp
 */
public class AsignaturaHilo extends Thread {

    private String codCarrera;
    private MateriaPensum materia;

    private ConcurrentHashMap asignaturas;
    private ConcurrentHashMap camposFormacion;

    public AsignaturaHilo(String codCarrera, MateriaPensum materia,
            ConcurrentHashMap asignaturas, ConcurrentHashMap camposFormacion) {
        try {
            this.codCarrera = codCarrera;
            this.materia = materia;
            this.asignaturas = asignaturas;
            this.camposFormacion = camposFormacion;
        } catch (Exception e) {
            Logger.getLogger("AsignaturaHilo").log(Level.SEVERE, "ERROR: ".concat(e.getMessage()));
        }
    }

    @Override
    public void run() {
        try {
            if (this.codCarrera != null
                    && this.materia != null
                    && this.asignaturas != null) {
                
                CampoFormacion campoFormacion = new CampoFormacion(this.materia);
                this.camposFormacion.putIfAbsent(campoFormacion.getCodCampoF(), campoFormacion);
                
                Asignatura asignatura = new Asignatura(this.materia);
                this.asignaturas.putIfAbsent(asignatura.getCodMateria(), asignatura);
            }
        } catch (Exception e) {
            Logger.getLogger("AsignaturaHilo").log(Level.INFO, "AsignaturaHilo|run: ".concat(this.materia.getCodMateria()).concat(e.getMessage()));
        }
    }
}
