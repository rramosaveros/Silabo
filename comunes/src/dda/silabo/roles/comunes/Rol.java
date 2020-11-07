/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.roles.comunes;

import dda.silabo.clases.unidos.EntidadUnidos;
import dda.silabo.opciones.comunes.Opcion;
import dda.silabo.usuarios.comunes.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adry
 */
public class Rol {

    private Integer idTipoEntidad;
    private Integer idRol;
    private String descRol;
    private String estado;
    private String check;
    private String rolChar;
    private List<EntidadUnidos> entidades = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Opcion> opciones = new ArrayList<>();
    private String fncClick;

    /**
     * @return the descRol
     */
    public String getDescRol() {
        return descRol;
    }

    /**
     * @param descRol the descRol to set
     */
    public void setDescRol(String descRol) {
        this.descRol = descRol;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
        public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the idRol
     */
    public Integer getIdRol() {
        return idRol;
    }

    /**
     * @param idRol the idRol to set
     */
    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the opciones
     */
    public List<Opcion> getOpciones() {
        return opciones;
    }

    /**
     * @param opciones the opciones to set
     */
    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }

    /**
     * @return the check
     */
    public String getCheck() {
        return check;
    }

    /**
     * @param check the check to set
     */
    public void setCheck(String check) {
        this.check = check;
    }

    /**
     * @return the rolChar
     */
    public String getRolChar() {
        return rolChar;
    }

    /**
     * @param rolChar the rolChar to set
     */
    public void setRolChar(String rolChar) {
        this.rolChar = rolChar;
    }

    /**
     * @return the idTipoEntidad
     */
    public Integer getIdTipoEntidad() {
        return idTipoEntidad;
    }

    /**
     * @param idTipoEntidad the idTipoEntidad to set
     */
    public void setIdTipoEntidad(Integer idTipoEntidad) {
        this.idTipoEntidad = idTipoEntidad;
    }

    /**
     * @return the entidades
     */
    public List<EntidadUnidos> getEntidades() {
        return entidades;
    }

    /**
     * @param entidades the entidades to set
     */
    public void setEntidades(List<EntidadUnidos> entidades) {
        this.entidades = entidades;
    }

    /**
     * @return the fncClick
     */
    public String getFncClick() {
        return fncClick;
    }

    /**
     * @param fncClick the fncClick to set
     */
    public void setFncClick(String fncClick) {
        this.fncClick = fncClick;
    }

}
