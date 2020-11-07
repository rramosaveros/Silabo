/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.comunes;

import ec.edu.espoch.academico.Persona;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adry Qp
 */
public class Docente {

    private String cedula;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String correoAlternativo;
    private String celular;
    private List<DocenteTitulos> tercerNivel = new ArrayList<>();
    private List<DocenteTitulos> cuartoNivel = new ArrayList<>();

    public Docente() {
    }

    public Docente(Persona docente) {
        if (docente.getCedula() != null) {
            this.cedula = docente.getCedula();
        }
        if (docente.getNombres() != null) {
            this.nombres = docente.getNombres();
        }
        if (docente.getApellidos() != null) {
            this.apellidos = docente.getApellidos();
        }
//        if (dictado.getDocente().getTelefono() != null) {
//            this.telefono = dictado.getDocente().getTelefono();
//        }
        if (docente.getEmail() != null) {
            this.correo = docente.getEmail();
        }
//        if (dictado.getDocente().getTituloTercer() != null) {
//            this.tituloTercer = dictado.getDocente().getTituloTercer();
//        }
//        if (dictado.getDocente().getTituloPost() != null) {
//            this.tituloPost = dictado.getDocente().getTituloPost();
//        }
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
     * @return the tercerNivel
     */
    public List<DocenteTitulos> getTercerNivel() {
        return tercerNivel;
    }

    /**
     * @param tercerNivel the tercerNivel to set
     */
    public void setTercerNivel(List<DocenteTitulos> tercerNivel) {
        this.tercerNivel = tercerNivel;
    }

    /**
     * @return the cuartoNivel
     */
    public List<DocenteTitulos> getCuartoNivel() {
        return cuartoNivel;
    }

    /**
     * @param cuartoNivel the cuartoNivel to set
     */
    public void setCuartoNivel(List<DocenteTitulos> cuartoNivel) {
        this.cuartoNivel = cuartoNivel;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the correoAlternativo
     */
    public String getCorreoAlternativo() {
        return correoAlternativo;
    }

    /**
     * @param correoAlternativo the correoAlternativo to set
     */
    public void setCorreoAlternativo(String correoAlternativo) {
        this.correoAlternativo = correoAlternativo;
    }
}
