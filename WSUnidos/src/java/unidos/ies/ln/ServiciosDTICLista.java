/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.ies.ln;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import unidos.importacion.dtic.UsuarioDTIC;

/**
 *
 * @author Jorge Zaruma
 */
public class ServiciosDTICLista {

    String getJsonConsulta(String urle) {
        String result = null;
        try {
            String strJson = "";
            //SERVICIO
            URL url = new URL(urle);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            InputStream content = (InputStream) connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(content, "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                strJson = line;
            }
            result = strJson;
            connection.disconnect();
        } catch (Exception e) {
        }
        return result;
    }

}
