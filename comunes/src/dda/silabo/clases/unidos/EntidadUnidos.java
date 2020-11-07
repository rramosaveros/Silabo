/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.clases.unidos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Adry Qp
 */
public class EntidadUnidos {

    private String codigo;
    private String nombre;
    private String tipo;
    private List<FacultadUnidos> facultades = new ArrayList();
    private String id;
    private String desc;
    private Integer avance;
    private String fncClick;

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public EntidadUnidos() {
        this.setId("");
        this.setDesc("");
    }

    public EntidadUnidos(String id) {
        this.setId(id);
    }

    public EntidadUnidos(String id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the facultades
     */
    public List<FacultadUnidos> getFacultades() {
        return facultades;
    }

    /**
     * @param facultades the facultades to set
     */
    public void setFacultades(List<FacultadUnidos> facultades) {
        this.facultades = facultades;
    }

    public ConcurrentHashMap<String, FacultadUnidos> FacultadesToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de facultades
        for (FacultadUnidos facultad : facultades) {
            result.put(facultad.getCodFacultad(), facultad);
        }
        return result;
    }

    public void setFacultades(ConcurrentHashMap<String, FacultadUnidos> facultades) {
        try {
            this.facultades.clear();
            Enumeration<String> codFacultades = facultades.keys();
            String codFacultad;
            while (codFacultades.hasMoreElements()) {
                codFacultad = (String) codFacultades.nextElement();
                FacultadUnidos facultad = facultades.get(codFacultad);
                this.facultades.add(facultad);
            }
        } catch (Exception e) {
            Logger.getLogger("Ies").log(Level.SEVERE, "Ies|setFacultades".concat(e.getMessage()));
        }
    }

    public FacultadUnidos getFacultad(String codFacultad) {

        FacultadUnidos result = null;
        try {
            ConcurrentHashMap<String, FacultadUnidos> facultades = this.FacultadesToHashMap();
            result = facultades.get(codFacultad);
        } catch (Exception e) {
            Logger.getLogger("Ies").log(Level.SEVERE, "Ies|getFacultad".concat(e.getMessage()));
        }
        return result;
    }

    public static String XMLGregorianCalendarToString(XMLGregorianCalendar fechaCreacion) {
        String result = "";
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            result = dateFormat.format(fechaCreacion.toGregorianCalendar().getTime());
        } catch (Exception e) {
            Logger.getLogger("Facultad").log(Level.SEVERE, "Facultad|XMLGregorianCalendarToString: ".concat(e.getMessage()));
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        final EntidadUnidos other = (EntidadUnidos) obj;
        return Objects.equals(this.id, other.id);
    }

    /**
     * @return the avance
     */
    public Integer getAvance() {
        return avance;
    }

    /**
     * @param avance the avance to set
     */
    public void setAvance(Integer avance) {
        this.avance = avance;
    }

    /**
     * @return the fncClick
     */
    public String getFncClick() {
        return fncClick;
    }

    /**
     * @param fncClick the fncClick to set
     */
        public void setFncClick(String fncClick) {
        this.fncClick = fncClick;
    }
}
