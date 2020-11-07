/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.hilos;

import ec.edu.espoch.academico.ArrayOfDictadoMateria;
import ec.edu.espoch.academico.DictadoMateria;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import unidos.comunes.Asignatura;
import unidos.comunes.CampoFormacion;
import unidos.comunes.Docente;

/**
 *
 * @author Jorge Zaruma
 */
public class DocenteCampoHilo extends Thread {

    private String codCarrera;
    private String codMateria;

    private Asignatura asignatura;
    private ConcurrentHashMap docentes;
    private ConcurrentHashMap camposFormacion;

    public DocenteCampoHilo(String codCarrera, Asignatura asignatura,
            ConcurrentHashMap docentes, ConcurrentHashMap camposFormacion) {
        try {
            this.codCarrera = codCarrera;
            this.asignatura = asignatura;
            this.docentes = docentes;
            this.camposFormacion = camposFormacion;
        } catch (Exception e) {
            Logger.getLogger("DocenteHilo").log(Level.SEVERE, "ERROR: ".concat(e.getMessage()));
        }
    }

    @Override
    public void run() {
        try {
            ArrayOfDictadoMateria asignaturaOASis = getDictadosMateria(this.codCarrera, this.asignatura.getCodMateria());
            if (asignaturaOASis != null) {
                // CampoFormacion
                ConcurrentHashMap<String, Docente> campoFormacionDocentes = null;
                CampoFormacion campoFormacion = (CampoFormacion) this.camposFormacion.get(this.asignatura.getCodArea());
                if (campoFormacion != null) {
                    campoFormacionDocentes = campoFormacion.DocentesToHashMap();
                }
                // Docentes
                List<DictadoMateria> docentesOASis = asignaturaOASis.getDictadoMateria();
                ConcurrentHashMap<String, Docente> asignaturaDocentes = this.asignatura.DocentesToHashMap();
                for (DictadoMateria docenteOASis : docentesOASis) {
                    if (docenteOASis != null
                            && docenteOASis.getDocente() != null
                            && docenteOASis.getDocente().getCedula() != null) {

                        Docente docente = new Docente(docenteOASis.getDocente()); //constructor copia
                        this.docentes.putIfAbsent(docente.getCedula(), docente);

                        asignaturaDocentes.putIfAbsent(docente.getCedula(), docente);
                        if (campoFormacionDocentes != null) {
                            campoFormacionDocentes.putIfAbsent(docente.getCedula(), docente);
                        }
                    }
                }
                this.asignatura.setDocentes(asignaturaDocentes);
                campoFormacion.setDocentes(campoFormacionDocentes);
            }
        } catch (Exception e) {
            Logger.getLogger("DocenteHilo").log(Level.INFO, "ERROR: ".concat(codMateria).concat(e.getMessage()));
        }
    }

    private static ArrayOfDictadoMateria getDictadosMateria(java.lang.String codCarrera, java.lang.String codMateria) {
        ArrayOfDictadoMateria result = null;
        try {
            ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
            ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
            result = port.getDictadosMateria(codCarrera, codMateria);
        } catch (Exception e) {
            Logger.getLogger("DocenteHilo").log(Level.INFO, "ERROR: ".concat(codMateria).concat(e.getMessage()));
        }
        return result;

    }

}
