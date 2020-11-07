/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidad.recursos.comunes;

//import com.silabo.clases.ClObservacion;
import dda.silabo.observaciones.comunes.Observacion;
import dda.silabo.observaciones.comunes.Observaciones;
import dda.silabo.silabo.comunes.Silabo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Recursos {

    private List<Recurso> recursos = new ArrayList<>();
    private Observaciones observacion = new Observaciones();
    private List<Observacion> observaciones = new ArrayList<>();
    private String ayuda;
    private String titulo;
    private Silabo silabos = null;

    public void instanciar() {
        this.observaciones = new ArrayList<>();
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

    public void addRecurso(Recurso recurso) {
        if (recurso != null) {
            this.getRecursos().add(recurso);
        }
    }

    /**
     * @return the recursos
     */
    public List<Recurso> getRecursos() {
        return recursos;
    }

    /**
     * @param recursos the recursos to set
     */
    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
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
     * @return the silabo
     */
    public Silabo getSilabos() {
        return silabos;
    }

    /**
     * @param silabo the silabo to set
     */
    public void setSilabos(Silabo silabo) {
        this.silabos = silabo;
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
