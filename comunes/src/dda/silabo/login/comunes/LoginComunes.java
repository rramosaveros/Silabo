/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.login.comunes;

import dda.silabo.roles.comunes.Rol;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class LoginComunes {

    /**
     * @return the rolActivo
     */
    public String getRolActivo() {
        return rolActivo;
    }

    /**
     * @param rolActivo the rolActivo to set
     */
    public void setRolActivo(String rolActivo) {
        this.rolActivo = rolActivo;
    }

    private String cedula, nombres, apellidos, email, rolActivo;
    private Integer idRolActivo;
    private List<Rol> roles = new ArrayList<>();

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
     * @return the idRolActivo
     */
    public Integer getIdRolActivo() {
        return idRolActivo;
    }

    /**
     * @param idRolActivo the idRolActivo to set
     */
    public void setIdRolActivo(Integer idRolActivo) {
        this.idRolActivo = idRolActivo;
    }

}
