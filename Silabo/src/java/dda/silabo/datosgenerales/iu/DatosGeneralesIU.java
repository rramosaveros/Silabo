/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.datosgenerales.iu;

import dda.silabo.datosgenerales.comunes.DatosGenerales;

/**
 *
 * @author Jorge
 */
public class DatosGeneralesIU extends DatosGenerales {

    public String toHTML() {
        String result = "";
        String nombreMateria = "SÍLABO";
        String nombreNivel = "SEMESTRE";
        String nombreCreditos = "NÚMERO DE CRÉDITOS";
        String nombreHoras = "NÚMERO DE HORAS SEMANAL";
        if (this.getVigencia() != null) {
            if (this.getVigencia().equals("vigente")) {
                nombreMateria = "ASIGNATURA";
                nombreNivel = "NIVEL";
                nombreCreditos = "TOTAL HORAS";
                nombreHoras = "NÚMERO DE HORAS SEMANAL";
            }
        }
        result += " <div class='form-group row'>"
                + "<div class='col-xs-12'>"
                + "<table class='table-bordered table-hover' width='100%'>"
                + " <tr>"
                + "<td><b>FACULTAD</b></td>"
                + "<td colspan='2'>" + this.getNombre_facultad() + "</td>"
                + "</tr>"
                + "<tr>"
                + "  <td><b>ESCUELA</b></td>"
                + "<td colspan='2'>" + this.getNombre_escuela() + "</td>"
                + "</tr>"
                + "<tr>"
                + "  <td><b>CARRERA</b></td>"
                + "<td colspan='2'>" + this.getNombre_carrera() + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td><b>SEDE</b></td>"
                + "<td colspan='2'>" + this.getNombre_sede() + "</td>"
                + "</tr>"
                + " <tr>"
                + "   <td><b>MODALIDAD</b></td>"
                + " <td colspan='2'>" + this.getModalidad() + "</td>"
                + "</tr>"
                + " <tr>"
                + "<td><b>" + nombreMateria + "</b></td>"
                + " <td colspan='2'>" + this.getSilabo_materia() + "</td>"
                + " </tr>"
                + "<tr>"
                + "  <td><b>" + nombreNivel + "</b></td>"
                + "     <td colspan='2'>" + this.getNivel() + "</td>"
                + "     </tr>"
                + "   <tr>"
                + "       <td><b>PERÍODO ACADÉMICO</b></td>"
                + "       <td colspan='2'>" + this.getPeriodo_academico() + "</td>"
                + "   </tr>"
                + "   <tr>"
                + "       <td style='text-align:center;'><b>CAMPO DE FORMACIÓN</b></td>"
                + "       <td style='text-align:center;'><b>CÓDIGO</b></td>"
                + "       <td style='text-align:center;'><b>" + nombreCreditos + "</b></td>"
                + "           </tr>"
                + "           <tr>"
                + "               <td style='text-align:center;'> " + this.getCampo() + "</td>"
                + "               <td style='text-align:center;'>" + this.getCodigo_asignatura() + "</td>"
                + "               <td style='text-align:center;'>" + Math.round(this.getNumero_creditos()) + "</td>"
                + "           </tr>"
                + "           <tr>"
                + "               <td style='text-align:center;'><b>" + nombreHoras + "</b></td>"
                + "               <td style='text-align:center;'><b>PRERREQUISITOS</b></td>"
                + "               <td style='text-align:center;'><b>CORREQUSITOS</b></td>"
                + "           </tr>"
                + "           <tr>"
                + "               <td style='text-align:center;'>" + this.getHoras_semanales() + "</td>"
                + "               <td style='text-align:center;'>"
                + this.getPrerequisitos()
                + "</td>"
                + "<td style='text-align:center;' >"
                + this.getCorrequisitos();
        result += "</td>"
                + " </tr>"
                + " </table></div></div>";
        return result;
    }
}
