/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.datosdocentes.iu;

import dda.silabo.datosdocentes.comunes.DatoDocente;
import dda.silabo.datosdocentes.comunes.DatosDocentes;
import dda.silabo.datosdocentes.comunes.Titulos;
import java.util.List;

/**
 *
 * @author TOSHIBA
 */
public class DatosDocentesIU extends DatosDocentes {

    public List<DatoDocente> IngenierosNoAutenticados(List<DatoDocente> datosDocentesIU) {
        String result = "";
        for (DatoDocente dd : this.getDatosdocentes()) {
            DatoDocente dd2 = datosDocentesIU.stream().filter(dt -> dt.getCedula().equals(dd.getCedula())).findFirst().orElse(null);
            if (dd2 == null) {
                datosDocentesIU.add(dd);
            }
        }
        return datosDocentesIU;
    }

    public String toHTML(String cedula, String rolActivo, DatosDocentesIU docentesTotal) {
        String result = "";
        List<DatoDocente> totalDocentes = docentesTotal.IngenierosNoAutenticados(this.getDatosdocentes());
        for (DatoDocente datoDocente : totalDocentes) {
            String idselect1 = "", idselect2 = "", disabled = "disabled";
            String idTecer = "", idCuarto = "", idTelefono = "";
            if (datoDocente.getCedula().equals(cedula) || !rolActivo.equals("Doc")) {
                idselect1 = "tercerNivel";
                idselect2 = "cuartoNivel";
                disabled = "";
                idTecer = "tercerNivel2";
                idCuarto = "cuartoNivel2";
                idTelefono = "Telefono";
            }
            if (datoDocente.getTelefono() == null) {
                datoDocente.setTelefono("");
            }
            if (datoDocente.getCorreo() == null) {
                datoDocente.setCorreo("");
            }
            result += "<div class='form-group row'>"
                    + "<label for='example-text-input' class='col-xs-4 col-form-label'>Nombre del Profesor:</label>"
                    + "<div class='col-xs-8'>"
                    + "<input class='form-control' type='text' id='example-text-input' readonly value='" + datoDocente.getNombres() + " " + datoDocente.getApellidos() + "'>"
                    + "</div>   </div> "
                    + "<div class='form-group row'>"
                    + "<label for='example-text-input' class='col-xs-4 col-form-label'>N&uacute;mero Telef&oacute;nico:</label>"
                    + "<div class='col-xs-8'>"
                    + "<input class='form-control' onkeypress='soloNumeros(event);'  maxlength='10' type='text' " + disabled + " id='" + idTelefono + "' value='" + datoDocente.getTelefono() + "'>"
                    + "</div> </div>"
                    + "<div class='form-group row'>"
                    + "<label for='example-text-input' class='col-xs-4 col-form-label'>Correo Electr&oacute;nico:</label>"
                    + "<div class='col-xs-8'>"
                    + "<input class='form-control' type='text' readonly id='example-text-input' value='" + datoDocente.getCorreo() + "'>"
                    + "</div> </div>"
                    + "<div class='form-group row'>"
                    + "<label for='example-text-input' class='col-xs-4 col-form-label'>T&iacute;tulos Acad&eacute;micos de Tercer Nivel:</label>"
                    + "<div class='col-xs-8'>";
            Titulos tercernivel = datoDocente.getTercerNivel().stream().filter(dd -> dd.getSelected().equals("agregado")).findFirst().orElse(null);
            if (!datoDocente.getTercerNivel().isEmpty() && tercernivel == null) {
                result += "<select multiple class='selectpicker dda-select form-control' id='" + idselect1 + "' " + disabled + ">";
                for (Titulos titulos : datoDocente.getTercerNivel()) {
                    result += "<option data-subtext='(" + titulos.getNivel() + ")' value='" + titulos.getId() + "' " + titulos.getSelected() + ">" + titulos.getDescripcion() + "</option>";
                }
                result += "</select>";
            } else {
                String descripcion = "";
                Integer id = 0;
                if (tercernivel != null) {
                    descripcion = tercernivel.getDescripcion();
                    id = tercernivel.getId();
                }
                result += "<input class='form-control' " + disabled + " type='text' onkeypress='return soloLetras(event);' onkeyup='upperCaseTexto(this.id);' id='" + idTecer + "' data-id='" + id + "' value='" + descripcion + "'>";
            }
            result += " </div>   </div>"
                    + "<div class='form-group row'>"
                    + "<label for='example-text-input' class='col-xs-4 col-form-label'>T&iacute;tulos Acad&eacute;micos de Posgrado:</label>"
                    + "<div class='col-xs-8'>";
            Titulos cuartonivel = datoDocente.getCuartoNivel().stream().filter(dd -> dd.getSelected().equals("agregado")).findFirst().orElse(null);
            if (!datoDocente.getCuartoNivel().isEmpty() && cuartonivel == null) {
                result += "<select multiple class='selectpicker dda-select form-control' id='" + idselect2 + "' " + disabled + ">";
                for (Titulos titulos : datoDocente.getCuartoNivel()) {
                    result += "<option data-subtext='(" + titulos.getNivel() + ")' value='" + titulos.getId() + "' " + titulos.getSelected() + ">" + titulos.getDescripcion() + "</option>";
                }
                result += "</select>";
            } else {
                String descripcion = "";
                Integer id = 0;
                if (cuartonivel != null) {
                    descripcion = cuartonivel.getDescripcion();
                    id = cuartonivel.getId();
                }
                result += "<input class='form-control' " + disabled + " type='text' onkeypress='return soloLetras(event);' onkeyup='upperCaseTexto(this.id);' data-id='" + id + "' id='" + idCuarto + "' value='" + descripcion + "'>";
            }

            result += " </div>   </div>";
            if (datoDocente.getCedula().equals(cedula)) {
                if (datoDocente.getTercerNivel().isEmpty() || tercernivel != null || cuartonivel != null) {
                    result += "Acceda al <a target='_blank' href='https://seguridad.espoch.edu.ec/cas/login?service=https%3A%2F%2Fhojavida.espoch.edu.ec%3A8181%2FhojavidaIU%2F&renew=true' > SISTEMA DE HOJA DE VIDA</a> para agregar los títulos académicos registrados en el senescyt";
                }
                result += "<div class='form-group row'>"
                        + "    <div class='col-xs-9'>"
                        + "    </div>"
                        + "    <div class='col-xs-3'>"
                        + "        <button type='button' id='btnGuardar' class='btn btn-primary float-xs-right' onclick='GuardarDocentes(" + datoDocente.getId() + ")' data-toggle='tooltip' data-placement='top' title='Guardar Cambios'>"
                        + "            Guardar |  <i class='fa fa-save'></i>"
                        + "        </button>"
                        + "    </div>"
                        + "</div>";

            }
            result += "<hr/>";
        }
        return result;
    }

    public String nombresDtoHTML() {
        String result = "";
        result = this.getDatosdocentes().stream().map((docente) -> "Ing." + docente.getNombres() + " " + docente.getApellidos() + "<br>").reduce(result, String::concat);
        result += "";
        return result;
    }
}
