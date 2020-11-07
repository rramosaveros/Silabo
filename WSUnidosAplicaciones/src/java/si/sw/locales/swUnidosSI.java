package si.sw.locales;

import ec.edu.espoch.academico.ArrayOfDictadoMateria;
import ec.edu.espoch.academico.ArrayOfEscuela;
import ec.edu.espoch.academico.ArrayOfEscuelaEntidad;
import ec.edu.espoch.academico.ArrayOfEstudiante;
import ec.edu.espoch.academico.ArrayOfFacultad;
import ec.edu.espoch.academico.ArrayOfHorarioClaseParalelo;
import ec.edu.espoch.academico.ArrayOfHorarioDocente;
import ec.edu.espoch.academico.ArrayOfMateria;
import ec.edu.espoch.academico.ArrayOfMateriaParalelo;
import ec.edu.espoch.academico.ArrayOfParametro;
import ec.edu.espoch.academico.ArrayOfPeriodo;
import ec.edu.espoch.academico.ArrayOfRolCarrera;
import ec.edu.espoch.academico.ArrayOfTitulosDocente;
import ec.edu.espoch.academico.ArrayOfUnidadAcademica;
import ec.edu.espoch.academico.Materia;
import ec.edu.espoch.academico.ParamCarrera;
import ec.edu.espoch.academico.Persona;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import si.sw.clases.Unidos;

//import unidos.comunes.EntidadLN;
@WebService(serviceName = "swUnidosSI")
public class swUnidosSI {

    @WebMethod(operationName = "lPADictadosMateria")//listo
    public ArrayOfDictadoMateria lPADictadosMateria(String codCarrera, String codMateria) {
        Unidos unidos = new Unidos();
        ArrayOfDictadoMateria result = unidos.lPADictadosMateria(codCarrera, codMateria);
        return result;
    }

    @WebMethod(operationName = "lPADatosMateria")//listo
    public Materia lPADatosMateria(java.lang.String codCarrera, java.lang.String codMateria) {
        Unidos unidos = new Unidos();
        Materia result = unidos.getDatosMateria(codCarrera, codMateria);
        return result;
    }

    @WebMethod(operationName = "getMateriasDocente")//listo
    public ArrayOfMateria getMateriasDocente(String codCarrera, String cedula, String codPeriodo) {
        Unidos unidos = new Unidos();
        ArrayOfMateria result = unidos.getMateriasDocente(codCarrera, cedula, codPeriodo);
        return result;
    }

    @WebMethod(operationName = "ArrayOfEscuelaEntidad")//listo
    public ArrayOfEscuelaEntidad getEscuelaEntidad() {
        Unidos unidos = new Unidos();
        ArrayOfEscuelaEntidad result = unidos.getEscuelaEntidad();
        return result;
    }

    @WebMethod(operationName = "getDatosUsuarioCarrera")//listo
    public Persona getDatosUsuarioCarrera(java.lang.String codCarrera, java.lang.String cedula) {
        Unidos unidos = new Unidos();
        Persona result = unidos.getDatosUsuarioCarrera(codCarrera, cedula);
        return result;
    }

    @WebMethod(operationName = "getMateriasEstudiante")//listo
    public ArrayOfMateria getMateriasEstudiante(java.lang.String codCarrera, java.lang.String cedula, java.lang.String codPeriodo) {
        Unidos unidos = new Unidos();
        ArrayOfMateria result = unidos.getMateriasEstudiante(codCarrera, cedula, codPeriodo);
        return result;
    }

    @WebMethod(operationName = "getDatosMateria")//listo
    public Materia getDatosMateria(java.lang.String codCarrera, java.lang.String codMateria) {
        Unidos unidos = new Unidos();
        Materia result = unidos.getDatosMateria(codCarrera, codMateria);
        return result;
    }

    @WebMethod(operationName = "autenticarUsuarioCarrera")//listo
    public ArrayOfRolCarrera autenticarUsuarioCarrera(java.lang.String arg0, java.lang.String arg1) {
        Unidos unidos = new Unidos();
        ArrayOfRolCarrera result = unidos.autenticarUsuarioCarrera(arg0, arg1);
        return result;
    }

