/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.db;

import redis.clients.jedis.Jedis;

/**
 *
 * @author
 */
public class Global {

    // condicciones de conexion
    public static String driverclass = "org.postgresql.Driver";
    /*el driver de la clase que ya esta en servicios*/

    //public static String databaseURL = "jdbc:postgresql://192.168.182.129:5432/ddaplanificacion"; /*copiar la base de datos url*/
//    public static String databaseURL = "jdbc:postgresql://10.0.0.133:5432/db_silabo2"; // la direccion de tu maquina centos 
    /*copiar la base de datos url*/
//    public static String databaseURL = "jdbc:postgresql://172.17.103.25:5445/silabo"; //DTIC Servidor Pruebas 
//    public static String databaseURL = "jdbc:postgresql://localhost:5432/silabotesis"; //Servidor preproducción
//    public static String usuarioDB = "uth2018"; //DTIC Servidor Pruebas
//    public static String usuarioDB = "postgres";
//    public static String usuarioDB = "postgres"; //Servidor preproducción
    /*usuario de la base de datos*/
//    public static String claveDB = "2018uth@@"; //fijate en las credenciales //DTIC Servidor Pruebas
//    public static String claveDB = "1234"; //fijate en las credenciales 
//    public static String claveDB = "1234567"; //Servidor preproducción
    /*contrasena de la base de datos*/

//    public static String ipRedis = "172.17.102.143"; // cambia a localhost //DTIC Servidor Pruebas
//    public static String ipRedis = "192.168.0.117"; // cambia a localhost //DTIC Servidor Pruebas
    
    /*postgres credenciales para servidores con docker*/
    public static String databaseURL = "jdbc:postgresql://192.168.0.119:5432/silabovf";
    public static String usuarioDB = "postgres";
    public static String claveDB = "123456";
//    
//    /*usuario de redis*/
    public static String ipRedis = "192.168.0.117"; // cambia a localhost 
    public static Integer portRedis = 6379;
    
    
//    public static String databaseURL = "jdbc:postgresql://localhost:5432/silaboss";
//    public static String usuarioDB = "postgres";
//    public static String claveDB = "1234";
    
    /*usuario de redis*/
//    public static String ipRedis = "localhost"; // cambia a localhost 
//    public static Integer portRedis = 6379;
    
    public Jedis conexion() {
        Jedis jedis = null;
        try {
            jedis = new Jedis(ipRedis, portRedis);
//            jedis.auth("redisespoch2018");//comenta esta linea 
            jedis.auth("fieespochredis");//pass docker 
        } catch (Exception e) {
        }
        return jedis;
    }

}
