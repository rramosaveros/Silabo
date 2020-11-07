/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.silabo.ln;

import dda.silabo.sw.EstructuraDesarrolloAD;

/**
 *
 * @author Jorge Zaruma
 */
public class SilaboLN {

    public EstructuraDesarrolloAD silaboContenidoAsignatura(java.lang.String codCarrera, java.lang.String codPeriodo, java.lang.String codMateria) {
        dda.silabo.sw.SilaboSWPublico_Service service = new dda.silabo.sw.SilaboSWPublico_Service();
        dda.silabo.sw.SilaboSWPublico port = service.getSilaboSWPublicoPort();
        return port.silaboContenidoAsignatura(codCarrera, codPeriodo, codMateria);
    }

}
