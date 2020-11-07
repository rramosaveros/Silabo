/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.pensum;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import ec.edu.espoch.academico.ArrayOfMateriaPensum;
import ec.edu.espoch.academico.ArrayOfMateriaRequisito;
import ec.edu.espoch.academico.MateriaPensum;
import redis.clients.jedis.Jedis;
import unidos.jedis.Global;

/**
 *
 * @author Jorge Zaruma
 */
public class PensumLN {

    Global global = new Global();
    Jedis jedis = global.conexion();
    Gson G = new Gson();

    public ArrayOfMateriaPensum PensumValido(String strCodCarrera) {
        ArrayOfMateriaPensum result = null;
        try {
            String pensum = jedis.get("Pensum" + strCodCarrera);
            if (pensum == null) {
                try {
                    result = getMallaCurricularPensumVigenteSinDescripcion(strCodCarrera);
                    jedis.set("Pensum" + strCodCarrera, G.toJson(result));
                    jedis.expire("Pensum" + strCodCarrera, 1440);
                } catch (Exception e) {
                    result = new ArrayOfMateriaPensum();
                    MateriaPensum materiaPensum = new MateriaPensum();
                    materiaPensum.setArea("No definido");
                    materiaPensum.setCodArea("No definido");
                    materiaPensum.setCodMateria("No definido");
                    materiaPensum.setCodNivel("No definido");
                    materiaPensum.setCreditos(00);
                    materiaPensum.setHorasPracticas(0);
                    materiaPensum.setHorasTeoricas(0);
                    materiaPensum.setMateria("No definido");
                    materiaPensum.setNivel("No definido");
                    ArrayOfMateriaRequisito arrayOfMateriaRequisito = new ArrayOfMateriaRequisito();
                    materiaPensum.setRequisitos(arrayOfMateriaRequisito);
                    result.getMateriaPensum().add(materiaPensum);
                }

            } else {
                result = G.fromJson(pensum, ArrayOfMateriaPensum.class); // deserializar. convierte el String serializado a la clase que corresponda
            }
        } catch (JsonSyntaxException e) {
        }
        return result;
    }

    private static ArrayOfMateriaPensum getMallaCurricularPensumVigenteSinDescripcion(java.lang.String strCodCarrera) {
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getMallaCurricularPensumVigenteSinDescripcion(strCodCarrera);
    }

}
