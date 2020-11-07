/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.ad;

import com.google.gson.Gson;
import dda.silabo.db.AccesoDatos;
import ec.edu.espoch.academico.ArrayOfMateriaPensum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jorge Zaruma
 */
public class TESTBASE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AccesoDatos ad = new AccesoDatos();
        //            if (ad.Connectar() != 0) {
//                for (int i = 8; i < 2000; i++) {
//                    ad.Connectar();
//                    String SQL = "INSERT INTO public.tbsilabo(\n"
//                            + "	id, estado)\n"
//                            + "	VALUES ('" + i + "','Aprobado');";
//                    PreparedStatement ps = ad.getCon().prepareStatement(SQL);
//                    ps.executeUpdate();
//                    ps.close();
//                    ad.getCon().commit();
//                }
//                String SQL = "SELECT id, estado\n"
//                        + "	FROM public.tbsilabo WHERE id>7 and id<2000;";
//                PreparedStatement ps = ad.getCon().prepareStatement(SQL);
//                ResultSet rs = ps.executeQuery();
//                while (true) {
//
//                }
//            }
        ArrayOfMateriaPensum aomp = pensumVigente("EIS");
        System.err.println(new Gson().toJson(aomp));
    }

    private static ArrayOfMateriaPensum pensumVigente(java.lang.String jsonCarrera) {
        unidos.sw.WsUnidosEspoch_Service service = new unidos.sw.WsUnidosEspoch_Service();
        unidos.sw.WsUnidosEspoch port = service.getWsUnidosEspochPort();
        return port.pensumVigente(jsonCarrera);
    }

}
