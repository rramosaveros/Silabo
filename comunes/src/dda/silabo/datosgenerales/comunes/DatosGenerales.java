/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.datosgenerales.comunes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class DatosGenerales {

    private String nombre_facultad;
    private String nombre_escuela;
    private String nombre_carrera;
    private String nombre_sede;
    private String modalidad;
    private String silabo_materia;
    private String nivel;
    private String periodo_academico;
    private String campo;
    private String codigoCampo;
    private String codigo_asignatura;
    private Double numero_creditos;
    private Integer horas_semanales;
    private String correquisitos;
    private String prerequisitos;
    private String titulo;
    private String ayuda;
    private String vigencia;
    

    public DatosGenerales() {

        this.silabo_materia = "No definido";
        this.nivel = "";
        this.periodo_academico = "";
        this.campo = "";
        this.codigoCampo = "";
        this.codigo_asignatura = "";
        this.numero_creditos = 0.00;
        this.horas_semanales = 0;
        this.correquisitos = "";
        this.prerequisitos = "";
        this.vigencia = "vigente";
    }

    /**
     * @return the correquisitos
     */
    public String getCorrequisitos() {
        return correquisitos;
    }

    /**
     * @param correquisitos the correquisitos to set
     */
    public void setCorrequisitos(String correquisitos) {
        this.correquisitos = correquisitos;
    }

    /**
     * @return the prerequisitos
     */
    public String getPrerequisitos() {
        return prerequisitos;
    }

    /**
     * @param prerequisitos the prerequisitos to set
     */
    public void setPrerequisitos(String prerequisitos) {
        this.prerequisitos = prerequisitos;
    }

    /**
     * @return the nombre_facultad
     */
    public String getNombre_facultad() {
        return nombre_facultad;
    }

    /**
     * @param nombre_facultad the nombre_facultad to set
     */
    public void setNombre_facultad(String nombre_facultad) {
        this.nombre_facultad = nombre_facultad;
    }

    /**
     * @return the nombre_escuela
     */
    public String getNombre_escuela() {
        return nombre_escuela;
    }

    /**
     * @param nombre_escuela the nombre_escuela to set
     */
    public void setNombre_escuela(String nombre_escuela) {
        this.nombre_escuela = nombre_escuela;
    }

    /**
     * @return the nombre_carrera
     */
    public String getNombre_carrera() {
        return nombre_carrera;
    }

    /**
     * @param nombre_carrera the nombre_carrera to set
     */
    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    /**
     * @return the nombre_sede
     */
    public String getNombre_sede() {
        return nombre_sede;
    }

    /**
     * @param nombre_sede the nombre_sede to set
     */
    public void setNombre_sede(String nombre_sede) {
        this.nombre_sede = nombre_sede;
    }

    /**
     * @return the modalidad
     */
    public String getModalidad() {
        return modalidad;
    }

    /**
     * @param modalidad the modalidad to set
     */
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    /**
     * @return the silabo_materia
     */
    public String getSilabo_materia() {
        return silabo_materia;
    }

    /**
     * @param silabo_materia the silabo_materia to set
     */
    public void setSilabo_materia(String silabo_materia) {
        this.silabo_materia = silabo_materia;
    }

    /**
     * @return the nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the periodo_academico
     */
    public String getPeriodo_academico() {
        return periodo_academico;
    }

    /**
     * @param periodo_academico the periodo_academico to set
     */
    public void setPeriodo_academico(String periodo_academico) {
        this.periodo_academico = periodo_academico;
    }

    /**
     * @return the campo
     */
    public String getCampo() {
        return campo;
    }

    /**
     * @param campo the campo to set
     */
    public void setCampo(String campo) {
        this.campo = campo;
    }

    /**
     * @return the codigo_asignatura
     */
    public String getCodigo_asignatura() {
        return codigo_asignatura;
    }

    /**
     * @param codigo_asignatura the codigo_asignatura to set
     */
    public void setCodigo_asignatura(String codigo_asignatura) {
        this.codigo_asignatura = codigo_asignatura;
    }

    /**
     * @return the horas_semanales
     */
    public Integer getHoras_semanales() {
        return horas_semanales;
    }

    /**
     * @param horas_semanales the horas_semanales to set
     */
    public void setHoras_semanales(Integer horas_semanales) {
        this.horas_semanales = horas_semanales;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the ayuda
     */
    public String getAyuda() {
        return ayuda;
    }

    /**
     * @param ayuda the ayuda to set
     */
    public void setAyuda(String ayuda) {
        this.ayuda = ayuda;
    }

    /**
     * @return the numero_creditos
     */
    public Double getNumero_creditos() {
        return numero_creditos;
    }

    /**
     * @param numero_creditos the numero_creditos to set
     */
    public void setNumero_creditos(Double numero_creditos) {
        this.numero_creditos = numero_creditos;
    }

    /**
     * @return the codigoCampo
     */
    public String getCodigoCampo() {
        return codigoCampo;
    }

    /**
     * @param codigoCampo the codigoCampo to set
     */
    public void setCodigoCampo(String codigoCampo) {
        this.codigoCampo = codigoCampo;
    }

    /**
     * @return the vigencia
     */
    public String getVigencia() {
        return vigencia;
    }

    /**
     * @param vigencia the vigencia to set
     */
    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

}
