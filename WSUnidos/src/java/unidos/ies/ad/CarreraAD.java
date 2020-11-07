/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.ies.ad;

import unidos.hilos.DocenteHilo;
import unidos.hilos.AsignaturaHilo;
import com.google.gson.Gson;
import ec.edu.espoch.academico.ArrayOfDictadoMateria;
import ec.edu.espoch.academico.ArrayOfMateriaPensum;
import ec.edu.espoch.academico.ArrayOfUnidadAcademica;
import ec.edu.espoch.academico.DictadoMateria;
import ec.edu.espoch.academico.MateriaPensum;
import ec.edu.espoch.academico.MateriaRequisito;
import ec.edu.espoch.academico.UnidadAcademica;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import unidos.comunes.Asignatura;
import unidos.comunes.CampoFormacion;
import unidos.comunes.Carrera;
import unidos.comunes.Docente;
import unidos.hilos.DocenteCarreraHilo;
import unidos.pensum.PensumLN;

/**
 *
 * @author Adriana Qp
 */
public class CarreraAD extends Carrera {

    public void carreraAsignaturasFromOASis() {
        try {
            PensumLN pensumLN = new PensumLN();
            ArrayOfMateriaPensum pensum = pensumLN.PensumValido(this.getCodCarrera());
            if (pensum.getMateriaPensum().size() > 0) {
                this.loadCarreraAsignaturas(pensum.getMateriaPensum());
            }
        } catch (Exception e) {
            Logger.getLogger("CarreraAD").log(Level.SEVERE, "CarreraAD|carreraFromOASis: ".concat(e.getMessage()));
        }
    }

    //******************************************************************************************************
    //*                               ASIGNATURAS POR CARRERA                                              *
    //******************************************************************************************************
    public void getCarreraAsignaturas(List<MateriaPensum> materiaPensum, String codCarrera) {
        try {
            this.setCodCarrera(codCarrera);
            loadCarreraAsignaturas(materiaPensum);
        } catch (Exception e) {
            Logger.getLogger("CarreraAD").log(Level.SEVERE, "CarreraAD|getAsignaturasCarrera: ".concat(e.getMessage()));
        }
    }

    private void loadCarreraAsignaturas(List<MateriaPensum> materiaPensum) {
        try {
            ConcurrentHashMap<String, Asignatura> asignaturas = this.AsignaturasToHashMap();
            ConcurrentHashMap<String, CampoFormacion> camposFormacion = this.CamposFormacionToHashMap();
            if (camposFormacion != null) {
                Integer numeroProcesos = Runtime.getRuntime().availableProcessors() - 1; //NUMERO DE PROCESADORES
                ExecutorService executor = Executors.newFixedThreadPool(numeroProcesos);
                for (int m = 0; m < materiaPensum.size(); m++) {
                    MateriaPensum materia = (MateriaPensum) materiaPensum.get(m);
                    if (materia != null && materia.getCodMateria() != null) {

                        AsignaturaHilo ah = new AsignaturaHilo(this.getCodCarrera(), materia, asignaturas, camposFormacion); //crea un hilo
                        ah.start(); // EJECUTA EL HILO
                        executor.execute(ah);
                    }
                }

                executor.shutdown();  //se ejecuta cuando termina el proceso load
                while (!executor.isTerminated()) {
                }
                updateRequisitos(materiaPensum, asignaturas);
                this.setAsignaturas(asignaturas); //lista de asignaturas
            }
        } catch (Exception e) {
            Logger.getLogger("CarreraAD").log(Level.SEVERE, "CarreraAD|loadCarreraAsignaturas: ".concat(e.getMessage()));
        }
    }

