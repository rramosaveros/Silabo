/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jar;

import ec.edu.espoch.academico.ArrayOfMateriaPensum;
import javax.xml.ws.Holder;
import redis.clients.jedis.Jedis;

/**
 *
 * @author Jorge Zaruma
 */
public class ConexionRedis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String result = "result";

        try {
//            Holder<String> p = new Holder<>();    //Pensum
//            Holder<ArrayOfMateriaPensum> m = new Holder<>();      //Malla
//            ArrayOfMateriaPensum AP = getMallaCurricularPensumVigenteSinDescripcion("EIS");
//            getMallaCurricularPensumVigente("EIS", p, m);
//            Integer nMaterias = AP.getMateriaPensum().size();
//            Jedis jedis = new Jedis("172.17.102.143", 6379);
//            jedis.auth("redisespoch2018");
            Jedis jedis = new Jedis("192.168.0.117", 6379);
            jedis.auth("fieespochredis");
            result = jedis.ping();
        } catch (Exception e) {
            result = "catch";
        }
        System.out.println("Estado de conexion redis " + result);
    }

    private static void getMallaCurricularPensumVigente(java.lang.String strCodCarrera, javax.xml.ws.Holder<java.lang.String> pensum, javax.xml.ws.Holder<ec.edu.espoch.academico.ArrayOfMateriaPensum> getMallaCurricularPensumVigenteResult) {
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        port.getMallaCurricularPensumVigente(strCodCarrera, pensum, getMallaCurricularPensumVigenteResult);
    }

    private static ArrayOfMateriaPensum getMallaCurricularPensumVigenteSinDescripcion(java.lang.String strCodCarrera) {
        ec.edu.espoch.academico.InfoCarrera service = new ec.edu.espoch.academico.InfoCarrera();
        ec.edu.espoch.academico.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getMallaCurricularPensumVigenteSinDescripcion(strCodCarrera);
    }

}
