package com.atarion.game.interfaz.escena.online;


import org.junit.Test;


public class MensajeJSONTest
{
    @Test
    public void testEscribirArray()
    {   
        MensajeJSON mensaje = new MensajeJSON();
        
        MensajeJSON mensaje2 = new MensajeJSON();
        mensaje2.escribirAtributo("id","Adios");
        
        MensajeJSON mensaje3 = new MensajeJSON();
        mensaje3.escribirAtributo("id","Buenas");
        
        mensaje.concatenar(mensaje2,"iniciales");
        mensaje.concatenar(mensaje3,"iniciales");
        
        System.out.println(mensaje.getMensaje());
        
        mensaje.getJson().getJSONArray("iniciales").forEach
        (
            entrada -> 
            { 
                MensajeJSON linea = new MensajeJSON().recibir(entrada.toString());
                System.out.println(linea.getJson().getString("id"));
            }
        );
    }    
}
