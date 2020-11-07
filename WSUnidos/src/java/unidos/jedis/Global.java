/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidos.jedis;

import redis.clients.jedis.Jedis;

/**
 *
 * @author Adry Qp
 */
public class Global {

//    public static String ipRedis = "localhost"; // direccion ip para el servidor redis
//    public static String ipRedis = "172.17.102.143"; // direccion ip para el servidor redis
    public static String ipRedis = "192.168.0.117"; // direccion ip para el servidor redis
    public static Integer portRedis = 6379; // puerto del servidor 
    public static String pass = "fieespochredis"; //contraseña para el servidor asignada anteriormente

//    public static String ipRedis = "localhost"; // direccion ip para el servidor redis
//    public static Integer portRedis = 6379; // puerto del servidor 
    public Jedis conexion() {
        Jedis jedis = null;
        try {
            jedis = new Jedis(ipRedis, portRedis);
            jedis.auth(pass);
        } catch (Exception e) {
        }
        return jedis;
    }
}
