/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidad.informacion;

import dda.silabo.estructura.unidadinformacion.comunes.Unidades;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Silvia Macas
 */
public class EstructuraDesarrollo {

    private List<Unidades> unidades = new ArrayList();
    private String Ayuda;
    private String Titulo;
    private String codPrograma;
    private String desc;

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

}
