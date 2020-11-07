/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.archivos.comunes;

import java.sql.Date;

/**
 *
 * @author Dante
 */
public class ArchivoPublicacion {

    private Integer intIdArchivoPublicacion;
    private Integer intIdPublicacion;
    private String strTipoArchivo;
    private byte[] btyArchivo;
    private String strNombreArchivo;
    private Date dtFechaArchivo;

    public Integer getIntIdArchivoPublicacion() {
        return intIdArchivoPublicacion;
    }

    public void setIntIdArchivoPublicacion(Integer intIdArchivoPublicacion) {
        this.intIdArchivoPublicacion = intIdArchivoPublicacion;
    }

    public Integer getIntIdPublicacion() {
        return intIdPublicacion;
    }

    public void setIntIdPublicacion(Integer intIdPublicacion) {
        this.intIdPublicacion = intIdPublicacion;
    }

    public String getStrTipoArchivo() {
        return strTipoArchivo;
    }

    public void setStrTipoArchivo(String strTipoArchivo) {
        this.strTipoArchivo = strTipoArchivo;
    }

    public byte[] getBtyArchivo() {
        return btyArchivo;
    }

    public void setBtyArchivo(byte[] btyArchivo) {
        this.btyArchivo = btyArchivo;
    }

    public String getStrNombreArchivo() {
        return strNombreArchivo;
    }

    public void setStrNombreArchivo(String strNombreArchivo) {
        this.strNombreArchivo = strNombreArchivo;
    }

    public Date getDtFechaArchivo() {
        return dtFechaArchivo;
    }

    public void setDtFechaArchivo(Date dtFechaArchivo) {
        this.dtFechaArchivo = dtFechaArchivo;
    }

}
