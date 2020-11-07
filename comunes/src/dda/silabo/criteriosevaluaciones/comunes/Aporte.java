/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.criteriosevaluaciones.comunes;

/**
 *
 * @author jose-
 */
public class Aporte {

    private Integer id_aportes;
    private String descripcion;
    private Nota nota = new Nota();
    private Integer notaTotal;

    public Aporte() {
        notaTotal = 0;
    }

    /**
     * @return the id_aportes
     */
    public Integer getId_aportes() {
        return id_aportes;
    }

    /**
     * @param id_aportes the id_aportes to set
     */
    public void setId_aportes(Integer id_aportes) {
        this.id_aportes = id_aportes;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the nota
     */
    public Nota getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(Nota nota) {
        this.nota = nota;
    }

    /**
     * @return the notaTotal
     */
    public Integer getNotaTotal() {
        return notaTotal;
    }

    /**
     * @param notaTotal the notaTotal to set
     */
    public void setNotaTotal(Integer notaTotal) {
        this.notaTotal = notaTotal;
    }
}
