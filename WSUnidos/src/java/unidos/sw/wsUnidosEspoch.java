/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.sw;

import dda.panalitico.ws.ArchivoAD;
import dda.panalitico.ws.BibliografiasComunes;
import dda.panalitico.ws.ContenidosAD;
import dda.panalitico.ws.ProcedimientosAD;
import dda.silabo.sw.EstructuraDesarrolloAD;
import ec.edu.espoch.academico.ArrayOfMateriaPensum;
import ec.edu.espoch.academico.Periodo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import unidos.entidad.ln.EntidadLN;
import unidos.ies.ad.CarreraAD;
import unidos.ies.ad.DocenteAD;
import unidos.ies.ad.EscuelaAD;
import unidos.ies.ad.FacultadAD;
import unidos.ies.ad.IesAD;
import unidos.ies.ln.CarreraLN;
import unidos.ies.ln.DocenteLN;
import unidos.ies.ln.EscuelaLN;
import unidos.ies.ln.FacultadLN;
import unidos.ies.ln.IesLN;
import unidos.panalitico.ln.ProgramaAnaliticoLN;
import unidos.pensum.PensumLN;
import unidos.periodo.PeriodoLN;
import unidos.silabo.ln.SilaboLN;

@WebService(serviceName = "wsUnidosEspoch")
public class wsUnidosEspoch {

