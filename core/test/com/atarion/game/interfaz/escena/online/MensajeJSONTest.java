package com.atarion.game.interfaz.escena.online;


import java.util.HashSet;
import org.json.JSONObject;
import org.junit.Test;


public class MensajeJSONTest
{
    @Test
    public void testEscribirArray()
    {   
        HashSet<String> coleccion = new HashSet<>();
        coleccion.add("{ 'atributo' : 'Hola' }");
        coleccion.add("{ 'atributo' : 'Muy' }");
        coleccion.add("{ 'atributo' : 'Buenas' }");
        coleccion.add("{ 'atributo' : 'Adios' }");
        
        MensajeJSON mensaje = new MensajeJSON();
        mensaje.escribirArray("datos",coleccion,ParteMensaje.SINGULAR);
        
        System.out.println(mensaje.getMensaje().toString());
        
        JSONObject objeto = mensaje.getJson();
        objeto.getJSONArray("datos").forEach
        (
            dato ->
            {
                MensajeJSON elemento = new MensajeJSON().recibir(dato.toString());
                System.out.println(elemento.getJson().getString("atributo"));
            }
        );
    }    
}
