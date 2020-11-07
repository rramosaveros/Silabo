/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.importacion.dtic;

/**
 *
 * @author Jorge Zaruma
 */
public class titId {

    private String titNombre;
    private naaId naaId = new naaId();

    /**
     * @return the titNombre
     */
    public String getTitNombre() {
        return titNombre;
    }

    /**
     * @param titNombre the titNombre to set
     */
    public void setTitNombre(String titNombre) {
        this.titNombre = titNombre;
    }

    /**
     * @return the naaId
     */
    public naaId getNaaId() {
        return naaId;
    }

    /**
     * @param naaId the naaId to set
     */
    public void setNaaId(naaId naaId) {
        this.naaId = naaId;
    }
}