    //******************************************************************************************************
    //*                                            FACULTADES                                              *
    //******************************************************************************************************
    @WebMethod(operationName = "FacultadesLista")//listo
    public IesAD FacultadesLista(@WebParam(name = "jsonEntidad") String jsonEntidad) {
        IesAD result = null;
        try {
            IesLN iesLN = new IesLN();
            result = iesLN.FacultadesLista(jsonEntidad);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "ERROR|Entidades: ".concat(e.getMessage()));
        }
        return result;
    }

    //******************************************************************************************************
    //*                                              ESCUELAS                                              *
    //******************************************************************************************************
    @WebMethod(operationName = "EscuelasLista")//listo
    public FacultadAD EscuelasLista(@WebParam(name = "jsonEntidad") String jsonEntidad) {
        FacultadAD result = null;
        try {
            FacultadLN facultadLN = new FacultadLN();
            result = facultadLN.EscuelasLista(jsonEntidad);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|Escuelas ".concat(e.getMessage()));
        }
        return result;
    }

    //******************************************************************************************************
    //*                                              CARRERAS                                              *
    //******************************************************************************************************
    @WebMethod(operationName = "CarrerasLista")//listo
    public EscuelaAD CarrerasLista(@WebParam(name = "jsonEntidad") String jsonEntidad) {
        EscuelaAD result = null;
        try {
            EscuelaLN escuelaLN = new EscuelaLN();
            result = escuelaLN.CarrerasLista(jsonEntidad);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|Carreras: ".concat(e.getMessage()));
        }
        return result;
    }

    @WebMethod(operationName = "redisFlushAll")//listo
    public String redisFlushAll() {
        String result = null;
        try {
            IesLN iesLN = new IesLN();
            result = iesLN.redisFlushAll();
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|redisFlushAll: ".concat(e.getMessage()));
        }
        return result;
    }

    //******************************************************************************************************
    //*                              DOCENTES CAMPOS DE FORMACION                                       *
    //******************************************************************************************************
    @WebMethod(operationName = "DocentesCamposFormacion")//FALTA
    public CarreraAD DocentesCamposFormacion(@WebParam(name = "jsonCarrera") String jsonCarrera) {
        CarreraAD result = null;
        IesLN iesLN = new IesLN();
        try {
            result = iesLN.DocentesCamposFormacion(jsonCarrera);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|CamposFormacionCarrera: ".concat(e.getMessage()));
        }
        return result;
    }

    //******************************************************************************************************
    //*                                    DOCENTES POR CARRERA                                            *
    //******************************************************************************************************
    @WebMethod(operationName = "DocentesCarrera")//FALTA
    public CarreraAD DocentesCarrera(@WebParam(name = "jsonCarrera") String jsonCarrera) {
        CarreraAD result = null;
        IesLN iesLN = new IesLN();
        try {
            result = iesLN.DocentesCarrera(jsonCarrera);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|DocentesCarrera: ".concat(e.getMessage()));
        }
        return result;
    }

    //******************************************************************************************************
    //*                                    DOCENTES POR ESCUELA                                          *
    //******************************************************************************************************
    @WebMethod(operationName = "DocentesEscuela")
    public EscuelaAD DocentesEscuela(@WebParam(name = "jsonCarrera") String jsonCarrera) {
        EscuelaAD result = null;
        IesLN iesLN = new IesLN();
        try {
            result = iesLN.DocentesEscuela(jsonCarrera);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|DocentesEscuela: ".concat(e.getMessage()));
        }
        return result;
    }

    //******************************************************************************************************
    //*                                    DOCENTES POR FACULTAD                                            *
    //******************************************************************************************************
    @WebMethod(operationName = "DocentesFacultad")
    public FacultadAD DocentesFacultad(@WebParam(name = "jsonFacultad") String jsonFacultad) {
        FacultadAD result = null;
        IesLN iesLN = new IesLN();
        try {
            result = iesLN.DocentesFacultad(jsonFacultad);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|DocentesFacultad: ".concat(e.getMessage()));
        }
        return result;
    }

    //******************************************************************************************************
    //*                                 ASIGNATURAS POR CARRERA                                            *
    //******************************************************************************************************
    @WebMethod(operationName = "AsignaturasCarrera")
    public CarreraAD AsignaturasCarrera(@WebParam(name = "jsonCarrera") String jsonCarrera) {
        CarreraAD result = null;
        IesLN iesLN = new IesLN();
        try {
            result = iesLN.AsignaturasCarrera(jsonCarrera);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|AsignaturasCarrera: ".concat(e.getMessage()));
        }
        return result;
    }

    //******************************************************************************************************
    //*                              CAMPOS DE FORMACION POR CARRERA                                       *
    //******************************************************************************************************
    @WebMethod(operationName = "AsignaturasCamposFormacion")
    public CarreraAD AsignaturasCamposFormacion(@WebParam(name = "jsonCarrera") String jsonCarrera) {
        CarreraAD result = null;
        IesLN iesLN = new IesLN();
        try {
            result = iesLN.AsignaturasCamposFormacion(jsonCarrera);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|CamposFormacionCarrera: ".concat(e.getMessage()));
        }
        return result;
    }
    //******************************************************************************************************
    //*                                             ARBOL INSTITUCION                                      *
    //******************************************************************************************************

    @WebMethod(operationName = "UnidadesAcademicasInstitucion")//listo
    public String UnidadesAcademicasCargar(@WebParam(name = "jsonDatos") String jsonDatos) {

        String result = "";
        EntidadLN iesLN = new EntidadLN();
        try {
            result = iesLN.loadUnidadesAcademicas();
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|CamposFormacionCarrera: ".concat(e.getMessage()));
        }
        return result;
    }

    //******************************************************************************************************
    //*                                             ARBOL FACULTAD                                         *
    //******************************************************************************************************
    @WebMethod(operationName = "ArbolFacultad")//listo
    public String arbolFacultad(@WebParam(name = "jsonFacultad") String jsonFacultad) {
        String result = "";
        FacultadLN facultadLN = new FacultadLN();
        try {
            result = facultadLN.arbolFacultad(jsonFacultad);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|arbolFacultad: ".concat(e.getMessage()));
        }
        return result;
    }

    //******************************************************************************************************
    //*                                             ARBOL ESCUELA                                         *
    //******************************************************************************************************
    @WebMethod(operationName = "ArbolEscuela")//listo
    public String arbolEscuela(@WebParam(name = "jsonEscuela") String jsonEscuela) {
        String result = "";
        EscuelaLN escuelaLN = new EscuelaLN();
        try {
            result = escuelaLN.arbolEscuela(jsonEscuela);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|arbolEscuela: ".concat(e.getMessage()));
        }
        return result;
    }

    //******************************************************************************************************
    //*                                             ARBOL CARRERA                                         *
    //******************************************************************************************************
    @WebMethod(operationName = "ArbolCarrera")//listo
    public CarreraAD arbolCarrera(@WebParam(name = "jsonCarrera") String jsonCarrera) {
        CarreraAD result = null;
        CarreraLN carreraLN = new CarreraLN();
        try {
            result = carreraLN.arbolCarrera(jsonCarrera);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|arbolEscuela: ".concat(e.getMessage()));
        }
        return result;
    }
