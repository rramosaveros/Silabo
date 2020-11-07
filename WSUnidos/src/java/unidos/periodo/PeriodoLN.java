/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.periodo;

import ec.edu.espoch.academico.ArrayOfPeriodo;
import ec.edu.espoch.academico.Periodo;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Jorge Zaruma
 */
public class PeriodoLN {

    public Periodo PeriodoValido() {
        Periodo result = null;
        try {
            result = getPeriodoActual();
            if (result == null) {
                ArrayOfPeriodo arrayOfPeriodo = getPeriodosAcademicos();
                List<Periodo> periodos = arrayOfPeriodo.getPeriodo();
                List<Periodo> periodosFiltrados = periodos.stream().filter(p -> (p.getCodigo().substring(0, 1).equals("P"))).collect(Collectors.toList());
                int size = periodosFiltrados.size();
                if (size > 0) {
                    result = periodosFiltrados.get(0);
                }
            }
        } catch (Exception e) {
            result = new Periodo();
        }
        return result;
    }

    private static Periodo getPeriodoActual() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getPeriodoActual();
    }

    private static ArrayOfPeriodo getPeriodosAcademicos() {
        ec.edu.espoch.academico.InfoGeneral service = new ec.edu.espoch.academico.InfoGeneral();
        ec.edu.espoch.academico.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getPeriodosAcademicos();
    }

}
