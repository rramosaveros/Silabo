/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.bibliografias.comunes;

/**
 *
 * @author Jorge Zaruma
 */
public class BibliografiaSitioWeb {

    private Integer id;
    private String autor;
    private String nombreSitio;
    private Integer anio;
    private String mes;
    private Integer dia;
    private String url;
    private String disabled;

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
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the nombreSitio
     */
    public String getNombreSitio() {
        return nombreSitio;
    }

    /**
     * @param nombreSitio the nombreSitio to set
     */
    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    /**
     * @return the anio
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the dia
     */
    public Integer getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(Integer dia) {
        this.dia = dia;
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
     * @return the disabled
     */
    public String getDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
}
