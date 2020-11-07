/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.datosdocentes.comunes;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TOSHIBA
 */
public class DatosDocentes {

    private List<DatoDocente> datosdocentes = new ArrayList<>();
    private String ayuda;
    private String titulo;

    /**
     * @return the datosdocentes
     */
    public List<DatoDocente> getDatosdocentes() {
        return datosdocentes;
    }

    /**
     * @param datosdocentes the datosdocentes to set
     */
    public void setDatosdocentes(List<DatoDocente> datosdocentes) {
        this.datosdocentes = datosdocentes;
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

    public void addDatosDocentes(DatoDocente datodocente) {
        if (datodocente != null) {
            this.getDatosdocentes().add(datodocente);
        }
    }

    public void setDocentesHash(ConcurrentHashMap<String, DatoDocente> docentes) {
        try {
            this.datosdocentes.clear();
            Enumeration<String> cedulas = docentes.keys();
            String cedula;
            while (cedulas.hasMoreElements()) {
                cedula = (String) cedulas.nextElement();
                DatoDocente docente = docentes.get(cedula);
                this.datosdocentes.add(docente);
            }
        } catch (Exception e) {
            Logger.getLogger("Asignatura").log(Level.SEVERE, "Asignatura|setDocentes: ".concat(e.getMessage()));
        }
    }
}
