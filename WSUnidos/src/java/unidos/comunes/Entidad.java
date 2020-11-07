/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.comunes;

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
public class Entidad {

    private List<Facultad> facultades = new ArrayList();
    private String id;
    private String desc;

    public Entidad() {
        this.setId("");
        this.setDesc("");
    }

    public Entidad(String id) {
        this.setId(id);
    }

    public Entidad(String id, String desc) {
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
    public List<Facultad> getFacultades() {
        return facultades;
    }

    /**
     * @param facultades the facultades to set
     */
    public void setFacultades(List<Facultad> facultades) {
        this.facultades = facultades;
    }

    public ConcurrentHashMap<String, Facultad> FacultadesToHashMap() {
        ConcurrentHashMap result = new ConcurrentHashMap();
        // recorrer la lista de facultades
        for (Facultad facultad : facultades) {
            result.put(facultad.getCodFacultad(), facultad);
        }
        return result;
    }

    public void setFacultades(ConcurrentHashMap<String, Facultad> facultades) {
        try {
            this.facultades.clear();
            Enumeration<String> codFacultades = facultades.keys();
            String codFacultad;
            while (codFacultades.hasMoreElements()) {
                codFacultad = (String) codFacultades.nextElement();
                Facultad facultad = facultades.get(codFacultad);
                this.facultades.add(facultad);
            }
        } catch (Exception e) {
            Logger.getLogger("Ies").log(Level.SEVERE, "Ies|setFacultades".concat(e.getMessage()));
        }
    }

    public Facultad getFacultad(String codFacultad) {

        Facultad result = null;
        try {
            ConcurrentHashMap<String, Facultad> facultades = this.FacultadesToHashMap();
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
        final Entidad other = (Entidad) obj;
        return Objects.equals(this.id, other.id);
    }
}
