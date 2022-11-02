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
public class TwitterService {
    
    //Base de datos en memoria que guarda los JSON de los tweets
    private static List<String> DB = new ArrayList<>();
    
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        DB.add("{"
                + "\"user\":\""+ "anonym" +"\","
                + "\"tweet\":\"" + "Hola Mundo" + "\""
                + "}");
        
        //Servicio de Publicación
        post("/tweet", (req, res) ->{
            String response = "{"
                + "\"user\":\""+ req.queryParams("user") +"\","
                + "\"tweet\":\"" + req.queryParams("message") + "\""
                + "}";
            DB.add(response);
            return response;
        });
        //Servicio de Stream
        get("/stream",(req,res)->{
            String s = "FEED PRINCIPAL" + "</br></br>";
            for(String tweet : DB){
                String u = getUser(tweet);
                String m = getMessage(tweet);
                s += "User: " + u.replace("\"", "") + "</br>" ;
                s += m.replace("\"", "") + "</br></br>";
            }
            return s;
        });
    }
    
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8081;
    }

    private static String getUser(String tweet) {
        String user = tweet.replace("{", "").replace("}", "").split(",")[0];
        user = user.split(":")[1];
        return user;
    }
    private static String getMessage(String tweet) {
        String user = tweet.replace("{", "").replace("}", "").split(",")[1];
        user = user.split(":")[1];
        return user;
    }
}
