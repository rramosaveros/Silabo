/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.reportes.iu;

import dda.silabo.reportes.comunes.DatosElemento;
import dda.silabo.reportes.comunes.Elemento;
import dda.silabo.reportes.comunes.ReporteGrafico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Zaruma
 */
public class ReporteGraficoIU extends ReporteGrafico {

    public void obtenerValoresDefectoGrafico() {
        this.setTitulo("Nombre Institución");
        this.setSubtitulo("Subtitulo");
        this.setAyuda("Graficos no Disponibles");
        List<Elemento> elementos = new ArrayList<>();
        Elemento elemento = new Elemento();
        elemento.setCodigo("0");
        elemento.setNombre("Entidad no Definida");
        DatosElemento datosElemento = new DatosElemento();
        datosElemento.setAprobado(0);
        datosElemento.setCorregir(0);
        datosElemento.setInicio(0);
        datosElemento.setRevision(0);
        elemento.setDatos(datosElemento);
        elementos.add(elemento);
        this.setElementos(elementos);
    }
}
