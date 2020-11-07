/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.ies.ad;

import com.google.gson.Gson;
import ec.edu.espoch.academico.ArrayOfEscuela;
import ec.edu.espoch.academico.ArrayOfFacultad;
import ec.edu.espoch.academico.ArrayOfUnidadAcademica;
import ec.edu.espoch.academico.Escuela;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import unidos.comunes.Carrera;
import unidos.comunes.Docente;
import unidos.comunes.Facultad;
import unidos.ies.ln.IesLN;

/**
 *
 * @author Jorge Zaruma
 */
public class FacultadAD extends Facultad {

    public void EscuelasLista() {
        try {
            ArrayOfEscuela arrayOfEscuela = getTodasEscuelas();
            List<Escuela> escuelas = arrayOfEscuela.getEscuela().stream().filter(es -> es.getCodEstado().equals("ABI")).collect(Collectors.toList());
            escuelas.stream().map((escuela) -> new unidos.comunes.Escuela(escuela)).forEachOrdered((escuelaC) -> {
                this.getEscuelas().add(escuelaC);
            });
        } catch (Exception e) {
        }
    }

    private static ArrayOfEscuela getTodasEscuelas() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getTodasEscuelas();
    }

    public void arbolFacultad(String codFacultad, Gson gson) {
        EscuelasLista();
        this.setCodFacultad(codFacultad);
        List<unidos.comunes.Escuela> escuelas = this.getEscuelas().stream().filter(fa -> fa.getCodFacultad().equals(codFacultad)).collect(Collectors.toList());
        this.getEscuelas().clear();
        this.informacionFacultad(codFacultad);
        escuelas.stream().map((escuela) -> {
            EscuelaAD escuelaAD = new EscuelaAD();
            escuelaAD.arbolEscuela(escuela.getCodEscuela(), gson);
            return escuelaAD;
        }).forEachOrdered((escuelaAD) -> {
            this.getEscuelas().add(escuelaAD);
        });
    }

    private void informacionFacultad(String codFacultad) {
        ArrayOfFacultad arrayOfFacultad = getTodasFacultades();
        ec.edu.espoch.academico.Facultad facultad = arrayOfFacultad.getFacultad().stream().filter(fa -> fa.getCodigo().equals(codFacultad)).findFirst().orElse(null);
        if (facultad != null) {
            this.setCodEstado(facultad.getCodEstado());
            this.setCodFacultad(facultad.getCodigo());
            this.setDecano(facultad.getDecano());
            this.setNombre(facultad.getNombre());
            this.setUbicacion(facultad.getUbicacion());
            this.setViceDecano(facultad.getVicedecano());
            this.setFechaCreacion(facultad.getFechaCreacion());
        }
    }

    private static ArrayOfFacultad getTodasFacultades() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getTodasFacultades();
    }

    public void DocentesFacultad() {
        try {
            Integer numeroProcesos = Runtime.getRuntime().availableProcessors() - 1; //NUMERO DE PROCESADORES
            ExecutorService executor = Executors.newFixedThreadPool(numeroProcesos);
            EscuelaAD escuelaAD = new EscuelaAD();
            IesLN iesLN = new IesLN();
            ConcurrentHashMap<String, Docente> docentes = new ConcurrentHashMap<>();
            for (unidos.comunes.Escuela e : this.getEscuelas()) {
                for (Carrera c : e.getCarreras()) {
                    CarreraAD carreraAD = new CarreraAD();
                    carreraAD.setCodCarrera(c.getCodCarrera());
                    CarreraAD carreraAD1 = iesLN.DocentesCarrera("{'codCarrera':'" + carreraAD.getCodCarrera() + "'}");
                    if (carreraAD1 != null) {
                        escuelaAD.agregarDocentesEnCache(docentes, carreraAD1.getDocentes());
                    }

                }
            }

            executor.shutdown();  //se ejecuta cuando termina el proceso load
            while (!executor.isTerminated()) {
            }
            this.getEscuelas().clear();
            this.setDocentes(docentes);
        } catch (Exception e) {
        }
    }

}
