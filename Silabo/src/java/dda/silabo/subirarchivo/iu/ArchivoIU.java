/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.subirarchivo.iu;

/**
 *
 * @author Jorge Zaruma
 */
public class ArchivoIU {

    public String modalArchivotoHTML(String estado) {
        String result = "";
        if (estado.equals("Corregido")) {
            estado = "Revisión";
        }
        result += "<div style='margin-top:10%;' class='modal fade' id='modaArchivos' tabindex='-1' role='dialog'>"
                + "  <div class='modal-dialog modal-dialog-centered'>"
                + " <div class='modal-content'>"
                + "   <div class='modal-header'>"
                + "      <h4 style='text-align: center;'>Subir Archivos</h4>"
                + "    </div>"
                + "          <div class='modal-body'>";
        if (estado.equals("Aprobado")) {
            result += "                  <div class='form-group'>"
                    + "                      <input type='file' name='archivosolicitud' id='archivosolicitud' accept='application/pdf' class='file filestyle form-control'>"
                    + "                  </div>"
                    + "                  <div id='mensajeSubir' style='text-align: center;'>"
                    + "                  </div>"
                    + "                  <div class='form-inline'>"
                    + "                    </div>";
        } else {
            result += "                  <div class='form-group' style='text-align:center;'>"
                    + "                      <p>Imposible Subir Archivo<br>El Sílabo no está aprobado</p>"
                    + "                  </div>"
                    + "                  <div class='form-inline'>"
                    + "                    </div>";
        }
        result += "            </div>"
                + "            <div class='modal-footer'>";
        if (estado.equals("Aprobado")) {
            result += "                <button id='btnGuardar' onclick=subirArchivos();   class='btn btn-primary' title='Guardar cambios'>"
                    + "                          Guardar | <i class='fa fa-save'></i>"
                    + "                        </button>";

        }
        result += "&nbsp;&nbsp;<button class='btn btn-primary' onclick='cerrarModalContenido();' type='button' data-dismiss='modal'>Cerrar</button>";

        result += "            </div>"
                + "        </div>"
                + "    </div>"
                + "</div>";
        return result;
    }

    public String modalImportartoHTML() {
        String result = "";
        result += "<div class='modal fade' id='Importarmodal' tabindex='-1' data-backdrop='static' role='dialog' aria-labelledby='myModalLabel'>"
                + "                                        <div class='modal-dialog modal-lg'>"
                + "                                            <div class='modal-content'>"
                + "                                                <div class='modal-header'>"
                + "                                                    <h4 class='modal-title' id='myModalLabel'>Importar S&iacute;labo de Asignatura</h4>"
                + "                                                </div>"
                + "                                                <div class='modal-body'>"
                + "                                                    <div id='contenidomodal' style='width: 100%;'>"
                + "                                                        <form class='form-inline' id='slcImportarPeriodos'>"
                + "                                                            <select class='selectpicker dda-select' id='modalPeriodos'>"
                + "                                                                <option data-icon='fa-refresh' data-subtext='(EIS)' selected=''> Periodos 1....</option>"
                + "                                                            </select>"
                + "                                                        </form>"
                + "                                                        <br/><br/>"
                + "<table class='table' width='100%'><tr><td width='50%'><b>Carreras</b></td><td width='50%'><b>Asignaturas</b></td></tr></table>"
                + "                                                        <div id='brrNavegacion' class='row'>"
                + "                                                            <form class='form-inline' id='slcImportar'>"
                + "                                                                <select class='selectpicker dda-select' id='modalCarreras'>"
                + "                                                                    <option data-icon='fa-refresh' data-subtext='(EIS)' selected=''> carrera 1....</option>"
                + "                                                                </select>"
                + "                                                                <select class='selectpicker dda-select' id='modalAsignaturas'>"
                + "                                                                    <option data-icon='fa-refresh' data-subtext='(EIS)' selected=''> Asignaturas 1....</option>"
                + "                                                                </select>"
                + "                                                            </form>"
                + "                                                        </div>"
                + "                                                        <br><br>"
                + "                                                        <div class='container col-xs-12' >"
                + ""
                + "                                                            <div id='contenidos' class='col-xs-12' >"
                + "                                                                <div class='card  bg-light mb-3 border-secondary mb-3'>"
                + "                                                                    <div class='card-header'  style='text-align: center; '>"
                + "                                                                        <h5 class='card-title'>Special title treatment</h5>"
                + "                                                                    </div>"
                + "                                                                    <div class='card-body'>"
                + "<input type='text' class='form-control'>"
                + "                                                                    </div>"
                + "                                                                </div>"
                + "                                                            </div>"
                //                + "                                                            <div class='card-footer text-muted' style='text-align: center;'>"
                //                + "                                                                <i id='gImportacion' onclick='guardarImportacion(); return false;' style='color: #00417f;' class='fa fa-arrow-circle-o-down fa-3x ' title='Importar Silabo' ></i>"
                //                + "                                                            </div>"
                + ""
                + "                                                        </div>"
                + ""
                + "                                                    </div>"
                + "                                                </div>"
                + "                                                <div class='modal-footer'>"
                + "   <button type='button' class='btn btn-secondary' id='importar'><b>Importar</b></button>"
                + "                                                    <button type='button' onclick='cerrarModalContenido();' class='btn btn-secondary' data-dismiss='modal'><b>Cerrar</b></button>"
                + "                                                </div>"
                + "                                            </div>"
                + "                                        </div>"
                + "                                    </div>";
        return result;
    }
}
