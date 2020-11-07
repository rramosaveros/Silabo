/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.usuarios.comunes;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Evelyn
 */
public class Usuarios {

    private String codCarrera;
    private List<Usuario> docentes = new ArrayList<>();
    private String titulo;

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return docentes;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.docentes = usuarios;
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
     * @return the codCarrera
     */
    public String getCodCarrera() {
        return codCarrera;
    }

    /**
     * @param codCarrera the codCarrera to set
     */
    public void setCodCarrera(String codCarrera) {
        this.codCarrera = codCarrera;
    }

    public void setDocentes(ConcurrentHashMap<String, Usuario> usuarios) {
        try {
            Enumeration<String> cedulas = usuarios.keys();
            String cedula;
            while (cedulas.hasMoreElements()) {
                cedula = (String) cedulas.nextElement();
                Usuario docente = usuarios.get(cedula);
                this.docentes.add(docente);
            }
        } catch (Exception e) {
            Logger.getLogger("Asignatura").log(Level.SEVERE, "Asignatura|setDocentes: ".concat(e.getMessage()));
        }
    }

}
