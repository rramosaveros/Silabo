/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.autentificarse.iu;

/**
 *
 * @author Jorge Zaruma
 */
public class Persona {

    private String Nombres, Apellidos, Cedula;

    /**
     * @return the Nombres
     */
    public String getNombres() {
        return Nombres;
    }

    /**
     * @param Nombres the Nombres to set
     */
    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    /**
     * @return the Apellidos
     */
    public String getApellidos() {
        return Apellidos;
    }

    /**
     * @param Apellidos the Apellidos to set
     */
    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    /**
     * @return the Cedulas
     */
    public String getCedula() {
        return Cedula;
    }

    /**
     * @param Cedulas the Cedulas to set
     */
    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

}
