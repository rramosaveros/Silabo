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
public class Titulos {

    private String ifoRegistro;
    private tacId tacId = new tacId();

    /**
     * @return the ifoRegistro
     */
    public String getIfoRegistro() {
        return ifoRegistro;
    }

    /**
     * @param ifoRegistro the ifoRegistro to set
     */
    public void setIfoRegistro(String ifoRegistro) {
        this.ifoRegistro = ifoRegistro;
    }

    /**
     * @return the tacId
     */
    public tacId getTacId() {
        return tacId;
    }

    /**
     * @param tacId the tacId to set
     */
    public void setTacId(tacId tacId) {
        this.tacId = tacId;
    }
}
