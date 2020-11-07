/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.hilos;

import ec.edu.espoch.academico.Escuela;
import ec.edu.espoch.academico.Facultad;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adry Qp
 */
public class FacultadHilo extends Thread {

    private String codFacultad;
    private Facultad facultad;
    private Escuela escuela;

    private ConcurrentHashMap facultades;
    private ConcurrentHashMap escuelas;

    public FacultadHilo(String codFacultad, Facultad facultad, ConcurrentHashMap facultades,
            ConcurrentHashMap escuelas) {
        try {
            this.codFacultad = codFacultad;
            this.facultad = facultad;
            this.facultades = facultades;
            this.escuelas = escuelas;
        } catch (Exception e) {
            Logger.getLogger("EntidadHilo").log(Level.SEVERE, "ERROR: ".concat(e.getMessage()));
        }
    }

    @Override
    public void run() {
        try {
            if (this.codFacultad != null && this.facultad != null
                    && this.facultades != null && this.escuelas != null) {

                unidos.comunes.Facultad facultad = new unidos.comunes.Facultad(this.facultad);

                Enumeration<String> codEscuelas = escuelas.keys();
                String codEscuela;
                while (codEscuelas.hasMoreElements()) {
                    codEscuela = (String) codEscuelas.nextElement();
                    unidos.comunes.Escuela escuela = (unidos.comunes.Escuela) escuelas.get(codEscuela);
                    if (escuela.getCodFacultad().equals(facultad.getCodFacultad())) {
                        facultad.addEscuela(escuela);
                    }
                }

                this.facultades.putIfAbsent(facultad.getCodFacultad(), facultad);
            }
        } catch (Exception e) {
            Logger.getLogger("EntidadHilo").log(Level.INFO, "EntidadHilo|run".concat(this.facultad.getCodigo()).concat(e.getMessage()));
        }
    }

}
