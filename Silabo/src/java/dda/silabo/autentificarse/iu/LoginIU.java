/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.autentificarse.iu;

import dda.silabo.login.comunes.LoginComunes;
import dda.silabo.roles.comunes.Rol;

/**
 *
 * @author Jorge Zaruma
 */
public class LoginIU extends LoginComunes {
    
    public void agregarRolActivo(String rolActivo) {
        int idRolActivo = 0;
        if (rolActivo == null) {
            for (Rol rol : this.getRoles()) {
                if (rol.getRolChar().equals("Doc")) {
                    rolActivo = rol.getRolChar();
                    idRolActivo = rol.getIdRol();
                }
            }
            if (rolActivo != null) {
                this.setRolActivo(rolActivo);
                this.setIdRolActivo(idRolActivo);
            } else if (!this.getRoles().isEmpty()) {
                this.setRolActivo(this.getRoles().get(0).getRolChar());
                this.setIdRolActivo(this.getRoles().get(0).getIdRol());
            }
        } else {
            for (Rol rol : this.getRoles()) {
                if (rol.getRolChar().equals(rolActivo)) {
                    rolActivo = rol.getRolChar();
                    idRolActivo = rol.getIdRol();
                }
            }
            this.setRolActivo(rolActivo);
            this.setIdRolActivo(idRolActivo);
        }
        
    }
}
