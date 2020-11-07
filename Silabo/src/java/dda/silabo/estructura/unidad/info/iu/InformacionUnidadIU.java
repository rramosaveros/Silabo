/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.estructura.unidad.info.iu;

import dda.silabo.escenarios.comunes.Escenario;
import dda.silabo.estructura.unidadinformacion.comunes.EstructuraDesarrollo;
import dda.silabo.estructura.unidadinformacion.comunes.Subtemas;
import dda.silabo.estructura.unidadinformacion.comunes.Unidades;
import dda.silabo.estructura.unidadinformacion.comunes.Temas;
import java.util.List;

/**
 *
 * @author Adry
 */
public class InformacionUnidadIU extends EstructuraDesarrollo {

    public String toHTML() {

        String result = "";
        List<Unidades> unidades = this.getUnidad();
        if (unidades.size() > 0) {
            result += "<form>"
                    + " <div class='form-group row'>"
                    + " <div id='seccionContenido' class='col-xs-12'>";
            int contTemas = 0, contSubtemas = 0;
            for (int i = 0; i < unidades.size(); i++) {
                int numUnidad = 0;
                String idUnidad = "0";
                numUnidad = unidades.get(i).getNumUnidad();
                idUnidad = unidades.get(i).getIdUnidad();
                result += " <div class='unidad' id='contenidoUnidad'>"
                        + "<div class='input-group'>"
                        + "<span class='input-group-addon'>Titulo de Unidad: </span>"
                        + "<input type='text' readonly class='form-control tituloUnidad' name='" + numUnidad + "' id='" + idUnidad + "' placeholder='Título de la unidad' value='" + unidades.get(i).getTitulo() + "'/>";
                result += "<span class='input-group-addon' onclick='agregarTema(this)' data-toggle='tooltip' data-placement='bottom' title='Agregar Tema' id=''>"
                        + "<i class='fa fa-plus-circle'></i>"
                        + "</span>"
                        + "       </div>";
                List<Temas> temas = unidades.get(i).getTemas();
                if (temas.size() > 0) {
                    for (int j = 0; j < temas.size(); j++) {
                        contTemas++;
                        String readonly = "";
                        String sistema = "S";
                        if (!temas.get(j).getSistema().equals("S")) {
                            readonly = "readonly";
                            sistema = "A";
                        }
                        result += "<div class='tema' id='t" + contTemas + "'>"
                                + "<input type='hidden' class='sistema' value='" + sistema + "'>"
                                + "<div class='input-group'>"
                                + "<span class='input-group-addon'>"
                                + "Tema " + (j + 1) + " : </span>";
                        result += "<input type='text' " + readonly + " class='form-control descTema' onkeypress='return soloLetras(event);' placeholder='Título del tema' value='" + temas.get(j).getDescripcion() + "'>"
                                + "<span class='input-group-addon idTema' onclick='agregarSubtema(this)' data-toggle='tooltip' data-placement='bottom' title='Agregar Subtema' id='" + temas.get(j).getId_temas() + "'>"
                                + "<i class='fa fa-plus-circle'></i>"
                                + "</span>"
                                + "<span class='input-group-addon' onclick='deleteTema(this)' data-toggle='tooltip' data-placement='bottom' title='Eliminar Tema' id='" + temas.get(j).getId_temas() + "'>"
                                + "<i class='fa fa-minus-circle'> </i>"
                                + "</span>";
                        result += "  </div>";

                        List<Subtemas> subtemas = temas.get(j).getSubtemas();
                        if (subtemas.size() > 0) {
                            for (int st = 0; st < subtemas.size(); st++) {
                                contSubtemas++;
                                readonly = "";
                                sistema = "S";
                                if (!subtemas.get(st).getSistema().equals("S")) {
                                    readonly = "readonly";
                                    sistema = "A";
                                }

                                result += "<div class='subtema' id='st" + contSubtemas + "'>"
                                        + "<input type='hidden' class='sistema' value='" + sistema + "'>"
                                        + "<div class='input-group'>"
                                        + "<span class='input-group-addon'>"
                                        + "Subtema " + (st + 1) + ": </span>";

                                result += "<input type='text' " + readonly + " class='form-control descSubtema' onkeypress='return soloLetras(event);' placeholder='Título del subtema' value='" + subtemas.get(st).getDescripcion() + "'>"
                                        + "<span class='input-group-addon idSubtema' onclick='deleteSubtema(this)'  data-toggle='tooltip' data-placement='bottom' title='Eliminar Subtema' id='" + subtemas.get(st).getId_temas_subtemas() + "'>"
                                        + "<i class='fa fa-minus-circle'> </i>"
                                        + "</span>";
                                result += " </div>"
                                        + " </div>";

                            }
                        }
                        result += " </div>";
                    }
                }
                result += " </div>";
            }
            result += "<div id='contenidoObservaciones'></div>";
            result += "<input type='hidden' id='contTemas' value='" + contTemas + "'>";
            result += "<input type='hidden' id='contSubtemas' value='" + contSubtemas + "'>";
            result += "<input type='hidden' id='idUnidad' value='" + this.getSilabos().getIdUnidad() + "'";

            result += " </div>"
                    + " </div>"
                    + " </form>";

        }
        return result;
    }

