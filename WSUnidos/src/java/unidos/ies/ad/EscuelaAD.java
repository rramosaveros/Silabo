/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.ies.ad;

import com.google.gson.Gson;
import ec.edu.espoch.academico.ArrayOfCarrera;
import ec.edu.espoch.academico.ArrayOfEscuela;
import ec.edu.espoch.academico.ArrayOfEscuelaEntidad;
import ec.edu.espoch.academico.ArrayOfUnidadAcademica;
import ec.edu.espoch.academico.Materia;
import ec.edu.espoch.academico.UnidadAcademica;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import unidos.comunes.Carrera;
import unidos.comunes.Docente;
import unidos.comunes.Escuela;
import unidos.ies.ln.IesLN;

/**
 *
 * @author Jorge Zaruma
 */
public class EscuelaAD extends Escuela {

    public void CarrerasLista() {
        try {
            ArrayOfUnidadAcademica arrayOfUnidadAcademica = getTodasCarreras();
            List<UnidadAcademica> unidadesAcademicas = arrayOfUnidadAcademica.getUnidadAcademica().stream().filter(ca -> ca.getCodEstado().equals("ABI")).collect(Collectors.toList());
            unidadesAcademicas.stream().map((ua) -> {
                CarreraAD carreraAD = new CarreraAD();
                carreraAD.Carreras(ua);
                return carreraAD;
            }).forEachOrdered((carreraAD) -> {
                this.getCarreras().add(carreraAD);
            });
        } catch (Exception e) {

        }
    }

    private static ArrayOfUnidadAcademica getTodasCarreras() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getTodasCarreras();
    }

    void Escuelas(ec.edu.espoch.academico.Escuela escuela) {
        try {
            this.setCodEscuela(escuela.getCodigo());
            this.setCodEstado(escuela.getCodEstado());
            this.setCodFacultad(escuela.getCodFacultad());
            this.setDirector(escuela.getDirector());
            this.setNombre(escuela.getNombre());
            this.setUbicacion(escuela.getUbicacion());
        } catch (Exception e) {
        }
    }

    public void arbolEscuela(String codEscuela, Gson gson) {
        CarrerasLista();
        List<Carrera> carreras = this.getCarreras().stream().filter(ca -> (ca.getCodEscuela().equals(codEscuela) && ca.getCodEstado().equals("ABI"))).collect(Collectors.toList());
        this.getCarreras().clear();
        this.informacionEscuela(codEscuela);
        carreras.stream().map((carrera) -> {
            CarreraAD carreraAD = new CarreraAD();
            carreraAD.setNombre(carrera.getNombre());
            carreraAD.setCodCarrera(carrera.getCodCarrera());
            carreraAD.setCodEstado(carrera.getCodEstado());

            //carreraAD.arbolCarrera(carrera.getCodCarrera(), gson);
            return carreraAD;
        }).forEachOrdered((carreraAD) -> {
            this.getCarreras().add(carreraAD);
        });
    }

    private static ArrayOfEscuela getTodasEscuelas() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getTodasEscuelas();
    }

    private void informacionEscuela(String codEscuela) {
        ArrayOfEscuela arrayOfEscuela = getTodasEscuelas();
        ec.edu.espoch.academico.Escuela escuela = arrayOfEscuela.getEscuela().stream().filter(es -> es.getCodigo().equals(codEscuela)).findFirst().orElse(null);
        if (escuela != null) {
            this.setCodEscuela(escuela.getCodigo());
            this.setNombre(escuela.getNombre());
            this.setCodFacultad(escuela.getCodFacultad());
            this.setDirector(escuela.getDirector());
            this.setUbicacion(escuela.getUbicacion());
            this.setCodEstado(escuela.getCodEstado());
        }
    }

    public void DocentesEscuela() {
        try {
            IesLN iesLN = new IesLN();
            ConcurrentHashMap<String, Docente> docentes = new ConcurrentHashMap<>();
            Integer numeroProcesos = Runtime.getRuntime().availableProcessors() - 1; //NUMERO DE PROCESADORES
            ExecutorService executor = Executors.newFixedThreadPool(numeroProcesos);
            for (Carrera c : this.getCarreras()) {
                CarreraAD carreraAD = new CarreraAD();
                carreraAD.setCodCarrera(c.getCodCarrera());
                CarreraAD carreraAD1 = iesLN.DocentesCarrera("{'codCarrera':'" + carreraAD.getCodCarrera() + "'}");
                if (carreraAD1 != null) {
                    if (!carreraAD1.getDocentes().isEmpty()) {
                        agregarDocentesEnCache(docentes, carreraAD1.getDocentes());
                    } else {
                        carreraAD.DocentesCarrera(docentes);
                    }
                }

            }
            executor.shutdown();  //se ejecuta cuando termina el proceso load
            while (!executor.isTerminated()) {
            }
            this.setDocentes(docentes);
        } catch (Exception e) {

        }
    }

    public void agregarDocentesEnCache(ConcurrentHashMap<String, Docente> docentes, List<Docente> docentes0) {
        try {
            docentes0.forEach((d) -> {
                docentes.putIfAbsent(d.getCedula(), d);
            });
        } catch (Exception e) {
        }
    }

    private static ArrayOfCarrera getCarrerasAbiertas() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getCarrerasAbiertas();
    }

}
