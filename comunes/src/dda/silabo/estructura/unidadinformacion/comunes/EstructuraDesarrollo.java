/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidadinformacion.comunes;

import dda.silabo.observaciones.comunes.Observacion;
import dda.silabo.observaciones.comunes.Observaciones;
import dda.silabo.silabo.comunes.Silabo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Silvia Macas
 */
public class EstructuraDesarrollo {

    private List<Unidades> unidades = new ArrayList();
    private Observaciones observacion = new Observaciones();
    private List<Observacion> observaciones = new ArrayList<>();
    private Silabo silabos = null;
    private String Ayuda;
    private String Titulo;
    private String codPrograma;
    private String desc;
    private String UnidadSistema;

    public void addDatosUnidad(Unidades unidad) {
        if (unidad != null) {
            this.getUnidad().add(unidad);
        }
    }

    /**
     * @return the Unidad
     */
    public List<Unidades> getUnidad() {
        return unidades;
    }

    /**
     * @param Unidad the Unidad to set
     */
    public void setUnidad(List<Unidades> Unidad) {
        this.unidades = Unidad;
    }

    /**
     * @return the Ayuda
     */
    public String getAyuda() {
        return Ayuda;
    }

    /**
     * @param Ayuda the Ayuda to set
     */
    public void setAyuda(String Ayuda) {
        this.Ayuda = Ayuda;
    }

    /**
     * @return the Titulo
     */
    public String getTitulo() {
        return Titulo;
    }

    /**
     * @param Titulo the Titulo to set
     */
    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    /**
     * @return the codPrograma
     */
    public String getCodPrograma() {
        return codPrograma;
    }

    /**
     * @param codPrograma the codPrograma to set
     */
    public void setCodPrograma(String codPrograma) {
        this.codPrograma = codPrograma;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the observaciones
     */
    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the silabos
     */
    public Silabo getSilabos() {
        return silabos;
    }

    /**
     * @param silabos the silabos to set
     */
    public void setSilabos(Silabo silabos) {
        this.silabos = silabos;
    }

    /**
     * @return the UnidadSistema
     */
    public String getUnidadSistema() {
        return UnidadSistema;
    }

    /**
     * @param UnidadSistema the UnidadSistema to set
     */
    public void setUnidadSistema(String UnidadSistema) {
        this.UnidadSistema = UnidadSistema;
    }

    /**
     * @return the observacion
     */
    public Observaciones getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(Observaciones observacion) {
        this.observacion = observacion;
    }
}