    public String toHTMLImportacion() {
        String result = "";
        result += "<div id='accordion'>";
        for (Unidades u : this.getUnidad()) {
            result += "<div class='card'>"
                    + "<div class='card-header' id='headingOne' onclick='seleccionarUnidad(this)'>"
                    + "  <h5 class='mb-0' style='float: left; width: 90%;'>"
                    + "    <button class='btn btn-link' data-toggle='collapse' data-target='#collapse" + u.getIdUnidad() + "' aria-expanded='true' aria-controls='collapseR'>"
                    + "      Unidad " + u.getNumUnidad() + ": " + u.getTitulo()
                    + "    </button>"
                    + "  </h5>"
                    + "<div style='float: right; width: 10%;' class='radio'><input type='radio' class='radiob' name='unidad' id='" + u.getNumUnidad() + "'></div>"
                    + "</div>"
                    + "<div id='collapse" + u.getIdUnidad() + "' class='collapse' aria-labelledby='headingOne' data-parent='#accordion'>"
                    + "<div class='card-body'>";
            result += "<ul>";
            for (Temas t : u.getTemas()) {
                result += "  <li><b>" + t.getDescripcion() + "</b>";
                result += "<ul>";
                for (Subtemas s : t.getSubtemas()) {
                    result += "  <li>" + s.getDescripcion() + "</li>";
                }
                result += "</ul>";
                result += "</li>";
            }
            result += "</ul>";
            result += " </div>"
                    + "</div>"
                    + " </div>";
        }
        result += "</div>";

        return result;
    }

    public String toHTMLModal() {
        String result = "";
        try {
            result += "<div style='margin-top:10%;' class='modal' id='modalgestionar' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel' aria-hidden='true'>"
                    + "            <div class='modal-dialog modal-lg'>"
                    + "              <div class='modal-content'>"
                    + "                <div class='modal-header'>"
                    + "                  <h5 style='text-align:center;' class='modal-title'><b>Gestionar Unidades</b></h5>"
                    + "                </div>"
                    + "                <div  class='modal-body'>"
                    + "<div class='col-xs-12' id='seccionContenido'>"
                    + "<div class='float-xs-right pr-0 unidad-nueva'>"
                    + "&nbsp;&nbsp;<button class='btn btn-primary' aria-expanded='false' aria-controls='collapseObjetivo' onclick='agregarNuevaUnidad();' type='button' href='#collapseObjetivo' data-toggle='collapse'>"
                    + "<i class='fa fa-plus-circle'></i>  Agregar Nueva Unidad"
                    + "</button>"
                    + "</div>"
                    + " <div class='float-xs-right pr-0 unidad-nueva'>"
                    + "                                            <button class='btn btn-primary' aria-expanded='false' aria-controls='collapseObjetivo' onclick='reestablecerUnidades();' type='button' href='#collapseObjetivo' data-toggle='collapse'>"
                    + "                                                <i class='fa fa-retweet'></i>   Reestablecer"
                    + "                                            </button>"
                    + "                                        </div>"
                    + "</div>"
                    + "<table multiple class='record_table table-hover' id='exampleSelect2' width='100%'>"
                    + "<tr style='text-align: center;'>"
                    + "<td><b>Titulo de la Unidad</b></td>"
                    + "<td><b>Número de Unidad</b></td>"
                    + "</tr>";
            for (Unidades u : this.getUnidad()) {
                result += "<tr class='informacion' id='" + u.getNumUnidad() + "'>"
                        //                        + "<td><input type='checkbox' id='" + recurso.getIdRecurso() + "' class='Recursos'" + estadoContenido + " value='" + recurso.getStrDescripcion() + "' " + recurso.getChv_check() + ">" + recurso.getStrDescripcion() + "</td>"
                        + "<td style='width: 80%;' class='titulo'>"
                        + "             <div class='input-group'>"
                        + "            <input type='text' class='form-control' id='" + u.getIdUnidad() + "' value='" + u.getTitulo() + "' data-value='" + u.getTitulo() + "' onkeyup='actualizarValor(this);' onkeypress='return soloLetras(event);' placeholder='Información unidad'>"
                        + "            </div>"
                        + "</td>"
                        + "<td style='width: 20%; text-align: center;' class='numero'>"
                        + " <div class='input-group'>"
                        + "<select class='form-control' onchange='cambianumeroUnidad(this);'>";
                for (int i = 1; i <= this.getUnidad().size(); i++) {
                    String selected = "";
                    if (i == u.getNumUnidad()) {
                        selected = "selected";
                    }
                    result += "<option value='" + i + "' " + selected + ">" + i + "</option>";
                }
                result += "</select>"
                        + "<span class='input-group-addon eliminar' title='Eliminar Unidad' onclick='EliminarUnidad(this);'><i class='fa fa-trash'></i></span>"
                        + "</div>"
                        + "</td>"
                        + "</tr>";
            }
            result += "</table>"
                    + "                </div>"
                    + "                <div class='modal-footer'>"
                    + "                <button id='btnGuardar' onclick=guardarUnidades();   class='btn btn-primary' title='Guardar cambios'>"
                    + "                          Guardar | <i class='fa fa-save'></i>"
                    + "                        </button>"
                    + "                  &nbsp;&nbsp;<button type='button' class='btn btn-primary' onclick='cerrarModalContenido();' data-dismiss='modal'>Cerrar</button>"
                    + "                </div>"
                    + "              </div>"
                    + "            </div>"
                    + "            </div>";
        } catch (Exception e) {
        }
        return result;
    }
}
