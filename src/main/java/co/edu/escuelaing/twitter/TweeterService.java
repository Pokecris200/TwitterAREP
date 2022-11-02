/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.escuelaing.twitter;

import java.util.ArrayList;
import java.util.List;
import static spark.Spark.*;

/**
 *
 * @author cristian.forero-m
 */
public class TweeterService {
    
    //Base de datos en memoria que guarda los JSON de los tweets
    private static List<String> DB = new ArrayList<>();
    
    public static void main(String[] args) {
        port(getPort());
        
        //Servicio de Publicación
        post("/tweet", (req, res) ->{
            String response = "{"
                + "\"user\":\""+ req.queryParams("user") +"\","
                + "\"tweet\":\"" + req.queryParams("message") + "\""
                + "}";
            return response;
        });
        //Servicio de Stream
        get("/stream",(req,res)->{
            String s = "FEED PRINCIPAL" + "</br>"
                    + "hola gfcvbnlkñdsfjmlkjdsngfkjnbrdgnjlkanglkjbdkvd " + "\n"
                    + "ihsdbvinsdovmdoklfg";
            return s;
        });
    }
    
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8081;
    }
}
