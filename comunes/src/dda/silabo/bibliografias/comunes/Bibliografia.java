/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.bibliografias.comunes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TOSHIBA
 */
public class Bibliografia {

    private Integer id_seccion;
    private Integer id_bibliografia;
    private String descripcion;
    private String tipo;
    private String url;
    private List<BibliografiaLibro> libros = new ArrayList<>();
    private List<BibliografiaSitioWeb> sitios = new ArrayList<>();

    /**
     * @return the id_bibliografia
     */
    public Integer getId_bibliografia() {
        return id_bibliografia;
    }

    /**
     * @param id_bibliografia the id_bibliografia to set
     */
    public void setId_bibliografia(Integer id_bibliografia) {
        this.id_bibliografia = id_bibliografia;
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
     * @return the id_seccion
     */
    public Integer getId_seccion() {
        return id_seccion;
    }

    /**
     * @param id_seccion the id_seccion to set
     */
    public void setId_seccion(Integer id_seccion) {
        this.id_seccion = id_seccion;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the libros
     */
    public List<BibliografiaLibro> getLibros() {
        return libros;
    }

    /**
     * @param libros the libros to set
     */
    public void setLibros(List<BibliografiaLibro> libros) {
        this.libros = libros;
    }

    /**
     * @return the sitios
     */
    public List<BibliografiaSitioWeb> getSitios() {
        return sitios;
    }

    /**
     * @param sitios the sitios to set
     */
    public void setSitios(List<BibliografiaSitioWeb> sitios) {
        this.sitios = sitios;
    }

}
