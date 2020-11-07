/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.datosdocentes.comunes;

import dda.silabo.clases.unidos.AsignaturaUnidos;
import dda.silabo.reportes.comunes.Estados;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TOSHIBA
 */
public class DatoDocente {

    /**
     * @return the strTercerNivel
     */
    public String getStrTercerNivel() {
        return strTercerNivel;
    }

    /**
     * @param strTercerNivel the strTercerNivel to set
     */
    public void setStrTercerNivel(String strTercerNivel) {
        this.strTercerNivel = strTercerNivel;
    }

    /**
     * @return the strCuartoNivel
     */
    public String getStrCuartoNivel() {
        return strCuartoNivel;
    }

    /**
     * @param strCuartoNivel the strCuartoNivel to set
     */
    public void setStrCuartoNivel(String strCuartoNivel) {
        this.strCuartoNivel = strCuartoNivel;
    }

    private Integer id;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String rol;
    private List<AsignaturaUnidos> asignaturas = new ArrayList<>();
    private Estados estados = new Estados();
    private List<Titulos> tercerNivel = new ArrayList<>();
    private List<Titulos> cuartoNivel = new ArrayList<>();
    private String strTercerNivel;
    private String strCuartoNivel;
    private Integer idTercerNivel;
    private Integer idCuartoNivel;

    public DatoDocente() {
        this.strTercerNivel = "";
        this.strCuartoNivel = "";
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the asignaturas
     */
    public List<AsignaturaUnidos> getAsignaturas() {
        return asignaturas;
    }

    /**
     * @param asignaturas the asignaturas to set
     */
    public void setAsignaturas(List<AsignaturaUnidos> asignaturas) {
        this.asignaturas = asignaturas;
    }

    /**
     * @return the estados
     */
    public Estados getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(Estados estados) {
        this.estados = estados;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * @return the tercerNivel
     */
    public List<Titulos> getTercerNivel() {
        return tercerNivel;
    }

    /**
     * @param tercerNivel the tercerNivel to set
     */
    public void setTercerNivel(List<Titulos> tercerNivel) {
        this.tercerNivel = tercerNivel;
    }

    /**
     * @return the cuartoNivel
     */
    public List<Titulos> getCuartoNivel() {
        return cuartoNivel;
    }

    /**
     * @param cuartoNivel the cuartoNivel to set
     */
    public void setCuartoNivel(List<Titulos> cuartoNivel) {
        this.cuartoNivel = cuartoNivel;
    }

    /**
     * @return the idTercerNivel
     */
    public Integer getIdTercerNivel() {
        return idTercerNivel;
    }

    /**
     * @param idTercerNivel the idTercerNivel to set
     */
    public void setIdTercerNivel(Integer idTercerNivel) {
        this.idTercerNivel = idTercerNivel;
    }

    /**
     * @return the idCuartoNivel
     */
    public Integer getIdCuartoNivel() {
        return idCuartoNivel;
    }

    /**
     * @param idCuartoNivel the idCuartoNivel to set
     */
    public void setIdCuartoNivel(Integer idCuartoNivel) {
        this.idCuartoNivel = idCuartoNivel;
    }

}