//******************************************************************************************************
    //*                                            Ultimo Periodo Valido                                        *
    //******************************************************************************************************

    @WebMethod(operationName = "PeriodoActual")//listo
    public Periodo PeriodoValido() {
        Periodo result = null;
        PeriodoLN periodoLN = new PeriodoLN();
        try {
            result = periodoLN.PeriodoValido();
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|arbolEscuela: ".concat(e.getMessage()));
        }
        return result;
    }
    //******************************************************************************************************
    //*                                            Ultimo Pensum Valido                                        *
    //******************************************************************************************************

    @WebMethod(operationName = "PensumVigente")//listo
    public ArrayOfMateriaPensum PensumValido(@WebParam(name = "jsonCarrera") String jsonCarrera) {
        ArrayOfMateriaPensum result = null;
        PensumLN pensumLN = new PensumLN();
        try {
            result = pensumLN.PensumValido(jsonCarrera);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|arbolEscuela: ".concat(e.getMessage()));
        }
        return result;
    }

    //******************************************************************************************************
    //*                                            INFORMACION DE DOCENTES                                        *
    //******************************************************************************************************
    @WebMethod(operationName = "DocenteInformacion")//listo
    public DocenteAD DocenteInformacion(@WebParam(name = "cedula") String cedula) {
//        String result = null;
        DocenteLN docenteLN = new DocenteLN();
        DocenteAD result = null;
        try {
            result = docenteLN.DocenteInformacion(cedula);
        } catch (Exception e) {
            Logger.getLogger("WSBDatosAnalitico").log(Level.SEVERE, "WSBDatosAnalitico|arbolEscuela: ".concat(e.getMessage()));
        }
        return result;
    }

    //******************************************************************************************************
    //*                                            LOGOS DE INSTITUCION                                        *
    //******************************************************************************************************
    @WebMethod(operationName = "LogosInstitucion")
    public ArchivoAD LogosInstitucion(@WebParam(name = "codEntidad") String codEntidad, @WebParam(name = "tipoEntidad") String tipoEntidad) {
        ArchivoAD result;
        try {
            ProgramaAnaliticoLN unidades = new ProgramaAnaliticoLN();
            result = unidades.programaAnaliticoLogoEntidad(codEntidad, tipoEntidad);
        } catch (Exception e) {
            result = null;
        }

        return result;
    }
    //******************************************************************************************************
    //*                                            SERVICIOS WEB DE PROGRAMAS ANALITICOS                                   *
    //******************************************************************************************************

    @WebMethod(operationName = "ProgramaAnaliticoBibliografias")
    public BibliografiasComunes ProgramaAnaliticoBibliografias(@WebParam(name = "codAsignatura") String codAsignatura, @WebParam(name = "codCarrera") String codCarrera) {
        BibliografiasComunes codigo = null;
        try {
            ProgramaAnaliticoLN bibliografiasLN = new ProgramaAnaliticoLN();
            codigo = bibliografiasLN.programaAnaliticoBibliografias(codAsignatura, codCarrera);
        } catch (Exception e) {
        }
        return codigo;
    }

    //WS QUE DEVUELVE LOS CONTENIDOS DE UN PLAN ANALÍTICO DADO LA ASIGNATURA Y CARRERA
    @WebMethod(operationName = "ProgramaAnaliticoContenidosAsignatura")
    public ContenidosAD ProgramaAnaliticoContenidosAsignatura(@WebParam(name = "codAsginatura") String codAsginatura, @WebParam(name = "codCarrera") String codCarrera) {
        ContenidosAD JSON;
        try {
            ProgramaAnaliticoLN logica = new ProgramaAnaliticoLN();
            JSON = logica.programaAnaliticoContenidosAsignatura(codAsginatura, codCarrera);
        } catch (Exception e) {
            JSON = null;
        }
        return JSON;
    }
    //WS QUE DEVUELVE LOS PROCEDIMIENTOS DE UN PLAN ANALÍTICO DADO LA ASIGNATURA Y CARRERA

    @WebMethod(operationName = "ProgramaAnaliticoProcedimientosAsignatura")
    public ProcedimientosAD ProgramaAnaliticoProcedimientosAsignatura(@WebParam(name = "codAsginatura") String codAsginatura, @WebParam(name = "codCarrera") String codCarrera) {
        ProcedimientosAD result;
        try {
            ProgramaAnaliticoLN objLN = new ProgramaAnaliticoLN();
            result = objLN.programaAnaliticoProcedimientosAsignatura(codAsginatura, codCarrera);
        } catch (Exception e) {
            result = null;
        }

        return (result);
    }

    //WS QUE DEVUELVE LA VIGENCIA DE UNA CARRRERA DE ACUERDO A SU CODIGO
    @WebMethod(operationName = "ProgramaAnaliticoVigenciaCarrera")
    public String ProgramaAnaliticoVigenciaCarrera(@WebParam(name = "codCarrera") String codCarrera) {
        String result = "no definido";
        try {
            ProgramaAnaliticoLN objLN = new ProgramaAnaliticoLN();
            result = objLN.programaAnaliticoVigenciaCarrera(codCarrera); 
        } catch (Exception e) {
            result = null;
        }

        return (result);
    }
    //******************************************************************************************************
    //*                                            SERVICIOS WEB DE SILABOS                                       *
    //******************************************************************************************************

    @WebMethod(operationName = "SilaboContenidoAsignatura")
    public EstructuraDesarrolloAD SilaboContenidoAsignatura(@WebParam(name = "codCarrera") String codCarrera, @WebParam(name = "codPeriodo") String codPeriodo, @WebParam(name = "codMateria") String codMateria) {
        EstructuraDesarrolloAD result = null;
        try {
            SilaboLN silaboLN = new SilaboLN();
            result = silaboLN.silaboContenidoAsignatura(codCarrera, codPeriodo, codMateria);
        } catch (Exception e) {

        }
        return result;
    }
    //******************************************************************************************************
    //*                                            SERVICIOS WEB DE CALENDARIO-Fechas Feriados                                     *
    //******************************************************************************************************

    @WebMethod(operationName = "fechasFeriadosSW")
    public String fechasFeriadosSW(@WebParam(name = "datos") String datos) {
        String result = null;
        try {
            result = "[{\"title\": \"Inicio periodo examenes principales y consignacion de calificaciones\",\"start\":\"2016-08-01T00:00\",\"end\":\"2016-08-01T23:00\"},{\"title\": \"Fecha limite de entrega de notas\",\"start\":\"2017-02-04T00:00\",\"end\":\"2017-02-04T24:00\"},{\"title\": \"Día de Trabajo \",\"start\":\"2017-05-01T00:00\",\"end\":\"2017-05-01T00:00\"},{\"title\": \"dfgdf\",\"start\":\"2017-10-09T12:00\",\"end\":\"2017-10-09T12:00\"},{\"title\": \"Independencia de Cuenca\",\"start\":\"2017-11-02T12:00\",\"end\":\"2017-11-02T12:00\"}]";

        } catch (Exception e) {
            result = null;

        }
        return result;
    }

    //******************************************************************************************************
    //*                                            SERVICIOS WEB DE CALENDARIO-Fechas Entrega de Notas                                    *
    //******************************************************************************************************
    @WebMethod(operationName = "fechasEntregaNotasSW")
    public String fechasEntregaNotasSW(@WebParam(name = "datos") String datos) {
        String result = null;
        try {
            result = "[{\"title\": \"Fecha limite consignación de calificaciones segundo parcial\",\"start\":\"2017-06-16T00:00\",\"end\":\"2017-06-16T23:59\"},{\"title\": \"Parcial1\",\"start\":\"2017-11-07T12:00\",\"end\":\"2017-11-10T24:00\"}]";

        } catch (Exception e) {
            result = null;

        }
        return result;
    }
}
