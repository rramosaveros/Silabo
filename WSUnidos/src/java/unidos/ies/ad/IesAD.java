/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.ies.ad;

import com.google.gson.Gson;
import ec.edu.espoch.academico.ArrayOfFacultad;
import ec.edu.espoch.academico.Facultad;
import java.util.List;
import java.util.stream.Collectors;
import unidos.comunes.Entidad;

/**
 *
 * @author Adry Qp
 */
public class IesAD extends Entidad {

    public void FacultadesLista() {
        try {
            ArrayOfFacultad arrayOfFacultad = getTodasFacultades();
            List<Facultad> facultades = arrayOfFacultad.getFacultad().stream().filter(fa -> fa.getCodEstado().equals("ABI")).collect(Collectors.toList());
            for (Facultad facultad1 : facultades) {
                unidos.comunes.Facultad facultad = new unidos.comunes.Facultad(facultad1);
                this.getFacultades().add(facultad);
            }
        } catch (Exception e) {
        }
    }

    private static ArrayOfFacultad getTodasFacultades() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getTodasFacultades();
    }

}
