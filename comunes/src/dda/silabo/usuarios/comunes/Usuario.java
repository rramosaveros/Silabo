/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.usuarios.comunes;

import dda.silabo.clases.unidos.EntidadUnidos;
import dda.silabo.roles.comunes.Rol;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private Integer id;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String tipo;
    private String email;
    private String estado;
    private List<Rol> roles = new ArrayList<>();
    private List<EntidadUnidos> entidades = new ArrayList<>();

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
     * @return the nombre
     */
    public String getNombre() {
        return nombres;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombres = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellidos;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellidos = apellido;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the roles
     */
    public List<Rol> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Rol> roles) {
        this.roles = roles;
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

}
