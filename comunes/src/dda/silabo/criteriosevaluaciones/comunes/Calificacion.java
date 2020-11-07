/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.criteriosevaluaciones.comunes;

/**
 *
 * @author Jorge
 */
public class Calificacion {
    private String actividad;
    private String aporte;
    private Integer nota;
    /**
     * @return the actividad
     */
    public String getActividad() {
        return actividad;
    }

    /**
     * @param actividad the actividad to set
     */
    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    /**
     * @return the aporte
     */
    public String getAporte() {
        return aporte;
    }

    /**
     * @param aporte the aporte to set
     */
    public void setAporte(String aporte) {
        this.aporte = aporte;
    }

    /**
     * @return the nota
     */
    public Integer getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(Integer nota) {
        this.nota = nota;
    }

}