    @WebMethod(operationName = "getRolUsuarioCarrera")//listo
    public ArrayOfRolCarrera getRolUsuarioCarrera(java.lang.String login) {
        Unidos unidos = new Unidos();
        ArrayOfRolCarrera result = unidos.getRolUsuarioCarrera(login);
        return result;
    }

    @WebMethod(operationName = "getPeriodosAcademicos")//listo
    public ArrayOfPeriodo getPeriodosAcademicos() {
        Unidos unidos = new Unidos();
        ArrayOfPeriodo result = unidos.getPeriodosAcademicos();
        return result;
    }

    @WebMethod(operationName = "getParametrosCarrera")//listo
    public ParamCarrera getParametrosCarrera(java.lang.String strCodCarrera) {
        Unidos unidos = new Unidos();
        ParamCarrera result = unidos.getParametrosCarrera(strCodCarrera);
        return result;
    }

    @WebMethod(operationName = "getParametrosCarreraTotal")//listo
    public ArrayOfParametro getParametrosCarreraTotal(java.lang.String strCodCarrera) {
        Unidos unidos = new Unidos();
        ArrayOfParametro result = unidos.getParametrosCarreraTotal(strCodCarrera);
        return result;
    }

    @WebMethod(operationName = "getMallaCurricularPensumVigente")//listo
    public void getMallaCurricularPensumVigente(java.lang.String strCodCarrera, javax.xml.ws.Holder<java.lang.String> pensum, javax.xml.ws.Holder<ec.edu.espoch.academico.ArrayOfMateriaPensum> getMallaCurricularPensumVigenteResult) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        Unidos unidos = new Unidos();
        unidos.getMallaCurricularPensumVigente(strCodCarrera, pensum, getMallaCurricularPensumVigenteResult);
    }

    @WebMethod(operationName = "getFacultadesTotales")//listo
    public ArrayOfFacultad getFacultadesTotales() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        Unidos unidos = new Unidos();
        ArrayOfFacultad result = unidos.getFacultadesTotales();
        return result;
    }

    @WebMethod(operationName = "getTodasEscuelas")//listo
    public ArrayOfEscuela getTodasEscuelas() {
        Unidos unidos = new Unidos();
        ArrayOfEscuela result = unidos.getTodasEscuelas();
        return result;

    }

    @WebMethod(operationName = "getTodasCarreras")//listo
    public ArrayOfUnidadAcademica getTodasCarreras() {
        Unidos unidos = new Unidos();
        ArrayOfUnidadAcademica result = unidos.getTodasCarreras();
        return result;

    }

    @WebMethod(operationName = "getTitulosDocentes")//listo
    public ArrayOfTitulosDocente getTitulosDocentes(String strCodCarrera, String strCedula) {
        Unidos unidos = new Unidos();
        ArrayOfTitulosDocente result = unidos.getTitulosDocentes(strCodCarrera, strCedula);
        return result;

    }

    @WebMethod(operationName = "ArrayOfHorarioDocente")//listo
    public ArrayOfHorarioDocente getHorarioDocente(String strCodCarrera, String strCedula) {
        Unidos unidos = new Unidos();
        ArrayOfHorarioDocente result = unidos.getHorarioDocente(strCodCarrera, strCedula);
        return result;

    }

    @WebMethod(operationName = "loadPracticasDocente")//listo
    public String loadPracticasDocente(String strCodCarrera, String strCedula) {
        Unidos unidos = new Unidos();
        String result = "";
        return result;

    }

    @WebMethod(operationName = "getHorariosDocenteParalelo")//listo
    public ArrayOfHorarioClaseParalelo getHorariosDocenteParalelo(String strCodCarrera, String strCedula, String strCodPeriodo) {
        Unidos unidos = new Unidos();
        ArrayOfHorarioClaseParalelo result = unidos.getHorariosDocenteParalelo(strCodCarrera, strCedula, strCodPeriodo);
        return result;

    }

    @WebMethod(operationName = "getAlumnosMateria")//listo
    public ArrayOfEstudiante getAlumnosMateria(java.lang.String strCodCarrera, java.lang.String strCodNivel, java.lang.String strCodParalelo, java.lang.String strCodPeriodo, java.lang.String strCodMateria) {
        Unidos unidos = new Unidos();
        ArrayOfEstudiante result = unidos.getAlumnosMateria(strCodCarrera, strCodNivel, strCodParalelo, strCodPeriodo, strCodMateria);
        return result;

    }

    @WebMethod(operationName = "getMateriasParaleloEstudiante")//listo
    public ArrayOfMateriaParalelo getMateriasParaleloEstudiante(java.lang.String codCarrera, java.lang.String cedula, java.lang.String codPeriodo) {
        Unidos unidos = new Unidos();
        ArrayOfMateriaParalelo result = unidos.getMateriasParaleloEstudiante(codCarrera, cedula, codPeriodo);
        return result;

    }

    @WebMethod(operationName = "getEstadoEstudianteCarrera")//listo
    public String getEstadoEstudianteCarrera(java.lang.String strCodCarrera, java.lang.String strCedula) {
        Unidos unidos = new Unidos();
        String result = unidos.getEstadoEstudianteCarrera(strCodCarrera, strCedula);
        return result;

    }

    //planificacion 
    //Servicio de Fin del Primer Parcial
    @WebMethod(operationName = "getFechaInicioPrimerParcialSWU")//vale
    public String getFechaInicioPrimerParcialSWU(@WebParam(name = "codigoEscuela") String codEscuela) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicio = new Date();
        String Fecha1 = "2017-10-10";

        return Fecha1;
    }

    @WebMethod(operationName = "getFechaFinPrimerParcialSWU")//vale
    public String getFechaFinPrimerParcialSWU(@WebParam(name = "codigoEscuela") String codEscuela) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicio = new Date();
        String Fecha1 = "2017-10-15";

        return Fecha1;
    }

    //Servicio de Fin del Segundo Parcial
    @WebMethod(operationName = "getFechaInicioSegundoParcialSWU")//vale
    public String getFechaInicioSegundoParcialSWU(@WebParam(name = "codigoEscuela") String codEscuela) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicio = new Date();
        String Fecha1 = "2017-11-14";

        return Fecha1;
    }

    @WebMethod(operationName = "getFechaFinSegundoParcialSWU")//vale
    public String getFechaFinSegundoParcialSWU(@WebParam(name = "codigoEscuela") String codEscuela) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicio = new Date();
        String Fecha1 = "2017-11-24";

        return Fecha1;
    }

    //Servicio de Fin del Tercer Parcial
    @WebMethod(operationName = "getFechaInicioTercerParcialSWU")//vale
    public String getFechaInicioTercerParcialSWU(@WebParam(name = "codigoEscuela") String codEscuela) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicio = new Date();
        String Fecha1 = "2018-01-16";

        return Fecha1;
    }

    @WebMethod(operationName = "getFechaFinTercerParcialSWU")//vale
    public String getFechaFinTercerParcialSWU(@WebParam(name = "codigoEscuela") String codEscuela) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicio = new Date();
        String Fecha1 = "2018-01-20";

        return Fecha1;
    }

    @WebMethod(operationName = "getFechaInicioClaseEscuelaSWU")//vale
    public String getFechaInicioClaseEscuelaSWU(@WebParam(name = "codigoEscuela") String codEscuela) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicio = new Date();
        String Fecha1 = "2017-10-10";

        return Fecha1;
    }

    @WebMethod(operationName = "getFechaFinClaseEscuelaSWU")//vale
    public String getFechaFinClaseEscuelaSWU(@WebParam(name = "codigoEscuela") String codEscuela) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicio = new Date();
        String Fecha1 = "2018-10-10";

        return Fecha1;
    }

}
