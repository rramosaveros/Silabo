/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.importacion.comunes;

import dda.silabo.clases.unidos.CarreraUnidos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class ImportarSilabo {

    private List<CarreraUnidos> carreras = new ArrayList<>();

    /**
     * @return the carreras
     */
    public List<CarreraUnidos> getCarreras() {
        return carreras;
    }

    /**
     * @param carreras the carreras to set
     */
    public void setCarreras(List<CarreraUnidos> carreras) {
        this.carreras = carreras;
    }

}
