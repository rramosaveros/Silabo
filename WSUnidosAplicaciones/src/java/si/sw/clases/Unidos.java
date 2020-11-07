/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.sw.clases;

import ec.edu.espoch.academico.ArrayOfDictadoMateria;
import ec.edu.espoch.academico.ArrayOfEscuela;
import ec.edu.espoch.academico.ArrayOfEscuelaEntidad;
import ec.edu.espoch.academico.ArrayOfEstudiante;
import ec.edu.espoch.academico.ArrayOfFacultad;
import ec.edu.espoch.academico.ArrayOfHorarioClaseParalelo;
import ec.edu.espoch.academico.ArrayOfHorarioDocente;
import ec.edu.espoch.academico.ArrayOfMateria;
import ec.edu.espoch.academico.ArrayOfMateriaParalelo;
import ec.edu.espoch.academico.ArrayOfMateriaPensum;
import ec.edu.espoch.academico.ArrayOfParametro;
import ec.edu.espoch.academico.ArrayOfPeriodo;
import ec.edu.espoch.academico.ArrayOfRolCarrera;
import ec.edu.espoch.academico.ArrayOfTitulosDocente;
import ec.edu.espoch.academico.ArrayOfUnidadAcademica;
import ec.edu.espoch.academico.Materia;
import ec.edu.espoch.academico.ParamCarrera;
import ec.edu.espoch.academico.Persona;
import javax.xml.ws.Holder;

/**
 *
 * @author Jorge Zaruma
 */
public class Unidos {

    public ArrayOfDictadoMateria lPADictadosMateria(String codCarrera, String codMateria) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getDictadosMateria(codCarrera, codMateria);
    }

    public Materia lPADatosMateria(java.lang.String codCarrera, java.lang.String codMateria) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getDatosMateria(codCarrera, codMateria);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayOfMateria getMateriasDocente(String codCarrera, String cedula, String codPeriodo) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getMateriasDocente(codCarrera, cedula, codPeriodo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayOfEscuelaEntidad getEscuelaEntidad() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getEscuelaEntidad();
    }

    public Persona getDatosUsuarioCarrera(java.lang.String codCarrera, java.lang.String cedula) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getDatosUsuarioCarrera(codCarrera, cedula);
    }

    public ArrayOfMateria getMateriasEstudiante(java.lang.String codCarrera, java.lang.String cedula, java.lang.String codPeriodo) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getMateriasEstudiante(codCarrera, cedula, codPeriodo);
    }

    public Materia getDatosMateria(java.lang.String codCarrera, java.lang.String codMateria) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getDatosMateria(codCarrera, codMateria);
    }

    public ArrayOfRolCarrera getRolUsuarioCarrera(java.lang.String login) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ec.edu.espoch.academico.Seguridad service = new ec.edu.espoch.academico.Seguridad();
        ec.edu.espoch.academico.SeguridadSoap port = service.getSeguridadSoap();
        return port.getRolUsuarioCarrera(login);
    }

    public ArrayOfPeriodo getPeriodosAcademicos() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getPeriodosAcademicos();
    }

    public ParamCarrera getParametrosCarrera(java.lang.String strCodCarrera) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getParametrosCarrera(strCodCarrera);
    }

    public ArrayOfParametro getParametrosCarreraTotal(java.lang.String strCodCarrera) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getParametrosCarreraTotal(strCodCarrera);
    }

    public ArrayOfRolCarrera autenticarUsuarioCarrera(java.lang.String login, java.lang.String password) {
        ec.edu.espoch.academico.Seguridad service = new ec.edu.espoch.academico.Seguridad();
        ec.edu.espoch.academico.SeguridadSoap port = service.getSeguridadSoap();
        return port.autenticarUsuarioCarrera(login, password);
    }

    public void getMallaCurricularPensumVigente(String strCodCarrera, Holder<String> pensum, Holder<ArrayOfMateriaPensum> mallaCurricularPensumVigenteResult) {
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        port.getMallaCurricularPensumVigente(strCodCarrera, pensum, mallaCurricularPensumVigenteResult);
    }

    public ArrayOfFacultad getFacultadesTotales() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getFacultadesTotales();
    }

    public ArrayOfEscuela getTodasEscuelas() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getTodasEscuelas();
    }

    public ArrayOfUnidadAcademica getTodasCarreras() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getTodasCarreras();
    }

    public ArrayOfTitulosDocente getTitulosDocentes(java.lang.String strCodCarrera, java.lang.String strCedula) {
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getTitulosDocentes(strCodCarrera, strCedula);
    }

    public ArrayOfHorarioClaseParalelo getHorariosDocenteParalelo(java.lang.String strCodCarrera, java.lang.String strCedula, java.lang.String strCodPeriodo) {
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getHorariosDocenteParalelo(strCodCarrera, strCedula, strCodPeriodo);
    }

    public ArrayOfEstudiante getAlumnosMateria(java.lang.String strCodCarrera, java.lang.String strCodNivel, java.lang.String strCodParalelo, java.lang.String strCodPeriodo, java.lang.String strCodMateria) {
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getAlumnosMateria(strCodCarrera, strCodNivel, strCodParalelo, strCodPeriodo, strCodMateria);
    }

    public ArrayOfMateriaParalelo getMateriasParaleloEstudiante(java.lang.String codCarrera, java.lang.String cedula, java.lang.String codPeriodo) {
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getMateriasParaleloEstudiante(codCarrera, cedula, codPeriodo);
    }

    public String getEstadoEstudianteCarrera(java.lang.String strCodCarrera, java.lang.String strCedula) {
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getEstadoEstudianteCarrera(strCodCarrera, strCedula);
    }

    public ArrayOfHorarioDocente getHorarioDocente(java.lang.String codCarrera, java.lang.String strCedula) {
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getHorarioDocente(codCarrera, strCedula);
    }

}