    private void updateRequisitos(List<MateriaPensum> materiaPensum, ConcurrentHashMap<String, Asignatura> asignaturas) {
        try {
            for (int m = 0; m < materiaPensum.size(); m++) {
                MateriaPensum materia = (MateriaPensum) materiaPensum.get(m);
                if (materia != null
                        && materia.getRequisitos() != null
                        && materia.getRequisitos().getMateriaRequisito() != null) {
                    List<MateriaRequisito> requisitos = materia.getRequisitos().getMateriaRequisito();
                    Asignatura asignatura = asignaturas.get(materia.getCodMateria());
                    for (int m1 = 0; m1 < requisitos.size(); m1++) {
                        MateriaRequisito requisito = (MateriaRequisito) requisitos.get(m1);

                        Asignatura asignaturaRequisito = asignaturas.get(requisito.getCodMateria());
                        if (requisito.getCodTipo().equals("PRE")) {
                            asignatura.addPrerrequisito(asignaturaRequisito);
                        } else {
                            asignatura.addCorrequisito(asignaturaRequisito);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger("CarreraAD").log(Level.SEVERE, "CarreraAD|updateRequisitos: ".concat(e.getMessage()));
        }
    }

    void Carreras(UnidadAcademica ua) {
        try {
            this.setCodCarrera(ua.getCodigo());
            this.setNombre(ua.getNombre());
            this.setCodEscuela(ua.getCodEscuela());
            this.setCodEstado(ua.getCodEstado());
        } catch (Exception e) {
        }
    }

    public void arbolCarrera(String codCarrera, Gson gson) {
        try {
            this.informacionCarrera(codCarrera, gson);
            PensumLN pensumLN = new PensumLN();
            ArrayOfMateriaPensum arrayOfMateriaPensum = pensumLN.PensumValido(codCarrera);
            List<MateriaPensum> distinctElements = arrayOfMateriaPensum.getMateriaPensum().stream().filter(distinctByKey(p -> p.getCodArea())).collect(Collectors.toList());
            List<CampoFormacion> campos = new ArrayList<>();
            distinctElements.stream().map((materiaPensum) -> {
                CampoFormacion campoFormacion = new CampoFormacion();
                campoFormacion.setCodCampoF(materiaPensum.getCodArea());
                campoFormacion.setDescCampoF(materiaPensum.getArea());
                return campoFormacion;
            }).forEachOrdered((campoFormacion) -> {
                campos.add(campoFormacion);
            });
            this.setCamposFormacion(campos);

        } catch (Exception e) {
        }
    }

    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private void informacionCarrera(String codCarrera, Gson gson) {
        ArrayOfUnidadAcademica academica = getTodasCarreras();
        ec.edu.espoch.academico.UnidadAcademica unidadAcademica = academica.getUnidadAcademica().stream().filter(ca -> ca.getCodigo().equals(codCarrera)).findFirst().orElse(null);
        this.setCodCarrera(unidadAcademica.getCodigo());
        this.setNombre(unidadAcademica.getNombre());
        this.setCodEscuela(unidadAcademica.getCodEscuela());
        this.setCodEstado(unidadAcademica.getCodEstado());
    }

    private static ArrayOfUnidadAcademica getTodasCarreras() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getTodasCarreras();
    }

    public void DocentesCarrera(ConcurrentHashMap<String, Docente> docentes) {
        try {
            PensumLN pensumLN = new PensumLN();
            ArrayOfMateriaPensum academica = pensumLN.PensumValido(this.getCodCarrera());
            if (academica != null) {
                Integer numeroProcesos = Runtime.getRuntime().availableProcessors() - 1; //NUMERO DE PROCESADORES
                ExecutorService executor = Executors.newFixedThreadPool(numeroProcesos);

                academica.getMateriaPensum().stream().map((asignatura) -> new DocenteCarreraHilo(this.getCodCarrera(), asignatura, docentes)).map((dh) -> {
                    dh.start(); // EJECUTA EL HILO
                    return dh;
                }).forEachOrdered((dh) -> {
                    executor.execute(dh);
                });

                executor.shutdown();  //se ejecuta cuando termina el proceso load
                while (!executor.isTerminated()) {
                }

                this.setDocentes(docentes);
            }
        } catch (Exception e) {
            Logger.getLogger("CarreraAD").log(Level.SEVERE, "CarreraAD|loadCarreraDocentes: ".concat(e.getMessage()));
        }
    }

    public void CamposFormacionCarrera() {
        try {
            this.getCamposFormacion().forEach((campoFormacion) -> {
                List<Asignatura> asignaturas = this.getAsignaturas().stream().filter(as -> as.getCodArea().equals(campoFormacion.getCodCampoF())).collect(Collectors.toList());
                campoFormacion.setAsignaturas(asignaturas);
            });
            this.getAsignaturas().clear();
        } catch (Exception e) {
            Logger.getLogger("CarreraAD").log(Level.SEVERE, "CarreraAD|updateCamposFormacion: ".concat(e.getMessage()));
        }
    }

    public void DocentesCamposFormacion(String codCampo) {
        try {
            List<CampoFormacion> auxCampos = this.getCamposFormacion().stream().filter(cam -> cam.getCodCampoF().equals(codCampo)).collect(Collectors.toList());
            for (CampoFormacion cf : auxCampos) {
                Integer numeroProcesos = Runtime.getRuntime().availableProcessors() - 1; //NUMERO DE PROCESADORES
                ExecutorService executor = Executors.newFixedThreadPool(numeroProcesos);
                ConcurrentHashMap<String, Docente> docentes = new ConcurrentHashMap<>();
                for (Asignatura as : cf.getAsignaturas()) {
                    ArrayOfDictadoMateria asignaturaOASis = getDictadosMateria(this.getCodCarrera(), as.getCodMateria());
                    if (asignaturaOASis != null) {

                        // Docentes
                        List<DictadoMateria> docentesOASis = asignaturaOASis.getDictadoMateria();
                        for (DictadoMateria docenteOASis : docentesOASis) {
                            if (docenteOASis != null
                                    && docenteOASis.getDocente() != null
                                    && docenteOASis.getDocente().getCedula() != null) {

                                Docente docente = new Docente(docenteOASis.getDocente()); //constructor copia
                                docentes.putIfAbsent(docente.getCedula(), docente);

                            }
                        }
                    }
                }
                this.getCamposFormacion().clear();
                cf.getAsignaturas().clear();
                cf.setDocentes(docentes);
                this.getCamposFormacion().add(cf);
                executor.shutdown();  //se ejecuta cuando termina el proceso load

            }

        } catch (Exception e) {
        }
    }

    private static ArrayOfDictadoMateria getDictadosMateria(java.lang.String codCarrera, java.lang.String codMateria) {
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getDictadosMateria(codCarrera, codMateria);
    }

}
