package com.atarion.game.interfaz.escena.online.servidor;


import com.atarion.game.entidad.jugador.Jugador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;


public class HiloServidor implements Runnable
{
    private Socket cliente = null;
    private Integer clave = 0;
    private HashMap<Integer,Socket> clientes = null;
    
    
    public HiloServidor(Socket cliente,Integer clave,HashMap<Integer,Socket> clientes)
    { 
        this.cliente = cliente; 
        this.clientes = clientes;
        this.clave = clave;
    }
    
    
    @Override
    public void run()
    {
        try
        {  
            BufferedReader lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            
            HashMap<Integer,Socket> notificadores = clientes;
            notificadores.remove(clave);
        
            while(!cliente.isClosed())
            {
                String estado = lector.readLine();
                System.out.println("Got client message: " + estado);
                
                cliente.getOutputStream().write(estado.concat("\n").getBytes());
                notificadores.forEach
                (
                    (identificador,notificador) -> 
                    {  
                        try
                        { notificador.getOutputStream().write(estado.concat("\n").getBytes()); } 
                        catch (IOException ex)
                        {}
                    }
                );
            }
        } 
        catch (IOException ex)
        {} 
    }
}
