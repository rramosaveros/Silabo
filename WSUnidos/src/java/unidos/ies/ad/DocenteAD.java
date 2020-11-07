/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.ies.ad;

import unidos.comunes.Docente;
import unidos.comunes.DocenteTitulos;
import unidos.importacion.dtic.Titulos;
import unidos.importacion.dtic.UsuarioDTIC;

/**
 *
 * @author Jorge Zaruma
 */
public class DocenteAD extends Docente {

    public void DocenteInformacion(UsuarioDTIC usuarioDTIC, Titulos[] tituloses) {
        try {
            this.setNombres(usuarioDTIC.getPerNombres());
            this.setApellidos(usuarioDTIC.getPerPrimerapellido() + " " + usuarioDTIC.getPerSegundoapellido());
            this.setCedula(usuarioDTIC.getPerCedula());
            this.setCorreo(usuarioDTIC.getPerEmail());
            this.setTelefono(usuarioDTIC.getPerTelefonocasa());
            this.setCelular(usuarioDTIC.getPerTelefonocelular());
            this.setCorreoAlternativo(usuarioDTIC.getPerEmailalternativo());
            for (Titulos titulos : tituloses) {
                DocenteTitulos docenteTitulos = new DocenteTitulos();
                if (titulos.getTacId().getTitId().getNaaId().getNaaId() == 4) {
                    docenteTitulos.setDescripcion(titulos.getTacId().getTitId().getTitNombre());
                    docenteTitulos.setNivel(titulos.getTacId().getTitId().getNaaId().getNaaId());
                    this.getCuartoNivel().add(docenteTitulos);
                } else if (titulos.getTacId().getTitId().getNaaId().getNaaId() == 3) {
                    docenteTitulos.setDescripcion(titulos.getTacId().getTitId().getTitNombre());
                    docenteTitulos.setNivel(titulos.getTacId().getTitId().getNaaId().getNaaId());
                    this.getTercerNivel().add(docenteTitulos);
                }
            }
        } catch (Exception e) {
            this.setNombres("Desconocido");
            this.setApellidos("Desconocido");
            this.setCedula("0000000000");
            this.setCorreo("usuario@hotmail.com");
            this.setTelefono("(022)432-432");
            this.setCelular("09999921345");
            this.setCorreoAlternativo("usuario2@hotmail.com");
        }
    }

}
