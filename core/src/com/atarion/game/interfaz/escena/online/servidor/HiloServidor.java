package com.atarion.game.interfaz.escena.online.servidor;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;


public class HiloServidor extends Thread
{
    private String identificador;
    private Socket socket = null;
    private EscenaServidor escena = null;
    
    
    public HiloServidor(Entry<String,SimpleEntry<Socket,String>> cliente, EscenaServidor escena)
    { 
        this.identificador = cliente.getKey();
        this.socket = cliente.getValue().getKey();
        
        this.escena = escena;
    }
    
    
    @Override
    public void run()
    {
        try
        {  
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
            socket.getOutputStream().write(this.identificador.concat("\n").getBytes());
            socket.getOutputStream().write(this.identificador.concat("\n").getBytes());
            
            while(!socket.isClosed())
            {
                try
                {   
                    escena.getSemaforo().acquire();
                
                    escena.getClientes().replace(identificador,new SimpleEntry<Socket,String>(socket,lector.readLine()));
                    System.out.println("Recibido estado del cliente " + this.identificador + " -> " + escena.getClientes().get(identificador).getValue());
                
                    StringBuilder respuesta = new StringBuilder("");
                    escena.getClientes().forEach
                    (
                        (clave,valor) -> 
                        {    
                            if(!clave.equals(identificador) && !valor.getKey().isClosed())
                            { respuesta.append(valor.getValue()); } 
                        }
                    );
                    respuesta.append("\n");
                    socket.getOutputStream().write(respuesta.toString().getBytes());
                
                    escena.getSemaforo().release();
                } 
                catch (InterruptedException ex)
                {}
            }
        } 
        catch (IOException ex)
        {} 
    }
}
